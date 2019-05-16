package com.soshiant.server.model.user;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: Sep 15, 2011
 * Time: 1:47:00 PM
 */
public class SecureUser extends User {

    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public SecureUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                      boolean accountNonLocked, Collection<GrantedAuthority> authorities, String salt) {

        super(username, password, enabled,accountNonExpired,
              credentialsNonExpired,accountNonLocked, authorities);
        this.salt = salt;
    }
}

