<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
%>

<head>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/jquery-ui-themes-1.11.4/ui-redmond/jquery-ui.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/superMenu/super-panel.css" media="screen" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/superMenu/super-panel.js"></script>
</head>
<table width="100%" style="height: 1px;">
    <tr>
        <td>
            <s:div>
                <span data-panel="panel1" class="panel-button"></span>
            </s:div>
            <s:div id="panel1" cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 250px; height: 100%;float:left;vertical-align: top; margin-top: 120px; margin-left: 40px">
                <sj:menu id="leftMenuItems" cssStyle="width:90%; margin-top: 30px;">
                    <sj:menuItem title="%{getText('staff.staff')}">
                        <sj:menu id="staffsSubMenuItems" cssStyle="width:100%">

                            <s:url id="staffInputActionUrl" namespace="/" action="staffInputAction" />
                            <sj:menuItem title="%{getText('staff.registerNewStaff')}" href='%{staffInputActionUrl}'/>

                            <s:url id="dailyShiftInputActionUrl" namespace="/" action="dailyShiftInputAction" />
                            <sj:menuItem title="%{getText('menu.registerNewDailyShift')}" href='%{dailyShiftInputActionUrl}'/>

                            <s:url id="staffListInputActionUrl" namespace="/" action="staffListInputAction" />
                            <sj:menuItem title="%{getText('staff.staffSearch')}" href='%{staffListInputActionUrl}'/>

                        </sj:menu>
                    </sj:menuItem>

                    <sj:menuItem title="%{getText('menu.sitesAndServers')}">
                        <sj:menu id="usersSubMenuItems" cssStyle="width:100%">
                            <s:url id="siteInputActionUrl" namespace="/" action="siteInputAction" />
                            <sj:menuItem title="%{getText('menu.registerSite')}" href='%{siteInputActionUrl}'/>

                            <s:url id="serverInputActionUrl" namespace="/" action="serverInputAction" />
                            <sj:menuItem title="%{getText('menu.registerServer')}" href='%{serverInputActionUrl}'/>

                            <s:url id="rmfConfigInputActionUrl" namespace="/" action="rmfConfigInputAction" />
                            <sj:menuItem title="%{getText('menu.RmfConfig')}" href='%{rmfConfigInputActionUrl}'/>

                            <s:url id="monitorableServersListActionUrl" namespace="/" action="monitorableServersListInputAction" />
                            <sj:menuItem title="%{getText('menu.serverList')}" href='%{monitorableServersListActionUrl}'/>

                            <s:url id="chartReportInputActionUrl" namespace="/" action="chartReportInputAction" />
                            <sj:menuItem title="%{getText('menu.chartReport')}" href='%{chartReportInputActionUrl}'/>

                        </sj:menu>
                    </sj:menuItem>

                    <sj:menuItem title="%{getText('label.users')}">
                        <sj:menu id="usersSubMenuItems" cssStyle="width:100%">
                            <s:url id="changeUserNameInputActionUrl" namespace="/" action="changeUserNameInputAction" />
                            <sj:menuItem title="%{getText('changeUserName.title')}" href='%{changeUserNameInputActionUrl}'/>

                            <s:url id="changePasswordInputActionUrl" namespace="/" action="changePasswordInputAction" />
                            <sj:menuItem title="%{getText('changePassword.title')}" href='%{changePasswordInputActionUrl}'/>

                            <s:url id="changeEmailAddressInputActionUrl" namespace="/" action="changeEmailAddressInputAction" />
                            <sj:menuItem title="%{getText('changeEmailAddress.title')}" href='%{changeEmailAddressInputActionUrl}'/>


                        </sj:menu>
                    </sj:menuItem>

                </sj:menu>
            </s:div>
        </td>
    </tr>
</table>