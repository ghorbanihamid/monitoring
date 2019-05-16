package com.soshiant.server.facade.log;

import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.model.log.AppLog;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: Dec 10, 2009
 * Time: 10:39:26 AM
 */
public interface AppLogFacade {

    public enum DataKeys {

    }

    public FacadeResult getLogByDoerId(int doerId,int logDate);

    public FacadeResult getLogByDate(int logDate);

    public FacadeResult logInfo(AppLog appLog);

    public FacadeResult logInfo(byte mainOperation, int doerId, int PassiveCustId, int errorCode, int errorStep,
                                int actionCode, String doerType, String PassiveCustType, String actionName,
                                String spName, String appMessage, String exceptionMessage, String modelValue);


}
