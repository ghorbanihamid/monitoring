package com.soshiant.server.action.usersetting;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 12/08/2011
 * Time: 6:05 PM
 */

import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.user.UserFacade;
import com.soshiant.server.model.user.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;
import org.apache.struts2.interceptor.validation.SkipValidation;


public class ChangeUserNameAction extends ActionSupport implements ModelDriven, PrincipalAware {

    Logger log = Logger.getLogger(this.getClass().getName());
    private String logMessage;
    private String actionName;

    private String currentUserName;
    private String newUserName;
    private String confirmNewUserName;

    private UserFacade userFacade;
    private AppLogFacade appLogFacade;

    protected PrincipalProxy principalProxy;


    UserInfo kliUser = new UserInfo();

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    public PrincipalProxy getPrincipalProxy() {
        return principalProxy;
    }

    public void setPrincipalProxy(PrincipalProxy principalProxy) {
        this.principalProxy = principalProxy;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public String getConfirmNewUserName() {
        return confirmNewUserName;
    }

    public void setConfirmNewUserName(String confirmNewUserName) {
        this.confirmNewUserName = confirmNewUserName;
    }

    public Object getModel() {
        return kliUser;
    }

//======================================================================================================================
    @SkipValidation
    public String showChangeUserNameInputForm() throws Exception {

        this.actionName = "ChangeUserNameAction.showChangeUserNameInputForm";

        try {
            this.logMessage = this.actionName + " method : forwarded to input jsp";
            log.debug(logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.STUDENT_ACTION_PROCESS_CODE, "", "UserInfo", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, ServerConstants.EmptyModel);
            return INPUT;
        } catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "UserInfo", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }

    public String saveNewUserName() throws Exception {

        this.actionName = "ChangeUserNameAction.saveNewUserName";

        try {
            FacadeResult facadeResult = userFacade.saveNewUserName(this.currentUserName,this.newUserName);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error saving New UserName ";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STUDENT_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.kliUser.toString());
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STUDENT_ACTION_PROCESS_CODE, "", "UserInfo", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, this.kliUser.toString());

            return SUCCESS;
        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "UserInfo", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), this.kliUser.toString());
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return INPUT;
        }
    }

    public void validate() {

        if (CommonUtil.isNullOrEmpty(this.currentUserName)) {
            addActionError(getText("error.changeUserName.currentUserName.required"));
        }

        String userName = this.principalProxy.getRemoteUser();

        if (!userName.equals(this.currentUserName)) {
            addActionError(getText("error.changeUserName.currentUserNameMustBeYours"));
        }

        if (CommonUtil.isNullOrEmpty(this.newUserName)) {
            addActionError(getText("error.changeUserName.currentUserName.required"));
        }

        if (CommonUtil.isNullOrEmpty(this.confirmNewUserName)) {
            addActionError(getText("error.changeUserName.currentUserName.required"));
        }

        if (!this.confirmNewUserName.equals(this.newUserName)) {
            addActionError(getText("error.changeUserName.newUserNameAndConfirmNotEqual"));
        }

        if (this.currentUserName.equals(this.newUserName)) {
            addActionError(getText("error.changeUserName.equalCurrentAndNewUserName"));
        }
    }


}
