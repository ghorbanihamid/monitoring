package com.soshiant.common.util;

import com.opensymphony.xwork2.ActionContext;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.log.AppLogFacadeImpl;
import com.soshiant.server.facade.user.UserFacade;
import com.soshiant.server.facade.user.UserFacadeImpl;
import com.soshiant.server.model.user.UserInfo;
import com.soshiant.server.model.user.UserPermissionsView;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class CustomerUtil {

    private static Logger log = Logger.getLogger("CustomerUtil");

    private static String logMessage;
    private static String actionName = "CustomerUtil";

    //=====================================================================================================================
    public static UserInfo getUserInfo() {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if (request == null) {
                log.error(" CustomerUtil.getUserInfo() exception, request is null");
                return null;
            }
            HttpSession session = request.getSession();
            if (session == null) {
                log.error(" CustomerUtil.getUserInfo() exception, session is null");
                return null;
            }
            UserInfo userInfo = (UserInfo) session.getAttribute(ServerConstants.OBJECT_USER_INFO);
            return userInfo;
        }
        catch (Exception e){
            log.error(" CommonUtil.getUserInfo() , exception : " +e.getMessage());
            return null;
        }
    }
    //=====================================================================================================================
    public static boolean saveUserInfoToSession(UserInfo userInfo) {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if (request == null) {
                log.error(" CustomerUtil.saveUserInfoToSession() exception, request is null");
                return false;
            }
            HttpSession session = request.getSession();
            if (session == null) {
                log.error(" CustomerUtil.saveUserInfoToSession() exception, session is null");
                return false;
            }
            session.setAttribute(ServerConstants.OBJECT_USER_INFO, userInfo);
            return true;
        }
        catch (Exception e){
            log.error(" CustomerUtil.saveUserInfoToSession() , exception : " +e.getMessage());
            return false;
        }
    }
    //=====================================================================================================================
    public static List<UserPermissionsView> getUserPermissionFromSession() {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if (request == null) {
                log.error(" CustomerUtil.getUserPermissionFromSession() exception, request is null");
                return null;
            }
            HttpSession session = request.getSession();
            if (session == null) {
                log.error(" CustomerUtil.getUserPermissionFromSession() exception, session is null");
                return null;
            }
            List<UserPermissionsView> userPermissionsList = (List<UserPermissionsView>) session.getAttribute(ServerConstants.OBJECT_USER_PERMISSIONS);
            if(userPermissionsList == null){
                userPermissionsList = getUserPermissionFromDBAndSetToSession((short)0);
            }
            return userPermissionsList;
        }
        catch (Exception e){
            log.error(" CustomerUtil.getUserPermissionFromSession() , exception : " +e.getMessage());
            return null;
        }
    }
    //=====================================================================================================================
    public static List<UserPermissionsView> getUserPermissionFromDBAndSetToSession(int userId) {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if (request == null) {
                log.error(" CustomerUtil.getUserPermissionFromDB() exception, request is null");
                return null;
            }
            HttpSession session = request.getSession();
            if (session == null) {
                log.error(" CustomerUtil.getUserPermissionFromDB() exception, session is null");
                return null;
            }

            if(userId == 0) {
                UserInfo userInfo = getUserInfo();
                if (userInfo == null) {
                    return null;
                }
            }
            UserFacadeImpl userFacade = (UserFacadeImpl) ContextLoader.getCurrentWebApplicationContext().getBean("userFacade");
            AppLogFacadeImpl appLogFacade = (AppLogFacadeImpl) ContextLoader.getCurrentWebApplicationContext().getBean("appLogFacade");
            FacadeResult facadeResult = userFacade.getUserPermissions(userId);
            if (facadeResult.getErrorCode() != 0) {
                logMessage = "CustomerUtil.getUserPermissionFromDB(): error in getUserPermissions() " + facadeResult.getErrorCode();
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", actionName, facadeResult.getSqlText(), logMessage, facadeResult.getErrorMessage()," userId: " + userId );
                return null;
            }
            List<UserPermissionsView> userPermissions =  (List<UserPermissionsView>) facadeResult.getData();

            if (userPermissions == null) {
                logMessage = actionName + " : error getUserPermissionFromDB() , record not found";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", actionName, facadeResult.getSqlText(), logMessage, facadeResult.getErrorMessage(), " userId = " + userId);
                return null;
            }

            session.setAttribute(ServerConstants.OBJECT_USER_PERMISSIONS,userPermissions);
            return userPermissions;
        }
        catch (Exception e){
            log.error(" CustomerUtil.getUserPermissionFromDB() , exception : " +e.getMessage());
            return null;
        }

    }
    public static List<UserPermissionsView> getUserPermissionFromSession(int serverId) {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if (request == null) {
                log.error(" CustomerUtil.getUserPermissionFromSession() exception, request is null");
                return null;
            }
            HttpSession session = request.getSession();
            if (session == null) {
                log.error(" CustomerUtil.getUserPermissionFromSession() exception, session is null");
                return null;
            }
            List<UserPermissionsView> userPermissionsOfServer = new ArrayList<>();

            List<UserPermissionsView> userPermissions = (List<UserPermissionsView>) session.getAttribute(ServerConstants.OBJECT_USER_PERMISSIONS);
            if(userPermissions == null) {
                userPermissions = getUserPermissionFromDBAndSetToSession((short)0);
            }
            if(userPermissions != null) {
                for (UserPermissionsView userPermission : userPermissions) {
                    if (userPermission.getServerId() == serverId) {
                        userPermissionsOfServer.add(userPermission);
                    }
                }
            }
            return userPermissionsOfServer;
        }
        catch (Exception e){
            log.error(" CustomerUtil.getUserPermissionFromSession() , exception : " +e.getMessage());
            return null;
        }
    }
    //=====================================================================================================================

    //=====================================================================================================================

}
