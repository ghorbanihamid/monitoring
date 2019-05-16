<%@ page import="com.soshiant.common.util.BundleUtil" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<%
    BundleUtil bundle = BundleUtil.getInstance();
    String direction = bundle.getMessage("dir.ltr");
%>

<form action="j_spring_security_check" namespace="/" method="POST" validate="true">
<div>
    <table border="0" cellpadding="0" cellspacing="0" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

        <tr class="bodySpaceRow">
            <td colspan="2" align="center" dir="<s:text name="dir.ltr" />" >
                &nbsp;
            </td>
        </tr>

        <!--  errors -->
        <tr>
            <td colspan="2" class="actionErrorClass" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
                <s:actionerror/>
            </td>
        </tr>
        <tr class="bodySpaceRow">
            <td colspan="2" align="center" dir="<s:text name="dir.ltr" />" >
                &nbsp;
            </td>
        </tr>

        <tr class="bodyHeaderRow">
            <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
                &nbsp;&nbsp;&nbsp;<s:text name="login.loginMessage"/>
            </td>
        </tr>

        <tr class="bodySpaceRow">
            <td colspan="2" align="center" dir="<s:text name="dir.ltr" />" >
                &nbsp;
            </td>
        </tr>
        <tr class="bodyElementsRows">
            <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" valign="middle" width="45%">
                <s:text name="login.userName"/>
            </td>
            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                <s:textfield name="j_username" theme="simple" title="Username" size="20" maxlength="30" />
            </td>
        </tr>
        <tr class="bodyElementsRows">
            <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" valign="middle" width="45%">
                <s:text name="login.password"/>
            </td>
            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                <s:password name="j_password" theme="simple" title="Password" size="20" maxlength="30" />
            </td>
        </tr>

        <tr>
            <td colspan="2">
                &nbsp;
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center" dir="<s:text name="dir.ltr" />" >
                <a href="#" style="margin-left:30px;"><s:text name="login.forgotPassword"/></a>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                &nbsp;
            </td>
        </tr>

        <tr>
            <td colspan="2" class="bodyDividerRow">
                &nbsp;
            </td>
        </tr>

        <!-- buttons-->
        <tr class="bodyButtonRow" dir="<s:text name="dir.ltr" />">
            <td colspan="4" dir="<s:text name="dir.ltr" />">
                <table border="0" align="center" dir="<s:text name="dir.ltr" />">
                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <s:submit key="button.login" id="loginButton" style="WIDTH:75px" styleClass="buttonFwd" tabindex="9"/>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>

    </table>
</div>
</form>

<script type="text/javascript" language="javascript">

    $("#loginButton").submit(function (){

        if($("#j_username").isNullOrEmpty()){
            alert('<s:text name="error.login.userNameRequired" />');
            return false;
        }
        if($("#j_password").isNullOrEmpty()){
            alert('<s:text name="error.login.passwordRequired" />');
            return false;
        }
        return true;
    });

</script>




<%--<div id="login-box" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">--%>

    <%--<form action="loginAction.action" id="loginActionForm" method="POST">--%>
        <%--<H2><s:text name="login.loginMessage"/></H2>--%>
        <%--<br />--%>
        <%--<br />--%>
        <%--<div class="loginBox-fieldName" dir="<s:text name="dir.ltr" />"><s:text name="login.userName"/></div>--%>
        <%--<div class="loginBox-fieldValue" >--%>
            <%--<s:textfield name="j_username" theme="simple" cssClass="form-login" title="Username" size="30" maxlength="30" />--%>
        <%--</div>--%>

        <%--<div class="loginBox-fieldName"><s:text name="login.password"/></div>--%>
        <%--<div class="loginBox-fieldValue">--%>
            <%--<s:password name="j_password" theme="simple" cssClass="form-login" title="Password"  size="30" maxlength="50" />--%>
        <%--</div>--%>
        <%--<br />--%>
        <%--<span class="login-box-options"><a href="#" style="margin-left:30px;"><s:text name="login.forgotPassword"/></a></span>--%>
        <%--<br />--%>
        <%--<br />--%>
        <%--&lt;%&ndash;<input type="submit" class="loginButton" value="" />&ndash;%&gt;--%>
        <%--<input type="button" class="loginButton" style="background: url(<s:text name="login.loginBtnImage" />) no-repeat" />--%>
        <%--&lt;%&ndash;<s:submit key="button.cancel" style="WIDTH:75px"  styleClass="buttonBack" tabindex="10" onclick="return returnToWorkSheet();"/>&ndash;%&gt;--%>
    <%--</form>--%>

<%--</div>--%>

