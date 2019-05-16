package com.soshiant.server.service.monitoring;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.dao.monitoring.MonitoringDao;
import com.soshiant.server.model.monitoring.MainFrame.RMF.ConfiguredMetricsView;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMetricInfo;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMonitoringData;
import com.soshiant.server.service.log.AppLogService;
import com.soshiant.server.stub.MonitoringStub;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;


/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class MonitoringServiceImpl implements MonitoringService {

    Logger log = Logger.getLogger(MonitoringServiceImpl.class);

    MonitoringStub monitoringStub;
    private MonitoringDao monitoringDao;
    private AppLogService appLogService;


    public void setMonitoringStub(MonitoringStub monitoringStub) {
        this.monitoringStub = monitoringStub;
    }

    public void setMonitoringDao(MonitoringDao monitoringDao) {
        this.monitoringDao = monitoringDao;
    }

    public void setAppLogService(AppLogService appLogService) {
        this.appLogService = appLogService;
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public ArrayList<RMFMonitoringData> getRMFMonitoringData(ConfiguredMetricsView configuredMetricsView) {

        try {
            String rmfXmlResult = monitoringStub.getRMFMonitoringXMLFromServer(configuredMetricsView);
            return getDataFromXMLResult(configuredMetricsView,rmfXmlResult);
        }
        catch (Exception e){
            log.error("getMonitoringDataFromStub() Exception, rmfUrl : " + configuredMetricsView.toString() + "\r\n Message : "+ e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public List<ConfiguredMetricsView> getRmfURLsForServer(int serverId) {

        List<ConfiguredMetricsView> tempConfiguredMetricsViewList = new ArrayList<>();
        List<ConfiguredMetricsView> configuredMetricsViewList = monitoringDao.getServerInfoForRmfMonitoring(serverId);
        for (int i = 0; i < configuredMetricsViewList.size(); i++) {
            ConfiguredMetricsView configuredMetricsView = configuredMetricsViewList.get(i);
            createRmfUrl(configuredMetricsView);
            tempConfiguredMetricsViewList.add(configuredMetricsView);
        }
        return tempConfiguredMetricsViewList;
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public List<ConfiguredMetricsView> getRmfURLsForAllServers() {

        List<ConfiguredMetricsView> tempConfiguredMetricsViewList = new ArrayList<>();
        List<ConfiguredMetricsView> configuredMetricsViewList = monitoringDao.getAllServersInfoForRmfMonitoring();
        for (int i = 0; i < configuredMetricsViewList.size(); i++) {
            ConfiguredMetricsView configuredMetricsView = configuredMetricsViewList.get(i);
            createRmfUrl(configuredMetricsView);
            tempConfiguredMetricsViewList.add(configuredMetricsView);
        }
        return tempConfiguredMetricsViewList;
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public List<RMFMonitoringData> getRMFMonitoringDataFromDB(int serverId,int reportDate, int reportFromTime, int reportToTime,String[] metricIdList) {
        try {
            List<RMFMonitoringData> resultData = monitoringDao.getRMFMonitoringDataFromDB(serverId, reportDate, reportFromTime, reportToTime, metricIdList);
            return resultData;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public RMFMetricInfo getRMFMetricIdInfo(String metricId) {
        try {
            RMFMetricInfo resultData = monitoringDao.getRMFMetricIdInfo(metricId);
            return resultData;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public void saveRMFMonitoringData(ConfiguredMetricsView configuredMetricsView,ArrayList<RMFMonitoringData> rmfMonitoringDataList) {

        if(ServerConstants.saveRmfMonitoringDataIsEnabled) {
            monitoringDao.saveRMFMonitoringDataToDatabase(configuredMetricsView, rmfMonitoringDataList);
        }
        else {

        }
    }
    //======================================================================================================================
    private void createRmfUrl(ConfiguredMetricsView configuredMetricsView){

        String rmfUrl = "http://" + configuredMetricsView.getServerIp() + ":" + configuredMetricsView.getRmfPort() + "/gpm/perform/perform.xml?resource=\"";
        if (configuredMetricsView.getResourceType().equalsIgnoreCase("sysplex")) {
            rmfUrl += configuredMetricsView.getzOSSysplexName() + configuredMetricsView.getResourceName() + "\"&id=" + configuredMetricsView.getMetricId();
        }
        else {
            rmfUrl += configuredMetricsView.getzOSSystemId() + configuredMetricsView.getResourceName() + "\"&id=" + configuredMetricsView.getMetricId();
        }
        configuredMetricsView.setRmfUrl(rmfUrl);
    }
    //======================================================================================================================
    ArrayList<RMFMonitoringData> getDataFromXMLResult(ConfiguredMetricsView configuredMetricsView,String xmlData){

        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputStream xmlDataStream = new ByteArrayInputStream(xmlData.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(xmlDataStream);
            if(doc == null){
                return null;
            }
            doc.getDocumentElement().normalize();

            String metricDescription = "-1";
            NamedNodeMap metricNodeMap = doc.getElementsByTagName("metric").item(0).getAttributes();
            if(metricNodeMap != null)
                metricDescription   = metricNodeMap.item(0).getNodeValue();

            String gathererInterval = "-1";
            NamedNodeMap gathererIntervalNodeMap = doc.getElementsByTagName("gathererinterval").item(0).getAttributes();
            if(gathererIntervalNodeMap != null && gathererIntervalNodeMap.getLength() > 0)
                gathererInterval = gathererIntervalNodeMap.item(0).getNodeValue();

            String dataRange = "-1";
            NamedNodeMap dataRangeNodeMap = doc.getElementsByTagName("data-range").item(0).getAttributes();
            if(dataRangeNodeMap != null && dataRangeNodeMap.getLength() > 0)
                dataRange = dataRangeNodeMap.item(0).getNodeValue();

            String localStartTime = "-1";
            String localEndTime = "-1";
            String utcStartTime = "-1";
            String utcEndTime = "-1";

            NamedNodeMap timeRangeNodeMap = doc.getElementsByTagName("timerange").item(0).getAttributes();
            if(timeRangeNodeMap != null && timeRangeNodeMap.getLength() > 0) {
                localStartTime = timeRangeNodeMap.getNamedItem("localstart").getNodeValue();
                localEndTime = timeRangeNodeMap.getNamedItem("localend").getNodeValue();
                utcStartTime = timeRangeNodeMap.getNamedItem("utcstart").getNodeValue();
                utcEndTime = timeRangeNodeMap.getNamedItem("utcend").getNodeValue();
            }
            String localStartDateTime = "-1";
            String localEndDateTime = "-1";
            NamedNodeMap timeStampNodeMap = doc.getElementsByTagName("timestamp").item(0).getAttributes();
            if(timeStampNodeMap != null && timeStampNodeMap.getLength() > 0) {
                localStartDateTime = timeStampNodeMap.getNamedItem("localstart").getNodeValue();
                localEndDateTime = timeStampNodeMap.getNamedItem("localtime").getNodeValue();
            }
            int spaceIndex = localStartDateTime.indexOf(" ");
            String startTransTime = StringUtils.replace(CommonUtil.nvl(localStartDateTime.substring(spaceIndex), "-1"), ":", "");
            spaceIndex = localEndDateTime.indexOf(" ");
            String endTransTime = StringUtils.replace(CommonUtil.nvl(localEndDateTime.substring(spaceIndex), "-1"), ":", "");

            NodeList rowTagNodes = doc.getElementsByTagName("row");

            ArrayList<RMFMonitoringData> RMFMonitoringDataList = null;
            if(rowTagNodes.getLength() > 0){
                RMFMonitoringDataList = new ArrayList<>();

                for (int i = 0; i < rowTagNodes.getLength(); i++) {
                    RMFMonitoringData RMFMonitoringData = new RMFMonitoringData();
                    RMFMonitoringData.setRmfMetricId(configuredMetricsView.getMetricId());
                    RMFMonitoringData.setPersianTransDate(DateTimeUtil.getCurrentShamsiDate());
                    RMFMonitoringData.setTransStartTime(Integer.parseInt(startTransTime.trim()));
                    RMFMonitoringData.setTransEndTime(Integer.parseInt(endTransTime.trim()));
                    RMFMonitoringData.setServerId(configuredMetricsView.getServerId());
                    RMFMonitoringData.setRmfMetricDesc(metricDescription);
                    RMFMonitoringData.setGathererInterval(Short.parseShort(CommonUtil.nvl(gathererInterval, "-1")));
                    RMFMonitoringData.setDataRange(Short.parseShort(CommonUtil.nvl(dataRange, "-1")));
                    RMFMonitoringData.setLocalStartTime(new BigInteger(CommonUtil.nvl(localStartTime, "-1")));
                    RMFMonitoringData.setLocalEndTime(new BigInteger(CommonUtil.nvl(localEndTime, "-1")));
                    RMFMonitoringData.setUtcStartTime(new BigInteger(CommonUtil.nvl(utcStartTime, "-1")));
                    RMFMonitoringData.setUtcEndTime(new BigInteger(CommonUtil.nvl(utcEndTime, "-1")));

                    Node nNode = rowTagNodes.item(i);
                    if (nNode != null && nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        RMFMonitoringData.setInsertDate(DateTimeUtil.getCurrentShamsiDate());
                        RMFMonitoringData.setRowEx(CommonUtil.nvl(eElement.getAttribute("ex"), "-1"));
                        RMFMonitoringData.setRowPer(CommonUtil.nvl(eElement.getAttribute("per"), "-1"));
                        RMFMonitoringData.setRowValue(CommonUtil.nvl(eElement.getAttribute("value"), "-1"));
                        RMFMonitoringData.setRowLabel(CommonUtil.nvl(eElement.getAttribute("label"),""));

                        RMFMonitoringDataList.add(RMFMonitoringData);
                    }
                }
            }
            return RMFMonitoringDataList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //======================================================================================================================
}
