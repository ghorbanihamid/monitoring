package com.soshiant.common.result;

import java.io.Serializable;


public class ResultObject implements Serializable {

	private static final long serialVersionUID = -481548768308113290L;
    
	private Object      resultData  = null;
    private ErrorObject errorObject = null;

    public ResultObject() {

    }

    public ResultObject(Object resultData) {
        this.resultData = resultData;
    }

    public ResultObject(ErrorObject errorObject) {
        this.errorObject = errorObject;
    }

    public ResultObject(Object resultData, ErrorObject errorObject) {
        this.resultData = resultData;
        this.errorObject = errorObject;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    public ErrorObject getErrorObject() {
        return errorObject;
    }

    public void setErrorObject(ErrorObject errorObject) {
        this.errorObject = errorObject;
    }

    public int getErrorCode() {

        if(errorObject == null)
            return 0;
        else
            return errorObject.getErrorCode();
    }

    public int getSourceErrorStep() {

        if(errorObject == null)
            return 0;
        else
            return errorObject.getSourceErrorStep();
    }

    public int getSqlErrorStep() {

        if(errorObject == null)
            return 0;
        else
            return errorObject.getSqlErrorStep();
    }

    public String getSqlText() {

        if(errorObject == null)
            return "";
        else
            return errorObject.getSqlText();
    }

    public String getErrorKey() {

        if(errorObject == null)
            return "";
        else
            return errorObject.getErrorKey();
    }

    public String getErrorMessage() {

        if(errorObject == null)
            return "";
        else
            return errorObject.getErrorMessage();
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "resultData=" + resultData +
                ", errorObject=" + errorObject +
                '}';
    }
}
