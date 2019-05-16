package com.soshiant.server.dao.parameters;

import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.dao.DaoException;
import com.soshiant.server.model.parameters.*;
import com.soshiant.server.model.position.Positions;
import com.soshiant.server.model.server.Server;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

@Repository
public class ParametersDaoHibernateImpl extends HibernateDaoSupport implements ParametersDao {

    public static String OBJECT_STAFF_COUNTER = "STAFF_COUNTER";
    public static String RMF_MONITORING_SERVICE_STATUS = "RMF_MONITORING_SERVICE_STATUS";
    public static String SAVE_RMF_MONITORING_DATA = "SAVE_RMF_MONITORING_DATA";

    public static String CHART_REFRESH_TIME_MINUTE = "CHART_REFRESH_TIME_MINUTE";

    public static String SEND_SMS_SERVICE_STATUS = "SEND_SMS_SERVICE_STATUS";
    public static String RECEIVE_SMS_SERVICE_STATUS = "RECEIVE_SMS_SERVICE_STATUS";

    public static String SEND_EMAIL_SERVICE_STATUS = "SEND_EMAIL_SERVICE_STATUS";


    public List<Bank> getBanksList() throws DaoException {

        return getHibernateTemplate().loadAll(Bank.class);
    }

    public List<City> getCitiesList() throws DaoException {

        return getHibernateTemplate().loadAll(City.class);
    }


    public List<Positions> getPositionsList() {

        return getHibernateTemplate().loadAll(Positions.class);
    }
    //==================================================================================================================
    public List<Server> getMainFrameServersList() {

        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(Server.class);
            criteria.add(Restrictions.eq(ServerConstants.SERVER_TYPE, ServerConstants.SERVER_TYPE_MAINFRAME));
            return  (List<Server>) criteria.list();
        }
        catch (Exception e){
            logger.error(" getMainFrameServersList() : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    //==================================================================================================================
    public boolean getRmfMonitoringServiceStatus() {

        Parameters prm = (Parameters)getHibernateTemplate().getSessionFactory().getCurrentSession()
                                                  .createCriteria(Parameters.class)
                                                  .add(Restrictions.eq(ServerConstants.PARAMETER_NAME, SAVE_RMF_MONITORING_DATA))
                                                  .setMaxResults(1)
                                                  .uniqueResult();
        boolean rmfMonitoringStatus = Boolean.parseBoolean(prm.getParameterValue());
        return rmfMonitoringStatus;

    }

    //==================================================================================================================
    public boolean getSaveRmfMonitoringDataServiceStatus() {

        Parameters prm = (Parameters)getHibernateTemplate().getSessionFactory().getCurrentSession()
                                                  .createCriteria(Parameters.class)
                                                  .add(Restrictions.eq(ServerConstants.PARAMETER_NAME, RMF_MONITORING_SERVICE_STATUS))
                                                  .setMaxResults(1)
                                                  .uniqueResult();
        boolean saveRmfMonitoringStatus = Boolean.parseBoolean(prm.getParameterValue());
        return saveRmfMonitoringStatus;

    }

    //==================================================================================================================
    public int getChartRefreshTime() {

        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(Parameters.class);
            criteria.add(Restrictions.eq(ServerConstants.PARAMETER_NAME, CHART_REFRESH_TIME_MINUTE));
            criteria.setMaxResults(1);
            Parameters prm = (Parameters) criteria.uniqueResult();
            int refreshTime = Integer.parseInt(prm.getParameterValue());
            return refreshTime;
        }
        catch (Exception e){
            logger.error(" getChartRefreshTime() : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    //==================================================================================================================
    public boolean getSendSmsServiceStatus() {

        Parameters prm = (Parameters)getHibernateTemplate().getSessionFactory().getCurrentSession()
                                                  .createCriteria(Parameters.class)
                                                  .add(Restrictions.eq(ServerConstants.PARAMETER_NAME, SEND_SMS_SERVICE_STATUS))
                                                  .setMaxResults(1)
                                                  .uniqueResult();
        boolean sendSmsServiceStatus = Boolean.parseBoolean(prm.getParameterValue());
        return sendSmsServiceStatus;

    }

    //==================================================================================================================
    public boolean getReceiveSmsServiceStatus() {

        Parameters prm = (Parameters)getHibernateTemplate().getSessionFactory().getCurrentSession()
                                                  .createCriteria(Parameters.class)
                                                  .add(Restrictions.eq(ServerConstants.PARAMETER_NAME, RECEIVE_SMS_SERVICE_STATUS))
                                                  .setMaxResults(1)
                                                  .uniqueResult();
        boolean sendSmsServiceStatus = Boolean.parseBoolean(prm.getParameterValue());
        return sendSmsServiceStatus;

    }

    //==================================================================================================================
    public boolean getSendEmailServiceStatus() {

        Parameters prm = (Parameters)getHibernateTemplate().getSessionFactory().getCurrentSession()
                                                  .createCriteria(Parameters.class)
                                                  .add(Restrictions.eq(ServerConstants.PARAMETER_NAME, SEND_EMAIL_SERVICE_STATUS))
                                                  .setMaxResults(1)
                                                  .uniqueResult();
        boolean sendEmailServiceStatus = Boolean.parseBoolean(prm.getParameterValue());
        return sendEmailServiceStatus;

    }

    //==================================================================================================================
    public String makeNewStaffCounter(short termYear) {

        Parameters prm = (Parameters)getHibernateTemplate().getSessionFactory().getCurrentSession()
                                                  .createCriteria(Parameters.class)
                                                  .add(Restrictions.eq(ServerConstants.PARAMETER_YEAR, termYear))
                                                  .add(Restrictions.eq(ServerConstants.PARAMETER_NAME, OBJECT_STAFF_COUNTER))
                                                  .setMaxResults(1)
                                                  .uniqueResult();
        String newStdCounter = String.valueOf(Integer.parseInt(prm.getParameterValue()) + 1);

        saveParameterNewValue(prm.getParameterId(), String.valueOf(newStdCounter));

        int counterLength = newStdCounter.trim().length();
        for(int i = 1; i <= 4 - counterLength; i++){
            newStdCounter = "0" + newStdCounter;
        }
        return newStdCounter;
    }

    //==================================================================================================================
    public String makeNewSiteCounter() {

        Parameters prm = (Parameters)getHibernateTemplate().getSessionFactory().getCurrentSession()
                                                  .createCriteria(Parameters.class)
                                                  .add(Restrictions.eq(ServerConstants.PARAMETER_NAME, OBJECT_STAFF_COUNTER))
                                                  .setMaxResults(1)
                                                  .uniqueResult();
        String newSiteCounter = String.valueOf(Integer.parseInt(prm.getParameterValue()) + 1);

        saveParameterNewValue(prm.getParameterId(), String.valueOf(newSiteCounter));

        int counterLength = newSiteCounter.trim().length();
        for(int i = 1; i <= 4 - counterLength; i++){
            newSiteCounter = "0" + newSiteCounter;
        }
        return newSiteCounter;
    }

    //==================================================================================================================
    public String makeNewServerCounter() {

        Parameters prm = (Parameters)getHibernateTemplate().getSessionFactory().getCurrentSession()
                                                  .createCriteria(Parameters.class)
                                                  .add(Restrictions.eq(ServerConstants.PARAMETER_NAME, OBJECT_STAFF_COUNTER))
                                                  .setMaxResults(1)
                                                  .uniqueResult();
        String newServerCounter = String.valueOf(Integer.parseInt(prm.getParameterValue()) + 1);

        saveParameterNewValue(prm.getParameterId(), String.valueOf(newServerCounter));

        int counterLength = newServerCounter.trim().length();
        for(int i = 1; i <= 4 - counterLength; i++){
            newServerCounter = "0" + newServerCounter;
        }
        return newServerCounter;
    }

    //==================================================================================================================
    public boolean saveParameterNewValue(short parameterId,String newValue) {

        String qryStr = new StringBuilder().append(" UPDATE Parameters set parameterValue = ").append(newValue).append(" WHERE  parameterId = ").append(parameterId).toString();

        getHibernateTemplate().bulkUpdate(qryStr);
        return true;

    }

}
