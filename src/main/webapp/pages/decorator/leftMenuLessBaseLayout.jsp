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

    <!-- This files are needed for AJAX Validation of XHTML Forms -->
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/struts/utils.js"></script>--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/struts/xhtml/validation.js"></script>--%>


    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/calendar/calendar-brown.css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/application.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/fg.menu.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/calendar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/jalali.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/calendar-setup.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/lang/calendar-en.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/lang/calendar-fa.js"></script>

    <s:hidden id="theme" value="lightness" scope="request"/>
    <sj:head compressed="false" jquerytheme="%{theme}" />

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/jquery-ui-themes-1.10.3/ui-lightness/jquery-ui.css" media="screen"/>
</head>

<body>

<table class="baseLayout" align="center" dir="<s:text name="dir.ltr" />">
    <tr>
        <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
            <tiles:insertAttribute  name="header"/>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
            <tiles:insertAttribute  name="topMenu"/>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
            <table>
                <tr>
                    <td id="rightColumn" class="bodyContainer" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                        <tiles:insertAttribute  name="body"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
            &nbsp;
        </td>
    </tr>
    <%--<tr>--%>
    <%--<td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >--%>
    <%--<tiles:insertAttribute  name="footer"/>--%>
    <%--</td>--%>
    <%--</tr>--%>

</table>

<script type="text/javascript">
    //    Make it possible to link to an specific site in the Showcase
    //    e.g. and link to index.action?ajaxhistory=true\ #main=_sj_action_accordionlink
    //    opens now the accordion examples
    //    jQuery(document).ready(function() {
    //        var theme = $('#theme').val();
    //        $('#jquery_theme_link').attr('href', 'themes/' + theme + '/jquery-ui.css');
    //        if (jQuery.struts2_jquery.ajaxhistory) {
    //            var topic = $.bbq.getState('main');
    //            if (topic !== undefined && topic != '') {
    //                jQuery.publish(topic);
    //            }
    //        }
    //    });
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.maskedinput-1.3.min.js"></script>

</body>

</html>