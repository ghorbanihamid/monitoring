package com.soshiant.server.service.login;


import org.springframework.security.core.Authentication;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

public interface LoginService {

    public Authentication authenticateUser(Authentication authentication) throws Exception;

}
