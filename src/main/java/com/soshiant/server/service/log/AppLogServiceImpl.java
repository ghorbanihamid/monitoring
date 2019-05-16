package com.soshiant.server.service.log;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.dao.log.AppLogDao;
import com.soshiant.server.model.log.AppLog;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class AppLogServiceImpl implements AppLogService {

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private AppLogDao appLogDao;

    public void setAppLogDao(AppLogDao appLogDao) {
        this.appLogDao = appLogDao;
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public List<AppLog> getLogByDoerId(int doerId,int logDate) {
        List<AppLog> appLogList = appLogDao.getLogByDoerId(doerId,logDate);
        return appLogList;
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public List<AppLog> getLogByDate(int logDate) {
        List<AppLog> appLogList = appLogDao.getLogByDate(logDate);
        return appLogList;
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public void logInfo(AppLog appLog) {

        try{
            if(appLog != null) {
                this.appLogDao.insertLog(appLog);
            }
            else {
                log.error("error logging : appLog is null");
            }

        } catch (Exception e) {
            log.error("Exception in logInfo(), AppLog Data : " + appLog + ", Message : " + e.getMessage());
            insertLogToFile(appLog);
        }
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public void logInfo(byte mainOperation, int doerId, int PassiveCustId, int errorCode, int errorStep,
                        int actionCode, String doerType, String PassiveCustType, String actionName,
                        String sqlText, String appMessage, String exceptionMessage, String modelValue) {

        AppLog tempAppLog = new AppLog();
        try {
            tempAppLog.setActionCode(actionCode);

            tempAppLog.setActionName(StringUtils.defaultString(StringUtils.substring(actionName,0, 100)));

            tempAppLog.setSqlText(StringUtils.defaultString(StringUtils.substring(sqlText,0, 200)));

            tempAppLog.setAppMessage(StringUtils.defaultString(StringUtils.substring(appMessage,0, 300)));

            tempAppLog.setExceptionMessage(StringUtils.defaultString(StringUtils.substring(exceptionMessage,0, 500)));

            tempAppLog.setModelValue(StringUtils.defaultString(StringUtils.substring(modelValue,0, 300)));

            tempAppLog.setBrowserName(StringUtils.defaultString(StringUtils.substring(CommonUtil.getClientBrowserName(),0,30)));

            tempAppLog.setDoerId(CommonUtil.getCurrentUserIdFromSession(getHttpSession()));

            tempAppLog.setDoerType(CommonUtil.getCurrentUserTypeNameFromSession(getHttpSession()));

            tempAppLog.setDoerIp(StringUtils.defaultString(StringUtils.substring(CommonUtil.getClientIpAddress(),0,50)));

            tempAppLog.setErrorCode(errorCode);

            tempAppLog.setLogDate(DateTimeUtil.getCurrentShamsiDate());

            tempAppLog.setLogTime(DateTimeUtil.getCurrentTimeStamp());

            tempAppLog.setMainOperation(mainOperation);

            tempAppLog.setPassiveCustomerId(PassiveCustId);

            tempAppLog.setPassiveCustomerType(StringUtils.defaultString(StringUtils.substring(PassiveCustType,0,30)));

            tempAppLog.setSessionId(StringUtils.defaultString(StringUtils.substring(CommonUtil.getClientSessionId(),0,50)));

            tempAppLog.setUrl(StringUtils.defaultString(StringUtils.substring(CommonUtil.getClientUrl(),0,1000)));

            this.appLogDao.insertLog(tempAppLog);

        }
        catch (Exception e) {
            log.error("Exception in logInfo(), Data : " + tempAppLog + ", Message : " + e.getMessage());
            insertLogToFile(tempAppLog);
        }
    }

    //======================================================================================================================
    public void insertLogToFile(AppLog appLog) {

        try {
            if (appLog != null) {
                Logger fileLogger = Logger.getLogger("kliWebAppFileLog");
                fileLogger.info(appLog);
            } else
                log.error("AppLogFacadeImpl.insertLogToFile(), Can not write null log to file");
        } catch (Exception e) {
            log.fatal("Can not write log to file,insertLogToFile(), error : " + e.getMessage());
        }
    }
    //======================================================================================================================
    private HttpSession getHttpSession() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
    }
}
