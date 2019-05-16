<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjc" uri="/struts-jquery-chart-tags"%>

<%

%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fusionChart/fusioncharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fusionChart/themes/fusioncharts.theme.fint.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fusionChart/jqueryPlugin/fusioncharts.jqueryplugin.js"></script>

<s:form namespace="/" id="chartReportForm" name="chartReportForm" action="chartReportInputAction" theme="simple">

    <s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 1000px;height: 680px;vertical-align: top; resize: none;">

        <h2 align="center"><s:text name="menu.chartReport"/></h2>

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
                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                    <div style="height: 20px; text-align: center;" class="ui-widget-header ui-corner-top">
                        <s:text name="label.searchOperation"/>
                    </div>
                    <s:div cssClass="type-text ui-state-default ui-corner-bottom" cssStyle="margin-top: 0px">

                        <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                            <tr class="bodyElementsRows">
                                <td colspan="6" style="height: 35px;">
                                    <s:div cssStyle="overflow:hidden;height: 35px">
                                        <s:actionmessage id="serverMessageList" cssClass="actionMessageClass"/>
                                        <s:actionerror id="serverErrorList" cssClass="actionErrorClass"/>
                                        <ul id="clientErrorList" class="actionErrorClass"></ul>
                                    </s:div>
                                </td>
                            </tr>

                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="80px">
                                    <s:text name="label.serverList"/>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="200px">

                                    <sj:select id="serverId"
                                               name="serverId"
                                               href="mainFrameServersListAction.action"
                                               list="mainFrameServersList"
                                               listKey="serverId"
                                               listValue="serverName"
                                               dataType="json"
                                               cssStyle="width: 190px"
                                               headerKey="0"
                                               headerValue="%{getText('label.notDefined')}"
                                               emptyOption="false"
                                               onChangeTopics="serverSelected"
                                               tabindex="12"
                                            >
                                    </sj:select>
                                </td>

                                <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="300px">
                                    <s:text name="label.metricId"/>
                                    <sj:select id="rmfMetricId"
                                               name="rmfMetricId"
                                               href="permittedMetricIdListJsonAction.action"
                                               list="permittedRmfMetricIdList"
                                               listKey="key"
                                               listValue="value"
                                               dataType="json"
                                               cssStyle="width: 200px"
                                               headerKey="0"
                                               headerValue="%{getText('label.notDefined')}"
                                               emptyOption="false"
                                               listenTopics="serverSelected"
                                               tabindex="12"
                                            >
                                    </sj:select>
                                </td>
                                <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">

                                </td>
                            </tr>

                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="80px">
                                    <s:text name="label.reportDate"/>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="200px">
                                    <s:textfield id="reportDate" name="reportDate" maxLength="10" required="true" validate="true" theme="simple" cssStyle="width: 80px" tabindex="5"/>
                                    <img id="searchDateImg" src="<s:text name="webApp.datePickerImage" />" style="vertical-align: top;" />
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px">
                                    <s:text name="label.reportFromTime"/>
                                    <sj:datepicker id="reportFromTime" label="With Grid" timepicker="true" timepickerOnly="true" timepickerGridHour="4" timepickerGridMinute="10" showOn="focus" cssStyle="width: 40px;"/>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="120px">
                                    <s:text name="label.reportToTime"/>
                                    <sj:datepicker id="reportToTime" label="With Grid" timepicker="true" timepickerOnly="true" timepickerGridHour="4" timepickerGridMinute="10" showOn="focus" cssStyle="width: 40px;" />
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px">

                                    <input type="button"  value="<s:text name="button.fetch"/>" class="buttonForward ui-widget-content ui-corner-all"style="width: 70px;" onclick="fetchChartInfo();" tabindex="3">


                                    <%--<s:url id="getChartDataUrl" namespace="/" action="chartReportJsonAction" />--%>
                                    <%--<sj:a--%>
                                            <%--id="fetchChartData"--%>
                                            <%--button="true"--%>
                                            <%--value="%{getText('button.fetch')}"--%>
                                            <%--formIds="chartReportForm"--%>
                                            <%--dataType="json"--%>
                                            <%--cssStyle="width: 130px;"--%>
                                            <%--buttonIcon="ui-icon-gear"--%>
                                            <%--indicator="indicator1"--%>
                                            <%--onclick="fetchChartInfo();"--%>
                                            <%--tabindex="3"--%>
                                            <%-->--%>
                                        <%--<s:text name="button.fetch"/>--%>
                                    <%--</sj:a>--%>

                                </td>
                                <td>
                                    <img id="indicator1" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                                </td>
                            </tr>

                            <tr class="bodyElementsRows">
                                <td colspan="6" style="height: 25px;">
                                    &nbsp;
                                </td>
                            </tr>
                        </table>
                    </s:div>

                </td>
            </tr>

            <tr class="bodyElementsRows">
                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                    &nbsp;
                </td>
            </tr>
            <%--<% for(int i = 0; i < chartDataList.size(); i++) { %>--%>
            <tr class="bodyElementsRows">
                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%" valign="top">
                    <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%" cellpadding="1">
                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                                <s:url var="chartReportJsonActionUrl" action="chartReportJsonAction"/>
                                <div id="chartContainer">

                                </div>

                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <%--<%}%>--%>
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
        var reportChart;
        //======================================================================================================================
        Calendar.setup({
            inputField  : "reportDate",     // id of the input field
            button      : "searchDateImg",  // trigger for the calendar (button ID)
            ifFormat    : "%Y/%m/%d",       // format of the input field
            dateType	: 'jalali'
        });
        $("#reportDate").mask("1399/99/99");
        //==============================================================================================================
        $.subscribe('validateForFetch', function(event,data) {

            removeClientErrors();

            var elmValue = getElement("reportDate").value;

            if(isNullOrEmpty(elmValue)){
                addClientError('<s:text name="error.common.reportDateRequired" />');
                getElement("reportDate").focus();
                return false;
            }
            else if(!isValidDate(elmValue)){
                addClientError('<s:text name="error.common.invalidReportDate" />');
                getElement("reportDate").focus();
                return false;
            }
            return true;

        });
        //======================================================================================================================
        $.subscribe('handleError', function(event,data) {
            removeClientErrors();
            addClientError(event.originalEvent.request.statusText);
            clearFields();
            $('#reportDate').focus();

        });
        //======================================================================================================================
        $.subscribe('handleSuccess', function(event,data) {
            var chartData = event.originalEvent.data;
            alert("data : " + chartData);
            updateChart(chartData);
        });
        //======================================================================================================================
        //======================================================================================================================
    });
    //=================================================== Java Script ==================================================
    FusionCharts.ready(function(){

        reportChart = new FusionCharts({
            "id":"rmfChart",
            "type": "bar2d",
            "renderAt": "chartContainer",
            "width": "500",
            "height": "300",
            "dataFormat": "json",
            "dataSource":  {

                "chart": {
                        "caption": "Top 5 Stores by Sales",
                        "subCaption": "Last month",
                        "yAxisName": "Sales (In USD)",
                        "numberPrefix": "$",
                        "paletteColors": "#0075c2",
                        "bgColor": "#ffffff",
                        "showBorder": "0",
                        "showCanvasBorder": "0",
                        "usePlotGradientColor": "0",
                        "plotBorderAlpha": "10",
                        "placeValuesInside": "1",
                        "valueFontColor": "#ffffff",
                        "showAxisLines": "1",
                        "axisLineAlpha": "25",
                        "divLineAlpha": "10",
                        "alignCaptionWithCanvas": "0",
                        "showAlternateVGridColor": "0",
                        "captionFontSize": "14",
                        "subcaptionFontSize": "14",
                        "subcaptionFontBold": "0",
                        "toolTipColor": "#ffffff",
                        "toolTipBorderThickness": "0",
                        "toolTipBgColor": "#000000",
                        "toolTipBgAlpha": "80",
                        "toolTipBorderRadius": "2",
                        "toolTipPadding": "5"
                },
                        "data": [
                        {
                            "label": "Bakersfield Central",
                            "value": "880000"
                        },
                        {
                            "label": "Garden Groove harbour",
                            "value": "730000"
                        },
                        {
                            "label": "Los Angeles Topanga",
                            "value": "590000"
                        },
                        {
                            "label": "Compton-Rancho Dom",
                            "value": "520000"
                        },
                        {
                            "label": "Daly City Serramonte",
                            "value": "330000"
                        }
                    ]
            }

        });
        reportChart.render();
    })
    //==================================================================================================================
    function updateChart(jsonData) {
        // First time I initialize my chart
        if (FusionCharts("rmfChart") === undefined) {
            alert("2");
            var chart = new myChartId({
                // Some rendering options
                swfUrl: "Charts/MSLine.swf",
                id: "rmfChart",
                width: "100%",
                height: "280px",
                dataFormat: 'json'
            });
            chart.setJSONData(jsonData);
            chart.render("myContainerId");
        }
        else {
            FusionCharts("rmfChart").setJSONData(jsonData);
            FusionCharts("rmfChart").render();
        }
    }
    //==================================================================================================================
    function fetchChartInfo(){

        removeClientErrors();
        var serverIdElmValue = getSelectValue("serverId");

        if(isNullOrEmpty(serverIdElmValue)){
            addClientError('<s:text name="error.common.serverIdRequired" />');
            getElement("serverId").focus();
            return false;
        }
        else if(!isNumeric(serverIdElmValue)){
            addClientError('<s:text name="error.common.invalidServerId" />');
            getElement("serverId").focus();
            return false;
        }

        var metricIdElmValue = getSelectValue("rmfMetricId");

        if(isNullOrEmpty(metricIdElmValue) || metricIdElmValue == '0'){
            addClientError('<s:text name="error.common.MetricIdRequired" />');
            getElement("rmfMetricId").focus();
            return false;
        }

        var reportDateElmValue = getElement("reportDate").value;

        if(isNullOrEmpty(reportDateElmValue)){
            addClientError('<s:text name="error.common.reportDateRequired" />');
            getElement("reportDate").focus();
            return false;
        }
        else if(!isValidDate(reportDateElmValue)){
            addClientError('<s:text name="error.common.invalidReportDate" />');
            getElement("reportDate").focus();
            return false;
        }

        var reportFromTimeElmValue = getElement("reportFromTime").value;

        if(!isNullOrEmpty(reportFromTimeElmValue)) {
            if (!isValidTime(reportFromTimeElmValue)) {
                addClientError('<s:text name="error.common.invalidFromTime" />');
                getElement("reportFromTime").focus();
                return false;
            }
        }

        var reportToTimeElmValue = getElement("reportToTime").value;

        if(!isNullOrEmpty(reportToTimeElmValue)) {
            if (!isValidTime(reportToTimeElmValue)) {
                addClientError('<s:text name="error.common.invalidToTime" />');
                getElement("reportToTime").focus();
                return false;
            }
        }

        $.ajax({
            cache:false,
            url: "ajax/chartReportJsonAction.action",
            type: "POST",
            data: {serverId: serverIdElmValue,rmfMetricId:metricIdElmValue,reportDate: reportDateElmValue,reportFromTime:reportFromTimeElmValue,reportToTime:reportToTimeElmValue},
            dataType: "json",

            error: function() {
                addClientError('<s:text name="error.common.staffIdNotFound" />');
                getElement("reportDate").focus();

            },
            success: function(chartData) {
                updateChart(chartData.jsonData);
            }
        });
    }


</script>