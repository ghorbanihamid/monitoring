package com.soshiant.server.action.parameter;

import com.soshiant.common.util.FileUtils;
import com.soshiant.common.util.ParametersUtil;
import com.soshiant.server.model.parameters.KeyValueObject;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.parameters.ParametersFacade;
import com.soshiant.server.model.position.Positions;
import com.opensymphony.xwork2.ActionSupport;
import com.soshiant.server.model.server.Server;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: Nov 30, 2011
 * Time: 5:19:59 AM
 */
public class ParametersAction extends ActionSupport implements ServletRequestAware {

    Logger log = Logger.getLogger(this.getClass().getName());
    private String logMessage;
    private String actionName = ParametersAction.class.getSimpleName();

    private HttpServletRequest servletRequest;


    private static Map<String, String> yesNoList;
    private static Map<String, String> howIntroduceList;
    private static Map<String, String> genderStatusList;
    private static Map<String, String> educationLevelList;
    private static Map<String, String> maritalStatusList;
    private static Map<String, String> languageSkillsList;
    private static Map<String, String> sessionTypesList;
    private static Map<String, String> feePayTypesList;
    private static Map<String, String> classDaysList;
    private static Map<String, String> classTypesList;
    private static List<Positions> positionsList;
    private static List<Server> mainFrameServersList;


    private ParametersFacade parametersFacade;
    private AppLogFacade appLogFacade;

    public void setParametersFacade(ParametersFacade parametersFacade) {
        this.parametersFacade = parametersFacade;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    //======================================================================================================================
    public String getRealPath(String selectedPath) {
        try{
            return  this.servletRequest.getSession().getServletContext().getRealPath(selectedPath) +
                    FileUtils.getPathSeparator() +
                    selectedPath;
        }
        catch (Exception e){
            return "";
        }
    }

    //======================================================================================================================
    public String loadYesNoList() {

        if (yesNoList == null || yesNoList.isEmpty()) {

            yesNoList = ParametersUtil.getYesNoList();
        }
        return SUCCESS;

    }

    public List<KeyValueObject> getYesNoList() {
        List<KeyValueObject> yesNoKeyValueList = new ArrayList<KeyValueObject>(0);
        if (yesNoList != null && !yesNoList.isEmpty()) {
            yesNoKeyValueList = new ArrayList<KeyValueObject>(yesNoList.size());
            for (String key : yesNoList.keySet()) {
                KeyValueObject KeyValueObject = new KeyValueObject(key, yesNoList.get(key));
                yesNoKeyValueList.add(KeyValueObject);
            }
        }
        return yesNoKeyValueList;
    }
    //======================================================================================================================
    public String loadGenderStatusList() {

        if (genderStatusList == null || genderStatusList.isEmpty()) {
            genderStatusList = ParametersUtil.getGenderStatusList();
        }
        return SUCCESS;

    }

    public List<KeyValueObject> getGenderStatusList() {
        List<KeyValueObject> genderStatusKeyValueList = new ArrayList<KeyValueObject>(0);
        if (genderStatusList != null && !genderStatusList.isEmpty()) {
            genderStatusKeyValueList = new ArrayList<KeyValueObject>(genderStatusList.size());
            for (String key : genderStatusList.keySet()) {
                KeyValueObject KeyValueObject = new KeyValueObject(key, genderStatusList.get(key));
                genderStatusKeyValueList.add(KeyValueObject);
            }
        }
        return genderStatusKeyValueList;
    }
    //======================================================================================================================
    public String loadMaritalStatusList() {

        if (maritalStatusList == null || maritalStatusList.isEmpty()) {
            maritalStatusList = ParametersUtil.getMaritalStatusList();
        }
        return SUCCESS;

    }

    public List<KeyValueObject> getMaritalStatusList() {
        List<KeyValueObject> maritalStatusKeyValueList = new ArrayList<KeyValueObject>(0);
        if (maritalStatusList != null && !maritalStatusList.isEmpty()) {
            maritalStatusKeyValueList = new ArrayList<KeyValueObject>(maritalStatusList.size());
            for (String key : maritalStatusList.keySet()) {
                KeyValueObject KeyValueObject = new KeyValueObject(key, maritalStatusList.get(key));
                maritalStatusKeyValueList.add(KeyValueObject);
            }
        }
        return maritalStatusKeyValueList;
    }
    //======================================================================================================================
    public String loadEducationLevelList() {

        if(educationLevelList == null || educationLevelList.isEmpty()){
            educationLevelList = ParametersUtil.getEducationLevelList();
        }
        return SUCCESS;
    }

    public List<KeyValueObject> getEducationLevelList() {
        List<KeyValueObject> educationLevelKeyValueList = new ArrayList<KeyValueObject>(0);
        if (educationLevelList != null && !educationLevelList.isEmpty()) {
            educationLevelKeyValueList = new ArrayList<KeyValueObject>(educationLevelList.size());
            for (String key : educationLevelList.keySet()) {
                KeyValueObject KeyValueObject = new KeyValueObject(key, educationLevelList.get(key));
                educationLevelKeyValueList.add(KeyValueObject);
            }
        }
        return educationLevelKeyValueList;
    }
    //======================================================================================================================
    public String loadLanguageSkills() {

        if(languageSkillsList == null || languageSkillsList.isEmpty()){

            languageSkillsList = ParametersUtil.getLanguageSkillsList();
        }
        return SUCCESS;
    }

    public List<KeyValueObject> getLanguageSkillsList() {
        List<KeyValueObject> languageSkillsKeyValueList = new ArrayList<KeyValueObject>(0);
        if (languageSkillsList != null && !languageSkillsList.isEmpty()) {
            languageSkillsKeyValueList = new ArrayList<KeyValueObject>(languageSkillsList.size());
            for (String key : languageSkillsList.keySet()) {
                KeyValueObject KeyValueObject = new KeyValueObject(key, languageSkillsList.get(key));
                languageSkillsKeyValueList.add(KeyValueObject);
            }
        }
        return languageSkillsKeyValueList;
    }
    //======================================================================================================================
    public List<Positions> getPositionsList() {

        if(positionsList == null || positionsList.isEmpty() ){

            loadPositionsList();
        }
        return positionsList;

    }

    public String loadPositionsList() {

        if(positionsList == null || positionsList.isEmpty() ){

            FacadeResult facadeResult = parametersFacade.getPositionsList();
            if (facadeResult.getErrorCode() != 0) {
                logMessage = actionName + " : error getting Positions List";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.PARAMETER_ACTION_PROCESS_CODE, "", "", actionName, facadeResult.getSqlText(), logMessage, facadeResult.getErrorMessage(), "");
                addActionError(getText("error.common.systemCouldNotRespond1"));
                return null;
            }
            positionsList = (List<Positions>) facadeResult.getData();
        }
        return SUCCESS;
    }
    //======================================================================================================================

    //======================================================================================================================
    public List<Server> getMainFrameServersList() {

        if(mainFrameServersList == null || mainFrameServersList.isEmpty() ){

            loadMainFrameServersListList();
        }
        return mainFrameServersList;

    }

    public String loadMainFrameServersListList() {

        if(mainFrameServersList == null || mainFrameServersList.isEmpty() ){

            FacadeResult facadeResult = parametersFacade.getMainFrameServersList();
            if (facadeResult.getErrorCode() != 0) {
                logMessage = actionName + " : error getting mainFrameServersList List";
                log.error(logMessage);
                if (ServerConstants.isAppLogEnabled)
                    appLogFacade.logInfo(ServerConstants.ActionMethodIsNotMainOperation, 0, 0, facadeResult.getErrorCode(), facadeResult.getErrorStep(), ActionConstants.PARAMETER_ACTION_PROCESS_CODE, "", "", actionName, facadeResult.getSqlText(), logMessage, facadeResult.getErrorMessage(), "");
                addActionError(getText("error.common.systemCouldNotRespond1"));
                return null;
            }
            mainFrameServersList = (List<Server>) facadeResult.getData();
        }
        return SUCCESS;
    }
    //======================================================================================================================

}
