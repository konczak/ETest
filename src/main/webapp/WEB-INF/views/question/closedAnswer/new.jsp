<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>

<c:url var="activeJsUrl" value="/resources/js/activeLink/closedAnswer.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="closedAnswer.new.title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="closedAnswer.new.header"/></h1>
        </div>
        <div class="project-template">
            <form:form method="POST" commandName="closedAnswer" role="form">
                <form:errors path="*" cssClass="info-danger" element="div" />

                <myForm:input object="closedAnswer" fieldName="answer" required="true"/>
                <button type="submit" class="btn btn-primary" name="add" value="add">
                    <spring:message code="button_submit"/>
                </button>
                <button type="submit" class="btn btn-default" name="cancel" value="cancel">
                    <spring:message code="button_cancel"/>
                </button>
            </form:form>
        </div>
        <script src="${activeJsUrl}"></script>
    </body>
</html>
