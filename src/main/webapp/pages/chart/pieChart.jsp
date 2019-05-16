<%@ page import="com.soshiant.common.util.CommonUtil" %>
<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page import="com.soshiant.server.model.server.Server" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjc" uri="/struts-jquery-chart-tags"%>

<%
%>

<s:form namespace="/" id="pieChartForm" name="pieChartForm" action="pieChartInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 1000px;height: 680px;vertical-align: top; resize: none;">

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

                <sjc:chart
                        id="chartPie"
                        cssStyle="width: 600px; height: 400px;"
                        pie="true"
                        pieLabel="true"
                        >
                    <sjc:chartData
                            id="pieSerie1"
                            label="Serie 1"
                            data="10"
                            />
                    <sjc:chartData
                            id="pieSerie2"
                            label="Serie 2"
                            data="3"
                            />
                    <sjc:chartData
                            id="pieSerie3"
                            label="Serie 3"
                            data="17"
                            />
                    <sjc:chartData
                            id="pieSerie4"
                            label="Serie 4"
                            data="37"
                            />
                </sjc:chart>
                <sj:a onClickTopics="reloadMap" button="true" buttonIcon="ui-icon-refresh">Load/Reload Map</sj:a>
                <sj:a onClickTopics="reloadList" button="true" buttonIcon="ui-icon-refresh">Reload List</sj:a>

            </td>
        </tr>

        <tr class="bodyElementsRows">
            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                &nbsp;
            </td>
        </tr>

    </table>

</s:div>
<input type="hidden" id="searchField" name="searchField" value=""/>
</s:form>
<img id="indicator" src="/images/indicator.gif" alt="Loading..." style="display:none"/>

<script type="text/javascript" language="javascript">
//======================================================================================================================
    $(document).ready(function () {
//======================================================================================================================
        $("#studentListGrid").parents('div.ui-jqgrid-bdiv').css("max-height","350px");
        $("#studentListGrid").parents('div.ui-jqgrid-bdiv').css("max-Width","1000px");
//    $("#studentListGrid").parents('div.ui-jqgrid-bdiv').css("resize","none");
//    $("#studentListGrid").parents('div.ui-jqgrid-view table.ui-jqgrid-htable').css("border-style","none");
//    $("#studentListGrid").parents('div.ui-jqgrid-view table.ui-jqgrid-htable').css("border-collapse","separate");
        $("#studentListGrid").jqGrid({recordtext: "Total rows : {2}", emptyrecords: "Nothing to display"});
//======================================================================================================================
        $.subscribe('handleError', function(event,data) {
            removeClientErrors();
            addClientError(event.originalEvent.request.statusText);
        });
//======================================================================================================================
        $.subscribe('getSelectedRowInfo', function(event, data) {
            alert('Selected Row : ' + event.originalEvent.id);
            var grid = event.originalEvent.grid;
            var sel_id = grid.jqGrid('getGridParam', 'selrow');
            var studentName = grid.jqGrid('getCell', sel_id, 'studentEnName');
            alert(studentName);
        });
//======================================================================================================================
        $.subscribe('showHideColumns', function(event, data) {
            $.struts2_jquery.require("js/plugins/grid.setcolumns.js");
            $("#studentListGrid").jqGrid('setColumns', {});
        });

//======================================================================================================================
//        $("#myGridFilter").jqGrid('filterGrid','#studentListGrid',{
//            autosearch : false,
//            gridNames : true,
//            formtype : 'vertical',
//            autosearch : false,
//            enableSearch : true,
//            enableClear : true,
//            gridModel : true,
//            buttonclass : 'ui-state-default ui-corner-all'
//        });
//================================================== Java Script =======================================================
});

//================================================== Java Script =======================================================
    function reloadGrid(searchField) {

        removeClientErrors();
        var searchStrVal = "";
        var searchFieldVal = "";
        if(searchField == '<%=ServerConstants.BRANCH_ID%>') {// branch

            searchFieldVal = '<%=ServerConstants.BRANCH_ID%>';
            searchStrVal = $("#studentBranch").val();
            if(isNullOrEmpty(searchStrVal)){
                getElement("indicator1").style.display = 'none';
                addClientError('<s:text name="error.common.branchRequired" />');
                $("#studentBranch").focus();
                return false;
            }
        }
        else {
            searchFieldVal = $("#searchField").val();
            searchStrVal = $("#searchString").val();

            if(isNullOrEmpty(searchFieldVal)){
                getElement("indicator1").style.display = 'none';
                addClientError('<s:text name="error.common.searchFieldRequired" />');
                $("#searchString").focus();
                return false;
            }
            if(isNullOrEmpty(searchStrVal)){
                getElement("indicator1").style.display = 'none';
                addClientError('<s:text name="error.common.cellPhoneRequired" />');
                $("#searchString").focus();
                return false;
            }
        }
        var gridUrl = $('#studentListGrid').jqGrid('getGridParam','url');
        var qstIndex= gridUrl.indexOf("?");
        gridUrl = gridUrl.substring(0,qstIndex);
        var newUrl = gridUrl + "?searchField=" + searchFieldVal + "&searchString=" + searchStrVal;
        $("#studentListGrid").jqGrid('setGridParam',{url:newUrl,page:1}).trigger("reloadGrid");
        if(searchFieldVal == '<%=ServerConstants.BRANCH_ID%>') {// branch
            $("#studentBranch").focus();
        }
        else {
            $("#searchField").focus();
        }

    }
//======================================================================================================================
    function booleanFormatter(cellvalue, options, rowObject) {

        var formattedText = "";
        if(cellvalue != null){
            if(cellvalue == 0)
                formattedText = '<s:text name="label.no"/>';
            else if(cellvalue == 1)
                formattedText = '<s:text name="label.yes"/>';
        }
        return formattedText;
    }
//======================================================================================================================
    function dateFormatter(cellvalue, options, rowObject) {

        var formattedText = "";
        if(cellvalue != null && cellvalue != 0){
            formattedText = addDateSeparator(cellvalue.toString());
        }
        return formattedText;
    }
//======================================================================================================================

</script>