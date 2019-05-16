package com.soshiant.server.dao.monitoring;

import com.soshiant.server.model.monitoring.MainFrame.RMF.ConfiguredMetricsView;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMetricInfo;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMonitoringData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 4:31 PM
 */
public interface MonitoringDao {

    public List<ConfiguredMetricsView> getServerInfoForRmfMonitoring(int serverId);

    public ConfiguredMetricsView getServerMetricInfo(int serverId,String metricId);

    public List<ConfiguredMetricsView> getAllServersInfoForRmfMonitoring();

    public List<RMFMonitoringData> getRMFMonitoringDataFromDB(int serverId,int reportDate, int reportFromTime, int reportToTime,String[] metricIdList);

    public RMFMetricInfo getRMFMetricIdInfo(String metricId);

    public void saveRMFMonitoringDataToDatabase(ConfiguredMetricsView configuredMetricsView,ArrayList<RMFMonitoringData> rmfMonitoringDataList);

}
