package com.soshiant.server.dao.staffPosition;

import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.model.staff.StaffPosition;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 10/6/11
 * Time: 11:21 AM
 */

@Repository
public class StaffPositionDaoHibernateImpl extends HibernateDaoSupport implements StaffPositionDao {
    private final static Logger logger = Logger.getLogger(StaffPositionDaoHibernateImpl.class);

    public List<StaffPosition> getStaffPositionListInfo(int staffId) {

        return (List<StaffPosition>) getHibernateTemplate().getSessionFactory().getCurrentSession()
                                                       .createCriteria(StaffPosition.class)
                                                       .add(Restrictions.eq(ServerConstants.STAFF_ID, staffId))
                                                       .list();
    }

    public StaffPosition getStaffPositionInfo(int staffId, byte positionId) {

        return (StaffPosition) getHibernateTemplate().getSessionFactory().getCurrentSession()
                                             .createCriteria(StaffPosition.class)
                                             .add(Restrictions.and(
                                                     Restrictions.eq(ServerConstants.STAFF_ID, staffId),
                                                     Restrictions.eq(ServerConstants.POSITION_ID, positionId))
                                             )
                                             .setMaxResults(1)
                                             .uniqueResult();
    }

    public void saveStaffPosition(StaffPosition staffPosition) {

        getHibernateTemplate().save(staffPosition);
    }

    public int activateStaff(int staffId) {

//        String qryStr = " UPDATE Staff set staffStatus = " + ServerConstants.STAFF_STATUS_ACTIVE +
//                        " WHERE staffId = " +  staffId;

        String qryStr = new StringBuilder().append(" UPDATE Staff set staffStatus = ").append(ServerConstants.STAFF_STATUS_ACTIVE).append(" WHERE staffId = ").append(staffId).toString();
        return getHibernateTemplate().bulkUpdate(qryStr);
    }

    public int toggleStaffPositionStatus(StaffPosition staffPosition) {

//        String qryStr = " UPDATE StaffPosition set positionStatus = " + staffPosition.getPositionStatus() + " , toggleStatusDate = " + staffPosition.getToggleStatusDate() +
//                        " WHERE staffId = " +  staffPosition.getStaffId() +
//                        " AND positionId = " + staffPosition.getPositionId();

        String qryStr = String.format("UPDATE StaffPosition set positionStatus = %s , toggleStatusDate = %d WHERE staffId = %d AND positionId = %s", staffPosition.getPositionStatus(), staffPosition.getToggleStatusDate(), staffPosition.getStaffId(), staffPosition.getPositionId());
        return getHibernateTemplate().bulkUpdate(qryStr);
    }

}
