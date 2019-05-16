<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%
%>

<table class="header" align="center" dir="<s:text name="dir.ltr" />">
    <tr>
        <td width="100%" style="background: #b92025;height: 80px">
            <table width="100%" >
                <tr>
                    <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                        <img src="<s:text name="webApp.headerLogoImage" />" alt="" class="headerLayout" style="width: 100%"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td width="100%" style="color: #b92025;background: #f4bc25;height: 5px">
        </td>
    </tr>

    <tr>
        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
            <table align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%" >
                <tr>
                    <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                        &nbsp;&nbsp;
                        <a href="index.html"><s:text name="webApp.aboutUs" /></a>&nbsp;&nbsp;
                        <a href="index.html"><s:text name="webApp.contactUs" /></a>&nbsp;&nbsp;
                    </td>
                    <td align="<s:text name="align.right" />">
                        &nbsp;&nbsp;
                        <%--<a href='<s:url value="changeLanguage.action" />'><s:text name="language.otherLanguage"/></a>--%>
                        &nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </td>
    </tr>

</table>



<%--<div class="header" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" style="border:solid 1px red;">--%>

    <%--<div  class="headerImage" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">--%>
        <%--<img src="<s:text name="webApp.headerLogoImage" />" alt=""/>--%>
    <%--</div>--%>
    <%--<div class="headerLinks" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" style="border:solid 1px red;">--%>
        <%--<div class="headerMenu"  style="border:solid 1px blue;">--%>
            <%--<ul>--%>
                <%--<li class="active"><a href="index.html"><s:text name="webApp.home" /></a></li>--%>
                <%--<li><a href="support.html"><s:text name="webApp.support" /></a></li>--%>
                <%--<li><a href="about.html"><s:text name="webApp.aboutUs" /></a></li>--%>
                <%--<li><a href="contact.html"><s:text name="webApp.contactUs" /></a></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <%--<div class="headerChooseLanguage"  style="border:solid 1px #000000;">--%>
            <%--<ul>--%>
                <%--<li><a href='<s:url value="changeLanguage.action" />'><s:text name="language.otherLanguage"/></a></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
    <%--</div>--%>

<%--</div>--%>
