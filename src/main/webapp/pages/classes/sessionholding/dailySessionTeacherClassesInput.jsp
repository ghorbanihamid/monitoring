<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page import="com.soshiant.server.model.classes.DailyClass" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<%
%>

<s:form namespace="/" id="dailySessionTeacherClassesInputForm" name="dailySessionTeacherClassesInputForm" action="dailyTeacherClassesInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 1000px;height: 680px;vertical-align: top; resize: none;">

<h2><s:text name="label.studentList"/></h2>

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
                <s:text name="label.branch"/>
            </div>
            <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">
                <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                    <tr class="bodyElementsRows">
                        <td colspan="2" style="height: 25px;">
                            &nbsp;
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">

                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="110px">
                            <s:text name="classSession.listAllClasses"/>
                        </td>

                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr"/>" width="30px">
                            <s:checkbox id="listAllClasses" name="listAllClasses" theme="simple" tabindex="1"/>
                        </td>

                        <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="70px">
                            <s:text name="label.branch"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="160px">
                            <sj:select id="branchId"
                                       name="branchId"
                                       href="activeBranchesListAction.action"
                                       formIds="dailySessionTeacherClassesInputForm"
                                       list="activeBranchesList"
                                       listKey="branchId"
                                       listValue="branchName"
                                       dataType="json"
                                       cssClass="commonElement"
                                       cssStyle="height:20px; width:150px"
                                       headerKey="0"
                                       headerValue="%{getText('label.notDefined')}"
                                       emptyOption="false"
                                       tabindex="2"
                                >
                            </sj:select>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px">
                            <s:submit id="getTeacherClassesBtn"
                                      action="dailyTeacherClassesListAction"
                                      value="%{getText('button.fetch')}"
                                      cssStyle="width: 70px;"
                                      cssClass="buttonForward"
                                      tabindex="3"
                                      type="button"
                                      onclick="return validateForm();"
                            />
                            <%--<s:url namespace="/" id="dailyTeacherClassesListUrl"  action="dailyTeacherClassesListAction"/>--%>
                            <%--<sj:a--%>
                                    <%--id="getTeacherClassesBtn"--%>
                                    <%--button="true"--%>
                                    <%--value="%{getText('button.fetch')}"--%>
                                    <%--href="%{dailyTeacherClassesListUrl}"--%>
                                    <%--formIds="dailySessionTeacherClassesInputForm"--%>
                                    <%--effect="pulsate"--%>
                                    <%--effectDuration="1500"--%>
                                    <%--effectOptions="{mode:'show' , times:3}"--%>
                                    <%--cssStyle="width: 70px;"--%>
                                    <%--indicator="indicator1"--%>
                                    <%--buttonIcon="ui-icon-gear"--%>
                                    <%--onBeforeTopics="validateForFetch"--%>
                                    <%--onCompleteTopics="handleServerError"--%>
                                    <%--onErrorTopics="handleError"--%>
                                    <%--tabindex="3"--%>
                                    <%-->--%>
                                <%--&lt;%&ndash;<s:text name="button.fetch"/>&ndash;%&gt;--%>
                            <%--</sj:a>--%>
                        </td>
                        <td>
                             <img id="indicator1" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
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

    <tr class="bodyElementsRows">
        <td align="left" dir="ltr" width="100%">
            &nbsp;
        </td>
    </tr>

</table>

</s:div>

</s:form>
<img id="indicator" src="/images/indicator.gif" alt="Loading..." style="display:none"/>

<script type="text/javascript" language="javascript">

$(document).ready(function () {
//======================================================================================================================
    $.subscribe('validateForFetch', function(event,data) {

        removeClientErrors();
        alert(getElement("branchId").value);
        if(getElement("branchId").value == 0){
            event.originalEvent.options.submit = false;
            getElement("indicator1").style.display = 'none';
            addClientError('<s:text name="error.common.branchRequired" />');
            getElement("branchId").focus();
            return false;
        }

        return true;

    });
//======================================================================================================================
//======================================================================================================================
});
//================================================== Java Script =======================================================
    function validateForm(){

        removeClientErrors();

        if(getElement("branchId").value == 0){
            addClientError('<s:text name="error.common.branchRequired" />');
            getElement("branchId").focus();
            return false;
        }

        if(hasClientError)
            return false;

        return true;
    };
//======================================================================================================================

</script>