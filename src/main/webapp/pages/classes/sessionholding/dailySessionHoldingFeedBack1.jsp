<%@ page import="com.soshiant.common.util.CommonUtil" %>
<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page import="java.util.Vector" %>
<%@ page import="com.soshiant.server.model.student.Student" %>
<%@ page import="com.soshiant.server.model.classes.DailyClassStudentsInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.soshiant.server.model.classes.StudentSessionScores" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<%
    List<StudentSessionScores> tempStudentsList = (List<StudentSessionScores>) request.getAttribute(ServerConstants.SESSION_STUDENTS_LIST_VECTOR);
    int studentNo = 0;
    int stdCounter = -1;
    if(tempStudentsList != null){
        studentNo = tempStudentsList.size();
    }
    String studentCountStr = String.valueOf(studentNo);
    String sessionIdVal = "0";
%>

<%--<s:form namespace="/" id="dailySessionFeedbackForm" name="dailySessionFeedbackForm" action="saveDailySessionFeedBackAction" theme="simple">--%>

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 650px">

<h2><s:text name="label.feedback"/></h2>

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
                <s:text name="label.student"/>
            </div>
            <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
                <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                    <tr class="bodyElementsRows">
                        <td colspan="5" style="height: 25px;">
                            &nbsp;
                        </td>
                    </tr>
                    <tr class="bodyElementsRows">
                        <td dir="ltr" align="center" width="250px">
                            <s:text name="label.name"/>
                        </td>
                        <td dir="ltr" align="center" width="310px">
                            <s:text name="label.feedback"/>
                        </td>
                        <td dir="ltr" align="center" width="40px">
                            &nbsp;
                        </td>
                        <td dir="ltr" align="center" width="100px">
                            &nbsp;
                        </td>
                        <td dir="ltr" align="center">
                            &nbsp;
                        </td>
                    </tr>
                    <tr class="bodyElementsRows">
                        <td colspan="5" style="height: 25px;">
                            &nbsp;
                        </td>
                    </tr>
                    <s:iterator value="studentSessionScoresArrayList" status="arrayIndex">

                    <%
                        stdCounter++;
//                        for(int stdCounter = 0; stdCounter < studentNo; stdCounter++) {

                            StudentSessionScores sessionStudent = tempStudentsList.get(stdCounter);
                            String studentIdVal = String.valueOf(sessionStudent.getStudentId());
                            sessionIdVal = String.valueOf(sessionStudent.getSessionId());
                            String feedbackSliderName = "feedbackSlider[" + stdCounter + "]";
                    %>

                    <tr class="bodyElementsRows">
                        <s:form namespace="/" id="dailySessionFeedbackForm" name="dailySessionFeedbackForm" action="saveDailySessionFeedBackAction" theme="simple">
                        <%--<s:set name="myStudentIdVal" value="studentSessionScoresArrayList[#arrayIndex.index]"/>--%>
                        <%--<input type="hidden" id="studentId[<%=stdCounter%>]" name="studentId[<%=stdCounter%>]" value="<%=studentIdVal%>"/>--%>
                        <input type="hidden" id="studentId"    name="studentId"    value="<%=studentIdVal%>"/>
                        <input type="hidden" id="sessionId"    name="sessionId"    value="<%=sessionIdVal%>"/>
                        <input type="hidden" id="studentCount" name="studentCount" value="<%=studentNo%>"/>
                        <input type="hidden" id="feedback"     name="feedback"/>

                        <%String tdId = "groupBox" + (stdCounter);%>
                        <td colspan="5" id="<%=tdId%>" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
                            <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                                <tr class="bodyElementsRows">
                                    <td dir="ltr" align="center" width="250px">
                                        <s:property value="studentName" />
                                    </td>
                                    <td dir="ltr" align="center" width="310px">


                                        <sj:slider
                                                   id="slider%{#arrayIndex.index}"
                                                   value="10"
                                                   range="min"
                                                   min="10"
                                                   max="100"
                                                   step="5"
                                                   cssStyle="width: 300px; margin: 10px;"
                                                   displayValueElement="feedbackSpan%{#arrayIndex.index}"

                                        />
                                    </td>
                                    <td dir="ltr" align="center" width="40px">
                                        <span id="feedbackSpan<%=stdCounter%>" name="feedbackSpan[<%=stdCounter%>]"></span>
                                    </td>
                                    <td dir="ltr" align="center" width="100px">

                                        <input type="button"
                                               id='<%="homeWorkBtn" + stdCounter%>'
                                               name='<%="homeWorkBtn" + stdCounter%>'
                                               value="<s:text name="button.save"/>"
                                               style="width: 80px;"
                                               <%--class="buttonForward"--%>
                                               onclick='<%="return submitData(this," + stdCounter + ");"%>'
                                        />

                                        <%--<s:url id="saveFeedbackUrl" namespace="/" action="saveDailySessionFeedBackAction"/>--%>
                                        <%--<sj:submit id="saveFeedback"--%>
                                                   <%--name="saveFeedback"--%>
                                                   <%--button="true"--%>
                                                   <%--value="%{getText('button.save')}"--%>
                                                   <%--href="%{saveFeedbackUrl}"--%>
                                                   <%--formIds="dailySessionFeedbackForm"--%>
                                                   <%--buttonIcon="ui-icon-gear"--%>
                                                   <%--cssStyle="width: 80px;"--%>
                                                   <%--cssClass="buttonForward"--%>
                                                   <%--indicator="indicator1"--%>
                                                   <%--onErrorTopics="handleError"--%>
                                                   <%--tabindex="2"--%>
                                        <%--/>--%>
                                    </td>

                                    <td dir="ltr" align="center">
                                        <img id="indicator1" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                                    </td>

                                </tr>
                            </table>

                        </td>
                        <td dir="ltr" align="center">
                            &nbsp;
                        </td>
                        </s:form>
                    </tr>

                    <%--<%--%>
                        <%--}--%>
                    <%--%>--%>
                    </s:iterator>

                    <tr class="bodyElementsRows" style="border: dashed #00008b">
                        <td colspan="5" style="height: 25px;">
                             &nbsp;
                        </td>
                    </tr>

                </table>
            </s:div>
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td align="left" dir="ltr" style="height: 25px;" width="100%">
            &nbsp;
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td>
            <table dir="<s:text name="dir.ltr" />" width="100%">
                <tr class="bodyElementsRows">
                    <td dir="<s:text name="dir.ltr" />" align="center">
                        <s:submit id="saveButton" name="saveButton" key="button.save" formIds="dailySessionHoldingInputForm" action="dailySessionHoldingSaveAction" cssStyle="width: 75px;" cssClass="buttonForward" tabindex="30"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>

</table>

</s:div>

<input type="hidden" id="sessionId" name="sessionId" value="<%=sessionIdVal%>"/>
<input type="hidden" id="studentCount" name="studentCount" value="<%=studentNo%>"/>

<%--</s:form>--%>

<script type="text/javascript" language="javascript">

$(document).ready(function () {
//======================================================================================================================
//======================================================================================================================
});
//================================================== Java Script =======================================================
    function submitData(obj,arrayIndex) {

        getElement("project[" + i + "]").value = studentsProject[i];
        obj.disabled = true;
        document.dailySessionHoldingInputForm.submit;
    }
//======================================================================================================================

</script>