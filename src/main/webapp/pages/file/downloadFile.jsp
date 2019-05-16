<%@ page import="com.soshiant.server.action.file.FileDownloadAction" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>

<%
  String fileContentType = "";
  String fileName = "";
%>

<s:form namespace="/" id="downloadFileForm" name="downloadFileForm" action="downloadFileAction" theme="simple">

    <s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform"  cssStyle="height: 650px">

        <h2 align="center"><s:text name="label.downloadFile"/></h2>

        <table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

            <tr class="bodyElementsRows">
                <td style="height: 25px;">
                    &nbsp;
                </td>
            </tr>

            <tr class="bodyElementsRows">
                <td>
                    <s:url action="downloadFileAction" var="downloadFileUrlTag" >
                        <s:param name="contentType"><%=fileContentType%></s:param>
                        <s:param name="fileName"><%=fileName%></s:param>
                    </s:url>
                    <s:a href="%{downloadFileUrlTag}">
                        <img src="<s:text name="monitoring.downloadImage" />" alt="print friendly" width="20" height="20" border="0"/>&nbsp;
                        <s:text name="button.download"/>
                    </s:a>
                </td>
            </tr>

        </table>

    </s:div>
    <input type="hidden" name="contentType" value="<%=FileDownloadAction.CONTENT_TYPE_PDF%>"/>
    <%--<input type="hidden" name="fileName" value="<%=fileName%>"/>--%>

</s:form>

<script type="text/javascript" language="javascript">

$(document).ready(function() {
//======================================================================================================================
//======================================================================================================================
});
//======================================================================================================================
</script>
