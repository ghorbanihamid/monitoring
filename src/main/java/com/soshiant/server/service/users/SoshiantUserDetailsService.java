package com.soshiant.server.service.users;

import com.soshiant.common.util.BundleUtil;
import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.dao.user.UserDao;
import com.soshiant.server.model.user.SecureUser;
import com.soshiant.server.service.BusinessException;
import com.soshiant.server.service.log.AppLogService;
import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: March 30, 2012
 * Time: 2:35:53 PM
 */

@Service("soshiantUserService")
public class SoshiantUserDetailsService implements UserDetailsService {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SaltSource saltSource;

    private UserService userService;
    private UserDao userDao;
    private AppLogService appLogService;

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {

        Locale locale = new Locale("en", "US");
        BundleUtil bundle = BundleUtil.getInstance(locale);
        try{
            String userCaptchaResponse = getHttpServletRequest().getParameter(ServerConstants.OBJECT_CAPTCHA_RESPONSE);
            if(userCaptchaResponse != null && StringUtils.isEmpty(userCaptchaResponse)){
                throw new BadCredentialsException(bundle.getMessage("errors.login.invalidCaptcha"));
            }
            boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(getHttpServletRequest(), userCaptchaResponse);
            if(!captchaPassed){
                throw new BadCredentialsException(bundle.getMessage("errors.login.invalidCaptcha"));
            }

            if(userName.trim().length() < ServerConstants.UserNameMinLength || userName.trim().length() > ServerConstants.UserNameMaxLength){

                throw new BadCredentialsException(bundle.getMessage("errors.login.invalidUserNameRange"));
            }

            if(CommonUtil.hasSpecialCharacter(userName.trim())){

                throw new BadCredentialsException(bundle.getMessage("errors.login.invalidUserNameOrPassword"));
            }

            SecureUser userInfo = userService.getSecuredUserInfo(userName);
            if(userInfo == null){
                throw new BadCredentialsException(bundle.getMessage("errors.login.invalidUserNameOrPassword"));
            }
            return userInfo;

        } catch (Exception exception) {
            logger.error(exception.getMessage());
            exception.printStackTrace();
            appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "", "KliUserDetailsService.loadUserByUsername()", ServerConstants.NoSqlText, "",exception.getMessage(), "UserName : " + userName);
            if (exception instanceof BadCredentialsException) {
                throw new BadCredentialsException(exception.getMessage());
            } else {
                throw new BadCredentialsException(bundle.getMessage("error.common.systemCouldNotRespond1"));
            }
        }

    }
}

