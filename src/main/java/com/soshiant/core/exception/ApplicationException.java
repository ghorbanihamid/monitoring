package com.soshiant.core.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cheshmapoosh
 * Date: Dec 9, 2011
 * Time: 10:58:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationException extends NestedRuntimeException {

    private List<ErrorInfo> errorInfoList = new ArrayList<ErrorInfo>();

    public ApplicationException() {
        super();
    }



    public ApplicationException(String msg) {
        super(msg);
    }

    public ApplicationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ErrorInfo addInfo(ErrorInfo info) {
        this.errorInfoList.add(info);
        return info;
    }

    public ErrorInfo addInfo() {
        ErrorInfo info = new ErrorInfo();
        this.errorInfoList.add(info);
        return info;
    }

    public List<ErrorInfo> getErrorInfoList() {
        return errorInfoList;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (StringUtils.isBlank(message)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0, errorInfoListSize = errorInfoList.size(); i < errorInfoListSize; i++) {
                if (i > 0) {
                    sb.append("\n");
                }
                sb.append(i + 1).append("- ");
                ErrorInfo errorInfo = errorInfoList.get(i);
                String desc = errorInfo.getErrorDescription();
                Throwable cause = getCause();
                if (cause != null) {
                    if (desc != null) {
                        sb.append(message).append("; ");
                    }
                    sb.append("nested exception is ").append(cause);
                } else {
                    sb.append(StringUtils.defaultString(message));
                }

            }
            message = sb.toString();
        }
        return message;
    }

    @Override
    public boolean contains(Class exType) {
        if (super.contains(exType)) {
            return true;
        }

        for (ErrorInfo errorInfo : errorInfoList) {
            Throwable cause = errorInfo.getCause();
            if (cause == this) {
                return false;
            }
            if (cause instanceof NestedRuntimeException) {
                return ((NestedRuntimeException) cause).contains(exType);
            } else {
                while (cause != null) {
                    if (exType.isInstance(cause)) {
                        return true;
                    }
                    if (cause.getCause() == cause) {
                        break;
                    }
                    cause = cause.getCause();
                }
                return false;
            }
        }

        return false;
    }
}
