<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page import="com.soshiant.common.util.CommonUtil" %>
<%@ page import="com.soshiant.server.model.student.AdvisorStudentsInfo" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<%
    List<AdvisorStudentsInfo> tempStudentsList = (List<AdvisorStudentsInfo>) request.getAttribute(ServerConstants.ADVISOR_ALL_STUDENTS_LIST_VECTOR);
    String advisorDisplayStatus = "none";
    short loginUserType = CommonUtil.getCurrentUserTypeIdFromSession();
    if(loginUserType >= ServerConstants.POSITION_TYPE_BRANCH_HEAD){
        advisorDisplayStatus = "";
    }
    advisorDisplayStatus = "";
%>

<s:form namespace="/" id="advisorRegStudentsListInputForm" name="advisorRegStudentsListInputForm" action="advisorRegStudentsListInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 1000px;height: 680px;vertical-align: top; resize: none;">

<h2 align="center"><s:text name="advisorFinanceReport.title"/></h2>

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
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
            <div style="height: 20px; text-align: center;" class="ui-widget-header ui-corner-top">
                <s:text name="label.advisorInformation"/>
            </div>
            <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
                <table width="100%">

                    <tr class="bodyElementsRows">
                        <td colspan="4" style="height: 25px;">
                             &nbsp;
                        </td>
                    </tr>

                    <tr class="bodyElementsRows" style="display:<%=advisorDisplayStatus%>">

                        <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="200px">
                            <s:text name="label.advisorId"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="110px">
                            <s:textfield id="advisorId" name="advisorId" maxLength="6" theme="simple" cssStyle="width:100px" onkeypress="return numericOnKeyPress(event);"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px">
                            <s:url id="advisorNameUrl" namespace="/" action="advisorNameForRegStudentListJsonAction"/>
                            <sj:submit button="true"
                                       value="%{getText('button.fetch')}"
                                       formIds="advisorRegStudentsListInputForm"
                                       href="%{advisorNameUrl}"
                                       targets="advisorDescriptionSpan"
                                       effect="pulsate"
                                       effectOptions="{mode:'show' , times:3}"
                                       effectDuration="1500"
                                       cssStyle="width: 50px;"
                                       cssClass="buttonForward"
                                       indicator="indicator1"
                                       onBeforeTopics="validateForFetch"
                                       onErrorTopics="handleError"
                                       onCompleteTopics="handleServerError"
                                       tabindex="2"/>
                        </td>
                        <td>
                            <img id="indicator1" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                            &nbsp;
                            <span id="advisorDescriptionSpan"></span>
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="15%">
                            <s:text name="label.termName"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">

                            <sj:select id="termId"
                                       name="termId"
                                       href="termsListForPastMonthsNextTermAction.action"
                                       list="termsListForPastMonthsNextTerm"
                                       listKey="termId"
                                       listValue="termName"
                                       dataType="json"
                                       cssClass="commonElement"
                                       cssStyle="height: 20px;width: 94%"
                                       headerKey="0"
                                       headerValue="%{getText('label.notDefined')}"
                                       emptyOption="false"
                                       onChangeTopics="fillRegStudentsData"
                                       tabindex="3"
                                    >
                            </sj:select>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px">

                            <s:submit id="reportButton" name="reportButton" key="button.getReport" formIds="advisorRegStudentsListInputForm" action="advisorRegStudentsListResultAction" cssStyle="width: 75px;" cssClass="buttonForward" tabindex="4"/>

                            <%--<s:url id="advisorRegStudentsListUrl" namespace="/" action="advisorRegStudentsListJsonAction"/>--%>
                            <%--<sj:submit button="true"--%>
                                       <%--value="%{getText('button.fetch')}"--%>
                                       <%--formIds="advisorRegStudentsListInputForm"--%>
                                       <%--href="%{advisorRegStudentsListUrl}"--%>
                                       <%--targets="sumStudentsFee"--%>
                                       <%--effect="pulsate"--%>
                                       <%--effectOptions="{mode:'show' , times:3}"--%>
                                       <%--effectDuration="1500"--%>
                                       <%--cssStyle="width: 50px;"--%>
                                       <%--cssClass="buttonForward"--%>
                                       <%--indicator="indicator2"--%>
                                       <%--onBeforeTopics="validateForFetch"--%>
                                       <%--onErrorTopics="handleError"--%>
                                       <%--onCompleteTopics="handleServerError"--%>
                                       <%--onSuccessTopics="fillRegStudentsData"--%>
                                       <%--tabindex="2"/>--%>
                        </td>
                        <td>
                            <img id="indicator2" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                            &nbsp;
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td colspan="4" style="height: 25px;">
                             &nbsp;
                        </td>
                    </tr>

                    <%--<tr class="bodyElementsRows">--%>
                        <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100px">--%>
                            <%--<s:text name="advisorFinanceReport.sumStudentsFee"/>:--%>
                        <%--</td>--%>
                        <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100px">--%>
                            <%--<span id="sumStudentsFee"></span>--%>
                        <%--</td>--%>
                        <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100px">--%>
                            <%--<s:text name="advisorFinanceReport.sumStudentsFee"/>:--%>
                        <%--</td>--%>
                        <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >--%>
                            <%--<span id="advisorPortion"></span>--%>
                        <%--</td>--%>
                    <%--</tr>--%>

                </table>
            </s:div>
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
            &nbsp;
        </td>
    </tr>
    <%--<tr class="bodyElementsRows">--%>
        <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">--%>

            <%--<s:url id="advisorRegStudentsListUrl" namespace="/" action="advisorRegStudentsListJsonAction"/>--%>
            <%--<sjg:grid--%>
                    <%--id="studentListGrid"--%>
                    <%--caption="%{getText('label.advisorStudentList')}"--%>
                    <%--dataType="json"--%>
                    <%--href="%{advisorRegStudentsListUrl}"--%>
                    <%--formIds="advisorRegStudentsListInputForm"--%>
                    <%--pager="true"--%>
                    <%--gridModel="studentList"--%>
                    <%--rowList="20,30,50"--%>
                    <%--rowNum="0"--%>
                    <%--rownumbers="true"--%>
                    <%--draggable="false"--%>
                    <%--resizable="false"--%>
                    <%--resizableAnimate="false"--%>
                    <%--resizableGhost="false"--%>
                    <%--resizableHandles="false"--%>
                    <%--navigator="true"--%>
                    <%--navigatorSearch="true"--%>
                    <%--navigatorEdit="false"--%>
                    <%--navigatorDelete="false"--%>
                    <%--navigatorAdd="false"--%>
                    <%--direction="%{getText('dir.ltr')}"--%>
                    <%--scroll="true"--%>
                    <%--scrollrows="true"--%>
                    <%--pagerButtons="true"--%>
                    <%--width="1000"--%>
                    <%--reloadTopics="fillRegStudentsData"--%>
                    <%--onCompleteTopics="calculateAmountField"--%>
                    <%-->--%>
                <%--<sjg:gridColumn name="studentId"           index="studentId"           title="%{getText('label.studentId')}"        width="80"  sortable="true"  search="true"  searchoptions="{sopt:['eq']}" formatter="integer"/>--%>
                <%--<sjg:gridColumn name="studentName"         index="studentName"         title="%{getText('label.studentName')}"      width="150" sortable="true"  search="true"  searchoptions="{sopt:['eq','cn']}"/>--%>
                <%--<sjg:gridColumn name="stdNickName"         index="stdNickName"         title="%{getText('label.nickName')}"         width="150" sortable="true"  search="true"  searchoptions="{sopt:['eq','cn']}"/>--%>
                <%--<sjg:gridColumn name="classFee"            index="classFee"            title="%{getText('label.classFee')}"         width="150" sortable="false" search="false" />--%>
                <%--<sjg:gridColumn name="stdReceiptNo"        index="stdReceiptNo"        title="%{getText('label.receiptNo')}"        width="150" sortable="false" search="false" />--%>
                <%--<sjg:gridColumn name="className"           index="className"           title="%{getText('label.className')}"        width="150" sortable="false" search="false" />--%>
                <%--<sjg:gridColumn name="stdRegisterDate"     index="stdRegisterDate"     title="%{getText('label.registerDate')}"     width="150" sortable="false" search="false" formatter="dateFormatter"/>--%>
                <%--<sjg:gridColumn name="stdCellPhone"        index="stdCellPhone"        title="%{getText('label.cellPhone')}"        width="150" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>--%>
                <%--<sjg:gridColumn name="stdHomePhone"        index="stdHomePhone"        title="%{getText('label.homePhone')}"        width="150" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>--%>
                <%--<sjg:gridColumn name="stdEmailAddress"     index="stdEmailAddress"     title="%{getText('label.emailAddress')}"     width="150" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>--%>
            <%--</sjg:grid>--%>
        <%--</td>--%>
    <%--</tr>--%>
</table>

</s:div>

</s:form>


<script type="text/javascript" language="javascript">

$(document).ready(function () {
//======================================================================================================================
    $.subscribe('validateForFetch', function(event,data) {

        removeClientErrors();
        getElement("advisorDescriptionSpan").innerHTML = "";

        var elmValue = getElement("advisorId").value;

        if(isNullOrEmpty(elmValue)){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.advisorIdRequired" />');
            getElement("advisorId").focus();
            return false;
        }
        else if(!isNumeric(elmValue)){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.invalidAdvisorId" />');
            getElement("advisorId").focus();
            return false;
        }
        else if(elmValue == 0){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.invalidAdvisorId" />');
            getElement("advisorId").focus();
            return false;
        }

        return true;

    });
//======================================================================================================================
    $.subscribe('handleServerError', function(event,data) {

        removeClientErrors();
        var jsonObjects = eval('(' + event.originalEvent.data + ')');
        if(jsonObjects != null){
            var errorMessage = "";
            if(jsonObjects.actionErrors != null){
                $.each(jsonObjects.actionErrors, function(e) {
                    errorMessage += jsonObjects.actionErrors;
                });
                addClientError(errorMessage);
            }
            if(jsonObjects.actionMessages != null){
                $.each(jsonObjects.actionErrors, function(e) {
                    errorMessage += jsonObjects.actionMessages;
                });
                addClientError(errorMessage);
            }
        }
    });
//======================================================================================================================
//    $.subscribe('fillRegStudentsData', function(event,data) {
//
//        var jsonObjects = eval('(' + event.originalEvent.data + ')');
//        if(jsonObjects != null){
//            if(jsonObjects.sumStudentsFee != null){
//                getElement("sumStudentsFee").innerHTML = jsonObjects.sumStudentsFee;
//            }
//        }
//
//    });
//======================================================================================================================
    $.subscribe('calculateAmountField', function(data) {

//        var gridData = $("#studentListGrid").jqGrid('getGridParam','data');
//        var gridSelRow = $("#studentListGrid").jqGrid('getGridParam','selrow');
        alert(data);
        if(gridData != null){
            var sumClassFee = 0;
            for(var i = 0; i < gridData.length; i++ ){
                var rowData = gridData[i];
                sumClassFee += rowData.classFee;
                var gridSelRow = $("#studentListGrid").jqGrid('getGridParam','selrow');
//                var rowData = jQuery('#grid').jqGrid('getRowData', gridSelRow);
//                sumClassFee += rowData.classFee;
            }
        }

    });
//======================================================================================================================
    $.subscribe('handleError', function(event,data) {
        removeClientErrors();
        addClientError(event.originalEvent.request.statusText);
    });
//======================================================================================================================
//======================================================================================================================
});
//================================================== Java Script =======================================================
    function dateFormatter(cellvalue, options, rowObject) {

        var formattedText =addDateSeparator(cellvalue.toString());
        return formattedText;
    }
//======================================================================================================================
    function handleServerError(jsonObjects) {

        removeClientErrors();
        if(jsonObjects != null){
            var errorMessage = "";
            if(jsonObjects.actionErrors != null){
                $.each(jsonObjects.actionErrors, function(e) {
                    errorMessage += jsonObjects.actionErrors;
                });
                addClientError(errorMessage);
            }
            if(jsonObjects.actionMessages != null){
                $.each(jsonObjects.actionErrors, function(e) {
                    errorMessage += jsonObjects.actionMessages;
                });
                addClientError(errorMessage);
            }
        }
    }
//======================================================================================================================

</script>