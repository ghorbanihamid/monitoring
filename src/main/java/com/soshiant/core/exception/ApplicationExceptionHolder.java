package com.soshiant.core.exception;

/**
 * Created by IntelliJ IDEA.
 * User: home
 * Date: 1/27/12
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationExceptionHolder {
    private static final ThreadLocal<ApplicationException> holder = new ThreadLocal<ApplicationException>();

    public static void clear() {
        holder.set(null);
    }

    public static ApplicationException getContext() {
        ApplicationException appEx = holder.get();

        if (appEx == null) {
            appEx = createEmptyApplicationException();
            holder.set(appEx);
        }

        return appEx;
    }

    public static void setContext(ApplicationException applicationException) {
        if (applicationException == null){
            throw new NullPointerException("Only non-null ApplicationException instances are permitted");
        }
        holder.set(applicationException);
    }

    private static ApplicationException createEmptyApplicationException() {
        return new ApplicationException();
    }
}
