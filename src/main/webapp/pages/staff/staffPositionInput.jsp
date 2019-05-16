<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<%

%>

<s:form id="staffPositionInputForm" namespace="/" action="staffPositionInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 680px;vertical-align: top;">

<h2 align="center"><s:text name="staffPosition.registration.title"/></h2>

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
                <s:text name="staffPosition.registration.registerNewPosition"/>
            </div>
            <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
                <table width="100%">

                    <tr class="bodyElementsRows">
                        <td colspan="4" style="height: 25px;">
                             &nbsp;
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">

                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100px">
                            <s:text name="label.staffId"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="160px">
                            <s:textfield id="staffId" name="staffId" maxLength="6" theme="simple" cssStyle="width:90%" onkeypress="return numericOnKeyPress(event);" tabindex="1"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="60px">
                            <s:url id="staffNameUrl" namespace="/" action="staffNameForPositionJsonAction"/>
                            <sj:a      button="true"
                                       value="%{getText('button.fetch')}"
                                       href="%{staffNameUrl}"
                                       targets="staffNameSpan"
                                       formIds="staffPositionInputForm"
                                       effect="pulsate"
                                       effectDuration="1500"
                                       effectOptions="{mode:'show' , times:3}"
                                       cssStyle="width: 70px;"
                                       buttonIcon="ui-icon-gear"
                                       indicator="indicator1"
                                       onBeforeTopics="validateStaffId"
                                       onSuccessTopics="fillGrid"
                                       onErrorTopics="handleError"
                                       tabindex="2"
                            >
                                <s:text name="button.fetch"/>
                            </sj:a>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                            <img id="indicator1" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                            <span id="staffNameSpan"></span>
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">

                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100px">
                            <s:text name="staffPosition.staffPosition"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="160px">
                            <sj:select id="positionId"
                                   name="positionId"
                                   href="positionsListAction.action"
                                   list="positionsList"
                                   listKey="positionId"
                                   listValue="positionName"
                                   dataType="json"
                                   cssClass="commonElement"
                                   cssStyle="width: 94%"
                                   headerKey="0"
                                   headerValue="%{getText('label.notDefined')}"
                                   emptyOption="false"
                                   tabindex="3"
                                >
                            </sj:select>
                        </td>

                        <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                            &nbsp;
                        </td>
                    </tr>

                </table>
            </s:div>
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td>
            <table dir="<s:text name="dir.ltr" />" width="100%">
                <tr class="bodyElementsRows">
                    <td dir="<s:text name="dir.ltr" />" align="center">
                        <s:submit  key="button.save" action="staffPositionSaveAction" cssStyle="width: 75px;" cssClass="buttonForward" onclick="return validateForm();" tabindex="4"/>
                        &nbsp;&nbsp;&nbsp;
                        <s:submit  key="button.toggleStatus" action="staffPositionEditAction" cssStyle="width: 75px;" cssClass="buttonForward" onclick="return validateForm();" tabindex="5"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td style="height: 25px;">
             &nbsp;
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td>

            <s:url id="staffPositionsListUrl" namespace="/" action="staffPositionListJsonAction"/>
            <s:url id="staffPositionsEditUrl" namespace="/" action="staffPositionEditJsonAction"/>
            <sjg:grid
                        id="staffPositionsGrid"
                        caption="%{getText('staffPosition.staffPositionsList')}"
                        dataType="json"
                        href="%{staffPositionsListUrl}"
                        formIds="staffPositionInputForm"
                        pager="true"
                        gridModel="staffPositionList"
                        rowList="20,30,50"
                        rowNum="0"
                        viewrecords="true"
                        rownumbers="true"
                        draggable="false"
                        resizable="false"
                        resizableAnimate="false"
                        resizableGhost="false"
                        resizableHandles="false"
                        navigator="true"
                        navigatorSearch="false"
                        navigatorAdd="false"
                        navigatorEdit="false"
                        navigatorDelete="false"
                        navigatorEditOptions="{height:280,reloadAfterSubmit:true}"
                        direction="%{getText('dir.ltr')}"
                        scroll="true"
                        scrollrows="true"
                        pagerButtons="true"
                        autowidth="true"
                        shrinkToFit="false"
                        editinline="false"
                        cellEdit="false"
                        cellurl="%{staffPositionsEditUrl}"
                        onSelectRowTopics="rowSelect"
                        onEditInlineSuccessTopics="onEditSuccess"
                        reloadTopics="fillGrid"
                        >
                <sjg:gridColumn name="positionId"         index="positionId"         title="%{getText('label.positionId')}"                  width="80"  sortable="false" search="false" />
                <sjg:gridColumn name="positionName"       index="positionName"       title="%{getText('staffPosition.staffPositionName')}"   width="150" sortable="false" search="false" />
                <sjg:gridColumn name="registerDate"       index="registerDate"       title="%{getText('label.registerDate')}"                width="100" sortable="false" search="false" formatter="dateFormatter"/>
                <sjg:gridColumn name="positionStatusName" index="positionStatusName" title="%{getText('staffPosition.staffPositionStatus')}" width="100" sortable="false" search="false" editable="false" edittype="select"
                                                                                                                                                                                         editoptions="{value:'1:Active;2:Disabled'}"/>

            </sjg:grid>
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td style="height: 25px;">
             &nbsp;
        </td>
    </tr>

    <%--<tr class="bodyElementsRows">--%>
        <%--<td>--%>
            <%--<div style="height: 20px; text-align: center;" class="ui-widget-header ui-corner-top">--%>
                <%--<s:text name="staffPosition.staffPositionsList"/>--%>
            <%--</div>--%>
            <%--<s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">--%>
                <%--<table width="100%">--%>

                    <%--<tr class="bodyElementsRows">--%>
                        <%--<td colspan="4" style="height: 25px;">--%>
                             <%--&nbsp;--%>
                        <%--</td>--%>
                    <%--</tr>--%>

                    <%--<tr class="bodyElementsRows">--%>
                        <%--<td colspan="4" align="center" dir="<s:text name="dir.ltr" />" width="100%">--%>

                        <%--</td>--%>
                    <%--</tr>--%>

                    <%--<tr class="bodyElementsRows">--%>
                        <%--<td colspan="4" style="height: 25px;">--%>
                             <%--&nbsp;--%>
                        <%--</td>--%>
                    <%--</tr>--%>

                    <%--<tr class="bodyElementsRows">--%>
                        <%--<td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="120px;">--%>
                            <%--<s:text name="label.positionId"/>--%>
                        <%--</td>--%>
                        <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px;">--%>
                            <%--&lt;%&ndash;<s:textfield id="positionId" name="positionId" maxLength="15" theme="simple" cssStyle="width: 100px;" onkeypress="return numericOnKeyPress(event);" tabindex="2"/>&ndash;%&gt;--%>
                        <%--</td>--%>
                        <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="60px;">--%>
                            <%--<s:submit id="enableButton" key="button.enable" cssStyle="width: 50px;" cssClass="buttonForward" onclick="return enableStaffPosition();" tabindex="9"/>--%>
                        <%--</td>--%>
                        <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">--%>
                            <%--<s:submit id="disableButton" key="button.disable" cssStyle="width: 50px;" cssClass="buttonForward" onclick="return disableStaffPosition();" tabindex="9"/>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</s:div>--%>
        <%--</td>--%>
    <%--</tr>--%>

    <tr class="bodyElementsRows">
        <td style="height: 25px;">
             &nbsp;
        </td>
    </tr>



</table>

</s:div>

</s:form>

<img id="indicator" src="/images/indicator.gif" alt="Loading..." style="display:none"/>

<script type="text/javascript" language="javascript">

$(document).ready(function() {

//======================================================================================================================
    $.subscribe('validateStaffId', function(event,data) {

        removeClientErrors();
        getElement("staffNameSpan").innerHTML = "";

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

//        return true;

    });
//======================================================================================================================
    $.subscribe('validateForm', function(event,data) {

        removeClientErrors();
        getElement("staffNameSpan").innerHTML = "";

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

        if(getElement("positionId").selectedIndex == 0){
            addClientError('<s:text name="error.staffPositionRequired" />');
            getElement("positionId").focus();
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
//    $.subscribe('fillGrid', function(event,data) {
//        $("#staffPositionsGrid").trigger("reloadGrid");
//    });
//======================================================================================================================
    $.subscribe('rowSelect', function(event,data) {

        $("#staffPositionsGrid").html('<p>Edit Mode for Row : '+event.originalEvent.id+'</p>');
    });
//======================================================================================================================
    $.subscribe('onEditSuccess', function(event, data){

        var message = event.originalEvent.response.statusText;
        $("#staffPositionsGrid").html('<p>Status: ' + message + '</p>');
    });

//======================================================================================================================

});

//======================================================================================================================
    function dateFormatter(cellvalue, options, rowObject) {

        var formattedText = "";
        if(cellvalue != null){
            formattedText =addDateSeparator(cellvalue.toString());
        }
        return formattedText;
    }
//======================================================================================================================
    function validateForm(){

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

        if(getElement("positionId").selectedIndex == 0){
            addClientError('<s:text name="error.staffPositionRequired" />');
            getElement("positionId").focus();
            return false;
        }
        getElement("indicator2").display = "";
        return true;
    }
//======================================================================================================================

</script>
