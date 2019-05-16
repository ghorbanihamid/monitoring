package com.soshiant.server.model.staff;

import com.soshiant.common.util.BundleUtil;
import com.soshiant.common.util.CommonUtil;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

@Entity
@Table(name = "STAFFSINFO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "StaffId")})
public class Staff implements java.io.Serializable {

    @Id
    private int staffId;

    @Transient
    private boolean editMode;

    private byte staffStatus;
    private byte genderStatus;

    @Transient
    private int    newStaffId;

    private String staffEnName;
    private String staffEnFamily;
    private String staffOlName;
    private String staffOlFamily;
    private String homePhone;
    private String workPhone;
    private String cellPhone;
    private String emailAddress;

    @Transient
    private String genderName;

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    @Column(name = "StaffId", unique = true, nullable = false)
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getNewStaffId() {
        return newStaffId;
    }

    public void setNewStaffId(int newStaffId) {
        this.newStaffId = newStaffId;
    }

    @Column(name = "StaffStatus", unique = false, nullable = false)
    public byte getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(byte staffStatus) {
        this.staffStatus = staffStatus;
    }

    @Column(name = "genderStatus", unique = false, nullable = false)
    public byte getGenderStatus() {
        return genderStatus;
    }

    public void setGenderStatus(byte genderStatus) {
        this.genderStatus = genderStatus;
    }

    @Column(name = "staffEnName", unique = false, nullable = false, length = 30)
    public String getStaffEnName() {
        return staffEnName;
    }

    public void setStaffEnName(String staffEnName) {
        this.staffEnName = staffEnName;
    }

    @Column(name = "staffEnFamily", unique = false, nullable = false, length = 30)
    public String getStaffEnFamily() {
        return staffEnFamily;
    }

    public void setStaffEnFamily(String staffEnFamily) {
        this.staffEnFamily = staffEnFamily;
    }

    @Column(name = "staffOlName", unique = false, nullable = false, length = 30)
    public String getStaffOlName() {
        return staffOlName;
    }

    public void setStaffOlName(String staffOlName) {
        this.staffOlName = staffOlName;
    }

    @Column(name = "staffOlFamily", unique = false, nullable = false, length = 30)
    public String getStaffOlFamily() {
        return staffOlFamily;
    }

    public void setStaffOlFamily(String staffOlFamily) {
        this.staffOlFamily = staffOlFamily;
    }

    @Column(name = "HomePhone", unique = false, nullable = false, length = 20)
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @Column(name = "WorkPhone", unique = false, nullable = false, length = 20)
    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    @Column(name = "CellPhone", unique = false, nullable = false, length = 15)
    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    @Column(name = "EmailAddress", unique = false, nullable = false, length = 30)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getStaffName() {

        if(CommonUtil.isCurrentLocalePersian())
            return staffOlName + " " + staffOlFamily;
        else
            return staffEnName + " " + staffEnFamily;
    }

    public String getStaffEnFullName() {

        return staffEnName + " " + staffEnFamily;
    }

    public String getStaffOlFullName() {

        return staffOlName + " " + staffOlFamily;
    }

    @Transient
    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getStaffStatusName() {

        BundleUtil bundle = BundleUtil.getInstance();
        switch (staffStatus){
            case 0 : return bundle.getMessage("staff.status.notActive");
            case 1 : return bundle.getMessage("staff.status.active");
            case 2 : return bundle.getMessage("staff.status.disabled");
            default: return "";
        }
    }

}

