<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>


<s:form id="changePasswordFailedForm" theme="simple" cssClass="yform">
<h2 align="center"><s:text name="changePassword.title"/></h2>
<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all">
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

                        <!--  error -->
                        <tr>
                        <td class="actionErrorClass" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                <s:actionerror/>
                            </td>
                        </tr>

                    </table>
                </s:div>
            </td>
        </tr>
    </table>
</s:div>

</s:form>
