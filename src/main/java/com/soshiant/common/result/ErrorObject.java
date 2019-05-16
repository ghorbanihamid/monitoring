package com.soshiant.common.result;

import java.io.Serializable;


public class ErrorObject implements Serializable {

	private static final long serialVersionUID = -7332649827960471297L;

    private int errorCode = 0;

    private int sqlErrorStep = 0;

    private int sourceErrorStep = 0;

    private String errorKey = "";

    private String sqlText = "";

    private String errorMessage = "";


    public ErrorObject() {

    }

    public ErrorObject(int errorCode) {
        this.errorCode = errorCode;
        this.errorKey = "error.db." + String.valueOf(this.errorCode);
    }

    public ErrorObject(String errorKey) {
        this.errorKey = errorKey;
    }

    public ErrorObject(int errorCode, String errorKey) {
        this.errorCode = errorCode;
        this.errorKey = errorKey;
    }

    public ErrorObject(int errorCode, int sourceErrorStep) {

        this.errorCode = errorCode;
        this.sourceErrorStep = sourceErrorStep;
    }

    public ErrorObject(int errorCode, String errorKey, int sourceErrorStep) {
        this.errorCode = errorCode;
        this.sourceErrorStep = sourceErrorStep;
        this.errorKey = errorKey;
    }

    public ErrorObject(int errorCode, int sqlErrorStep, String errorKey) {
        this.errorCode = errorCode;
        this.sqlErrorStep = sqlErrorStep;
        this.errorKey = errorKey;
    }

    public ErrorObject(int errorCode, int sqlErrorStep, String sqlText, String errorKey) {

        this.errorCode = errorCode;
        this.sqlErrorStep = sqlErrorStep;
        this.errorKey = errorKey;
        this.sqlText = sqlText;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getSqlErrorStep() {
        return sqlErrorStep;
    }

    public void setSqlErrorStep(int sqlErrorStep) {
        this.sqlErrorStep = sqlErrorStep;
    }

    public int getSourceErrorStep() {
        return sourceErrorStep;
    }

    public void setSourceErrorStep(int sourceErrorStep) {
        this.sourceErrorStep = sourceErrorStep;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return "\nErrorObject{" +
            " errorCode = " + errorCode +","+
            " sqlErrorStep =" + sqlErrorStep +","+
            " sourceErrorStep =" + sourceErrorStep +","+
            " sqlText = " + sqlText + "," +
            " errorKey =" + errorKey + "," +
        "}\n";
    }
}
