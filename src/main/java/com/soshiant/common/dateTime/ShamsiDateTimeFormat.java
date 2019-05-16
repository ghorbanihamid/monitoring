package com.soshiant.common.dateTime;

import com.behsazan.common.time.chrono.ShamsiChronology;
import org.joda.time.DateTime;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 12/16/11
 * Time: 10:48 AM
 */
public enum ShamsiDateTimeFormat {

    YEAR("yyyy"),
    SHORT_YEAR("yy"),
    MONTH("MM"),
    DAY_OF_MONTH("dd"),
    DAY_OF_YEAR("D"),
    DATE("yyyyMMdd"),
    DATE_TIME("yyyyMMddHHmmss"),
    DATE_TIME_SLASH_COLON("yyyy/MM/dd-HH:mm:ss"),
    TIME("HHmmss"),
    TIME_COLON("HH:mm:ss"),
    SHORT_TIME("HHmm"),
    SHORT_TIME_COLON("HH:mm");

    private String pattern;

    ShamsiDateTimeFormat(String pattern) {
        this.pattern = pattern;
    }

    public String getCurrentShamsiDate() {

        return DateTime.now(ShamsiChronology.getInstance()).toString(pattern);
    }

}
