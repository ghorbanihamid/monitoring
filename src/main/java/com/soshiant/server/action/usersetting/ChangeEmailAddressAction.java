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
import com.soshiant.server.facade.staff.StaffFacade;
import com.soshiant.server.facade.user.UserFacade;
import com.soshiant.server.model.user.UserInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.Map;


public class ChangeEmailAddressAction extends ActionSupport implements ModelDriven {

    Logger log = Logger.getLogger(this.getClass().getName());
    private String logMessage;
    private String actionName;

    private String newEmailAddress;

    private UserFacade userFacade;
    private StaffFacade staffFacade;
    private AppLogFacade appLogFacade;

    UserInfo kliUser = new UserInfo();

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public void setStaffFacade(StaffFacade staffFacade) {
        this.staffFacade = staffFacade;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    public String getNewEmailAddress() {
        return newEmailAddress;
    }

    public void setNewEmailAddress(String newEmailAddress) {
        this.newEmailAddress = newEmailAddress;
    }

    public Object getModel() {
        return kliUser;
    }

//======================================================================================================================
    @SkipValidation
    public String showChangeEmailAddressInputForm() throws Exception {

        this.actionName = "ChangeEmailAddressAction.showChangeEmailAddressInputForm";

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

    public String saveNewEmailAddress() throws Exception {

        this.actionName = "ChangeEmailAddressAction.saveNewEmailAddress";

        try {
            Map session = ActionContext.getContext().getSession();
            String userId = session.get(ServerConstants.OBJECT_USER_ID) == null ? String.valueOf(ServerConstants.OBJECT_USER_ID) : session.get(ServerConstants.OBJECT_USER_ID).toString();
            String userType = session.get(ServerConstants.OBJECT_USER_TYPE) == null ? String.valueOf(ServerConstants.OBJECT_USER_TYPE) : session.get(ServerConstants.OBJECT_USER_TYPE).toString();
            String userName = session.get(ServerConstants.OBJECT_USERNAME) == null ? String.valueOf(ServerConstants.OBJECT_USERNAME) : session.get(ServerConstants.OBJECT_USERNAME).toString();
            if(Integer.parseInt(userType) == ServerConstants.POSITION_TYPE_STUDENT){
                FacadeResult facadeResult = staffFacade.saveStaffEmailAddress(Integer.parseInt(userId), this.newEmailAddress);
                if (facadeResult.getErrorCode() != 0) {
                    this.logMessage = this.actionName + " : error saving New EmailAddress ";
                    log.error(logMessage);
                    if (ServerConstants.isAppLogEnabled)
                        appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STUDENT_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.kliUser.toString());
                    addActionError(getText(facadeResult.getErrorKey()));
                    return INPUT;
                }
            }
            else {
                FacadeResult facadeResult = staffFacade.saveStaffEmailAddress(Integer.parseInt(userId), this.newEmailAddress);
                if (facadeResult.getErrorCode() != 0) {
                    this.logMessage = this.actionName + " : error saving New EmailAddress ";
                    log.error(logMessage);
                    if (ServerConstants.isAppLogEnabled)
                        appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STUDENT_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.kliUser.toString());
                    addActionError(getText(facadeResult.getErrorKey()));
                    return INPUT;
                }
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

        if (CommonUtil.isNullOrEmpty(this.newEmailAddress)) {
            addActionError(getText("error.changeEmailAddress.newEmailRequired"));
        }


    }


}
