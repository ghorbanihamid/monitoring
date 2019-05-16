package com.soshiant.server.service.staff;


import com.soshiant.server.model.staff.Staff;
import com.soshiant.server.model.staff.StaffPosition;
import com.soshiant.server.model.staff.StaffShift;
import com.soshiant.server.model.staff.StaffShiftView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

public interface StaffService {

    public List getStaffListByBranchId(short branchId);

    public Staff getStaffInfo(int staffId);

    public List<Staff> getStaffList(String searchField, String searchString);

    public List<StaffShiftView> getDailyShiftList(int shiftDate,int staffId);

    public int saveStaffInfo(Staff staff);

    public void saveDailyShiftInfo(StaffShift staffShift);

    public List<StaffPosition> getStaffPositionListInfo(int staffId);

    public String saveStaffPositionInfo(StaffPosition staffPosition);

    public boolean toggleStaffPositionStatus(StaffPosition staffPosition);

    public boolean saveStaffEmailAddress(int staffId, String newEmailAddress);

}
