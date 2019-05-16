package com.soshiant.server.model.position;

import com.soshiant.common.util.CommonUtil;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Hamid
 * Date: 8/24/11
 * Time: 8:13 PM
 */

@Entity
@Table(name = "POSITIONSINFO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "POSITIONID")})
public class Positions implements java.io.Serializable{

    @Id
    private short  positionId;

    private byte   salaryPercent;
    private String positionEnName;
    private String positionOlName;


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PositionId" , unique = true, nullable = false)
    public short getPositionId() {
        return positionId;
    }

    public void setPositionId(short positionId) {
        this.positionId = positionId;
    }

    @Column(name = "SalaryPercent", unique = false, nullable = false)
    public byte getSalaryPercent() {
        return salaryPercent;
    }

    public void setSalaryPercent(byte salaryPercent) {
        this.salaryPercent = salaryPercent;
    }

    @Column(name = "PositionEnName", unique = false, nullable = false)
    public String getPositionEnName() {
        return positionEnName;
    }

    public void setPositionEnName(String positionEnName) {
        this.positionEnName = positionEnName;
    }

    @Column(name = "PositionOlName", unique = false, nullable = false)
    public String getPositionOlName() {
        return positionOlName;
    }

    public void setPositionOlName(String positionOlName) {
        this.positionOlName = positionOlName;
    }

    public String getPositionName() {

        if(CommonUtil.isCurrentLocalePersian())
            return positionOlName;
        else
            return positionEnName;
    }

    public Positions() {
    }

    public Positions(short id, String positionEnName, String positionOlName) {
        this.positionId = id;
        this.positionEnName = positionEnName;
        this.positionOlName = positionOlName;
    }

}