<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>


<s:form id="dailySessionHoldingResultForm" theme="simple">
<h2><s:text name="dailySessionHolding.title"/></h2>
<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform"  cssStyle="width: 1000px;height: 680px;vertical-align: top; resize: none;">
    <table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
        <tr>
            <td>
                <s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all">

                    <table border="0" cellpadding="0" cellspacing="0" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" style="width:100%;">

                        <tr class="bodySpaceRow">
                            <td align="center" dir="<s:text name="dir.ltr" />" >
                                &nbsp;
                            </td>
                        </tr>

                        <!--  message -->
                        <tr>
                        <td class="actionErrorClass" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                <s:actionmessage/>
                            </td>
                        </tr>

                    </table>
                </s:div>
            </td>
        </tr>
    </table>
</s:div>

</s:form>
