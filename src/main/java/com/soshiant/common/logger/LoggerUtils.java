package com.soshiant.common.logger;

import com.soshiant.server.dao.DaoException;
import org.apache.commons.lang3.ClassUtils;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public abstract class LoggerUtils {

    public static String getExceptionLogMessage(SQLException e) {
        return (new StringBuffer("").append(ClassUtils.getShortClassName(e.getClass())).append(".").append(getExceptionMethodName(e)).append(" on ").append(getExceptionLineNumber(e)).append(" Message: ").append(e.getMessage())).append(" ErrorCode: ").append(e.getErrorCode()).append(" SQLState: ").append(e.getSQLState()).toString();
    }

    public static String getExceptionLogMessage(Exception e) {
        return (new StringBuffer("").append(ClassUtils.getShortClassName(e.getClass())).append(".").append(getExceptionMethodName(e)).append(" on ").append(getExceptionLineNumber(e)).append(" Message: ").append(e.getMessage())).toString();
    }

    public static String getExceptionLogMessage(Throwable e) {
        return (new StringBuffer("").append(ClassUtils.getShortClassName(e.getClass())).append(".").append(getExceptionMethodName(e)).append(" on ").append(getExceptionLineNumber(e)).append(" Message: ").append(e.getMessage())).toString();
    }

    public static String getExceptionLogMessage(DaoException e) {
        return (new StringBuffer("").append(ClassUtils.getShortClassName(e.getClass())).append(".").append(getExceptionMethodName(e)).append(" on ").append(getExceptionLineNumber(e)).append(" Message: ").append(e.getMessage())).toString();
    }

    public static String getExceptionMethodName(Throwable e) {
        return e.getStackTrace()[0].getMethodName() + "()";
    }

    public static String getExceptionLineNumber(Throwable e) {
        return "Line:" + e.getStackTrace()[0].getLineNumber();
    }


}
