package com.soshiant.server.model.server;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

@Entity
@Table(name = "RMFMONITORINGCONFIG", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ConfigId")})
public class RmfConfig implements java.io.Serializable {

    @Id
    private int configId;

    private int serverId;

    @Transient
    private boolean editMode;

    private boolean needAlarmSms;
    private boolean needAlarmEmail;
    private boolean needAlarmBeep;

    private short configStatus;

    private int unusualMoreValue;
    private int unusualLessValue;
    private int criticalMoreValue;
    private int criticalLessValue;


    private String metricId;


    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    @Column(name = "ConfigId", unique = true, nullable = false)
    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    @Column(name = "serverId", unique = true, nullable = false)
    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    @Column(name = "NeedAlarmSms", unique = true, nullable = false)
    public boolean isNeedAlarmSms() {
        return needAlarmSms;
    }

    public void setNeedAlarmSms(boolean needAlarmSms) {
        this.needAlarmSms = needAlarmSms;
    }

    @Column(name = "NeedAlarmEmail", unique = true, nullable = false)
    public boolean isNeedAlarmEmail() {
        return needAlarmEmail;
    }

    public void setNeedAlarmEmail(boolean needAlarmEmail) {
        this.needAlarmEmail = needAlarmEmail;
    }

    @Column(name = "NeedAlarmBeep", unique = true, nullable = false)
    public boolean isNeedAlarmBeep() {
        return needAlarmBeep;
    }

    public void setNeedAlarmBeep(boolean needAlarmBeep) {
        this.needAlarmBeep = needAlarmBeep;
    }

    @Column(name = "ConfigStatus", unique = true, nullable = false)
    public short getConfigStatus() {
        return configStatus;
    }

    public void setConfigStatus(short configStatus) {
        this.configStatus = configStatus;
    }

    @Column(name = "UnusualMoreValue", unique = true, nullable = false)
    public int getUnusualMoreValue() {
        return unusualMoreValue;
    }

    public void setUnusualMoreValue(int unusualMoreValue) {
        this.unusualMoreValue = unusualMoreValue;
    }

    @Column(name = "UnusualLessValue", unique = true, nullable = false)
    public int getUnusualLessValue() {
        return unusualLessValue;
    }

    public void setUnusualLessValue(int unusualLessValue) {
        this.unusualLessValue = unusualLessValue;
    }

    @Column(name = "CriticalMoreValue", unique = true, nullable = false)
    public int getCriticalMoreValue() {
        return criticalMoreValue;
    }

    public void setCriticalMoreValue(int criticalMoreValue) {
        this.criticalMoreValue = criticalMoreValue;
    }

    @Column(name = "CriticalLessValue", unique = true, nullable = false)
    public int getCriticalLessValue() {
        return criticalLessValue;
    }

    public void setCriticalLessValue(int criticalLessValue) {
        this.criticalLessValue = criticalLessValue;
    }

    @Column(name = "MetricId", unique = true, nullable = false)
    public String getMetricId() {
        return metricId;
    }

    public void setMetricId(String metricId) {
        this.metricId = metricId;
    }


    @Override
    public String toString() {
        return "RmfConfig{" +
                "configId=" + configId +
                ", serverId=" + serverId +
                ", editMode=" + editMode +
                ", needAlarmSms=" + needAlarmSms +
                ", needAlarmEmail=" + needAlarmEmail +
                ", needAlarmBeep=" + needAlarmBeep +
                ", configStatus=" + configStatus +
                ", unusualMoreValue=" + unusualMoreValue +
                ", unusualLessValue=" + unusualLessValue +
                ", criticalMoreValue=" + criticalMoreValue +
                ", criticalLessValue=" + criticalLessValue +
                ", metricId='" + metricId + '\'' +
                '}';
    }
}

