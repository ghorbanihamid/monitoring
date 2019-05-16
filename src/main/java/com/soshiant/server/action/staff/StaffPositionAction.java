package com.soshiant.server.action.staff;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.staff.StaffFacade;
import com.soshiant.server.model.staff.Staff;
import com.soshiant.server.model.staff.StaffPosition;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 11/17/11
 * Time: 6:14 PM
 */
public class StaffPositionAction extends ActionSupport{

    Logger log = Logger.getLogger(StaffPositionAction.class);

    private String actionName;
    private String logMessage = "";
    private String staffNameForStaffPosition;


    private List<StaffPosition> gridModel;
    private List<StaffPosition> staffPositionList;

    private byte            positionId;
    private int             staffId;

    private int             rows             = 0;          // get how many rows we want to have into the grid - rowNum attribute in the grid 
    private int             page             = 0;          // Get the requested page. By default grid sets this to 1.
    private int             totalPages       = 0;          // sorting order - asc or desc 
    private int             records          = 0;          // All Records 
    private String          sortOrder;
    private String          sidx;
    private String          searchField;
    private String          searchString;
    private String          searchOperation;         // the Search Operation ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc'] 
    private String          oper;
    private boolean         loadOnce         = false;

    private StaffFacade staffFacade;
    private AppLogFacade appLogFacade;


    public byte getPositionId() {
        return positionId;
    }

    public void setPositionId(byte positionId) {
        this.positionId = positionId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStaffNameForStaffPosition() {
        return staffNameForStaffPosition;
    }

    public List<StaffPosition> getStaffPositionList() {
        return staffPositionList;
    }

    public void setStaffPositionList(List<StaffPosition> staffPositionList) {
        this.staffPositionList = staffPositionList;
    }

    public void setStaffFacade(StaffFacade staffFacade) {
        this.staffFacade = staffFacade;
    }
    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

//======================================================================================================================
    @SkipValidation
    public String showStaffPositionInputForm() throws Exception {

        this.actionName = "staffPositionListAction.showStaffPositionInputForm";

        try {
            this.logMessage = this.actionName + " method : forwarded to input jsp";
            log.debug(logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, ServerConstants.EmptyModel);
            return INPUT;
        } catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }
//======================================================================================================================
    @SkipValidation
    public String fetchStaffInfo() throws Exception {

        this.actionName = "StaffPositionAction.getStaffInfo()";
        clearErrorsAndMessages();
        try {
            FacadeResult facadeResult = staffFacade.getStaffInfo(this.staffId);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getting Staff Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), ServerConstants.EmptyString);
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            Staff staff = (Staff) facadeResult.getData();
            if (staff == null) {
                this.logMessage = this.actionName + " : error, Staff not found, staffId : " + this.staffId;
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, ServerConstants.ERROR_DB_STAFF_ID_NOT_FOUND, 0, ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "", this.actionName, "", this.logMessage,"" , ServerConstants.EmptyString);
                addActionMessage(getText("error.common.staffIdNotFound"));
                return INPUT;
            }
            this.staffNameForStaffPosition = staff.getStaffName();
            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, "");

            return SUCCESS;
        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), "");
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return ERROR;
        }

    }

//======================================================================================================================
    public String saveStaffPositionInfo() throws Exception {

        this.actionName = "staffPositionListAction.saveStaffPositionInfo()";
        clearErrorsAndMessages();
        try {
            FacadeResult facadeResult = staffFacade.getStaffPositionListInfo(this.staffId);
            if(facadeResult.getErrorCode() != 0){
                this.logMessage = this.actionName + " : error getting Staff positions List";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "");
                addActionError(getText(facadeResult.getErrorKey()));
                return ERROR;
            }
            List<StaffPosition> tempStaffPositionList = (List<StaffPosition>) facadeResult.getData();
            if(tempStaffPositionList != null){
                for(int i = 0; i < tempStaffPositionList.size(); i++){
                    StaffPosition registeredStaffPosition = tempStaffPositionList.get(i);
                    if(registeredStaffPosition.getPositionId() == this.positionId){
                        this.logMessage = this.actionName + " : error, the PositionId already registered for this staff, staffId : " + this.staffId + ", positionId : " + this.positionId;
                        log.error(logMessage);
                        if (ServerConstants.isAppLogEnabled)
                            appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, ServerConstants.ERROR_DB_STAFF_ALREADY_HAS_POSITION, 0, ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "", this.actionName, "", this.logMessage, "", "");
                        addActionError(getText("error.common.duplicatePositionId"));
                        return ERROR;
                    }
                }
            }
            StaffPosition staffPosition = new StaffPosition(this.staffId,this.positionId);

            facadeResult = staffFacade.saveStaffPositionInfo(staffPosition);

            if(facadeResult.getErrorCode() != 0){
                this.logMessage = this.actionName + " : error saving Staff Position Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "");
                addActionError(getText(facadeResult.getErrorKey()));
                return ERROR;
            }
            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "StaffPosition", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, "");
            return SUCCESS;

        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "StaffPosition", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), "");
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return ERROR;
        }
    }
//======================================================================================================================
    @SkipValidation
    public String editStaffPosition() throws Exception {

        this.actionName = "staffPositionListAction.editStaffPosition()";
        clearErrorsAndMessages();
        try {
            StaffPosition staffPosition = new StaffPosition(this.staffId,this.positionId);

            FacadeResult facadeResult = staffFacade.toggleStaffPositionStatus(staffPosition);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error toggling Staff Position Status, StaffId : " + this.staffId + ", PositionId : " + this.positionId;
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "");
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, "");
            return SUCCESS;

        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "StaffSearch", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), "");
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return ERROR;
        }
    }
//======================================================================================================================
    @SkipValidation
    public String fetchStaffPositionListInfo() throws Exception {

        this.actionName = "staffPositionListAction.getStaffPositionListInfo()";
        clearErrorsAndMessages();
        try {

            FacadeResult facadeResult = staffFacade.getStaffPositionListInfo(this.staffId);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getting Staff positions List, staffId : " + this.staffId;
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "");
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            this.staffPositionList = (List<StaffPosition>) facadeResult.getData();

            if(this.staffPositionList == null || this.staffPositionList.isEmpty()) {

                this.logMessage = this.actionName + " : Staff positions List is empty";
                log.info(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, "", "");
                addActionMessage(getText("error.common.requestedInformationNotAvailable"));
                return INPUT;
            }
            this.loadOnce = true;

            if (!CommonUtil.isNullOrEmpty(this.searchField) && !CommonUtil.isNullOrEmpty(this.searchString) && !CommonUtil.isNullOrEmpty(this.searchOperation)){

                log.debug("Search in : " + searchField + ", value : " + searchString + ", operation : " + searchOperation);
            }
            else{
                if(this.gridModel != null && !this.gridModel.isEmpty())
                    this.gridModel.clear();
                this.gridModel = staffPositionList;
            }
            this.records =  this.gridModel.size();

            //calculate the total pages for the query     
            this.totalPages =(int) Math.ceil((double)this.records / (double)this.rows);

            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, "");
            return SUCCESS;

        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_POSITION_ACTION_PROCESS_CODE, "", "StaffSearch", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), "");
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return INPUT;
        }
    }

    /**
    * @return how many rows we want to have into the grid
    */
    public Integer getRows(){

        return rows;
    }

    public void setRows(Integer rows){

        this.rows = rows;
    }

    /**
    * @return current page of the query
    */
    public Integer getPage(){

        return page;
    }

    public void setPage(Integer page){

        this.page = page;
    }

    /**
    * @return total pages for the query
    */
    public Integer getTotal() {

        return totalPages;
    }

    public void setTotal(Integer totalPages){

        this.totalPages = totalPages;
    }

      /**
       * @return total number of records for the query. e.g. select count(*) from table
       */
    public Integer getRecords(){

        return records;
    }

    public void setRecords(Integer records){


        this.records = records;

        if (this.records > 0 && this.rows > 0){

            this.totalPages = (int) Math.ceil((double) this.records / (double) this.rows);
        }
        else{

            this.totalPages = 0;
        }
    }

    /**
    * @return sorting order
    */
    public String getSord(){

        return sortOrder;
    }

    public void setSord(String sortOrder){

        this.sortOrder = sortOrder;
    }

    /**
    * @return get index row - i.e. user click to sort.
    */
    public String getSidx(){

        return sidx;
    }

    public void setSidx(String sidx){

        this.sidx = sidx;
    }

    public void setSearchField(String searchField){

        this.searchField = searchField;
    }

    public void setSearchString(String searchString){

        this.searchString = searchString;
    }

    public void setSearchOper(String searchOper){

        this.searchOperation = searchOper;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public void setLoadOnce(boolean loadOnce){

        this.loadOnce = loadOnce;
    }

    public List<StaffPosition> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<StaffPosition> gridModel) {
        this.gridModel = gridModel;
    }
}
