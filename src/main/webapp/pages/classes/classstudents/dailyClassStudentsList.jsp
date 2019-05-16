<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page import="com.soshiant.common.util.CommonUtil" %>
<%@ page import="com.soshiant.server.action.file.FileDownloadAction" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<%
    String branchDisplayStatus = "none";
    short loginUserType = CommonUtil.getCurrentUserTypeIdFromSession();
    if(loginUserType >= ServerConstants.POSITION_TYPE_BRANCH_HEAD){
        branchDisplayStatus = "";
    }
    branchDisplayStatus = "";
    String fileName = "";
%>

<s:form namespace="/" id="dailyClassStudentsListInputForm" action="dailyClassStudentsListInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 1000px;height: 680px;vertical-align: top; resize: none;">

<h2><s:text name="label.dailyClassStudentsList"/></h2>

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
                <s:text name="label.dailyClassInformation"/>
            </div>
            <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
                <table width="100%">

                    <tr class="bodyElementsRows">
                        <td colspan="8" style="height: 25px;">
                             &nbsp;
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">

                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px">
                            <span style="display:<%=branchDisplayStatus%>">
                                <s:text name="label.branch"/>
                            </span>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="160px">
                            <span style="display:<%=branchDisplayStatus%>">
                                <sj:select id="branchId"
                                           name="branchId"
                                           href="activeBranchesListAction.action"
                                           formIds="dailyClassStudentsListInputForm"
                                           list="activeBranchesList"
                                           listKey="branchId"
                                           listValue="branchName"
                                           dataType="json"
                                           cssClass="commonElement"
                                           cssStyle="height:20px; width:150px"
                                           headerKey="0"
                                           headerValue="%{getText('label.notDefined')}"
                                           emptyOption="false"
                                           tabindex="1"
                                    >
                                </sj:select>
                            </span>
                        </td>

                        <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="50px">
                            <s:text name="label.termName"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="110px">

                            <sj:select id="termId"
                                       name="termId"
                                       href="termsListForPastMonthsNextTermAction.action"
                                       formIds="dailyClassStudentsListInputForm"
                                       list="termsListForPastMonthsNextTerm"
                                       listKey="termId"
                                       listValue="termName"
                                       dataType="json"
                                       cssClass="commonElement"
                                       cssStyle="height:20px; width:100px"
                                       headerKey="0"
                                       headerValue="%{getText('label.notDefined')}"
                                       emptyOption="false"
                                       onChangeTopics="termSelected"
                                       tabindex="2"
                                    >
                            </sj:select>
                        </td>

                        <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="50px">
                            <s:text name="label.class"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="360px">

                            <sj:select id="classId"
                                       name="classId"
                                       href="dailyClassListOfTermJsonAction.action"
                                       formIds="dailyClassStudentsListInputForm"
                                       list="classListOfTerm"
                                       listKey="stringClassId"
                                       listValue="className"
                                       dataType="json"
                                       cssClass="commonElement"
                                       cssStyle="height:20px; width:350px"
                                       headerKey="0"
                                       headerValue="%{getText('label.notDefined')}"
                                       emptyOption="false"
                                       reloadTopics="termSelected"
                                       tabindex="3"
                                    >
                            </sj:select>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px">
                            <s:url id="dailyClassInformationUrl" namespace="/" action="dailyClassStudentsClassInfoJsonAction"/>
                            <sj:a id="fetchClassInfoBtn"
                                       button="true"
                                       value="%{getText('button.fetch')}"
                                       href="%{dailyClassInformationUrl}"
                                       targets="classInformationSpan"
                                       formIds="dailyClassStudentsListInputForm"
                                       effect="pulsate"
                                       effectDuration="1500"
                                       cssStyle="width: 70px;"
                                       buttonIcon="ui-icon-gear"
                                       cssClass="buttonForward"
                                       indicator="indicator1"
                                       onBeforeTopics="validateForFetch"
                                       onSuccessTopics="fillClassStudentListGrid"
                                       onErrorTopics="handleServerError"
                                       tabindex="4"
                                >
                                <s:text name="button.fetch"/>
                            </sj:a>
                        </td>
                        <td>
                             <img id="indicator1" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td colspan="8" style="height: 25px;">
                             <span id="classInformationSpan"></span>
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td colspan="8" style="height: 25px;">
                             &nbsp;
                        </td>
                    </tr>

                </table>
            </s:div>
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="100%">
            <s:url action="printDailyClassStudentsListAction" var="printUrlTag" >
            </s:url>
            <a href="javascript:printReport()">
                <img src="<s:text name="monitoring.printImage" />" alt="print friendly" width="20" height="20" border="0"/>&nbsp;
                <s:text name="button.print"/>
            </a>

        </td>
    </tr>
    <tr class="bodyElementsRows">
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

            <s:url id="dailyClassStudentListUrl" namespace="/" action="dailyClassStudentsListJsonAction"/>
            <%--navigatorCloneToTop="true"--%>
            <sjg:grid
                    id="dailyClassStudentsGrid"
                    caption="%{getText('label.dailyClassStudentsList')}"
                    direction="%{getText('dir.ltr')}"
                    dataType="json"
                    href="%{dailyClassStudentListUrl}"
                    formIds="dailyClassStudentsListInputForm"
                    gridModel="gridModel"
                    rowList="10,15,20"
                    rowNum="15"
                    pager="true"
                    toppager="true"
                    pagerButtons="true"
                    rownumbers="true"
                    viewrecords="true"
                    scroll="true"
                    scrollrows="true"
                    width="1000"
                    autowidth="false"
                    height="280"
                    shrinkToFit="false"
                    resizable="false"
                    resizableAnimate="false"
                    resizableGhost="false"
                    resizableHandles="false"
                    navigator="true"
                    navigatorView="true"
                    navigatorSearch="true"
                    navigatorEdit="false"
                    navigatorDelete="false"
                    navigatorAdd="false"
                    navigatorExtraButtons="{ separator: {title :'separator'},
                                             hide     : {title : '%{getText('button.showHide')}',
                                                                icon: 'ui-icon-wrench',
                                                                topic: 'showHideColumns'}
                                       }"

                    onSelectRowTopics="getSelectedRowInfo"
                    reloadTopics="fillClassStudentListGrid"
                    onErrorTopics="handleServerError"
                    >

                <sjg:gridColumn name="studentId"           index="studentId"           title="%{getText('label.studentId')}"    align="center"  hidden="false"   width="70"  resizable="true" sortable="true"  search="true"  searchoptions="{sopt:['eq']}" />
                <sjg:gridColumn name="studentName"         index="studentName"         title="%{getText('label.studentName')}"                  hidden="false"   width="150" resizable="true" sortable="true"  search="true"  searchoptions="{sopt:['eq','cn']}"/>
                <sjg:gridColumn name="branchName"          index="branchName"          title="%{getText('label.branch')}"       align="center"  hidden="false"   width="150" resizable="true" sortable="false" search="false" />
                <sjg:gridColumn name="cellPhone"           index="cellPhone"           title="%{getText('label.cellPhone')}"    align="center"  hidden="false"   width="100" resizable="true" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
                <sjg:gridColumn name="homePhone"           index="homePhone"           title="%{getText('label.homePhone')}"    align="center"  hidden="false"   width="150" resizable="true" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
                <sjg:gridColumn name="emailAddress"        index="emailAddress"        title="%{getText('label.emailAddress')}"                 hidden="false"   width="200" resizable="true" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
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

        if($("#branchId").val() == 0){
            event.originalEvent.options.submit = false;
            addClientError('<s:text name="error.common.branchRequired" />');
            getElement("branchId").focus();
            return false;
        }

        if($("#termId").val() == 0){
            event.originalEvent.options.submit = false;
            addClientError('<s:text name="error.common.termRequired" />');
            getElement("termId").focus();
            return false;
        }

        if($("#classId").val() == 0){
            event.originalEvent.options.submit = false;
            addClientError('<s:text name="error.common.classIdRequired" />');
            getElement("classId").focus();
            return false;
        }
        return true;

    });
//======================================================================================================================
    $.subscribe('handleServerError', function(event,data) {

        removeClientErrors();
//        alert('handleServerError func : status: ' + event.originalEvent.status + '\n\nresponseText: \n' + event.originalEvent.request.responseText +      '\n\nThe output div should have already been updated with the responseText.');
//        $.each(event.originalEvent.data, function(index, value) {
//            alert(value);
//        });
        var jsonObjects = $.parseJSON(event.originalEvent.request.responseText);
        if(jsonObjects != null){
            if(jsonObjects.actionErrors != null){
                $.each(jsonObjects.actionErrors, function(e) {
                    addClientError(jsonObjects.actionErrors);
                });
                return;
            }
            if(jsonObjects.actionMessages != null){
                $.each(jsonObjects.actionErrors, function(e) {
                    addClientError(jsonObjects.actionMessages);
                });
                return;
            }
        }

        var errorCode = event.originalEvent.request.status;
        if(errorCode == 1300){
            addClientError('<s:text name="error.db.1300" />');
        }
        else  {
            addClientError('<s:text name="error.common.systemCouldNotRespond2" />');
        }
        addClientError(event.originalEvent.request.statusText);
        $('#classId').focus();

    });
//======================================================================================================================
    $.subscribe('getSelectedRowInfo', function(event,data) {

        alert('Selected Row : ' + event.originalEvent.id);
        var grid = event.originalEvent.grid;
        var sel_id = grid.jqGrid('getGridParam', 'selrow');
        var studentName = grid.jqGrid('getCell', sel_id, 'studentEnName');
        alert(studentName);
    });
//======================================================================================================================
});
//================================================== Java Script =======================================================
    function dateFormatter(cellvalue, options, rowObject) {

        var formattedText = "";
        if(cellvalue != null){
            formattedText = addDateSeparator(cellvalue.toString());
        }
        return formattedText;
    }
//======================================================================================================================
function printReport(){

//    if(! validateFormElements()){
//        return false;
//    }
    var url = "<s:property value="#printUrlTag"/>" + "?classId=" + $("#classId").val();
    window.location = url;
}
//======================================================================================================================


</script>