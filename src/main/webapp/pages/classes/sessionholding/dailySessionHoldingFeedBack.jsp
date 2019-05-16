<%@ page import="com.soshiant.server.constants.ServerConstants" %>
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
    String sessionIdVal = "0";
%>

<s:form namespace="/" id="dailySessionFeedbackForm" name="dailySessionFeedbackForm" action="saveDailySessionFeedBackAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 1000px;height: 680px;vertical-align: top; resize: none;">

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

        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
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
                    <%
                        if(studentNo > 0) {
                    %>
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
                    <%
                        }
                    %>
                    <tr class="bodyElementsRows">
                        <td colspan="5" style="height: 25px;">
                            &nbsp;
                        </td>
                    </tr>
                    <%
                        if(tempStudentsList != null && !tempStudentsList.isEmpty()){
                    %>
                    <s:iterator value="studentSessionScoresArrayList" status="arrayIndex">

                    <%
                        stdCounter++;
                        StudentSessionScores sessionStudent = tempStudentsList.get(stdCounter);
                        String studentIdVal = String.valueOf(sessionStudent.getStudentId());
                        String studentNameVal = String.valueOf(sessionStudent.getStudentId());
                        sessionIdVal = String.valueOf(sessionStudent.getSessionId());
                    %>

                    <tr class="bodyElementsRows">

                        <input type="hidden" id="studentId[<%=stdCounter%>]" name="studentId[<%=stdCounter%>]" value="<%=studentIdVal%>"/>
                        <input type="hidden" id="studentName[<%=stdCounter%>]" name="studentName[<%=stdCounter%>]" value="<%=studentNameVal%>"/>
                        <input type="hidden" id="feedbackScore[<%=stdCounter%>]" name="feedbackScore[<%=stdCounter%>]" value="0"/>

                        <%String tdId = "groupBox" + (stdCounter);%>
                        <td colspan="5" id="<%=tdId%>" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
                            <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                                <tr class="bodyElementsRows">
                                    <td dir="ltr" align="center" width="250px">
                                        <s:property value="studentName" />
                                    </td>
                                    <td dir="ltr" align="center" width="310px">

                                        <sj:slider
                                                   id="feedbackSlider%{#arrayIndex.index}"
                                                   value="10"
                                                   range="min"
                                                   min="10"
                                                   max="100"
                                                   step="5"
                                                   animate="true"
                                                   cssStyle="width: 300px; margin: 10px;"
                                                   displayValueElement="feedbackSpan%{#arrayIndex.index}"
                                                   onChangeTopics="sliderStop"
                                        />
                                    </td>
                                    <td dir="ltr" align="center" width="40px">
                                        <span id="feedbackSpan<%=stdCounter%>" name="feedbackSpan<%=stdCounter%>"></span>
                                    </td>
                                    <td dir="ltr" align="center" width="100px">

                                        <input type="button"
                                               id='<%="feedbackBtn" + stdCounter%>'
                                               name='<%="feedbackBtn" + stdCounter%>'
                                               value="<s:text name="button.save"/>"
                                               style="width: 80px;"
                                               <%--class="buttonForward"--%>
                                               onclick='<%="return submitData(this.form," + stdCounter + ");"%>'
                                        />

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
                    </tr>

                    </s:iterator>

                    <%
                        }
                    %>

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

</table>

</s:div>

<input type="hidden" id="sessionId" name="sessionId" value="<%=sessionIdVal%>"/>
<input type="hidden" id="studentCount" name="studentCount" value="<%=studentNo%>"/>

</s:form>

<script type="text/javascript" language="javascript">

$(document).ready(function () {
//======================================================================================================================
    $.subscribe('sliderStop', function(event, data) {

        getElement("feedbackScore").value = event.originalEvent.ui.value;
	});
//======================================================================================================================
});
//================================================== Java Script =======================================================
    function submitData(frm,arrayIndex) {
        getElement("feedbackScore[" + arrayIndex + "]").value = getElement("feedbackSlider" + arrayIndex).value;
        document.dailySessionFeedbackForm.submit();
    }
//======================================================================================================================

</script>