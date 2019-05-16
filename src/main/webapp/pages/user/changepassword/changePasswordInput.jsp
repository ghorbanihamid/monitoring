<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>


<s:form id="changePasswordInputForm" name="changePasswordInputForm" action="changePasswordSaveAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 650px">

<h2 align="center"><s:text name="changePassword.title"/></h2>

<table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

    <tr class="bodyElementsRows">
        <td>
            <s:div cssStyle="overflow:hidden;height: 20px">
                <s:actionmessage id="serverMessageList" cssClass="actionMessageClass"/>
                <s:actionerror id="serverErrorList" cssClass="actionErrorClass"/>
                <ul id="clientErrorList" class="actionErrorClass"/>
            </s:div>
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td style="height: 25px;">
             &nbsp;
        </td>
    </tr>

    <tr  class="bodyElementsRows">
        <td>
            <div style="height: 20px; text-align: center;" class="ui-widget-header ui-corner-top">
                <s:text name="label.userInformation"/>
            </div>
            <s:div cssClass="type-text ui-state-default ui-corner-bottom"  cssStyle="margin-top: 0px">

                <table border="0" cellpadding="0" cellspacing="0" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                    <tr class="bodyElementsRows">
                        <td colspan="2" style="height: 25px;">
                             &nbsp;
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="200px">
                            <s:text name="changePassword.currentPassword"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                            <s:password id="currentPassword" name="currentPassword" maxLength="20" required="true" theme="simple" cssClass="commonElement" cssStyle="width: 200px" onkeypress="return englishAndNumberOnKeyPress(event);" tabindex="1" />
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="200px">
                            <s:text name="changePassword.newPassword"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                            <s:password id="newPassword" name="newPassword" maxLength="20" required="true" theme="simple" cssClass="commonElement" cssStyle="width: 200px" onkeypress="return englishAndNumberOnKeyPress(event);" tabindex="2"/>
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="200px">
                            <s:text name="changePassword.confirmNewPassword"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                            <s:password id="confirmNewPassword" name="confirmNewPassword" maxLength="20" required="true" theme="simple" cssClass="commonElement" cssStyle="width: 200px" onkeypress="return englishAndNumberOnKeyPress(event);" tabindex="3"/>
                        </td>
                    </tr>


                    <tr class="bodyElementsRows">
                        <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                            &nbsp;
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td colspan="2" style="height: 25px;">
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
                        <s:submit id="saveButton" key="button.save" action="changePasswordSaveAction" cssClass="buttonForward" onclick="return validateForm();" tabindex="3"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

</s:div>

</s:form>

<script type="text/javascript" language="javascript">

//======================================================================================================================
$(document).ready(function() {

//======================================================================================================================

});
//======================================================================================================================
    function validateForm(){

        removeClientErrors();

        var passMinLength = <%=ServerConstants.PasswordMinLength%>;
        var passMaxLength = <%=ServerConstants.PasswordMaxLength%>;

        if(isNullOrEmpty(getElement("currentPassword").value)){
            addClientError('<s:text name="error.changePassword.currentPasswordRequired" />');
            getElement("currentPassword").focus();
            return false;
        }
        else if(!isEnglishAndNumberText(getElement("currentPassword").value)){
            addClientError('<s:text name="error.changePassword.invalidCurrentPassword" />');
            getElement("currentPassword").focus();
            return false;
        }

        if(isNullOrEmpty(getElement("newPassword").value)){
            addClientError('<s:text name="error.changePassword.newPasswordRequired" />');
            getElement("newPassword").focus();
            return false;
        }
        else if(!isEnglishAndNumberText(getElement("newPassword").value)){
            addClientError('<s:text name="error.changePassword.invalidNewPassword" />');
            getElement("newPassword").focus();
            return false;
        }

        if(isNullOrEmpty(getElement("confirmNewPassword").value)){
            addClientError('<s:text name="error.changePassword.confirmNewPasswordRequired" />');
            getElement("confirmNewPassword").focus();
            return false;
        }
        else if(!isEnglishAndNumberText(getElement("confirmNewPassword").value)){
            addClientError('<s:text name="error.changePassword.invalidConfirmNewPassword" />');
            getElement("confirmNewPassword").focus();
            return false;
        }

        if(getElement("newPassword").value != getElement("confirmNewPassword").value){
            addClientError('<s:text name="error.changePassword.newPasswordAndConfirmNotEqual" />');
            getElement("newPassword").focus();
            return false;
        }

        if(getElement("currentPassword").value == getElement("newPassword").value){
            addClientError('<s:text name="error.changePassword.equalCurrentAndNewPassword" />');
            getElement("newPassword").focus();
            return false;
        }

        if(getElement("newPassword").value.length < passMinLength || getElement("newPassword").value.length > passMaxLength){
            addClientError('<s:text name="error.changePassword.newPasswordLengthIsWrong" />');
            getElement("newPassword").focus();
            return false;
        }

        if(isNumeric(getElement("newPassword").value)){
            addClientError('<s:text name="error.changePassword.newPasswordMustHasNumberAndCharacter" />');
            getElement("newPassword").focus();
            return false;
        }

        if(isEnglishText(getElement("newPassword").value)){
            addClientError('<s:text name="error.changePassword.newPasswordMustHasNumberAndCharacter" />');
            getElement("newPassword").focus();
            return false;
        }

        if(hasClientError)
            return false;

        return true;
    };
//======================================================================================================================

//======================================================================================================================
</script>
