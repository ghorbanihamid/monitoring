<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page import="com.soshiant.common.util.CommonUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<%
    String advisorDisplayStatus = "none";
    short loginUserType = CommonUtil.getCurrentUserTypeIdFromSession();
    if(loginUserType >= ServerConstants.POSITION_TYPE_BRANCH_HEAD){
        advisorDisplayStatus = "";
    }
    advisorDisplayStatus = "";
%>

<s:form namespace="/" id="advisorAllStudentsListInputForm" name="advisorAllStudentsListInputForm" action="advisorAllStudentsListInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 1000px;height: 680px;vertical-align: top; resize: none;">

    <h2 align="center"><s:text name="label.advisorStudentList"/></h2>

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
            <td>
                <div style="height: 20px; text-align: center;" class="ui-widget-header ui-corner-top">
                    <s:text name="label.advisorInformation"/>
                </div>
                <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
                    <table width="100%">

                        <tr class="bodyElementsRows">
                            <td colspan="6" style="height: 25px;">
                                &nbsp;
                            </td>
                        </tr>

                        <tr class="bodyElementsRows">
                            <td colspan="6" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                                <table width="100%">
                                    <tr class="bodyElementsRows" style="display:<%=advisorDisplayStatus%>">

                                        <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="200px">
                                            <s:text name="label.advisorId"/>:
                                        </td>
                                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100px">
                                            <s:textfield id="advisorId" name="advisorId" maxLength="6" theme="simple" cssStyle="width:90px" onkeypress="return numericOnKeyPress(event);"/>
                                        </td>
                                        <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="100px">
                                            <s:text name="label.termName"/>:
                                        </td>
                                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">
                                            <sj:select id="termId"
                                                       name="termId"
                                                       formIds="advisorAllStudentsListInputForm"
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
                                                       tabindex="3"
                                                    >
                                            </sj:select>
                                        </td>
                                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px">
                                            <s:url id="advisorAllStudentsListUrl" namespace="/" action="advisorAllStudentsListJsonAction"/>
                                            <sj:a id="fetchBtn"
                                                  button="true"
                                                  value="%{getText('button.fetch')}"
                                                  href="%{advisorAllStudentsListUrl}"
                                                  formIds="advisorAllStudentsListInputForm"
                                                  targets="advisorDescriptionSpan"
                                                  effect="pulsate"
                                                  effectOptions="{mode:'show' , times:3}"
                                                  effectDuration="1500"
                                                  cssStyle="width: 70px;"
                                                  indicator="indicator1"
                                                  buttonIcon="ui-icon-gear"
                                                  onBeforeTopics="validateAdvisorId"
                                                  onErrorTopics="handleError"
                                                  onCompleteTopics="handleServerError"
                                                  onSuccessTopics="handleJsonResult"
                                                  tabindex="2"
                                                >
                                                    <s:text name="button.fetch"/>
                                                </sj:a>

                                        </td>
                                        <td>
                                            <img id="indicator1" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                                            &nbsp;
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr class="bodyElementsRows">
                            <td colspan="6" style="height: 25px;">
                                &nbsp;
                            </td>
                        </tr>
                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100px">
                                <s:text name="label.advisorInformation"/>:
                            </td>
                            <td colspan="5" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                <span id="advisorDescriptionSpan"></span>
                            </td>
                        </tr>
                        <tr class="bodyElementsRows">
                            <td colspan="6" style="height: 25px;">
                                &nbsp;
                            </td>
                        </tr>
                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100px">
                                <s:text name="advisorAllStudentList.allStudentsCount"/>:
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px">
                                <span id="allStudentsCountSpan"></span>
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px">
                                <s:text name="advisorAllStudentList.regStudentsCount"/>:
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px">
                                <span id="regStudentsCountSpan"></span>
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px">
                                <s:text name="advisorAllStudentList.unRegStudentsCount"/>:
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                <span id="unRegStudentsCountSpan"></span>
                            </td>
                        </tr>

                        <tr class="bodyElementsRows">
                            <td colspan="6" style="height: 25px;">
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
            <td align="center" dir="<s:text name="dir.ltr" />" width="100%">

                <sjg:grid
                        id="studentListGrid"
                        caption="%{getText('label.advisorStudentList')}"
                        dataType="local"
                        formIds="advisorAllStudentsListInputForm"
                        pager="true"
                        gridModel="allStudentsList"
                        groupField="['studentId']"
                        groupColumnShow="[true]"
                        groupCollapse="true"
                        groupText="['<b>{0} - {1} Students(s)</b>']"
                        rowList="20,30,50"
                        rowNum="0"
                        rownumbers="true"
                        draggable="false"
                        resizable="false"
                        resizableAnimate="false"
                        resizableGhost="false"
                        resizableHandles="false"
                        navigator="true"
                        navigatorSearch="true"
                        navigatorEdit="false"
                        navigatorDelete="false"
                        navigatorAdd="false"
                        direction="%{getText('dir.ltr')}"
                        scroll="true"
                        scrollrows="true"
                        pagerButtons="true"
                        width="1000"
                        shrinkToFit="false"
                        >
                    <sjg:gridColumn name="studentId"           index="studentId"           title="%{getText('label.studentId')}"        width="80"  sortable="true"  search="true"  searchoptions="{sopt:['eq']}"/>
                    <sjg:gridColumn name="studentName"         index="studentName"         title="%{getText('label.studentName')}"      width="150" sortable="true"  search="true"  searchoptions="{sopt:['eq','cn']}"/>
                    <sjg:gridColumn name="stdNickName"         index="stdNickName"         title="%{getText('label.nickName')}"         width="150" sortable="true"  search="true"  searchoptions="{sopt:['eq','cn']}"/>
                    <sjg:gridColumn name="className"           index="className"           title="%{getText('label.className')}"        width="150" sortable="false" search="false" />
                    <sjg:gridColumn name="stdRegisterDate"     index="stdRegisterDate"     title="%{getText('label.registerDate')}"     width="150" sortable="false" search="false" formatter="dateFormatter"/>
                    <sjg:gridColumn name="stdCellPhone"        index="stdCellPhone"        title="%{getText('label.cellPhone')}"        width="150" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
                    <sjg:gridColumn name="stdHomePhone"        index="stdHomePhone"        title="%{getText('label.homePhone')}"        width="150" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
                    <sjg:gridColumn name="stdEmailAddress"     index="stdEmailAddress"     title="%{getText('label.emailAddress')}"     width="150" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>

                </sjg:grid>
            </td>
        </tr>
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

        if(getElement("termId").value == 0){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.invalidTerm" />');
            getElement("termId").focus();
            return false;
        }
        return true;

    });
//======================================================================================================================
    $.subscribe('handleServerError', function(event,data) {
//        getElement("advisorDescriptionSpan").innerHTML = "";
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
    $.subscribe('handleError', function(event,data) {
        removeClientErrors();
        addClientError(event.originalEvent.request.statusText);
    });
//======================================================================================================================
    $.subscribe('handleJsonResult', function(event,data) {

        $("#studentListGrid").jqGrid('clearGridData',true);
        getElement("advisorDescriptionSpan").innerHTML = "";
        var jsonObjects = eval('(' + event.originalEvent.data + ')');
        if(jsonObjects != null){
            var advisorInfo = jsonObjects.advisorDesc;
            if(advisorInfo != null){
                getElement("advisorDescriptionSpan").innerHTML = advisorInfo;
            }
            var gridData = jsonObjects.allStudentsList;
            if(gridData != null && gridData.length > 0){
                var studentsCount = gridData.length
                getElement("allStudentsCountSpan").innerHTML = studentsCount;
                var unRegStdCount = jsonObjects.unRegisteredStdCount;
                if(unRegStdCount != null){
                    getElement("unRegStudentsCountSpan").innerHTML = unRegStdCount;
                    getElement("regStudentsCountSpan").innerHTML = studentsCount - unRegStdCount;
                }
                for(var i = 0; i < gridData.length;i++){
                    $("#studentListGrid").jqGrid('addRowData',i+1,gridData[i]);
                }
            }
            else{
                addClientError('<s:text name="error.advisorAllStudentList.advisorDoesNotHaveAnyStudent" />');
            }

        }

    });
//======================================================================================================================
});
//================================================== Java Script =======================================================
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
    function dateFormatter(cellvalue, options, rowObject) {

        var formattedText =addDateSeparator(cellvalue.toString());
        return formattedText;
    }
//======================================================================================================================

</script>