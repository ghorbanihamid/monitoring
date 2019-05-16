<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>


<s:form id="changeEmailAddressInputForm" name="changeEmailAddressInputForm" action="changeEmailAddressSaveAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 650px">

<h2 align="center"><s:text name="changeEmailAddress.title"/></h2>

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
                            <s:text name="changeEmailAddress.newEmailAddress"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                            <s:textfield id="newEmailAddress" name="newEmailAddress" maxLength="30" required="true" theme="simple" cssClass="commonElement" onkeypress="return emailOnKeyPress(event);" tabindex="1"/>
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
                        <s:submit id="saveButton" key="button.save" action="changeEmailAddressSaveAction" cssClass="buttonForward" onclick="return validateForm();" tabindex="3"/>
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

        if(isNullOrEmpty(getElement("newEmailAddress").value)){
            addClientError('<s:text name="error.changeEmailAddress.newEmailRequired" />');
            getElement("newEmailAddress").focus();
            return false;
        }
        else if(!isEmailAddress(getElement("newEmailAddress").value)){
            addClientError('<s:text name="error.changeEmailAddress.invalidNewEmailAddress" />');
            getElement("newEmailAddress").focus();
            return false;
        }

        if(hasClientError)
            return false;

        return true;
    };
//======================================================================================================================

//======================================================================================================================
</script>
