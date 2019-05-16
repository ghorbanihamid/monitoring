package com.soshiant.server.model.site;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

@Entity
@Table(name = "SITEINFO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "SiteId")})
public class Site implements java.io.Serializable {

    @Id
    private int siteId;

    @Transient
    private boolean editMode;

    private short siteStatus;

    private int managerId;

    private String siteName;
    private String siteTelNo;
    private String siteFax;
    private String description;





    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    @Column(name = "siteId", unique = true, nullable = false)
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Column(name = "siteStatus", unique = true, nullable = false)
    public short getSiteStatus() {
        return siteStatus;
    }

    public void setSiteStatus(short siteStatus) {
        this.siteStatus = siteStatus;
    }

    @Column(name = "managerId", unique = true, nullable = false)
    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Column(name = "siteName", unique = true, nullable = false)
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    @Column(name = "siteTelNo", unique = true, nullable = false)
    public String getSiteTelNo() {
        return siteTelNo;
    }

    public void setSiteTelNo(String siteTelNo) {
        this.siteTelNo = siteTelNo;
    }

    @Column(name = "siteFax", unique = true, nullable = false)
    public String getSiteFax() {
        return siteFax;
    }

    public void setSiteFax(String siteFax) {
        this.siteFax = siteFax;
    }

    @Column(name = "description", unique = true, nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Site{" +
                "siteId=" + siteId +
                ", editMode=" + editMode +
                ", managerId=" + managerId +
                ", siteName=" + siteName +
                ", siteFax=" + siteFax +
                ", description=" + description +
                '}';
    }
}

