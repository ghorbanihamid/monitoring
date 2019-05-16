package com.soshiant.server.action.server;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.server.ServerFacade;
import com.soshiant.server.model.server.RmfConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 2/18/16
 * Time: 12:01 PM
 */
public class RmfConfigAction extends ActionSupport implements ModelDriven {
    Logger log = Logger.getLogger(RmfConfigAction.class);

    private String actionName;
    private String logMessage = "";

    private RmfConfig rmfConfig = new RmfConfig();

    private ServerFacade serverFacade;
    private AppLogFacade appLogFacade;


    public void setServerFacade(ServerFacade serverFacade) {
        this.serverFacade = serverFacade;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    //======================================================================================================================
    public Object getModel() {
        return rmfConfig;
    }
    //======================================================================================================================
    @SkipValidation
    public String showRmfConfigInputForm() throws Exception {

        this.actionName = "RmfConfigAction.showRmfConfigInputForm()";

        try {
            this.logMessage = this.actionName + " method : forwarded to input jsp";
            log.debug(logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, ServerConstants.EmptyModel);
            return INPUT;
        } catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }
    //======================================================================================================================
    @SkipValidation
    public String fetchRmfConfigInfo() throws Exception {

        this.actionName = "RmfConfigAction.fetchRmfConfigInfo()";
        clearErrorsAndMessages();
        try {
            FacadeResult facadeResult = serverFacade.getRmfConfigInfo(rmfConfig.getConfigId());
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error fetchRmfConfigInfo()";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.rmfConfig.toString());
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            this.rmfConfig = (RmfConfig) facadeResult.getData();
            if(this.rmfConfig == null){
                this.logMessage = this.actionName + " : error RmfConfig not found";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.rmfConfig.toString());
                addActionError(getText("error.common.serverIdNotFound"));
                return INPUT;
            }

            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, this.rmfConfig.toString());
            return SUCCESS;
        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), this.rmfConfig.toString());
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return INPUT;
        }
    }
    //======================================================================================================================
    public String saveRmfConfigInfo() throws Exception {

        this.actionName = "RmfConfigAction.saveRmfConfigInfo()";
        clearErrorsAndMessages();
        FacadeResult facadeResult;
        try {
            facadeResult = serverFacade.saveRmfConfigInfo(rmfConfig);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error saveRmfConfigInfo()";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.rmfConfig.toString());
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, this.rmfConfig.toString());
            return SUCCESS;
        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), this.rmfConfig.toString());
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return INPUT;
        }
    }
    //======================================================================================================================
    public void validate() {

        clearErrorsAndMessages();

        if (rmfConfig.getServerId() <= 0) {
            addActionError(getText("error.common.olNameRequired"));
        }

        if (rmfConfig.getUnusualLessValue() <= 0)
            addActionError(getText("error.common.enFamilyRequired"));


        if (rmfConfig.getUnusualMoreValue() <= 0) {
            addActionError(getText("error.common.invalidHomePhone"));
        }

        if (rmfConfig.getCriticalLessValue() <= 0) {
            addActionError(getText("error.common.invalidHomePhone"));
        }

        if (rmfConfig.getCriticalMoreValue() <= 0) {
            addActionError(getText("error.common.invalidHomePhone"));
        }

        if (!StringUtils.isEmpty(rmfConfig.getMetricId())) {
            if (!StringUtils.isEmpty(rmfConfig.getMetricId())) {
                addActionError(getText("error.common.homePhoneRequired"));
            }
        }
    }

}
