<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/page/"%>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/userGroup.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="userGroup.edit.title"/></title>
    </head>
    <body>
        <page:header text="userGroup.edit.header"/>
        <form:form method="POST" commandName="userGroup" role="form">
            <form:errors path="*" cssClass="info-danger" element="div" />

            <form:hidden path="id"/>
            <myForm:input object="userGroup" fieldName="title" required="true"/>
            <button type="submit" class="btn btn-primary" name="edit" value="edit">
                <spring:message code="button_submit"/>
            </button>
            <button type="submit" class="btn btn-default" name="cancel" value="cancel">
                <spring:message code="button_cancel"/>
            </button>
        </form:form>

        <script src="${activeJsUrl}"></script>
    </body>
</html>
