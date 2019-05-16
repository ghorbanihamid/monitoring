package com.soshiant.server.service.staff;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.common.util.ParametersUtil;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.dao.staff.StaffDao;
import com.soshiant.server.dao.staffPosition.StaffPositionDao;
import com.soshiant.server.model.parameters.SendSmsInfo;
import com.soshiant.server.model.staff.Staff;
import com.soshiant.server.model.staff.StaffPosition;
import com.soshiant.server.model.staff.StaffShift;
import com.soshiant.server.model.staff.StaffShiftView;
import com.soshiant.server.model.user.UserInfo;
import com.soshiant.server.service.BusinessException;
import com.soshiant.server.service.parameters.ParametersService;
import com.soshiant.server.service.parameters.SmsService;
import com.soshiant.server.service.users.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

@Transactional
public class StaffServiceImpl implements StaffService {

    private static Logger logger = Logger.getLogger(StaffServiceImpl.class);

    private StaffDao staffDao;
    private StaffPositionDao staffPositionDao;
    private ParametersService parametersService;
    private UserService userService;
    private SmsService smsService;

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    public void setStaffPositionDao(StaffPositionDao staffPositionDao) {
        this.staffPositionDao = staffPositionDao;
    }

    public void setParametersService(ParametersService parametersService) {
        this.parametersService = parametersService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }

    //======================================================================================================================
    public List getStaffListByBranchId(short branchId) {

        List<Staff> staffs = staffDao.getStaffListByBranchId(branchId);
        for(int i = 0; i < staffs.size(); i++ ){
            staffs.set(i,parametersService.setStaffDescription(staffs.get(i)));
        }
        return staffs;
    }

    //======================================================================================================================
    public Staff getStaffInfo(int staffId) {

        Staff staffInfo = parametersService.setStaffDescription(staffDao.getStaffInfo(staffId));
        return staffInfo;
    }

    //======================================================================================================================
    public List<Staff> getStaffList(String searchField, String searchString){

        List<Staff> staffList = null;

        switch (searchField){
            case ServerConstants.NATIONAL_CODE : staffList = staffDao.getStaffListByNationalCode(Long.parseLong(searchString));
                                             break;
            case ServerConstants.ID_NUMBER : staffList = staffDao.getStaffListByIdNumber(Long.parseLong(searchString));
                                             break;
            case ServerConstants.CELL_PHONE : staffList = staffDao.getStaffListByCellphone(searchString);
                                             break;
            case ServerConstants.EMAIL_ADDRESS : staffList = staffDao.getStaffListByEmailAddress(searchString);
                                             break;
        }

        if(staffList == null || staffList.size() == 0){
            throw new BusinessException(ServerConstants.ERROR_DB_INFORMATION_NOT_FOUND);
        }

        for(int i = 0; i < staffList.size(); i++ ){
            staffList.set(i,setStaffDescription(staffList.get(i)));
        }
        return staffList;
    }
    //======================================================================================================================
    public int saveStaffInfo(Staff staff) {

        if(!staff.isEditMode()) {

            staff.setStaffId(staff.getNewStaffId());

            List<Staff> tempStaffInfo;

            tempStaffInfo = staffDao.getStaffListByEmailAddress(staff.getEmailAddress());
            if(tempStaffInfo != null  && tempStaffInfo.size() > 0){
                throw new BusinessException(ServerConstants.ERROR_DB_DUPLICATE_EMAIL_ADDRESS);
            }

            tempStaffInfo = staffDao.getStaffListByEmailAddress(staff.getCellPhone());
            if(tempStaffInfo != null  && tempStaffInfo.size() > 0){
                throw new BusinessException(ServerConstants.ERROR_DB_DUPLICATE_CELL_PHONE);
            }

            String staffCounter =  parametersService.makeNewStaffCounter();
            if(staffCounter == null){
                throw new BusinessException(ServerConstants.ERROR_DB_CANNOT_FETCH_SYSTEM_PARAMETERS);
            }
            int staffId = Integer.parseInt(String.valueOf(DateTimeUtil.getCurrentShamsiShortYear()) +
//                                           ServerConstants.USER_TYPE_STAFF +
                                           staffCounter);
            if(staffId == 0){
//                throw new BusinessException(ServerConstants.ERROR_DB_CANNOT_FETCH_SYSTEM_PARAMETERS);
                return ServerConstants.ERROR_DB_CANNOT_FETCH_SYSTEM_PARAMETERS;
            }
            staff.setStaffId(staffId);

        }
        staffDao.saveStaffInfo(staff);

        return staff.getStaffId();
    }
    //======================================================================================================================
    public List<StaffShiftView> getDailyShiftList(int shiftDate,int staffId){

        List<StaffShiftView> staffList = staffDao.getDailyShiftList(shiftDate,staffId);
        return staffList;
    }
    //======================================================================================================================
    public void saveDailyShiftInfo(StaffShift staffShift) {

        staffDao.saveDailyShiftInfo(staffShift);


    }
//======================================================================================================================
    public List<StaffPosition> getStaffPositionListInfo(int staffId) {

        List<StaffPosition> staffPositions = staffPositionDao.getStaffPositionListInfo(staffId);
        return staffPositions;
    }
    //======================================================================================================================
    public String saveStaffPositionInfo(StaffPosition staffPosition) {

        String userName = "";
        String userNameSuffix = "2";
        Staff staffInfo = staffDao.getStaffInfo(staffPosition.getStaffId());
        if(staffInfo == null){
            throw new BusinessException(ServerConstants.ERROR_DB_STAFF_ID_NOT_FOUND);
        }
        else {
            staffPositionDao.activateStaff(staffPosition.getStaffId());
        }

        boolean needNewUser = false;
        UserInfo userInfo = userService.getUserInfo(staffInfo.getStaffId());
        if(userInfo == null){
            needNewUser = true;
        }
        staffPositionDao.saveStaffPosition(staffPosition);
        if(needNewUser) {

            userName = staffInfo.getStaffEnFamily() + "_" + staffInfo.getStaffEnName();// + userNameSuffix;
            userService.saveNewUser(staffPosition.getStaffId(),staffPosition.getPositionId(), userName);

            String messageText = ServerConstants.SMS_DEAR_NEW_USER_TEXT + ServerConstants.SMS_NEW_LINE +
                                 ServerConstants.SMS_USER_NAME_TEXT + userName + ServerConstants.SMS_NEW_LINE +
                                 ServerConstants.SMS_PASSWORD_TEXT + ServerConstants.DEFAULT_PASSWORD + ServerConstants.SMS_NEW_LINE +
                                 ServerConstants.SMS_INSTITUTE_NAME;

            staffPosition.setUserName(userName);
            SendSmsInfo smsInfo = new SendSmsInfo(DateTimeUtil.getCurrentShamsiDate(),
                                                  staffInfo.getCellPhone(),
                                                  StringUtils.defaultString(StringUtils.substring(messageText, 0, ServerConstants.SMS_MESSAGE_TEST_MAX_LENGTH)));
            smsService.saveSmsInfo(smsInfo);
        }
        return userName;
    }
    //======================================================================================================================
    public boolean toggleStaffPositionStatus(StaffPosition staffPosition) {

        StaffPosition staffPositionInfo =  staffPositionDao.getStaffPositionInfo(staffPosition.getStaffId(),staffPosition.getPositionId());
        if(staffPositionInfo == null){
            throw new BusinessException(ServerConstants.ERROR_DB_STAFF_DOES_NOT_HAVE_POSITION);
        }
        if(staffPositionInfo.getPositionStatus() == ServerConstants.STAFF_POSITION_STATUS_ACTIVE){
           staffPosition.setPositionStatus(ServerConstants.STAFF_POSITION_STATUS_DISABLE);
        }
        else {
           staffPosition.setPositionStatus(ServerConstants.STAFF_POSITION_STATUS_ACTIVE);
        }
        staffPosition.setToggleStatusDate(DateTimeUtil.getCurrentShamsiYear());
        staffPositionDao.toggleStaffPositionStatus(staffPosition);

        return true;
    }
    //======================================================================================================================
    public boolean saveStaffEmailAddress(int staffId, String newEmailAddress) {

        staffDao.saveStaffEmailAddress(staffId,newEmailAddress);

        return true;

    }

    //======================================================================================================================
    public Staff setStaffDescription(Staff staff){

        if(staff != null){

            staff.setGenderName(ParametersUtil.getGenderStatusName(staff.getGenderStatus()));

        }
        return staff;
    }
    //======================================================================================================================

}