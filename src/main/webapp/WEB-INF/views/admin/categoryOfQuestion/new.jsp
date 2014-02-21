<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="button" tagdir="/WEB-INF/tags/button"%>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/categoryOfQuestion.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="categoryOfQuestion.new.title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="categoryOfQuestion.new.header"/></h1>
        </div>
        <form:form method="POST" commandName="categoryOfQuestion" role="form">
            <form:errors path="*" cssClass="info-danger" element="div" />

            <myForm:input object="categoryOfQuestion" fieldName="title" required="true"/>
            <button:submit/>
            <button:cancel/>
        </form:form>
        <script src="${activeJsUrl}"></script>
    </body>
</html>
