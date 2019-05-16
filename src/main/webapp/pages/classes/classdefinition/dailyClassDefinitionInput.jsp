<%@ page import="com.soshiant.server.constants.ServerConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>


<%

%>

<s:form namespace="/" id="dailyClassDefinitionInputForm" name="dailyClassDefinitionInputForm" action="dailyClassDefinitionInputAction" theme="simple">
<%--<s:token name="duplicateToken"></s:token>--%>
<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="width: 800px;height: 680px;vertical-align: top; resize: none;">

<h2><s:text name="label.dailyClassDefinition"/></h2>

<table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

    <tr class="bodyElementsRows">
        <td>
            <s:div cssStyle="overflow:hidden;height: 20px">
                <s:actionmessage id="serverMessageList" cssClass="actionMessageClass"/>
                <s:actionerror id="serverErrorList" cssClass="actionErrorClass"/>
                <ul id="clientErrorList" class="actionErrorClass"/>
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
                            <s:text name="label.dailyClassInformation"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50px;">
                            <input type="checkbox" id="editMode" name="editMode" onclick="return handleEditMode();" style="width: 20px" tabindex="1"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px;">
                            &nbsp;&nbsp;&nbsp;<s:text name="label.classId"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="150px;">
                            <s:textfield id="classId" name="classId" maxLength="20" theme="simple" cssStyle="width: 150px;" onkeypress="return numericOnKeyPress(event);" tabindex="2"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="60px;">
                            <s:url namespace="/" id="dailyClassInfoUrl"  action="dailyClassInfoJsonAction"/>
                            <sj:a
                                   id="fetchClassInfoBtn"
                                   button="true"
                                   value="%{getText('button.fetch')}"
                                   href="%{dailyClassInfoUrl}"
                                   formIds="dailyClassDefinitionInputForm"
                                   dataType="json"
                                   effect="pulsate"
                                   effectDuration="1500"
                                   effectOptions="{mode:'show' , times:3}"
                                   cssStyle="width: 70px;"
                                   indicator="indicator1"
                                   buttonIcon="ui-icon-gear"
                                   onBeforeTopics="validateClassId"
                                   onSuccessTopics="handleSuccessFetchClass"
                                   onCompleteTopics="handleServerError"
                                   onErrorTopics="handleError"
                                   tabindex="3"
                                >
                                   <s:text name="button.fetch"/>
                            </sj:a>
                        </td>
                        <td>
                             <img id="indicator1" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                        </td>
                    </tr>
                </table>
            </s:div>
        </td>
    </tr>
    <tr class="bodyElementsRows">
        <td>
            <sj:tabbedpanel id="localtabs">
                <sj:tab id="classInfoTab"      target="classInfoDiv"      label="%{getText('dailyClass.classInformation')}"/>
                <sj:tab id="classDayInfoTab"   target="classDayInfoDiv"   label="%{getText('dailyClass.classDayInfo')}"/>

                <s:div id="classInfoDiv" name="classInfoDiv" cssStyle="overflow:hidden;height: 400px">
                    <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

                    <tr class="bodyElementsRows">
                        <td colspan="4" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                                <tr class="bodyElementsRows">
                                    <td align="center" dir="<s:text name="dir.ltr" />" width="100%">
                                        <s:text name="dailyClass.classType.termic"/>
                                        <input type="radio" name="classType" id="classType1" value="<%=ServerConstants.CLASS_TYPE_TERMIC%>" checked="true" onclick="selectClassType();" tabindex="4">&nbsp;&nbsp;
                                        <s:text name="dailyClass.classType.esp"/>
                                        <input type="radio" name="classType" id="classType2" value="<%=ServerConstants.CLASS_TYPE_ESP%>" onclick="selectClassType();" tabindex="5">&nbsp;&nbsp;
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">

                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="label.branch"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="35%">
                            <sj:select id="branchId"
                                       name="branchId"
                                       href="activeBranchesListAction.action"
                                       formIds="dailyClassDefinitionInputForm"
                                       list="activeBranchesList"
                                       listKey="branchId"
                                       listValue="branchName"
                                       dataType="json"
                                       cssClass="commonElement"
                                       cssStyle="width: 90%"
                                       headerKey="-1"
                                       headerValue="%{getText('label.notDefined')}"
                                       emptyOption="false"
                                       onChangeTopics="branchSelected"
                                       onSuccessTopics="branchSelected"
                                       tabindex="6"
                                    >
                            </sj:select>
                        </td>

                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="10%">
                            <s:text name="label.roomName"/>
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="35%">
                            <sj:select id="roomId"
                                       name="roomId"
                                       href="selectedBranchRoomsJsonAction.action"
                                       formIds="dailyClassDefinitionInputForm"
                                       list="selectedBranchRoomsList"
                                       listKey="roomId"
                                       listValue="roomName"
                                       dataType="json"
                                       cssClass="commonElement"
                                       cssStyle="width: 94%"
                                       headerKey="-1"
                                       headerValue="%{getText('label.notDefined')}"
                                       emptyOption="false"
                                       listenTopics="branchSelected"
                                       reloadTopics="branchSelected"
                                       deferredLoading="true"
                                       onchange="addRoomNameToClassName()"
                                       tabindex="7"
                                    >
                            </sj:select>
                                <%--deferredLoading="true" : Defers the initial loading of this element. The element will not be loaded until one of the reloadTopics is published.--%>
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="label.levelName"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="35%">

                            <sj:select id="levelId"
                                       name="levelId"
                                       href="cycleLevelsListAction.action"
                                       list="cycleLevelsList"
                                       listKey="levelId"
                                       listValue="levelName"
                                       dataType="json"
                                       cssClass="commonElement"
                                       cssStyle="width: 90%"
                                       headerKey="-1"
                                       headerValue="%{getText('label.notDefined')}"
                                       emptyOption="false"
                                       onchange="addLevelNameToClassName();"
                                       tabindex="6"
                                    >
                            </sj:select>

                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="10%">
                            <s:text name="label.termName"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="35%">

                            <sj:select id="termId"
                                       name="termId"
                                       href="termsListForNextMonthsAction.action"
                                       list="termsListForNextMonths"
                                       listKey="termId"
                                       listValue="termName"
                                       dataType="json"
                                       cssClass="commonElement"
                                       cssStyle="width: 94%"
                                       headerKey="-1"
                                       headerValue="%{getText('label.notDefined')}"
                                       emptyOption="false"
                                       onchange="addTermNameToClassName();"
                                       tabindex="7"
                                    >
                            </sj:select>
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="dailyClass.classSessionsCount"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="35%">
                            <s:textfield id="classSessionsCount" name="classSessionsCount" maxLength="2" theme="simple" cssStyle="width:89%" onkeypress="return numericOnKeyPress(event);" tabindex="8" />
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="10%">
                            <s:text name="dailyClass.classFee"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="35%">
                            <s:textfield id="classFee" name="classFee" maxLength="7" theme="simple" cssStyle="width:91%" onkeypress="return numericOnKeyPress(event);" tabindex="9" />
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="dailyClass.startDate"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="35%">
                            <s:textfield id="startDate" name="startDate" maxLength="10" required="true" validate="true" theme="simple" cssStyle="width: 100px" tabindex="10"/>
                            <img id="startDateImg" src="<s:text name="monitoring.datePickerImage" />" style="vertical-align: top;" />
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="10%">
                            <s:text name="dailyClass.endDate"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="35%">
                            <s:textfield id="endDate" name="endDate" maxLength="10" required="true" validate="true" theme="simple" cssStyle="width: 100px" tabindex="11"/>
                            <img id="endDateImg" src="<s:text name="monitoring.datePickerImage" />" style="vertical-align: top;" />
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="10%">
                            <s:text name="dailyClass.registerDeadline"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="35%">
                            <s:textfield id="registerDeadline" name="registerDeadline" maxLength="10" required="true" validate="true" theme="simple" cssStyle="width: 100px" tabindex="11"/>
                            <img id="endDateImg" src="<s:text name="monitoring.datePickerImage" />" style="vertical-align: top;" />
                        </td>

                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="dailyClass.classCapacity"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="35%">
                            <s:textfield id="classCapacity" name="classCapacity" maxLength="2" theme="simple" cssStyle="width:91%" onkeypress="return numericOnKeyPress(event);" tabindex="12" />
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="label.className"/>:
                        </td>
                        <td colspan="3" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                            <s:textfield id="className" name="className" maxLength="200" theme="simple" cssStyle="width:97%" onkeypress="return englishAndNumberOnKeyPress(event);" tabindex="13"/>
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">

                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="20%">
                            <s:text name="label.cycle"/>:
                        </td>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="35%">
                            <sj:select id="cycleId"
                                       name="cycleId"
                                       href="allCyclesListAction.action"
                                       formIds="dailyClassDefinitionInputForm"
                                       list="allCyclesList"
                                       listKey="cycleId"
                                       listValue="cycleName"
                                       dataType="json"
                                       cssClass="commonElement"
                                       cssStyle="width: 90%"
                                       headerKey="-1"
                                       headerValue="%{getText('label.notDefined')}"
                                       emptyOption="false"
                                       tabindex="6"
                                    >
                            </sj:select>

                        </td>
                        <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                            <input type="button" id="addCycleToGridBtn" value="<s:text name="label.add"/>" style="width: 75px;" class="buttonForward" onclick="return addClassCycleToGrid();"   tabindex="27" >
                            <input type="button" id="clearCycleGridBtn" value="<s:text name="label.clearGrid"/>" style="width: 75px;" class="buttonForward" onclick="return clearCycleGridData();" tabindex="28" >
                        </td>
                    </tr>

                    <tr class="bodyElementsRows">
                        <td colspan="4"  align="center" dir="<s:text name="dir.ltr" />" >
                            <s:url id="classCyclesListUrl" namespace="/" action="classDaysListJsonAction"/>
                            <sjg:grid
                                        id="classCyclesGrid"
                                        caption="%{getText('dailyClass.classCyclesInfo')}"
                                        dataType="local"
                                        formIds="dailyClassDefinitionInputForm"
                                        gridModel="gridModel"
                                        pager="true"
                                        direction="%{getText('dir.ltr')}"
                                        rowList="20,30,50"
                                        rowNum="0"
                                        rownumbers="true"
                                        draggable="false"
                                        resizable="false"
                                        resizableAnimate="false"
                                        resizableGhost="false"
                                        resizableHandles="false"
                                        scroll="true"
                                        scrollrows="true"
                                        pagerButtons="true"
                                        width="350"
                                        shrinkToFit="false"
                                        autowidth="false"
                                        height="100"
                                        multiselect="false"
                                        onSelectRowTopics="rowselect"
                                        navigator="true"
                                        navigatorSearch="false"
                                        navigatorEdit="false"
                                        navigatorDelete="true"
                                        navigatorDeleteOptions="{height:280,reloadAfterSubmit:false}"
                                        navigatorAdd="false"
                                        viewrecords="false"
                                        navigatorView="false"
                                        navigatorRefresh="false"
                                        navigatorExtraButtons="{ separator: {title :'separator'},
                                                                 hide     : {caption : '%{getText('button.delete')}',
                                                                             icon: 'ui-icon-trash',
                                                                             title: 'Delete selected row',
                                                                             onclick: function(){ deleteSelectedRow();}
                                                                             }
                                                           }"

                                    >
                                <sjg:gridColumn name="classCycleId"      index="classCycleId"      title="%{getText('label.cycle')}"       hidden="false"  width="50"  resizable="false" sortable="true"  search="false" />
                                <sjg:gridColumn name="classCycleName"    index="classCycleName"    title="%{getText('label.cycleName')}"   hidden="false" width="150" resizable="false" sortable="true"  search="false" />
                            </sjg:grid>

                        </td>
                    </tr>

                    </table>
                </s:div>

                <s:div id="classDayInfoDiv" name="classDayInfoDiv" cssStyle="overflow:hidden;height: 400px">
                    <table dir="<s:text name="dir.ltr"/>" border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                                <table dir="<s:text name="dir.ltr"/>" border="0" cellpadding="0" cellspacing="0"  width="100%">
                                    <tr class="bodyElementsRows">
                                        <td align="center" dir="<s:text name="dir.ltr"/>" width="100%">

                                            <s:text name="label.evenDays"/>
                                            <input type="radio" name="classDayType" id="dayType2" value="1" onclick="selectClassDays(this.value);" tabindex="14">&nbsp;&nbsp;
                                            <s:text name="label.oddDays"/>
                                            <input type="radio" name="classDayType" id="dayType3" value="2" onclick="selectClassDays(this.value);" tabindex="15">&nbsp;&nbsp;
                                            <s:text name="label.everyDay"/>
                                            <input type="radio" name="classDayType" id="dayType4" value="3" onclick="selectClassDays(this.value);" tabindex="16">&nbsp;&nbsp;
                                            <s:text name="label.other"/>
                                            <input type="radio" name="classDayType" id="dayType1" value="0" onclick="selectClassDays(this.value);" checked="true" tabindex="17">

                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%" height="20px;">
                                &nbsp;
                            </td>
                        </tr>

                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" height="40px;">
                                <table dir="<s:text name="dir.ltr"/>" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
                                    <tr class="bodyElementsRows">
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="100px;"  height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <s:text name="label.day"/>
                                        </td>
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="70px;" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <s:text name="dailyClass.startTime"/>
                                        </td>
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="70px;" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <s:text name="dailyClass.endTime"/>
                                        </td>
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="60px;" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <s:text name="dailyClass.sessionCount"/>
                                        </td>
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="100px;" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <s:text name="dailyClass.sessionType"/>
                                        </td>
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="80px;" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <s:text name="dailyClass.sessionTeacherId"/>
                                        </td>
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="70px;" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            &nbsp;
                                        </td>
                                        <td align="center" dir="<s:text name="dir.ltr" />" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <s:text name="label.teacherName"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                <table dir="<s:text name="dir.ltr" />" border="0" cellpadding="0" cellspacing="0" width="100%" >
                                    <tr class="bodyElementsRows">
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="100px;" height="10px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <sj:select
                                                       id="localDayNo"
                                                       name="localDayNo"
                                                       href="classDaysListAction.action"
                                                       list="classDaysList"
                                                       listKey="key"
                                                       listValue="value"
                                                       dataType="json"
                                                       cssClass="commonElement"
                                                       cssStyle="width: 88px"
                                                       headerKey="-1"
                                                       headerValue="%{getText('label.notDefined')}"
                                                       emptyOption="false"
                                                       tabindex="20"
                                                    >
                                            </sj:select>
                                        </td>
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="70px;"  height="10px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <sj:datepicker id="localClassStartTime" name="localClassStartTime" maxLength="5" required="true" validate="true" theme="simple" timepicker="true" timepickerOnly="true" cssStyle="width: 40px" tabindex="21"/>
                                        </td>
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="70px;"  height="10px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <sj:datepicker id="localClassEndTime" name="localClassEndTime" maxLength="5" required="true" validate="true" theme="simple" timepicker="true" timepickerOnly="true" cssStyle="width: 40px" tabindex="22"/>
                                        </td>
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="60px;"  height="10px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <s:textfield id="localSessionCount" name="localSessionCount" maxLength="20" theme="simple" cssStyle="width:45px" onkeypress="return numericOnKeyPress(event);" tabindex="23"/>
                                        </td>
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="100px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <sj:select
                                                       id="localSessionType"
                                                       name="localSessionType"
                                                       href="sessionTypesListAction.action"
                                                       list="sessionTypesList"
                                                       listKey="key"
                                                       listValue="value"
                                                       dataType="json"
                                                       cssClass="commonElement"
                                                       theme="simple"
                                                       cssStyle="width: 88px"
                                                       headerKey="-1"
                                                       headerValue="%{getText('label.notDefined')}"
                                                       emptyOption="false"
                                                       tabindex="24"
                                                    >
                                            </sj:select>
                                        </td>
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="80px" height="10px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <s:textfield id="localTeacherId" name="localTeacherId" maxLength="6" theme="simple" cssStyle="width:70px" onkeypress="return numericOnKeyPress(event);" tabindex="25"/>
                                        </td>
                                        <td align="center" dir="<s:text name="dir.ltr" />" width="70px" height="10px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">
                                            <s:url id="dailyClassTeacherNameJsonAction" namespace="/" action="dailyClassTeacherNameJsonAction"/>
                                            <sj:a
                                                    id="fetchTchNameBtn"
                                                    name="fetchTchNameBtn"
                                                    button="true"
                                                    value="%{getText('button.fetch')}"
                                                    href="%{dailyClassTeacherNameJsonAction}"
                                                    formIds="dailyClassDefinitionInputForm"
                                                    effect="pulsate"
                                                    effectDuration="1500"
                                                    effectOptions="{mode:'show' , times:3}"
                                                    targets="teacherNameSpan"
                                                    cssStyle="width: 60px;"
                                                    buttonIcon="ui-icon-gear"
                                                    indicator="indicatorFetch"
                                                    onBeforeTopics="validateTeacherId"
                                                    onErrorTopics="handleError"
                                                    tabindex="26">
                                                <s:text name="button.fetch"/>
                                            </sj:a>
                                        </td>
                                        <td id="teacherNameTD" align="center" dir="<s:text name="dir.ltr" />" height="40px;" style="border: solid; border-width: 1px; border-color: #bbbbbb">
                                            <img id="indicatorFetch" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
                                            <span id="teacherNameSpan" name="teacherNameSpan">
                                                &nbsp;
                                            </span>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr class="bodyElementsRows">
                            <td>
                                <table dir="<s:text name="dir.ltr" />" width="100%">
                                    <tr class="bodyElementsRows">
                                        <td dir="<s:text name="dir.ltr" />" align="center">
                                            <input type="button" value="<s:text name="label.add"/>" style="width: 75px;" class="buttonForward" onclick="return addClassDaysToGrid();"  tabindex="27" >
                                            <input type="button" value="<s:text name="label.clearGrid"/>" style="width: 75px;" class="buttonForward" onclick="return clearClassDaysGridData();"  tabindex="28" >
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <tr class="bodyElementsRows">
                            <td align="center" dir="<s:text name="dir.ltr" />" >
                                <s:url id="classDaysListUrl" namespace="/" action="classDaysListJsonAction"/>
                                <sjg:grid
                                            id="classDaysGrid"
                                            caption="%{getText('dailyClass.classDayInfo')}"
                                            dataType="local"
                                            formIds="dailyClassDefinitionInputForm"
                                            gridModel="gridModel"
                                            pager="true"
                                            direction="%{getText('dir.ltr')}"
                                            rowList="20,30,50"
                                            rowNum="0"
                                            rownumbers="true"
                                            draggable="false"
                                            resizable="false"
                                            resizableAnimate="false"
                                            resizableGhost="false"
                                            resizableHandles="false"
                                            scroll="true"
                                            scrollrows="true"
                                            pagerButtons="true"
                                            width="750"
                                            shrinkToFit="false"
                                            autowidth="false"
                                            height="150"
                                            multiselect="false"
                                            onSelectRowTopics="rowselect"
                                            navigator="true"
                                            navigatorSearch="false"
                                            navigatorEdit="false"
                                            navigatorDelete="true"
                                            navigatorDeleteOptions="{height:280,reloadAfterSubmit:false}"
                                            navigatorAdd="false"
                                            viewrecords="false"
                                            navigatorView="false"
                                            navigatorRefresh="false"
                                            navigatorExtraButtons="{ separator: {title :'separator'},
                                                                     hide     : {caption : '%{getText('button.delete')}',
                                                                                 icon: 'ui-icon-trash',
                                                                                 title: 'Delete selected row',
                                                                                 onclick: function(){ deleteSelectedRow();}
                                                                                 }
                                                               }"

                                        >
                                    <sjg:gridColumn name="classDayNo"        index="classDayNo"      title="%{getText('dailyClass.classDayName')}"       hidden="true"  width="20"  resizable="false" sortable="true"  search="false" />
                                    <sjg:gridColumn name="classDayName"      index="classDayName"    title="%{getText('label.day')}"                     hidden="false" width="100" resizable="false" sortable="true"  search="false" />
                                    <sjg:gridColumn name="classStartTime"    index="classStartTime"  title="%{getText('dailyClass.startTime')}"          hidden="false" width="70"  resizable="false" sortable="true"  search="false" />
                                    <sjg:gridColumn name="classEndTime"      index="classEndTime"    title="%{getText('dailyClass.endTime')}"            hidden="false" width="70"  resizable="false" sortable="false" search="false" />
                                    <sjg:gridColumn name="sessionCount"      index="sessionCount"    title="%{getText('dailyClass.sessionCount')}"       hidden="false" width="80"  resizable="false" sortable="false" search="false" />
                                    <sjg:gridColumn name="sessionTypeId"     index="sessionTypeId"   title="%{getText('dailyClass.sessionType')}"        hidden="true"  width="100" resizable="false" sortable="false" search="false" />
                                    <sjg:gridColumn name="sessionTypeName"   index="sessionTypeName" title="%{getText('dailyClass.sessionType')}"        hidden="false" width="100" resizable="false" sortable="false" search="false" />
                                    <sjg:gridColumn name="teacherId"         index="teacherId"       title="%{getText('dailyClass.sessionTeacherId')}"   hidden="false" width="100" resizable="false" sortable="false" search="false" />
                                    <sjg:gridColumn name="teacherName"       index="teacherName"     title="%{getText('dailyClass.sessionTeacherName')}" hidden="false" width="100" resizable="false" sortable="false" search="false" />
                                </sjg:grid>

                            </td>
                        </tr>

                    </table>
                </s:div>

            </sj:tabbedpanel>

        </td>
    </tr>

    <tr class="bodyElementsRows">
        <td>
            <table dir="<s:text name="dir.ltr" />" width="100%">
                <tr class="bodyElementsRows">
                    <td dir="<s:text name="dir.ltr" />" align="center">
                        <s:submit id="saveButton" name="saveButton" key="button.save" action="dailyClassDefinitionSaveAction" cssStyle="width: 75px;" cssClass="buttonForward" onclick="return validateForm();" tabindex="30" />
                    </td>
                </tr>
            </table>
        </td>
    </tr>

</table>

</s:div>
    <input type="hidden" id="classDayRowId"       name="classDayRowId"/>
    <input type="hidden" id="classDaysCount"      name="classDaysCount"/>
    <input type="hidden" id="classCyclesCount"    name="classCyclesCount"/>
<%for(int i = 0; i < ServerConstants.MAX_CLASS_DAYS_COUNT; i++){%>
    <input type="hidden" id="classDays[<%= i %>]"       name="classDays[<%= i %>]"/>
    <input type="hidden" id="classStartTimes[<%= i %>]" name="classStartTimes[<%= i %>]"/>
    <input type="hidden" id="classEndTimes[<%= i %>]"   name="classEndTimes[<%= i %>]"/>
    <input type="hidden" id="sessionCounts[<%= i %>]"   name="sessionCounts[<%= i %>]"/>
    <input type="hidden" id="sessionTypes[<%= i %>]"    name="sessionTypes[<%= i %>]"/>
    <input type="hidden" id="teacherIds[<%= i %>]"      name="teacherIds[<%= i %>]"/>
<%}%>
<%for(int i = 0; i < ServerConstants.MAX_CLASS_CYCLES_COUNT; i++){%>
    <input type="hidden" id="classCycles[<%= i %>]"     name="classCycles[<%= i %>]"/>
<%}%>
</s:form>

<img id="indicator" src="/images/indicator.gif" alt="Loading..." style="display:none"/>

<script type="text/javascript" language="javascript">

$(document).ready(function() {
//======================================================================================================================
    handleEditMode()
    selectClassType();
//======================================================================================================================
    Calendar.setup({
        inputField  : "startDate",             // id of the input field
        button      : "startDateImg",       // trigger for the calendar (button ID)
        ifFormat    : "%Y/%m/%d",            // format of the input field
        dateType	: 'jalali'
    });
    $("#startDate").mask("1399/99/99");

    Calendar.setup({
        inputField  : "endDate",             // id of the input field
        button      : "endDateImg",       // trigger for the calendar (button ID)
        ifFormat    : "%Y/%m/%d",            // format of the input field
        dateType	: 'jalali'
    });
    $("#endDate").mask("1399/99/99");

    $("#localClassStartTime").mask("99:99");
    $("#localClassEndTime").mask("99:99");
//======================================================================================================================
    $.subscribe('validateClassId', function(event,data) {

        removeClientErrors();

        var elmValue = getElement("classId").value;

        if(isNullOrEmpty(elmValue)){
            event.originalEvent.options.submit = false;
            getElement("indicator1" + dayRowId).style.display = 'none';
            addClientError('<s:text name="error.common.classIdRequired" />');
            getElement("classId").focus();
            return false;
        }
        else if(!isNumeric(elmValue)){
            event.originalEvent.options.submit = false;
            getElement("indicator1" + dayRowId).style.display = 'none';
            addClientError('<s:text name="error.common.invalidClassId" />');
            getElement("classId").focus();
            return false;
        }
        else if(elmValue == 0){
            event.originalEvent.options.submit = false;
            getElement("indicator1" + dayRowId).style.display = 'none';
            addClientError('<s:text name="error.common.invalidClassId" />');
            getElement("classId").focus();
            return false;
        }

        return true;

    });
//======================================================================================================================
    $.subscribe('handleSuccessFetchClass', function(event,data) {
        removeClientErrors();

        var classInfo = event.originalEvent.data;

        $('#classDayType').val(classInfo.classDayType);
        $('#branchId').val(classInfo.branchId);
        $.publish('branchSelected');
        $('#roomId').val(classInfo.roomId);
        $('#levelId').val(classInfo.levelId);
        $('#termId').val(classInfo.termId);
        $('#classType').val(classInfo.classType);
        $('#classDayType').val(classInfo.classDayType);
        $('#classFee').val(classInfo.classFee);
        $('#classCapacity').val(classInfo.classCapacity);
        $('#className').val(classInfo.className);
        $('#branchId').focus();

    });
//======================================================================================================================
    $.subscribe('validateTeacherId', function(event,data) {

        removeClientErrors();
        getElement("teacherNameSpan").innerHTML = "";

        var elmValue = getElement("localTeacherId").value;

        if(isNullOrEmpty(elmValue)){
            event.originalEvent.options.submit = false;
            getElement("indicatorFetch" + dayRowId).style.display = 'none';
            addClientError('<s:text name="error.dailyClass.teacherIdRequired" />');
            getElement("localTeacherId").focus();
            return false;
        }
        else if(!isNumeric(elmValue)){
            event.originalEvent.options.submit = false;
            getElement("indicatorFetch" + dayRowId).style.display = 'none';
            addClientError('<s:text name="error.dailyClass.invalidTeacherId" />');
            getElement("localTeacherId").focus();
            return false;
        }
        else if(elmValue <= 0){
            event.originalEvent.options.submit = false;
            getElement("indicatorFetch" + dayRowId).style.display = 'none';
            addClientError('<s:text name="error.dailyClass.invalidTeacherId" />');
            getElement("localTeacherId").focus();
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
    $.subscribe('handleServerError', function(event,data) {

        removeClientErrors();
        var jsonObjects = eval('(' + event.originalEvent.data + ')');
        if(jsonObjects != null){
            var errorMessage = "";
            if(jsonObjects.actionErrors != null){
                $.each(jsonObjects.actionErrors, function(e) {
                    errorMessage += jsonObjects.actionErrors;
                });
                addClientError(errorMessage);
            }
            if(jsonObjects.actionMessages != null){
                $.each(jsonObjects.actionErrors, function(e) {
                    errorMessage += jsonObjects.actionMessages;
                });
                addClientError(errorMessage);
            }
        }
    });
//======================================================================================================================
    $.subscribe('getClassFee', function(event,data){

        removeClientErrors();

        var elmValue = getElement("classType").selectedIndex;

        if(isNullOrEmpty(elmValue) || elmValue == 0){
            addClientError('<s:text name="error.dailyClass.classTypeRequired" />');
            getElement("classType").focus();
            return false;
        }

        $.ajax({
            cache:false,
            url: "ajax/fetchSelectedClassFeeJsonAction.action",
            type: "POST",
            data: {classType: $("#classType").val()},
            dataType: "json",

            error: function() {
                addClientError('<s:text name="error.dailyClass.invalidClassType" />');
                $('#classFee').val("");
            },
            success: function(tmpClassFee) {
                $('#classFee').val(tmpClassFee);
            }
        });
        return false;
    });
//======================================================================================================================
    $.subscribe('rowselect', function(event, data) {
        alert('Selected Row : ' + event.originalEvent.id);
    });
//======================================================================================================================
});
//======================================================================================================================
    function deleteSelectedRow(){

        var ids = $("#classDaysGrid").jqGrid('getGridParam','selarrrow');
        alert(ids);
        $("#classDaysGrid").delRowData( '1' );
    }
//======================================================================================================================
    function selectClassDays(radioValue){

        if(parseInt(radioValue) == 0){   // other

            getElement("localDayNo").disabled = false;
        }
        else {
            getElement("localDayNo").selectedIndex = 0;
            getElement("localDayNo").disabled = true;
        }
        return true;
    }
//======================================================================================================================
    function addClassDaysToGrid(){

        var selectedClassDayType = getRadioOptionValue("classDayType");
        var selectedDayNo = getSelectValue("localDayNo");
        if(selectedClassDayType == 0 && selectedDayNo == -1){
            return;
        }

        var localStartTime = getElement("localClassStartTime").value;
        if(isNullOrEmpty(localStartTime)){
            alert('<s:text name="error.dailyClass.startTimeRequired" />');
            return;
        }
        var localEndTime   = getElement("localClassEndTime").value;
        if(isNullOrEmpty(localEndTime)){
            alert('<s:text name="error.dailyClass.endTimeRequired" />');
            return;
        }

        var lclSessionCount = getElement("localSessionCount").value;
        if(isNullOrEmpty(lclSessionCount)){
            alert('<s:text name="error.dailyClass.sessionCountRequired" />');
            return;
        }

        var sessionTypeId = getSelectValue("localSessionType");
        if(sessionTypeId == -1){
            alert('<s:text name="error.dailyClass.sessionTypeRequired" />');
            return;
        }
        var selectedDayName = getSelectText("localDayNo");
        var sessionTypeName = getSelectText("localSessionType");

        var localTeacherId = getElement("localTeacherId").value;
        if(isNullOrEmpty(localTeacherId)){
            alert('<s:text name="error.dailyClass.teacherIdRequired" />');
            return;
        }

        var localTeacherName = getElement("teacherNameSpan").innerHTML;
        if(isNullOrEmpty(localTeacherName)){
            alert('<s:text name="error.dailyClass.validateTeacherId" />');
            return;
        }

        var gridData;
        switch(parseInt(selectedClassDayType)){
            case 0 :
                gridData = [
                                {"classDayNo"     : selectedDayNo,
                                "classDayName"    : selectedDayName,
                                "classStartTime"  : localStartTime,
                                "classEndTime"    : localEndTime,
                                "sessionCount"    : lclSessionCount,
                                "sessionTypeId"   : sessionTypeId,
                                "sessionTypeName" : sessionTypeName,
                                "teacherId"       : localTeacherId,
                                "teacherName"     : localTeacherName}
                            ];
                break;

            case 1 :
                gridData = [
                                {"classDayNo"     : '0',
                                "classDayName"    : '<s:text name="label.saturday"/>',
                                "classStartTime"  : localStartTime,
                                "classEndTime"    : localEndTime,
                                "sessionCount"    : lclSessionCount,
                                "sessionTypeId"   : sessionTypeId,
                                "sessionTypeName" : sessionTypeName,
                                "teacherId"       : localTeacherId,
                                "teacherName"     : localTeacherName},

                                {"classDayNo"     : '2',
                                "classDayName"    : '<s:text name="label.monday"/>',
                                "classStartTime"  : localStartTime,
                                "classEndTime"    : localEndTime,
                                "sessionCount"    : lclSessionCount,
                                "sessionTypeId"   : sessionTypeId,
                                "sessionTypeName" : sessionTypeName,
                                "teacherId"       : localTeacherId,
                                "teacherName"     : localTeacherName},

                                {"classDayNo"     : '4',
                                "classDayName"    : '<s:text name="label.wednesday"/>',
                                "classStartTime"  : localStartTime,
                                "classEndTime"    : localEndTime,
                                "sessionCount"    : lclSessionCount,
                                "sessionTypeId"   : sessionTypeId,
                                "sessionTypeName" : sessionTypeName,
                                "teacherId"       : localTeacherId,
                                "teacherName"     : localTeacherName}
                            ];
                break;

            case 2 :
                gridData = [
                                {"classDayNo"     : '1',
                                "classDayName"    : '<s:text name="label.sunday"/>',
                                "classStartTime"  : localStartTime,
                                "classEndTime"    : localEndTime,
                                "sessionCount"    : lclSessionCount,
                                "sessionTypeId"   : sessionTypeId,
                                "sessionTypeName" : sessionTypeName,
                                "teacherId"       : localTeacherId,
                                "teacherName"     : localTeacherName},

                                {"classDayNo"     : '3',
                                "classDayName"    : '<s:text name="label.tuesday"/>',
                                "classStartTime"  : localStartTime,
                                "classEndTime"    : localEndTime,
                                "sessionCount"    : lclSessionCount,
                                "sessionTypeId"   : sessionTypeId,
                                "sessionTypeName" : sessionTypeName,
                                "teacherId"       : localTeacherId,
                                "teacherName"     : localTeacherName},

                                {"classDayNo"     : '5',
                                "classDayName"    : '<s:text name="label.thursday"/>',
                                "classStartTime"  : localStartTime,
                                "classEndTime"    : localEndTime,
                                "sessionCount"    : lclSessionCount,
                                "sessionTypeId"   : sessionTypeId,
                                "sessionTypeName" : sessionTypeName,
                                "teacherId"       : localTeacherId,
                                "teacherName"     : localTeacherName}
                            ];
                break;

            case 3 :
                gridData = [
                                {"classDayNo"     : '0',
                                "classDayName"    : '<s:text name="label.saturday"/>',
                                "classStartTime"  : localStartTime,
                                "classEndTime"    : localEndTime,
                                "sessionCount"    : lclSessionCount,
                                "sessionTypeId"   : sessionTypeId,
                                "sessionTypeName" : sessionTypeName,
                                "teacherId"       : localTeacherId,
                                "teacherName"     : localTeacherName},

                                {"classDayNo"     : '1',
                                "classDayName"    : '<s:text name="label.sunday"/>',
                                "classStartTime"  : localStartTime,
                                "classEndTime"    : localEndTime,
                                "sessionCount"    : lclSessionCount,
                                "sessionTypeId"   : sessionTypeId,
                                "sessionTypeName" : sessionTypeName,
                                "teacherId"       : localTeacherId,
                                "teacherName"     : localTeacherName},

                                {"classDayNo"     : '2',
                                "classDayName"    : '<s:text name="label.monday"/>',
                                "classStartTime"  : localStartTime,
                                "classEndTime"    : localEndTime,
                                "sessionCount"    : lclSessionCount,
                                "sessionTypeId"   : sessionTypeId,
                                "sessionTypeName" : sessionTypeName,
                                "teacherId"       : localTeacherId,
                                "teacherName"     : localTeacherName},

                                {"classDayNo"     : '3',
                                "classDayName"    : '<s:text name="label.tuesday"/>',
                                "classStartTime"  : localStartTime,
                                "classEndTime"    : localEndTime,
                                "sessionCount"    : lclSessionCount,
                                "sessionTypeId"   : sessionTypeId,
                                "sessionTypeName" : sessionTypeName,
                                "teacherId"       : localTeacherId,
                                "teacherName"     : localTeacherName},

                                {"classDayNo"     : '4',
                                "classDayName"    : '<s:text name="label.wednesday"/>',
                                "classStartTime"  : localStartTime,
                                "classEndTime"    : localEndTime,
                                "sessionCount"    : lclSessionCount,
                                "sessionTypeId"   : sessionTypeId,
                                "sessionTypeName" : sessionTypeName,
                                "teacherId"       : localTeacherId,
                                "teacherName"     : localTeacherName},

                                {"classDayNo"     : '5',
                                "classDayName"    : '<s:text name="label.thursday"/>',
                                "classStartTime"  : localStartTime,
                                "classEndTime"    : localEndTime,
                                "sessionCount"    : lclSessionCount,
                                "sessionTypeId"   : sessionTypeId,
                                "sessionTypeName" : sessionTypeName,
                                "teacherId"       : localTeacherId,
                                "teacherName"     : localTeacherName}
                            ];
                break;

        }
        for(var i = 0; i < gridData.length;i++){
            $("#classDaysGrid").jqGrid('addRowData',i+1,gridData[i]);
        }

        return true;
    }
//======================================================================================================================
    function clearClassDaysGridData(){
        $("#classDaysGrid").jqGrid('clearGridData',true);
        return true;
    }
//======================================================================================================================
    function selectClassType(){

        var radioValue = getRadioOptionValue("classType");
        if(parseInt(radioValue) == <%=ServerConstants.CLASS_TYPE_TERMIC%>){          // termic

            getElement("cycleId").disabled = true;
            getElement("addCycleToGridBtn").disabled = true;
            getElement("clearCycleGridBtn").disabled = true;
        }
        else if(parseInt(radioValue) == <%=ServerConstants.CLASS_TYPE_ESP%>){      // ESP
            getElement("cycleId").disabled = false;
            getElement("addCycleToGridBtn").disabled = false;
            getElement("clearCycleGridBtn").disabled = false;
        }
        return true;
    }
//======================================================================================================================
    function addClassCycleToGrid(){

        var selectedCycleId = getSelectValue("cycleId");
        if(selectedCycleId == -1){
            return;
        }
        var selectedCycleName = getSelectText("cycleId");

        var cycleGridData = $("#classCyclesGrid").jqGrid('getGridParam','data');
        if(cycleGridData != null || cycleGridData.length > 0){
            var duplicateCycleId = false;
            for(var rowData in cycleGridData){
                var addedCycleId = cycleGridData[rowData].classCycleId;
                if(addedCycleId == selectedCycleId){
                    duplicateCycleId = true;
                    break;
                }
            }
            if(duplicateCycleId == true){
                alert('<s:text name="error.common.duplicateCycleId" />');
                return;
            }
        }
        var gridData = [
                        {"classCycleId"     : selectedCycleId,
                         "classCycleName"   : selectedCycleName}
                    ];
        for(var i = 0; i < gridData.length;i++){
            $("#classCyclesGrid").jqGrid('addRowData',i+1,gridData[i]);
        }

        return true;
    }
//======================================================================================================================
    function clearCycleGridData(){
        $("#classCyclesGrid").jqGrid('clearGridData',true);
        return true;
    }
//======================================================================================================================
    function handleEditMode(){

        if(getElement("editMode").checked){
            getElement("classId").disabled = false;
            getElement("fetchClassInfoBtn").disabled = false;
        }
        else{
            getElement("classId").disabled = true;
            getElement("fetchClassInfoBtn").disabled = true;
        }
    }
//======================================================================================================================
    function validateForm(){

        removeClientErrors();

        if(getElement("branchId").selectedIndex <= 0){
            addClientError('<s:text name="error.common.branchRequired" />');
            getElement("branchId").focus();
        }

        if(getElement("roomId").selectedIndex <= 0){
            addClientError('<s:text name="error.common.roomIdRequired" />');
            getElement("roomId").focus();
        }

        if(hasClientError)
            return false;

        if(getElement("levelId").selectedIndex <= 0){
            addClientError('<s:text name="error.common.cycleLevelIdRequired" />');
            getElement("levelId").focus();
        }

        if(getElement("termId").selectedIndex <= 0){
            addClientError('<s:text name="error.common.termRequired" />');
            getElement("termId").focus();
        }

        if(hasClientError)
            return false;

        if(isNullOrEmpty(getElement("classSessionsCount").value)){
            addClientError('<s:text name="error.dailyClass.sessionCountRequired" />');
            getElement("classSessionsCount").focus();
        }
        else if(getElement("classSessionsCount").value <= 0){
            addClientError('<s:text name="error.dailyClass.invalidSessionCount" />');
            getElement("classSessionsCount").focus();
        }

        if(isNullOrEmpty(getElement("classFee").value)){
            addClientError('<s:text name="error.dailyClass.classFeeRequired" />');
            getElement("classFee").focus();
        }
        else if(getElement("classFee").value <= 0){
            addClientError('<s:text name="error.dailyClass.invalidClassFee" />');
            getElement("classFee").focus();
        }

        if(hasClientError)
            return false;

        if(isNullOrEmpty(getElement("startDate").value)){
            addClientError('<s:text name="error.dailyClass.startDateRequired" />');
            getElement("startDate").focus();
        }
        else if(isValidDate(getElement("startDate").value)){
            addClientError('<s:text name="error.dailyClass.invalidStartDate" />');
            getElement("startDate").focus();
        }

        if(isNullOrEmpty(getElement("endDate").value)){
            addClientError('<s:text name="error.dailyClass.endDateRequired" />');
            getElement("endDate").focus();
        }
        else if(isValidDate(getElement("endDate").value)){
            addClientError('<s:text name="error.dailyClass.invalidEndDate" />');
            getElement("endDate").focus();
        }

        if(isNullOrEmpty(getElement("registerDeadline").value)){
            addClientError('<s:text name="error.dailyClass.registerDeadlineRequired" />');
            getElement("registerDeadline").focus();
        }
        else if(isValidDate(getElement("registerDeadline").value)){
            addClientError('<s:text name="error.dailyClass.invalidRegisterDeadline" />');
            getElement("registerDeadline").focus();
        }

        if(hasClientError)
            return false;

        if(isNullOrEmpty(getElement("classCapacity").value)){
            addClientError('<s:text name="error.dailyClass.classCapacityRequired" />');
            getElement("classCapacity").focus();
        }
        else if(getElement("classCapacity").value <= 0){
            addClientError('<s:text name="error.dailyClass.invalidClassCapacity" />');
            getElement("classCapacity").focus();
        }

        if(hasClientError)
            return false;

        if(isNullOrEmpty(getElement("className").value)){
            addClientError('<s:text name="error.dailyClass.classNameRequired" />');
            getElement("className").focus();
        }
        else if(!isEnglishAndNumberText(getElement("className").value)){
            addClientError('<s:text name="error.dailyClass.invalidClassName" />');
            getElement("className").focus();
        }

        if(hasClientError)
            return false;

        var gridData = $("#classDaysGrid").jqGrid('getGridParam','data');
        if(gridData == null || gridData.length == 0){
            addClientError('<s:text name="error.dailyClass.ClassDaysRequired" />');
            return;
        }

        for(var rowData in gridData){
            getElement("classDays["        + rowData + "]").value = gridData[rowData].classDayNo;
//            getElement("dayName["          + rowData + "]").value = gridData[rowData].classDayName;
            getElement("classStartTimes["  + rowData + "]").value = gridData[rowData].classStartTime;
            getElement("classEndTimes["    + rowData + "]").value = gridData[rowData].classEndTime;
            getElement("sessionCounts["    + rowData + "]").value = gridData[rowData].sessionCount;
            getElement("sessionTypes["     + rowData + "]").value = gridData[rowData].sessionTypeId;
//            getElement("sessionTypeName["  + rowData + "]").value = gridData[rowData].sessionTypeName;
            getElement("teacherIds["       + rowData + "]").value = gridData[rowData].teacherId;
//            getElement("teacherName["      + rowData + "]").value = gridData[rowData].teacherName;
        }
        getElement("classDaysCount").value = gridData.length;
        if(hasClientError)
            return false;

        var radioValue = getRadioOptionValue("classType");
        if(parseInt(radioValue) != <%=ServerConstants.CLASS_TYPE_TERMIC%> && parseInt(radioValue) != <%=ServerConstants.CLASS_TYPE_ESP%>){    // Termic == 1 && ESP == 2 Class Types
            addClientError('<s:text name="error.dailyClass.classTypeRequired" />');
            return;
        }
        if(parseInt(radioValue) == <%=ServerConstants.CLASS_TYPE_ESP%>){    // ESP Class Types

            var cycleGridData = $("#classCyclesGrid").jqGrid('getGridParam','data');
            if(cycleGridData == null || cycleGridData.length == 0){
                addClientError('<s:text name="error.dailyClass.classCycleRequired" />');
                return;
            }
            for(var cycleRowData in cycleGridData){
                getElement("classCycles[" + cycleRowData + "]").value = cycleGridData[cycleRowData].classCycleId;
            }
            getElement("classCyclesCount").value = cycleGridData.length;
        }

        if(hasClientError)
            return false;
        return true;
    };
//======================================================================================================================
    var levelName = "";
    var termName = "";
    var dayName = "";
    var timeName = "";
    var roomName = "";
    var className = "";

    function createClassName(){

        if(!isNullOrEmpty(levelName)){
            className = levelName;
        }
        if(!isNullOrEmpty(termName)){
            className = isNullOrEmpty(className) ? termName : className + "-" + termName;
        }
        if(!isNullOrEmpty(dayName)){
            className = isNullOrEmpty(className) ? dayName : className + "-" + dayName;
        }
        if(!isNullOrEmpty(timeName)){
            className = isNullOrEmpty(className) ? timeName : className + "-" + timeName;
        }
        if(!isNullOrEmpty(roomName)){
            className = isNullOrEmpty(className) ? roomName : className + "-" + roomName;
        }
        getElement("className").value = className;
    }
//======================================================================================================================
    function addLevelNameToClassName(){

        levelName = getElement('levelId').options[getElement('levelId').selectedIndex].text;
        createClassName();
    }
//======================================================================================================================
    function addTermNameToClassName(){

        termName = getElement('termId').options[getElement('termId').selectedIndex].text;
        createClassName();
    }
//======================================================================================================================
    function addTimeNameToClassName(elmNo){

        timeName = getElement('firstClassTimeIds' + elmNo).options[getElement('firstClassTimeIds' + elmNo).selectedIndex].text;
        createClassName();
    }
//======================================================================================================================
    function addRoomNameToClassName(){

        roomName = getElement('roomId').options[getElement('roomId').selectedIndex].text;
        createClassName();
    }
//======================================================================================================================

</script>
