package com.soshiant.server.action.usersetting;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 12/08/2011
 * Time: 6:05 PM
 */


import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.staff.StaffFacade;
import com.soshiant.server.facade.user.UserFacade;
import com.soshiant.server.model.staff.Staff;
import com.soshiant.server.model.user.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;
import org.apache.struts2.interceptor.validation.SkipValidation;


public class ResetPasswordAction extends ActionSupport implements PrincipalAware {

    Logger log = Logger.getLogger(this.getClass().getName());
    private String logMessage;
    private String actionName;

    private String userName;
    private String userInfoDesc;

    private StaffFacade staffFacade;

    private UserFacade userFacade;
    private AppLogFacade appLogFacade;

    protected PrincipalProxy principalProxy;

    UserInfo kliUser = new UserInfo();

    public void setStaffFacade(StaffFacade staffFacade) {
        this.staffFacade = staffFacade;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserInfoDesc() {
        return userInfoDesc;
    }

    public void setUserInfoDesc(String userInfoDesc) {
        this.userInfoDesc = userInfoDesc;
    }

//======================================================================================================================
    @SkipValidation
    public String showResetPasswordInputForm() throws Exception {

        this.actionName = "ResetPasswordAction.showChangePasswordInputForm";

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
//======================================================================================================================
    @SkipValidation
    public String fetchUserInfoForResettingPassword() throws Exception {

        this.actionName = "ResetPasswordAction.getUserInfoForResettingPassword()";
        clearErrorsAndMessages();
        try {
            if (StringUtils.isEmpty(this.userName)) {
                addActionError(getText("error.common.userNameRequired"));
                return INPUT;
            }
            FacadeResult facadeResult = userFacade.getUserInfo(this.userName);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getting Student Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STUDENT_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "UserName: " + this.userName);
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            UserInfo tmpUserInfo = (UserInfo) facadeResult.getData();
            if(tmpUserInfo == null){

                addActionError(getText("error.common.invalidUserId"));
                return INPUT;
            }
            if(!tmpUserInfo.isEnabled()){

                addActionError(getText("error.common.userIsNotActive"));
                return INPUT;
            }

            facadeResult = staffFacade.getStaffInfo(tmpUserInfo.getUserId());
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getting Staff Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STUDENT_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "UserName: " + this.userName);
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            Staff staffInfo = (Staff) facadeResult.getData();
            if(staffInfo == null){

                addActionError(getText("error.common.invalidStudentId"));
                return INPUT;
            }
            if(staffInfo.getStaffStatus() != ServerConstants.STAFF_STATUS_ACTIVE){

                addActionError(getText("error.common.studentIsNotActive"));
                return INPUT;
            }
            this.userInfoDesc = getText("label.staff") + " : " + staffInfo.getStaffName();

            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STUDENT_ACTION_PROCESS_CODE, "", "StudentAdvisorLog", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, "UserName: " + this.userName);
            return SUCCESS;
        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "StudentAdvisorLog", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), "UserName: " + this.userName);
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return INPUT;
        }

    }
//======================================================================================================================
    public String resetUserPassword() throws Exception {

        this.actionName = "ResetPasswordAction.resetUserPassword";

        try{
            if (StringUtils.isEmpty(this.userName)) {
                addActionError(getText("error.common.userNameRequired"));
                return INPUT;
            }
            FacadeResult facadeResult = userFacade.getUserInfo(this.userName);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getting User Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STUDENT_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "UserName: " + this.userName);
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            UserInfo tmpUserInfo = (UserInfo) facadeResult.getData();
            if(tmpUserInfo == null){

                addActionError(getText("error.common.invalidUserId"));
                return INPUT;
            }
            if(!tmpUserInfo.isEnabled()){

                addActionError(getText("error.common.userIsNotActive"));
                return INPUT;
            }

            String userName = this.principalProxy.getRemoteUser();
            facadeResult = userFacade.resetUserPassword(userName);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error resetting User Password ";
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
//======================================================================================================================

}
