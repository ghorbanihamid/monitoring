package com.soshiant.server.dao.staffPosition;

import com.soshiant.server.model.staff.StaffPosition;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 10/6/11
 * Time: 11:21 AM
 */

public interface StaffPositionDao {

    public List<StaffPosition> getStaffPositionListInfo(int staffId);

    public StaffPosition getStaffPositionInfo(int staffId, byte positionId);

    public void saveStaffPosition(StaffPosition staffPosition);

    public int activateStaff(int staffId);

    public int toggleStaffPositionStatus(StaffPosition staffPosition);

}
