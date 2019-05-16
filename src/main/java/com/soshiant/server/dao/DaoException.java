package com.soshiant.server.dao;

import org.hibernate.HibernateException;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class DaoException extends RuntimeException {

    private int errorCode = 0;

    private int errorStep = 0;

    private String sqlText = null;

    private String sqlState = null;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorStep() {
        return errorStep;
    }

    public void setErrorStep(int errorStep) {
        this.errorStep = errorStep;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public String getSqlState() {
        return sqlState;
    }

    public void setSqlState(String sqlState) {
        this.sqlState = sqlState;
    }

    //=====================================================================================================

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String spName, int errorCode, int errorStep) {

        this.errorCode = errorCode;
        this.errorStep = errorStep;
        this.sqlText = spName;
        this.sqlState = "";
    }

    public DaoException(String sqlText, SQLException sqlException) {

        super(sqlException.getMessage());
        this.errorCode = sqlException.getErrorCode();
        this.sqlState = sqlException.getSQLState();
        this.sqlText = sqlText;
    }

    public DaoException(String sqlText, HibernateException exception) {

        super(exception.getMessage());
        this.sqlText = sqlText;
    }

    public String toString() {
        return new StringBuffer("{ errorCode : ").append(this.errorCode)
                .append(" errorStep : ").append(this.errorStep)
                .append(" sqlState : ").append(this.sqlState)
                .append(" sqlText : ").append(this.sqlText).append(" }").toString();
    }


}
