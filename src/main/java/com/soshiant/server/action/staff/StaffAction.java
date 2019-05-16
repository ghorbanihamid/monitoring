package com.soshiant.server.action.staff;

import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.staff.StaffFacade;
import com.soshiant.server.model.staff.Staff;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Created by IntelliJ IDEA.
 * User: ghorbani
 * Date: 9/18/11
 * Time: 12:01 PM
 */
public class StaffAction extends ActionSupport implements ModelDriven {
    Logger log = Logger.getLogger(StaffAction.class);

    private String actionName;
    private String logMessage = "";

    private Staff staff = new Staff();

    private StaffFacade staffFacade;
    private AppLogFacade appLogFacade;


    public void setStaffFacade(StaffFacade staffFacade) {
        this.staffFacade = staffFacade;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    //======================================================================================================================
    public Object getModel() {
        return staff;
    }
    //======================================================================================================================
    @SkipValidation
    public String showStaffInputForm() throws Exception {

        this.actionName = "StaffAction.showStaffInputForm()";

        try {
            this.logMessage = this.actionName + " method : forwarded to input jsp";
            log.debug(logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, ServerConstants.EmptyModel);
            return INPUT;
        } catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }
    //======================================================================================================================
    @SkipValidation
    public String fetchStaffInfo() throws Exception {

        this.actionName = "StaffAction.getStaffInfo()";
        clearErrorsAndMessages();
        try {
            FacadeResult facadeResult = staffFacade.getStaffInfo(staff.getStaffId());
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getting Staff Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.staff.toString());
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            this.staff = (Staff) facadeResult.getData();
            if(this.staff == null){
                this.logMessage = this.actionName + " : error Staff not found";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.staff.toString());
                addActionError(getText("error.common.staffIdNotFound"));
                return INPUT;
            }

            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, this.staff.toString());
            return SUCCESS;
        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), this.staff.toString());
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return INPUT;
        }
    }
    //======================================================================================================================
    public String saveStaffInfo() throws Exception {

        this.actionName = "StaffAction.saveStaffInfo()";
        clearErrorsAndMessages();
        FacadeResult facadeResult;
        try {

            facadeResult = staffFacade.saveStaffInfo(staff);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error saving Staff Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.staff.toString());
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, this.staff.toString());
            return SUCCESS;
        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), this.staff.toString());
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return INPUT;
        }
    }
    //======================================================================================================================
    public void validate() {

        clearErrorsAndMessages();

        if (StringUtils.isEmpty(staff.getStaffOlName()))
            addActionError(getText("error.common.olNameRequired"));
        else if (!CommonUtil.isPersianText(staff.getStaffOlName()))
            addActionError(getText("error.common.invalidOlName"));

        if (StringUtils.isEmpty(staff.getStaffOlFamily()))
            addActionError(getText("error.common.olFamilyRequired"));
        else if (!CommonUtil.isPersianText(staff.getStaffOlFamily()))
            addActionError(getText("error.common.invalidOlFamily"));

        if (StringUtils.isEmpty(staff.getStaffEnName()))
            addActionError(getText("error.common.enNameRequired"));
        else if (!CommonUtil.isEnglishText(staff.getStaffEnName()))
            addActionError(getText("error.common.invalidEnName"));

        if (StringUtils.isEmpty(staff.getStaffEnFamily()))
            addActionError(getText("error.common.enFamilyRequired"));
        else if (!CommonUtil.isEnglishText(staff.getStaffEnFamily()))
            addActionError(getText("error.common.invalidEnFamily"));

        if (StringUtils.isEmpty(staff.getHomePhone()))
            addActionError(getText("error.common.homePhoneRequired"));
        else if (!StringUtils.isNumeric(staff.getHomePhone()))
            addActionError(getText("error.common.invalidHomePhone"));

        if (StringUtils.isEmpty(staff.getCellPhone()) )
            addActionError(getText("error.common.cellPhoneRequired"));
        else if (!StringUtils.isNumeric(staff.getHomePhone()))
            addActionError(getText("error.common.invalidCellPhone"));

        if (!StringUtils.isEmpty(staff.getWorkPhone())) {

            if (!StringUtils.isNumeric(staff.getWorkPhone()) )
                addActionError(getText("error.common.invalidStudyField"));
        }

        if (StringUtils.isEmpty(staff.getEmailAddress()))
            addActionError(getText("error.common.emailAddressRequired"));
        else if (!CommonUtil.isValidEmailAddress(staff.getEmailAddress()))
            addActionError(getText("error.common.invalidEmailAddress"));


    }

}
