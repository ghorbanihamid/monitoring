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
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;
import org.apache.struts2.interceptor.validation.SkipValidation;


public class ChangePasswordAction extends ActionSupport implements ModelDriven, PrincipalAware {

    Logger log = Logger.getLogger(this.getClass().getName());
    private String logMessage;
    private String actionName;

    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;

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

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public Object getModel() {
        return kliUser;
    }

//======================================================================================================================
    @SkipValidation
    public String showChangePasswordInputForm() throws Exception {

        this.actionName = "ChangePasswordAction.showChangePasswordInputForm()";

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

    public String saveNewPassword() throws Exception {

        this.actionName = "ChangePasswordAction.saveNewPassword()";

        try{
            String userName = this.principalProxy.getRemoteUser();
            FacadeResult facadeResult = userFacade.changePassword(userName, this.currentPassword,this.newPassword);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error saving New Password ";
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

        if (StringUtils.isEmpty(this.currentPassword)) {
            addActionError(getText("error.changePassword.currentPasswordRequired"));
        }

        if (StringUtils.isEmpty(this.newPassword)) {
            addActionError(getText("error.changePassword.newPasswordRequired"));
        }

        if (StringUtils.isEmpty(this.confirmNewPassword)) {
            addActionError(getText("error.changePassword.confirmNewPasswordRequired"));
        }

        if (!this.confirmNewPassword.equals(this.newPassword)) {
            addActionError(getText("error.changePassword.newPasswordAndConfirmNotEqual"));
        }

        if (this.currentPassword.equals(this.newPassword)) {
            addActionError(getText("error.changePassword.equalCurrentAndNewPassword"));
        }

        if (this.newPassword.trim().length() < ServerConstants.PasswordMinLength || this.newPassword.trim().length() > ServerConstants.PasswordMaxLength) {
            addActionError(getText("error.changePassword.newPasswordLengthIsWrong"));
        }

        if (StringUtils.isNumeric(this.newPassword) || StringUtils.isAlpha(this.newPassword)) {
            addActionError(getText("error.changePassword.newPasswordMustHasNumberAndCharacter"));
        }


    }


}
