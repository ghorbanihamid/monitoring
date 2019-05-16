package com.soshiant.server.dao.group;

import com.soshiant.server.dao.DaoException;
import com.soshiant.server.model.group.Groups;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 4:31 PM
 */
public interface GroupDao {

    public List<Groups> findAll() throws DaoException;

}
