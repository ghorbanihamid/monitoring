package com.soshiant.server.model.permission;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * UserInfo: Ghorbani
 * Date: 9/20/11
 * Time: 4:05 PM
 */
@Entity
@Table(name = "PERMISSIONS")
public class Permissions implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int permissionId;

    private int createDate;

    private String permissionName;

    private String permissionAction;

    private String permissionDescription;

    private String permissionUrl;


    @Column(name = "PermissionId")
    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Column(name = "createDate")
    public int getCreateDate() {
        return createDate;
    }

    public void setCreateDate(int createDate) {
        this.createDate = createDate;
    }

    @Column(name = "PermissionName")
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Column(name = "permissionAction")
    public String getPermissionAction() {
        return permissionAction;
    }

    public void setPermissionAction(String permissionAction) {
        this.permissionAction = permissionAction;
    }

    @Column(name = "PermissionDescription", columnDefinition = "text")
    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    @Column(name = "PermissionUrl")
    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }


    public Permissions() {

    }

    public Permissions(String action, String url, String description) {

//        this.createDate = Da;
        this.permissionName = action;
        this.permissionDescription = description;
        this.permissionUrl = url;
    }

    //@Override
    public String toString() {
        return "Permissions [permissionId=" + this.permissionId + ", permissionName=" + this.permissionName
                + ", permissionDescription=" + this.permissionDescription + ", permissionUrl= " + this.permissionUrl
                + ", permissionAction=" + this.permissionAction + ", createDate= " + this.createDate + "]";
    }
}