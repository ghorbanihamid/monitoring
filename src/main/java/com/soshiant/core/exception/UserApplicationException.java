package com.soshiant.core.exception;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: home
 * Date: 1/27/12
 * Time: 12:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserApplicationException extends ApplicationException {

    private ApplicationException cause;

    public UserApplicationException(ApplicationException cause) {
        super(cause.getMessage(), cause);
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder();
        List<ErrorInfo> errorInfoList = cause.getErrorInfoList();
        for (int i = 0, errorInfoListSize = errorInfoList.size(); i < errorInfoListSize; i++) {
            if (i > 0){
                message.append("\n");
            }
            ErrorInfo errorInfo = errorInfoList.get(i);
            message.append(errorInfo.getUserErrorDescription());
        }
        return message.toString();

    }

    @Override
    public ErrorInfo addInfo(ErrorInfo info) {
        return cause.addInfo(info);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public ErrorInfo addInfo() {
        return cause.addInfo();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<ErrorInfo> getErrorInfoList() {
        return cause.getErrorInfoList();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean contains(Class exType) {

        return cause.contains(exType);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
