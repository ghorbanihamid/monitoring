package com.soshiant.server.model.staff;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.common.util.BundleUtil;
import com.soshiant.server.constants.ServerConstants;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 10/7/11
 * Time: 2:02 AM
 */

@Entity
@Table(name = "STAFFPOSITIONS",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"StaffId", "PositionId"})})
public class StaffPosition implements java.io.Serializable {

    @Id
    private int   staffId;
    @Id
    private byte  positionId;

    private byte  positionStatus;
    private byte  participationPercent;
    private int   registerDate;
    private int   toggleStatusDate;

    @Transient
    private String positionName;

    @Transient
    private String userName;


    @Column(name = "staffId", unique = true, nullable = false)
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @Column(name = "PositionId", unique = true, nullable = false)
    public byte getPositionId() {
        return positionId;
    }

    public void setPositionId(byte positionId) {
        this.positionId = positionId;
    }

    @Column(name = "positionStatus", unique = true, nullable = false)
    public byte getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(byte positionStatus) {
        this.positionStatus = positionStatus;
    }

    public String getPositionStatusName() {

        BundleUtil bundle = BundleUtil.getInstance();

        switch (positionStatus){

            case 0 : return bundle.getMessage("staffPosition.status.notActive");
            case 1 : return bundle.getMessage("staffPosition.status.active");
            case 2 : return bundle.getMessage("staffPosition.status.disabled");
            default: return "";
        }
    }

    @Column(name = "ParticipationPercent", unique = true, nullable = false)
    public byte getParticipationPercent() {
        return participationPercent;
    }

    public void setParticipationPercent(byte participationPercent) {
        this.participationPercent = participationPercent;
    }

    @Column(name = "RegisterDate", unique = true, nullable = false)
    public int getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(int registerDate) {
        this.registerDate = registerDate;
    }

    @Column(name = "ToggleStatusDate", unique = true, nullable = false)
    public int getToggleStatusDate() {
        return toggleStatusDate;
    }

    public void setToggleStatusDate(int toggleStatusDate) {
        this.toggleStatusDate = toggleStatusDate;
    }

    @Transient
    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Transient
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public StaffPosition() {

    }

    public StaffPosition(int staffId, byte positionId) {
        this.staffId = staffId;
        this.positionId = positionId;
        this.positionStatus = ServerConstants.STAFF_POSITION_STATUS_ACTIVE;
        this.registerDate = DateTimeUtil.getCurrentShamsiDate();
    }
}
