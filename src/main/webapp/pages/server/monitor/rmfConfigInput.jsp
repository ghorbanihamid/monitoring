<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="kli" tagdir="/WEB-INF/tags" %>

<%

%>

<s:form namespace="/" id="rmfConfigInputForm" name="rmfConfigInputForm" action="rmfConfigInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 680px;vertical-align: top; resize: none;">

<h2 align="center"><s:text name="rmf.registerConfigTitle"/></h2>

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
                            <s:checkbox id="editMode" name="server.editMode" theme="simple" onclick="return handleEditCheckBox();" cssStyle="width: 20px" tabindex="1"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px;">
                            &nbsp;&nbsp;&nbsp;<s:text name="rmfConfig.configId"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="160px;">
                            <s:textfield id="configId" name="rmfConfig.configId" maxLength="15" theme="simple" cssStyle="width: 100px;" onkeypress="return numericOnKeyPress(event);" tabindex="2"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                            <s:url id="rmfConfigInfoUrl" namespace="/" action="rmfConfigInfoJsonAction" />
                            <sj:a
                                   id="rmfConfigInfoButton"
                                   button="true"
                                   value="%{getText('button.fetch')}"
                                   href="%{serverInfoUrl}"
                                   formIds="rmfConfigInputForm"
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
                <sj:tab id="rmfConfigInfoTab"      target="rmfConfigInfoDiv"      label="%{getText('rmf.rmfConfigInfo')}"/>

                <s:div id="rmfConfigInfoDiv" name="rmfConfigInfoDiv" cssStyle="overflow:hidden;height: 350px">
                    <s:div cssClass="type-text ui-state-default ui-corner-all" cssStyle="margin-top: 0px">
                        <table width="100%">
                            <tr class="bodyElementsRows">
                                <td colspan="4" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                    &nbsp;
                                </td>
                            </tr>

                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">
                                    <s:text name="server.serverId"/>&nbsp;<span class="asterisk">*</span>&nbsp;
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                                    <s:textfield id="serverId" name="rmfConfig.serverId" maxLength="20" theme="simple" cssStyle="width: 200px" onkeypress="return numericOnKeyPress(event);" tabindex="20"/>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">
                                    <s:text name="label.metricId"/>&nbsp;<span class="asterisk">*</span>&nbsp;
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                                    <s:textfield id="metricId" name="rmfConfig.metricId" maxLength="20" theme="simple" cssStyle="width: 200px" onkeypress="return numericOnKeyPress(event);" tabindex="21"/>
                                </td>

                            </tr>

                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">
                                    <s:text name="rmfConfig.unusualMoreValue"/>:
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                                    <s:textfield id="unusualMoreValue" name="rmfConfig.unusualMoreValue" maxLength="20" theme="simple" cssStyle="width: 200px" onkeypress="return numericOnKeyPress(event);" tabindex="23"/>
                                </td>

                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="15%">
                                    <s:text name="rmfConfig.unusualLessValue"/>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">
                                    <s:textfield id="unusualLessValue" name="rmfConfig.unusualLessValue" maxLength="30" theme="simple" cssStyle="width: 200px" onkeypress="return emailOnKeyPress(event);" tabindex="24"/>
                                </td>

                            </tr>

                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">
                                    <s:text name="rmfConfig.criticalMoreValue"/>:
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                                    <s:textfield id="criticalMoreValue" name="rmfConfig.criticalMoreValue" maxLength="20" theme="simple" cssStyle="width: 200px" onkeypress="return numericOnKeyPress(event);" tabindex="23"/>
                                </td>

                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="15%">
                                    <s:text name="rmfConfig.criticalLessValue"/>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">
                                    <s:textfield id="criticalLessValue" name="rmfConfig.criticalLessValue" maxLength="30" theme="simple" cssStyle="width: 200px" onkeypress="return emailOnKeyPress(event);" tabindex="24"/>
                                </td>

                            </tr>

                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="15%">
                                    <s:text name="rmfConfig.needAlarmSms"/>:
                                </td>
                                <td colspan="3" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
                                    <s:checkbox id="needAlarmSms" name="rmfConfig.needAlarmSms" maxLength="20" theme="simple" cssStyle="width: 200px" onkeypress="return numericOnKeyPress(event);" tabindex="23"/>
                                </td>

                            </tr>

                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="15%">
                                    <s:text name="rmfConfig.needAlarmEmail"/>
                                </td>
                                <td colspan="3"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">
                                    <s:checkbox id="needAlarmEmail" name="rmfConfig.needAlarmEmail" maxLength="30" theme="simple" cssStyle="width: 200px" onkeypress="return emailOnKeyPress(event);" tabindex="24"/>
                                </td>

                            </tr>

                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">
                                    <s:text name="rmfConfig.needAlarmBeep"/>:
                                </td>
                                <td colspan="3" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">
                                    <s:checkbox id="needAlarmBeep" name="rmfConfig.needAlarmBeep" maxLength="20" theme="simple" cssStyle="width: 200px" onkeypress="return numericOnKeyPress(event);" tabindex="23"/>
                                </td>

                            </tr>

                            <%--<tr class="bodyElementsRows">--%>
                                <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px">--%>
                                    <%--<s:text name="rmfConfig.beepFileName"/>:--%>
                                <%--</td>--%>
                                <%--<td colspan="3" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="30%">--%>
                                    <%--<s:textfield id="beepFileName" name="rmfConfig.beepFileName" maxLength="20" theme="simple" cssStyle="width: 200px" onkeypress="return numericOnKeyPress(event);" tabindex="23"/>--%>
                                <%--</td>--%>
                            <%--</tr>--%>

                            <tr class="bodyElementsRows">
                                <td colspan="4" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                    &nbsp;
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
                        <s:submit id="saveButton" name="saveButton" key="button.save" action="serverSaveAction" cssStyle="width: 75px;" cssClass="buttonForward" onclick="return validateServerForm();" tabindex="30"/>
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
        var elmValue = getElement("serverId").value;

        if(isNullOrEmpty(elmValue)){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.serverIdRequired" />');
            getElement("serverId").focus();
            return false;
        }
        else if(!isNumeric(elmValue)){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.invalidServerId" />');
            getElement("serverId").focus();
            return false;
        }
        else if(elmValue == 0){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.invalidServerId" />');
            getElement("serverId").focus();
            return false;
        }
        return true;
    });
    //======================================================================================================================
    $.subscribe('handleError', function(event,data) {
        removeClientErrors();
        addClientError(event.originalEvent.request.statusText);
        clearFields();
        $('#serverId').focus();

    });
    //======================================================================================================================
    function clearFields() {

        $('#serverId').val("");
        $('#managerId').val("");
        $('#serverName').val("");
        $('#serverTelNo1').val("");
        $('#serverTelNo2').val("");
        $('#serverTelNo3').val("");
        $('#serverFax').val("");
        $('#serverStatus').val("");
        $('#description').val("");
    };
    //======================================================================================================================
    $.subscribe('handleSuccess', function(event,data) {
        removeClientErrors();

        var serverInfo = event.originalEvent.data;

        $('#serverId').val(serverInfo.serverId);
        $('#managerId').val(serverInfo.managerId);
        $('#serverName').val(serverInfo.serverName);
        $('#serverTelNo1').val(serverInfo.serverTelNo1);
        $('#serverTelNo2').val(serverInfo.serverTelNo2);
        $('#serverTelNo3').val(addDateSeparator(serverInfo.serverTelNo3));
        $('#serverFax').val(serverInfo.serverFax);
        $('#description').val(serverInfo.description);
        $('#serverStatus').val(serverInfo.serverStatus);
        $('#serverName').focus();
    });
    //======================================================================================================================

});

    //======================================================================================================================
    function handleEditCheckBox(){

        if($('#editMode').is(':checked')){
            $('#serverId').attr('disabled', false);
            $('#fetchInfoButton').attr('disabled', false);
        }
        else{
            $('#serverId').attr('disabled', true);
            $('#fetchInfoButton').attr('disabled', true);
        }

    }
    //======================================================================================================================
    function validateServerForm(){

        removeClientErrors();

        if(isNullOrEmpty(getElement("serverName").value)){
            addClientError('<s:text name="error.common.nameRequired" />');
            getElement("serverName").focus();
        }
        else if(!isPersianText(getElement("serverName").value)){
            addClientError('<s:text name="error.common.invalidName" />');
            getElement("serverName").focus();
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

        if(!isNullOrEmpty(getElement("serverTelNo1").value)){
            if(!isNumeric(getElement("serverTelNo1").value)) {
                addClientError('<s:text name="error.common.invalidHomePhone" />');
                getElement("serverTelNo1").focus();
            }
        }

        if(!isNullOrEmpty(getElement("serverTelNo2").value)){
            if(!isNumeric(getElement("serverTelNo2").value)) {
                addClientError('<s:text name="error.common.invalidHomePhone" />');
                getElement("serverTelNo2").focus();
            }
        }

        if(!isNullOrEmpty(getElement("serverTelNo3").value)){
            if(!isNumeric(getElement("serverTelNo3").value)) {
                addClientError('<s:text name="error.common.invalidHomePhone" />');
                getElement("serverTelNo3").focus();
            }
        }

        if(!isNullOrEmpty(getElement("serverFax").value)){
            if(!isNumeric(getElement("serverFax").value)) {
                addClientError('<s:text name="error.common.invalidHomePhone" />');
                getElement("serverFax").focus();
            }
        }

        if(hasClientError)
            return false;

        return true;
    };
//======================================================================================================================


</script>
