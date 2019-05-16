<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"     uri="/struts-tags" %>
<%@ taglib prefix="sj"    uri="/struts-jquery-tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%
%>

<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Style-Type" content="text/css"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/application.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/jquery.calendars.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/jquery.calendars.plus.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/jquery.calendars.picker.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/jquery.calendars.persian.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/jquery.calendars.picker-fa.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.maskedinput-1.3.min.js"></script>

    <script type="text/javascript" src="js/fg.menu.js"></script>

    <link rel="stylesheet" type="text/css" href="styles/jquery.calendars.picker.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="styles/layout.css"/>
    <link rel="stylesheet" type="text/css" href="styles/patch_layout.css"/>
    <link type="text/css"  href="styles/fg.menu.css" media="screen" rel="stylesheet"/>

    <s:hidden id="theme" value="sunny" scope="request"/>
    <%--<sj:head jqueryui="true"/>--%>
    <sj:head compressed="false" jquerytheme="%{theme}" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/jquery-ui-themes-1.10.3/ui-lightness/jquery-ui.css" media="screen"/>

</head>

<body>

<table class="sessionBaseLayout" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
    <tr>
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%" >
            <tiles:insertAttribute  name="header"/>
        </td>
    </tr>
    <tr>
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%" >
            <div id="nav" class="hlist ui-widget-header">
                <div style="float: left; margin-top: 1px; margin-bottom: 1px;">
                    <%--<ul>--%>
                       <%--<li>--%>
                           <%--<a tabindex="101" href='<s:url namespace="/" action="dailyTeacherClassesInputAction" />' class="ui-widget ui-state-default"><span style="align: center"><s:text name="dailySessionHolding.title"/></span></a>--%>
                       <%--</li>--%>
                    <%--</ul>--%>
                </div>
                <div style="float: right; margin-top: 1px; margin-bottom: 1px;">
                    <ul>
                       <li>
                           <a tabindex="102" href='<s:url value="/logout" />' class="ui-widget ui-state-default"><span style="align: center"><s:text name="menu.subTitle.logOut"/></span></a>
                       </li>
                    </ul>
                </div>
            </div>
        </td>
    </tr>
    <tr>
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%" >
            <tiles:insertAttribute  name="body"/>
        </td>
    </tr>
    <tr>
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%" >
             &nbsp;
        </td>
    </tr>

</table>

<script type="text/javascript">

</script>

</body>

</html>