package com.soshiant.server.model.parameters;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

@Entity
@Table(name = "BANKSINFO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "BankCode")})
public class Bank implements java.io.Serializable {

    @Id
    private byte   bankCode;
    private byte   statusX;
    private String bankEnName;
    private String bankOlName;


    @Column(name = "BankCode", unique = true, nullable = false)
    public byte getBankCode() {
        return bankCode;
    }

    public void setBankCode(byte bankCode) {
        this.bankCode = bankCode;
    }

    @Column(name = "StatusX", unique = true, nullable = false)
    public byte getStatusX() {
        return statusX;
    }

    public void setStatusX(byte statusX) {
        this.statusX = statusX;
    }

    @Column(name = "BankEnName", unique = true, nullable = false)
    public String getBankEnName() {
        return bankEnName;
    }

    public void setBankEnName(String bankEnName) {
        this.bankEnName = bankEnName;
    }

    @Column(name = "BankOlName", unique = true, nullable = false)
    public String getBankOlName() {
        return bankOlName;
    }

    public void setBankOlName(String bankOlName) {
        this.bankOlName = bankOlName;
    }

    public Bank(){

    }

    public Bank(byte bankCode,String bankEnName,String bankOlName){

        this.bankCode = bankCode;
        this.bankEnName = bankEnName;
        this.bankOlName = bankOlName;

    }
}
