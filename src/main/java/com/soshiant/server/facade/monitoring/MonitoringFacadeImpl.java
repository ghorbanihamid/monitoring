package com.soshiant.server.facade.monitoring;

import com.soshiant.common.util.ReportUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.SimpleFacadeResult;
import com.soshiant.server.model.monitoring.MainFrame.RMF.ConfiguredMetricsView;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMetricInfo;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMonitoringData;
import com.soshiant.server.model.parameters.KeyValueObject;
import com.soshiant.server.service.log.AppLogService;
import com.soshiant.server.service.monitoring.MonitoringService;
import com.soshiant.server.thread.RMFMonitoringThread;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class MonitoringFacadeImpl implements MonitoringFacade {

    private Logger log = Logger.getLogger(MonitoringFacadeImpl.class);
    private String actionName;
    private String logMessage = "";

    public static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    public static HashMap<String, ScheduledFuture> rmfMonitoringScheduledTasksList = new HashMap<String, ScheduledFuture>();

    private MonitoringService monitoringService;
    private AppLogService appLogService;

    public void setMonitoringService(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    public void setAppLogService(AppLogService appLogService) {
        this.appLogService = appLogService;
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getRMFMonitoringDataFromDB(int serverId,int reportDate, int reportFromTime, int reportToTime,String[] metricIdList) {

        try {
            List<RMFMonitoringData> resultData = monitoringService.getRMFMonitoringDataFromDB(serverId,reportDate, reportFromTime, reportToTime,metricIdList);
            SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(resultData);
            return simpleFacadeResult.success();
        }
        catch (Exception e){
            return null;
        }
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getRMFMetricIdInfo(String metricId) {

        try {
            RMFMetricInfo resultData = monitoringService.getRMFMetricIdInfo(metricId);
            SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(resultData);
            return simpleFacadeResult.success();
        }
        catch (Exception e){
            return null;
        }
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult generateChartJsonData(String metricId,List<KeyValueObject> dataList) {

        try {
            ConfiguredMetricsView rmfMetricInfo = null;//monitoringService.getRMFMetricIdInfo(metricId);
            String jsonData = ReportUtil.generateChartJsonDataFromList(rmfMetricInfo,dataList);
            SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(jsonData);
            return simpleFacadeResult.success();
        }
        catch (Exception e){
            return null;
        }
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult generateChartJsonData(ConfiguredMetricsView metricInfo,List<KeyValueObject> dataList) {

        try {
            String jsonData = ReportUtil.generateChartJsonDataFromList(metricInfo,dataList);
            SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(jsonData);
            return simpleFacadeResult.success();
        }
        catch (Exception e){
            return null;
        }
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult createRMFMonitoringThreads() {

        try {
//            final ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(beeper, taskDelay, taskPeriod, TimeUnit.SECONDS);
            List<ConfiguredMetricsView> configuredMetricsViewList = monitoringService.getRmfURLsForAllServers();
            if (configuredMetricsViewList == null || configuredMetricsViewList.size() == 0) {
                this.logMessage = this.actionName + " : error monitoringService.getMonitoringURLs() ";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, ServerConstants.NoSqlText, this.logMessage, "", ServerConstants.EmptyModel);
                return null;
            }

            for (ConfiguredMetricsView configuredMetricsView : configuredMetricsViewList) {

                int taskDelay = 60;
                int taskPeriod = configuredMetricsView.getThreadPeriod() > 0 ? configuredMetricsView.getThreadPeriod() : 60;

                RMFMonitoringThread rmfMonitoringThreadObject = new RMFMonitoringThread(configuredMetricsView);
                Thread rmfMonitoringThread = new Thread(rmfMonitoringThreadObject);
                rmfMonitoringThread.setName(configuredMetricsView.getMetricId());
                // rmfMonitoringThread.start();
                ScheduledFuture rmfMonitoringScheduledService = scheduledExecutorService.scheduleAtFixedRate(rmfMonitoringThread, taskDelay, taskPeriod, TimeUnit.SECONDS);
                rmfMonitoringScheduledTasksList.put(configuredMetricsView.getServerRmfServiceId(),rmfMonitoringScheduledService);
//                RMFMonitoringThread.rmfMonitoringDataMap.put(rmfInfo.getServerId(),null);
            }

//            scheduledExecutorService.shutdown();

            SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(null);
            return simpleFacadeResult.success();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //======================================================================================================================
    public void get(){

        if(rmfMonitoringScheduledTasksList != null && rmfMonitoringScheduledTasksList.size() > 0){
            for (Object scheduledTasksObject : rmfMonitoringScheduledTasksList.entrySet()) {
                ScheduledExecutorService scheduledService = (ScheduledExecutorService) scheduledTasksObject;
                scheduledService.shutdownNow();
            }
        }
    }
    //======================================================================================================================
    //======================================================================================================================
    //@Override
    public Class getKeysEnumClass() {
        return DataKeys.class;
    }
}
