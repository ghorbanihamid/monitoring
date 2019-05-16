package com.soshiant.server.action.server;

import com.opensymphony.xwork2.ActionSupport;
import com.soshiant.common.result.ErrorObject;
import com.soshiant.common.result.ResultObject;
import com.soshiant.common.util.CommonUtil;
import com.soshiant.common.util.CustomerUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ErrorConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.server.ServerFacade;
import com.soshiant.server.model.server.Server;
import com.soshiant.server.model.user.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 11/17/11
 * Time: 6:14 PM
 */
public class monitorServerAction extends ActionSupport implements ServletRequestAware {

    Logger log = Logger.getLogger(monitorServerAction.class);

    private String actionName;
    private String logMessage = "";


    private List<Server>         gridModel;
    private List<Server>         serverList;
    private Integer              serverId;
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

    HttpServletRequest           request;

    private ServerFacade serverFacade;
    private AppLogFacade appLogFacade;


    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getServletRequest() {
        return this.request;
    }

    public List<Server> getServerList() {
        return serverList;
    }

    public void setServerList(List<Server> serverList) {
        this.serverList = serverList;
    }

    public void setServerFacade(ServerFacade serverFacade) {
        this.serverFacade = serverFacade;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }


    //======================================================================================================================
    @SkipValidation
    public String showMonitorServerInputForm() throws Exception {

        this.actionName = "ServerListAction.showMonitorServerInputForm()";

        try {
            log.debug(this.actionName + " method : forward to input jsp successfully done. ");
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, ServerConstants.EmptyModel);
            return INPUT;
        }
        catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }

    //======================================================================================================================
    @SkipValidation
    public String fetchMonitorableServersOfUserList() throws Exception {

        this.actionName = "ServerListAction.fetchMonitorableServersOfUserList()";
        clearErrorsAndMessages();
        try {

            UserInfo userInfo = CustomerUtil.getUserInfo();
            if(userInfo == null){
                this.logMessage = this.actionName + " : error getting userInfo()";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, -1, ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "", this.actionName, "", this.logMessage, "UserInfo object is null", "");
                addActionError(getText("errors.login.reLogin"));
                return LOGIN;
            }

            ResultObject resultObject = getMonitorableServersOfUserList();
            if (resultObject.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getMonitorableServersOfUserList() ";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, resultObject.getErrorCode(), resultObject.getSqlErrorStep(), ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "", this.actionName, resultObject.getSqlText(), this.logMessage, resultObject.getErrorMessage(), "");
                addActionError(getText(resultObject.getErrorKey()));
                return INPUT;
            }
            this.serverList = (List<Server>) resultObject.getResultData();

            if(this.serverList == null || this.serverList.isEmpty()) {

                this.logMessage = this.actionName + " : MonitorableServersOfUser List is empty";
                log.info(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "", this.actionName, resultObject.getSqlText(), this.logMessage, "", "");
                addActionMessage(getText("error.common.requestedInformationNotAvailable"));
                return INPUT;
            }

            if(this.gridModel != null && !this.gridModel.isEmpty())
                this.gridModel.clear();
            this.gridModel = serverList;

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
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, "");
            return SUCCESS;

        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "ServerSearch", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), "");
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return INPUT;
        }
    }
    //======================================================================================================================
    @SkipValidation
    public ResultObject getMonitorableServersOfUserList() throws Exception {

        ResultObject  resultObject = new ResultObject();
        ErrorObject errorObject;
        this.actionName = "ServerListAction.getMonitorableServersOfUserList()";
        try {

            UserInfo userInfo = CustomerUtil.getUserInfo();
            if(userInfo == null){
                this.logMessage = this.actionName + " : error getting userInfo()";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, -1, ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "", this.actionName, "", this.logMessage, "UserInfo object is null", "");
                resultObject.setErrorObject(new ErrorObject(-1,"errors.login.reLogin"));
                return resultObject;
            }

            FacadeResult facadeResult = serverFacade.getMonitorableServersOfUser(userInfo.getUserId());
            if (facadeResult.getErrorCode() != 0) {
                errorObject = resultObject.getErrorObject();
                this.logMessage = this.actionName + " : error getMonitorableServersOfUser() ";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, errorObject.getErrorCode(), errorObject.getSqlErrorStep(), ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "", this.actionName, errorObject.getSqlText(), this.logMessage, errorObject.getErrorMessage(), "");
                resultObject.setErrorObject(new ErrorObject(errorObject.getErrorCode(),errorObject.getErrorKey()));
                return resultObject;
            }
            List<Server>  monitorableServerList = (List<Server>) facadeResult.getData();

            if(CommonUtil.isNullOrEmpty(monitorableServerList)) {

                this.logMessage = this.actionName + " : MonitorableServersOfUser List is empty";
                errorObject = new ErrorObject(ErrorConstants.ERROR_CODE_DATA_NOT_FOUND, ErrorConstants.ERROR_KEY_DATA_NOT_FOUND);
                log.info(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "", this.actionName, "", " MonitorableServersOfUser List is empty", "", "");
                resultObject.setErrorObject(errorObject);
                return resultObject;
            }
            resultObject.setResultData(monitorableServerList);
            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, "");
            return resultObject;

        }
        catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            errorObject = new ErrorObject(ErrorConstants.ERROR_CODE_ACTION_EXCEPTION, ErrorConstants.ERROR_KEY_ACTION_EXCEPTION);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_SEARCH_ACTION_PROCESS_CODE, "", "ServerSearch", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), "");
            resultObject.setErrorObject(errorObject);
            return resultObject;
        }
    }
    //======================================================================================================================
    private boolean validateServerFetch(){

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
    public void searchServer(){

        int findItemsCount = 0;
        if(this.gridModel == null)
            gridModel = new ArrayList();
        else if(!this.gridModel.isEmpty())
            this.gridModel.clear();
        if(this.searchField.equals("serverId")){

            for(int i = 0; i < this.serverList.size();i++){
                Server tempServer = this.serverList.get(i);
                if(tempServer.getServerId() ==  Integer.parseInt(this.searchString)){
                    this.gridModel.add(findItemsCount,tempServer);
                    findItemsCount++;
                }
            }
        }
        else if(this.searchField.equals("serverName")){

            for(int i = 0; i < this.serverList.size();i++){
                Server tempServer = this.serverList.get(i);

                if(this.searchOperation.equalsIgnoreCase("eq")){      // eq : equal
                    if(tempServer.getServerName().equalsIgnoreCase(this.searchString)){
                        this.gridModel.add(findItemsCount,tempServer);
                        findItemsCount++;
                    }
                }
                else if(this.searchOperation.equalsIgnoreCase("cn")){  // cn : contains
                    if(tempServer.getServerName().toLowerCase().contains(this.searchString.toLowerCase())){
                        this.gridModel.add(findItemsCount,tempServer);
                        findItemsCount++;
                    }
                }
            }
        }
        else if(this.searchField.equals("OsType")){

            for(int i = 0; i < this.serverList.size();i++){
                Server tempServer = this.serverList.get(i);
                if(this.searchOperation.equalsIgnoreCase("eq")){      // eq : equal

                    if(String.valueOf(tempServer.getOsType()).equalsIgnoreCase(this.searchString)){
                        this.gridModel.add(findItemsCount,tempServer);
                        findItemsCount++;
                    }
                }
            }
        }
        else if(this.searchField.equals("managerId")){

            for(int i = 0; i < this.serverList.size();i++){
                Server tempServer = this.serverList.get(i);
                if(this.searchOperation.equalsIgnoreCase("eq")){      // eq : equal
                    if(String.valueOf(tempServer.getManagerId()).equalsIgnoreCase(this.searchString)){
                        this.gridModel.add(findItemsCount,tempServer);
                        findItemsCount++;
                    }
                }
            }
        }
        else if(this.searchField.equals("ServerType")){

            for(int i = 0; i < this.serverList.size();i++){
                Server tempServer = this.serverList.get(i);
                if(this.searchOperation.equalsIgnoreCase("eq")){      // eq : equal
                    if(String.valueOf(tempServer.getServerType()).equalsIgnoreCase(this.searchString)){
                        this.gridModel.add(findItemsCount,tempServer);
                        findItemsCount++;
                    }
                }
            }
        }
    }

    //======================================================================================================================
    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
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

    public List<Server> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<Server> gridModel) {
        this.gridModel = gridModel;
    }
}
