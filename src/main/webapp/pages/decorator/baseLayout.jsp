<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"     uri="/struts-tags" %>
<%@ taglib prefix="sj"    uri="/struts-jquery-tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Style-Type" content="text/css"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/calendar/calendar-brown.css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/application.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/calendar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/jalali.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/calendar-setup.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/lang/calendar-en.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/lang/calendar-fa.js"></script>

    <s:hidden id="theme" value="redmond" scope="request"/>
    <sj:head compressed="false" jquerytheme="%{theme}" />

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/jquery-ui-themes-1.11.4/ui-redmond/jquery-ui.css" media="screen"/>

</head>

<body>

<table class="baseLayout" align="center" dir="<s:text name="dir.ltr" />">
    <tr>
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
            <tiles:insertAttribute  name="header"/>
        </td>
    </tr>
    <%--<tr>--%>
        <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">--%>
            <%--<tiles:insertAttribute  name="topMenu"/>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <tr>
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
            <s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 705px;vertical-align: top;resize: vertical;">
            <table width="100%">
                <tr>
                    <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                        <tiles:insertAttribute  name="topMenu"/>
                    </td>
                </tr>
                <tr>
                    <%--<td id="leftColumn" class="menuContainer" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">--%>
                         <%--<tiles:insertAttribute  name="leftMenu"/>--%>
                    <%--</td>--%>
                    <td id="rightColumn" class="bodyContainer" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                        <tiles:insertAttribute  name="body"/>
                    </td>
                </tr>
            </table>
            </s:div>
        </td>
    </tr>
    <tr>
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
             &nbsp;
        </td>
    </tr>
    <%--<tr>--%>
        <%--<td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >--%>
            <%--<tiles:insertAttribute  name="footer"/>--%>
        <%--</td>--%>
    <%--</tr>--%>

</table>

</body>

</html>