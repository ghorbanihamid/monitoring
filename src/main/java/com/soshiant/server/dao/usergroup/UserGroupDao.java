package com.soshiant.server.dao.usergroup;

import com.soshiant.server.dao.DaoException;
import com.soshiant.server.model.user.UserGroups;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * UserInfo: hamid
 * Date: 7/17/11
 * Time: 4:31 PM
 */
public interface UserGroupDao {

    public List<UserGroups> getUserGroupByUser(int userId) throws DaoException;

}
