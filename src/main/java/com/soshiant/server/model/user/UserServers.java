package com.soshiant.server.model.user;


import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 8/24/11
 * Time: 7:09 PM
 */
@Entity
@Table(name = "VW_USER_SERVERS",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"UserId", "ServerId"})})
public class UserServers implements java.io.Serializable {

    @Id
    private int userId;

    @Id
    private int serverId;

    private byte serverStatus;

    private short serverType;

    private String serverIp;
    private String serverName;
    private String serverDescription;


    @Column(name = "UserId", unique = false, nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Column(name = "ServerId", unique = false, nullable = false)
    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    @Column(name = "ServerStatus", unique = false, nullable = false)
    public byte getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(byte serverStatus) {
        this.serverStatus = serverStatus;
    }

    @Column(name = "ServerType", unique = false, nullable = false)
    public short getServerType() {
        return serverType;
    }

    public void setServerType(short serverType) {
        this.serverType = serverType;
    }

    @Column(name = "serverName", unique = false, nullable = false)
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Column(name = "serverIp", unique = false, nullable = false)
    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    @Column(name = "ServerDescription", unique = false, nullable = false)
    public String getServerDescription() {
        return serverDescription;
    }

    public void setServerDescription(String serverDescription) {
        this.serverDescription = serverDescription;
    }

    @Override
    public String toString() {
        return "UserServers{" +
                "userId=" + userId +
                ", serverId=" + serverId +
                ", serverName=" + serverName +
                ", serverIp=" + serverIp +
                '}';
    }
}