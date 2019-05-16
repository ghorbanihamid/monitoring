package com.soshiant.server.dao.position;

import com.soshiant.server.model.position.Positions;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * UserInfo: Mahta
 * Date: 10/6/11
 * Time: 11:21 AM
 */

public interface PositionDao {

    public List<Positions> findAll();

//    public List<Positions> findAllExceptTeacher();

    public Positions findPositionById(int roleId);

    public void savePosition(Positions position);

    public void updatePosition(Positions position);

}
