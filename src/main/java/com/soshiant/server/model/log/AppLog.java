package com.soshiant.server.model.log;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 8/3/11
 * Time: 8:20 PM
 */

@Entity
@Table(name = "applog", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")})
public class AppLog implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id = 0;

    private byte mainOperation = 0;

    private int doerId = 0;

    private int passiveCustomerId = 0;

    private int logDate = 0;

    private int errorCode = 0;

    private int actionCode;

    private String doerIp = "";

    private String doerType = "";

    private String passiveCustomerType = "";

    private String sqlText = "";

    private String actionName = "";

    private String appMessage = "";

    private String exceptionMessage = "";

    private String modelValue = "";

    private String sessionId = "";

    private String browserName = "";

    private String url = "";

    private Timestamp logTime;


    @Column(name = "ID", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "MainOperation", unique = true, nullable = false)
    public byte getMainOperation() {
        return mainOperation;
    }

    public void setMainOperation(byte mainOperation) {
        this.mainOperation = mainOperation;
    }

    @Column(name = "DoerId", unique = true, nullable = false)
    public int getDoerId() {
        return doerId;
    }

    public void setDoerId(int doerId) {
        this.doerId = doerId;
    }

    @Column(name = "PassiveCustomerId", unique = true, nullable = false)
    public int getPassiveCustomerId() {
        return passiveCustomerId;
    }

    public void setPassiveCustomerId(int passiveCustomerId) {
        passiveCustomerId = passiveCustomerId;
    }

    @Column(name = "DoerIp", unique = true, nullable = false)
    public String getDoerIp() {
        return doerIp;
    }

    public void setDoerIp(String doerIp) {
        this.doerIp = doerIp;
    }

    @Column(name = "LogDate", unique = true, nullable = false)
    public int getLogDate() {
        return logDate;
    }

    public void setLogDate(int logDate) {
        this.logDate = logDate;
    }

    @Column(name = "ErrorCode", unique = true, nullable = false)
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Column(name = "ActionCode", unique = true, nullable = false)
    public int getActionCode() {
        return actionCode;
    }

    public void setActionCode(int actionCode) {
        this.actionCode = actionCode;
    }

    @Column(name = "DoerType", unique = true, nullable = false)
    public String getDoerType() {
        return doerType;
    }

    public void setDoerType(String doerType) {
        this.doerType = doerType;
    }

    @Column(name = "PassiveCustomerType", unique = true, nullable = false)
    public String getPassiveCustomerType() {
        return passiveCustomerType;
    }

    public void setPassiveCustomerType(String passiveCustType) {
        this.passiveCustomerType = passiveCustType;
    }

    @Column(name = "SqlText", unique = true, nullable = false)
    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    @Column(name = "ActionName", unique = true, nullable = false)
    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Column(name = "AppMessage", unique = true, nullable = false)
    public String getAppMessage() {
        return appMessage;
    }

    public void setAppMessage(String appMessage) {
        this.appMessage = appMessage;
    }

    @Column(name = "exceptionMessage", unique = true, nullable = false)
    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Column(name = "modelValue", unique = true, nullable = false)
    public String getModelValue() {
        return modelValue;
    }

    public void setModelValue(String modelValue) {
        this.modelValue = modelValue;
    }

    @Column(name = "SessionId", unique = true, nullable = false)
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Column(name = "BrowserName", unique = true, nullable = false)
    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    @Column(name = "Url", unique = true, nullable = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "LogTime", unique = true, nullable = false)
    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }


    @Override
    public String toString() {
        return "AppLog{" +
                "id=" + id +
                ", mainOperation=" + mainOperation +
                ", doerId=" + doerId +
                ", passiveCustomerId=" + passiveCustomerId +
                ", logDate=" + logDate +
                ", errorCode=" + errorCode +
                ", actionCode=" + actionCode +
                ", doerIp='" + doerIp + '\'' +
                ", doerType='" + doerType + '\'' +
                ", passiveCustomerType='" + passiveCustomerType + '\'' +
                ", sqlText='" + sqlText + '\'' +
                ", actionName='" + actionName + '\'' +
                ", appMessage='" + appMessage + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", modelValue='" + modelValue + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", browserName='" + browserName + '\'' +
                ", url='" + url + '\'' +
                ", logTime=" + logTime +
                '}';
    }
}
