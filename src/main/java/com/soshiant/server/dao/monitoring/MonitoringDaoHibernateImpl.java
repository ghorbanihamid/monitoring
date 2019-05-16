package com.soshiant.server.dao.monitoring;

import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.model.monitoring.MainFrame.RMF.ConfiguredMetricsView;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMetricInfo;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMonitoringData;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */



@Repository
public class MonitoringDaoHibernateImpl extends HibernateDaoSupport implements MonitoringDao {

    public List<ConfiguredMetricsView> getServerInfoForRmfMonitoring(int serverId) {

        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(ConfiguredMetricsView.class);
            criteria.add(Restrictions.eq(ServerConstants.SERVER_ID, serverId));
            return  (List<ConfiguredMetricsView>) criteria.list();
        }
        catch (Exception e){
            logger.error(" getServerInfoForRmfMonitoring(), serverId:" + serverId + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    //======================================================================================================================
    public List<ConfiguredMetricsView> getAllServersInfoForRmfMonitoring() {

        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(ConfiguredMetricsView.class);
            return  (List<ConfiguredMetricsView>) criteria.list();
        }
        catch (Exception e){
            logger.error(" getServersInfoForRmfMonitoring() : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    //======================================================================================================================
    public ConfiguredMetricsView getServerMetricInfo(int serverId,String metricId) {

        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(ConfiguredMetricsView.class);
            criteria.add(Restrictions.eq(ServerConstants.SERVER_ID, serverId));
            criteria.add(Restrictions.eq(ServerConstants.RMF_METRIC_ID, metricId));
            return  (ConfiguredMetricsView) criteria.uniqueResult();
        }
        catch (Exception e){
            logger.error(" getServersInfoForRmfMonitoring() : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    //======================================================================================================================
    public List<RMFMonitoringData> getRMFMonitoringDataFromDB(int serverId,int reportDate, int reportFromTime, int reportToTime,String[] metricIdList) {

        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(RMFMonitoringData.class);
            criteria.add(Restrictions.eq(ServerConstants.SERVER_ID, serverId));
            criteria.add(Restrictions.eq(ServerConstants.PERSIAN_TRANS_DATE, reportDate));
            if(reportFromTime > 0)
                criteria.add(Restrictions.between(ServerConstants.RMF_TRANS_START_TIME, reportFromTime, reportToTime));
            if(!CommonUtil.isNullOrEmpty(metricIdList))
                criteria.add(Restrictions.in(ServerConstants.RMF_METRIC_ID, metricIdList));

            criteria.addOrder(Order.asc(ServerConstants.RMF_METRIC_ID));
            criteria.addOrder(Order.asc(ServerConstants.RMF_TRANS_START_TIME));
            criteria.setMaxResults(10);
            return  (List<RMFMonitoringData>) criteria.list();
        }
        catch (Exception e){
            logger.error(" getRMFMonitoringDataFromDB() : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

    }
    //======================================================================================================================
    public RMFMetricInfo getRMFMetricIdInfo(String metricId) {

        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(RMFMetricInfo.class);
            criteria.add(Restrictions.eq(ServerConstants.RMF_METRIC_ID, metricId));
            criteria.setMaxResults(1);
            return  (RMFMetricInfo) criteria.uniqueResult();
        }
        catch (Exception e){
            logger.error(" getRMFMetricIdInfo() : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

    }
    //======================================================================================================================
    public void saveRMFMonitoringDataToDatabase(ConfiguredMetricsView configuredMetricsView,ArrayList<RMFMonitoringData> rmfMonitoringDataList) {

        if(rmfMonitoringDataList != null && !rmfMonitoringDataList.isEmpty()){

            getHibernateTemplate().saveOrUpdateAll(rmfMonitoringDataList);
//            RMFMonitoringData rmfMonitoringData;
//            for(int i = 0; i < rmfMonitoringDataList.size();i++){
//                rmfMonitoringData = rmfMonitoringDataList.get(i);
//                getHibernateTemplate().save(rmfMonitoringData);
//            }
        }

    }

    //======================================================================================================================


}
