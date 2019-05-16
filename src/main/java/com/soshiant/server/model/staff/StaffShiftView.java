package com.soshiant.server.model.staff;


import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ServerConstants;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

@Entity
@Table(name = "VW_STAFF_SHIFT", uniqueConstraints = {
        @UniqueConstraint(columnNames = "StaffId")})
public class StaffShiftView implements java.io.Serializable {

    @Id
    private int staffId;
    @Id
    private int shiftDate;


    private int shiftStartTime;
    private int shiftEndTime;

    private byte genderStatus;
    private byte staffStatus;

    private String staffOlName;
    private String staffOlFamily;
    private String staffEnName;
    private String staffEnFamily;
    private String homePhone;
    private String workPhone;
    private String cellPhone;
    private String emailAddress;


    @Column(name = "StaffId", unique = true, nullable = false)
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @Column(name = "shiftDate", unique = true, nullable = false)
    public int getShiftDate() {
        return shiftDate;
    }

    public String getShiftDateStr() {
        return DateTimeUtil.addDateSeparator(String.valueOf(shiftDate));
    }

    public void setShiftDate(int shiftDate) {
        this.shiftDate = shiftDate;
    }

    @Column(name = "ShiftStartTime", unique = true, nullable = false)
    public int getShiftStartTime() {
        return shiftStartTime;
    }

    public String getShiftStartTimeStr() {
        return DateTimeUtil.addTimeSeparator(String.valueOf(shiftStartTime), ServerConstants.TIME_FORMAT_WITH_MINUTE);
    }

    public void setShiftStartTime(int shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    @Column(name = "ShiftEndTime", unique = true, nullable = false)
    public int getShiftEndTime() {
        return shiftEndTime;
    }

    public String getShiftEndTimeStr() {
        return DateTimeUtil.addTimeSeparator(String.valueOf(shiftEndTime), ServerConstants.TIME_FORMAT_WITH_MINUTE);
    }

    public void setShiftEndTime(int shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }

    @Column(name = "GenderStatus", unique = true, nullable = false)
    public byte getGenderStatus() {
        return genderStatus;
    }

    public void setGenderStatus(byte genderStatus) {
        this.genderStatus = genderStatus;
    }

    @Column(name = "StaffStatus", unique = true, nullable = false)
    public byte getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(byte staffStatus) {
        this.staffStatus = staffStatus;
    }

    @Column(name = "StaffOlName", unique = true, nullable = false)
    public String getStaffOlName() {
        return staffOlName;
    }

    public void setStaffOlName(String staffOlName) {
        this.staffOlName = staffOlName;
    }

    @Column(name = "StaffOlFamily", unique = true, nullable = false)
    public String getStaffOlFamily() {
        return staffOlFamily;
    }

    public void setStaffOlFamily(String staffOlFamily) {
        this.staffOlFamily = staffOlFamily;
    }

    @Column(name = "StaffEnName", unique = true, nullable = false)
    public String getStaffEnName() {
        return staffEnName;
    }

    public void setStaffEnName(String staffEnName) {
        this.staffEnName = staffEnName;
    }

    @Column(name = "StaffEnFamily", unique = true, nullable = false)
    public String getStaffEnFamily() {
        return staffEnFamily;
    }

    public void setStaffEnFamily(String staffEnFamily) {
        this.staffEnFamily = staffEnFamily;
    }

    @Column(name = "HomePhone", unique = true, nullable = false)
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @Column(name = "WorkPhone", unique = true, nullable = false)
    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    @Column(name = "CellPhone", unique = true, nullable = false)
    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    @Column(name = "EmailAddress", unique = true, nullable = false)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getStaffEnFullName() {
        return staffEnName + " " + staffEnFamily;
    }

    public String getStaffOlFullName() {
        return staffOlName + " " + staffOlFamily;
    }

    public String getStaffFullName() {

        if(CommonUtil.isCurrentLocalePersian())
            return staffOlName + " " + staffOlFamily;
        else
            return staffEnName + " " + staffEnFamily;
    }

}

