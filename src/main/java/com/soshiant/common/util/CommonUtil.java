package com.soshiant.common.util;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.server.constants.ServerConstants;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public abstract class CommonUtil {

    private static Logger log = Logger.getLogger("CommonUtil");

    public static Locale getCurrentLocale() {
        return ActionContext.getContext().getLocale();
    }

    public static boolean setLocale(Locale locale) {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if (request == null) {
                log.error(" CommonUtil.setLocale() exception, request is null");
                return false;
            }
            HttpSession session = request.getSession();
            if (session == null) {
                log.error(" CommonUtil.setLocale() exception, session is null");
                return false;
            }
            session.setAttribute(ServerConstants.LOCALE_KEY, locale);
            ActionContext.getContext().setLocale(locale);
            return true;
        }
        catch (Exception e){
            log.error(" CommonUtil.setLocale() , exception : " +e.getMessage());
            return false;
        }

    }

    public static boolean setLanguageToEnglish() {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if(request == null){
                log.error(" CommonUtil.setLanguageToEnglish() exception, request is null");
                return false;
            }
            HttpSession session = request.getSession();
            if(session == null){
                log.error(" CommonUtil.setLanguageToEnglish() exception, session is null");
                return false;
            }
            session.setAttribute(ServerConstants.LOCALE_KEY, new Locale("en", "US"));
            ActionContext.getContext().setLocale(new Locale("en", "US"));
            return true;
        }
        catch (Exception e){
            log.error(" CommonUtil.setLanguageToEnglish() , exception : " +e.getMessage());
            return false;
        }

    }

    public static boolean setLanguageToEnglish(HttpSession session) {

        try {
            if(session == null){
                log.error(" CommonUtil.setLanguageToEnglish() exception, session is null");
                return false;
            }
            session.setAttribute(ServerConstants.LOCALE_KEY, new Locale("en", "US"));
            ActionContext.getContext().setLocale(new Locale("en", "US"));
            return true;
        }
        catch (Exception e){
            log.error(" CommonUtil.setLanguageToEnglish() , exception : " +e.getMessage());
            return false;
        }

    }

    public static boolean setLanguageToPersian() {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if(request == null){
                log.error(" CommonUtil.setLanguageToPersian() exception, request is null");
                return false;
            }
            HttpSession session = request.getSession();
            if(session == null){
                log.error(" CommonUtil.setLanguageToPersian() exception, session is null");
                return false;
            }
            session.setAttribute(ServerConstants.LOCALE_KEY, new Locale("fa", "IR"));
            ActionContext.getContext().setLocale(new Locale("fa", "IR"));
            return true;
        }
        catch (Exception e){
            log.error(" CommonUtil.setLanguageToPersian() , exception : " +e.getMessage());
            return false;
        }

    }

    public static boolean setLanguageToPersian(HttpSession session) {

        try {
            if(session == null){
                log.error(" CommonUtil.setLanguageToPersian() exception, session is null");
                return false;
            }
            session.setAttribute(ServerConstants.LOCALE_KEY, new Locale("fa", "IR"));
            ActionContext.getContext().setLocale(new Locale("fa", "IR"));
            return true;
        }
        catch (Exception e){
            log.error(" CommonUtil.setLanguageToPersian() , exception : " +e.getMessage());
            return false;
        }

    }

    public static int getCurrentUserIdFromSession() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if(request == null){
                log.error(" CommonUtil.getCurrentUserIdFromSession() exception, request is null");
                return -1;
            }
            HttpSession session = request.getSession();
            if(session == null){
                log.error(" CommonUtil.getCurrentUserIdFromSession() exception, session is null");
                return -1;
            }
            String userId = session.getAttribute(ServerConstants.OBJECT_USER_ID) != null ? session.getAttribute(ServerConstants.OBJECT_USER_ID).toString() : "0";
            return Integer.parseInt(CommonUtil.nvl(userId, "-1"));
        }
        catch (Exception e){
            log.error(" CommonUtil.getCurrentUserIdFromSession() , getCurrentUserIdFromSession exception : " + e.getMessage());
            return -1;
        }
    }

    public static int getCurrentUserIdFromSession(HttpSession session) {
        try {
            if(session == null){
                log.error(" CommonUtil.getCurrentUserIdFromSession() exception, session is null");
                return -1;
            }
            String userId = session.getAttribute(ServerConstants.OBJECT_USER_ID) != null ? session.getAttribute(ServerConstants.OBJECT_USER_ID).toString() : "0";
            return Integer.parseInt(CommonUtil.nvl(userId, "-1"));
        }
        catch (Exception e){
            log.error(" CommonUtil.getCurrentUserIdFromSession() , getCurrentUserIdFromSession exception : " + e.getMessage());
            return -1;
        }
    }

    public static short getCurrentUserBranchFromSession() {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if(request == null){
                log.error(" CommonUtil.getCurrentUserBranchFromSession() exception, request is null");
                return -1;
            }
            HttpSession session = request.getSession();
            if(session == null){
                log.error(" CommonUtil.getCurrentUserBranchFromSession() exception, session is null");
                return -1;
            }
            String branchId = session.getAttribute(ServerConstants.OBJECT_USER_BRANCH) != null ? session.getAttribute(ServerConstants.OBJECT_USER_BRANCH).toString() : "0";
            return Short.parseShort(CommonUtil.nvl(branchId, "-1"));
        }
        catch (Exception e){
            log.error(" CommonUtil.getCurrentUserBranchFromSession() : null pointer exception, ServletActionContext.getRequest().getSession() ");
            return -1;
        }
    }

    public static short getCurrentUserTypeIdFromSession() {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if(request == null){
                log.error(" CommonUtil.getCurrentUserTypeIdFromSession() exception, request is null");
                return -1;
            }
            HttpSession session = request.getSession();
            if(session == null){
                log.error(" CommonUtil.getCurrentUserTypeIdFromSession() exception, session is null");
                return -1;
            }
            String userTypeId = session.getAttribute(ServerConstants.OBJECT_USER_TYPE) != null ? session.getAttribute(ServerConstants.OBJECT_USER_TYPE).toString() : "0";
            return Short.parseShort(userTypeId);
        }
        catch (Exception e){
            log.error(" CommonUtil.getCurrentUserTypeIdFromSession() exception, Message : " + e.getMessage());
            return -1;
        }
    }

    public static short getCurrentUserTypeIdFromSession(HttpSession session) {

        try {
            if(session == null){
                log.error(" CommonUtil.getCurrentUserTypeIdFromSession() exception, session is null");
                return -1;
            }
            String userTypeId = session.getAttribute(ServerConstants.OBJECT_USER_TYPE) != null ? session.getAttribute(ServerConstants.OBJECT_USER_TYPE).toString() : "0";
            return Short.parseShort(userTypeId);
        }
        catch (Exception e){
            log.error(" CommonUtil.getCurrentUserTypeIdFromSession() exception, Message : " + e.getMessage());
            return -1;
        }
    }

    public static String getCurrentUserTypeNameFromSession() {

        short userTypeId = getCurrentUserTypeIdFromSession();
        String userTypeName = "";
        switch (userTypeId){
            case 0  : userTypeName = ServerConstants.POSITION_TYPE_NAME_UNKNOWN;
                      break;
            case 1  : userTypeName = ServerConstants.POSITION_TYPE_NAME_STUDENT;
                      break;
            case 2  : userTypeName = ServerConstants.POSITION_TYPE_NAME_ADVISOR;
                      break;
            case 3  : userTypeName = ServerConstants.POSITION_TYPE_NAME_TEACHER;
                      break;
            case 4  : userTypeName = ServerConstants.POSITION_TYPE_NAME_BRANCH_ASSISTANT;
                      break;
            case 5  : userTypeName = ServerConstants.POSITION_TYPE_NAME_BRANCH_HEAD;
                      break;
            case 6  : userTypeName = ServerConstants.POSITION_TYPE_NAME_HEAD_TEACHER;
                      break;
            case 7  : userTypeName = ServerConstants.POSITION_TYPE_NAME_FINANCE_ADMIN;
                      break;
            case 8  : userTypeName = ServerConstants.POSITION_TYPE_NAME_EDUCATION_ARCHITECT;
                      break;
            case 9  : userTypeName = ServerConstants.POSITION_TYPE_NAME_SHARE_HOLDERS;
                      break;
            case 10 : userTypeName = ServerConstants.POSITION_TYPE_NAME_COMPUTER_ADMIN;
                      break;
            case 11 : userTypeName = ServerConstants.POSITION_TYPE_NAME_CHAIRMAN;
        }
        return userTypeName;
    }

    public static String getCurrentUserTypeNameFromSession(HttpSession session) {

        short userTypeId = getCurrentUserTypeIdFromSession(session);
        String userTypeName = "";
        switch (userTypeId){
            case 0  : userTypeName = ServerConstants.POSITION_TYPE_NAME_UNKNOWN;
                      break;
            case 1  : userTypeName = ServerConstants.POSITION_TYPE_NAME_STUDENT;
                      break;
            case 2  : userTypeName = ServerConstants.POSITION_TYPE_NAME_ADVISOR;
                      break;
            case 3  : userTypeName = ServerConstants.POSITION_TYPE_NAME_TEACHER;
                      break;
            case 4  : userTypeName = ServerConstants.POSITION_TYPE_NAME_BRANCH_ASSISTANT;
                      break;
            case 5  : userTypeName = ServerConstants.POSITION_TYPE_NAME_BRANCH_HEAD;
                      break;
            case 6  : userTypeName = ServerConstants.POSITION_TYPE_NAME_HEAD_TEACHER;
                      break;
            case 7  : userTypeName = ServerConstants.POSITION_TYPE_NAME_FINANCE_ADMIN;
                      break;
            case 8  : userTypeName = ServerConstants.POSITION_TYPE_NAME_EDUCATION_ARCHITECT;
                      break;
            case 9  : userTypeName = ServerConstants.POSITION_TYPE_NAME_SHARE_HOLDERS;
                      break;
            case 10 : userTypeName = ServerConstants.POSITION_TYPE_NAME_COMPUTER_ADMIN;
                      break;
            case 11 : userTypeName = ServerConstants.POSITION_TYPE_NAME_CHAIRMAN;
        }
        return userTypeName;
    }

    public static int getCurrentTermId() {

        int currentYear = DateTimeUtil.getCurrentShamsiYear();
        int currentMonth = DateTimeUtil.getCurrentShamsiMonth();
        int currentDay = DateTimeUtil.getCurrentShamsiDay();
        String currentYearStr = String.valueOf(currentYear);

        if(currentDay < ServerConstants.START_DAY_OF_TERM){
            currentMonth--;
        }
        String currentMonthStr = String.valueOf(currentMonth);
        if(currentMonth == 0){
            currentYear--;
            currentYearStr = String.valueOf(currentYear);
            currentMonthStr = ServerConstants.LAST_TERM_MONTH_OF_YEAR;
        }

        if(currentMonthStr.length() == 1){
            currentMonthStr = "0"  + currentMonthStr;
        }
        return Integer.parseInt(currentYearStr + currentMonthStr);
    }

    public static int getNextTermId() {

        int currentYear = DateTimeUtil.getCurrentShamsiYear();
        int currentMonth = DateTimeUtil.getCurrentShamsiMonth();
        int currentDay = DateTimeUtil.getCurrentShamsiDay();
        String currentYearStr = String.valueOf(currentYear);

        if(currentDay >= ServerConstants.START_DAY_OF_TERM){
            currentMonth++;
        }
        String currentMonthStr = String.valueOf(currentMonth);
        if(currentMonth == 13){
            currentYear++;
            currentYearStr = String.valueOf(currentYear);
            currentMonthStr = ServerConstants.FIRST_TERM_MONTH_OF_YEAR;
        }

        if(currentMonthStr.length() == 1){
            currentMonthStr = "0" + currentMonthStr;
        }
        return Integer.parseInt(currentYearStr + currentMonthStr);
    }

    public static int getFirstTermIdOfCurrentYear() {

        String currentYear = String.valueOf(DateTimeUtil.getCurrentShamsiYear());
        return Integer.parseInt(currentYear + ServerConstants.FIRST_TERM_MONTH_OF_YEAR);
    }

    public static int getFirstTermIdOfNextYear() {

        String currentYear = String.valueOf(DateTimeUtil.getCurrentShamsiYear() + 1);
        return Integer.parseInt(currentYear + ServerConstants.FIRST_TERM_MONTH_OF_YEAR);
    }

    public static int getSecondTermIdOfNextYear() {

        String currentYear = String.valueOf(DateTimeUtil.getCurrentShamsiYear() + 1);
        return Integer.parseInt(currentYear + ServerConstants.SECOND_TERM_MONTH_OF_YEAR);
    }

    public static int getLastTermIdOfCurrentYear() {

        String currentYear = String.valueOf(DateTimeUtil.getCurrentShamsiYear());
        return Integer.parseInt(currentYear + ServerConstants.LAST_TERM_MONTH_OF_YEAR);
    }

    public static int getLastTermIdOfPastYear() {

        String currentYear = String.valueOf(DateTimeUtil.getCurrentShamsiYear() - 1);
        return Integer.parseInt(currentYear + ServerConstants.LAST_TERM_MONTH_OF_YEAR);
    }

    public static int getElevenTermIdOfPastYear() {

        String currentYear = String.valueOf(DateTimeUtil.getCurrentShamsiYear() - 1);
        return Integer.parseInt(currentYear + ServerConstants.ELEVEN_TERM_MONTH_OF_YEAR);
    }

    public static int getRegistrationGapTermId(int termId) {
        String termIdStr = String.valueOf(termId);
        if(termIdStr.length() < 6){
            return termId;
        }
        int termYear = Integer.parseInt(termIdStr.substring(0,4));
        int termMonth = Integer.parseInt(termIdStr.substring(4,6));
        termMonth -= ServerConstants.NUMBER_OF_GAP_TERMS;
        String currentMonthStr = String.valueOf(termMonth);
        if(termMonth == 0){
            termYear--;
            currentMonthStr = ServerConstants.LAST_TERM_MONTH_OF_YEAR;
        }

        if(currentMonthStr.length() == 1){
            currentMonthStr = "0"  + currentMonthStr;
        }
        return Integer.parseInt(String.valueOf(termYear) + currentMonthStr);
    }

    public static boolean isCurrentLocalePersian() {

        Locale locale = getCurrentLocale();
        if (locale == null)
            return false;
        return "fa".equals(locale.getLanguage());
    }

    public static boolean isCurrentLocaleEnglish() {

        Locale locale = getCurrentLocale();
        if (locale == null)
            return false;
        return "en".equals(locale.getLanguage());
    }

    public static boolean isPersianLocale(Locale locale) {
        if (locale == null)
            return false;
        return "fa".equals(locale.getLanguage());
    }

    public static boolean isEnglishLocale(Locale locale) {
        if (locale == null)
            return false;
        return "en".equals(locale.getLanguage());
    }

    public static Locale getPersianLocale() {
        return new Locale("fa", "IR");
    }

    public static Locale getEnglishLocale() {
        return new Locale("en", "US");
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("null");
    }

    public static boolean isNullOrEmpty(Object object) {
        return object == null;
    }

    public static boolean isNullOrEmpty(Vector vector) {
        return vector == null || vector.size() == 0;
    }

    public static boolean isNullOrEmpty(Map map) {
        return map == null || map.isEmpty() || map.size() == 0;
    }

    public static boolean isNullOrEmpty(ArrayList arrayList) {
        return arrayList == null || arrayList.size() == 0;
    }

    public static boolean isNullOrEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static String nvl(String mainObject, String targetObject) {

        if (isNullOrEmpty(mainObject))
            return targetObject;
        return mainObject;
    }

    public static String getClientBrowserName() throws Exception {

        String IE = "Internet Explorer";
        String NETSCAPE = "Netscape";
        String OPERA = "Opera";
        String FIREFOX = "Firefox";
        String WML = "WML";
        String UNKNOWN = "Unknown";

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String accept = request.getHeader("ACCEPT");
            String agent = request.getHeader("USER-AGENT");
            if (accept != null && accept.length() > 0 && accept.indexOf("wml") != -1) {

                return "wml browser is: " + agent + " and the MIME TYPE is:  " + accept;
            }
            //if NOT WML, it should be HTML. so check for the browser
            if (agent != null && agent.length() > 0) {

                if (agent.indexOf("Firefox") != -1) {

                    return FIREFOX;
                }
                if (agent.indexOf("Netscape") != -1) {
                    return NETSCAPE;
                }
                if (agent.indexOf("Opera") != -1) {
                    return OPERA;
                }
                if (agent.indexOf("MSIE") != -1) {
                    return IE;
                }
            }
            return UNKNOWN;

        } catch (Exception e) {
            return UNKNOWN;
        }

    }

    public static String getClientIpAddress() throws Exception {

        String UNKNOWN = "Unknown";
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            return request.getRemoteAddr();

        } catch (Exception e) {

            return UNKNOWN;
        }
    }

    public static String getClientSessionId() throws Exception {

        String UNKNOWN = "Unknown";
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            return request.getSession().getId();

        } catch (Exception e) {

            return UNKNOWN;
        }
    }

    public static String getClientUrl() throws Exception {

        String UNKNOWN = "Unknown";
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            return request.getRequestURI();

        } catch (Exception e) {

            return UNKNOWN;
        }
    }

    public static String removeZeroFromCellphone(String cellphone){

        if(cellphone.startsWith("+98")){
            cellphone = cellphone.substring(3);
        }
        else if(cellphone.startsWith("0098")){
            cellphone = cellphone.substring(4);
        }
        else if(cellphone.startsWith("98")){
            cellphone = cellphone.substring(2);
        }
        if(cellphone.startsWith("0")){
            cellphone = cellphone.substring(1);
        }
        return cellphone;
    }

    public static boolean isValidEmailAddress(String email) {

        if (email == null)
            return false;

        email = email.trim();

        return !(email.equals("") || email.indexOf("@") == -1
                || email.indexOf(".") == -1 || email.startsWith("@")
                || email.endsWith("@") || email.startsWith(".") || email.endsWith(".")) && !(email.length() < 6 || email.length() > 50) && (StringUtils.isNumeric(email) || !(email.indexOf(" ") != -1 || email.indexOf("\\") != -1 || email.indexOf(":") != -1
                || email.indexOf("\"") != -1 || email.indexOf(";") != -1 || email.indexOf(",") != -1
                || email.indexOf("<") != -1 || email.indexOf(">") != -1 || email.indexOf("(") != -1
                || email.indexOf(")") != -1 || email.indexOf("*") != -1) && email.lastIndexOf("@") == email.indexOf("@") && email.indexOf("..") == -1 && email.indexOf("/") == -1 && !(email.indexOf("www.") != -1 || email.indexOf("WWW.") != -1));

    }


    public static  boolean  isEnglishCharacter(char chr) {

        if (chr >= 65 && chr <= 90) {
            return true;
        }
        if (chr >= 97 && chr <= 122) {
            return true;
        }
        return false;
    }

    public static  boolean isEnglishText(String txt){
        char chr;
        boolean isEnglish = true;
        for(int i = 0; i < txt.length() && isEnglish; i++){
            chr = txt.charAt(i);
            if(!isEnglishCharacter(chr)) {
                if(chr != 32){          // space character
                    isEnglish = false;
                }
            }
        }
        return isEnglish;
    }

    public static  boolean isPersianCharacter(char chr){

        int farsiKeyCodes[] = {1662, 1670, 1580, 1581, 1582, 1607, 1593, 1594, 1601, 1602, 1603,
                                      1572,1579,1589, 1590, 1711, 1705, 1605, 1606, 1578, 1575, 1604, 1576, 1740, 1587,
                                      1588, 1608, 1574, 1571, 1583, 1584, 1585, 1585, 1586, 1591, 1592, 1570, 1728,1569,
                                      1571, 1573, 1572, 1688, 1610, 1577, 1611, 1612, 1613, 1614, 1615, 1617};
        for (int i = 0; i < farsiKeyCodes.length; i++) {
            if (chr == farsiKeyCodes[i]) {
                return true;
            }
        }
        return false;
    }

    public static  boolean isPersianText(String txt){

        char chr;
        boolean isPersian = true;
        for (int i = 0; i <txt.length() && isPersian; i++) {
            chr = txt.charAt(i);
            if(!isPersianCharacter(chr)){
                if(chr != 32){          // space character
                    isPersian = false;
                }
            }
        }
        return isPersian;
    }

    public static  boolean hasSpecialCharacter(String txt){

        boolean hasSpecialChar = false;
        char chr;
        for(int i=0; i< txt.length() && !hasSpecialChar; i++){

            chr = txt.charAt(i);
            if(isEnglishCharacter(chr) || isPersianCharacter(chr) || StringUtils.isNumeric(Character.toString(chr))){
                continue;
            }

            if(chr > 128 && chr < 255 ){
                hasSpecialChar = true;
            }

            if("33,34,35,36,37,38,39,40,41,42,43,47,58,59,60,61,62,63,64,91,92,93,94,123,124,125,126".indexOf(chr) >= 0){
                hasSpecialChar = true;
            }
        }
        return hasSpecialChar;
    }

    public static String listToString(ArrayList list){

        String result = "";
        if(list == null || list.size() == 0){
            return result;
        }
        for(int i = 0; i< list.size(); i++) {
            Object obj = list.get(i);
            result  = obj.toString() + "\r\n";
        }
        return result;
    }

    public static String getChartTypeName(int chartTypeId){

        switch (chartTypeId){
            case ServerConstants.CHART_TYPE_COLUMN2D     : return "Column2D";
            case ServerConstants.CHART_TYPE_COLUMN3D     : return "Column3D";
            case ServerConstants.CHART_TYPE_LINE         : return "Line";
            case ServerConstants.CHART_TYPE_BAR2D        : return "Bar2D";
            case ServerConstants.CHART_TYPE_PIE2D        : return "Pie2D";
            case ServerConstants.CHART_TYPE_PIE3D        : return "Pie3D";
            case ServerConstants.CHART_TYPE_AREA2D       : return "Area2D";
            case ServerConstants.CHART_TYPE_AREA3D       : return "Area3D";
            case ServerConstants.CHART_TYPE_BUBBLE       : return "";
            case ServerConstants.CHART_TYPE_TIME_LINE    : return "";
            case ServerConstants.CHART_TYPE_TREE         : return "";
            case ServerConstants.CHART_TYPE_DOUGHNUT2D   : return "Doughnut2D";
            case ServerConstants.CHART_TYPE_DOUGHNUT3D   : return "Doughnut3D";
            case ServerConstants.CHART_TYPE_PARETO2D     : return "Pareto2D";
            case ServerConstants.CHART_TYPE_PARETO3D     : return "Pareto3D";
            case ServerConstants.CHART_TYPE_ZOOMLINE     : return "Pareto3D";
            default:return "column2d";
        }
    }
}
