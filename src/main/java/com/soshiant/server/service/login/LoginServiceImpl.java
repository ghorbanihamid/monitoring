package com.soshiant.server.service.login;

import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.dao.user.UserDao;
import com.soshiant.server.model.staff.Staff;
import com.soshiant.server.model.staff.StaffPosition;
import com.soshiant.server.model.user.UserInfo;
import com.soshiant.server.service.BusinessException;
import com.soshiant.server.service.log.AppLogService;
import com.soshiant.server.service.staff.StaffService;
import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

@Transactional
public class LoginServiceImpl implements LoginService {

    private static Logger logger = Logger.getLogger(LoginServiceImpl.class);

    private StaffService staffService;
    private UserDao userDao;
    private AppLogService appLogService;


    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setAppLogService(AppLogService appLogService) {
        this.appLogService = appLogService;
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
//======================================================================================================================
    public Authentication authenticateUser(Authentication authentication) throws Exception {

        UserDetails userDetails;
        boolean userIsEnabled;
        boolean userIsTeacher = false;

        try {
//            String userCaptchaResponse = getHttpServletRequest().getParameter(ServerConstants.OBJECT_CAPTCHA_RESPONSE);
//            if(userCaptchaResponse != null && StringUtils.isEmpty(userCaptchaResponse)){
//                throw new BusinessException(ServerConstants.ERROR_INVALID_CAPTCHA);
//            }
//            boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(getHttpServletRequest(), userCaptchaResponse);
//            if(!captchaPassed){
//                throw new BusinessException(ServerConstants.ERROR_INVALID_CAPTCHA);
//            }
//            String teacherStatus = getHttpServletRequest().getParameter(ServerConstants.OBJECT_LOGIN_AS_TEACHER);
//            if(teacherStatus.equals("true")){
//                userIsTeacher = true;
//            }
            String userName = (String) authentication.getPrincipal();
            String userPassword = (String) authentication.getCredentials();

            if(userName.trim().length() < ServerConstants.UserNameMinLength || userName.trim().length() > ServerConstants.UserNameMaxLength){

                throw new BadCredentialsException("Username is not in the range 8 and 20");
            }

            if(userPassword.trim().length() < ServerConstants.PasswordMinLength || userPassword.trim().length() > ServerConstants.PasswordMaxLength) {

                throw new BadCredentialsException("Password is not in the range 8 and 20");
            }

            UserInfo userInfo = userDao.getUserInfo(userName);
            if(userInfo == null){
                if (ServerConstants.isAppLogEnabled)
                    appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", "LoginService.authenticateUser()", ServerConstants.NoSqlText, "user not found", "", "UserName : " + userName);
                throw new Exception("");
            }
            if(!userInfo.isEnabled()){
                if (ServerConstants.isAppLogEnabled)
                    appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", "LoginService.authenticateUser()", ServerConstants.NoSqlText, "user is not active", "", "UserName : " + userName);
                throw new Exception();
            }

            userDetails =  new User(userInfo.getUsername(),
                    userInfo.getPassword().toLowerCase(),
                    userInfo.isEnabled(),
                    true,
                    true,
                    true,
                    getAuthorities(1) );

//            return userDetails;

            String encodedPassword = passwordEncoder.encodePassword(userPassword,null);
            if (!userInfo.getPassword().equalsIgnoreCase(encodedPassword)) {

                throw new BadCredentialsException("User Name or password is invalid.");

            }
            Staff staffInfo = staffService.getStaffInfo(userInfo.getUserId());
            if (staffInfo == null) {
                if (ServerConstants.isAppLogEnabled)
                    appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", "LoginService.authenticateUser()", ServerConstants.NoSqlText, "Staff not found", "", "UserName : " + userName + " StaffId : " + userInfo.getUserId());
                throw new BadCredentialsException("User Name or password is invalid.");
            }

            if(staffInfo.getStaffStatus() != ServerConstants.STAFF_STATUS_ACTIVE){

                if (ServerConstants.isAppLogEnabled)
                    appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", "LoginService.authenticateUser()", ServerConstants.NoSqlText, "Staff status is not active", "", "UserName : " + userName + " StaffId : " + userInfo.getUserId());
                throw new BadCredentialsException("User Name or password is invalid.");
            }

            List<StaffPosition> staffPositions = staffService.getStaffPositionListInfo(staffInfo.getStaffId());

            if (staffPositions == null) {
                if (ServerConstants.isAppLogEnabled)
                    appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", "LoginService.authenticateUser()", ServerConstants.NoSqlText, "Staff Positions not found", "", "UserName : " + userName + " StaffId : " + userInfo.getUserId());
                throw new BadCredentialsException("User Name or password is invalid.");
            }
            return authentication;

        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

    }

    public Collection<GrantedAuthority> getAuthorities(Integer access) {
        // Create a list of grants for this user
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

        // All users are granted with ROLE_USER access
        // Therefore this user gets a ROLE_USER by default
        logger.debug("Grant ROLE_USER to this user");
        authList.add(new GrantedAuthorityImpl("ROLE_USER"));

        // Check if this user has admin access
        // We interpret Integer(1) as an admin user
        if ( access.compareTo(1) == 0) {
            // User has admin access
            logger.debug("Grant ROLE_ADMIN to this user");
            authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
        }

        // Return list of granted authorities
        return authList;

    }


}