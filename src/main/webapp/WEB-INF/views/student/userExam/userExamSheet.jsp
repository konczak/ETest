<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<c:url var="activeJsUrl" value="/resources/js/activeLink/userExam.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="userExam.sheet.title" arguments="${userExam.testTemplateSubject}, ${userExam.testTemplateSuffix}"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="userExam.sheet.header" arguments="${userExam.testTemplateSubject}, ${userExam.testTemplateSuffix}"/></h1>
        </div>

        <dl class="dl-horizontal">
            <dt><spring:message code="entity_id"/></dt>
            <dd>${userExam.id}</dd>
            <dt><spring:message code="userExam.subject.label"/></dt>
            <dd>${userExam.testTemplateSubject} ${userExam.testTemplateSuffix}</dd>
            <dt><spring:message code="exam.activeFrom.label"/></dt>
            <dd>${userExam.activeFrom} [${userExam.activeInSeconds}sec]</dd>
            <dt><spring:message code="exam.activeTo.label"/></dt>
            <dd>${userExam.activeTo} [${userExam.inactiveInSeconds}sec]</dd>
        </dl>

        <div class="row">
            <div class="col-lg-3">
                <div class="list-group">
                    <c:forEach items="${userExam.closedQuestions}" var="closedQuestion" varStatus="status">
                        <spring:url var="userExamClosedQuestionUrl" value="/userExam/{id}/closedQuestion/{closedQuestionId}">
                            <spring:param name="id" value="${userExam.id}"/>
                            <spring:param name="closedQuestionId" value="${closedQuestion}"/>
                        </spring:url>
                        <a href="${userExamClosedQuestionUrl}" class="list-group-item">
                            Pytanie ${status.count}
                        </a>
                    </c:forEach>
                </div>
            </div>
            <div class="col-lg-9">
                <c:choose>
                    <c:when test="${empty userExam.closedQuestion}">
                        Welcome!
                    </c:when>
                    <c:otherwise>
                        ClosedQuestion and closedAnswers etc
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <script src="${activeJsUrl}"></script>
    </body>
</html>
