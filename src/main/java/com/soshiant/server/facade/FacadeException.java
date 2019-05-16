package com.soshiant.server.facade;

import com.soshiant.server.service.BusinessException;
import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class FacadeException extends RuntimeException implements FacadeResult {


    private int errorCode = 0;

    private int errorStep = 0;

    private String errorKey = "";

    private String sqlText = "";

    private String errorMessage = "";

    private FacadeResult facadeResult;


    public FacadeException(Exception exception) {

        System.out.print("error : " + exception.getMessage() + " , StackTrace : " + exception.getStackTrace());
        convertExceptionToError(exception);

    }

    public FacadeException(int errorCode) {

        this.errorCode = errorCode;
        this.errorKey = "error.db." + String.valueOf(this.errorCode);

    }

    public FacadeException(FacadeResult facadeResult) {
        this(facadeResult, "");
    }

    public FacadeException(FacadeResult facadeResult, String message) {
        super(message);
        this.facadeResult = facadeResult;
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

        if (BusinessException.class.isInstance(exception)){
            BusinessException  businessException = BusinessException.class.cast(exception);
            this.errorCode = businessException.getErrorCode();
            this.errorStep = businessException.getErrorStep();
            this.errorKey = businessException.getErrorKey() != null ? businessException.getErrorKey() : "";
            this.errorMessage = businessException.getErrorMessage() != null ? businessException.getMessage() : "";
            return;
        }

        if(SQLException.class.isInstance(exception)){
            SQLException sqlException = SQLException.class.cast(exception);
            this.errorCode = sqlException.getErrorCode();
            this.errorStep = 0;
            this.errorKey = "error.db." + String.valueOf(this.errorCode);
            this.errorMessage = sqlException.getMessage()!= null ? sqlException.getMessage() : "";
            return;

        }
        if(HibernateException.class.isInstance(exception)){
            HibernateException hibernateException = HibernateException.class.cast(exception);
            this.errorCode = -1;
            this.errorStep = 0;
            this.errorKey = "error.common.systemCouldNotRespond1";
            this.errorMessage = hibernateException.getMessage()!= null ? hibernateException.getMessage() : "";
            return;
        }
        if(DataAccessException.class.isInstance(exception)){
            DataAccessException dataAccessException = DataAccessException.class.cast(exception);
            this.errorCode = -1;
            this.errorStep = 0;
            this.errorKey = "error.common.systemCouldNotRespond1";
            this.errorMessage = dataAccessException.getMessage() != null ? dataAccessException.getMessage() : "";
            return;
        }

        if(NullPointerException.class.isInstance(exception)){

            NullPointerException nullPointerException = NullPointerException.class.cast(exception);
            this.errorCode = -1;
            this.errorStep = 0;
            this.errorKey = "error.common.systemCouldNotRespond1";
            this.errorMessage = nullPointerException.toString();
            return;
        }
        this.errorCode = -1;
        this.errorStep = 0;
        this.errorKey = "error.common.systemCouldNotRespond1";
        if(exception.toString() != null){
            this.errorMessage = exception.toString();
            return;
        }
        if(exception.getMessage() != null){
            this.errorMessage = exception.getMessage();
            return;
        }
        if(exception.getStackTrace()!= null){
            if(exception.getStackTrace()[0] != null)
                this.errorMessage = exception.getStackTrace()[0].toString();
        }

    }




    public FacadeResult getFacadeResult() {
        return facadeResult;
    }

    public FacadeException(FacadeResult facadeResult, Throwable cause) {
        this(facadeResult);
        initCause(cause);
    }


//    @Override
//    public FacadeException getFacadeException() {
//        return facadeResult.getFacadeException();
//    }
}
