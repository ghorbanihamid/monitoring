package com.soshiant.common.dateTime;

import org.joda.time.DateTime;
import org.joda.time.chrono.GregorianChronology;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 12/16/11
 * Time: 10:48 AM
 */
public enum GregorianDateTimeFormat {

    YEAR("yyyy"),
    MONTH("MM"),
    DAY_OF_MONTH("dd"),
    DAY_OF_YEAR("D"),
    DATE("yyyyMMdd"),
    DATE_TIME("yyyyMMddHHmmss"),
    DATE_TIME_SLASH_COLON("yyyy/MM/dd-HH:mm:ss"),
    LONG_TIME("HHmmss"),
    LONG_TIME_COLON("HH:mm:ss"),
    SHORT_TIME("HHmm"),
    SHORT_TIME_COLON("HH:mm");

    private String pattern;

    GregorianDateTimeFormat(String pattern) {
        this.pattern = pattern;
    }

    public String getCurrentShamsiDate() {

        return DateTime.now(GregorianChronology.getInstance()).toString(pattern);
    }

}
