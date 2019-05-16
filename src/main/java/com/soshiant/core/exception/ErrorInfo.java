package com.soshiant.core.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: cheshmapoosh
 * Date: Dec 9, 2011
 * Time: 11:03:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class ErrorInfo {

    private Throwable cause                = null;
    private String    errorId              = null;
    private String    contextId            = null;

    public enum ErrorType {CLIENT, EXTERNAL, INTERNAL};
    private ErrorType errorType;
    
    public enum Severity {WARNING, ERROR, FATAL};
    private Severity severity;

    private String    userErrorDescription = null;
    private String    errorDescription     = null;
    private String    errorCorrection      = null;

    private Map<String, Object> parameters =
            new HashMap<String, Object>();

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public String getUserErrorDescription() {
        return userErrorDescription;
    }

    public void setUserErrorDescription(String userErrorDescription) {
        this.userErrorDescription = userErrorDescription;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorCorrection() {
        return errorCorrection;
    }

    public void setErrorCorrection(String errorCorrection) {
        this.errorCorrection = errorCorrection;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}