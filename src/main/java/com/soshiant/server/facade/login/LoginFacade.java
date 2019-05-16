package com.soshiant.server.facade.login;


import com.soshiant.server.facade.Facade;
import com.soshiant.server.facade.FacadeResult;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public interface LoginFacade extends Facade {

    public enum DataKeys {
    }

    FacadeResult getUserInfo(String userName);

}
