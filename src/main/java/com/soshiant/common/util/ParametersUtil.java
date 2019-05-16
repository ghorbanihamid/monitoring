package com.soshiant.common.util;

import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.parameters.ParametersFacade;
import com.soshiant.server.model.staff.Staff;
import com.soshiant.common.util.BundleUtil;
import org.apache.commons.collections.map.ListOrderedMap;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class ParametersUtil {

    private static BundleUtil bundle = BundleUtil.getInstance();

    private static int chartRefreshTime   = 0;

    private static Map yesNoList          = new ListOrderedMap();
    private static Map maritalStatus      = new ListOrderedMap();
    private static Map genderStatus       = new ListOrderedMap();
    private static Map educationLevel     = new ListOrderedMap();
    private static Map languageSkills     = new ListOrderedMap();
    private static Map classDays          = new ListOrderedMap();

    ParametersFacade parametersFacade;

    public void setParametersFacade(ParametersFacade parametersFacade) {
        this.parametersFacade = parametersFacade;
    }

    //======================================================================================================================
    public static int getChartRefreshTime() {

        if (chartRefreshTime <= 0 ) {
            chartRefreshTime = 2;
        }
        return chartRefreshTime;
    }
    //======================================================================================================================
    public static Map getYesNoList() {

        if (yesNoList == null || yesNoList.isEmpty()) {
            yesNoList.put("1", bundle.getMessage("label.yes"));
            yesNoList.put("2", bundle.getMessage("label.no"));
        }
        return yesNoList;
    }
    //======================================================================================================================
    public static Map getMaritalStatusList() {

        if (maritalStatus == null || maritalStatus.isEmpty()) {
            maritalStatus.put("1", bundle.getMessage("label.maritalStatus.single"));
            maritalStatus.put("2", bundle.getMessage("label.maritalStatus.married"));
        }
        return maritalStatus;
    }
    //======================================================================================================================
    public static String getMaritalStatusName(byte maritalStatusId) {

        if (maritalStatus == null || maritalStatus.isEmpty()) {
            getMaritalStatusList();
        }
        String maritalName = "";
        if(maritalStatus.containsKey(String.valueOf(maritalStatusId)))
        maritalName = (String)maritalStatus.get(String.valueOf(maritalStatusId));
        return maritalName;
    }
    //======================================================================================================================
    public static Map getGenderStatusList() {

        if (genderStatus == null || genderStatus.isEmpty()) {
            genderStatus.put("1", bundle.getMessage("label.genderStatus.male"));
            genderStatus.put("2", bundle.getMessage("label.genderStatus.female"));
        }
        return genderStatus;
    }
    //======================================================================================================================
    public static String getGenderStatusName(byte genderStatusId) {

        if (genderStatus == null || genderStatus.isEmpty()) {
            getGenderStatusList();
        }

        String genderName = "";
        if(genderStatus.containsKey(String.valueOf(genderStatusId)))
            genderName = (String)genderStatus.get(String.valueOf(genderStatusId));
        return genderName;
    }
    //======================================================================================================================
    public static Map getEducationLevelList() {
        if (educationLevel == null || educationLevel.isEmpty()) {
            educationLevel.put("1", bundle.getMessage("label.educationLevel.unRead"));
            educationLevel.put("2", bundle.getMessage("label.educationLevel.primary"));
            educationLevel.put("3", bundle.getMessage("label.educationLevel.secondary"));
            educationLevel.put("4", bundle.getMessage("label.educationLevel.highSchool"));
            educationLevel.put("5", bundle.getMessage("label.educationLevel.associatesDegree"));
            educationLevel.put("6", bundle.getMessage("label.educationLevel.bachelorDegree"));
            educationLevel.put("7", bundle.getMessage("label.educationLevel.masterDegree"));
            educationLevel.put("8", bundle.getMessage("label.educationLevel.phdDegree"));
        }
        return educationLevel;
    }
    //======================================================================================================================
    public static String getEducationLevelName(byte educationLevelId) {

        if (educationLevel == null || educationLevel.isEmpty()) {
            getEducationLevelList();
        }
        String educationName = "";
        if(educationLevel.containsKey(String.valueOf(educationLevelId)))
            educationName = (String)educationLevel.get(String.valueOf(educationLevelId));
        return educationName;
    }
    //======================================================================================================================
    public static Map getLanguageSkillsList() {
        if (languageSkills == null || languageSkills.isEmpty()) {
            languageSkills.put("1", bundle.getMessage("label.languageSkills.listening"));
            languageSkills.put("2", bundle.getMessage("label.languageSkills.writing"));
            languageSkills.put("3", bundle.getMessage("label.languageSkills.speaking"));
            languageSkills.put("4", bundle.getMessage("label.languageSkills.reading"));
        }
        return languageSkills;
    }
    //======================================================================================================================
    public static Map getDays() {
        if (classDays == null || classDays.isEmpty()) {
            classDays.put("0", bundle.getMessage("label.saturday"));
            classDays.put("1", bundle.getMessage("label.sunday"));
            classDays.put("2", bundle.getMessage("label.monday"));
            classDays.put("3", bundle.getMessage("label.tuesday"));
            classDays.put("4", bundle.getMessage("label.wednesday"));
            classDays.put("5", bundle.getMessage("label.thursday"));
            classDays.put("6", bundle.getMessage("label.friday"));
        }
        return classDays;
    }
    //======================================================================================================================
    public static boolean isValidDay(byte dayNo) {
        if (classDays == null || classDays.isEmpty()) {
            getDays();
        }
        String dayName = (String)classDays.get(String.valueOf(dayNo));
        if(dayName != null && !dayName.isEmpty())
            return true;
        else
            return false;
    }
    //======================================================================================================================
    public static String getLanguageSkillName(byte languageSkillId) {

        if (languageSkills == null || languageSkills.isEmpty()) {
            getLanguageSkillsList();
        }
        String skillName = "";
        if(languageSkills.containsKey(String.valueOf(languageSkillId))) {
            skillName = (String)languageSkills.get(String.valueOf(languageSkillId));
        }
        return skillName;
    }
    //======================================================================================================================
    public Staff setStaffDescription(Staff staff){

        if(staff != null){

            String tempValue = getGenderStatusName(staff.getGenderStatus());
            staff.setGenderName(tempValue);
        }
        return staff;
    }
    //======================================================================================================================

}
