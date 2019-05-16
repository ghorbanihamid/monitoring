package com.soshiant.server.facade.group;

import com.soshiant.server.dao.group.GroupDao;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.SimpleFacadeResult;
import com.soshiant.server.facade.user.UserFacade;
import com.soshiant.server.model.group.Groups;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * UserInfo: Mahta
 * Date: 10/6/11
 * Time: 11:19 AM
 */

public class GroupFacadeImpl implements GroupFacade {

    private GroupDao groupDao;

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Transactional(readOnly = true)
    public FacadeResult findAll() {
        List<Groups> groups = groupDao.findAll();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(groups);
        return (simpleFacadeResult.success());
    }

    //@Override
    public Class getKeysEnumClass() {
        return UserFacade.DataKeys.class;
    }

}