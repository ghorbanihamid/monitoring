package com.soshiant.server.action.staff.shift;

/*
 * Created by ghorbani on 2016/02/09.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.parameters.ParametersFacade;
import com.soshiant.server.facade.staff.StaffFacade;
import com.soshiant.server.model.staff.Staff;
import com.soshiant.server.model.staff.StaffShift;
import com.soshiant.server.model.staff.StaffShiftView;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.json.annotations.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DailyShiftAction extends ActionSupport implements SessionAware {

    Logger log = Logger.getLogger(this.getClass().getName());
    private String logMessage;
    private String actionName;

    private Map<String, Object> session;

    private List<StaffShiftView>    staffShiftList;


    private StaffFacade staffFacade;
    private AppLogFacade appLogFacade;

    Staff staffInfo = new Staff();

    StaffShift staffShift = new StaffShift();

    public void setStaffFacade(StaffFacade staffFacade) {
        this.staffFacade = staffFacade;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    //======================================================================================================================
    @SkipValidation
    public String showDailyShiftInputForm() throws Exception {

        this.actionName = "DailyShiftAction.showDailyShiftInputForm()";

        try {
            this.logMessage = this.actionName + " method : forwarded to input jsp";
            log.debug(logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.DAILY_SHIFT_LIST_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, ServerConstants.EmptyModel);
            return INPUT;
        } catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.DAILY_SHIFT_LIST_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }
    //======================================================================================================================
    @SkipValidation
    public String fetchStaffInfo() throws Exception {

        this.actionName = "DailyShiftAction.fetchStaffInfo()";

        try {
            if (staffShift.getStaffId() <= 0) {
                addActionError(getText("error.shift.dataRequired"));
                return ERROR;
            }

            FacadeResult facadeResult = staffFacade.getStaffInfo(staffShift.getStaffId());
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error fetchStaffInfo()";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.DAILY_SHIFT_LIST_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.staffShift.toString());
                addActionError(getText(facadeResult.getErrorKey()));
                return ERROR;
            }
            this.logMessage = this.actionName + " method : fetchStaffInfo()";
            log.debug(logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.DAILY_SHIFT_LIST_ACTION_PROCESS_CODE, "", "Student", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, ServerConstants.EmptyModel);
            return SUCCESS;
            
        } catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.DAILY_SHIFT_LIST_ACTION_PROCESS_CODE, "", "Student", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }
    //======================================================================================================================
    @SkipValidation
    public String fetchDailyShiftList() throws Exception {

        this.actionName = "DailyShiftAction.fetchDailyShiftList()";

        try {
            if (staffShift.getStaffId() <= 0 && staffShift.getShiftDate() <= 0) {
                addActionError(getText("error.shift.dataRequired"));
                return ERROR;
            }
            FacadeResult facadeResult = staffFacade.getDailyShiftList(staffShift.getShiftDate(),staffShift.getStaffId());
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error fetchDailyShiftList()";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.DAILY_SHIFT_LIST_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.staffShift.toString());
                addActionError(getText(facadeResult.getErrorKey()));
                return ERROR;
            }
            staffShiftList = (List<StaffShiftView>) facadeResult.getData();
            if (staffShiftList == null || staffShiftList.size() == 0) {
                this.logMessage = this.actionName + " : error fetchDailyShiftList() empty List " + staffShift.toString();
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.DAILY_SHIFT_LIST_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.staffShift.toString());
                addActionError(getText(facadeResult.getErrorKey()));
                return ERROR;
            }
            this.logMessage = this.actionName + " method : fetchDailyShiftList()";
            log.debug(logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.DAILY_SHIFT_LIST_ACTION_PROCESS_CODE, "", "Student", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, ServerConstants.EmptyModel);
            return SUCCESS;

        } catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.DAILY_SHIFT_LIST_ACTION_PROCESS_CODE, "", "Student", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }
    //======================================================================================================================
    public String saveDailyShiftInfo() throws Exception {

        this.actionName = "DailyClassDefinitionAction.saveDailyClassInfo()";

        try {
            FacadeResult facadeResult = staffFacade.saveDailyShiftInfo(staffShift);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error saving Class Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.DAILY_SHIFT_LIST_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.staffShift.toString());
                addActionError(getText(facadeResult.getErrorKey()));
                return ERROR;
            }
            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.DAILY_SHIFT_LIST_ACTION_PROCESS_CODE, "", "Student", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, this.staffShift.toString());

            return SUCCESS;
        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.DAILY_SHIFT_LIST_ACTION_PROCESS_CODE, "", "Student", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), this.staffShift.toString());
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return ERROR;
        }
    }

    public void validate() {

        if (staffShift.getStaffId() <= 0) {
            addActionError(getText("error.common.branchRequired"));
            return;
        }

        if (staffShift.getShiftDate() <= 0) {
            addActionError(getText("error.common.roomIdRequired"));
            return;
        }

        if (staffShift.getShiftStartTime() <= 0) {
            addActionError(getText("error.common.cycleLevelIdRequired"));
            return;
        }

        if (staffShift.getShiftEndTime() <= 0) {
            addActionError(getText("error.common.termRequired"));
            return;
        }
    }

    @JSON
    public List<StaffShiftView> getStaffShiftList() {
        return staffShiftList;
    }

    public Staff getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(Staff staffInfo) {
        this.staffInfo = staffInfo;
    }

    public StaffShift getStaffShift() {
        return staffShift;
    }

    public void setStaffShift(StaffShift staffShift) {
        this.staffShift = staffShift;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
