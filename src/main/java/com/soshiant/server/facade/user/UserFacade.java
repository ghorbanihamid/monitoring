package com.soshiant.server.facade.user;


import com.soshiant.server.facade.Facade;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.model.user.UserInfo;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public interface UserFacade extends Facade {

    public enum DataKeys {
    }

    FacadeResult getUser(String userName);

    FacadeResult getUserInfo(String userName);

    FacadeResult getUserInfo(int userId);

    FacadeResult getUserPermissions(int userId);

    FacadeResult saveUserInfo(UserInfo user);

    FacadeResult saveNewUserName(String currentUserName, String newUserName);

    FacadeResult resetUserPassword(String currentUserName);

    FacadeResult changePassword(String userName, String currentPassword,String newPassword);

}
