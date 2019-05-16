<%@ page import="com.soshiant.common.util.CommonUtil" %>
<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page import="java.util.Vector" %>
<%@ page import="com.soshiant.server.model.student.Student" %>
<%@ page import="com.soshiant.server.model.classes.DailyClassStudentsInfo" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<%
    List<DailyClassStudentsInfo> tempStudentsList = (List<DailyClassStudentsInfo>) request.getAttribute(ServerConstants.CLASS_STUDENTS_LIST_VECTOR);
    int studentNo = 0;
    if(tempStudentsList != null){
        studentNo = tempStudentsList.size();
    }
    String classId = "0";
    String studentCountStr = String.valueOf(studentNo);
%>

<s:form namespace="/" id="dailySessionHoldingInputForm" name="dailySessionHoldingInputForm" action="dailySessionHoldingSaveAction" theme="simple">

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
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
            <div style="height: 20px; text-align: center;" class="ui-widget-header ui-corner-top">
                <s:text name="label.student"/>
            </div>
            <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
                <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                    <tr class="bodyElementsRows">
                        <td colspan="11" style="height: 25px;">
                            &nbsp;
                        </td>
                    </tr>
                    <tr class="bodyElementsRows">
                        <td dir="ltr" align="center" width="150px">
                            <s:text name="label.name"/>
                        </td>
                        <td dir="ltr" align="center" width="100px">
                            <s:text name="label.nickName"/>
                        </td>
                        <td dir="ltr" align="center" width="50px">
                            <s:text name="label.todayScores"/>
                        </td>
                        <td dir="ltr" align="center" width="90px">
                            <s:text name="label.homeWork"/>
                        </td>
                        <td dir="ltr" align="center" width="100px">
                            <s:text name="label.keyQuestions"/>
                        </td>
                        <td dir="ltr" align="center" width="100px">
                            <s:text name="label.rightAnswers"/>
                        </td>
                        <td dir="ltr" align="center" width="70px">
                            <s:text name="label.warnings"/>
                        </td>
                        <td dir="ltr" align="center" width="70px">
                            <s:text name="label.project"/>
                        </td>
                        <td dir="ltr" align="center" width="50px">
                            &nbsp;
                        </td>
                        <td dir="ltr" align="center" width="50px">
                            <s:text name="label.present"/>
                        </td>
                        <td dir="ltr" align="center">
                            &nbsp;
                        </td>
                    </tr>
                    <tr class="bodyElementsRows">
                        <td colspan="11" style="height: 25px;">
                            &nbsp;
                        </td>
                    </tr>
                    <%
                        for(int stdCounter = 0; stdCounter < studentNo; stdCounter++) {

                            DailyClassStudentsInfo classStudent = tempStudentsList.get(stdCounter);
                            String studentName = classStudent.getStudentEnFamily() + " " + classStudent.getStudentEnName();
                            String nickName = classStudent.getNickName();
                            String studentIdVal = String.valueOf(classStudent.getStudentId());
                            classId = String.valueOf(classStudent.getClassId());
                            String todayRank = "0";
                            String keyQuestionsValue = String.valueOf(ServerConstants.PARAMETER_KEY_QUESTIONS_COUNT);
                            String rightAnswersValue = String.valueOf(ServerConstants.PARAMETER_RIGHT_ANSWERS_COUNT);


                    %>
                    <tr class="bodyElementsRows">
                        <input type="hidden" id="studentId[<%=stdCounter%>]" name="studentId[<%=stdCounter%>]" value="<%=studentIdVal%>"/>
                        <input type="hidden" id="studentName[<%=stdCounter%>]" name="studentName[<%=stdCounter%>]" value="<%=studentName%>"/>
                        <input type="hidden" id="present[<%=stdCounter%>]" name="present[<%=stdCounter%>]" value="1"/>
                        <%String tdId = "groupBox" + (stdCounter);%>
                        <td colspan="9" id="<%=tdId%>" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
                            <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                                <tr class="bodyElementsRows">
                                    <td dir="ltr" align="center" width="150px">
                                        <span>
                                            <%=studentName%>
                                        </span>
                                    </td>
                                    <td dir="ltr" align="center" width="100px">
                                        <span>
                                            <%=nickName%>
                                        </span>
                                    </td>
                                    <td dir="ltr" align="center" width="50px">
                                        <span id='<%="todayScore" + stdCounter%>'>

                                        </span>
                                    </td>
                                    <td dir="ltr" align="center" width="90px">
                                        <input type="hidden" id="homeWork[<%=stdCounter%>]" name="homeWork[<%=stdCounter%>]"/>
                                        <input type="button"
                                               id='<%="homeWorkBtn" + stdCounter%>'
                                               name='<%="homeWorkBtn" + stdCounter%>'
                                               value="<s:text name="button.homeWork"/>"
                                               style="width: 80px;"
                                               <%--class="buttonForward"--%>
                                               onclick='<%="return homeWorkClick(this," + stdCounter + ");"%>'
                                        />

                                        <%--<s:url id="advisorNameUrl" namespace="/" action="staffNameForPositionJsonAction"/>--%>
                                        <%--<sj:submit id="homeWorkBtn"--%>
                                                   <%--name="homeWorkBtn"--%>
                                                   <%--button="true"--%>
                                                   <%--value="%{getText('button.homeWork')}"--%>
                                                   <%--href="%{advisorNameUrl}"--%>
                                                   <%--formIds="dailySessionHoldingInputForm"--%>
                                                   <%--buttonIcon="ui-icon-gear"--%>
                                                   <%--cssStyle="width: 80px;"--%>
                                                   <%--cssClass="buttonForward"--%>
                                                   <%--indicator="indicator1"--%>
                                                   <%--onSuccessTopics="fillStudentListGrid"--%>
                                                   <%--onErrorTopics="handleError"--%>
                                                   <%--tabindex="2"/>--%>
                                    </td>
                                    <td dir="ltr" align="center" width="100px">
                                        <input type="hidden" id="keyQuestions[<%=stdCounter%>]" name="keyQuestions[<%=stdCounter%>]"/>
                                        <input type="button"
                                               id='<%="keyQuestionsBtn" + stdCounter%>'
                                               name='<%="keyQuestionsBtn" + stdCounter%>'
                                               value="<%=keyQuestionsValue%>"
                                               style="width: 90px;"
                                               <%--class="buttonForward"--%>
                                               onclick='<%="return keyQuestionsClick(this," + stdCounter + ");"%>'
                                        />

                                        <%--<s:url id="advisorNameUrl" namespace="/" action="staffNameForPositionJsonAction"/>--%>
                                        <%--<sj:submit id="keyQuestionsBtn"--%>
                                                   <%--name="keyQuestionsBtn"--%>
                                                   <%--button="true"--%>
                                                   <%--value="%{getText('button.keyQuestions')}"--%>
                                                   <%--href="%{advisorNameUrl}"--%>
                                                   <%--formIds="dailySessionHoldingInputForm"--%>
                                                   <%--buttonIcon="ui-icon-gear"--%>
                                                   <%--cssStyle="width: 90px;"--%>
                                                   <%--cssClass="buttonForward"--%>
                                                   <%--indicator="indicator1"--%>
                                                   <%--onSuccessTopics="fillStudentListGrid"--%>
                                                   <%--onErrorTopics="handleError"--%>
                                                   <%--tabindex="2"/>--%>
                                    </td>
                                    <td dir="ltr" align="center" width="100px">
                                        <input type="hidden" id="rightAnswers[<%=stdCounter%>]" name="rightAnswers[<%=stdCounter%>]"/>
                                        <input type="button"
                                               id='<%="rightAnswersBtn" + stdCounter%>'
                                               name='<%="rightAnswersBtn" + stdCounter%>'
                                               value="<%=rightAnswersValue%>"
                                               style="width: 90px;"
                                               <%--class="buttonForward"--%>
                                               onclick='<%="return rightAnswersClick(this," + stdCounter + ");"%>'
                                        />

                                        <%--<s:url id="advisorNameUrl" namespace="/" action="staffNameForPositionJsonAction"/>--%>
                                        <%--<sj:submit id="rightAnswersBtn"--%>
                                                   <%--name="rightAnswersBtn"--%>
                                                   <%--button="true"--%>
                                                   <%--value="%{getText('button.rightAnswers')}"--%>
                                                   <%--href="%{advisorNameUrl}"--%>
                                                   <%--formIds="dailyClassStudentsListInputForm"--%>
                                                   <%--buttonIcon="ui-icon-gear"--%>
                                                   <%--cssStyle="width: 90px;"--%>
                                                   <%--cssClass="buttonForward"--%>
                                                   <%--indicator="indicator1"--%>
                                                   <%--onSuccessTopics="fillStudentListGrid"--%>
                                                   <%--onErrorTopics="handleError"--%>
                                                   <%--tabindex="2"/>--%>
                                    </td>
                                    <td dir="ltr" align="center" width="70px">
                                        <input type="hidden" id="warning[<%=stdCounter%>]" name="warning[<%=stdCounter%>]"/>
                                        <input type="button"
                                               id='<%="warningBtn" + stdCounter%>'
                                               name='<%="warningBtn" + stdCounter%>'
                                               value="<s:text name="button.warnings"/>"
                                               style="width: 60px;"
                                               <%--class="buttonForward"--%>
                                               onclick='<%="return warningClick(this," + stdCounter + ");"%>'
                                        />

                                        <%--<s:url id="advisorNameUrl" namespace="/" action="staffNameForPositionJsonAction"/>--%>
                                        <%--<sj:submit id="warningsBtn"--%>
                                                   <%--name="warningsBtn"--%>
                                                   <%--button="true"--%>
                                                   <%--value="%{getText('button.warnings')}"--%>
                                                   <%--href="%{advisorNameUrl}"--%>
                                                   <%--formIds="dailySessionHoldingInputForm"--%>
                                                   <%--buttonIcon="ui-icon-gear"--%>
                                                   <%--cssStyle="width: 60px;"--%>
                                                   <%--cssClass="buttonForward"--%>
                                                   <%--indicator="indicator1"--%>
                                                   <%--onSuccessTopics="fillStudentListGrid"--%>
                                                   <%--onErrorTopics="handleError"--%>
                                                   <%--tabindex="2"/>--%>
                                    </td>
                                    <td dir="ltr" align="center" width="70px">

                                        <input type="hidden" id="project[<%=stdCounter%>]" name="project[<%=stdCounter%>]"/>
                                        <input type="button"
                                               id='<%="projectBtn" + stdCounter%>'
                                               name='<%="projectBtn" + stdCounter%>'
                                               value="<s:text name="button.project"/>"
                                               style="width: 60px;"
                                               <%--class="buttonForward"--%>
                                               onclick='<%="return projectClick(this," + stdCounter + ");"%>'
                                        />

                                        <%--<s:url id="advisorNameUrl" namespace="/" action="staffNameForPositionJsonAction"/>--%>
                                        <%--<sj:submit id="projectBtn"--%>
                                                   <%--name="projectBtn"--%>
                                                   <%--button="true"--%>
                                                   <%--value="%{getText('button.project')}"--%>
                                                   <%--href="%{advisorNameUrl}"--%>
                                                   <%--formIds="dailySessionHoldingInputForm"--%>
                                                   <%--buttonIcon="ui-icon-gear"--%>
                                                   <%--cssStyle="width: 60px;"--%>
                                                   <%--cssClass="buttonForward"--%>
                                                   <%--indicator="indicator1"--%>
                                                   <%--onSuccessTopics="fillStudentListGrid"--%>
                                                   <%--onErrorTopics="handleError"--%>
                                                   <%--tabindex="2"/>--%>
                                    </td>
                                    <td dir="ltr" align="center" width="50px">
                                        &nbsp;
                                    </td>

                                </tr>
                            </table>

                        </td>
                        <td dir="ltr" align="middle" valign="middle" width="50px">

                            <input  type="checkbox"
                                    id='<%="presentChk" + stdCounter%>'
                                    name='<%="presentChk" + stdCounter%>'
                                    value="<s:text name="label.present"/>"
                                    cssStyle="width: 50px;"
                                    checked="true"
                                    onclick='<%="return togglePresentStatus(this," + stdCounter + ");"%>'
                                    tabindex="2"
                            />
                        </td>
                        <td dir="ltr" align="center">
                            &nbsp;
                        </td>
                    </tr>
                    <%
                        }
                    %>

                    <tr class="bodyElementsRows" style="border: dashed #00008b">
                        <td colspan="11" style="height: 25px;">
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

<sj:dialog
        id="homeWorkDialog"
        showEffect="slide"
        hideEffect="explode"
        autoOpen="false"
        modal="true"
        title="Home work"
    >
    Complete homework?
</sj:dialog>

</s:div>
<s:token name="token"></s:token>
<s:hidden id="classId" name="classId" value="%{classId}" />
<input type="hidden" id="studentCount" name="studentCount" value="<%=studentNo%>"/>
<%--<s:hidden id="studentCount" name="studentCount" value="%{studentCountStr}" />--%>

</s:form>

<script type="text/javascript" language="javascript">

$(document).ready(function () {
//======================================================================================================================
//======================================================================================================================
});
      function okButton(btn,obj,arrayIndex) {
       alert(btn);
       alert(obj.disabled);
       alert(arrayIndex);
      }
//================================================== Java Script =======================================================
    var studentsCount = <%=studentNo%>;
    if(studentsCount == 0){
        addClientError('<s:text name="error.dailySessionHolding.emptyClass" />');
        getElement("saveButton").disabled = true;
    }
    var studentsTotalScore    = new Array(studentsCount);
    var studentsHomeWork      = new Array(studentsCount);
    var studentsKeyQuestions  = new Array(studentsCount);
    var studentsRightAnswers  = new Array(studentsCount);
    var studentsWarning       = new Array(studentsCount);
    var studentsProject       = new Array(studentsCount);

    var prmMaxRightAnswerCount  = <%=ServerConstants.PARAMETER_RIGHT_ANSWERS_COUNT%>;
    var prmMaxKeyQuestionsCount = <%=ServerConstants.PARAMETER_KEY_QUESTIONS_COUNT%>;

    for (var i = 0; i < studentsCount; i++){
        studentsTotalScore[i] = 0;
        studentsHomeWork[i] = 0;
        studentsKeyQuestions[i] = 0;
        studentsRightAnswers[i] = 0;
        studentsWarning[i] = 0;
        studentsProject[i] = 0;
    }
//======================================================================================================================
    function homeWorkClick(obj,arrayIndex) {

        $("#homeWorkDialog").dialog({
                                        autoOpen: false,
                                        title: 'Home work',
                                        modal:true,
                                        width:250,
                                        buttons: { "Yes": function() { homeWorkDialogClick("yes",obj,arrayIndex); },
                                                   "No" : function() { homeWorkDialogClick("no",obj,arrayIndex); }
                                                 }
                                    }
        );
        $("#homeWorkDialog").dialog('open');
    }
//======================================================================================================================
    function homeWorkDialogClick(btn,obj,arrayIndex) {

        if(obj.disabled == true){
            return;
        }
        if(btn == "yes"){
            getElement("homeWork[" + arrayIndex + "]").value = 2; // means complete Homework
            studentsHomeWork[arrayIndex] = 2;
            studentsTotalScore[arrayIndex] = studentsTotalScore[arrayIndex] + <%=ServerConstants.HOMEWORK_SCORE%>;
        }
        else {
            getElement("homeWork[" + arrayIndex + "]").value = 1; // means not complete Homework
            studentsHomeWork[arrayIndex] = 1;
            studentsTotalScore[arrayIndex] = studentsTotalScore[arrayIndex] + <%=ServerConstants.HOMEWORK_SCORE / 2%>;
        }
        getElement("todayScore" + arrayIndex ).innerHTML = studentsTotalScore[arrayIndex];
        obj.disabled = true;
        $("#homeWorkDialog").dialog('close');
    }
//======================================================================================================================
    function keyQuestionsClick(obj,arrayIndex) {

        var counter = parseInt(obj.value);
        counter--;
        studentsKeyQuestions[arrayIndex] = prmMaxKeyQuestionsCount - counter;
        getElement("keyQuestions[" + arrayIndex + "]").value = prmMaxKeyQuestionsCount - counter;
        studentsTotalScore[arrayIndex] = studentsTotalScore[arrayIndex] + <%=ServerConstants.KEY_QUESTION_SCORE%>;
        getElement("todayScore" + arrayIndex ).innerHTML = studentsTotalScore[arrayIndex];
        if(counter == 0){
            obj.disabled = true;
        }
        obj.value = counter;
    }
//======================================================================================================================
    function rightAnswersClick(obj,arrayIndex) {

        var counter = parseInt(obj.value);
        counter--;
        studentsRightAnswers[arrayIndex] = prmMaxRightAnswerCount - counter;
        getElement("rightAnswers[" + arrayIndex + "]").value = prmMaxRightAnswerCount - counter;
        studentsTotalScore[arrayIndex] = studentsTotalScore[arrayIndex] + <%=ServerConstants.RIGHT_ANSWER_SCORE%>;
        getElement("todayScore" + arrayIndex ).innerHTML = studentsTotalScore[arrayIndex];
        if(counter == 0){
            obj.disabled = true;
        }
        obj.value = counter;

    }
//======================================================================================================================
    function warningClick(obj,arrayIndex) {

        studentsWarning[arrayIndex] = 1;
        getElement("warning[" + arrayIndex + "]").value = 1;
        studentsTotalScore[arrayIndex] = studentsTotalScore[arrayIndex] + <%=ServerConstants.WARNING_SCORE%>;
        getElement("todayScore" + arrayIndex).innerHTML = studentsTotalScore[arrayIndex];
        obj.disabled = true;

    }
//======================================================================================================================
    function projectClick(obj,arrayIndex) {

        studentsProject[arrayIndex] = 1;
        getElement("project[" + arrayIndex + "]").value = 1;
        studentsTotalScore[arrayIndex] = studentsTotalScore[arrayIndex] + <%=ServerConstants.PROJECT_SCORE%>;
        getElement("todayScore" + arrayIndex ).innerHTML = studentsTotalScore[arrayIndex];
        obj.disabled = true;

    }
//======================================================================================================================
    function togglePresentStatus(obj,arrayIndex) {

        var presentChkElmId  = "presentChk" + arrayIndex;
        var tdElmId          = "groupBox" + arrayIndex;
        var homeworkElmId    = "homeWorkBtn" + arrayIndex;
        var keyQuestionElmId = "keyQuestionsBtn" + arrayIndex;
        var rightAnswerElmId = "rightAnswersBtn" + arrayIndex;
        var warningElmId     = "warningBtn" + arrayIndex;
        var projectElmId     = "projectBtn" + arrayIndex;

        if(getElement(presentChkElmId).checked == true){
            getElement(tdElmId).disabled = false;
            getElement(homeworkElmId).disabled = false;
            getElement(keyQuestionElmId).disabled = false;
            getElement(rightAnswerElmId).disabled = false;
            getElement(warningElmId).disabled = false;
            getElement(projectElmId).disabled = false;
            getElement("present[" + arrayIndex + "]").value = 1; // present
        }
        else{
            getElement(tdElmId).disabled = true;

            if(studentsHomeWork[arrayIndex] == 0)
                getElement(homeworkElmId).disabled = true;

            if(studentsKeyQuestions[arrayIndex] != prmMaxKeyQuestionsCount)
                getElement(keyQuestionElmId).disabled = true;

            if(studentsRightAnswers[arrayIndex] != prmMaxRightAnswerCount)
                getElement(rightAnswerElmId).disabled = true;

            if(studentsWarning[arrayIndex] == 0)
                getElement(warningElmId).disabled = true;

            if(studentsProject[arrayIndex] == 0)
                getElement(projectElmId).disabled = true;
            getElement("present[" + arrayIndex + "]").value = 0; // absent
        }
    }
//======================================================================================================================
    function submitData() {

        <%--for(var i = 0; i < <%=studentNo%>; i++){--%>
            <%--getElement("homeWork[" + i + "]").value = studentsHomeWork[i];--%>
            <%--getElement("keyQuestions[" + i + "]").value = studentsKeyQuestions[i];--%>
            <%--getElement("rightAnswers[" + i + "]").value = studentsRightAnswers[i];--%>
            <%--getElement("warning[" + i + "]").value = studentsWarning[i];--%>
            <%--getElement("project[" + i + "]").value = studentsProject[i];--%>
        <%--}--%>
        document.dailySessionHoldingInputForm.submit;
    }
//======================================================================================================================

</script>