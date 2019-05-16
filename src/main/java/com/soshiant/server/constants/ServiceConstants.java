package com.soshiant.server.constants;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 8/17/11
 * Time: 8:41 PM
 */
public class ServiceConstants {

    private static boolean isAppLogEnabled = false;

    private static boolean sendSmsEnabled = false;

    private static boolean receiveSmsEnabled = false;

    private static boolean sendEmailEnabled = false;

    private static boolean rmfMonitoringIsEnabled = false;
    private static boolean saveRmfMonitoringDataIsEnabled = false;



    public static boolean isIsAppLogEnabled() {
        return isAppLogEnabled;
    }

    public static void setIsAppLogEnabled(boolean isAppLogEnabled) {
        ServiceConstants.isAppLogEnabled = isAppLogEnabled;
    }

    public static boolean isRmfMonitoringIsEnabled() {
        return rmfMonitoringIsEnabled;
    }

    public static void setRmfMonitoringIsEnabled(boolean rmfMonitoringIsEnabled) {
        ServiceConstants.rmfMonitoringIsEnabled = rmfMonitoringIsEnabled;
    }

    public static boolean isSaveRmfMonitoringDataIsEnabled() {
        return saveRmfMonitoringDataIsEnabled;
    }

    public static void setSaveRmfMonitoringDataIsEnabled(boolean saveRmfMonitoringDataIsEnabled) {
        ServiceConstants.saveRmfMonitoringDataIsEnabled = saveRmfMonitoringDataIsEnabled;
    }

    public static boolean isSendSmsEnabled() {
        return sendSmsEnabled;
    }

    public static void setSendSmsEnabled(boolean sendSmsEnabled) {
        ServiceConstants.sendSmsEnabled = sendSmsEnabled;
    }

    public static boolean isReceiveSmsEnabled() {
        return receiveSmsEnabled;
    }

    public static void setReceiveSmsEnabled(boolean receiveSmsEnabled) {
        ServiceConstants.receiveSmsEnabled = receiveSmsEnabled;
    }

    public static boolean isSendEmailEnabled() {
        return sendEmailEnabled;
    }

    public static void setSendEmailEnabled(boolean sendEmailEnabled) {
        ServiceConstants.sendEmailEnabled = sendEmailEnabled;
    }
}
