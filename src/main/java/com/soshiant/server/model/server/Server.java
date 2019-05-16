package com.soshiant.server.model.server;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

@Entity
@Table(name = "SERVERSINFO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ServerId")})
public class Server implements java.io.Serializable {

    @Id
    private int serverId;

    @Transient
    private boolean editMode;

    private short serverStatus;
    private short serverType;
    private short osType;

    private int managerId;
    private int siteId;
    private int threadPeriod;
    private int rmfPort;


    private String serverName;
    private String serverIp;

    private String osVersion;
    private String zosSystemId;
    private String zosSysplexName;
    private String serverDescription;
    private String rmfUserName;
    private String rmfPassword;


    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    @Column(name = "serverId", unique = true, nullable = false)
    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    @Column(name = "serverStatus", unique = true, nullable = false)
    public short getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(short serverStatus) {
        this.serverStatus = serverStatus;
    }

    @Column(name = "managerId", unique = true, nullable = false)
    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Column(name = "serverName", unique = true, nullable = false)
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Column(name = "serverType", unique = true, nullable = false)
    public short getServerType() {
        return serverType;
    }

    public void setServerType(short serverType) {
        this.serverType = serverType;
    }

    @Column(name = "osType", unique = true, nullable = false)
    public short getOsType() {
        return osType;
    }

    public void setOsType(short osType) {
        this.osType = osType;
    }

    @Column(name = "serverIp", unique = true, nullable = false)
    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    @Column(name = "osVersion", unique = true, nullable = false)
    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    @Column(name = "zosSystemId", unique = true, nullable = false)
    public String getZosSystemId() {
        return zosSystemId;
    }

    public void setZosSystemId(String zosSystemId) {
        this.zosSystemId = zosSystemId;
    }

    @Column(name = "zosSysplexName", unique = true, nullable = false)
    public String getZosSysplexName() {
        return zosSysplexName;
    }

    public void setZosSysplexName(String zosSysplexName) {
        this.zosSysplexName = zosSysplexName;
    }

    @Column(name = "ServerDescription", unique = true, nullable = false)
    public String getServerDescription() {
        return serverDescription;
    }

    public void setServerDescription(String serverDescription) {
        this.serverDescription = serverDescription;
    }

    @Column(name = "SiteId", unique = true, nullable = false)
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Column(name = "ThreadPeriod", unique = true, nullable = false)
    public int getThreadPeriod() {
        return threadPeriod;
    }

    public void setThreadPeriod(int threadPeriod) {
        this.threadPeriod = threadPeriod;
    }

    @Column(name = "RmfPort", unique = true, nullable = false)
    public int getRmfPort() {
        return rmfPort;
    }

    public void setRmfPort(int rmfPort) {
        this.rmfPort = rmfPort;
    }

    @Column(name = "RmfUserName", unique = true, nullable = false)
    public String getRmfUserName() {
        return rmfUserName;
    }

    public void setRmfUserName(String rmfUserName) {
        this.rmfUserName = rmfUserName;
    }

    @Column(name = "RmfPassword", unique = true, nullable = false)
    public String getRmfPassword() {
        return rmfPassword;
    }

    public void setRmfPassword(String rmfPassword) {
        this.rmfPassword = rmfPassword;
    }

    @Override
    public String toString() {
        return "Server{" +
                "serverId=" + serverId +
                ", editMode=" + editMode +
                ", serverStatus=" + serverStatus +
                ", serverType=" + serverType +
                ", osType=" + osType +
                ", managerId=" + managerId +
                ", serverName=" + serverName +
                ", serverIp=" + serverIp +
                ", osVersion=" + osVersion +
                ", zosSystemId=" + zosSystemId +
                ", zosSysplexName=" + zosSysplexName +
                ", serverDescription=" + serverDescription +
                '}';
    }
}

