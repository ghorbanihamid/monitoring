package com.soshiant.server.action.log;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.user.UserFacade;
import com.soshiant.server.model.log.AppLog;
import com.soshiant.server.model.user.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 11/17/11
 * Time: 6:14 PM
 */
public class AppLogListAction extends ActionSupport{

    Logger log = Logger.getLogger(AppLogListAction.class);

    private String actionName;
    private String logMessage = "";

    private List<AppLog>     gridModel;
    private List<AppLog>     appLogList;

    private String           userName;
    private String           userId;
    private String           logDate;


    private int              rows             = 0;          // get how many rows we want to have into the grid - rowNum attribute in the grid 
    private int              page             = 0;          // Get the requested page. By default grid sets this to 1.
    private int              totalPages       = 0;          // Your Total Pages
    private int              recordsCount     = 0;          // All Records 
    private String           sortOrder;                     // sorting order - asc or desc
    private String           sortIndex;
    private String           searchField;                   // Search Field
    private String           searchString;                  // The Search String
    private String           searchOperation;         // the Search Operation ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc'] 
    private String           oper;
    private boolean          loadOnce         = false;

    private AppLogFacade appLogFacade;
    private UserFacade userFacade;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public List<AppLog> getAppLogList() {
        return appLogList;
    }

    public void setAppLogList(List<AppLog> appLogList) {
        this.appLogList = appLogList;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    //======================================================================================================================
    @SkipValidation
    public String showAppLogListInputForm() throws Exception {

        this.actionName = "AppLogListAction.showAppLogListInputForm";
        try {
            this.logMessage = this.actionName + " method : forwarded to input jsp";
            log.debug(logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.GET_APP_LOG_LIST_ACTION_PROCESS_CODE, "", "Student", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, ServerConstants.EmptyModel);
            return INPUT;
        } catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.GET_APP_LOG_LIST_ACTION_PROCESS_CODE, "", "Student", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }
//======================================================================================================================
    @SkipValidation
    public String fetchAppLogList() throws Exception {

        this.actionName = "AppLogListAction.fetchAppLogList()";
        clearErrorsAndMessages();
        try{

            FacadeResult facadeResult = null;
            int tempLogDate = 0;
            if(!StringUtils.isEmpty(this.logDate)){
                tempLogDate = Integer.parseInt(DateTimeUtil.removeDateSeparator(this.logDate));
            }
            int tempUserId = 0;
            if(!StringUtils.isEmpty(this.userId) && StringUtils.isNumeric(this.userId)){
                tempUserId = Integer.parseInt(this.userId);
            }
            else if(!StringUtils.isEmpty(this.userName)){

                facadeResult = userFacade.getUserInfo(this.userName);
                if (facadeResult.getErrorCode() != 0) {
                    this.logMessage = this.actionName + " : error getting User Info";
                    log.error(logMessage);
                    if (ServerConstants.isAppLogEnabled)
                        appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.GET_APP_LOG_LIST_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "searchString :" +this.searchString);
                    addActionError(getText(facadeResult.getErrorKey()));
                    return INPUT;
                }
                UserInfo tmpUserInfo = (UserInfo) facadeResult.getData();
                if(tmpUserInfo == null){

                    addActionError(getText("error.common.invalidUserName"));
                    return INPUT;
                }
                tempUserId = tmpUserInfo.getUserId();
            }

            if(tempUserId == 0 && tempLogDate == 0) {
                addActionError(getText("error.common.invalidUserId"));
                return INPUT;
            }

            if(tempUserId > 0){
                facadeResult = appLogFacade.getLogByDoerId(tempUserId, tempLogDate);
                if (facadeResult.getErrorCode() != 0) {
                    this.logMessage = this.actionName + " : error getting LogByDoerId List";
                    log.error(logMessage);
                    if (ServerConstants.isAppLogEnabled)
                        appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.GET_APP_LOG_LIST_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "userId : " + tempUserId);
                    addActionError(getText(facadeResult.getErrorKey()));
                    return INPUT;
                }
            }
            else if(tempLogDate > 0){
                facadeResult = appLogFacade.getLogByDate(tempLogDate);
                if (facadeResult.getErrorCode() != 0) {
                    this.logMessage = this.actionName + " : error getting LogByDate List";
                    log.error(logMessage);
                    if (ServerConstants.isAppLogEnabled)
                        appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.GET_APP_LOG_LIST_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), "logDate : " + tempLogDate);
                    addActionError(getText(facadeResult.getErrorKey()));
                    return INPUT;
                }
            }
            this.appLogList = (List<AppLog>) facadeResult.getData();

            if(this.appLogList == null || this.appLogList.isEmpty()) {

                this.logMessage = this.actionName + " : AppLog List is empty";
                log.info(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.GET_APP_LOG_LIST_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, "", "userId : " + tempUserId + " , logDate : " + tempLogDate);
                addActionError(getText("error.common.requestedInformationNotAvailable"));
                return INPUT;
            }
            if (!CommonUtil.isNullOrEmpty(this.searchField) && !CommonUtil.isNullOrEmpty(this.searchString) && !CommonUtil.isNullOrEmpty(this.searchOperation)){

                log.debug("Search in : " + searchField + ", value : " + searchString + ", operation : " + searchOperation);
                searchAppLog();
            }
            else{
                if(this.gridModel != null && !this.gridModel.isEmpty())
                    this.gridModel.clear();
                this.gridModel = this.appLogList;
            }
            if(!CommonUtil.isNullOrEmpty(this.sortOrder)){

                if (this.sortOrder.equalsIgnoreCase("asc")){

//                    Comparator comparator = Collections.reverseOrder();
//                    Collections.sort(this.gridModel,comparator);
                }
                if (this.sortOrder != null && this.sortOrder.equalsIgnoreCase("desc")){

//                    Comparator comparator = Collections.reverseOrder();
//                    Collections.sort(this.gridModel,comparator);
                }
            }

            this.recordsCount =  this.gridModel.size();



            int toRecord = (this.rows * this.page);
            int fromRecord = toRecord - this.rows;
            if (toRecord > this.recordsCount)
                toRecord = this.recordsCount;

            //calculate the total pages for the query     
            this.totalPages =(int) Math.ceil((double)this.recordsCount / (double)this.rows);

            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.GET_APP_LOG_LIST_ACTION_PROCESS_CODE, "", "AppLog", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, "");
            return SUCCESS;

        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.GET_APP_LOG_LIST_ACTION_PROCESS_CODE, "", "AppLog", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), "");
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return ERROR;
        }
    }

    public void searchAppLog(){

        int findItemsCount = 0;
        if(this.gridModel == null)
            gridModel = new ArrayList();
        else if(!this.gridModel.isEmpty())
            this.gridModel.clear();
        if(this.searchField.equals("doerId")){

            for(int i = 0; i < this.appLogList.size();i++){
                AppLog tempAppLog = this.appLogList.get(i);
                if(tempAppLog.getDoerId() ==  Integer.parseInt(this.searchString)){
                    this.gridModel.add(findItemsCount,tempAppLog);
                    findItemsCount++;
                }
            }
        }
    }


    /**
    * @return how many rows we want to have into the grid
    */
    public int getRows(){

        return rows;
    }

    public void setRows(int rows){

        this.rows = rows;
    }

    /**
    * @return current page of the query
    */
    public int getPage(){

        return page;
    }

    public void setPage(int page){

        this.page = page;
    }

    /**
    * @return total pages for the query
    */
    public int getTotal() {

        return totalPages;
    }

    public void setTotal(int totalPages){

        this.totalPages = totalPages;
    }

    /**
    * @return total number of records for the query. e.g. select count(*) from table
    */
    public int getRecords(){

        return recordsCount;
    }

    public void setRecords(int records){

        this.recordsCount = records;

        if (this.recordsCount > 0 && this.rows > 0){

            this.totalPages = (int) Math.ceil((double) this.recordsCount / (double) this.rows);
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

        return sortIndex;
    }

    public void setSidx(String sidx){

        this.sortIndex = sidx;
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

    public List<AppLog> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<AppLog> gridModel) {
        this.gridModel = gridModel;
    }
}
