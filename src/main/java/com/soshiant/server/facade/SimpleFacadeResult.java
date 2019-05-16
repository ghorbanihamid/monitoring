package com.soshiant.server.facade;

import com.soshiant.common.util.BundleUtil;

import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class SimpleFacadeResult<K extends Enum, D> implements FacadeResult<K, D> {

    private int errorCode = 0;

    private int errorStep = 0;

    private String errorKey = "";

    private String sqlText = "";

    private String errorMessage = "";

    private D data;


    public SimpleFacadeResult(D data) {
        setData(data);
    }

    public SimpleFacadeResult() {

    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorStep() {
        return errorStep;
    }

    public String getErrorKey() {
        if(errorKey.equals(""))
            errorKey = "error.db." + Math.abs(errorCode);
        return errorKey;
    }

    public String getSqlText() {
        return sqlText;
    }

    public String getErrorMessage() {

        if(errorKey.equals("")) {
            Locale locale = new Locale("en", "US");
            BundleUtil bundle = BundleUtil.getInstance(locale);
            errorMessage = bundle.getMessage(errorKey);
        }
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public D getData(K key) {
        return data;
    }

    public int getErrorCode(K key) {
        return errorCode;
    }

    public FacadeResult success() {
        setErrorCode(0);
        return this;
    }

    public FacadeResult fail() {
        setErrorCode(-1);
        return this;
    }

    public FacadeResult fail(int errorCode) {
        setErrorCode(errorCode);
        return this;
    }


    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
