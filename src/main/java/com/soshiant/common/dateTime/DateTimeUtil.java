package com.soshiant.common.dateTime;

import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ServerConstants;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.chrono.GregorianChronology;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

public abstract class DateTimeUtil {


    public static int getCurrentShamsiDate() {

        return Integer.parseInt(ShamsiDateTimeFormat.DATE.getCurrentShamsiDate());
    }

    public static String getCurrentShamsiDateWithSeparator() {

        return addDateSeparator(ShamsiDateTimeFormat.DATE.getCurrentShamsiDate());
    }

    public static int getCurrentGregorianDate() {

        return Integer.parseInt(GregorianDateTimeFormat.DATE.getCurrentShamsiDate());
    }

    public static short getCurrentShamsiYear() {

        return Short.parseShort(ShamsiDateTimeFormat.YEAR.getCurrentShamsiDate());
    }

    public static short getCurrentShamsiShortYear() {

        return Short.parseShort(ShamsiDateTimeFormat.SHORT_YEAR.getCurrentShamsiDate());
    }

    public static short getCurrentShamsiMonth() {

        return Short.parseShort(ShamsiDateTimeFormat.MONTH.getCurrentShamsiDate());
    }

    public static short getCurrentShamsiDay() {

        return Short.parseShort(ShamsiDateTimeFormat.DAY_OF_MONTH.getCurrentShamsiDate());
    }

    public static short getCurrentGregorianYear() {

        return Short.parseShort(GregorianDateTimeFormat.YEAR.getCurrentShamsiDate());
    }

    public static DateTime getCurrentDateTime() {

        return DateTime.now(GregorianChronology.getInstance());
    }

    public static Calendar getCurrentDateTimeCalendar() {

        return Calendar.getInstance();
    }

    public static String getCurrentTime() {

        return GregorianDateTimeFormat.LONG_TIME.getCurrentShamsiDate();
    }

    public static String getCurrentTime(byte timeFormat,boolean separator) {

        if(timeFormat == ServerConstants.TIME_FORMAT_WITH_MINUTE) {
            if(separator) {
                return GregorianDateTimeFormat.SHORT_TIME_COLON.getCurrentShamsiDate();
            }
            else {
                return GregorianDateTimeFormat.SHORT_TIME.getCurrentShamsiDate();
                }
        }
        else {
            if(separator) {
                return GregorianDateTimeFormat.LONG_TIME_COLON.getCurrentShamsiDate();
            }
            else {
                return GregorianDateTimeFormat.LONG_TIME.getCurrentShamsiDate();
            }
        }

    }

    public static String getCurrentTime(boolean separator) {

        if(separator){
            return GregorianDateTimeFormat.LONG_TIME_COLON.getCurrentShamsiDate();
        }
        else{
            return GregorianDateTimeFormat.LONG_TIME.getCurrentShamsiDate();
        }

    }

    public static Timestamp getCurrentTimeStamp() {
        java.util.Date date = new java.util.Date();
        return new Timestamp(date.getTime());
    }

    public static boolean afterTodayDate(Date date) {
        return compareDate(date, new Date()) > 0;
    }

    public static boolean afterOrEqualTodayDate(Date date) {
        return compareDate(date, new Date()) >= 0;
    }

    public static boolean beforeTodayDate(Date date) {
        return compareDate(date, new Date()) < 0;
    }

    public static boolean beforeOrEqualTodayDate(Date date) {
        return compareDate(date, new Date()) <= 0;
    }

    public static int compareDate(Date date1, Date date2) {
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(date1);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);

        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(date2);
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.MILLISECOND, 0);

        return calendar1.compareTo(calendar2);
    }

    public static boolean isValidPersianDate(String persianDateStr)
    {

        if (persianDateStr.length() == 10 && persianDateStr.charAt(4) == '/' && persianDateStr.charAt(7) == '/') {
            String year = persianDateStr.substring(0, 4);
            String month = persianDateStr.substring(5, 7);
            String day = persianDateStr.substring(8, 10);

            if (!StringUtils.isNumeric(year) || !StringUtils.isNumeric(month) || !StringUtils.isNumeric(day))
                return false;

            if (!(Integer.parseInt(month) > 0 && Integer.parseInt(month) < 13) || !(Integer.parseInt(day) > 0 && Integer.parseInt(day) <= 31))
                return false;
        }
        else {
            return false;
        }
        return true;
    }

    public static String addDateSeparator(String sDate) {

        if (StringUtils.isEmpty(sDate) || !StringUtils.isNumeric(sDate)|| Integer.parseInt(sDate) == 0){
            return ServerConstants.EmptyString;
        }
        String tempDate = removeDateSeparator(sDate);
        if (tempDate.length() != 8 ){
            return sDate;
        }
        String sDay = String.valueOf(tempDate).substring(6, 8);
        String sMon = String.valueOf(tempDate).substring(4, 6);
        String sYear = String.valueOf(tempDate).substring(0, 4);

        return sYear + "/" + sMon + "/" + sDay;
    }

    public static String removeDateSeparator(String sDate) {

        if (StringUtils.isEmpty(sDate)){
            return "0";
        }
        else {
            sDate = StringUtils.replace(sDate, "/", "");
        }
        return sDate;
    }

    public static int removeDateSeparators(String sDate) {

        int iDate = 0;
        if (StringUtils.isEmpty(sDate)){
            return iDate;
        }
        else {
            iDate = Integer.parseInt(StringUtils.replace(sDate, "/", ""));
        }
        return iDate;
    }

    public static String addTimeSeparator(String sTime,byte timeFormat) {

        if (StringUtils.isEmpty(sTime) || !StringUtils.isNumeric(sTime)|| Integer.parseInt(sTime) == 0){
            return ServerConstants.EmptyString;
        }
        String tempTime = removeTimeSeparator(sTime);
        if (tempTime.length() != timeFormat ){
            int strLen = tempTime.length();
            for(int i = 0; i < timeFormat - strLen; i++){
                tempTime = '0' + tempTime;
            }
        }
        if(timeFormat == ServerConstants.TIME_FORMAT_WITH_SECOND){
            tempTime = new StringBuffer(tempTime).insert(2, ":").insert(5, ":").toString();
        }
        else if(timeFormat == ServerConstants.TIME_FORMAT_WITH_MINUTE){
            tempTime = new StringBuffer(tempTime).insert(2, ":").toString();
        }
        return tempTime;
    }

    public static String addTimeSeparator(int sTime,byte timeFormat) {

        String tempTime = String.valueOf(sTime);
        if (tempTime.length() != timeFormat ){
            int strLen = tempTime.length();
            for(int i = 0; i < timeFormat - strLen; i++){
                tempTime = '0' + tempTime;
            }
        }
        if(timeFormat == ServerConstants.TIME_FORMAT_WITH_SECOND){
            tempTime = new StringBuffer(tempTime).insert(2, ":").insert(5, ":").toString();
        }
        else if(timeFormat == ServerConstants.TIME_FORMAT_WITH_MINUTE){
            tempTime = new StringBuffer(tempTime).insert(2, ":").toString();
        }
        return tempTime;
    }

    public static String removeTimeSeparator(String sTime) {

        sTime = sTime.trim();
        if (!StringUtils.isEmpty(sTime)){
            sTime = StringUtils.replace(sTime, ":", "");
        }
        return sTime;
    }

    public static int removeTimeSeparators(String sTime) {

        sTime = sTime.trim();
        if (!StringUtils.isEmpty(sTime)){
            sTime = StringUtils.replace(sTime, ":", "");
        }
        return Integer.parseInt(CommonUtil.nvl(sTime,"0"));
    }

    public static boolean isValidTime(String timeStr,ShamsiDateTimeFormat timeFormat)
    {
        timeStr = timeStr.trim();
        if(timeFormat.equals(ShamsiDateTimeFormat.SHORT_TIME_COLON)){
            if (timeStr.length() == 5 && timeStr.charAt(2) == ':') {
                String hour = timeStr.substring(0, 2);
                String minute = timeStr.substring(3, 5);

                if (!StringUtils.isNumeric(hour) || !StringUtils.isNumeric(minute))
                    return false;

                if (!(Integer.parseInt(hour) > 0 ))
                    return false;
            }
            else {
                return false;
            }
        }
        else if(timeFormat.equals(ShamsiDateTimeFormat.TIME_COLON)){
            if (timeStr.length() == 8 && timeStr.charAt(2) == ':' && timeStr.charAt(5) == ':') {
                String hour = timeStr.substring(0, 2);
                String minute = timeStr.substring(3, 5);
                String second = timeStr.substring(6, 8);

                if (!StringUtils.isNumeric(hour) || !StringUtils.isNumeric(minute) || !StringUtils.isNumeric(second))
                    return false;

                if (!(Integer.parseInt(hour) > 0 ))
                    return false;
            }
            else {
                return false;
            }
        }
        return true;
    }

}
