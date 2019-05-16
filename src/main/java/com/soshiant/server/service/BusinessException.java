package com.soshiant.server.service;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class BusinessException extends RuntimeException {


    private int errorCode = 0;

    private int errorStep = 0;

    private String errorKey = "";

    private String sqlText = "";

    private String errorMessage = "";


    public BusinessException(int errorCode) {

        this.errorCode = errorCode;
        this.errorKey = "error.db." + String.valueOf(this.errorCode);

    }

    public BusinessException(int errorCode,String errorKey) {

        this.errorCode = errorCode;
        this.errorKey = errorKey;

    }

    public BusinessException(int errorCode,String errorKey,String errorMessage, String sqlText) {

        this.errorCode = errorCode;
        this.errorKey = errorKey;
        this.errorMessage = errorMessage;
        this.sqlText = sqlText;

    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getErrorStep() {
        return errorStep;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String getSqlText() {
        return sqlText;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
