package com.soshiant.common.interceptor;


/*
 * Simple interface for actions that want have the user object injected.  
 */

import com.soshiant.server.model.user.UserInfo;

public interface UserAware {

    public void setUser(UserInfo user);

}