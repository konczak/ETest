<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="button" tagdir="/WEB-INF/tags/button"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/page/"%>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/testTemplate.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="testTemplate.new.title"/></title>
    </head>
    <body>
        <page:header text="testTemplate.new.header"/>
        <form:form method="POST" commandName="testTemplate" role="form">
            <form:errors path="*" cssClass="info-danger" element="div" />

            <myForm:input object="testTemplate" fieldName="subject" required="true"/>
            <button:submit/>
            <button:cancel/>
        </form:form>
        <script src="${activeJsUrl}"></script>
    </body>
</html>
