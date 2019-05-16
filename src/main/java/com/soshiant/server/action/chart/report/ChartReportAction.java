package com.soshiant.server.action.chart.report;

import com.opensymphony.xwork2.ActionSupport;
import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.common.util.CommonUtil;
import com.soshiant.common.util.CustomerUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.monitoring.MonitoringFacade;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMetricInfo;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMonitoringData;
import com.soshiant.server.model.parameters.KeyValueObject;
import com.soshiant.server.model.user.UserPermissions;
import com.soshiant.server.model.user.UserPermissionsView;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.List;

/*
 * Created by Hamid on 11/27/15.
 */

@ParentPackage("json-default")
@Namespace("/chartJsonPackage")
public class ChartReportAction extends ActionSupport {

    private static final long     serialVersionUID = 4851863957798371834L;

    Logger log = Logger.getLogger(ChartReportAction.class);
    private String actionName;

    private String logMessage = "";

    private String serverId;
    private String rmfMetricId;
    private String reportDate;
    private String reportFromTime;
    private String reportToTime;
    private String jsonData;

    private List<KeyValueObject>  permittedRmfMetricIdList;

    private HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    private MonitoringFacade monitoringFacade;
    private AppLogFacade appLogFacade;



    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    public void setMonitoringFacade(MonitoringFacade monitoringFacade) {
        this.monitoringFacade = monitoringFacade;
    }

    //======================================================================================================================
    @SkipValidation
    public String showChartReportInputForm() throws Exception {

        this.actionName = "showChartReportInputForm()";

        try {
            this.reportDate = DateTimeUtil.getCurrentShamsiDateWithSeparator();

            serverId = "101";
            rmfMetricId = "8D0460";
            showChartReportResultForm();

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
    public String fetchPermittedRmfMetricIdList() throws Exception {

        this.actionName = "getPermittedRmfMetricIdList()";

        try {
            if(CommonUtil.isNullOrEmpty(serverId)){
                this.logMessage = this.actionName + " : error getPermittedRmfMetricIdList() ";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, "", this.logMessage, "user does not have any permission","serverId: " + serverId);
                addActionError(getText("error.common.invalidServerId"));
                return ERROR;
            }
            int tempServerId = Integer.parseInt(serverId);
            List<UserPermissionsView> userPermissionsList = CustomerUtil.getUserPermissionFromSession();
            for (UserPermissionsView userPermission : userPermissionsList) {
                if(userPermission.getServerId() == tempServerId){
                    permittedRmfMetricIdList.add(new KeyValueObject(userPermission.getMetricId(), userPermission.getMetricDesc()));
                }
            }
            if(permittedRmfMetricIdList == null) {
                this.logMessage = this.actionName + " : error getPermittedRmfMetricIdList() ";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, "", this.logMessage, "user does not have any permission","serverId: " + serverId);
                addActionError(getText("error.common.recordNotFound"));
                return ERROR;
            }
            return SUCCESS;
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
    public String showChartReportResultForm() throws Exception {

        this.actionName = "showChartReportResultForm()";
        String inputString = "rmfMetricId : " +  rmfMetricId + ",reportDate :" + reportDate +", reportFromTime : " + reportFromTime + ",reportToTime" + reportToTime;
        try {
            int tempServerId = Integer.parseInt(serverId);

            int tempReportDate = Integer.parseInt(CommonUtil.nvl(DateTimeUtil.removeDateSeparator(reportDate), "0"));
            if(tempReportDate == 0)
                tempReportDate = DateTimeUtil.getCurrentShamsiDate();

            reportFromTime = "0";
            reportToTime = "0";
            String[] rmfMetricIdList = new String[]{};
            boolean metricIdIsPermitted = false;
            List<UserPermissionsView> userPermissionsList = CustomerUtil.getUserPermissionFromSession();
            for (UserPermissionsView userPermission : userPermissionsList) {
                if(userPermission.getServerId() == tempServerId && userPermission.getMetricId().equals(rmfMetricId)){
                    metricIdIsPermitted = true;
                    break;
                }
            }
            if(!metricIdIsPermitted) {
                this.logMessage = this.actionName + " : error getRMFMonitoringDataFromDB() ";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, "", this.logMessage, "user does not have any permission for metricId",inputString);
                addActionError(getText("error.common.recordNotFound"));
                return ERROR;

            }
            rmfMetricIdList[0] = rmfMetricId;
            FacadeResult facadeResult = monitoringFacade.getRMFMonitoringDataFromDB(tempServerId,tempReportDate,Integer.parseInt(reportFromTime),Integer.parseInt(reportToTime),rmfMetricIdList);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getRMFMonitoringDataFromDB() ";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(),inputString);
                addActionError(getText(facadeResult.getErrorKey()));
                return ERROR;
            }
            List<RMFMonitoringData> rmfMonitoringResult = (List<RMFMonitoringData>) facadeResult.getData();

            if(rmfMonitoringResult == null || rmfMonitoringResult.size() == 0){
                this.logMessage = this.actionName + ".getRMFMonitoringDataFromDB() : error data not found";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(),inputString);
                addActionError(getText("error.common.recordNotFound"));
                return ERROR;
            }
//            String jsonDataPart = "";
//            String chartCaption = "";
//            String chartXAxisName = "";
//            String chartYAxisName = "";
//            String chartNumberPrefix = "";
//            for (String aRmfMetricId : rmfMetricIdList) {
//
//                RMFMetricInfo rmfMetricInfo = null;
//                facadeResult = monitoringFacade.getRMFMetricIdInfo(aRmfMetricId);
//                if (facadeResult.getErrorCode() != 0) {
//                    this.logMessage = this.actionName + " : error getRMFMetricIdInfo() ";
//                    log.error(logMessage);
//                    if (ServerConstants.isAppLogEnabled)
//                        appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(),"RmfMetricId : " + aRmfMetricId);
//                }
//                else {
//                    rmfMetricInfo = (RMFMetricInfo) facadeResult.getData();
//                }
//                if(rmfMetricInfo != null){
//                    chartCaption      =  rmfMetricInfo.getItemName();
//                    chartXAxisName    =  rmfMetricInfo.getChartLabelColumn();
//                    chartYAxisName    =  rmfMetricInfo.getChartValueColumn();
//                    chartNumberPrefix =  "";//rmfMetricInfo.getItemName();
//                }
//                for (RMFMonitoringData aRmfMonitoringResult : rmfMonitoringResult) {
//                    if (aRmfMonitoringResult.getRmfMetricId().equalsIgnoreCase(aRmfMetricId)) {
//                        jsonDataPart += "{ \"label\" : \" " + String.valueOf(aRmfMonitoringResult.getTransStartTime()) + "\"," +
//                                    "  \"value\" : \" " +  aRmfMonitoringResult.getRowValue() + "\" },";
//                    }
//                }
//                jsonDataPart = jsonDataPart.substring(0, jsonDataPart.length() -1) + "]}";
//                jsonData = "{\"chart\": {\n" +
//                        "         \"caption\"      : \"" + chartCaption      + "\" ,\n" +
//                        "         \"xAxisName\"    : \"" + chartXAxisName    + "\" ,\n" +
//                        "         \"yAxisName\"    : \"" + chartYAxisName    + "\" ,\n" +
//                        "         \"numberPrefix\" : \"" + chartNumberPrefix + "\" ,\n" +
//                        "          },\n" +
//                        "\"data\" :\n"  +
//                        "[\n" + jsonDataPart;
//            }

            for (String aRmfMetricId : rmfMetricIdList) {

                List<KeyValueObject> tempList = new ArrayList<>();
                for (RMFMonitoringData aRmfMonitoringResult : rmfMonitoringResult) {
                    if (aRmfMonitoringResult.getRmfMetricId().equalsIgnoreCase(aRmfMetricId)) {

                        tempList.add(new KeyValueObject(aRmfMonitoringResult.getRmfColumnValueForChartLabel(),aRmfMonitoringResult.getRowValue()));
                    }
                }
                facadeResult = monitoringFacade.generateChartJsonData(aRmfMetricId, tempList);
                jsonData = (String)facadeResult.getData();
            }

            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Staff", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage,inputString );
            return SUCCESS;
        }
        catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.LOGIN_ACTION_PROCESS_CODE, "", "Server", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            return  ERROR;
        }
    }

//    @JSON
//    public String getJSON() {
//        try {
//            return showChartReportResultForm();
//        }
//        catch (Exception e){
//            return null;
//        }
//    }
//
    public String getRmfMetricId() {
        return rmfMetricId;
    }

    public void setRmfMetricId(String rmfMetricId) {
        this.rmfMetricId = rmfMetricId;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getReportFromTime() {
        return reportFromTime;
    }

    public void setReportFromTime(String reportFromTime) {
        this.reportFromTime = reportFromTime;
    }

    public String getReportToTime() {
        return reportToTime;
    }

    public void setReportToTime(String reportToTime) {
        this.reportToTime = reportToTime;
    }


    public String getJsonData() {
        return jsonData;
    }

    public List<KeyValueObject> getPermittedRmfMetricIdList() {
        return permittedRmfMetricIdList;
    }
}
