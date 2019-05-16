package com.soshiant.server.model.user;

import com.soshiant.server.model.monitoring.MainFrame.RMF.ConfiguredMetricsView;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 8/24/11
 * Time: 7:09 PM
 */
@Entity
@Table(name = "VW_USER_RMF_PERMISSIONS",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"UserId", "ConfigId"})})
public class UserPermissionsView implements java.io.Serializable {

    @Id
    private int userId;

    @Id
    private int configId;

    private byte needAlarmSms;
    private byte needAlarmEmail;
    private byte needAlarmBeep;

    private byte configStatus;
    private byte serverStatus;

    private int serverId;
    private int unusualMoreValue;
    private int unusualLessValue;
    private int criticalLessValue;
    private int criticalMoreValue;

    private int startPermissionDate;
    private int endPermissionDate;
    private String serverName;


    private String chartType;
    private String serverDescription;
    private String serverIp;
    private String metricId;
    private String metricDesc;
    private String chartLabelColumn;
    private String chartValueColumn;
    private String valuePrefix;
    private String rmfMinValue;
    private String rmfMaxValue;
    private String beepFileName;




    @Column(name = "UserId", unique = false, nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "configId", unique = false, nullable = false)
    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    @Column(name = "needAlarmSms", unique = false, nullable = false)
    public byte getNeedAlarmSms() {
        return needAlarmSms;
    }

    public void setNeedAlarmSms(byte needAlarmSms) {
        this.needAlarmSms = needAlarmSms;
    }

    @Column(name = "needAlarmEmail", unique = false, nullable = false)
    public byte getNeedAlarmEmail() {
        return needAlarmEmail;
    }

    public void setNeedAlarmEmail(byte needAlarmEmail) {
        this.needAlarmEmail = needAlarmEmail;
    }

    @Column(name = "needAlarmBeep", unique = false, nullable = false)
    public byte getNeedAlarmBeep() {
        return needAlarmBeep;
    }

    public void setNeedAlarmBeep(byte needAlarmBeep) {
        this.needAlarmBeep = needAlarmBeep;
    }

    @Column(name = "configStatus", unique = false, nullable = false)
    public byte getConfigStatus() {
        return configStatus;
    }

    public void setConfigStatus(byte configStatus) {
        this.configStatus = configStatus;
    }

    @Column(name = "serverId", unique = false, nullable = false)
    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    @Column(name = "unusualMoreValue", unique = false, nullable = false)
    public int getUnusualMoreValue() {
        return unusualMoreValue;
    }

    public void setUnusualMoreValue(int unusualMoreValue) {
        this.unusualMoreValue = unusualMoreValue;
    }

    @Column(name = "unusualLessValue", unique = false, nullable = false)
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

    @Column(name = "criticalMoreValue", unique = false, nullable = false)
    public int getCriticalMoreValue() {
        return criticalMoreValue;
    }

    public void setCriticalMoreValue(int criticalMoreValue) {
        this.criticalMoreValue = criticalMoreValue;
    }

    @Column(name = "serverStatus", unique = false, nullable = false)
    public byte getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(byte serverStatus) {
        this.serverStatus = serverStatus;
    }

    @Column(name = "chartType", unique = false, nullable = false)
    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    @Column(name = "startPermissionDate", unique = false, nullable = false)
    public int getStartPermissionDate() {
        return startPermissionDate;
    }

    public void setStartPermissionDate(int startPermissionDate) {
        this.startPermissionDate = startPermissionDate;
    }

    @Column(name = "endPermissionDate", unique = false, nullable = false)
    public int getEndPermissionDate() {
        return endPermissionDate;
    }

    public void setEndPermissionDate(int endPermissionDate) {
        this.endPermissionDate = endPermissionDate;
    }

    @Column(name = "serverName", unique = false, nullable = false)
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Column(name = "serverDescription", unique = false, nullable = false)
    public String getServerDescription() {
        return serverDescription;
    }

    public void setServerDescription(String serverDescription) {
        this.serverDescription = serverDescription;
    }

    @Column(name = "serverIp", unique = false, nullable = false)
    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
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

    @Column(name = "chartLabelColumn", unique = false, nullable = false)
    public String getChartLabelColumn() {
        return chartLabelColumn;
    }

    public void setChartLabelColumn(String chartLabelColumn) {
        this.chartLabelColumn = chartLabelColumn;
    }

    @Column(name = "chartValueColumn", unique = false, nullable = false)
    public String getChartValueColumn() {
        return chartValueColumn;
    }

    public void setChartValueColumn(String chartValueColumn) {
        this.chartValueColumn = chartValueColumn;
    }

    @Column(name = "valuePrefix", unique = false, nullable = false)
    public String getValuePrefix() {
        return valuePrefix;
    }

    public void setValuePrefix(String valuePrefix) {
        this.valuePrefix = valuePrefix;
    }

    @Column(name = "rmfMinValue", unique = false, nullable = false)
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

    @Column(name = "BeepFileName", unique = false, nullable = false)
    public String getBeepFileName() {
        return beepFileName;
    }

    public void setBeepFileName(String beepFileName) {
        this.beepFileName = beepFileName;
    }

    public String getServerRmfServiceId() { // get a unique Id, complex of serverId & MetricId for each service of server

        return String.valueOf(serverId + "_" + metricId);
    }
}