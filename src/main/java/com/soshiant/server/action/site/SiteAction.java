package com.soshiant.server.action.site;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.site.SiteFacade;
import com.soshiant.server.model.site.Site;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Created by IntelliJ IDEA.
 * User: Cheshmapoosh
 * Date: 9/18/11
 * Time: 12:01 PM
 */
public class SiteAction extends ActionSupport implements ModelDriven {
    Logger log = Logger.getLogger(SiteAction.class);

    private String actionName;
    private String logMessage = "";

    private Site site = new Site();

    private SiteFacade siteFacade;
    private AppLogFacade appLogFacade;


    public void setSiteFacade(SiteFacade siteFacade) {
        this.siteFacade = siteFacade;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    //======================================================================================================================
    public Object getModel() {
        return site;
    }
    //======================================================================================================================
    @SkipValidation
    public String showSiteInputForm() throws Exception {

        this.actionName = "SiteAction.showSiteInputForm()";

        try {
            this.logMessage = this.actionName + " method : forwarded to input jsp";
            log.debug(logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Site", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, ServerConstants.EmptyModel);
            return INPUT;
        } catch (Exception e) {
            this.logMessage = this.actionName + " method Exception:";
            log.error(this.logMessage + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Site", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), ServerConstants.EmptyModel);
            addActionError(getText("error.common.systemCouldNotRespond1"));
            return ERROR;
        }
    }
    //======================================================================================================================
    @SkipValidation
    public String fetchSiteInfo() throws Exception {

        this.actionName = "SiteAction.getSiteInfo()";
        clearErrorsAndMessages();
        try {
            FacadeResult facadeResult = siteFacade.getSiteInfo(site.getSiteId());
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error getting Site Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.site.toString());
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            this.site = (Site) facadeResult.getData();
            if(this.site == null){
                this.logMessage = this.actionName + " : error Site not found";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.site.toString());
                addActionError(getText("error.common.siteIdNotFound"));
                return INPUT;
            }

            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Site", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, this.site.toString());
            return SUCCESS;
        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Site", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), this.site.toString());
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return INPUT;
        }
    }
    //======================================================================================================================
    public String saveSiteInfo() throws Exception {

        this.actionName = "SiteAction.saveSiteInfo()";
        clearErrorsAndMessages();
        FacadeResult facadeResult;
        try {
            facadeResult = siteFacade.saveSiteInfo(site);
            if (facadeResult.getErrorCode() != 0) {
                this.logMessage = this.actionName + " : error saving Site Info";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "", this.actionName, facadeResult.getSqlText(), this.logMessage, facadeResult.getErrorMessage(), this.site.toString());
                addActionError(getText(facadeResult.getErrorKey()));
                return INPUT;
            }
            this.logMessage = this.actionName + " method successfully done";
            log.debug(this.logMessage);
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Site", this.actionName, ServerConstants.NoSqlText, this.logMessage, ServerConstants.NoExceptionMessage, this.site.toString());
            return SUCCESS;
        } catch (Exception e) {
            log.error(this.actionName + " method Exception:" + e.getMessage());
            if (ServerConstants.isAppLogEnabled)
                appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.STAFF_ACTION_PROCESS_CODE, "", "Site", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), this.site.toString());
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return INPUT;
        }
    }
    //======================================================================================================================
    public void validate() {

        clearErrorsAndMessages();

        if (StringUtils.isEmpty(site.getSiteName()))
            addActionError(getText("error.common.olNameRequired"));
        else if (!CommonUtil.isPersianText(site.getSiteName()))
            addActionError(getText("error.common.invalidOlName"));

        if (site.getManagerId() <= 0 || String.valueOf(site.getManagerId()).length() < 6)
            addActionError(getText("error.common.enFamilyRequired"));


        if (!StringUtils.isEmpty(site.getSiteTelNo())) {
            if (!StringUtils.isNumeric(site.getSiteTelNo()))
                addActionError(getText("error.common.invalidHomePhone"));
        }

        if (!StringUtils.isEmpty(site.getSiteFax())) {
            if (!StringUtils.isNumeric(site.getSiteFax()))
                addActionError(getText("error.common.invalidHomePhone"));
        }

        if (!StringUtils.isEmpty(site.getDescription())) {
            if (!StringUtils.isEmpty(site.getDescription())) {
                addActionError(getText("error.common.homePhoneRequired"));
            }
        }
    }

}
