<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page import="com.soshiant.server.model.server.Server" %>
<%@ page import="java.util.List" %>
<%@ page import="com.soshiant.server.model.user.UserServers" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>

<%
    List<UserServers> monitorableServersList = (List<UserServers>)request.getAttribute(ServerConstants.OBJECT_MONITORABLE_SERVERS);
%>

<s:form namespace="/" id="monitorableServersListInputForm" name="monitorableServersListInputForm" action="monitorableServersListInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 680px;vertical-align: top; resize: none;">

    <h2 align="center"><s:text name="serverList.title"/></h2>

    <table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

        <tr>
            <td width="100%">
                <s:div cssStyle="overflow:hidden;height: 20px">
                    <s:actionmessage id="serverMessageList" cssClass="actionMessageClass"/>
                    <s:actionerror id="serverErrorList" cssClass="actionErrorClass"/>
                    <ul id="clientErrorList" class="actionErrorClass"></ul>
                </s:div>
            </td>
        </tr>
        <tr class="bodyElementsRows">
            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                &nbsp;
            </td>
        </tr>


        <tr class="bodyElementsRows">
            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%" valign="top">
                <table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                    <%
                        if(monitorableServersList != null && monitorableServersList.size() > 0){
                            for (int i = 0; i < monitorableServersList.size(); i++) {
                                UserServers userServers =  monitorableServersList.get(i);
                                String tdName = "serveId" + i;
                                if((i % 2)== 0) {
                    %>
                                    <tr class="bodyElementsRows">
                    <%
                                }
                    %>
                                        <td id="<%=tdName%>" align="center" dir="<s:text name="dir.ltr" />" width="50%" valign="top">
                                            <s:url id="chartInputActionUrl" namespace="/" action="chartInputAction">
                                                <%--<s:param name="serverId" value="<%=serverInfo.getServerId()%>"/>--%>
                                            </s:url>
                                            <a id="hrefTag<%=i%>" href="<s:property value="#chartInputActionUrl" />" onclick="return monitorSelectedServer(<%=i%>,<%=userServers.getServerId()%>)">
                                                <img src="<s:text name="webApp.serverImage" />" alt="" width="100" height="100" border="1"/>&nbsp;<br>
                                                <%=userServers.getServerName()%><br>
                                                <%=userServers.getServerIp()%>
                                            </a>
                                        </td>
                    <%
                                if((i % 2)!= 0) {
                    %>
                                    </tr>
                    <%
                                }
                            }
                        }
                    %>
                </table>
            </td>
        </tr>

        <tr class="bodyElementsRows">
            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                &nbsp;
            </td>
        </tr>

    </table>

</s:div>
</s:form>
<img id="indicator" src="/images/indicator.gif" alt="Loading..." style="display:none"/>

<script type="text/javascript" language="javascript">
    //======================================================================================================================
    $(document).ready(function () {
    //======================================================================================================================
    //================================================== Java Script =======================================================
    });

    //================================================== Java Script =======================================================
    function monitorSelectedServer(elmNo,serverId) {

        var elmId = "hrefTag" + elmNo;
        var hrefText = document.getElementById(elmId).getAttribute("href") + "?serverId=" + serverId ;
        document.getElementById(elmId).setAttribute("href",hrefText);

        return true;
    }
    //======================================================================================================================

</script>