<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<div>
    <table class="header" align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
        <tr>
            <td width="100%" style="background: #b92025;height: 80px">
                <table width="100%" >
                    <tr>
                        <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" width="100%">
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

    </table>

</div>