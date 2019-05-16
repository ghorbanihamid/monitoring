package com.soshiant.server.action.login;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

import com.soshiant.common.util.CommonUtil;
import com.soshiant.common.util.CustomerUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.login.LoginFacade;
import com.soshiant.server.facade.parameters.SmsFacade;
import com.soshiant.server.facade.staff.StaffFacade;
import com.soshiant.server.facade.user.UserFacade;
import com.soshiant.server.model.staff.Staff;
import com.soshiant.server.model.staff.StaffPosition;
import com.soshiant.server.model.user.UserInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.soshiant.server.model.user.UserPermissions;
import com.soshiant.server.model.user.UserPermissionsView;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginAction extends ActionSupport implements PrincipalAware,ServletRequestAware {

    private Logger log = Logger.getLogger(LoginAction.class);

    static public final Map locales = new HashMap();

    static {
        locales.put("en_US", "English");
        locales.put("fa_IR", "Persian");
//        locales.put("es_SP", "Spanish");
    }

    public Map getLocales() {
        return locales;
    }

    protected PrincipalProxy principalProxy;

    public PrincipalProxy getPrincipalProxy() {
        return principalProxy;
    }

    public void setPrincipalProxy(PrincipalProxy principalProxy) {
        this.principalProxy = principalProxy;
    }

    private String logMessage;
    private String actionName;

    private String  userName;
    private String  userPassword;
    private boolean loginAsTeacher;

    private UserFacade userFacade;
    private StaffFacade staffFacade;
    private LoginFacade loginFacade;
    private AppLogFacade appLogFacade;
    private SmsFacade smsFacade;

    private HttpServletRequest request;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isLoginAsTeacher() {
        return loginAsTeacher;
    }

    public void setLoginAsTeacher(boolean loginAsTeacher) {
        this.loginAsTeacher = loginAsTeacher;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public void setStaffFacade(StaffFacade staffFacade) {
        this.staffFacade = staffFacade;
    }

    public void setLoginFacade(LoginFacade loginFacade) {
        this.loginFacade = loginFacade;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    public void setSmsFacade(SmsFacade smsFacade) {
        this.smsFacade = smsFacade;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
    private HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
//======================================================================================================================
    public String execute() {

        this.actionName = "LoginAction.execute";
        CommonUtil.setLanguageToEnglish();
        log.debug("Current locale is : " + CommonUtil.getCurrentLocale().toString());
        return SUCCESS;

    }
//======================================================================================================================
    @SkipValidation
    public String loginSucceed() {
        try{
            boolean firstLogin = false;
//            smsFacade.sendSMSMessage("989122183481","Dear ostad azam, up to this moment you are 1st good teacher of institute, congratulation");
            String userName = this.principalProxy.getRemoteUser();
            FacadeResult facadeResult = userFacade.getUserInfo(userName);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getting UserInfo Info of user : " + userName;
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "userName : " + userName);
                addActionError(getText(facadeResult.getErrorKey()));
                return ERROR;
            }
            UserInfo userInfo = (UserInfo) facadeResult.getData();

            if (userInfo == null) {
                this.logMessage = this.actionName + " : UserInfo not found , userName : " + userName;
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 8010, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", this.actionName, ServerConstants.NoSqlText, this.logMessage, "", "userName : " + userName);
                addActionError(getText("errors.login.invalidUserNameOrPassword"));
                return ERROR;
            }

            if(userInfo.getPassChgDate() == 0){
                firstLogin = true;
            }
            facadeResult = staffFacade.getStaffInfo(userInfo.getUserId());
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getting Staff Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(),"userName : " + userName + ", userId:" + userInfo.getUserId() );
                addActionError(getText(facadeResult.getErrorKey()));
                return ERROR;
            }

            Staff staffInfo =  (Staff) facadeResult.getData();

            if (staffInfo == null) {
                this.logMessage = this.actionName + " : error getting Staff Info, Staff not found";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "userName : " + userName);
                addActionError(getText("error.common.staffIdNotFoundForUserId"));
                return ERROR;
            }

            if(staffInfo.getStaffStatus() != ServerConstants.STAFF_STATUS_ACTIVE){

                this.logMessage = this.actionName + " : Staff status is not active";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", this.actionName, ServerConstants.NoSqlText, this.logMessage, getText("error.common.staffIsNotActive"), "userName : " + userName + ", "+ staffInfo.toString());
                addActionError(getText("error.common.staffIsNotActive"));
                return ERROR;
            }

            facadeResult = staffFacade.getStaffPositionListInfo(staffInfo.getStaffId());
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getting Staff Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(),"userName : " + userName + ", userId: " + userInfo.getUserId() );
                addActionError(getText(facadeResult.getErrorKey()));
                return ERROR;
            }

            List<StaffPosition> staffPositions =  (List<StaffPosition>) facadeResult.getData();

            if (staffPositions == null) {
                this.logMessage = this.actionName + " : error getting Staff Positions , Staff Positions not found";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "userName : " + userName +" staffId = " + staffInfo.getStaffId());
                addActionError(getText("error.common.staffIdNotFoundForUserId"));
                return ERROR;
            }

            String userLoginStatus = ServerConstants.OBJECT_USER_LOGIN_STATUS_COMMON_USER;
            if(userLoginStatus.equals("")){
                this.logMessage = this.actionName + " login error, couldn't find right status to login";
                log.error(this.logMessage );
                addActionError(getText("errors.login.incompleteUserInformation"));
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "Login", this.actionName, ServerConstants.NoSqlText, this.logMessage, "", "userName : " + userName);
                return ERROR;
            }

            if(firstLogin){
                userLoginStatus = ServerConstants.OBJECT_USER_LOGIN_STATUS_FIRST_LOGIN;
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", "LoginAction.loginSucceed as :" + userLoginStatus, "", "firstLogin, forwarded to change password form", "", "staffId = " + staffInfo.getStaffId());
            }
            else{
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", "LoginAction.loginSucceed as" + userLoginStatus, "", "", "", "staffId = " + staffInfo.getStaffId());
            }
            Map session = ActionContext.getContext().getSession();
            session.put(ServerConstants.OBJECT_USER_LOGIN_STATUS, userLoginStatus);

            CustomerUtil.saveUserInfoToSession(userInfo);

            List<UserPermissionsView> userPermissions = CustomerUtil.getUserPermissionFromDBAndSetToSession(staffInfo.getStaffId());

            if (userPermissions == null) {
                this.logMessage = this.actionName + " : error CustomerUtil.getUserPermissionFromDBAndSetToSession(), record not found";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "userName : " + userName +" staffId = " + staffInfo.getStaffId());
                addActionError(getText("error.common.staffIdNotFoundForUserId"));
                return ERROR;
            }

            return userLoginStatus;

        }catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            addActionError(getText("error.common.systemCouldNotRespond1"));
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "Login", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            return ERROR;
        }
    }
//======================================================================================================================
    @SkipValidation
    public String loginFailed() {

        try {
            this.logMessage = " LoginAction.loginFailed, UserInfo name or password is invalid";
            log.error(logMessage);
            if (ServerConstants.isAppLogEnabled) {
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", "LoginAction.loginFailed", ServerConstants.NoSqlText, this.logMessage, "", "UserName : " + this.userName);
            }
//            addActionError(getText("errors.login.invalidUserNameOrPassword"));
            return ERROR;

        }catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }
//======================================================================================================================
    public void validate() {

        log.debug("user Action validate method Started.");
        int userNameLength = this.userName.length();
        if (this.userName.length() == 0) {
            addFieldError("userName", getText("error.login.userNameRequired"));
        }
        if (userNameLength < ServerConstants.UserNameMinLength || userNameLength > ServerConstants.UserNameMaxLength) {
            addFieldError("userName", getText("error.login.invalidUserNameRange"));
        }

        int passLength = this.userPassword.length();
        if (passLength == 0) {
            addFieldError("password", getText("error.login.passwordRequired"));
        }
        if (passLength < ServerConstants.PasswordMinLength || passLength > ServerConstants.PasswordMaxLength) {
            addFieldError("password", getText("error.login.invalidPasswordRange"));
        }
        log.debug("user Action validate method finished successfully.");
    }
//======================================================================================================================
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
//======================================================================================================================

}