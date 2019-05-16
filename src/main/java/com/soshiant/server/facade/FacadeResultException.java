package com.soshiant.server.facade;


import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class FacadeResultException extends RuntimeException implements FacadeResult {

    private int errorCode = 0;

    private int errorStep = 0;

    private String errorKey = "";

    private String sqlText = "";

    private String errorMessage = "";

    public FacadeResultException(Exception exception) {

        convertExceptionToError(exception);

    }

    public FacadeResultException(int errorCode) {

        this.errorCode = errorCode;
        this.errorKey = "error.db." + String.valueOf(this.errorCode);

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

    public boolean isSuccess() {
        return false;
    }

    public Object getData(Enum key) {
        return null;
    }

    public boolean isSuccess(Enum key) {
        return false;
    }

    public int getErrorCode(Enum key) {
        return errorCode;
    }

    public Object getData() {
        return null;
    }

    private void convertExceptionToError(Exception exception) {

        this.errorCode = -1;
        if (SQLException.class.isInstance(exception)) {
            SQLException sqlException = SQLException.class.cast(exception);
            this.errorCode = sqlException.getErrorCode();
            this.errorStep = 0;
            this.errorKey = "error.Common." + String.valueOf(this.errorCode);
            this.errorMessage = sqlException.getMessage();

        }
        if (HibernateException.class.isInstance(exception)) {
            HibernateException hibernateException = HibernateException.class.cast(exception);
            this.errorCode = -1;
            this.errorStep = 0;
            this.errorKey = "" + String.valueOf(this.errorCode);
            this.errorMessage = hibernateException.getMessage();

        }
        if (DataAccessException.class.isInstance(exception)) {
            DataAccessException dataAccessException = DataAccessException.class.cast(exception);
            this.errorCode = -1;
            this.errorStep = 0;
            this.errorKey = "" + String.valueOf(this.errorCode);
            this.errorMessage = dataAccessException.getMessage();

        }
        this.errorMessage = exception != null ? exception.getMessage() : "";
    }

}
