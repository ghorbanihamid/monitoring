<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>


<s:form id="resetPasswordInputForm" name="resetPasswordInputForm" action="resetPasswordSaveAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 650px">

<h2 align="center"><s:text name="resetPassword.title"/></h2>



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
                        <td style="height: 25px;">
                             &nbsp;
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="90px">
                            <s:text name="label.userName"/>:
                        </td>
                        <td align="center" dir="<s:text name="dir.ltr" />" width="100px">
                            <s:textfield id="userName" name="userName" maxLength="6" required="true" theme="simple" cssStyle="width: 80px" cssClass="commonElement" tabindex="1"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px">
                            <s:url id="userInfoForResetPasswordUrl" namespace="/" action="userInfoForResetPasswordJsonAction"/>
                            <sj:submit button="true"
                                       value="%{getText('button.fetch')}"
                                       href="%{userInfoForResetPasswordUrl}"
                                       targets="userInfoDescSpan"
                                       formIds="resetPasswordInputForm"
                                       effect="pulsate"
                                       effectOptions="{mode:'show' , times:3}"
                                       effectDuration="1500"
                                       cssStyle="width: 50px;"
                                       cssClass="buttonForward"
                                       indicator="indicator1"
                                       onBeforeTopics="validateUserName"
                                       onErrorTopics="handleError"
                                       tabindex="2"
                            />
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                            <img id="indicator1" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                            &nbsp;&nbsp;<span id="userInfoDescSpan"></span>
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td colspan="4" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                            &nbsp;
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td style="height: 25px;">
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
                        <s:submit id="resetButton" key="button.resetPassword" action="resetPasswordSaveAction" cssClass="buttonForward" cssStyle="width: 85px" onclick="return validateForm();" tabindex="3"/>
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
    $.subscribe('validateUserName', function(event,data) {

        removeClientErrors();
        getElement("userInfoDescSpan").innerHTML = "";

        if(isNullOrEmpty(getElement("userName").value)){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.userNameRequired" />');
            getElement("userName").focus();
            return false;
        }
        else if(!isEnglishText(getElement("userName").value)){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.invalidUserName" />');
            getElement("userName").focus();
            return false;
        }
        return true;

    });
//======================================================================================================================
    $.subscribe('handleError', function(event,data) {

        removeClientErrors();
        addClientError(event.originalEvent.request.statusText);
    });

//======================================================================================================================

});
//======================================================================================================================
    function validateForm(){

        removeClientErrors();

        if(isNullOrEmpty(getElement("userName").value)){
            addClientError('<s:text name="error.common.userNameRequired" />');
            getElement("userName").focus();
            return false;
        }
        else if(!isEnglishText(getElement("userName").value)){
            addClientError('<s:text name="error.common.invalidUserName" />');
            getElement("userName").focus();
            return false;
        }

        if(hasClientError)
            return false;

        return true;
    };
//======================================================================================================================

//======================================================================================================================
</script>
