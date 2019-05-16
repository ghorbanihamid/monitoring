package com.soshiant.server.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 18/11/2015.
 */

public class SessionInterceptor extends AbstractInterceptor implements StrutsStatics {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        ActionContext context = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
        HttpSession session = request.getSession(false);

        // session will almost *never* be null. Check for a valid user object.
        if (session == null) {
            return "timeout";
        }
        return invocation.invoke();
    }
}