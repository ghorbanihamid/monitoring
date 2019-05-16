package com.soshiant.server.facade.monitoring;


import com.soshiant.server.facade.Facade;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.model.monitoring.MainFrame.RMF.ConfiguredMetricsView;
import com.soshiant.server.model.parameters.KeyValueObject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public interface MonitoringFacade extends Facade {

    public enum DataKeys {
    }

    public FacadeResult getRMFMonitoringDataFromDB(int serverId,int reportDate, int reportFromTime, int reportToTime,String[] metricIdList);

    public FacadeResult getRMFMetricIdInfo(String metricId);

    public FacadeResult generateChartJsonData(String metricId,List<KeyValueObject> dataList);

    public FacadeResult generateChartJsonData(ConfiguredMetricsView metricInfo,List<KeyValueObject> dataList);

    public FacadeResult createRMFMonitoringThreads();

}
