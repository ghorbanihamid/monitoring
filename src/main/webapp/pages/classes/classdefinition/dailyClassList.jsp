<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page import="com.soshiant.common.util.CommonUtil" %>
<%@ page import="com.soshiant.server.action.file.FileDownloadAction" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%
    String branchDisplayStatus = "none";
    short loginUserType = CommonUtil.getCurrentUserTypeIdFromSession();
    if(loginUserType >= ServerConstants.POSITION_TYPE_BRANCH_HEAD){
        branchDisplayStatus = "";
    }
    branchDisplayStatus = "";
    String fileName = "t";
%>

<s:form namespace="/" id="dailyClassListInputForm" name="dailyClassListInputForm" action="dailyClassListInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 1000px;height: 680px;vertical-align: top; resize: none;">

<h2><s:text name="label.dailyClassList"/></h2>

<table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

<tr class="bodyElementsRows">
    <td width="100%">
        <s:div cssStyle="overflow:hidden;height: 20px">
            <s:actionmessage id="serverMessageList" cssClass="actionMessageClass"/>
            <s:actionerror id="serverErrorList" cssClass="actionErrorClass"/>
            <ul id="clientErrorList" class="actionErrorClass"></ul>
        </s:div>
    </td>
</tr>

<tr class="bodyElementsRows">
    <td style="height: 25px;">
        &nbsp;
    </td>
</tr>
<tr class="bodyElementsRows">
    <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
        <div style="height: 20px; text-align: center;" class="ui-widget-header ui-corner-top">
            <s:text name="label.branch"/>
        </div>
        <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
            <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                <tr class="bodyElementsRows">
                    <td colspan="6" style="height: 25px;">
                        &nbsp;
                    </td>
                </tr>

                <tr class="bodyElementsRows" width="100%">
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
                                           formIds="dailyClassListInputForm"
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
                    <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px">
                        <s:text name="label.termName"/>
                    </td>
                    <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="160px">
                        <sj:select id="termId"
                                   name="termId"
                                   href="termsListAroundCurrentYearAction.action"
                                   list="aroundCurrentYearTermsList"
                                   listKey="termId"
                                   listValue="termName"
                                   dataType="json"
                                   cssClass="commonElement"
                                   cssStyle="height: 20px;width: 94%"
                                   headerKey="0"
                                   headerValue="%{getText('label.notDefined')}"
                                   emptyOption="false"
                                   tabindex="2"
                                >
                        </sj:select>
                    </td>
                    <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px">
                        <s:url id="branchInfoUrl" namespace="/" action="branchInfoForClassListJsonAction"/>
                        <sj:a
                                id="fetchClassListBtn"
                                button="true"
                                value="%{getText('button.fetch')}"
                                href="%{branchInfoUrl}"
                                formIds="dailyClassListInputForm"
                                targets="branchDescSpan"
                                effect="pulsate"
                                effectDuration="1000"
                                effectOptions="{mode:'show' , times:3}"
                                cssStyle="width: 70px;"
                                buttonIcon="ui-icon-gear"
                                cssClass="buttonForward"
                                indicator="indicator1"
                                onBeforeTopics="validateForm"
                                onSuccessTopics="fillGrid"
                                onErrorTopics="handleError"
                                tabindex="3"
                                >
                            <s:text name="button.fetch"/>
                        </sj:a>
                    </td>
                    <td>
                        <img id="indicator1" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                    </td>
                </tr>

                <tr class="bodyElementsRows">
                    <td colspan="6" align="center">
                        <span id="branchDescSpan"></span>
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
<tr>
    <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="100%">
        <s:url action="printDailyClassListAction" var="printUrlTag" >
            <%--<s:param name="branchId">${branchId}</s:param>--%>
            <%--<s:param name="termId">99</s:param>--%>
        </s:url>
        <%--<s:a href="%{printUrlTag}">--%>
            <%--<img src="<s:text name="monitoring.printImage" />" alt="print friendly" width="20" height="20" border="0"/>&nbsp;--%>
            <%--<s:text name="button.print"/>--%>
        <%--</s:a>--%>
            <%--<sj:a href="%{printUrlTag}" formIds="dailyClassListInputForm">--%>
            <%--<img src="<s:text name="monitoring.printImage" />" alt="print friendly" width="20" height="20" border="0"/>&nbsp;--%>
            <%--<s:text name="button.print"/>--%>
            <%--</sj:a>--%>

        <a href="javascript:printReport()">
            <img src="<s:text name="monitoring.printImage" />" alt="print friendly" width="20" height="20" border="0"/>&nbsp;
            <s:text name="button.print"/>
        </a>
        &nbsp;&nbsp;&nbsp;
            <%--<s:url action="downloadFileAction" var="downloadFileUrlTag" >--%>
            <%--&lt;%&ndash;<input type="hidden" name="contentType" value="<%=FileDownloadAction.CONTENT_TYPE_PDF%>"/>&ndash;%&gt;--%>
            <%--<s:param name="contentType"><%=FileDownloadAction.CONTENT_TYPE_PDF%></s:param>--%>
            <%--&lt;%&ndash;<s:param name="contentType" value="<%=FileDownloadAction.CONTENT_TYPE_PDF%>"/>&ndash;%&gt;--%>
            <%--<s:param name="fileName"><%=fileName%></s:param>--%>
            <%--</s:url>--%>
            <%--<sj:a href="%{downloadFileUrlTag}" formIds="dailyClassListInputForm">--%>
            <%--<img src="<s:text name="monitoring.downloadImage" />" alt="print friendly" width="20" height="20" border="0"/>&nbsp;--%>
            <%--<s:text name="button.download"/>--%>
            <%--</sj:a>--%>
    </td>
</tr>

    <%--when an element of the outer grid is expanded then the key value(classId) is passed as id to the subGridUrl action of the inner action, --%>
    <%--according to which data is being fetched and returned as JSON in the inner grid. --%>
    <%--If key is set to false then the usual serial number of the record being selected for expansion(clicking on plus sign) is passed as id. --%>
    <%--Therefore, in both cases the action which populates the inner grid must have an attribute named id with its corresponding getter and setter.--%>

<tr class="bodyElementsRows">
    <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

        <s:url id="dailyClassListUrl"     namespace="/" action="dailyClassListJsonAction"/>
        <s:url id="dailyClassDaysListUrl" namespace="/" action="dailyClassDaysListJsonAction"/>
        <sjg:grid
                id="dailyClassGridTable"
                caption="%{getText('label.dailyClassList')}"
                dataType="json"
                href="%{dailyClassListUrl}"
                formIds="dailyClassListInputForm"
                pager="true"
                toppager="true"
                pagerButtons="true"
                gridModel="dailyClassList"
                rowList="20,30,50"
                rowNum="0"
                viewrecords="true"
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
                width="1000"
                height="300"
                scroll="true"
                scrollrows="true"
                autowidth="false"
                shrinkToFit="false"
                onSelectRowTopics="rowSelect"
                reloadTopics="fillGrid"
                >

            <sjg:grid
                    id="classDaysSubGrid"
                    subGridUrl="%{dailyClassDaysListUrl}"
                    gridModel="dailyClassDaysList"
                    rowNum="-1"
                    footerrow="true"
                    userDataOnFooter="true"
                    subGridWidth="15"
                    >
                <sjg:gridColumn name="stringClassId"   index="stringClassId"  title="%{getText('dailyClass.classId')}"       align="center"  hidden="false" width="80"  resizable="true" sortable="false"  search="false" key="true"/>
                <sjg:gridColumn name="dayName"         index="dayName"        title="%{getText('dailyClass.classDayName')}"  align="center"  hidden="false" width="80"  resizable="true" sortable="false"  search="false" key="true"/>
                <sjg:gridColumn name="startTime"       index="startTime"      title="%{getText('dailyClass.startTime')}"     align="center"  hidden="false" width="60"  resizable="true" sortable="false"  search="false" />
                <sjg:gridColumn name="endTime"         index="endTime"        title="%{getText('dailyClass.endTime')}"       align="center"  hidden="false" width="60"  resizable="true" sortable="false"  search="false" />
                <sjg:gridColumn name="sessionCount"    index="sessionCount"   title="%{getText('dailyClass.sessionCount')}"  align="center"  hidden="false" width="80"  resizable="true" sortable="false"  search="false" />
                <sjg:gridColumn name="sessionType"     index="sessionType"    title="%{getText('dailyClass.sessionType')}"   align="center"  hidden="false" width="80"  resizable="true" sortable="false"  search="false" />
                <sjg:gridColumn name="teacherId"       index="teacherId"      title="%{getText('label.teacherId')}"          align="center"  hidden="false" width="100" resizable="true" sortable="false"  search="false" />
                <sjg:gridColumn name="teacherName"     index="teacherName"    title="%{getText('label.teacherName')}"        align="center"  hidden="false" width="200" resizable="true" sortable="false"  search="false" />
            </sjg:grid>

            <sjg:gridColumn name="stringClassId"           index="stringClassId"          title="%{getText('label.classId')}"                   width="110" align="center" sortable="true"  search="true"  searchoptions="{sopt:['eq']}" key="true"/>
            <sjg:gridColumn name="className"               index="className"              title="%{getText('label.className')}"                 width="300" align="center" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
            <sjg:gridColumn name="classTypeName"           index="classTypeName"          title="%{getText('dailyClass.classType')}"            width="80"  align="center" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
            <sjg:gridColumn name="classLevelName"          index="classLevelName"         title="%{getText('label.levelName')}"                 width="130" align="center" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
            <sjg:gridColumn name="branchName"              index="branchName"             title="%{getText('label.branch')}"                    width="100" align="center" sortable="false" search="false" searchoptions="{sopt:['eq','cn']}"/>
            <sjg:gridColumn name="roomName"                index="roomName"               title="%{getText('label.roomName')}"                  width="100" align="center" sortable="false" search="false" />
            <sjg:gridColumn name="classSessionsCount"      index="classSessionsCount"     title="%{getText('dailyClass.classSessionsCount')}"   width="90"  align="center" sortable="false" search="false" />
            <sjg:gridColumn name="classCapacity"           index="classCapacity"          title="%{getText('dailyClass.classCapacity')}"        width="80"  align="center" sortable="false" search="false" />
            <sjg:gridColumn name="classRegStdCount"        index="classRegStdCount"       title="%{getText('dailyClass.regStdCount')}"          width="160" align="center" sortable="false" search="false" />
            <sjg:gridColumn name="classFee"                index="classFee"               title="%{getText('dailyClass.classFee')}"             width="80"  align="center" sortable="false" search="false" />
            <sjg:gridColumn name="termName"                index="termName"               title="%{getText('label.termName')}"                  width="100" align="center" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
            <sjg:gridColumn name="classStartDate"          index="classStartDate"         title="%{getText('dailyClass.startDate')}"            width="80"  align="center" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
            <sjg:gridColumn name="classEndDate"            index="classEndDate"           title="%{getText('dailyClass.endDate')}"              width="80"  align="center" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
            <sjg:gridColumn name="classStatus"             index="classStatus"            title="%{getText('dailyClass.classStatus')}"          width="150" align="center" sortable="false" search="false" searchoptions="{sopt:['eq','cn']}"/>
            <sjg:gridColumn name="classRegisterDate"       index="classRegisterDate"      title="%{getText('label.registerDate')}"              width="80"  align="center" sortable="false" search="false" />
            <sjg:gridColumn name="registeredUserName"      index="registeredUserName"     title="%{getText('label.registeredUserName')}"        width="150" align="center" sortable="false" search="false" />
            <sjg:gridColumn name="classChangeDate"         index="classChangeDate"        title="%{getText('label.changeDate')}"                width="80"  align="center" sortable="false" search="false" />
            <sjg:gridColumn name="changedUserName"         index="changedUserName"        title="%{getText('label.changedUserName')}"           width="150" align="center" sortable="false" search="false" />

        </sjg:grid>

    </td>
</tr>
</table>

</s:div>
<input type="hidden" id="classId" name="classId"/>
</s:form>
<img id="indicator" src="/images/indicator.gif" alt="Loading..." style="display:none"/>

<script type="text/javascript" language="javascript">

    $(document).ready(function () {
//======================================================================================================================
    $.subscribe('rowSelect', function(event, data) {
        alert('Selected Row : ' + event.originalEvent.id);
        var grid = event.originalEvent.grid;
        var selRowId = grid.jqGrid('getGridParam', 'selrow');
        var classId = grid.jqGrid('getCell', selRowId, 'stringClassId');
        alert(classId);
        getElement("ClassId").value =  classId;

    });
//======================================================================================================================
    $.subscribe('validateForm', function(event,data) {

        if(!validateFormElements()){
            return false;
        }
    });
//======================================================================================================================
});
//================================================== Java Script =======================================================
    function validateFormElements(){

        removeClientErrors();
        getElement("branchDescSpan").innerHTML = "";

        var displayStatus = "<%=branchDisplayStatus%>";
        if(displayStatus == "") {
            if($("#branchId").val() == 0){
                addClientError('<s:text name="error.common.branchRequired" />');
                $("#branchId").focus();
                return false;
            }
        }

        if($("#termId").val() == 0){
            addClientError('<s:text name="error.common.termRequired" />');
            $("#termId").focus();
            return false;
        }
        return true;
    }
//======================================================================================================================
    function printReport(){

        if(! validateFormElements()){
            return false;
        }
        var url = "<s:property value="#printUrlTag"/>" + "?branchId=" + $("#branchId").val() + "&termId=" + $("#termId").val();
        window.location = url;
    }
//======================================================================================================================
</script>