package com.soshiant.server.service.users;


import com.soshiant.server.facade.Facade;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.model.user.SecureUser;
import com.soshiant.server.model.user.UserInfo;
import com.soshiant.server.model.user.UserPermissions;
import com.soshiant.server.model.user.UserPermissionsView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public interface UserService {

    public UserInfo getUserInfo(String userName);

    public UserInfo getUserInfo(int userId);

    public SecureUser getSecuredUserInfo(String userName);

    public List<UserPermissionsView> getUserPermissions(int userId);

    public boolean saveNewUser(int userId, byte userType, String userName);

    public boolean saveNewUserName(String currentUserName, String newUserName);

    public boolean changePassword(String userName, String currentPassword, String newPassword);

    public boolean resetUserPassword(String currentUserName);

}
