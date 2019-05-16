package com.soshiant.server.thread;

import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.model.monitoring.MainFrame.RMF.ConfiguredMetricsView;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMonitoringData;
import com.soshiant.server.model.parameters.AlarmMessageInfo;
import com.soshiant.server.service.monitoring.MonitoringService;
import com.soshiant.server.service.monitoring.MonitoringServiceImpl;
import com.soshiant.server.service.parameters.SmsService;
import com.soshiant.server.stub.MonitoringStub;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hubert
 * Date: 4/21/12
 * Time: 11:08 AM
 **/
public class RMFMonitoringThread extends Thread{

    Logger log = Logger.getLogger(this.getClass().getName());

    public static HashMap<Integer, HashMap<String,List> > rmfMonitoringDataMap1 = new HashMap<>();
    public static HashMap<String, List> rmfMonitoringDataMap = new HashMap<>();
    private static int dataSizeInMemory = 20;


    boolean running = true;

    private int threadCounter;

    private ConfiguredMetricsView configuredMetricsView;

    private MonitoringService monitoringService;
    private SmsService smsService;

    public void setMonitoringService(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }

    //======================================================================================================================
    public RMFMonitoringThread(ConfiguredMetricsView configuredMetricsView) {
        this.threadCounter = 0;
        this.configuredMetricsView = configuredMetricsView;
    }
    //======================================================================================================================
    public RMFMonitoringThread(String metricId, String rmfUrl, String rmfUserName, String rmfPassword) {

        configuredMetricsView = new ConfiguredMetricsView();
        configuredMetricsView.setMetricId(metricId);
        configuredMetricsView.setRmfUrl(rmfUrl);
        configuredMetricsView.setRmfUserName(rmfUserName);
        configuredMetricsView.setRmfPassword(rmfPassword);
    }
    //======================================================================================================================
    public void run() {


        try{
            if(running){
                threadCounter++;
                MonitoringServiceImpl monitoringService = (MonitoringServiceImpl) ContextLoader.getCurrentWebApplicationContext().getBean("monitoringService");
                MonitoringStub monitoringStub = (MonitoringStub) ContextLoader.getCurrentWebApplicationContext().getBean("monitoringStub");
                monitoringService.setMonitoringStub(monitoringStub);
                this.monitoringService = monitoringService;
                ArrayList<RMFMonitoringData> rmfMonitoringDataList = monitoringService.getRMFMonitoringData(configuredMetricsView);
                System.out.println("ServerRmfServiceId : " + configuredMetricsView.getServerRmfServiceId() +", Count : " + threadCounter);
                if(rmfMonitoringDataList != null) {
                    addRMFMonitoringDataToMemory(configuredMetricsView, rmfMonitoringDataList);
                    checkForAlarm(this.configuredMetricsView, rmfMonitoringDataList);
                    monitoringService.saveRMFMonitoringData(this.configuredMetricsView, rmfMonitoringDataList);

                log.info("threadCount : "+ threadCounter);
//                log.info(CommonUtil.listToString(rmfMonitoringDataList));
                }
            }
        }
        catch (Exception e) {
            log.error("RMFMonitoringThread.run() Exception " + configuredMetricsView.toString() + "\r\n Message : " + e.getMessage());
            e.printStackTrace();
        }
    }
    //======================================================================================================================
    public void addRMFMonitoringDataToMemory(ConfiguredMetricsView configuredMetricsView,ArrayList<RMFMonitoringData> rmfMonitoringDataList) {

        try{
            String serviceId = configuredMetricsView.getServerRmfServiceId();
            if(rmfMonitoringDataList != null) {
                LinkedList dataList;
                if (rmfMonitoringDataMap.containsKey(serviceId)) {
                    dataList = (LinkedList)rmfMonitoringDataMap.get(serviceId);
                    dataList.add(rmfMonitoringDataList);
                    if(dataList.size() > dataSizeInMemory)
                        dataList.removeFirst();
                    rmfMonitoringDataMap.replace(serviceId, dataList);
                }
                else {
                    dataList = new LinkedList<>();
                    dataList.add(rmfMonitoringDataList);
                    rmfMonitoringDataMap.put(serviceId, dataList);
                }
            }
        }
        catch (Exception e) {
            log.error("addRMFMonitoringDataToMemory() Exception " + configuredMetricsView.toString() + "\r\n Message : " + e.getMessage());
        }
    }
    //======================================================================================================================
    public void checkForAlarm(ConfiguredMetricsView configuredMetricsView,ArrayList<RMFMonitoringData> rmfMonitoringDataList) {

        try{
            int criticalLessValueCounter = 0;
            int criticalMoreValueCounter = 0;

            int criticalLessValue = configuredMetricsView.getCriticalLessValue();
            int criticalMoreValue = configuredMetricsView.getCriticalMoreValue();

            String metricValue = "";
            String messageText = "";
            if( criticalLessValue > 0) {
                for (RMFMonitoringData rmfMonitoringData : rmfMonitoringDataList) {
                    if (Long.parseLong(rmfMonitoringData.getRowValue()) < criticalLessValue) {
                        criticalLessValueCounter++;
                        metricValue = rmfMonitoringData.getRowValue();
                    }
                }
                messageText = ServerConstants.SMS_DEAR_NEW_USER_TEXT + ServerConstants.SMS_NEW_LINE +
                              ServerConstants.LABEL_RMF_METRIC_DESC + configuredMetricsView.getMetricDesc() + ServerConstants.SMS_NEW_LINE +
                              ServerConstants.LABEL_VALUE + metricValue + ServerConstants.SMS_NEW_LINE;

                smsService.saveAlarmInfo(new AlarmMessageInfo(ServerConstants.MESSAGE_TYPE_SMS, messageText));
            }

            if( criticalMoreValue > 0) {
                for (RMFMonitoringData rmfMonitoringData : rmfMonitoringDataList) {
                    if (Long.parseLong(rmfMonitoringData.getRowValue()) > criticalMoreValue) {
                        criticalLessValueCounter++;
                        metricValue = rmfMonitoringData.getRowValue();
                    }
                }
                messageText = ServerConstants.SMS_DEAR_NEW_USER_TEXT + ServerConstants.SMS_NEW_LINE +
                              ServerConstants.LABEL_RMF_METRIC_DESC + configuredMetricsView.getMetricDesc() + ServerConstants.SMS_NEW_LINE +
                              ServerConstants.LABEL_VALUE + metricValue + ServerConstants.SMS_NEW_LINE;

                smsService.saveAlarmInfo(new AlarmMessageInfo(ServerConstants.MESSAGE_TYPE_SMS,messageText));
            }

        }
        catch (Exception e) {
            log.error("checkForAlarm() Exception " + configuredMetricsView.toString() + "\r\n Message : " + e.getMessage());
        }
    }
    //======================================================================================================================
    public void addRMFMonitoringDataToMemory1(ConfiguredMetricsView configuredMetricsView,ArrayList<RMFMonitoringData> rmfMonitoringDataList) {

        try{
            if(rmfMonitoringDataList != null) {
                LinkedList dataList;
                HashMap<String,List> tempServerDataMap;
                if (rmfMonitoringDataMap1.containsKey(configuredMetricsView.getServerId())) {
                    tempServerDataMap = rmfMonitoringDataMap1.get(configuredMetricsView.getServerId());

                    if (tempServerDataMap.containsKey(configuredMetricsView.getMetricId())) {
                        dataList = (LinkedList)tempServerDataMap.get(configuredMetricsView.getMetricId());
                        dataList.add(rmfMonitoringDataList);
                        if(dataList.size() > dataSizeInMemory)
                            dataList.removeFirst();
                        tempServerDataMap.replace(configuredMetricsView.getMetricId(), dataList);
                    }
                    else {
                        dataList = new LinkedList<>();
                        dataList.add(rmfMonitoringDataList);
                        tempServerDataMap.put(configuredMetricsView.getMetricId(), dataList);
                    }
                    rmfMonitoringDataMap1.replace(configuredMetricsView.getServerId(),tempServerDataMap);
                }
                else { // server is not in the map
                    dataList = new LinkedList<>();
                    dataList.add(rmfMonitoringDataList);
                    tempServerDataMap = new HashMap<>();
                    tempServerDataMap.put(configuredMetricsView.getMetricId(), dataList);
                    rmfMonitoringDataMap1.put(configuredMetricsView.getServerId(),tempServerDataMap);
                }
            }
        }
        catch (Exception e) {
            log.error("addRMFMonitoringDataToMemory1() Exception " + configuredMetricsView.toString() + "\r\n Message : " + e.getMessage());
        }
    }
    //======================================================================================================================

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
