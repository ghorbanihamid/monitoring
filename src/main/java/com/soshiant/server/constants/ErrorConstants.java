package com.soshiant.server.constants;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 8/17/11
 * Time: 8:41 PM
 */
public interface ErrorConstants {

    boolean isAppLogEnabled = true;

    public static final int ERROR_CODE_DATA_NOT_FOUND = 100;
    public static final int ERROR_CODE_DAO_EXCEPTION = 101;
    public static final int ERROR_CODE_SERVICE_EXCEPTION = 102;
    public static final int ERROR_CODE_FACADE_EXCEPTION = 103;
    public static final int ERROR_CODE_ACTION_EXCEPTION = 104;


    public static final String ERROR_KEY_RE_LOGIN       = "errors.login.reLogin";

    public static final String ERROR_KEY_DATA_NOT_FOUND    = "error.common.requestedInformationNotAvailable";
    public static final String ERROR_KEY_DAO_EXCEPTION     = "error.common.requestedInformationNotAvailable";
    public static final String ERROR_KEY_SERVICE_EXCEPTION = "error.common.requestedInformationNotAvailable";
    public static final String ERROR_KEY_FACADE_EXCEPTION  = "error.common.requestedInformationNotAvailable";
    public static final String ERROR_KEY_ACTION_EXCEPTION  = "error.common.requestedInformationNotAvailable";

}
