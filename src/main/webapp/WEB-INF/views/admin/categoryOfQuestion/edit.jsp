<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>

<c:url var="activeJsUrl" value="/resources/js/activeLink/categoryOfQuestion.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="categoryOfQuestion.edit.title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="categoryOfQuestion.edit.header"/></h1>
        </div>
        <form:form method="POST" commandName="categoryOfQuestion" role="form">
            <form:errors path="*" cssClass="info-danger" element="div" />

            <form:hidden path="id"/>
            <myForm:input object="categoryOfQuestion" fieldName="title" required="true"/>
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
