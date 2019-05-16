<%@ page import="com.soshiant.common.util.CommonUtil" %>
<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<%
%>

<s:form namespace="/" id="studentListInputForm" name="studentListInputForm" action="studentListInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 680px;vertical-align: top; resize: none;">

    <h2 align="center"><s:text name="label.studentList"/></h2>

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
            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                <sj:tabbedpanel id="localtabs">
                    <sj:tab id="searchTab"  target="searchDiv" label="%{getText('label.cellPhone')}"/>
                    <sj:tab id="branchTab"  target="branchDiv"    label="%{getText('label.branch')}"/>

                    <s:div id="searchDiv" name="searchDiv" cssStyle="overflow:hidden;height: 80px">
                        <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                            <tr class="bodyElementsRows">
                                <td colspan="6" style="height: 25px;">
                                    &nbsp;
                                </td>
                            </tr>

                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="200px">
                                    <s:text name="label.searchField"/>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="160px">

                                    <s:select id="searchField"
                                              name="searchField"
                                              list="#{'cellphone':'cellphone', 'emailAddress':'emailAddress','nationalCode':'nationalCode','idNumber':'idNumber'}"
                                              cssStyle="width: 150px;"
                                              headerKey="0"
                                              headerValue="%{getText('label.notDefined')}"
                                              tabindex="1"
                                            />

                                        <%--<select name="yourSearchEngine" id="resultAction_yourSearchEngine">--%>
                                        <%--<option value="-1">Select Search Engines</option>--%>
                                        <%--<option value="google.com">google.com</option>--%>
                                        <%--<option value="bing.com">bing.com</option>--%>
                                        <%--<option value="yahoo.com">yahoo.com</option>--%>
                                        <%--<option value="baidu.com">baidu.com</option>--%>
                                        <%--</select>--%>

                                </td>

                                <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="100px">
                                    <s:text name="label.searchString"/>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="160px">
                                    <s:textfield id="searchString" name="searchString" maxLength="30" theme="simple" cssStyle="width: 150px;" tabindex="2"/>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px">
                                    <input type="button"  value="<s:text name="button.fetch"/>" class="buttonForward" style="width: 70px;" onclick="reloadGrid('');" tabindex="3">
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

                    <s:div id="branchDiv" cssStyle="overflow:hidden;height: 80px">
                        <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                            <tr class="bodyElementsRows">
                                <td style="height: 20px;">
                                    &nbsp;
                                </td>
                            </tr>
                            <tr class="bodyElementsRows">
                                <td align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />" width="200px">
                                    <s:text name="label.branch"/>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="160px">
                                    <sj:select id="studentBranch"
                                               name="studentBranch"
                                               href="activeBranchesListAction.action"
                                               list="activeBranchesList"
                                               listKey="branchId"
                                               listValue="branchName"
                                               dataType="json"
                                               cssClass="commonElement"
                                               cssStyle="width: 160px"
                                               headerKey=""
                                               headerValue="%{getText('label.notDefined')}"
                                               emptyOption="false"
                                               tabindex="2"
                                            >
                                    </sj:select>
                                </td>
                                <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px">
                                    <input id="branchBtn" name="branchBtn" type="button"  value="<s:text name="button.fetch"/>" class="buttonForward" style="width: 70px;" onclick="reloadGrid('<%=ServerConstants.BRANCH_ID%>');" tabindex="3">
                                </td>
                                <td>
                                    <img id="indicator2" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                                </td>
                            </tr>
                        </table>
                    </s:div>


                </sj:tabbedpanel>
            </td>
        </tr>

        <tr class="bodyElementsRows">
            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                &nbsp;
            </td>
        </tr>

        <tr class="bodyElementsRows">
            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%" valign="top">
                <div id="myGridFilter"></div>
                <s:url id="studentListUrl" namespace="/" action="studentListJsonAction"/>
                <sjg:grid   id="studentListGrid"
                            caption="%{getText('label.studentList')}"
                            direction="%{getText('dir.ltr')}"
                            dataType="json"
                            href="%{studentListUrl}"
                            formIds="studentListInputForm"
                            gridModel="studentList"
                            rowList="20,30,50"
                            rowNum="0"
                            rownumbers="true"
                            viewrecords="true"
                            pager="true"
                            pagerButtons="true"
                            scroll="true"
                            scrollrows="true"
                            autowidth="false"
                            width="1000"
                            height="350"
                            shrinkToFit="false"
                            resizable="false"
                            resizableAnimate="false"
                            resizableGhost="false"
                            resizableHandles="false"
                            navigator="true"
                            navigatorView="true"
                            navigatorSearch="false"
                            navigatorEdit="false"
                            navigatorDelete="false"
                            navigatorAdd="false"
                            navigatorExtraButtons="{ separator: {title :'separator'},
                                                 hide     : {title : '%{getText('button.showHide')}',
                                                                    icon: 'ui-icon-wrench',
                                                                    topic: 'showHideColumns'}
                                           }"

                        >
                    <sjg:gridColumn name="studentId"           index="studentId"           title="%{getText('label.studentId')}"                       hidden="false" width="60"  resizable="false" sortable="true"  search="true"  searchoptions="{sopt:['eq']}"/>
                    <sjg:gridColumn name="studentEnFullName"   index="studentEnFullName"   title="%{getText('label.enFullName')}"       align="center" hidden="false" width="120" resizable="false" sortable="true"  search="true"  searchoptions="{sopt:['eq','cn']}"/>
                    <sjg:gridColumn name="studentOlFullName"   index="studentOlFullName"   title="%{getText('label.olFullName')}"                      hidden="false" width="120" resizable="false" sortable="true"  search="false" searchoptions="{sopt:['eq','cn']}"/>
                    <sjg:gridColumn name="nickName"            index="nickName"            title="%{getText('label.nickName')}"         align="center" hidden="false" width="120" resizable="false" sortable="false" search="false" />
                    <sjg:gridColumn name="branchName"          index="branchName"          title="%{getText('label.branch')}"           align="center" hidden="false" width="120" resizable="false" sortable="false" search="false" />
                    <sjg:gridColumn name="cellPhone"           index="cellPhone"           title="%{getText('label.cellPhone')}"        align="center" hidden="false" width="100" resizable="false" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
                    <sjg:gridColumn name="homePhone"           index="homePhone"           title="%{getText('label.homePhone')}"        align="center" hidden="false" width="100" resizable="false" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
                    <sjg:gridColumn name="workPhone"           index="workPhone"           title="%{getText('label.workPhone')}"                       hidden="false" width="100" resizable="false" sortable="false" search="false"/>
                    <sjg:gridColumn name="emailAddress"        index="emailAddress"        title="%{getText('label.emailAddress')}"                    hidden="false" width="120" resizable="false" sortable="false" search="true"  searchoptions="{sopt:['eq','cn']}"/>
                    <sjg:gridColumn name="birthDate"           index="birthDate"           title="%{getText('label.birthDate')}"        align="center" hidden="false" width="70"  resizable="false" sortable="false" search="false" formatter="dateFormatter"/>

                </sjg:grid>
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