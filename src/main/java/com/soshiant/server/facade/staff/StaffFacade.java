package com.soshiant.server.facade.staff;


import com.soshiant.server.facade.Facade;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.model.staff.Staff;
import com.soshiant.server.model.staff.StaffPosition;
import com.soshiant.server.model.staff.StaffShift;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

public interface StaffFacade extends Facade {

    public FacadeResult getStaffListByBranchId(short branchId);

    public FacadeResult getStaffInfo(int staffId);

    public FacadeResult getTeacherInfo(int teacherId);

    public FacadeResult getStaffList(String searchField, String searchString);

    public FacadeResult getDailyShiftList(int shiftDate,int staffId);

    public FacadeResult saveStaffInfo(Staff staff);

    public FacadeResult getStaffPositionListInfo(int staffId);

    public FacadeResult saveStaffPositionInfo(StaffPosition staffPosition);

    public FacadeResult toggleStaffPositionStatus(StaffPosition staffPosition);

    public FacadeResult saveStaffEmailAddress(int staffId, String newEmailAddress);

    public FacadeResult saveDailyShiftInfo(StaffShift staffShift);

    public enum DataKeys {
    }

}
