<%@ page import="com.soshiant.common.util.CommonUtil" %>
<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page import="java.util.Vector" %>
<%@ page import="com.soshiant.server.model.student.Student" %>
<%@ page import="com.soshiant.server.model.classes.DailyClass" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<%
    List<DailyClass> teacherClassesList =  (List<DailyClass>) request.getAttribute(ServerConstants.TEACHER_CLASSES_LIST_VECTOR);
%>

<s:form namespace="/" id="dailySessionTeacherClassesResultForm" name="dailySessionTeacherClassesResultForm" action="dailySessionHoldingInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 1000px;height: 680px;vertical-align: top; resize: none;">

<h2><s:text name="label.studentList"/></h2>

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
                <s:text name="label.branch"/>
            </div>
            <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
                <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                    <tr class="bodyElementsRows">
                        <td colspan="2" style="height: 25px;">
                            &nbsp;
                        </td>
                    </tr>

                    <%
                        String classId = "0";
                        String className = "";
                        if(teacherClassesList != null){
                            for(int classCounter = 0; classCounter < teacherClassesList.size(); classCounter++) {

                                DailyClass teacherClass = teacherClassesList.get(classCounter);
                                className = teacherClass.getClassName();
                                classId = String.valueOf(teacherClass.getClassId());
                    %>


                            <tr class="bodyElementsRows">
                                <%--<form id="teacherClassesForm[<%=classCounter%>]" name="teacherClassesForm[<%=classCounter%>]" action="dailySessionHoldingInputAction" method="post">--%>
                                <%--<s:form namespace="/" action="dailySessionHoldingInputAction" theme="simple">--%>
                                    <%--<input type="hidden" name="classId" value="<%=classId%>"/>--%>
                                    <%--<s:hidden id="classId" value="%{classId}" />--%>
                                    <td dir="ltr" align="left" width="50px">
                                        <%--<span><%=className%>&nbsp;&nbsp;&nbsp;</span>--%>
                                        <input type="button"
                                               id="selectBtn[<%=classCounter%>]"
                                               name="selectBtn[<%=classCounter%>]"
                                               value="<%=className%>"
                                               style="width: 400px;"
                                               <%--class="buttonForward"--%>
                                               Class="type-text ui-widget ui-widget-content ui-corner-all"
                                               onclick='<%="return chooseClass(this,\"" + classId + "\");"%>'
                                        />
                                        <%--<s:submit id="1"--%>
                                                  <%--name="1"--%>
                                                  <%--action="dailySessionHoldingInputAction"--%>
                                                  <%--cssStyle="width: 50px;"--%>
                                                  <%--cssClass="buttonForward"--%>
                                                  <%--value="%{getText('button.select')}"--%>
                                                  <%--type="button"--%>
                                                  <%--tabindex="2"--%>
                                        <%--/>--%>
                                    </td>
                                <%--</s:form>--%>
                                <%--</form>--%>
                            </tr>
                    <%
                        }
                    }
                    %>

                    <tr class="bodyElementsRows">
                        <td colspan="2" style="height: 25px;">
                             &nbsp;
                        </td>
                    </tr>

                </table>
            </s:div>
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td align="left" dir="ltr" width="100%">
            &nbsp;
        </td>
    </tr>

</table>

</s:div>

<s:hidden id="classId" name="classId"/>

</s:form>

<script type="text/javascript" language="javascript">

$(document).ready(function () {
//======================================================================================================================
//======================================================================================================================
//======================================================================================================================
});
//================================================== Java Script =======================================================
    function chooseClass(obj,classId) {
        getElement("classId").value = classId;
        document.dailySessionTeacherClassesResultForm.submit();
    }
//======================================================================================================================

</script>