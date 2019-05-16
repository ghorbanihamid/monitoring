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

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/application.js"></script>

    <s:hidden id="theme" value="redmond" scope="request"/>
    <%--<sj:head jqueryui="true"/>--%>
    <sj:head compressed="false" jquerytheme="%{theme}" />

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/jquery-ui-themes-1.11.4/ui-redmond/jquery-ui.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/virtualKeyboard/keyboard.css" >

</head>

<body>

<table class="homeLayout" align="center" dir="<s:text name="dir.ltr" />">
    <tr>
        <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
            <tiles:insertAttribute  name="header"/>
        </td>
    </tr>

    <tr>
        <td id="rightColumn" colspan="2" class="bodyContainer" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
            <tiles:insertAttribute  name="body"/>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
            <tiles:insertAttribute  name="footer"/>
        </td>
    </tr>

</table>

</body>

</html>






<%--<html>--%>

<%--<head>--%>
    <%--<title>Simple jsp page</title>--%>

    <%--<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css" />--%>
    <%--<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sideBarMenu_en.css" type="text/css" />--%>

    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.6.2.js"></script>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/cufon-yui.js"></script>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/arial.js"></script>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/cuf_run.js"></script>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/sideBarMenu.js"></script>--%>

<%--</head>--%>

<%--<body>--%>
<%--<div  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">--%>

    <%--<div align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">--%>
        <%--<jsp:include page="/pages/decorator/header.jsp"/>--%>
    <%--</div>--%>
    <%--<div class="spaceBeforeFooter"></div>--%>
    <%--<div class="content" dalign="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">--%>

        <%--<div class="sidebar" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">--%>
            <%--&lt;%&ndash;<tiles:insertAttribute  name="menu"/>&ndash;%&gt;--%>
        <%--</div>--%>
        <%--<div class="mainbar" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">--%>
            <%--<jsp:include page="/pages/login/homeLogin.jsp"/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="spaceBeforeFooter"></div>--%>
    <%--<div align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">--%>
        <%--<jsp:include page="/pages/decorator/footer.jsp"/>--%>
    <%--</div>--%>

<%--</div>--%>
<%--</body>--%>

<%--</html>--%>