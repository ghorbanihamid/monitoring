package com.soshiant.server.model.monitoring.MainFrame.RMF;

import javax.persistence.*;

/*
 * Created by Hamid on 11/30/15.
 */

@Entity
@Table(name = "VW_RMF_MONITORING_PARAMS",
uniqueConstraints = {@UniqueConstraint(columnNames = {"ServerId","metricId"})})
public class ConfiguredMetricsView implements java.io.Serializable {

    @Id
    private int serverId;
    @Id
    private String metricId;

    private boolean needAlarmSms;
    private boolean needAlarmEmail;
    private boolean needAlarmBeep;
    private boolean serverStatus;
    private boolean configStatus;

    private byte  osType;

    private short serverType;

    private int managerId;

    private int siteId;
    private int rmfPort;
    private int unusualMoreValue;
    private int unusualLessValue;
    private int criticalLessValue;
    private int criticalMoreValue;
    private int threadPeriod;

    private String serverIp;
    private String serverName;
    private String rmfUserName;
    private String rmfPassword;


    private String metricDesc;
    private String resourceName;
    private String resourceType;
    private String zOSSysplexName;
    private String zOSSystemId;
    private String beepFileName;
    private String oSVersion;
    private String serverDescription;
    private String chartType;
    private String chartLabelColumn;
    private String chartValueColumn;
    private String valuePrefix;
    private String parentTitle;
    private String rmfMinValue;
    private String rmfMaxValue;



    @Transient
    private String rmfUrl;

    @Column(name = "ServerId", unique = true, nullable = false)
    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    @Column(name = "NeedAlarmSms", unique = false, nullable = false)
    public boolean isNeedAlarmSms() {
        return needAlarmSms;
    }

    public void setNeedAlarmSms(boolean needAlarmSms) {
        this.needAlarmSms = needAlarmSms;
    }

    @Column(name = "needAlarmEmail", unique = false, nullable = false)
    public boolean isNeedAlarmEmail() {
        return needAlarmEmail;
    }

    public void setNeedAlarmEmail(boolean needAlarmEmail) {
        this.needAlarmEmail = needAlarmEmail;
    }

    @Column(name = "NeedAlarmBeep", unique = false, nullable = false)
    public boolean isNeedAlarmBeep() {
        return needAlarmBeep;
    }

    public void setNeedAlarmBeep(boolean needAlarmBeep) {
        this.needAlarmBeep = needAlarmBeep;
    }

    @Column(name = "ServerStatus", unique = false, nullable = false)
    public boolean isServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(boolean serverStatus) {
        this.serverStatus = serverStatus;
    }

    @Column(name = "ConfigStatus", unique = false, nullable = false)
    public boolean isConfigStatus() {
        return configStatus;
    }

    public void setConfigStatus(boolean configStatus) {
        this.configStatus = configStatus;
    }

    @Column(name = "ManagerId", unique = false, nullable = false)
    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Column(name = "siteId", unique = false, nullable = false)
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Column(name = "UnusualMoreValue", unique = false, nullable = false)
    public int getUnusualMoreValue() {
        return unusualMoreValue;
    }

    public void setUnusualMoreValue(int unusualMoreValue) {
        this.unusualMoreValue = unusualMoreValue;
    }

    @Column(name = "UnusualLessValue", unique = false, nullable = false)
    public int getUnusualLessValue() {
        return unusualLessValue;
    }

    public void setUnusualLessValue(int unusualLessValue) {
        this.unusualLessValue = unusualLessValue;
    }

    @Column(name = "CriticalLessValue", unique = false, nullable = false)
    public int getCriticalLessValue() {
        return criticalLessValue;
    }

    public void setCriticalLessValue(int criticalLessValue) {
        this.criticalLessValue = criticalLessValue;
    }

    @Column(name = "CriticalMoreValue", unique = false, nullable = false)
    public int getCriticalMoreValue() {
        return criticalMoreValue;
    }

    public void setCriticalMoreValue(int criticalMoreValue) {
        this.criticalMoreValue = criticalMoreValue;
    }

    @Column(name = "ThreadPeriod", unique = false, nullable = false)
    public int getThreadPeriod() {
        return threadPeriod;
    }

    public void setThreadPeriod(int threadPeriod) {
        this.threadPeriod = threadPeriod;
    }

    @Column(name = "ServerIp", unique = false, nullable = false)
    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    @Column(name = "ServerName", unique = false, nullable = false)
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Column(name = "ResourceName", unique = false, nullable = false)
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Column(name = "ResourceType", unique = false, nullable = false)
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Column(name = "ZOSSysplexName", unique = false, nullable = false)
    public String getzOSSysplexName() {
        return zOSSysplexName;
    }

    public void setzOSSysplexName(String zOSSysplexName) {
        this.zOSSysplexName = zOSSysplexName;
    }

    @Column(name = "ZOSSystemId", unique = false, nullable = false)
    public String getzOSSystemId() {
        return zOSSystemId;
    }

    public void setzOSSystemId(String zOSSystemId) {
        this.zOSSystemId = zOSSystemId;
    }

    @Column(name = "BeepFileName", unique = false, nullable = false)
    public String getBeepFileName() {
        return beepFileName;
    }

    public void setBeepFileName(String beepFileName) {
        this.beepFileName = beepFileName;
    }

    @Column(name = "OsType", unique = false, nullable = false)
    public byte getOsType() {
        return osType;
    }

    public void setOsType(byte osType) {
        this.osType = osType;
    }

    @Column(name = "ServerType", unique = false, nullable = false)
    public short getServerType() {
        return serverType;
    }

    public void setServerType(short serverType) {
        this.serverType = serverType;
    }

    @Column(name = "OSVersion", unique = false, nullable = false)
    public String getoSVersion() {
        return oSVersion;
    }

    public void setoSVersion(String oSVersion) {
        this.oSVersion = oSVersion;
    }

    @Column(name = "ServerDescription", unique = false, nullable = false)
    public String getServerDescription() {
        return serverDescription;
    }

    public void setServerDescription(String serverDescription) {
        this.serverDescription = serverDescription;
    }

    @Column(name = "ChartType", unique = false, nullable = false)
    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    @Column(name = "ChartLabelColumn", unique = false, nullable = false)
    public String getChartLabelColumn() {
        return chartLabelColumn;
    }

    public void setChartLabelColumn(String chartLabelColumn) {
        this.chartLabelColumn = chartLabelColumn;
    }

    @Column(name = "ChartValueColumn", unique = false, nullable = false)
    public String getChartValueColumn() {
        return chartValueColumn;
    }

    public void setChartValueColumn(String chartValueColumn) {
        this.chartValueColumn = chartValueColumn;
    }

    @Column(name = "ValuePrefix", unique = false, nullable = false)
    public String getValuePrefix() {
        return valuePrefix;
    }

    public void setValuePrefix(String valuePrefix) {
        this.valuePrefix = valuePrefix;
    }

    @Column(name = "RmfMinValue", unique = false, nullable = false)
    public String getRmfMinValue() {
        return rmfMinValue;
    }

    public void setRmfMinValue(String rmfMinValue) {
        this.rmfMinValue = rmfMinValue;
    }

    @Column(name = "RmfMaxValue", unique = false, nullable = false)
    public String getRmfMaxValue() {
        return rmfMaxValue;
    }

    public void setRmfMaxValue(String rmfMaxValue) {
        this.rmfMaxValue = rmfMaxValue;
    }

    @Column(name = "ParentTitle", unique = false, nullable = false)
    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    @Column(name = "rmfPort", unique = false, nullable = false)
    public int getRmfPort() {
        return rmfPort;
    }

    public void setRmfPort(int rmfPort) {
        this.rmfPort = rmfPort;
    }

    @Column(name = "rmfUserName", unique = false, nullable = false)
    public String getRmfUserName() {
        return rmfUserName;
    }

    public void setRmfUserName(String rmfUserName) {
        this.rmfUserName = rmfUserName;
    }

    @Column(name = "rmfPassword", unique = false, nullable = false)
    public String getRmfPassword() {
        return rmfPassword;
    }

    public void setRmfPassword(String rmfPassword) {
        this.rmfPassword = rmfPassword;
    }

    @Column(name = "metricId", unique = false, nullable = false)
    public String getMetricId() {
        return metricId;
    }

    public void setMetricId(String metricId) {
        this.metricId = metricId;
    }

    @Column(name = "metricDesc", unique = false, nullable = false)
    public String getMetricDesc() {
        return metricDesc;
    }

    public void setMetricDesc(String metricDesc) {
        this.metricDesc = metricDesc;
    }

    public String getRmfUrl() {
        return rmfUrl;
    }

    public void setRmfUrl(String rmfUrl) {
        this.rmfUrl = rmfUrl;
    }

    public String getServerRmfServiceId() { // get a unique Id, complex of serverId & MetricId for each service of server

        return String.valueOf(serverId + "_" + metricId);
    }

}
