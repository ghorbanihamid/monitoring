package com.soshiant.server.facade.log;

import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.SimpleFacadeResult;
import com.soshiant.server.model.log.AppLog;
import com.soshiant.server.service.log.AppLogService;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class AppLogFacadeImpl implements AppLogFacade {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    private AppLogService appLogService;

    public void setAppLogService(AppLogService appLogService) {
        this.appLogService = appLogService;
    }

//======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getLogByDoerId(int doerId,int logDate) {
        List<AppLog> appLogList = appLogService.getLogByDoerId(doerId,logDate);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(appLogList);
        return simpleFacadeResult.success();
    }
//======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getLogByDate(int logDate) {
        List<AppLog> appLogList = appLogService.getLogByDate(logDate);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(appLogList);
        return simpleFacadeResult.success();
    }
//======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult logInfo(AppLog appLog) {

        this.appLogService.logInfo(appLog);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult();
        return simpleFacadeResult.success();
    }
//======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult logInfo(byte mainOperation, int doerId, int PassiveCustId, int errorCode, int errorStep,
                        int actionCode, String doerType, String PassiveCustType, String actionName,
                        String sqlText, String appMessage, String exceptionMessage, String modelValue) {

        this.appLogService.logInfo(mainOperation, doerId, PassiveCustId, errorCode, errorStep,
                                   actionCode, doerType, PassiveCustType, actionName,
                                   sqlText, appMessage, exceptionMessage, modelValue);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult();
        return simpleFacadeResult.success();


    }

//======================================================================================================================

    //@Override
    public Class getKeysEnumClass() {
        return DataKeys.class;
    }
}
