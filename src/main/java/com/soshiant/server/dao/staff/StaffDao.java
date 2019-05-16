package com.soshiant.server.dao.staff;

import com.soshiant.server.model.staff.Staff;
import com.soshiant.server.model.staff.StaffShift;
import com.soshiant.server.model.staff.StaffShiftView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 10/6/11
 * Time: 11:21 AM
 */

public interface StaffDao {

    public List<Staff> getStaffListByBranchId(short branchId);

    public List<Staff> getStaffListByNationalCode(long nationalCode);

    public List<Staff> getStaffListByIdNumber(long searchString);

    public List<Staff> getStaffListByEmailAddress(String emailAddress);

    public List<Staff> getStaffListByCellphone(String cellphone);

    public List<StaffShiftView> getDailyShiftList(int shiftDate,int staffId);

    public Staff getStaffInfo(int staffId);

    public void saveStaffInfo(Staff staff);

    public void saveDailyShiftInfo(StaffShift staffShift);

    public boolean saveStaffEmailAddress(int staffId, String newEmailAddress);

}
