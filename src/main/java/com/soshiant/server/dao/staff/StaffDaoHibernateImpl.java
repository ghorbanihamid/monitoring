package com.soshiant.server.dao.staff;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.model.staff.Staff;
import com.soshiant.server.model.staff.StaffShift;
import com.soshiant.server.model.staff.StaffShiftView;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

@Repository
public class StaffDaoHibernateImpl extends HibernateDaoSupport implements StaffDao {

    private final static Logger logger = Logger.getLogger(StaffDaoHibernateImpl.class);

    //======================================================================================================================
    public List<Staff> getStaffListByBranchId(short branchId) {

        logger.debug("StaffDaoHibernateImpl.getStaffListByBranchId( ), branch id: " + branchId);

        if(branchId == 0){
            return (List<Staff>) getHibernateTemplate().loadAll(Staff.class);
        }
        else {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(Staff.class);
            criteria.add(Restrictions.eq(ServerConstants.BRANCH_ID, branchId));
            return  (List<Staff>) criteria.list();
        }
    }
    //======================================================================================================================
    public List<Staff> getStaffListByNationalCode(long searchString) {

        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Staff.class);
        criteria.add(Restrictions.eq(ServerConstants.NATIONAL_CODE, searchString));
        return  (List<Staff>) criteria.list();
    }
    //======================================================================================================================
    public List<Staff> getStaffListByIdNumber(long searchString) {

        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Staff.class);
        criteria.add(Restrictions.eq(ServerConstants.ID_NUMBER, searchString));
        return  (List<Staff>) criteria.list();
    }
    //======================================================================================================================
    public List<Staff> getStaffListByEmailAddress(String emailAddress) {

        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Staff.class);
        criteria.add(Restrictions.eq(ServerConstants.EMAIL_ADDRESS, emailAddress));
        return  (List<Staff>) criteria.list();
    }
    //======================================================================================================================
    public List<Staff> getStaffListByCellphone(String cellphone) {

        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Staff.class);
        criteria.add(Restrictions.eq(ServerConstants.CELL_PHONE, cellphone));
        return  (List<Staff>) criteria.list();
    }
    //======================================================================================================================
    public List<StaffShiftView> getDailyShiftList(int shiftDate,int staffId) {

        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(StaffShiftView.class);
        if(shiftDate > 0)
            criteria.add(Restrictions.eq(ServerConstants.SHIFT_DATE, shiftDate));
        if(staffId > 0)
            criteria.add(Restrictions.eq(ServerConstants.STAFF_ID, staffId));
        return  (List<StaffShiftView>) criteria.list();
    }
    //======================================================================================================================
    public Staff getStaffInfo(int staffId) {

        logger.debug("StaffDaoHibernateImpl.getStaffInfo(), staffId: " + staffId);
        return getHibernateTemplate().get(Staff.class, staffId);
    }
    //======================================================================================================================
    public void saveStaffInfo(Staff staff) {

        logger.debug("StaffDaoHibernateImpl.saveStaff(), staffName: " + staff.getStaffEnName());
        staff.setStaffEnName(StringUtils.capitalize(staff.getStaffEnName().toLowerCase()));
        staff.setStaffEnFamily(StringUtils.capitalize(staff.getStaffEnFamily().toLowerCase()));
        getHibernateTemplate().saveOrUpdate(staff);
    }

    //======================================================================================================================
    public void saveDailyShiftInfo(StaffShift staffShift) {

        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            session.saveOrUpdate(staffShift);
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    //======================================================================================================================
    public boolean saveStaffEmailAddress(int staffId, String newEmailAddress) {

        String qryStr = "UPDATE Staff set emailAddress = '"+ newEmailAddress +"' WHERE staffId = "+ staffId;
        getHibernateTemplate().bulkUpdate(qryStr);
        return true;

    }
    //======================================================================================================================

}
