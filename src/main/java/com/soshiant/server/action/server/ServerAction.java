package com.soshiant.server.action.server;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.server.ServerFacade;
import com.soshiant.server.model.server.Server;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 9/18/11
 * Time: 12:01 PM
 */
public class ServerAction extends ActionSupport implements ModelDriven {
    Logger log = Logger.getLogger(ServerAction.class);

    private String actionName;
    private String logMessage = "";

    private Server server = new Server();

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
        return server;
    }
    //======================================================================================================================
    @SkipValidation
    public String showServerInputForm() throws Exception {

        this.actionName = "ServerAction.showServerInputForm()";

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
    public String fetchServerInfo() throws Exception {

        this.actionName = "ServerAction.getServerInfo()";
        clearErrorsAndMessages();
        try {
            FacadeResult facadeResult = serverFacade.getServerInfo(server.getServerId());
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getting Server Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.server.toString());
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            this.server = (Server) facadeResult.getData();
            if(this.server == null){
                this.logMessage = this.actionName + " : error Server not found";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.server.toString());
                addActionError(getText("error.common.serverIdNotFound"));
                return INPUT;
            }

            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, this.server.toString());
            return SUCCESS;
        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), this.server.toString());
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return INPUT;
        }
    }
    //======================================================================================================================
    public String saveServerInfo() throws Exception {

        this.actionName = "ServerAction.saveServerInfo()";
        clearErrorsAndMessages();
        FacadeResult facadeResult;
        try {
            facadeResult = serverFacade.saveServerInfo(server);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error saving Server Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.server.toString());
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, this.server.toString());
            return SUCCESS;
        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), this.server.toString());
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return INPUT;
        }
    }
    //======================================================================================================================
    public void validate() {

        clearErrorsAndMessages();

        if (StringUtils.isEmpty(server.getServerName()))
            addActionError(getText("error.common.olNameRequired"));
        else if (!CommonUtil.isPersianText(server.getServerName()))
            addActionError(getText("error.common.invalidOlName"));

        if (server.getManagerId() <= 0 || String.valueOf(server.getManagerId()).length() < 6)
            addActionError(getText("error.common.enFamilyRequired"));


        if (server.getServerType() <= 0) {
            addActionError(getText("error.common.invalidHomePhone"));
        }

        if (server.getOsType() <= 0) {
            addActionError(getText("error.common.invalidHomePhone"));
        }

        if (!StringUtils.isEmpty(server.getOsVersion())) {
            if (!StringUtils.isNumeric(server.getOsVersion()))
                addActionError(getText("error.common.invalidHomePhone"));
        }

        if (!StringUtils.isEmpty(server.getZosSysplexName())) {
            if (!StringUtils.isNumeric(server.getZosSysplexName()))
                addActionError(getText("error.common.invalidHomePhone"));
        }

        if (!StringUtils.isEmpty(server.getZosSystemId())) {
            if (!StringUtils.isNumeric(server.getZosSystemId()))
                addActionError(getText("error.common.invalidHomePhone"));
        }

        if (!StringUtils.isEmpty(server.getServerDescription())) {
            if (!StringUtils.isEmpty(server.getServerDescription())) {
                addActionError(getText("error.common.homePhoneRequired"));
            }
        }
    }

}
