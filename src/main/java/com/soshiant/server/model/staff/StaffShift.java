package com.soshiant.server.model.staff;


import com.soshiant.common.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

@Entity
@Table(name = "STAFFSHIFT", uniqueConstraints = {
        @UniqueConstraint(columnNames = "StaffId")})
public class StaffShift implements java.io.Serializable {

    @Id
    private int staffId;
    @Id
    private int shiftDate;


    private int shiftStartTime;
    private int shiftEndTime;



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

    public void setShiftDate(int shiftDate) {
        this.shiftDate = shiftDate;
    }

    public void setShiftDateStr(String shiftDate) {
        this.shiftDate = Integer.parseInt(StringUtils.replace(CommonUtil.nvl(shiftDate, "0"),"/",""));
    }

    @Column(name = "ShiftStartTime", unique = true, nullable = false)
    public int getShiftStartTime() {
        return shiftStartTime;
    }

    public void setShiftStartTime(int shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    public void setShiftStartTimeStr(String shiftStartTime) {
        this.shiftStartTime = Integer.parseInt(StringUtils.replace(CommonUtil.nvl(shiftStartTime, "0"),":",""));
    }

    @Column(name = "ShiftEndTime", unique = true, nullable = false)
    public int getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(int shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }
    public void setShiftEndTimeStr(String shiftEndTime) {
        this.shiftEndTime = Integer.parseInt(StringUtils.replace(CommonUtil.nvl(shiftEndTime, "0"), ":", ""));
    }
}

