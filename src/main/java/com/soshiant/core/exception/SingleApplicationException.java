package com.soshiant.core.exception;

/**
 * Created by IntelliJ IDEA.
 * User: home
 * Date: 1/27/12
 * Time: 11:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class SingleApplicationException extends ApplicationException {
    public SingleApplicationException() {
        super();
    }

    @Override
    public ErrorInfo addInfo(ErrorInfo info) {
        super.getErrorInfoList().clear();
        return super.addInfo(info);
    }

    @Override
    public ErrorInfo addInfo() {
        super.getErrorInfoList().clear();
        return super.addInfo();
    }
}
