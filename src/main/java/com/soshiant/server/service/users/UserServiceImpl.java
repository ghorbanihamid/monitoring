package com.soshiant.server.service.users;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.dao.monitoring.MonitoringDao;
import com.soshiant.server.dao.user.UserDao;
import com.soshiant.server.model.monitoring.MainFrame.RMF.ConfiguredMetricsView;
import com.soshiant.server.model.user.SecureUser;
import com.soshiant.server.model.user.UserInfo;
import com.soshiant.server.model.user.UserPermissions;
import com.soshiant.server.model.user.UserPermissionsView;
import com.soshiant.server.service.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: March 30, 2012
 * Time: 2:35:53 PM
 */

@Service("UserService")
public class UserServiceImpl implements UserService {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SaltSource saltSource;

    private UserDao userDao;

    private MonitoringDao monitoringDao;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setMonitoringDao(MonitoringDao monitoringDao) {
        this.monitoringDao = monitoringDao;
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public UserInfo getUserInfo(String userName) {

        return userDao.getUserInfo(userName);
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public UserInfo getUserInfo(int userId) {

        return userDao.getUserInfo(userId);
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public SecureUser getSecuredUserInfo(String userName){

        return userDao.getSecuredUserInfo(userName);
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public List<UserPermissionsView> getUserPermissions(int userId) {

        try {
            int todayDate = DateTimeUtil.getCurrentShamsiDate();
            return userDao.getUserPermissions(userId, todayDate);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //======================================================================================================================
    @Transactional(readOnly = false)
    public boolean saveNewUser(int userId, byte userType, String userName) {

        userName = StringUtils.defaultString(StringUtils.substring(userName, 0, ServerConstants.UserNameMaxLength));
        String saltValue = UUID.randomUUID().toString();
        saltValue = saltValue.replaceAll("-","");
        saltValue = StringUtils.defaultString(StringUtils.substring(saltValue,0,25));
        String encPassword = passwordEncoder.encodePassword(ServerConstants.DEFAULT_PASSWORD, saltValue);
        UserInfo tempUser = new UserInfo(userName,
                                         encPassword,
                                         saltValue,
                                         ServerConstants.USER_STATUS_ACTIVE,
                                         userId,
                                         userType
                                        );

        userDao.saveNewUser(tempUser);
        return true;
    }


    //======================================================================================================================
    @Transactional(readOnly = false)
    public boolean saveNewUserName(String currentUserName, String newUserName) {

        return userDao.saveNewUserName(currentUserName, newUserName);
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public boolean changePassword(String userName, String currentPassword, String newPassword) {

        SecureUser userInfo = userDao.getSecuredUserInfo(userName);
        if(userInfo == null){
            throw new BusinessException(ServerConstants.ERROR_DB_USER_NOT_FOUND);
        }

        String encodedCurrentPassword = passwordEncoder.encodePassword(currentPassword, userInfo.getSalt());
        if(!userInfo.getPassword().equals(encodedCurrentPassword)){
            throw new BusinessException(ServerConstants.ERROR_DB_USER_INVALID_PASSWORD);
        }

        String newEncodedPassword = passwordEncoder.encodePassword(newPassword, saltSource.getSalt(userInfo));
        return userDao.saveNewPassword(DateTimeUtil.getCurrentShamsiDate(),userName,newEncodedPassword);
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public boolean resetUserPassword(String userName) {

        return userDao.resetUserPassword(userName);
    }
//======================================================================================================================

}

