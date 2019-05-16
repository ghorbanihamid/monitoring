package com.soshiant.server.service.log;

import com.soshiant.server.model.log.AppLog;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: Dec 10, 2009
 * Time: 10:39:26 AM
 */
public interface AppLogService {

    public  List<AppLog> getLogByDoerId(int doerId,int logDate);

    public List<AppLog> getLogByDate(int logDate);

    public void logInfo(AppLog appLog);

    public void logInfo(byte mainOperation, int doerId, int PassiveCustId, int errorCode, int errorStep,
                        int actionCode, String doerType, String PassiveCustType, String actionName,
                        String spName, String appMessage, String exceptionMessage, String modelValue);


}
