package com.soshiant.server.facade.login;

import com.soshiant.server.dao.user.UserDao;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.SimpleFacadeResult;
import com.soshiant.server.model.user.UserInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class LoginFacadeImpl implements LoginFacade {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    //===================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getUserInfo(String userName) {

        UserInfo userInfo = this.userDao.getUserInfo(userName);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(userInfo);
        return simpleFacadeResult.success();
    }

    //===================================================================================

    //@Override
    public Class getKeysEnumClass() {
        return DataKeys.class;
    }
}
