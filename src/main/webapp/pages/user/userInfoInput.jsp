<%@ page import="com.soshiant.server.util.ServerConstants" %>
<%@ page import="com.behsazan.common.dto.personalize.CustomerInfoDTO" %>
<%@ page import="com.behsazan.ebanking.server.web.action.personalize.CustomerInfoAction" %>
<%@ page contentType="text/html" %>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html-el.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld" %>
<%@ taglib prefix="ebanking" uri="/WEB-INF/tld/ebanking-html.tld" %>

<%
    CustomerInfoDTO customerDTO = request.getAttribute(ServerConstants.OBJECT_CUSTOMER_INFO) == null ? new CustomerInfoDTO() : (CustomerInfoDTO) request.getAttribute(ServerConstants.OBJECT_CUSTOMER_INFO);
%>


<html>
<script type="text/javascript" language="JavaScript">
    function validateFormUserInfo() {

        if (isEmpty(customerInfoForm.postCode.value)) {
            alert('<bean:message key="error.customerInfo.postCode" />');
            customerInfoForm.postCode.focus();
            return false;
        }
        if (isEmpty(customerInfoForm.job.value)) {
            alert('<bean:message key="error.customerInfo.job" />');
            customerInfoForm.job.focus();
            return false;
        }
    <%--if (isEmpty(customerInfoForm.fax.value)) {--%>
    <%--alert('<bean:message key="error.customerInfo.fax" />');--%>
    <%--customerInfoForm.fax.focus();--%>
    <%--return false;--%>
    <%--}--%>
        if (isEmpty(customerInfoForm.telephone.value)) {
            alert('<bean:message key="error.customerInfo.telephone" />');
            customerInfoForm.telephone.focus();
            return false;
        }
        if (isEmpty(customerInfoForm.address.value)) {
            alert('<bean:message key="error.customerInfo.address" />');
            customerInfoForm.address.focus();
            return false;
        }
    <%--if (isEmpty(customerInfoForm.economicCode.value)) {--%>
    <%--alert('<bean:message key="error.customerInfo.economicCode" />');--%>
    <%--customerInfoForm.economicCode.focus();--%>
    <%--return false;--%>
    <%--}--%>
        if (!isNumeric(customerInfoForm.postCode.value)) {
            alert("<bean:message key="error.customerInfo.isNumericPostCode"/>");
            customerInfoForm.postCode.focus();
            return false;
        }
        if (!isNumeric(customerInfoForm.telephone.value)) {
            alert("<bean:message key="error.customerInfo.isNumericTelephone"/>");
            customerInfoForm.telephone.focus();
            return false;
        }
        if (!isEmpty(customerInfoForm.fax.value)) {
            if (!isNumeric(customerInfoForm.fax.value)) {
                alert("<bean:message key="error.customerInfo.isNumericFax"/>");
                customerInfoForm.fax.focus();
                return false;
            }
        }
        if (!isEmpty(customerInfoForm.mobileNo.value)) {
            if ((!isNumeric(customerInfoForm.mobileNo.value)) || (customerInfoForm.mobileNo.value.length != 11) || ((customerInfoForm.mobileNo.value.substring(0,2)) != 09)){
                alert("<bean:message key="error.customerInfo.wrongMobileNo"/>");
                customerInfoForm.mobileNo.focus();
                return false;
            }
        }
        if (!isEmpty(customerInfoForm.economicCode.value)) {
            if (!isNumeric(customerInfoForm.economicCode.value)) {
                alert("<bean:message key="error.customerInfo.isNumericEconomicCode"/>");
                customerInfoForm.economicCode.focus();
                return false;
            }
        }
        return true;
    }
</script>
<jsp:include page="/jsp/include/header-tag.jsp"/>
<body  dir="<bean:message key="common.dir.rtl"/>" onload="enableFormSubmit(document.customerInfoForm);">
<jsp:include page="/jsp/include/header.jsp"/>

<center>
<div align="center" class="pageWidth">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
<!-- left column -->
<td class="rightColumn">
<table border="0" cellpadding="0" cellspacing="0" width="100%">

<tr>
    <td align="center">&nbsp;</td>
</tr>

<tr>
    <td align="center">&nbsp;</td>
</tr>

<tr>
    <!-- page title begin -->
    <td align="center">
        <table border="0" cellPadding=0 cellSpacing=0 width="100%">
            <tr>
                <td colspan="2" align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.rtl"/>" class="roadmap">
                    <a href="javascript:changeTab(2);" onblur="window.status='';return true"
                       onmouseover="window.status='return';return true" onfocus="window.status='';return true"
                       onmouseout="window.status='';return true"><bean:message key="yourAccounts.title"/></a> &gt;
                    <bean:message key="customerInfo.title"/></td>
            </tr>

            <tr>
                <td colspan="2">&nbsp;</td>
            </tr>
        </table>
    </td>
    <!-- page title end -->
</tr>

<tr>
    <td align="center">&nbsp;</td>
</tr>
<!--***-->
<tr>
    <td align="<bean:message key="common.align.right"/>">

        <table border="0" cellpadding="0" cellspacing="1" width="100%">
            <html:form method="post" action="/customerInfo" onsubmit="return disableFormSubmit(this);">
            <tr>
                <td colspan="2">
                    <html:messages id="customerInfoMessagekey" property="<%= CustomerInfoAction.messageKey%>"/>
                    <html:errors property="<%=CustomerInfoAction.messageKey %>"/>
                </td>
            </tr>

        </table>
    </td>
</tr>
<!--***-->
<tr>

<td align="<bean:message key="common.align.right"/>">

<table border="0" width="100%" cellpadding="1" cellspacing="1">
<!-- real customer begin-->
<tr class="blueHeader">
    <td colspan="4" align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.rtl"/>">&nbsp;<bean:message key="customerInfo.personalInfo"/>&nbsp;</td>
</tr>

<tr class="sectionBody">
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" nowrap class="text-body"><%=customerDTO.getLastName()%>&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.real.family"/></td>
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" nowrap class="text-body"><%=customerDTO.getFirstName()%>&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.name"/></td>
</tr>

<tr class="sectionBody">

    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" nowrap class="text-body">
        &nbsp;
        <html:select property="sex" tabindex="1">
            <%if (customerDTO.getSex().equals("0")) { %>
            <option value="0" selected><bean:message key="label.haj.gender.0"/></option>
            <option value="1"><bean:message key="label.haj.gender.1"/></option>
            <%} else {%>
            <option value="0"><bean:message key="label.haj.gender.0"/></option>
            <option value="1" selected><bean:message key="label.haj.gender.1"/></option>
            <%}%>

        </html:select>&nbsp; &nbsp;
    </td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<span class="asterisk">*</span>&nbsp;<bean:message
            key="customerInfo.real.sex"/></td>
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" nowrap class="text-body"><%=customerDTO.getFather()%>&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.real.father"/></td>
</tr>

<tr class="sectionBody">
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" nowrap class="text-body"><ebanking:format value="<%=customerDTO.getIdNo()%>" pattern="number"/>&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.real.idNo"/></td>
    <td align="<bean:message key="common.align.right"/>" dir="ltr" nowrap class="text-body">&nbsp;<ebanking:format value="<%=customerDTO.getBirthDate()%>"
                                                                              pattern="date"/>&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.real.birthDate"/></td>
</tr>

<tr class="sectionBody">
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" nowrap class="text-body"><ebanking:format value="<%=customerDTO.getIssueRgnCode()%>"
                                                                pattern="number"/>&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.real.issueRgnCode"/></td>
    <td align="<bean:message key="common.align.right"/>" dir="ltr" nowrap class="text-body">&nbsp;<ebanking:format value="<%=customerDTO.getIssueDate()%>"
                                                                              pattern="date"/>&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.real.issueDate"/></td>
</tr>

<tr class="sectionBody">
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" nowrap class="text-body"><ebanking:format value="<%=customerDTO.getIdSerial()%>"
                                                                pattern="number"/>&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.real.idSerial"/></td>
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" nowrap class="text-body"><ebanking:format value="<%=customerDTO.getIdSeri()%>" pattern="number"/>&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.real.idSeri"/></td>
</tr>

<tr class="sectionBody">
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>">
        <jsp:include page="/jsp/include/form-params.jsp"/>
        <html:text property="job" size="30" maxlength="30"
                   onkeypress="return farsiOnkeypress(event);" styleClass="textinput" tabindex="3"/>
        &nbsp;
    </td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<span class="asterisk">*</span>&nbsp;<bean:message
            key="customerInfo.real.job"/></td>
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" nowrap class="text-body">
        <html:select property="education" tabindex="2">
            <%
                String educationDesc = "label.haj.education.";
                for (int i = 1; i < 10; i++) {
                    if (customerDTO.getEducation().equals(Integer.toString(i))) { %>
            <option value="<%=i%>" selected><bean:message key="<%= educationDesc+Integer.toString(i)%>"/></option>
            <--uneducated -- >
            <% } else { %>
            <option value="<%=i%>"><bean:message key="<%= educationDesc+Integer.toString(i)%>"/></option>
            <--uneducated -- >
            <% }
            }
            %>
        </html:select>
        &nbsp;
    </td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<span class="asterisk">*</span>&nbsp;<bean:message
            key="customerInfo.real.education"/></td>
</tr>

<tr class="sectionBody">
        <%--<td align="<bean:message key="common.align.right"/>" nowrap class="text-body"><ebanking:format value="<%=customerDTO.getPostCode()%>" pattern="number"/>&nbsp;</td>--%>
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" onkeypress="return numbericOnKeypress(event);">
        <jsp:include page="/jsp/include/form-params.jsp"/>
        <html:text property="postCode" maxlength="10" tabindex="6"/>
        &nbsp;
    </td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<span class="asterisk">*</span>&nbsp;<bean:message
            key="customerInfo.postalCode"/></td>
        <%--<td align="<bean:message key="common.align.right"/>" nowrap class="text-body"><ebanking:format value="<%=customerDTO.getTell()%>" pattern="number"/>&nbsp;</td>--%>
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>">
        <jsp:include page="/jsp/include/form-params.jsp"/>
        <html:text property="telephone" maxlength="15" onkeypress="return numbericOnKeypress(event);" tabindex="4"/>
        &nbsp;
    </td>

    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<span class="asterisk">*</span>&nbsp;<bean:message
            key="customerInfo.tell"/></td>
</tr>

<tr class="sectionBody">
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>">
        <jsp:include page="/jsp/include/form-params.jsp"/>
        <html:text property="mobileNo" maxlength="11" onkeypress="return numbericOnKeypress(event);" tabindex="7" value="<%=customerDTO.getMobileNo()%>"/>
        &nbsp;
    </td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.mobileNo"/></td>
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>">
        <jsp:include page="/jsp/include/form-params.jsp"/>
        <html:text property="fax" maxlength="15" onkeypress="return numbericOnKeypress(event);" tabindex="7"/>
        &nbsp;
    </td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.fax"/></td>
</tr>

<tr class="sectionBody">
        <%--<td colspan="3" nowrap align="<bean:message key="common.align.right"/>" class="text-body"><%=customerDTO.getAddress()%>&nbsp;</td>--%>
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" colspan="3">
        <jsp:include page="/jsp/include/form-params.jsp"/>
        <html:text property="address" size="100" maxlength="100" onkeypress="return farsiOnkeypress(event);"
                   styleClass="textinput" tabindex="8"/>
        &nbsp;
    </td>

    <td nowrap align="<bean:message key="common.align.left"/>" class="text-label">&nbsp;<span class="asterisk">*</span>&nbsp;<bean:message
            key="customerInfo.address"/></td>
</tr>
<!-- real customer end-->

<!-- bank info begin -->
<tr class="blueHeader">
    <td colspan="4" align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.rtl"/>">&nbsp;<bean:message key="customerInfo.bankInfo"/>&nbsp;</td>
</tr>

<tr class="sectionBody">
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" nowrap class="text-body"><%=customerDTO.getCustomerType()%>&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.customerType"/></td>
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" nowrap class="text-body"><ebanking:format value="<%=customerDTO.getExtCustId()%>"
                                                                pattern="number"/>&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.extCustId"/></td>
</tr>

<tr class="sectionBody">
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" nowrap class="text-body"><ebanking:format value="<%=customerDTO.getNationalCode()%>"
                                                                pattern="number"/>&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.real.nationalCode"/>&nbsp;</td>
        <%--<td align="<bean:message key="common.align.right"/>" nowrap class="text-body"><ebanking:format value="<%=customerDTO.getEconomicCode()%>" pattern="number"/>&nbsp;</td>--%>
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>">
        <jsp:include page="/jsp/include/form-params.jsp"/>
        <html:text property="economicCode" maxlength="15" onkeypress="return numbericOnKeypress(event);" tabindex="9"/>
        &nbsp;
    </td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.economicCode"/>&nbsp;</td>
</tr>

<tr class="sectionBody">
    <td align="<bean:message key="common.align.right"/>" nowrap class="text-body">&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;</td>
    <td align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>" nowrap class="text-body"><%=customerDTO.getHaveMultiAccount()%>&nbsp;</td>
    <td align="<bean:message key="common.align.left"/>" nowrap class="text-label">&nbsp;<bean:message key="customerInfo.real.multiAccount"/></td>
</tr>
<!-- bank info end -->

</table>
<!-- divider -->


</td>

</tr>
<!-- divider -->
<tr>
    <td colspan="2" align="center" class="divider">&nbsp;</td>
</tr>
<!-- buttons-->
<!-- buttons-->
<tr class="tableButtonRow">
    <td colspan="2">
        <table border="0" align="center">
            <tr>
                <td>&nbsp;</td>
                <jsp:include page="/jsp/include/form-params.jsp"/>
                <input type="hidden" name="method" value="updateCustomerInfo"/>

                <td><html:button property="cancel" style="WIDTH:75px" tabindex="11" styleClass="buttonBack"
                                 onclick="changeTab(2);disableFormSubmit(document.customerInfoForm); "><bean:message
                        key="fundsTransfer.button.cancel"/>
                </html:button></td>
                <td>&nbsp;</td>
                <td><html:submit onclick="return validateFormUserInfo(this.form);" property="submit1" style="WIDTH:75px"
                                 tabindex="10"
                                 styleClass="buttonFwd"><bean:message
                        key="fundsTransfer.button.ok"/></html:submit></td>
                <td>&nbsp;</td>
            </tr>
        </table>
    </td>
    </html:form>
</tr>

<tr>
    <td colspan="2" align="<bean:message key="common.align.right"/>" dir="<bean:message key="common.dir.ltr"/>">
        <span class="asterisk">*</span>&nbsp; <bean:message key="common.denotesMandatoryFields"/>
    </td>
</tr>
</table>
</td>


<!-- right column -->
<td class="leftColumn">
    <table border="0" width="100%">
        <tr>
            <td width="100%" align="<bean:message key="common.align.right"/>">

                <jsp:include page="/jsp/personalize/personalizeUserInfo.jsp"/>
            </td>
        </tr>
    </table>
</td>

</tr>
</table>
</div>
</center>
<jsp:include page="/jsp/include/footer.jsp"/>
</body>

</html>
