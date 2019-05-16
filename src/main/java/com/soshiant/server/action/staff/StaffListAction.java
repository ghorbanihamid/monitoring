package com.soshiant.server.action.staff;

import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.staff.StaffFacade;
import com.soshiant.server.model.staff.Staff;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 11/17/11
 * Time: 6:14 PM
 */
public class StaffListAction extends ActionSupport implements SessionAware {

    Logger log = Logger.getLogger(StaffListAction.class);

    private String actionName;
    private String logMessage = "";


    private List<Staff>          gridModel;
    private List<Staff>          staffList;
    private Integer              staffId;
    private Integer              rows             = 0;          // get how many rows we want to have into the grid - rowNum attribute in the grid 
    private Integer              page             = 0;          // Get the requested page. By default grid sets this to 1.
    private Integer              totalPages       = 0;          // sorting order - asc or desc 
    private Integer              allRecordsCount  = 0;          // All Records 
    private String               sortOrder;
    private String               sidx;
    private String               searchField;
    private String               searchString;
    private String               searchOperation;         // the Search Operation ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc'] 
    private boolean              loadOnce         = false;
    private Map<String, Object>  session;


    private StaffFacade staffFacade;
    private AppLogFacade appLogFacade;


    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public void setStaffFacade(StaffFacade staffFacade) {
        this.staffFacade = staffFacade;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
    //======================================================================================================================
    @SkipValidation
    public String showStaffListInputForm() throws Exception {

        this.actionName = "StaffListAction.showStaffListInputForm()";

        try {
            this.logMessage = this.actionName + " method : forwarded to input jsp";
            log.debug(logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, ServerConstants.EmptyModel);
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
    public String fetchStaffList() throws Exception {

        this.actionName = "StaffListAction.fetchStaffList()";
        clearErrorsAndMessages();
        try {

            if(!validateStaffFetch()){
                return ERROR;
            }
//            Object list = session.get("seminarReservationListSession");
//            if (list != null) {
//                this.seminarReservationList = (List<SeminarReservation>) list;
//            }
//            else
//            {

            FacadeResult facadeResult = staffFacade.getStaffList(this.searchField,this.searchString);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getting Staff List";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "");
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            this.staffList = (List<Staff>) facadeResult.getData();

            if(this.staffList == null || this.staffList.isEmpty()) {

                this.logMessage = this.actionName + " : Staff List is empty";
                log.info(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, "", "");
                addActionMessage(getText("error.common.requestedInformationNotAvailable"));
                return INPUT;
            }

            if(this.gridModel != null && !this.gridModel.isEmpty())
                this.gridModel.clear();
            this.gridModel = staffList;

            if(!CommonUtil.isNullOrEmpty(this.sortOrder)){

                if (this.sortOrder.equalsIgnoreCase("asc")){

    //                Collections.sort(this.gridModel);
                }
                if (this.sortOrder != null && this.sortOrder.equalsIgnoreCase("desc")){

    //                Collections.sort(this.gridModel);
//                    Collections.reverse(this.gridModel);
                }
            }

            this.allRecordsCount =  this.gridModel.size();
            int toRecord = (this.rows * this.page);
            int fromRecord = toRecord - this.rows;
            if (toRecord > this.allRecordsCount)
                toRecord = this.allRecordsCount;

            //calculate the total pages for the query     
            this.totalPages =(int) Math.ceil((double)this.allRecordsCount / (double)this.rows);

            // for loading one time and using data again without fetching from DB
//            session.put("seminarReservationListSession", seminarReservationList);

            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, "");
            return SUCCESS;

        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "StaffSearch", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), "");
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return INPUT;
        }
    }
//======================================================================================================================
    private boolean validateStaffFetch(){

        if(StringUtils.isEmpty(this.searchField)){
            addActionError(getText("error.common.searchFieldRequired"));
            return false;
        }
        else {
            if(CommonUtil.hasSpecialCharacter(this.searchField)){
                this.searchField = "";
                addActionError(getText("error.common.invalidSearchField"));
                return false;
            }
        }

        if(StringUtils.isEmpty(this.searchString)){
            addActionError(getText("error.common.dataRequired"));
            return false;
        }
        else {
            if(CommonUtil.hasSpecialCharacter(this.searchString)){
                this.searchField = "";
                addActionError(getText("error.common.invalidData"));
                return false;
            }
        }

        return true;
    }
//======================================================================================================================
    public void searchStaff(){

        int findItemsCount = 0;
        if(this.gridModel == null)
            gridModel = new ArrayList();
        else if(!this.gridModel.isEmpty())
            this.gridModel.clear();
        if(this.searchField.equals("staffId")){

            for(int i = 0; i < this.staffList.size();i++){
                Staff tempStaff = this.staffList.get(i);
                if(tempStaff.getStaffId() ==  Integer.parseInt(this.searchString)){
                    this.gridModel.add(findItemsCount,tempStaff);
                    findItemsCount++;
                }
            }
        }
        else if(this.searchField.equals("staffEnFullName")){

            for(int i = 0; i < this.staffList.size();i++){
                Staff tempStaff = this.staffList.get(i);

                if(this.searchOperation.equalsIgnoreCase("eq")){      // eq : equal
                    if(tempStaff.getStaffEnFullName().equalsIgnoreCase(this.searchString)){
                        this.gridModel.add(findItemsCount,tempStaff);
                        findItemsCount++;
                    }
                }
                else if(this.searchOperation.equalsIgnoreCase("cn")){  // cn : contains
                    if(tempStaff.getStaffEnFullName().toLowerCase().contains(this.searchString.toLowerCase())){
                        this.gridModel.add(findItemsCount,tempStaff);
                        findItemsCount++;
                    }
                }
            }
        }
        else if(this.searchField.equals("staffOlFullName")){

            for(int i = 0; i < this.staffList.size();i++){
                Staff tempStaff = this.staffList.get(i);
                if(this.searchOperation.equalsIgnoreCase("eq")){      // eq : equal
                    if(tempStaff.getStaffOlFullName().equalsIgnoreCase(this.searchString)){
                        this.gridModel.add(findItemsCount,tempStaff);
                        findItemsCount++;
                    }
                }
                else if(this.searchOperation.equalsIgnoreCase("cn")){      // cn : contains
                    if(tempStaff.getStaffOlFullName().contains(this.searchString)){
                        this.gridModel.add(findItemsCount,tempStaff);
                        findItemsCount++;
                    }
                }
            }
        }
        else if(this.searchField.equals("cellPhone")){

            for(int i = 0; i < this.staffList.size();i++){
                Staff tempStaff = this.staffList.get(i);
                if(this.searchOperation.equalsIgnoreCase("eq")){      // eq : equal
                    if(tempStaff.getCellPhone().equalsIgnoreCase(this.searchString)){
                        this.gridModel.add(findItemsCount,tempStaff);
                        findItemsCount++;
                    }
                }
                else if(this.searchOperation.equalsIgnoreCase("cn")){      // cn : contains
                    if(tempStaff.getCellPhone().toLowerCase().contains(this.searchString.toLowerCase())){
                        this.gridModel.add(findItemsCount,tempStaff);
                        findItemsCount++;
                    }
                }
            }
        }
        else if(this.searchField.equals("homePhone")){

            for(int i = 0; i < this.staffList.size();i++){
                Staff tempStaff = this.staffList.get(i);
                if(this.searchOperation.equalsIgnoreCase("eq")){      // eq : equal
                    if(tempStaff.getHomePhone().equalsIgnoreCase(this.searchString)){
                        this.gridModel.add(findItemsCount,tempStaff);
                        findItemsCount++;
                    }
                }
                else if(this.searchOperation.equalsIgnoreCase("cn")){  // cn : contains
                    if(tempStaff.getHomePhone().toLowerCase().contains(this.searchString.toLowerCase())){
                        this.gridModel.add(findItemsCount,tempStaff);
                        findItemsCount++;
                    }
                }
            }
        }
        else if(this.searchField.equals("emailAddress")){

            for(int i = 0; i < this.staffList.size();i++){
                Staff tempStaff = this.staffList.get(i);
                if(this.searchOperation.equalsIgnoreCase("eq")){      // eq : equal
                    if(tempStaff.getEmailAddress().equalsIgnoreCase(this.searchString)){
                        this.gridModel.add(findItemsCount,tempStaff);
                        findItemsCount++;
                    }
                }
            }
        }
    }

//======================================================================================================================
    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
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

        return allRecordsCount;
    }

    public void setRecords(Integer records){

        this.allRecordsCount = records;
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
        return searchOperation;
    }

    public void setOper(String searchOperation) {
        this.searchOperation = searchOperation;
    }

    public void setLoadOnce(boolean loadOnce){

        this.loadOnce = loadOnce;
    }

    public List<Staff> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<Staff> gridModel) {
        this.gridModel = gridModel;
    }
}
