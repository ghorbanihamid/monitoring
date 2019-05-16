package com.soshiant.server.facade.user;

import com.soshiant.server.dao.user.UserDao;
import com.soshiant.server.dao.usergroup.UserGroupDao;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.SimpleFacadeResult;
import com.soshiant.server.model.user.UserInfo;
import com.soshiant.server.model.user.UserPermissions;
import com.soshiant.server.model.user.UserPermissionsView;
import com.soshiant.server.service.users.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class UserFacadeImpl implements UserFacade {

    UserService userService;

    private UserDao userDao;
    private UserGroupDao userGroupDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

//======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getUser(String userName) {

//        SecureUser secUsr;
        UserInfo user = userDao.getUserInfo(userName);
        if (user != null) {
//            List<UserGroups> userGroupses = userGroupDao.getUserGroupByUser(user.getUserId());
            GrantedAuthority[] grantedGroups = new GrantedAuthority[1];

//
//            if(userGroupses != null) {
//                int i = 0;
////                for (UserGroups userGroup : userGroupses)
            grantedGroups[0] = new GrantedAuthorityImpl("ROLE_ADMIN");
//            }

//            secUsr = new SecureUser(user.getUserName(), user.getPassword(),
//                    new Date().getTime(), true, true, true, true, grantedGroups);

        } else {
            GrantedAuthority[] grantedRoles = {new GrantedAuthorityImpl("GROUP_ANONYMOUS")};
//            secUsr = new SecureUser(userName, "", System.currentTimeMillis(), true,
//                    true, true, true, grantedRoles);
//            secUsr.setFailedLoginAttempts(FAILED_LOGIN_ATTEMPTS++);

        }

        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult();
        return (simpleFacadeResult.success());
    }
//======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getUserInfo(String userName) {

        UserInfo user = userDao.getUserInfo(userName);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(user);
        return (simpleFacadeResult.success());
    }
//======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getUserInfo(int userId) {

        UserInfo user = userDao.getUserInfo(userId);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(user);
        return (simpleFacadeResult.success());
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getUserPermissions(int userId) {

        try {
            List<UserPermissionsView> resultData = userService.getUserPermissions(userId);
            SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(resultData);
            return simpleFacadeResult.success();
        }
        catch (Exception e){
            return null;
        }
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult saveUserInfo(UserInfo user) {
        userDao.saveNewUser(user);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult();
        return simpleFacadeResult.success();
    }
//======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult saveNewUserName(String currentUserName, String newUserName) {

        boolean tmpResult = userDao.saveNewUserName(currentUserName,newUserName);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(tmpResult);
        return simpleFacadeResult.success();
    }
//======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult changePassword(String userName, String currentPassword, String newPassword) {

        boolean tmpResult = userService.changePassword(userName, currentPassword, newPassword);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(tmpResult);
        return simpleFacadeResult.success();
    }
//======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult resetUserPassword(String userName) {

        boolean tmpResult = userDao.resetUserPassword(userName);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(tmpResult);
        return simpleFacadeResult.success();
    }
//======================================================================================================================

    //@Override
    public Class getKeysEnumClass() {
        return DataKeys.class;
    }
}
