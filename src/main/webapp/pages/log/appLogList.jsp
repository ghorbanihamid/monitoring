<%@ page import="com.soshiant.common.util.CommonUtil" %>
<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<%
    String branchDisplayStatus = "none";
    short loginUserType = CommonUtil.getCurrentUserTypeIdFromSession();
    if(loginUserType == ServerConstants.POSITION_TYPE_BRANCH_HEAD){
        branchDisplayStatus = "";
    }
%>

<s:form namespace="/" id="appLogListInputForm" action="appLogListJsonAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 680px">

<h2 align="center"><s:text name="label.appLogList"/></h2>

<table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

    <tr>
        <td width="100%">
            <s:div cssStyle="overflow:hidden;height: 20px">
                <s:actionmessage id="serverMessageList" cssClass="actionMessageClass"/>
                <s:actionerror id="serverErrorList" cssClass="actionErrorClass"/>
                <ul id="clientErrorList" class="actionErrorClass"></ul>
            </s:div>
        </td>
    </tr>
    <tr class="bodyElementsRows">
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
            &nbsp;
        </td>
    </tr>
    <tr class="bodyElementsRows">

        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px">
            <div style="height: 20px; text-align: center;" class="ui-widget-header ui-corner-top">
                <s:text name="label.branch"/>
            </div>
            <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
                <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                    <tr class="bodyElementsRows">
                        <td colspan="4" style="height: 25px;">
                             &nbsp;
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">

                        <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="100px">
                            <s:text name="label.userId"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="160px">
                            <s:textfield id="userId" name="userId" maxLength="30" theme="simple" cssStyle="width: 150px;" tabindex="1"/>
                        </td>
                        <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="100px">
                            <s:text name="label.userName"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="160px">
                            <s:textfield id="userName" name="userName" maxLength="30" theme="simple" cssStyle="width: 150px;" tabindex="1"/>
                        </td>
                        <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="100px">
                            <s:text name="label.logDate"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="160px">
                            <s:textfield id="logDate" name="logDate" maxLength="30" theme="simple" cssStyle="width: 150px;" tabindex="2"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px">
                            <input type="button"  value="<s:text name="button.fetch"/>" class="buttonForward" style="width: 70px;" onclick="reloadGrid();" tabindex="3">
                        </td>
                        <td>
                             <img id="indicator1" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td colspan="4" style="height: 25px;">
                             &nbsp;
                        </td>
                    </tr>
                </table>
            </s:div>
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
            &nbsp;
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%" valign="top">

            <s:url id="appLogListUrl" namespace="/" action="appLogListJsonAction"/>
            <sjg:grid   id="appLogListGrid"
                        caption="%{getText('label.appLogList')}"
                        dataType="json"
                        href="%{appLogListUrl}"
                        formIds="appLogListInputForm"
                        pager="true"
                        gridModel="appLogList"
                        rowList="20,30,50"
                        rowNum="0"
                        rownumbers="true"
                        resizable="false"
                        resizableAnimate="false"
                        resizableGhost="false"
                        resizableHandles="false"
                        navigator="true"
                        navigatorSearch="false"
                        navigatorEdit="false"
                        navigatorDelete="false"
                        navigatorAdd="false"
                        shrinkToFit="false"
                        navigatorExtraButtons="{ separator: {title :'separator'},
                                                 hide : {title : '%{getText('button.showHide')}',
                                                         icon: 'ui-icon-wrench',
                                                         topic: 'showHideColumns'}
                                               }"
                        direction="%{getText('dir.ltr')}"
                        scroll="true"
                        scrollrows="true"
                        pagerButtons="true"
                        width="1000"
                        onCompleteTopics="setGridHeight"
                        >

                <sjg:gridColumn name="doerId"              index="doerId"            title="%{getText('appLog.doerId')}"           width="60"  sortable="true"  search="true"   searchoptions="{sopt:['eq']}"/>
                <sjg:gridColumn name="doerType"            index="doerType"          title="%{getText('appLog.doerType')}"         width="60"  sortable="false" search="false"  searchoptions="{sopt:['eq']}"/>
                <sjg:gridColumn name="logDate"             index="logDate"           title="%{getText('appLog.logDate')}"          width="70"  sortable="true"  search="false"  formatter="dateFormatter"/>
                <sjg:gridColumn name="errorCode"           index="errorCode"         title="%{getText('appLog.errorCode')}"        width="60"  sortable="false" search="false"  searchoptions="{sopt:['eq','cn']}"/>
                <sjg:gridColumn name="actionName"          index="actionName"        title="%{getText('appLog.actionName')}"       width="200" sortable="false" search="false"  searchoptions="{sopt:['eq','cn']}"/>
                <sjg:gridColumn name="appMessage"          index="appMessage"        title="%{getText('appLog.appMessage')}"       width="200" sortable="false" search="false"/>
                <sjg:gridColumn name="exceptionMessage"    index="exceptionMessage"  title="%{getText('appLog.exceptionMessage')}" width="200" sortable="false" search="false"  searchoptions="{sopt:['eq','cn']}"/>
                <sjg:gridColumn name="modelValue"          index="modelValue"        title="%{getText('appLog.modelValue')}"       width="200" sortable="false" search="false"  searchoptions="{sopt:['eq','cn']}"/>
                <sjg:gridColumn name="logTime"             index="logTime"           title="%{getText('appLog.logTime')}"          width="150" sortable="false" search="false"  searchoptions="{sopt:['eq','cn']}"/>
                <sjg:gridColumn name="doerIp"              index="doerIp"            title="%{getText('appLog.doerIp')}"           width="70"  sortable="false" search="false"/>
                <sjg:gridColumn name="browserName"         index="browserName"       title="%{getText('appLog.browserName')}"      width="100" sortable="false" search="false"/>
                <sjg:gridColumn name="url"                 index="url"               title="%{getText('appLog.url')}"              width="150" sortable="false" search="false"  searchoptions="{sopt:['eq','cn']}"/>
                <sjg:gridColumn name="sessionId"           index="sessionId"         title="%{getText('appLog.sessionId')}"        width="120" sortable="false" search="false"  searchoptions="{sopt:['eq','cn']}"/>
                <sjg:gridColumn name="sqlText"             index="sqlText"           title="%{getText('appLog.sqlText')}"          width="120" sortable="false" search="false"  searchoptions="{sopt:['eq','cn']}"/>
            </sjg:grid>
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
            &nbsp;
        </td>
    </tr>

</table>

</s:div>

</s:form>
<img id="indicator" src="/images/indicator.gif" alt="Loading..." style="display:none"/>

<script type="text/javascript" language="javascript">

$(document).ready(function () {
//======================================================================================================================
    $.subscribe('handleError', function(event,data) {
        removeClientErrors();
        addClientError(event.originalEvent.request.statusText);
    });
//======================================================================================================================
    $.subscribe('showHideColumns', function(event, data) {
		$.struts2_jquery.require("js/plugins/grid.setcolumns.js");
		$("#appLogListGrid").jqGrid('setColumns', {});
	});
//======================================================================================================================
    $.subscribe('setGridHeight', function(event, data) {
        $("#appLogListGrid").jqGrid('setGridHeight', Math.min(380,parseInt($(".ui-jqgrid-btable").css('height'))));
    });
//======================================================================================================================
});
//================================================== Java Script =======================================================
    function reloadGrid() {

        var gridUrl = $('#appLogListGrid').jqGrid('getGridParam','url');
        var qstIndex= gridUrl.indexOf("?");
        gridUrl = gridUrl.substring(0,qstIndex);
        var userIdVal = $("#userId").val();
        var userNameVal = $("#userName").val();
        var logDateVal = $("#logDate").val();
        var newUrl = gridUrl + "?userId=" + userIdVal + "&userName=" + userNameVal+ "&logDate=" + logDateVal;
        $("#appLogListGrid").jqGrid('setGridParam',{url:newUrl,page:1}).trigger("reloadGrid");
        $("#logDate").focus();
    }
//======================================================================================================================
    function dateFormatter(cellvalue, options, rowObject) {

        var formattedText = "";
        if(cellvalue != null){
            formattedText = addDateSeparator(cellvalue.toString());
        }
        return formattedText;
    }
//======================================================================================================================

</script>

