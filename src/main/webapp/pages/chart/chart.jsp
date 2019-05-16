<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"    uri="/struts-tags" %>
<%@ taglib prefix="sj"   uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjc"  uri="/struts-jquery-chart-tags"%>
<%@ taglib prefix="sc"   uri="/WEB-INF/tld/chart.tld" %>

<%
//    String defaultRefTime = ServerConstants.defaultRefreshTime;
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fusionChart/fusioncharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fusionChart/themes/fusioncharts.theme.fint.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fusionChart/jqueryPlugin/fusioncharts.jqueryplugin.js"></script>

<s:form namespace="/" id="chartForm" name="chartForm" action="chartInputAction" theme="simple">

    <s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 1200px;height: 800px;vertical-align: top; resize: none;">

        <table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

            <tr>
                <td>
                    <table align="<s:text name="align.left"/>" dir="<s:text name="dir.ltr" />" width="100%">
                        <tr class="bodyElementsRows">
                            <td width="50%" align="<s:text name="align.left" />">
                                <h3><s:text name="label.chart"/></h3>
                            </td>
                            <td width="50%" style="padding:0px;padding-right:10px;" title="<s:text name="label.reminderTimeHint"/>" align="<s:text name="align.right"/>">
                                <font style="font-family: tahoma;font-size: 11;color: black;">
                                    <bean:message key="label.remindedTime"/>
                                </font>
                                <input value="01:00" style="background-color: #9CCAA7;width: 42px;border-style: solid;border-width: 1px;border-color: gray;text-align:center; " name="Timer" id="Timer" readonly="true" tabindex="-1">
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

            <tr class="bodyElementsRows">
                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                    <div style="height: 20px; text-align: center;" class="ui-widget-header ui-corner-top">
                        <s:text name="label.chart"/>
                    </div>
                    <div style="height: 10px; text-align: center;" class="type-text ui-state-default ui-corner-bottom">

                    </div>
                </td>
            </tr>

            <tr class="bodyElementsRows">
                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                    <%-- ======================= chart Tag ======================== --%>
                    <sc:chart />
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

    $(document).ready(function () {
        //======================================================================================================================
        //======================================================================================================================
    });
    var defaultRefreshTime = "1";
    <%--<%=defaultRefTime%>;--%>

    if(isNullOrEmpty(defaultRefreshTime))
        defaultRefreshTime = 1;

    var secondsTimer = 0;
    var minuteTimer = defaultRefreshTime;

    function displayTimer() {

        var timerElm = document.getElementById("Timer");

        if (timerElm.value != "--:--") {

            if ((secondsTimer == 1 && minuteTimer == 0) || minuteTimer < 0) {
                timerElm.value = "--:--";
                refreshPage();
            }
            else if (secondsTimer == 0) {
                secondsTimer = 60;
                minuteTimer -= 1;
            }

            secondsTimer -= 1;

            if (timerElm.value != "--:--") {

                if (secondsTimer >= 10)
                    timerElm.value = "0" + minuteTimer + ':' + secondsTimer;
                else
                    timerElm.value = "0" + minuteTimer + ':' + "0" + secondsTimer;

                setTimeout("displayTimer()", 1000);
            }

            bgColor = "#9CCAA7";
            if(secondsTimer < 20)
                bgColor = "#F8BCB6";
            else if(secondsTimer < 40)
                bgColor = "#E8E6B8";

            timerElm.style.backgroundColor = bgColor;
        }

    }
    function refreshPage(){
        location.reload(true);
    }
    displayTimer();
</script>

