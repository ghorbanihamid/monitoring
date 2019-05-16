package com.soshiant.server.facade.staff;

import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.SimpleFacadeResult;
import com.soshiant.server.model.staff.Staff;
import com.soshiant.server.model.staff.StaffPosition;
import com.soshiant.server.model.staff.StaffShift;
import com.soshiant.server.model.staff.StaffShiftView;
import com.soshiant.server.service.parameters.ParametersService;
import com.soshiant.server.service.staff.StaffService;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

public class StaffFacadeImpl implements StaffFacade {

    private static Logger logger = Logger.getLogger(StaffFacadeImpl.class);

    private StaffService staffService;
    private ParametersService parametersService;

    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    public void setParametersService(ParametersService parametersService) {
        this.parametersService = parametersService;
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getStaffListByBranchId(short branchId) {

        List<Staff> staffs = staffService.getStaffListByBranchId(branchId);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(staffs);
        return (simpleFacadeResult.success());
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getStaffInfo(int staffId) {

        Staff staffInfo = staffService.getStaffInfo(staffId);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(staffInfo);
        return (simpleFacadeResult.success());
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getTeacherInfo(int teacherId) {

        Staff teacherInfo = staffService.getStaffInfo(teacherId);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(teacherInfo);
        return (simpleFacadeResult.success());
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getStaffList(String searchField, String searchString){

        List<Staff> staffs = staffService.getStaffList(searchField,searchString);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(staffs);
        return (simpleFacadeResult.success());
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getDailyShiftList(int shiftDate,int staffId){

        List<StaffShiftView> staffShiftList = staffService.getDailyShiftList(shiftDate, staffId);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(staffShiftList);
        return (simpleFacadeResult.success());
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult saveStaffInfo(Staff staff) {

        int staffId = staffService.saveStaffInfo(staff);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(staffId);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult saveDailyShiftInfo(StaffShift staffShift) {

        staffService.saveDailyShiftInfo(staffShift);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(true);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getStaffPositionListInfo(int staffId) {

        List<StaffPosition> staffPositionsList = staffService.getStaffPositionListInfo(staffId);
        for(int i = 0; i < staffPositionsList.size(); i++ ){
            staffPositionsList.set(i,setStaffPositionDesc(staffPositionsList.get(i)));
        }
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(staffPositionsList);
        return (simpleFacadeResult.success());
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult saveStaffPositionInfo(StaffPosition staffPosition){

        staffService.saveStaffPositionInfo(staffPosition);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(staffPosition);
        return simpleFacadeResult.success();
    }

    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult toggleStaffPositionStatus(StaffPosition staffPosition){

        boolean methodStatus = staffService.toggleStaffPositionStatus(staffPosition);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(methodStatus);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult saveStaffEmailAddress(int staffId, String newEmailAddress) {

        staffService.saveStaffEmailAddress(staffId,newEmailAddress);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult();
        return simpleFacadeResult.success();
    }
//======================================================================================================================
    public StaffPosition setStaffPositionDesc(StaffPosition staffPosition){

        String tempValue = parametersService.getPositionName(staffPosition.getPositionId());
        staffPosition.setPositionName(tempValue);

        return staffPosition;
    }
//======================================================================================================================
    //@Override
    public Class getKeysEnumClass() {
        return DataKeys.class;
    }


}