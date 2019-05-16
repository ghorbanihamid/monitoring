package com.soshiant.server.action.login;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

import com.soshiant.common.util.CommonUtil;
import com.soshiant.common.util.FileUtils;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.monitoring.MonitoringFacade;
import com.soshiant.server.facade.parameters.ParametersFacade;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class WelcomeAction extends ActionSupport implements ServletRequestAware {

    private Logger log = Logger.getLogger(WelcomeAction.class);
    private String actionName;
    private String logMessage = "";

    private ParametersFacade parametersFacade;
    private MonitoringFacade monitoringFacade;

    private AppLogFacade appLogFacade;

    private HttpServletRequest servletRequest;


    public void setParametersFacade(ParametersFacade parametersFacade) {
        this.parametersFacade = parametersFacade;
    }

    public void setMonitoringFacade(MonitoringFacade monitoringFacade) {
        this.monitoringFacade = monitoringFacade;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    static public final Map locales = new HashMap();

    static {
        locales.put("en_US", "English");
        locales.put("fa_IR", "Persian");
    }

    public Map getLocales() {
        return locales;
    }

    @SkipValidation
    public String execute() throws Exception {

        this.actionName = "WelcomeAction.execute()";
        clearErrorsAndMessages();
        try {
            FileUtils.setJasperReportRealPath(servletRequest.getSession().getServletContext().getRealPath(FileUtils.JASPER_REPORT_REAL_PATH) +
                                    FileUtils.getPathSeparator());

            FileUtils.setExportRealPath(servletRequest.getSession().getServletContext().getRealPath(FileUtils.EXPORT_REAL_PATH) +
                                    FileUtils.getPathSeparator());

            CommonUtil.setLanguageToEnglish();
            log.debug("Current locale is : " + CommonUtil.getCurrentLocale().toString());

            startRmfMonitoringService();
            this.logMessage = this.actionName + " successfully done.";
            log.info(logMessage);
            return SUCCESS;

        }
        catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }
    //==================================================================================================================
    public String startRmfMonitoringService() throws Exception {

        this.actionName = "WelcomeAction.startRmfMonitoringService()";
        clearErrorsAndMessages();
        try {
            FacadeResult facadeResult = parametersFacade.getRmfMonitoringServiceStatus();
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getRmfMonitoringServiceStatus()";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, ServerConstants.NoSqlText, this.logMessage, facadeResult.getErrorMessage(), ServerConstants.EmptyModel);
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            boolean mfMonitoringIsEnabled = (boolean) facadeResult.getData();
            if(mfMonitoringIsEnabled) {

                facadeResult = parametersFacade.getSaveRmfMonitoringDataServiceStatus();
                if (facadeResult.getErrorCode() != 0) {
                    this.logMessage = this.actionName + " : error getSaveRmfMonitoringDataServiceStatus()";
                    log.error(logMessage);
                    if (ServerConstants.isAppLogEnabled)
                        appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, ServerConstants.NoSqlText, this.logMessage, facadeResult.getErrorMessage(), ServerConstants.EmptyModel);
                }

                facadeResult = monitoringFacade.createRMFMonitoringThreads();
                if (facadeResult.getErrorCode() != 0) {
                    this.logMessage = this.actionName + " : error createRMFMonitoringThreads()";
                    log.error(logMessage);
                    if (ServerConstants.isAppLogEnabled)
                        appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, ServerConstants.NoSqlText, this.logMessage, facadeResult.getErrorMessage(), ServerConstants.EmptyModel);
                    addActionError(getText(facadeResult.getErrorKey()));
                    return INPUT;
                }
            }
            this.logMessage = this.actionName + " startRmfMonitoringService() successfully done.";
            log.info(logMessage);
            return SUCCESS;

        }
        catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }
    //==================================================================================================================
    public String checkForStartingSmsAndEmailServices() throws Exception {

        this.actionName = "WelcomeAction.checkForStartingSmsAndEmailServices()";
        clearErrorsAndMessages();
        try {
            FacadeResult facadeResult = parametersFacade.checkForStartingSmsAndEmailServices();
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getRmfMonitoringServiceStatus()";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, ServerConstants.NoSqlText, this.logMessage, facadeResult.getErrorMessage(), ServerConstants.EmptyModel);
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            this.logMessage = this.actionName + " checkForStartingSmsAndEmailServices() successfully done.";
            log.info(logMessage);
            return SUCCESS;

        }
        catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }
    //==================================================================================================================
    public String changeLanguage() {

        log.debug("Current locale is : " + CommonUtil.getCurrentLocale().toString());
        String currentLanguage = CommonUtil.getCurrentLocale().getLanguage();
        if (currentLanguage.equals("en")) {
            CommonUtil.setLanguageToPersian();
        } else {
            CommonUtil.setLanguageToEnglish();
        }
        log.debug("Language Changed : " + CommonUtil.getCurrentLocale().toString());
        return SUCCESS;
    }
    //==================================================================================================================
}

