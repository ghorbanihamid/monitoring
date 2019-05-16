<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags" %>
<%@ taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>


<%

%>

<s:form namespace="/" id="dailyShiftInputForm" name="dailyShiftInputForm" action="dailyShiftInputAction" theme="simple">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 680px;vertical-align: top; resize: none;">

<h2 align="center"><s:text name="shift.registerNewShiftTitle"/></h2>

<table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">

    <tr class="bodyElementsRows">
        <td>
            <s:div cssStyle="overflow:hidden;height: 20px">
                <s:actionmessage id="serverMessageList" theme="jquery" cssClass="actionMessageClass"/>
                <s:actionerror id="serverErrorList" theme="jquery" cssClass="actionErrorClass"/>
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
            <sj:tabbedpanel id="localTabs">
                <sj:tab id="dailyShiftInfoTab" target="dailyShiftInfoDiv" label="%{getText('shift.dailyShiftList')}"/>
                <%--<sj:tab id="classDayInfoTab"   target="classDayInfoDiv"   label="%{getText('dailyClass.classDayInfo')}"/>--%>

                <s:div id="dailyShiftInfoDiv" name="dailyShiftInfoDiv" cssStyle="overflow:hidden;height: 400px">
                    <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                        <tr class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="50%">

                                <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                                    <tr class="bodyElementsRows">
                                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px">
                                            <s:text name="shift.shiftDate"/>:
                                        </td>
                                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                            <s:textfield id="shiftDateStr" name="staffShift.shiftDateStr" maxLength="10" required="true" validate="true" theme="simple" cssStyle="width: 100px" tabindex="10"/>
                                            <img id="shiftDateImg" src="<s:text name="webApp.datePickerImage" />" style="vertical-align: top;" />
                                        </td>
                                    </tr>
                                    <tr class="bodyElementsRows">
                                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px">
                                            <s:text name="label.staffId"/>:
                                        </td>
                                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                            <s:textfield id="staffId" name="staffShift.staffId" maxLength="10" required="true" validate="true" theme="simple" cssStyle="width: 100px" tabindex="10"/>
                                            &nbsp;
                                            <span id="staffNameSpan">
                                                &nbsp;
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px">
                                            &nbsp;
                                        </td>
                                        <td  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">

                                            <s:url namespace="/" id="dailyShiftInfoUrl"  action="dailyShiftListJsonAction"/>
                                            <sj:a
                                                    id="dailyShiftInfoBtn"
                                                    button="true"
                                                    value="%{getText('button.fetch')}"
                                                    href="%{dailyShiftInfoUrl}"
                                                    formIds="dailyShiftInputForm"
                                                    dataType="json"
                                                    effect="pulsate"
                                                    effectDuration="1500"
                                                    effectOptions="{mode:'show' , times:3}"
                                                    cssStyle="width: 70px;"
                                                    indicator="indicatorFetch"
                                                    buttonIcon="ui-icon-gear"
                                                    onBeforeTopics="validateForFetch"
                                                    onClickTopics="reloadDailyShiftGrid"
                                                    tabindex="3"
                                                    >
                                                <s:text name="button.fetch"/>
                                            </sj:a>
                                            <img id="indicatorFetch" src="/images/indicator.gif" alt="Loading..." style="display:none"/>
                                        </td>

                                    </tr>

                                    <tr class="bodyElementsRows">
                                        <td colspan="2">
                                            &nbsp;
                                        </td>
                                    </tr>

                                    <tr class="bodyElementsRows">
                                        <td colspan="2">
                                            <s:text name="label.day" />
                                            <input type="radio" id="radioDay"    name="shiftStatus"   value="" onclick="return setTimeFields('day')" tabindex="2"/>
                                            &nbsp;&nbsp;
                                            <s:text name="label.night" />
                                            <input type="radio" id="radioNight"  name="shiftStatus" value="" onclick="return setTimeFields('night')" tabindex="2"/>
                                            &nbsp;&nbsp;
                                            <s:text name="label.24time" />
                                            <input type="radio" id="radio24"     name="shiftStatus"    value="" onclick="return setTimeFields('24')" tabindex="2"/>
                                        </td>
                                    </tr>

                                    <tr class="bodyElementsRows">
                                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px">
                                            <s:text name="shift.shiftStartTime"/>:
                                        </td>
                                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                            <sj:datepicker id="shiftStartTimeStr" name="staffShift.shiftStartTimeStr" maxLength="5" required="true" validate="true" theme="simple" timepicker="true" timepickerOnly="true" value="07:00" cssStyle="width: 50px" tabindex="21"/>
                                        </td>
                                    </tr>

                                    <tr class="bodyElementsRows">
                                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px">
                                            <s:text name="shift.shiftEndTime"/>:
                                        </td>
                                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
                                            <sj:datepicker id="shiftEndTimeStr" name="staffShift.shiftEndTimeStr" maxLength="5" required="true" validate="true" theme="simple" timepicker="true" timepickerOnly="true" value="19:00" cssStyle="width: 50px; vertical-align: bottom"  tabindex="21"/>
                                        </td>
                                    </tr>

                                    <tr class="bodyElementsRows">
                                        <td colspan="2">
                                            &nbsp;
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="80px">
                                            &nbsp;
                                        </td>
                                        <td  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">

                                            <s:url namespace="/" id="saveDailyShiftInfoUrl"  action="dailyShiftSaveAction"/>
                                            <sj:a
                                                    id="saveDailyShiftInfoBtn"
                                                    button="true"
                                                    value="%{getText('button.save')}"
                                                    href="%{saveDailyShiftInfoUrl}"
                                                    formIds="dailyShiftInputForm"
                                                    dataType="json"
                                                    effect="pulsate"
                                                    effectDuration="1500"
                                                    effectOptions="{mode:'show' , times:3}"
                                                    cssStyle="width: 70px;"
                                                    indicator="indicatorSave"
                                                    buttonIcon="ui-icon-gear"
                                                    onBeforeTopics="validateForSave"
                                                    onCompleteTopics="reloadDailyShiftGrid"
                                                    tabindex="3"
                                                    >
                                                <s:text name="button.save"/>
                                            </sj:a>
                                            <img id="indicatorSave" src="/images/indicator.gif" alt="Loading..." style="display:none"/>
                                        </td>

                                    </tr>

                                </table>

                            </td>
                            <td align="center" dir="<s:text name="dir.ltr" />" width="50%">
                                <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                                    <tr class="bodyElementsRows">
                                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
                                            <s:url id="dailyShiftListUrl" namespace="/" action="dailyShiftListJsonAction"/>
                                            <sjg:grid
                                                    id="dailyShiftListGrid"
                                                    caption="%{getText('shift.dailyShiftList')}"
                                                    dataType="json"
                                                    href="%{dailyShiftListUrl}"
                                                    formIds="dailyShiftInputForm"
                                                    gridModel="staffShiftList"
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
                                                    width="600"
                                                    shrinkToFit="false"
                                                    autowidth="false"
                                                    height="300"
                                                    multiselect="false"
                                                    onSelectRowTopics="rowselect"
                                                    reloadTopics="reloadDailyShiftGrid"
                                                    navigator="true"
                                                    navigatorSearch="false"
                                                    navigatorEdit="false"
                                                    navigatorDelete="true"
                                                    navigatorDeleteOptions="{height:280,reloadAfterSubmit:true}"
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
                                                <sjg:gridColumn name="shiftDateStr"      index="shiftDate"      title="%{getText('shift.shiftDate')}"       hidden="false"  width="80"  resizable="false" sortable="true"  search="false" />
                                                <sjg:gridColumn name="staffId"           index="staffId"        title="%{getText('label.staffId')}"         hidden="false"  width="80"  resizable="false" sortable="true"  search="false" />
                                                <sjg:gridColumn name="staffFullName"     index="staffName"      title="%{getText('label.staffName')}"       hidden="false"  width="150" resizable="false" sortable="true"  search="false" />
                                                <sjg:gridColumn name="shiftStartTimeStr" index="shiftStartTime" title="%{getText('shift.shiftStartTime')}"  hidden="false"  width="80"  resizable="false" sortable="true"  search="false" />
                                                <sjg:gridColumn name="shiftEndTimeStr"   index="shiftEndTime"   title="%{getText('shift.shiftEndTime')}"    hidden="false"  width="80"  resizable="false" sortable="true"  search="false" />
                                            </sjg:grid>

                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr class="bodyElementsRows">
                            <td colspan="2" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">

                            </td>
                        </tr>
                    </table>

                </s:div>

                <%--<s:div id="classDayInfoDiv" name="classDayInfoDiv" cssStyle="overflow:hidden;height: 400px">--%>
                    <%--<table dir="<s:text name="dir.ltr"/>" border="0" cellpadding="0" cellspacing="0" width="100%">--%>
                        <%--<tr class="bodyElementsRows">--%>
                            <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">--%>
                                <%--<table dir="<s:text name="dir.ltr"/>" border="0" cellpadding="0" cellspacing="0"  width="100%">--%>
                                    <%--<tr class="bodyElementsRows">--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr"/>" width="100%">--%>

                                            <%--<s:text name="label.evenDays"/>--%>
                                            <%--<input type="radio" name="classDayType" id="dayType2" value="1" onclick="selectClassDays(this.value);" tabindex="14">&nbsp;&nbsp;--%>
                                            <%--<s:text name="label.oddDays"/>--%>
                                            <%--<input type="radio" name="classDayType" id="dayType3" value="2" onclick="selectClassDays(this.value);" tabindex="15">&nbsp;&nbsp;--%>
                                            <%--<s:text name="label.everyDay"/>--%>
                                            <%--<input type="radio" name="classDayType" id="dayType4" value="3" onclick="selectClassDays(this.value);" tabindex="16">&nbsp;&nbsp;--%>
                                            <%--<s:text name="label.other"/>--%>
                                            <%--<input type="radio" name="classDayType" id="dayType1" value="0" onclick="selectClassDays(this.value);" checked="true" tabindex="17">--%>

                                        <%--</td>--%>
                                    <%--</tr>--%>
                                <%--</table>--%>
                            <%--</td>--%>
                        <%--</tr>--%>

                        <%--<tr class="bodyElementsRows">--%>
                            <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%" height="20px;">--%>
                                <%--&nbsp;--%>
                            <%--</td>--%>
                        <%--</tr>--%>

                        <%--<tr class="bodyElementsRows">--%>
                            <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" height="40px;">--%>
                                <%--<table dir="<s:text name="dir.ltr"/>" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">--%>
                                    <%--<tr class="bodyElementsRows">--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="100px;"  height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<s:text name="label.day"/>--%>
                                        <%--</td>--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="70px;" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<s:text name="dailyClass.startTime"/>--%>
                                        <%--</td>--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="70px;" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<s:text name="dailyClass.endTime"/>--%>
                                        <%--</td>--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="60px;" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<s:text name="dailyClass.sessionCount"/>--%>
                                        <%--</td>--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="100px;" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<s:text name="dailyClass.sessionType"/>--%>
                                        <%--</td>--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="80px;" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<s:text name="dailyClass.sessionTeacherId"/>--%>
                                        <%--</td>--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="70px;" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--&nbsp;--%>
                                        <%--</td>--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" height="40px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<s:text name="label.teacherName"/>--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                <%--</table>--%>
                            <%--</td>--%>
                        <%--</tr>--%>

                        <%--<tr class="bodyElementsRows">--%>
                            <%--<td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">--%>
                                <%--<table dir="<s:text name="dir.ltr" />" border="0" cellpadding="0" cellspacing="0" width="100%" >--%>
                                    <%--<tr class="bodyElementsRows">--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="100px;" height="10px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<sj:select--%>
                                                       <%--id="localDayNo"--%>
                                                       <%--name="localDayNo"--%>
                                                       <%--href="classDaysListAction.action"--%>
                                                       <%--list="classDaysList"--%>
                                                       <%--listKey="key"--%>
                                                       <%--listValue="value"--%>
                                                       <%--dataType="json"--%>
                                                       <%--cssClass="commonElement"--%>
                                                       <%--cssStyle="width: 88px"--%>
                                                       <%--headerKey="-1"--%>
                                                       <%--headerValue="%{getText('label.notDefined')}"--%>
                                                       <%--emptyOption="false"--%>
                                                       <%--tabindex="20"--%>
                                                    <%-->--%>
                                            <%--</sj:select>--%>
                                        <%--</td>--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="70px;"  height="10px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<sj:datepicker id="localClassStartTime" name="localClassStartTime" maxLength="5" required="true" validate="true" theme="simple" timepicker="true" timepickerOnly="true" cssStyle="width: 40px" tabindex="21"/>--%>
                                        <%--</td>--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="70px;"  height="10px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<sj:datepicker id="localClassEndTime" name="localClassEndTime" maxLength="5" required="true" validate="true" theme="simple" timepicker="true" timepickerOnly="true" cssStyle="width: 40px" tabindex="22"/>--%>
                                        <%--</td>--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="60px;"  height="10px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<s:textfield id="localSessionCount" name="localSessionCount" maxLength="20" theme="simple" cssStyle="width:45px" onkeypress="return numericOnKeyPress(event);" tabindex="23"/>--%>
                                        <%--</td>--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="100px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<sj:select--%>
                                                       <%--id="localSessionType"--%>
                                                       <%--name="localSessionType"--%>
                                                       <%--href="sessionTypesListAction.action"--%>
                                                       <%--list="sessionTypesList"--%>
                                                       <%--listKey="key"--%>
                                                       <%--listValue="value"--%>
                                                       <%--dataType="json"--%>
                                                       <%--cssClass="commonElement"--%>
                                                       <%--theme="simple"--%>
                                                       <%--cssStyle="width: 88px"--%>
                                                       <%--headerKey="-1"--%>
                                                       <%--headerValue="%{getText('label.notDefined')}"--%>
                                                       <%--emptyOption="false"--%>
                                                       <%--tabindex="24"--%>
                                                    <%-->--%>
                                            <%--</sj:select>--%>
                                        <%--</td>--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="80px" height="10px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<s:textfield id="localTeacherId" name="localTeacherId" maxLength="6" theme="simple" cssStyle="width:70px" onkeypress="return numericOnKeyPress(event);" tabindex="25"/>--%>
                                        <%--</td>--%>
                                        <%--<td align="center" dir="<s:text name="dir.ltr" />" width="70px" height="10px;" style="border: solid;border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<s:url id="dailyClassTeacherNameJsonAction" namespace="/" action="dailyClassTeacherNameJsonAction"/>--%>
                                            <%--<sj:a--%>
                                                    <%--id="fetchTchNameBtn"--%>
                                                    <%--name="fetchTchNameBtn"--%>
                                                    <%--button="true"--%>
                                                    <%--value="%{getText('button.fetch')}"--%>
                                                    <%--href="%{dailyClassTeacherNameJsonAction}"--%>
                                                    <%--formIds="dailyClassDefinitionInputForm"--%>
                                                    <%--effect="pulsate"--%>
                                                    <%--effectDuration="1500"--%>
                                                    <%--effectOptions="{mode:'show' , times:3}"--%>
                                                    <%--targets="teacherNameSpan"--%>
                                                    <%--cssStyle="width: 60px;"--%>
                                                    <%--buttonIcon="ui-icon-gear"--%>
                                                    <%--indicator="indicatorFetch"--%>
                                                    <%--onBeforeTopics="validateTeacherId"--%>
                                                    <%--onErrorTopics="handleError"--%>
                                                    <%--tabindex="26">--%>
                                                <%--<s:text name="button.fetch"/>--%>
                                            <%--</sj:a>--%>
                                        <%--</td>--%>
                                        <%--<td id="teacherNameTD" align="center" dir="<s:text name="dir.ltr" />" height="40px;" style="border: solid; border-width: 1px; border-color: #bbbbbb">--%>
                                            <%--<img id="indicatorFetch" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>--%>
                                            <%--<span id="teacherNameSpan" name="teacherNameSpan">--%>
                                                <%--&nbsp;--%>
                                            <%--</span>--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                <%--</table>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                        <%--<tr class="bodyElementsRows">--%>
                            <%--<td>--%>
                                <%--<table dir="<s:text name="dir.ltr" />" width="100%">--%>
                                    <%--<tr class="bodyElementsRows">--%>
                                        <%--<td dir="<s:text name="dir.ltr" />" align="center">--%>
                                            <%--<input type="button" value="<s:text name="label.add"/>" style="width: 75px;" class="buttonForward" onclick="return addClassDaysToGrid();"  tabindex="27" >--%>
                                            <%--<input type="button" value="<s:text name="label.clearGrid"/>" style="width: 75px;" class="buttonForward" onclick="return clearClassDaysGridData();"  tabindex="28" >--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                <%--</table>--%>
                            <%--</td>--%>
                        <%--</tr>--%>

                        <%--<tr class="bodyElementsRows">--%>
                            <%--<td align="center" dir="<s:text name="dir.ltr" />" >--%>
                                <%--<s:url id="classDaysListUrl" namespace="/" action="classDaysListJsonAction"/>--%>
                                <%--<sjg:grid--%>
                                            <%--id="classDaysGrid"--%>
                                            <%--caption="%{getText('dailyClass.classDayInfo')}"--%>
                                            <%--dataType="local"--%>
                                            <%--formIds="dailyClassDefinitionInputForm"--%>
                                            <%--gridModel="gridModel"--%>
                                            <%--pager="true"--%>
                                            <%--direction="%{getText('dir.ltr')}"--%>
                                            <%--rowList="20,30,50"--%>
                                            <%--rowNum="0"--%>
                                            <%--rownumbers="true"--%>
                                            <%--draggable="false"--%>
                                            <%--resizable="false"--%>
                                            <%--resizableAnimate="false"--%>
                                            <%--resizableGhost="false"--%>
                                            <%--resizableHandles="false"--%>
                                            <%--scroll="true"--%>
                                            <%--scrollrows="true"--%>
                                            <%--pagerButtons="true"--%>
                                            <%--width="750"--%>
                                            <%--shrinkToFit="false"--%>
                                            <%--autowidth="false"--%>
                                            <%--height="150"--%>
                                            <%--multiselect="false"--%>
                                            <%--onSelectRowTopics="rowselect"--%>
                                            <%--navigator="true"--%>
                                            <%--navigatorSearch="false"--%>
                                            <%--navigatorEdit="false"--%>
                                            <%--navigatorDelete="true"--%>
                                            <%--navigatorDeleteOptions="{height:280,reloadAfterSubmit:false}"--%>
                                            <%--navigatorAdd="false"--%>
                                            <%--viewrecords="false"--%>
                                            <%--navigatorView="false"--%>
                                            <%--navigatorRefresh="false"--%>
                                            <%--navigatorExtraButtons="{ separator: {title :'separator'},--%>
                                                                     <%--hide     : {caption : '%{getText('button.delete')}',--%>
                                                                                 <%--icon: 'ui-icon-trash',--%>
                                                                                 <%--title: 'Delete selected row',--%>
                                                                                 <%--onclick: function(){ deleteSelectedRow();}--%>
                                                                                 <%--}--%>
                                                               <%--}"--%>

                                        <%-->--%>
                                    <%--<sjg:gridColumn name="classDayNo"        index="classDayNo"      title="%{getText('dailyClass.classDayName')}"       hidden="true"  width="20"  resizable="false" sortable="true"  search="false" />--%>
                                    <%--<sjg:gridColumn name="classDayName"      index="classDayName"    title="%{getText('label.day')}"                     hidden="false" width="100" resizable="false" sortable="true"  search="false" />--%>
                                    <%--<sjg:gridColumn name="classStartTime"    index="classStartTime"  title="%{getText('dailyClass.startTime')}"          hidden="false" width="70"  resizable="false" sortable="true"  search="false" />--%>
                                    <%--<sjg:gridColumn name="classEndTime"      index="classEndTime"    title="%{getText('dailyClass.endTime')}"            hidden="false" width="70"  resizable="false" sortable="false" search="false" />--%>
                                    <%--<sjg:gridColumn name="sessionCount"      index="sessionCount"    title="%{getText('dailyClass.sessionCount')}"       hidden="false" width="80"  resizable="false" sortable="false" search="false" />--%>
                                    <%--<sjg:gridColumn name="sessionTypeId"     index="sessionTypeId"   title="%{getText('dailyClass.sessionType')}"        hidden="true"  width="100" resizable="false" sortable="false" search="false" />--%>
                                    <%--<sjg:gridColumn name="sessionTypeName"   index="sessionTypeName" title="%{getText('dailyClass.sessionType')}"        hidden="false" width="100" resizable="false" sortable="false" search="false" />--%>
                                    <%--<sjg:gridColumn name="teacherId"         index="teacherId"       title="%{getText('dailyClass.sessionTeacherId')}"   hidden="false" width="100" resizable="false" sortable="false" search="false" />--%>
                                    <%--<sjg:gridColumn name="teacherName"       index="teacherName"     title="%{getText('dailyClass.sessionTeacherName')}" hidden="false" width="100" resizable="false" sortable="false" search="false" />--%>
                                <%--</sjg:grid>--%>

                            <%--</td>--%>
                        <%--</tr>--%>

                    <%--</table>--%>
                <%--</s:div>--%>

            </sj:tabbedpanel>

        </td>
    </tr>


</table>

</s:div>
</s:form>

<img id="indicator" src="/images/indicator.gif" alt="Loading..." style="display:none"/>

<script type="text/javascript" language="javascript">

$(document).ready(function() {
    //======================================================================================================================
    Calendar.setup({
        inputField  : "shiftDateStr",             // id of the input field
        button      : "shiftDateImg",       // trigger for the calendar (button ID)
        ifFormat    : "%Y/%m/%d",            // format of the input field
        dateType	: 'jalali'
    });
    $("#shiftDateStr").mask("1399/99/99");

    $("#shiftStartTimeStr").mask("99:99");
    $("#shiftEndTimeStr").mask("99:99");
    //======================================================================================================================
    $.subscribe('validateForFetch', function(event,data) {

        removeClientErrors();
        getElement("staffNameSpan").innerHTML = "";

        var shiftDateValue = getElement("shiftDateStr").value;
        var staffIdValue   = getElement("staffId").value;

        if(isNullOrEmpty(staffIdValue) && isNullOrEmpty(shiftDateValue)){
            event.originalEvent.options.submit = false;
            getElement("indicatorFetch").style.display = 'none';
            addClientError('<s:text name="error.shift.dataRequired" />');
            getElement("staffId").focus();
            return false;
        }
        if(!isNullOrEmpty(staffIdValue) && !isNumeric(staffIdValue)){
            event.originalEvent.options.submit = false;
            getElement("indicatorFetch").style.display = 'none';
            addClientError('<s:text name="error.common.invalidStaffId" />');
            getElement("staffId").focus();
            return false;
        }
        if(!isNullOrEmpty(shiftDateValue) && !isValidDate(shiftDateValue)){
            event.originalEvent.options.submit = false;
            getElement("indicatorFetch").style.display = 'none';
            addClientError('<s:text name="error.common.invalidDate" />');
            getElement("shiftDateStr").focus();
            return false;
        }
        return true;
    });
    //======================================================================================================================
    $.subscribe('validateForSave', function(event,data) {

        removeClientErrors();
        alert("ok1");
        var shiftDateValue = getElement("shiftDateStr").value;
        var staffIdValue   = getElement("staffId").value;

        if(isNullOrEmpty(staffIdValue) && isNullOrEmpty(shiftDateValue)){
            event.originalEvent.options.submit = false;
            getElement("indicatorFetch").style.display = 'none';
            addClientError('<s:text name="error.shift.dataRequired" />');
            getElement("staffId").focus();
            return false;
        }
        if(!isNullOrEmpty(staffIdValue) && !isNumeric(staffIdValue)){
            event.originalEvent.options.submit = false;
            getElement("indicatorFetch").style.display = 'none';
            addClientError('<s:text name="error.common.invalidStaffId" />');
            getElement("staffId").focus();
            return false;
        }
        if(!isNullOrEmpty(shiftDateValue) && !isValidDate(shiftDateValue)){
            event.originalEvent.options.submit = false;
            getElement("indicatorFetch").style.display = 'none';
            addClientError('<s:text name="error.common.invalidDate" />');
            getElement("shiftDateStr").focus();
            return false;
        }

        if(isNullOrEmpty(getElement("shiftStartTimeStr").value)){
            event.originalEvent.options.submit = false;
            getElement("indicatorFetch").style.display = 'none';
            addClientError('<s:text name="error.common.startTimeRequired" />');
            getElement("shiftStartTimeStr").focus();
            return false;
        }

        if(isNullOrEmpty(getElement("shiftEndTimeStr").value)){
            event.originalEvent.options.submit = false;
            getElement("indicatorFetch").style.display = 'none';
            addClientError('<s:text name="error.common.endTimeRequired" />');
            getElement("shiftEndTimeStr").focus();
            return false;
        }
        alert("ok2");
        return true;
    });
    //======================================================================================================================
    $.subscribe('handleError', function(event,data) {
        removeClientErrors();
        addClientError(event.originalEvent.request.statusText);
    });

    //======================================================================================================================
    $.subscribe('handleServerError', function(event,data) {

        alert("Error");
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
    function setTimeFields(shiftState){

        if(shiftState == 'day') {
            getElement("shiftStartTimeStr").value = '07:00';
            getElement("shiftEndTimeStr").value = '19:00';
        }
        else if(shiftState == 'night') {
            getElement("shiftStartTimeStr").value = '19:00';
            getElement("shiftEndTimeStr").value = '07:00';
        }
        else if(shiftState == '24') {
            getElement("shiftStartTimeStr").value = '00:00';
            getElement("shiftEndTimeStr").value = '24:00';
        }

    }
    //======================================================================================================================
    function validateForm(){

        removeClientErrors();

        var shiftDateValue = getElement("shiftDateStr").value;
        var staffIdValue   = getElement("staffId").value;
        alert(shiftDateValue);

        if(isNullOrEmpty(shiftDateValue)){
            getElement("indicatorFetch").style.display = 'none';
            addClientError('<s:text name="error.common.dateRequired" />');
            getElement("shiftDateStr").focus();
            return false;
        }

        if(!isValidDate(shiftDateValue)){
            event.originalEvent.options.submit = false;
            getElement("indicatorFetch").style.display = 'none';
            addClientError('<s:text name="error.common.invalidDate" />');
            getElement("shiftDateStr").focus();
            return false;
        }

        if(isNullOrEmpty(staffIdValue)){
            getElement("indicatorFetch").style.display = 'none';
            addClientError('<s:text name="error.common.invalidStaffId" />');
            getElement("staffId").focus();
            return false;
        }

        if(!isNumeric(staffIdValue)){
            getElement("indicatorFetch").style.display = 'none';
            addClientError('<s:text name="error.common.invalidStaffId" />');
            getElement("staffId").focus();
            return false;
        }

        if(isNullOrEmpty(getElement("shiftStartTimeStr").value)){
            addClientError('<s:text name="error.common.startTimeRequired" />');
            getElement("shiftStartTimeStr").focus();
            return false;
        }

        if(isNullOrEmpty(getElement("shiftEndTimeStr").value)){
            addClientError('<s:text name="error.common.endTimeRequired" />');
            getElement("shiftEndTimeStr").focus();
            return false;
        }

        return true;
    };
    //======================================================================================================================

</script>
