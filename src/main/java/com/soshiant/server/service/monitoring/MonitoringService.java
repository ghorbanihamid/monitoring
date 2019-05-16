package com.soshiant.server.service.monitoring;


import com.soshiant.server.model.monitoring.MainFrame.RMF.ConfiguredMetricsView;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMetricInfo;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMonitoringData;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public interface MonitoringService {

    public ArrayList<RMFMonitoringData> getRMFMonitoringData(ConfiguredMetricsView configuredMetricsView);

    public List<ConfiguredMetricsView> getRmfURLsForServer(int serverId);

    public List<ConfiguredMetricsView> getRmfURLsForAllServers();

    public List<RMFMonitoringData> getRMFMonitoringDataFromDB(int serverId,int reportDate, int reportFromTime, int reportToTime,String[] metricIdList);

    public RMFMetricInfo getRMFMetricIdInfo(String metricId);

    public void saveRMFMonitoringData(ConfiguredMetricsView configuredMetricsView,ArrayList<RMFMonitoringData> rmfMonitoringDataList);

}
