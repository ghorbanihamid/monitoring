package com.soshiant.server.dao.log;

import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.model.log.AppLog;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

@Repository
public class AppLogDaoHibernateImpl extends HibernateDaoSupport implements AppLogDao {

//======================================================================================================================
    public List<AppLog> getLogByDoerId(int customerId, int logDate) {

        if(logDate == 0){
            return (List<AppLog>) getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .createCriteria(AppLog.class)
                    .add(Restrictions.eq(ServerConstants.DOER_ID, customerId))
                    .list();
        }
        else {
            return (List<AppLog>) getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .createCriteria(AppLog.class)
                    .add(Restrictions.or(
                            Restrictions.eq(ServerConstants.DOER_ID, customerId),
                            Restrictions.eq(ServerConstants.LOG_DATE, logDate))
                    )
                    .list();
        }
    }
//======================================================================================================================
    public List<AppLog> getLogByDate(int logDate) {

        return (List<AppLog>) getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(AppLog.class)
                .add(Restrictions.eq(ServerConstants.LOG_DATE, logDate))
                .list();
    }
//======================================================================================================================
    public void insertLog(AppLog appLog) {

        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            session.save(appLog);
        }
        catch (Exception e){
            logger.error(" insertLog() Exception,Data : " + appLog.toString() + ", Message :" + e.getMessage());
            e.printStackTrace();
            throw e;
        }

    }
//======================================================================================================================

}
