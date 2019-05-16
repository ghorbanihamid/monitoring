<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page import="com.soshiant.common.util.CommonUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.soshiant.server.model.student.AdvisorStudentsInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<%
    int unRegStdCount = (Integer) request.getAttribute(ServerConstants.ADVISOR_URREG_STUDENTS_COUNT);
    List<AdvisorStudentsInfo> tempStudentsList = (List<AdvisorStudentsInfo>) request.getAttribute(ServerConstants.ADVISOR_ALL_STUDENTS_LIST_VECTOR);
    int allStdCount = 0;
    if(tempStudentsList!= null){
        allStdCount = tempStudentsList.size();
    }
    int regStdCount = allStdCount - unRegStdCount;
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

                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">
                            <s:text name="advisorFinanceReport.studentsCount"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100px">
                            <span id="studentsCountSpan"></span>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">
                            <s:text name="advisorFinanceReport.sumStudentsFee"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
                            <span id="sumStudentsFeeSpan"></span>
                        </td>
                    </tr>
                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">
                            <s:text name="advisorFinanceReport.advisorPortion"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100px">
                            <span id="advisorPortionSpan"></span>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">
                            <s:text name="advisorFinanceReport.teacherPortion"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
                            <span id="teacherPortionSpan"></span>
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
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

            <sjg:grid
                    id="listGrid"
                    caption="%{getText('label.advisorStudentList')}"
                    dataType="local"
                    formIds="advisorRegStudentsListInputForm"
                    pager="true"
                    gridModel="studentList"
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
                    loadonce="true"
                    width="1000"
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
        var gridData = new Array();
        var jSumStudentsFee = 0;
        var jAdvisorPercent = <%=ServerConstants.ADVISOR_PERCENT%>;
        var jTeacherPercent = <%=ServerConstants.TEACHER_PERCENT%>;
        var jItPercent      = <%=ServerConstants.IT_PERCENT%>;
    <%

        int sumStudentsFee = 0;
        for(int i = 0; i < tempStudentsList.size(); i++){
            AdvisorStudentsInfo advisorStudentsInfo = tempStudentsList.get(i);
            sumStudentsFee += advisorStudentsInfo.getClassFee();
    %>
        gridData[<%=i%>] = {};
        gridData[<%=i%>]["studentId"]       = '<%=advisorStudentsInfo.getStudentId()%>';
        gridData[<%=i%>]["studentName"]     = '<%=advisorStudentsInfo.getStudentName()%>';
        gridData[<%=i%>]["stdNickName"]     = '<%=advisorStudentsInfo.getStdNickName()%>';
        gridData[<%=i%>]["className"]       = '<%=advisorStudentsInfo.getClassName()%>';
        gridData[<%=i%>]["stdRegisterDate"] = '<%=advisorStudentsInfo.getStdRegisterDate()%>';
        gridData[<%=i%>]["stdCellPhone"]    = '<%=advisorStudentsInfo.getStdCellPhone()%>';
        gridData[<%=i%>]["stdHomePhone"]    = '<%=advisorStudentsInfo.getStdHomePhone()%> ';
        gridData[<%=i%>]["stdEmailAddress"] = '<%=advisorStudentsInfo.getStdEmailAddress()%>';
    <%
        }
    %>
    jSumStudentsFee = <%=sumStudentsFee%>;
    $("#listGrid").jqGrid('clearGridData');
    for(var i=0;i <= gridData.length;i++){
        $("#listGrid").jqGrid('addRowData',i+1,gridData[i]);
    }
    getElement("sumStudentsFeeSpan").value = jSumStudentsFee;
    getElement("studentsCountSpan").value = <%=tempStudentsList.size()%>;
    getElement("advisorPortionSpan").value = Math.round(jSumStudentsFee * jAdvisorPercent);
    getElement("teacherPortionSpan").value = Math.round(jSumStudentsFee * jTeacherPercent);
//======================================================================================================================
//    $("#listGrid").jqGrid('setGridParam', {
//        datatype: 'local',
//        data: gridData,
//        rowNum: gridData.length
//    });
//    $("#listGrid").jqGrid('setData', gridData);
//    $("#listGrid").trigger("reloadGrid");
//    $("#listGrid").GridUnload();

//======================================================================================================================
});
//================================================== Java Script =======================================================
    function dateFormatter(cellvalue, options, rowObject) {

        var formattedText =addDateSeparator(cellvalue.toString());
        return formattedText;
    }
//======================================================================================================================

</script>