package com.soshiant.server.action.chart;

import com.opensymphony.xwork2.ActionSupport;
import com.soshiant.common.util.CustomerUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.monitoring.MonitoringFacade;
import com.soshiant.server.facade.parameters.ParametersFacade;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMetricInfo;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMonitoringData;
import com.soshiant.server.model.parameters.KeyValueObject;
import com.soshiant.server.model.user.UserPermissions;
import com.soshiant.server.thread.RMFMonitoringThread;
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
public class ChartAction extends ActionSupport {

    private static final long     serialVersionUID = 4851863957798371834L;

    Logger log = Logger.getLogger(ChartAction.class);
    private String actionName;

    private String logMessage = "";

    private String serverId;
    private String rmfMetricId;
    private String[] jsonData;

    private HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    private ParametersFacade parametersFacade;
    private MonitoringFacade monitoringFacade;
    private AppLogFacade appLogFacade;


    public void setParametersFacade(ParametersFacade parametersFacade) {
        this.parametersFacade = parametersFacade;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    public void setMonitoringFacade(MonitoringFacade monitoringFacade) {
        this.monitoringFacade = monitoringFacade;
    }

    //======================================================================================================================
    @SkipValidation
    public String showChartInputForm() throws Exception {

        this.actionName = "showChartInputForm()";

        try {
            getHttpServletRequest().setAttribute(ServerConstants.SERVER_ID,serverId);
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

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getRmfMetricId() {
        return rmfMetricId;
    }

    public void setRmfMetricId(String rmfMetricId) {
        this.rmfMetricId = rmfMetricId;
    }

    public String[] getJsonData() {
        return jsonData;
    }

}
