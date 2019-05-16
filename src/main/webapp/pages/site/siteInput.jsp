<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="kli" tagdir="/WEB-INF/tags" %>

<%

%>

<s:form namespace="/" id="siteInputForm" name="siteInputForm" action="siteInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 680px;vertical-align: top; resize: none;">

<h2 align="center"><s:text name="site.registerSiteTitle"/></h2>

<table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

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
                            <s:checkbox id="editMode" name="site.editMode" theme="simple" onclick="return handleEditCheckBox();" cssStyle="width: 20px" tabindex="1"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px;">
                            &nbsp;&nbsp;&nbsp;<s:text name="label.siteId"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px;">
                            <s:textfield id="siteId" name="site.siteId" maxLength="15" theme="simple" cssStyle="width: 100px;" onkeypress="return numericOnKeyPress(event);" tabindex="2"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                            <s:url id="siteInfoUrl" namespace="/" action="siteInfoJsonAction" />
                            <sj:a
                                   id="fetchInfoButton"
                                   button="true"
                                   value="%{getText('button.fetch')}"
                                   href="%{siteInfoUrl}"
                                   formIds="siteInputForm"
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
                             <img id="indicator1" name="indicator1" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                        </td>
                    </tr>
                </table>
            </s:div>
        </td>
    </tr>
    <tr class="bodyElementsRows">
        <td>
            <sj:tabbedpanel id="localtabs">
                <sj:tab id="personalInfoTab"      target="personalInfoDiv"      label="%{getText('label.personalInformation')}"/>

                <s:div id="personalInfoDiv" name="personalInfoDiv" cssStyle="overflow:hidden;height: 400px">
                    <s:div cssClass="type-text ui-state-default ui-corner-all" cssStyle="margin-top: 0px">
                        <table width="100%">
                            <tr class="bodyElementsRows">
                                <td colspan="4" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                                    &nbsp;
                                </td>
                            </tr>

                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                                    <s:text name="label.siteName"/>&nbsp;<span class="asterisk">*</span>&nbsp;
                                </td>
                                <td colspan="3" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                                    <s:textfield id="siteName" name="site.siteName" maxLength="30"  theme="simple" cssStyle="width: 95%"  onkeypress="return persianOnKeyPress(event);" tabindex="4"/>
                                </td>

                            </tr>

                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%;">
                                    &nbsp;&nbsp;&nbsp;<s:text name="label.staffId"/>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%;">
                                    <s:textfield id="managerId" name="site.managerId" maxLength="15" theme="simple" cssStyle="width: 100px;" onkeypress="return numericOnKeyPress(event);" tabindex="2"/>
                                    <s:url id="staffInfoUrl" namespace="/" action="staffInfoJsonAction" />
                                    <sj:a
                                            id="fetchManagerNameBtn"
                                            name="fetchManagerNameBtn"
                                            button="true"
                                            value="%{getText('button.fetch')}"
                                            href="%{staffInfoJsonAction}"
                                            formIds="siteInputForm"
                                            effect="pulsate"
                                            effectDuration="1500"
                                            effectOptions="{mode:'show' , times:3}"
                                            targets="managerNameSpan"
                                            cssStyle="width: 70px;"
                                            buttonIcon="ui-icon-gear"
                                            indicator="indicatorFetch"
                                            onBeforeTopics="validateTeacherId"
                                            onErrorTopics="handleError"
                                            tabindex="26">
                                        <s:text name="button.fetch"/>
                                    </sj:a>
                                    <img id="indicatorFetch" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                                </td>
                                <td colspan="2" id="managerNameTD" align="center" dir="<s:text name="dir.ltr" />" width="50%">
                                    <span id="managerNameSpan" name="managerNameSpan">
                                        &nbsp;
                                    </span>
                                </td>

                            </tr>

                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                                    <s:text name="label.telephoneNo1"/>&nbsp;<span class="asterisk">*</span>&nbsp;
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                                    <s:textfield id="siteTelNo1" name="site.siteTelNo1" maxLength="20" theme="simple" cssStyle="width: 90%" onkeypress="return numericOnKeyPress(event);" tabindex="20"/>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                                    <s:text name="label.telephoneNo2"/>&nbsp;<span class="asterisk">*</span>&nbsp;
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                                    <s:textfield id="siteTelNo2" name="site.siteTelNo2" maxLength="20" theme="simple" cssStyle="width: 90%" onkeypress="return numericOnKeyPress(event);" tabindex="21"/>
                                </td>

                            </tr>

                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                                    <s:text name="label.telephoneNo1"/>:
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                                    <s:textfield id="siteTelNo3" name="site.siteTelNo3" maxLength="20" theme="simple" cssStyle="width: 90%" onkeypress="return numericOnKeyPress(event);" tabindex="23"/>
                                </td>

                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                                    <s:text name="label.faxNo"/>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                                    <s:textfield id="siteFax" name="site.siteFax" maxLength="30" theme="simple" cssStyle="width: 90%" onkeypress="return emailOnKeyPress(event);" tabindex="24"/>
                                </td>

                            </tr>

                            <tr class="bodyElementsRows">
                                <td colspan="4" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                                    &nbsp;
                                </td>
                            </tr>
                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr"/>" width="20%">
                                    <s:text name="label.description"/>:
                                </td>
                                <td colspan="3" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr"/>">
                                    <s:textarea id="decription" name="site.decription" cols="25" rows="3" theme="simple" cssStyle="width: 96%" tabindex="27"/>
                                </td>
                            </tr>

                        </table>
                    </s:div>
                </s:div>

            </sj:tabbedpanel>
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td>
        </td>
    </tr>
    <tr class="bodyElementsRows">
        <td>
            <table dir="<s:text name="dir.ltr" />" width="100%">
                <tr class="bodyElementsRows">
                    <td dir="<s:text name="dir.ltr" />" align="center">
                        <s:submit id="saveButton" name="saveButton" key="button.save" action="siteSaveAction" cssStyle="width: 75px;" cssClass="buttonForward" onclick="return validateSiteForm();" tabindex="30"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>

</table>

</s:div>

<input type="hidden" id="registerDate" name="registerDate"/>

</s:form>

<script type="text/javascript" language="javascript">

$(document).ready(function() {
    //======================================================================================================================
    handleEditCheckBox();
    //======================================================================================================================
    $.subscribe('validateForFetch', function(event,data) {

        removeClientErrors();
        var elmValue = getElement("siteId").value;

        if(isNullOrEmpty(elmValue)){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.siteIdRequired" />');
            getElement("siteId").focus();
            return false;
        }
        else if(!isNumeric(elmValue)){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.invalidSiteId" />');
            getElement("siteId").focus();
            return false;
        }
        else if(elmValue == 0){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.invalidSiteId" />');
            getElement("siteId").focus();
            return false;
        }
        return true;
    });
    //======================================================================================================================
    $.subscribe('handleError', function(event,data) {
        removeClientErrors();
        addClientError(event.originalEvent.request.statusText);
        clearFields();
        $('#siteId').focus();

    });
    //======================================================================================================================
    function clearFields() {

        $('#siteId').val("");
        $('#managerId').val("");
        $('#siteName').val("");
        $('#siteTelNo1').val("");
        $('#siteTelNo2').val("");
        $('#siteTelNo3').val("");
        $('#siteFax').val("");
        $('#siteStatus').val("");
        $('#description').val("");
    };
    //======================================================================================================================
    $.subscribe('handleSuccess', function(event,data) {
        removeClientErrors();

        var siteInfo = event.originalEvent.data;

        $('#siteId').val(siteInfo.siteId);
        $('#managerId').val(siteInfo.managerId);
        $('#siteName').val(siteInfo.siteName);
        $('#siteTelNo1').val(siteInfo.siteTelNo1);
        $('#siteTelNo2').val(siteInfo.siteTelNo2);
        $('#siteTelNo3').val(addDateSeparator(siteInfo.siteTelNo3));
        $('#siteFax').val(siteInfo.siteFax);
        $('#description').val(siteInfo.description);
        $('#siteStatus').val(siteInfo.siteStatus);
        $('#siteName').focus();
    });
    //======================================================================================================================

});

    //======================================================================================================================
    function handleEditCheckBox(){

        if($('#editMode').is(':checked')){
            $('#siteId').attr('disabled', false);
            $('#fetchInfoButton').attr('disabled', false);
        }
        else{
            $('#siteId').attr('disabled', true);
            $('#fetchInfoButton').attr('disabled', true);
        }

    }
    //======================================================================================================================
    function validateSiteForm(){

        removeClientErrors();

        if(isNullOrEmpty(getElement("siteName").value)){
            addClientError('<s:text name="error.common.nameRequired" />');
            getElement("siteName").focus();
        }
        else if(!isPersianText(getElement("siteName").value)){
            addClientError('<s:text name="error.common.invalidName" />');
            getElement("siteName").focus();
        }

        if(isNullOrEmpty(getElement("managerId").value)){
            addClientError('<s:text name="error.common.olFamilyRequired" />');
            getElement("managerId").focus();
        }
        else if(!isPersianText(getElement("managerId").value)){
            addClientError('<s:text name="error.common.invalidOlFamily" />');
            getElement("managerId").focus();
        }

        if(hasClientError)
            return false;

        if(!isNullOrEmpty(getElement("siteTelNo1").value)){
            if(!isNumeric(getElement("siteTelNo1").value)) {
                addClientError('<s:text name="error.common.invalidHomePhone" />');
                getElement("siteTelNo1").focus();
            }
        }

        if(!isNullOrEmpty(getElement("siteTelNo2").value)){
            if(!isNumeric(getElement("siteTelNo2").value)) {
                addClientError('<s:text name="error.common.invalidHomePhone" />');
                getElement("siteTelNo2").focus();
            }
        }

        if(!isNullOrEmpty(getElement("siteTelNo3").value)){
            if(!isNumeric(getElement("siteTelNo3").value)) {
                addClientError('<s:text name="error.common.invalidHomePhone" />');
                getElement("siteTelNo3").focus();
            }
        }

        if(!isNullOrEmpty(getElement("siteFax").value)){
            if(!isNumeric(getElement("siteFax").value)) {
                addClientError('<s:text name="error.common.invalidHomePhone" />');
                getElement("siteFax").focus();
            }
        }

        if(hasClientError)
            return false;

        return true;
    };
//======================================================================================================================


</script>
