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

<s:form namespace="/" id="assignClassStudentsInputForm" name="assignClassStudentsInputForm" action="assignClassStudentsInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 800px;height: 680px;vertical-align: top; resize: none;">

    <h2><s:text name="assignClassStudents.title"/></h2>

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
                    <s:text name="assignClassStudents.classStudents"/>
                </div>
                <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
                    <table width="100%">

                        <tr class="bodyElementsRows">

                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px">
                                <s:text name="label.termName"/>:
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="250px">
                                <sj:select id="termId"
                                           name="termId"
                                           href="termsListForCurrentAndNextTermAction.action"
                                           formIds="assignClassStudentsInputForm"
                                           list="termsListForCurrentAndNextTerm"
                                           listKey="termId"
                                           listValue="termName"
                                           dataType="json"
                                           cssClass="commonElement"
                                           cssStyle="height:20px; width:240px"
                                           headerKey="0"
                                           headerValue="%{getText('label.notDefined')}"
                                           emptyOption="false"
                                           tabindex="1"
                                        >
                                </sj:select>
                            </td>
                            <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                &nbsp;
                            </td>
                        </tr>

                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px">
                                <s:text name="label.studentId"/>
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="250px">
                                <s:textfield id="studentId" name="studentId" maxLength="7" required="true" validate="true" theme="simple" cssStyle="width: 90%" cssClass="commonElement" onkeypress="return numericOnKeyPress(event);" tabindex="3"/>
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px">
                                <s:url id="studentInfoUrl" namespace="/" action="assignClsStudentNameJsonAction"/>
                                <sj:a      id="fetchStdInfoBtn"
                                           button="true"
                                           value="%{getText('button.fetch')}"
                                           href="%{studentInfoUrl}"
                                           targets="studentNameSpan"
                                           formIds="assignClassStudentsInputForm"
                                           effect="pulsate"
                                           effectOptions="{mode:'show' , times:3}"
                                           effectDuration="1000"
                                           cssStyle="width: 70px;"
                                           buttonIcon="ui-icon-gear"
                                           indicator="indicator1"
                                           onBeforeTopics="validateStudentId"
                                           onErrorTopics="handleError"
                                           onSuccessTopics="handleJsonResult"
                                           onCompleteTopics="fetchClassList"
                                           tabindex="4"
                                >
                                    <s:text name="button.fetch"/>
                                </sj:a>
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                <img id="indicator1" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                                &nbsp;&nbsp;<span id="studentNameSpan"></span>
                            </td>
                        </tr>

                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px">
                                <s:text name="label.class"/>
                            </td>
                            <td colspan="3" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                <sj:select id="classId"
                                           name="classId"
                                           href="studentAssignableClassListAction.action"
                                           list="classListForAssignStudent"
                                           listKey="stringClassId"
                                           listValue="className"
                                           dataType="json"
                                           cssClass="commonElement"
                                           cssStyle="width: 400px"
                                           headerKey="0"
                                           headerValue="%{getText('label.notDefined')}"
                                           emptyOption="false"
                                           reloadTopics="fetchClassList"
                                           onChangeTopics="getClassFee"
                                           tabindex="2"
                                    >
                                </sj:select>
                            </td>

                        </tr>

                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px">
                                <s:text name="assignClassStudents.classFee"/>
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="250px">
                                <s:property value="classFee" />
                            </td>

                            <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                &nbsp;
                            </td>
                        </tr>

                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px">
                                <s:text name="assignClassStudents.studentPaidFee"/>
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="250px">
                                <s:textfield id="studentPaidFee" name="studentPaidFee" maxLength="10" required="false" theme="simple" cssStyle="width: 90%" onkeypress="return numericOnKeyPress(event);" tabindex="5"/>
                            </td>

                            <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                &nbsp;
                            </td>
                        </tr>

                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px">
                                <s:text name="assignClassStudents.discountFee"/>
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="250px">
                                <s:textfield id="discountFee" name="discountFee" maxLength="10" required="false" theme="simple" cssStyle="width: 90%" onkeypress="return numericOnKeyPress(event);" tabindex="6"/>
                            </td>

                            <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                &nbsp;
                            </td>
                        </tr>

                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px">
                                <s:text name="assignClassStudents.payType"/>
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="250px">
                                <sj:select id="payType"
                                           name="payType"
                                           href="feePayTypesListAction.action"
                                           formIds="assignClassStudentsInputForm"
                                           list="feePayTypesList"
                                           listKey="key"
                                           listValue="value"
                                           dataType="json"
                                           cssClass="commonElement"
                                           cssStyle="height:20px; width:240px"
                                           headerKey="0"
                                           headerValue="%{getText('label.notDefined')}"
                                           emptyOption="false"
                                           tabindex="7"
                                        >
                                </sj:select>
                            </td>

                            <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                &nbsp;
                            </td>
                        </tr>

                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px">
                                <s:text name="assignClassStudents.receiptNo"/>
                            </td>
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="250px">
                                <s:textfield id="receiptNo" name="receiptNo" maxLength="10" required="false" theme="simple" cssStyle="width: 90%" onkeypress="return numericOnKeyPress(event);" tabindex="8"/>
                            </td>

                            <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                &nbsp;
                            </td>
                        </tr>

                    </table>
                </s:div>
            </td>
        </tr>
        <tr>
            <td>
                <table dir="<s:text name="dir.ltr" />" width="100%">
                    <tr>
                        <td dir="<s:text name="dir.ltr" />" align="center">
                            <s:submit id="registerButton" key="button.register" action="assignClassStudentsRegisterAction" cssStyle="width: 100px;" cssClass="buttonForward"  onclick="return validateRegisterForm();" tabindex="9"/>
                            &nbsp;&nbsp;&nbsp;
                            <s:submit id="unregisterButton" key="button.cancelRegisteration" action="assignClassStudentsUnRegisterAction" cssStyle="width: 100px;" cssClass="buttonForward"  onclick="return validateUnRegisterForm();" tabindex="10"/>
                        </td>
                    </tr>
                </table>
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
                        id="stdCurrTermClassesGrid"
                        caption="%{getText('assignClassStudents.studentsCurrentClasses')}"
                        dataType="local"
                        formIds="assignClassStudentsInputForm"
                        pager="true"
                        gridModel="studentCurrentClassesList"
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
                        width="800"
                        shrinkToFit="false"
                        >
                    <sjg:gridColumn name="stringClassId"      index="stringClassId"    title="%{getText('label.classId')}"                  width="110" align="center" sortable="true"  search="true"  searchoptions="{sopt:['eq']}"/>
                    <sjg:gridColumn name="className"          index="className"        title="%{getText('label.className')}"                width="350" align="center" sortable="false" search="false" />
                    <sjg:gridColumn name="registerDate"       index="registerDate"     title="%{getText('label.registerDate')}"             width="100" align="center" sortable="false" search="false" formatter="dateFormatter"/>
                    <sjg:gridColumn name="classFee"           index="classFee"         title="%{getText('label.classFee')}"                 width="100" align="center" sortable="false" search="false" />
                    <sjg:gridColumn name="receiptNo"          index="receiptNo"        title="%{getText('assignClassStudents.receiptNo')}"  width="100" align="center" sortable="false" search="false" />

                </sjg:grid>
            </td>
        </tr>
    </table>

</s:div>

</s:form>


<script type="text/javascript" language="javascript">

$(document).ready(function () {
//======================================================================================================================
    $.subscribe('validateStudentId', function(event,data) {

        removeClientErrors();
        getElement("studentNameSpan").innerHTML = "";

        if(getElement("termId").selectedIndex == 0){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.termRequired" />');
            getElement("termId").focus();
            return false;
        }

        if(isNullOrEmpty(getElement("studentId").value)){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.studentIdRequired" />');
            getElement("studentId").focus();
            return false;
        }
        else if(!isNumeric(getElement("studentId").value)){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.invalidStudentId" />');
            getElement("studentId").focus();
            return false;
        }
        else if(getElement("studentId").value == 0){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.invalidStudentId" />');
            getElement("studentId").focus();
            return false;
        }

        return true;

    });
//======================================================================================================================
    $.subscribe('getClassFee', function(event,data){

        removeClientErrors();

        var elmValue = getElement("classId").selectedIndex;

        if(isNullOrEmpty(elmValue) || elmValue == 0){
            $('#classFee').val("0");
            return false;
        }

        $.ajax({
            cache:false,
            url: "ajax/selectedClassFeeJsonAction.action",
            type: "POST",
            data: {classType: $("#classId").val()},
            dataType: "json",

            error: function() {
                addClientError('<s:text name="error.assignClassStudents.invalidClassId" />');
                $('#classFee').val("");
            },
            success: function(tmpClassFee) {
                $('#classFee').val(tmpClassFee);
            }
        });
        return false;
    });
//======================================================================================================================

//======================================================================================================================
});
//======================================================================================================================
    function validateRegisterForm(){

        removeClientErrors();

        if(isNullOrEmpty(getElement("studentId").value)){
            addClientError('<s:text name="error.common.studentIdRequired" />');
            getElement("studentId").focus();
        }
        else if(!isNumeric(getElement("studentId").value)){
            addClientError('<s:text name="error.common.invalidStudentId" />');
            getElement("studentId").focus();
        }

        if(getElement("classId").selectedIndex == 0){
            addClientError('<s:text name="error.common.classIdRequired" />');
            getElement("classId").focus();
        }

        if(!isNullOrEmpty(getElement("receiptNo").value)){
            if(!isNumeric(getElement("receiptNo").value)){
                addClientError('<s:text name="error.assignClassStudents.invalidReceiptNo" />');
                getElement("receiptNo").focus();
            }
        }

        if(isNullOrEmpty(getElement("classFee").value)){
            addClientError('<s:text name="error.assignClassStudents.classFeeRequired" />');
            getElement("classFee").focus();
        }
        else if(!isNumeric(getElement("classFee").value)){
            addClientError('<s:text name="error.assignClassStudents.invalidClassFee" />');
            getElement("classFee").focus();
        }

        if(hasClientError)
            return false;

        return true;
    };
//======================================================================================================================
//======================================================================================================================
    function validateUnRegisterForm(){

        removeClientErrors();

        if(isNullOrEmpty(getElement("studentId").value)){
            addClientError('<s:text name="error.common.studentIdRequired" />');
            getElement("studentId").focus();
        }

        if(isNullOrEmpty(getElement("studentId").value)){
            addClientError('<s:text name="error.common.classIdRequired" />');
            getElement("studentId").focus();
        }

        if(getElement("classId").selectedIndex == 0){
            addClientError('<s:text name="error.common.classIdRequired" />');
            getElement("classId").focus();
        }

        if(hasClientError)
            return false;

        return true;
    };
//======================================================================================================================
    $.subscribe('handleError', function(event,data) {
        removeClientErrors();
        addClientError(event.originalEvent.request.statusText);
    });
//======================================================================================================================
    $.subscribe('handleJsonResult', function(event,data) {

        $("#stdCurrTermClassesGrid").jqGrid('clearGridData',true);
        getElement("studentNameSpan").innerHTML = "";
        var jsonObjects = eval('(' + event.originalEvent.data + ')');
        if(jsonObjects != null){
            var studentDesc = jsonObjects.studentName;
            if(studentDesc != null){
                getElement("studentNameSpan").innerHTML = studentDesc;
            }
            else {
                addClientError('<s:text name="error.common.invalidStudentId" />');
                return;
            }
            var gridData = jsonObjects.studentCurrentClassesList;
            if(gridData != null && gridData.length > 0){
                for(var i = 0; i < gridData.length;i++){
                    $("#stdCurrTermClassesGrid").jqGrid('addRowData',i+1,gridData[i]);
                }
            }
        }
        $("#classId").trigger("reloadGrid");
    });
//======================================================================================================================
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