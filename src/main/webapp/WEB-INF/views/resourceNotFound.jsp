<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/page/"%>
<html>
    <head>
        <title><spring:message code="error_resourcenotfound_title"/></title>
    </head>
    <body>
        <page:header text="error_resourcenotfound_title"/>
        <p>
            <spring:message code="error_resourcenotfound_problemdescription" />
        </p>
        <spring:message var="message" code="exception_message" />
        <p class="alert alert-warning">
            ${exception.message}
        </p>
        <spring:message var="stacktrace" code="exception_stacktrace" />
        <p class="alert alert-danger">
            <c:forEach items="${exception.stackTrace}" var="trace">
                ${trace}<br>
            </c:forEach>
        </p>
    </body>
</html>