package com.soshiant.server.facade.user;

import com.soshiant.server.facade.FacadeResult;
import org.springframework.transaction.annotation.Transactional;

//import com.soshiant.server.model.user.SecureUser;
//import org.acegisecurity.GrantedAuthority;
//import org.acegisecurity.GrantedAuthorityImpl;
//import org.hibernate.Hibernate;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class GroupFacadeImpl implements GroupFacade {

//    private GroupDao groupDao;

//    public void setGroupDao(GroupDao groupDao) {
//        this.groupDao = groupDao;
//    }

    @Transactional(readOnly = true)
    public FacadeResult findAll() {
//        List<Groups> groupses = groupDao.findAll();
//        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(groupses);
        return null;
    }

    //@Override
    public Class getKeysEnumClass() {
        return UserFacade.DataKeys.class;
    }

}