<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" style="text-align:center;color:#182E7A;" >

    <span align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" style="font-size:10;">
        <s:text name="webApp.contact" />&nbsp;&nbsp;&nbsp;&nbsp;
    </span>

    <span align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" style="font-size:8;">
        <s:text name="webApp.email" />&nbsp;<s:text name="webApp.emailText" />
    </span>

    <span align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" style="font-size:8;">
        &nbsp;&nbsp;<s:text name="webApp.tel" />&nbsp;
    </span>

    <span align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" style="font-size:8;">
        <s:text name="webApp.phone"/>
    </span>

    <p>
       &nbsp;
    </p>

    <p>
        <span align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" style="font-size:10;">
            <s:text name="webApp.address" />&nbsp;
        </span>
        <span align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" style="font-size:8;">
            <s:text name="webApp.addressText" />
        </span>
    </p>

</div>
