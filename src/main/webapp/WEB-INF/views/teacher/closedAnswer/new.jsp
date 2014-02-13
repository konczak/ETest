<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/closedQuestion.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="closedAnswer.new.title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="closedQuestion.preview.header"/></h1>
        </div>

        <dl class="dl-horizontal">
            <dt><spring:message code="entity_id"/></dt>
            <dd>${closedAnswer.closedQuestionId}</dd>
            <dt><spring:message code="closedQuestion.question.label"/></dt>
            <dd>${closedAnswer.question}</dd>
        </dl>
        <div class="page-header">
            <h1><spring:message code="closedAnswer.new.header"/></h1>
        </div>
        <form:form method="POST" commandName="closedAnswer" role="form" enctype="multipart/form-data">
            <form:errors path="*" cssClass="info-danger" element="div" />

            <form:hidden path="closedQuestionId"/>
            <form:hidden path="question"/>

            <myForm:input object="closedAnswer" fieldName="answer" required="true"/>
            <myForm:checkbox object="closedAnswer" fieldName="correct"/>
            <myForm:file object="closedAnswer" fieldName="multipartFile" acceptedFiles="image/*"/>

            <button type="submit" class="btn btn-primary" name="add" value="add">
                <spring:message code="button_submit"/>
            </button>
            <button type="submit" class="btn btn-default" name="cancel" value="cancel">
                <spring:message code="button_cancel"/>
            </button>
        </form:form>
        <script src="${activeJsUrl}"></script>
    </body>
</html>
