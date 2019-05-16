<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="kli" tagdir="/WEB-INF/tags" %>

<%

%>

<s:form namespace="/" id="staffInputForm" name="staffInputForm" action="staffInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 680px;vertical-align: top;resize: none;">

<h2 align="center"><s:text name="staff.registerNewStaff"/></h2>

<table border="0" cellpadding="0" cellspacing="0"  align="center" dir="<s:text name="dir.ltr" />" width="70%">

    <tr class="bodyElementsRows">
        <td>
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
        <td>
            <div style="height: 20px; text-align: center;" class="ui-widget-header ui-corner-top">
                <s:text name="label.editInformation"/>
            </div>
            <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
                <table width="100%">
                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px;">
                            <s:text name="label.editInformation"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px;">
                            <s:checkbox id="editMode" name="editMode" theme="simple" onclick="return handleEditCheckBox();" cssStyle="width: 20px" tabindex="1"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px;">
                            &nbsp;&nbsp;&nbsp;<s:text name="label.staffId"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px;">
                            <s:textfield id="staffId" name="staffId" maxLength="15" theme="simple" cssStyle="width: 100px;" onkeypress="return numericOnKeyPress(event);" tabindex="2"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">

                            <s:url id="staffInfoUrl" namespace="/" action="staffInfoJsonAction" />
                            <sj:a
                                       id="fetchInfoButton"
                                       button="true"
                                       value="%{getText('button.fetch')}"
                                       href="%{staffInfoUrl}"
                                       formIds="staffInputForm"
                                       dataType="json"
                                       cssStyle="width: 70px;"
                                       buttonIcon="ui-icon-gear"
                                       indicator="indicator1"
                                       onBeforeTopics="validateForFetch"
                                       onSuccessTopics="handleSuccess"
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
                </table>
            </s:div>
        </td>
    </tr>
    <tr class="bodyElementsRows">
        <td>
            <div style="height: 20px; text-align: center;" class="ui-widget-header ui-corner-top">
                <s:text name="label.personalInformation"/>
            </div>
            <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
                <table width="100%">
                    <tr class="bodyElementsRows">
                        <td colspan="4" width="100%">
                            &nbsp;
                        </td>
                    </tr>
                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px;">
                            &nbsp;&nbsp;&nbsp;<s:text name="label.staffId"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px;">
                            <s:textfield id="newStaffId" name="newStaffId" maxLength="15" theme="simple" cssStyle="width: 100px;" onkeypress="return numericOnKeyPress(event);" tabindex="2"/>
                        </td>
                        <td colspan="2">
                            &nbsp;
                        </td>
                    </tr>
                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="label.olName"/>&nbsp;<span class="asterisk">*</span>&nbsp;
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                            <s:textfield id="staffOlName" name="staffOlName" maxLength="30" theme="simple" cssStyle="width: 90%" onkeypress="return persianOnKeyPress(event);" tabindex="4"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="label.olFamily"/>&nbsp;<span class="asterisk">*</span>&nbsp;
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                            <s:textfield id="staffOlFamily" name="staffOlFamily" maxLength="30" theme="simple" cssStyle="width: 90%" onkeypress="return persianOnKeyPress(event);" tabindex="5"/>
                        </td>
                    </tr>
                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="label.enName"/>&nbsp;<span class="asterisk">*</span>&nbsp;
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                            <s:textfield id="staffEnName" name="staffEnName" maxLength="30" theme="simple" cssStyle="width: 90%" onkeypress="return englishOnKeyPress(event);" tabindex="6"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="label.enFamily"/>&nbsp;<span class="asterisk">*</span>&nbsp;
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                            <s:textfield id="staffEnFamily" name="staffEnFamily" maxLength="30" theme="simple" cssStyle="width: 90%" onkeypress="return englishOnKeyPress(event);" tabindex="7"/>
                        </td>

                    </tr>

                    <tr class="bodyElementsRows">
                        <td colspan="4" width="100%">
                            &nbsp;
                        </td>
                    </tr>

                </table>
            </s:div>
        </td>
    </tr>
    <tr class="bodyElementsRows">
        <td>
            <div style="height: 20px; text-align: center;" class="ui-widget-header ui-corner-top">
                <s:text name="label.contactInformation"/>
            </div>
            <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
                <table width="100%">
                    <tr class="bodyElementsRows">
                        <td colspan="4" width="100%">
                            &nbsp;
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="label.homePhone"/>&nbsp;<span class="asterisk">*</span>&nbsp;
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                            <s:textfield id="homePhone" name="homePhone" maxLength="20" required="false" theme="simple" cssStyle="width: 90%" onkeypress="return numericOnKeyPress(event);" tabindex="20"/>
                        </td>

                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="label.cellPhone"/>&nbsp;<span class="asterisk">*</span>&nbsp;
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                            <s:textfield id="cellPhone" name="cellPhone" maxLength="20" required="false" theme="simple" cssStyle="width: 90%" onkeypress="return numericOnKeyPress(event);" tabindex="21"/>
                        </td>

                    </tr>

                    <tr class="bodyElementsRows">

                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="label.workPhone"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                            <s:textfield id="workPhone" name="workPhone" maxLength="20" theme="simple" cssStyle="width: 90%" onkeypress="return numericOnKeyPress(event);" tabindex="23"/>
                        </td>

                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="label.emailAddress"/>&nbsp;<span class="asterisk">*</span>&nbsp;
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                            <s:textfield id="emailAddress" name="emailAddress" maxLength="30" theme="simple" cssStyle="width: 90%" onkeypress="return emailOnKeyPress(event);" tabindex="24"/>
                        </td>

                    </tr>
                    <tr class="bodyElementsRows">
                        <td colspan="4" width="100%">
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
                <tr class="bodyElementsRows">
                    <td width="100%">
                        &nbsp;
                    </td>
                </tr>
                <tr>
                    <td dir="<s:text name="dir.ltr" />" align="center">
                        <s:submit id="saveButton" name="saveButton" key="button.save" action="staffSaveAction" cssStyle="width: 75px;" cssClass="buttonForward" onclick="return validateStaffForm();" tabindex="28"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>

</table>

</s:div>

</s:form>

<script type="text/javascript" language="javascript">

$(document).ready(function() {
//======================================================================================================================
    handleEditCheckBox();
//======================================================================================================================
    $(function($){
        $("#birthDate").mask("1399/99/99");
    });
//======================================================================================================================
    $.subscribe('validateForFetch', function(event,data) {

        removeClientErrors();

        var elmValue = getElement("staffId").value;

        if(isNullOrEmpty(elmValue)){
            addClientError('<s:text name="error.common.staffIdRequired" />');
            getElement("staffId").focus();
            return false;
        }
        else if(!isNumeric(elmValue)){
            addClientError('<s:text name="error.common.invalidStaffId" />');
            getElement("staffId").focus();
            return false;
        }
        else if(elmValue == 0){
            addClientError('<s:text name="error.common.invalidStaffId" />');
            getElement("staffId").focus();
            return false;
        }

        return true;

    });
//======================================================================================================================
    $.subscribe('handleError', function(event,data) {
        removeClientErrors();
        addClientError(event.originalEvent.request.statusText);
        clearFields();
        $('#staffId').focus();

    });
//======================================================================================================================
//======================================================================================================================
    function clearFields() {

        $('#staffOlName').val("");
        $('#staffOlFamily').val("");
        $('#staffEnName').val("");
        $('#staffEnFamily').val("");
        $('#fatherName').val("");
        $('#nickName').val("");
        $('#staffId').val("");
        $('#birthDate').val("");
        $('#idNumber').val("");
        $('#nationalCode').val("");
        $('#genderStatus').val("");
        $('#maritalStatus').val("");
        $('#studyField').val("");
        $('#educationLevel').val("");
        $('#branchId').val("");
        $('#homePhone').val("");
        $('#workPhone').val("");
        $('#cellPhone').val("");
        $('#emailAddress').val("");
        $('#homeAddress').val("");
        $('#workAddress').val("");
        $('#workAddress').val("");
        $('#leftHand').selected(false);
        $('#physicalProblem').selected(false);
        $('#physicalProblemExp').val("");

    };
//======================================================================================================================
    $.subscribe('handleSuccess', function(event,data) {
        removeClientErrors();

        var staffInfo = event.originalEvent.data;

        $('#staffOlName').val(staffInfo.staffOlName);
        $('#staffOlFamily').val(staffInfo.staffOlFamily);
        $('#staffEnName').val(staffInfo.staffEnName);
        $('#staffEnFamily').val(staffInfo.staffEnFamily);
        $('#fatherName').val(staffInfo.fatherName);
        $('#nickName').val(staffInfo.nickName);
        $('#birthDate').val(addDateSeparator(staffInfo.birthDate));
        $('#idNumber').val(staffInfo.idNumber);
        $('#nationalCode').val(staffInfo.nationalCode);
        $('#genderStatus').val(staffInfo.genderStatus);
        $('#maritalStatus').val(staffInfo.maritalStatus);
        $('#studyField').val(staffInfo.studyField);
        $('#educationLevel').val(staffInfo.educationLevel);
        $('#branchId').val(staffInfo.branchId);
        $('#homePhone').val(staffInfo.homePhone);
        $('#workPhone').val(staffInfo.workPhone);
        $('#cellPhone').val(staffInfo.cellPhone);
        $('#emailAddress').val(staffInfo.emailAddress);
        $('#homeAddress').val(staffInfo.homeAddress);
        $('#workAddress').val(staffInfo.workAddress);
        $('#leftHand').selected(staffInfo.leftHand);
        $('#physicalProblem').selected(staffInfo.physicalProblem);
        $('#physicalProblemExp').val(staffInfo.physicalProblemExp);
        $('#staffOlName').focus();
    });
//======================================================================================================================
});

//======================================================================================================================
    function handleEditCheckBox(){

        if($('#editMode').is(':checked')){
            $('#staffId').attr('disabled', false);
            $('#newStaffId').attr('disabled', true);
            $('#fetchInfoButton').attr('disabled', false);
        }
        else{
            $('#staffId').attr('disabled', true);
            $('#newStaffId').attr('disabled', false);
            $('#fetchInfoButton').attr('disabled', true);
        }
    }
//======================================================================================================================
    function fetchStaffInfo(){

        removeClientErrors();
        if(isNullOrEmpty(getElement("staffId").value)){
            addClientError('<s:text name="error.common.staffIdRequired" />');
            getElement("staffId").focus();
        }
        else if(!isNumeric(getElement("staffId").value)){
            addClientError('<s:text name="error.common.invalidStaffId" />');
            getElement("staffId").focus();
        }
        $.ajax({
            cache:false,
            url: "ajax/staffInfoJsonAction.action",
            type: "POST",
            data: {staffId: $("#staffId").val()},
            dataType: "json",

            error: function() {
                addClientError('<s:text name="error.common.staffIdNotFound" />');
                $('#staffOlName').val("");
                $('#staffOlFamily').val("");
                $('#staffEnName').val("");
                $('#staffEnFamily').val("");
                $('#staffId').val("");
                $('#newStaffId').val("");
                $('#homePhone').val("");
                $('#workPhone').val("");
                $('#cellPhone').val("");
                $('#emailAddress').val("");
                getElement("staffId").focus();

            },
            success: function(staff) {
                $('#newStaffId').val(staff.staffId);
                $('#staffOlName').val(staff.staffOlName);
                $('#staffOlFamily').val(staff.staffOlFamily);
                $('#staffEnName').val(staff.staffEnName);
                $('#staffEnFamily').val(staff.staffEnFamily);
                $('#homePhone').val(staff.homePhone);
                $('#workPhone').val(staff.workPhone);
                $('#cellPhone').val(staff.cellPhone);
                $('#emailAddress').val(staff.emailAddress);
                getElement("staffOlName").focus();
            }
        });
    }

//======================================================================================================================
    function validateStaffForm(){

        removeClientErrors();

        if(isNullOrEmpty(getElement("staffOlName").value)){
            addClientError('<s:text name="error.common.olNameRequired" />');
            getElement("staffOlName").focus();
        }
        else if(!isPersianText(getElement("staffOlName").value)){
            addClientError('<s:text name="error.common.invalidOlName" />');
            getElement("staffOlName").focus();
        }

        if(isNullOrEmpty(getElement("staffOlFamily").value)){
            addClientError('<s:text name="error.common.olFamilyRequired" />');
            getElement("staffOlFamily").focus();
        }
        else if(!isPersianText(getElement("staffOlFamily").value)){
            addClientError('<s:text name="error.common.invalidOlFamily" />');
            getElement("staffOlFamily").focus();
        }

        if(isNullOrEmpty(getElement("staffEnName").value)){
            addClientError('<s:text name="error.common.enNameRequired" />');
            getElement("staffEnName").focus();
        }
        else if(!isEnglishText(getElement("staffEnName").value)){
            addClientError('<s:text name="error.common.invalidEnName" />');
            getElement("staffEnName").focus();
        }

        if(isNullOrEmpty(getElement("staffEnFamily").value)){
            addClientError('<s:text name="error.common.enFamilyRequired" />');
            getElement("staffEnFamily").focus();
        }
        else if(!isEnglishText(getElement("staffEnFamily").value)){
            addClientError('<s:text name="error.common.invalidEnFamily" />');
            getElement("staffEnFamily").focus();
        }

        if(hasClientError)
            return false;

        if(isNullOrEmpty(getElement("homePhone").value)){
            addClientError('<s:text name="error.common.homePhoneRequired" />');
            getElement("homePhone").focus();
        }

        if(!isNumeric(getElement("homePhone").value)){
            addClientError('<s:text name="error.common.homePhoneRequired" />');
            getElement("homePhone").focus();
        }

        if(isNullOrEmpty(getElement("cellPhone").value)){
            addClientError('<s:text name="error.common.cellPhoneRequired" />');
            getElement("cellPhone").focus();
        }

        if(!isNumeric(getElement("cellPhone").value)){
            addClientError('<s:text name="error.common.invalidCellPhone" />');
            getElement("cellPhone").focus();
        }

        if(!isNullOrEmpty(getElement("workPhone").value)){
            if(!isNumeric(getElement("workPhone").value)){
                addClientError('<s:text name="error.common.invalidWorkPhone" />');
                getElement("workPhone").focus();
            }
        }

        if(isNullOrEmpty(getElement("emailAddress").value)){
            addClientError('<s:text name="error.common.emailAddressRequired" />');
            getElement("emailAddress").focus();
        }
        else if(!isEmailAddress(getElement("emailAddress").value)){
            addClientError('<s:text name="error.common.invalidEmailAddress" />');
            getElement("emailAddress").focus();
        }

        if(hasClientError)
            return false;

        return true;
    };
//======================================================================================================================

</script>
