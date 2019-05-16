<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>


<s:form namespace="/" id="loginForm" name="loginForm" action="/j_spring_security_check" theme="simple" method="post">

<s:div cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" cssStyle="height: 450px">

    <table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
        <tr class="bodyElementsRows">
            <td align="center" dir="<s:text name="dir.ltr" />" width="100px" height="350px">
                &nbsp;
            </td>
            <td id="middleColumn" align="center" dir="<s:text name="dir.ltr" />" width="400px" height="350px">

                <div style="height:50px;">

                </div>

                <s:div id="loginDiv" name="loginDiv"  cssClass="type-text type-select ui-widget ui-widget-content ui-state-default ui-corner-all" cssStyle="height: 300px;">

                    <table border="0" cellpadding="0" cellspacing="0"  align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                        <tr  class="bodyElementsRows">
                            <td align="center" dir="<s:text name="dir.ltr" />">
                                <s:div cssClass="actionErrorClass" cssStyle="overflow:hidden;height: 50px">
                                    ${SPRING_SECURITY_LAST_EXCEPTION.message}
                                    <s:actionerror id="serverErrorList" cssClass="actionErrorClass"/>
                                    <ul id="clientErrorList" class="actionErrorClass"/>
                                </s:div>
                            </td>
                        </tr>
                        <tr  class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                &nbsp;
                            </td>
                        </tr>
                        <tr  class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                <table>
                                    <tr>
                                        <td width="80px" align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />">
                                            &nbsp;<s:text name="login.userName"/>
                                        </td>
                                        <td width="160px">
                                            &nbsp;<s:textfield id="j_username" name="j_username" theme="simple" cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" title="Username" labelposition="top" maxlength="30" cssStyle="width: 150px;height: 20px; direction: ltr" tabindex="1"/>
                                        </td>
                                        <td width="30px">
                                            &nbsp;
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr  class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                <table>
                                    <tr>
                                        <td width="80px" align="<s:text name="align.right" />" dir="<s:text name="dir.ltr" />">
                                            &nbsp;<s:text name="login.password"/>
                                        </td>
                                        <td width="160px">
                                            &nbsp;<s:password id="j_password" name="j_password" theme="simple" cssClass="commonElement" title="password" labelposition="top" maxlength="30" cssStyle="width: 150px;height: 20px;direction: ltr" tabindex="2"/>
                                        </td>
                                        <td width="30px">
                                            &nbsp;<img id="passwordImg" src="<s:text name="webApp.keyboardImage" />" title="" style="vertical-align: middle;">
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr  class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                <table>
                                    <tr>
                                        <td width="80px">
                                            &nbsp;
                                        </td>
                                        <td width="160px" align="center" dir="<s:text name="dir.ltr" />">
                                            <img src="jcaptcha.jpg" style="width: 150px;height: 50px;"/>
                                        </td>
                                        <td width="30px">
                                            &nbsp;
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr  class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />">
                                <table>
                                    <tr>
                                        <td width="80px">
                                            &nbsp;
                                        </td>
                                        <td width="160px" align="center" dir="<s:text name="dir.ltr" />">
                                            <s:textfield id="jcaptchaResponse" name="jcaptchaResponse" theme="simple" cssClass="type-text type-select ui-widget ui-widget-content ui-corner-all yform" title="Username" labelposition="top" maxlength="30" cssStyle="width: 150px;height: 20px;direction: ltr" tabindex="4"/>
                                        </td>
                                        <td width="30px">
                                            &nbsp;
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr  class="bodyElementsRows">
                            <td align="<s:text name="align.left" />" dir="<s:text name="dir.ltr" />" >
                                &nbsp;
                            </td>
                        </tr>
                        <tr class="bodyElementsRows">
                            <td align="center" dir="<s:text name="dir.ltr" />" >
                                <s:submit id="loginButton" key="login.login" cssStyle="width: 75px;" cssClass="buttonForward" onclick="return validateLoginForm();" tabindex="5"/>
                            </td>
                        </tr>

                    </table>

                </s:div>

            </td>
            <td id="rightColumn" align="center" dir="<s:text name="dir.ltr" />" height="400px">
                &nbsp;
            </td>
        </tr>
    </table>

</s:div>

</s:form>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/virtualKeyboard/jquery.keyboard.js"></script>
<script type="text/javascript" language="javascript">
$(document).ready(function() {
//======================================================================================================================
    $('#j_password').keyboard({
                                openOn : false,
                                stayOpen : true,
                                layout : 'qwerty',
                                autoAccept: true,
                                usePreview:false,
                                preventPaste: true,
                                position: {
                                            of: "#loginDiv",
                                            my: "center bottom",
                                            at: "center bottom",
                                            offset: '0 20'
                                        }
                              });

    $('#passwordImg').click(function(){
        $('#j_password').getkeyboard().reveal();
    });

//======================================================================================================================
});
    function validateLoginForm(){

        removeClientErrors();

        if(isNullOrEmpty(getElement("j_username").value)){
            addClientError('<s:text name="error.login.userNameRequired" />');
            getElement("j_username").focus();
        }

        if(isNullOrEmpty(getElement("j_password").value)){
            addClientError('<s:text name="error.login.passwordRequired" />');
            getElement("j_password").focus();
        }

        if(isNullOrEmpty(getElement("jcaptchaResponse").value)){

            addClientError('<s:text name="errors.login.captchaRequired" />');
            getElement("jcaptchaResponse").focus();
        }
        return true;
    };

</script>

