<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="width"    required="false" type="java.lang.Integer" %>
<%@ attribute name="disabled" required="false" type="java.lang.Boolean"%>
<%@ attribute name="property" required="true"  %>
<%@ attribute name="value"    required="false" %>
<%@ attribute name="styleId"  required="false" %>


<%--<c:if test="${inputText eq true}">
     <input id="${property}" name="${property}" maxlength="10" class="calendar"
           style="direction:ltr; text-align: center; width:100px;">
</c:if>
<c:if test="${inputText eq null || inputText eq false}">
     <s:textfield styleClass="calendar"
             styleId="${property}"
           property="${property}"
           value="${value}"
           maxlength="10"
           disabled="${disabled}"
           style="direction:ltr; tex t-align: center; width:100px;">
</s:textfield>

 </c:if>--%>
<c:if test="${styleId eq null}">
    <c:if test="${value ne null}">
        <input type="text"
               id="${property}" name="${property}"
               value="${value}" class="calendar"
               maxlength="10"
               style="direction:ltr; text-align: center; width:${width}px;"
                />
<%--
        <s:textfield cssClass="calendar"
                   id="${property}"
                   name="${property}"
                   value="${value}"
                   maxlength="10"
                   disabled="${disabled}"
                   style="direction:ltr; text-align: center; width:%{width}px;">
        </s:textfield>
--%>
    </c:if>
    <c:if test="${value eq null}">
        <input type="text"
               id="${property}" name="${property}"
               class="calendar"
               maxlength="10"
               style="direction:ltr; text-align: center; width:${width}px;"
                />
<%--
        <s:textfield cssClass="calendar"
                   id="${property}"
                   name="${property}"
                   maxlength="10"
                   disabled="${disabled}"
                   style="direction:ltr; text-align: center; width:%{width}px;">
        </s:textfield>
--%>
    </c:if>
</c:if>
<c:if test="${styleId ne null}">
    <c:if test="${value ne null}">
        <input type="text"
               id="${property}" name="${property}"
               value="${value}" class="calendar"
               maxlength="10"
               style="direction:ltr; text-align: center; width:${width}px;"
                />
        <%--<s:textfield cssClass="calendar"
                   id="${styleId}"
                   name="${property}"
                   value="${value}"
                   maxlength="10"
                   disabled="${disabled}"
                   style="direction:ltr; text-align: center; width:%{width}px;">
        </s:textfield>--%>
    </c:if>
    <c:if test="${value eq null}">
        <input type="text"
               id="${property}" name="${property}"
               class="calendar"
               maxlength="10"
               style="direction:ltr; text-align: center; width:${width}px;"
                />
        <%--<s:textfield cssClass="calendar"
                   id="${styleId}"
                   name="${property}"
                   maxlength="10"
                   disabled="${disabled}"
                   style="direction:ltr; text-align: center; width:%{width}px;">
        </s:textfield>--%>
    </c:if>
</c:if>

<script type="text/javascript">
    jQueryMaskCalendar();
</script>
<%--

<img src="images/cal.jpg" id="${property}+'Img'" name="${property}+'Img'" alt="تقویم"/>
--%>