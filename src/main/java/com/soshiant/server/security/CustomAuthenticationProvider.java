package com.soshiant.server.security;

import com.soshiant.server.service.login.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    Logger log = Logger.getLogger(this.getClass().getName());

    LoginService loginService;


    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //======================================================================================================================
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Authentication authenticationResult;
        String userName = (String) authentication.getPrincipal();
        try{
            authenticationResult = loginService.authenticateUser(authentication);
//            authenticationResult.setAuthenticated(true);
            return authenticationResult;

        } catch (Exception exception) {
            exception.printStackTrace();
            if (exception instanceof BadCredentialsException) {
                log.error("login failed for user:" + userName);
                throw new BadCredentialsException("Dear User, through this moment system couldn't respond(1).");
            } else {
                log.error("System couldn't respond to user user:" + userName + " error message : " + exception.getMessage());
                throw new BadCredentialsException("Dear User, through this moment system couldn't respond(1).");
            }
        }

    }
//======================================================================================================================
    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
