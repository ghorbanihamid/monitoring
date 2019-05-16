package com.soshiant.server.model.monitoring.MainFrame.RMF;

import javax.persistence.*;

/*
 * Created by Hamid on 11/30/15.
 */

@Entity
@Table(name = "VW_RMF_MONITORING_PARAMS",
uniqueConstraints = {@UniqueConstraint(columnNames = {"serverId", "monitoringItemId"})})
public class RMFInfo implements java.io.Serializable {

    @Id
    private int serverId;
    @Id
    private int monitoringItemId;

    private boolean needAlarmSms;
    private boolean needAlarmEmail;
    private boolean needAlarmBeep;
    private boolean serverStatus;
    private boolean configStatus;

    private int managerId;

    private int siteId;
    private int monitoringPort;
    private int unusualMoreValue;
    private int unusualLessValue;
    private int criticalLessValue;
    private int criticalMoreValue;
    private int threadDelay;
    private int threadPeriod;

    private String serverIp;
    private String serverName;
    private String monitoringUserName;
    private String monitoringPassword;

    private String rmfMetricId;
    private String rmfMetricDesc;
    private String resourceName;
    private String resourceType;
    private String zOSSysplexName;
    private String zOSSystemId;
    private String beepFileName;

    @Transient
    private String rmfUrl;

    @Column(name = "ServerId", unique = true, nullable = false)
    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    @Column(name = "MonitoringItemId", unique = true, nullable = false)
    public int getMonitoringItemId() {
        return monitoringItemId;
    }

    public void setMonitoringItemId(int monitoringItemId) {
        this.monitoringItemId = monitoringItemId;
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

    @Column(name = "MonitoringPort", unique = false, nullable = false)
    public int getMonitoringPort() {
        return monitoringPort;
    }

    public void setMonitoringPort(int monitoringPort) {
        this.monitoringPort = monitoringPort;
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

    @Column(name = "ThreadDelay", unique = false, nullable = false)
    public int getThreadDelay() {
        return threadDelay;
    }

    public void setThreadDelay(int threadDelay) {
        this.threadDelay = threadDelay;
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

    @Column(name = "MonitoringUserName", unique = false, nullable = false)
    public String getMonitoringUserName() {
        return monitoringUserName;
    }

    public void setMonitoringUserName(String monitoringUserName) {
        this.monitoringUserName = monitoringUserName;
    }

    @Column(name = "MonitoringPassword", unique = false, nullable = false)
    public String getMonitoringPassword() {
        return monitoringPassword;
    }

    public void setMonitoringPassword(String monitoringPassword) {
        this.monitoringPassword = monitoringPassword;
    }

    @Column(name = "RmfMetricDesc", unique = false, nullable = false)
    public String getRmfMetricDesc() {
        return rmfMetricDesc;
    }

    public void setRmfMetricDesc(String rmfMetricDesc) {
        this.rmfMetricDesc = rmfMetricDesc;
    }

    @Column(name = "RmfMetricId", unique = false, nullable = false)
    public String getRmfMetricId() {
        return rmfMetricId;
    }

    public void setRmfMetricId(String rmfMetricId) {
        this.rmfMetricId = rmfMetricId;
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

    public String getRmfUrl() {
        return rmfUrl;
    }

    public void setRmfUrl(String rmfUrl) {
        this.rmfUrl = rmfUrl;
    }

    public String getServerRmfServiceId() { // get a unique Id, complex of serverId & MetricId for each service of server

        return String.valueOf(serverId + "_" + rmfMetricId);
    }


}
