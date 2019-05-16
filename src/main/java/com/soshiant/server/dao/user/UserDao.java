package com.soshiant.server.dao.user;

import com.soshiant.server.model.user.SecureUser;
import com.soshiant.server.model.user.UserInfo;
import com.soshiant.server.model.user.UserPermissions;
import com.soshiant.server.model.user.UserPermissionsView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 4:31 PM
 */
public interface UserDao {

    public List<UserInfo> getUserInfoList();

    public UserInfo getUserInfo(String userName);

    public SecureUser getSecuredUserInfo(String userName);

    public UserInfo getUserInfo(int userId);

    public boolean saveNewUserName(String currentUserName, String newUserName);

    public boolean saveNewPassword(int passChangeDate, String userName, String newEncodedPassword);

    public boolean resetUserPassword(String userName);

    public void deleteUser(String userName);

    public boolean saveNewUser(UserInfo userInfo);

    public List<UserPermissionsView> getUserPermissions(int userId, int todayDate);
}
