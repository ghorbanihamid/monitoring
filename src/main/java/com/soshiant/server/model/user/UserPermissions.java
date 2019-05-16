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
@Table(name = "userpermissions",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"UserId", "ConfigId"})})
public class UserPermissions implements java.io.Serializable {

    @Id
    private int userId;

    @Id
    private int configId;

    private int startPermissionDate;

    private int endPermissionDate;

    private int createDate;

    private int creatorUser;

    private int updaterDate;

    private int updaterUser;

    private String rmfItems;


    @Transient
    ArrayList<ConfiguredMetricsView> rmfItemList;

    @Column(name = "UserId", unique = false, nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Column(name = "ConfigId", unique = false, nullable = false)
    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    @Column(name = "StartPermissionDate", unique = false, nullable = false)
    public int getStartPermissionDate() {
        return startPermissionDate;
    }

    public void setStartPermissionDate(int startPermissionDate) {
        this.startPermissionDate = startPermissionDate;
    }

    @Column(name = "EndPermissionDate", unique = false, nullable = false)
    public int getEndPermissionDate() {
        return endPermissionDate;
    }

    public void setEndPermissionDate(int endPermissionDate) {
        this.endPermissionDate = endPermissionDate;
    }

    @Column(name = "CreateDate", unique = false, nullable = false)
    public int getCreateDate() {
        return createDate;
    }

    public void setCreateDate(int createDate) {
        this.createDate = createDate;
    }

    @Column(name = "CreatorUser", unique = false, nullable = false)
    public int getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(int creatorUser) {
        this.creatorUser = creatorUser;
    }

    @Column(name = "UpdaterDate", unique = false, nullable = false)
    public int getUpdaterDate() {
        return updaterDate;
    }

    public void setUpdaterDate(int updaterDate) {
        this.updaterDate = updaterDate;
    }

    @Column(name = "UpdaterUser", unique = false, nullable = false)
    public int getUpdaterUser() {
        return updaterUser;
    }

    public void setUpdaterUser(int updaterUser) {
        this.updaterUser = updaterUser;
    }

    @Override
    public String toString() {
        return "UserPermissions{" +
                "userId=" + userId +
                ", configId =" + configId +
                ", startPermissionDate=" + startPermissionDate +
                ", endPermissionDate=" + endPermissionDate +
                ", createDate=" + createDate +
                ", creatorUser=" + creatorUser +
                ", updaterDate=" + updaterDate +
                ", updaterUser=" + updaterUser +
                ", rmfItems=" + rmfItems +
                '}';
    }
}