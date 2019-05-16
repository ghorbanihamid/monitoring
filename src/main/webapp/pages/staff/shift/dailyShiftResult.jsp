<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>

<%

%>

<s:form namespace="/" id="dailyClassDefinitionResultForm" name="dailyClassDefinitionResultForm" action="dailyClassDefinitionSaveAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 800px;height: 680px;vertical-align: top; resize: none;">

<h2><s:text name="label.dailyClassDefinition"/></h2>

<table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

    <tr class="bodyElementsRows">
        <td width="100%">
            <s:div cssStyle="overflow:hidden;height: 20px">
                <s:actionmessage id="serverMessageList" cssClass="actionMessageClass"/>
                <s:actionerror id="serverErrorList" cssClass="actionErrorClass"/>
                <ul id="clientErrorList" class="actionErrorClass"/>
            </s:div>
        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td style="height: 25px;" width="100%">
             &nbsp;
        </td>
    </tr>
    <tr class="bodyElementsRows">
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" class="actionMessageClass" width="100%">
            <s:text name="dailyClass.classCreatedSuccessfully"/>
        </td>
    </tr>
    <tr class="bodyElementsRows">
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
            <table width="100%">
                <tr class="bodyElementsRows">
                    <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" class="actionMessageClass" width="75px;">
                        <s:text name="label.classId"/> :
                    </td>
                    <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" class="actionMessageClass">
                        <s:label id="classId" name="classId" cssStyle="font: bold; font-size: 12px; color: #3300ff "/>
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
});
//======================================================================================================================
</script>
