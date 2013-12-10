<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url var="activeJsUrl" value="/resources/js/activeLink/testTemplate.js" />
<spring:url var="closedQuestionsUrl" value="/teacher/testTemplate/{id}/closedQuestions">
    <spring:param name="id" value="${testTemplate.id}"/>
</spring:url>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="testTemplate.preview.title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="testTemplate.preview.header"/></h1>
        </div>

        <dl class="dl-horizontal">
            <dt><spring:message code="entity_id"/></dt>
            <dd>${testTemplate.id}</dd>
            <dt><spring:message code="testTemplate.subject.label"/></dt>
            <dd>${testTemplate.subject}</dd>
            <dt><spring:message code="testTemplate.author.label"/></dt>
            <dd>${testTemplate.authorLastname} ${testTemplate.authorFirstname}</dd>
        </dl>

        <div class="page-header">
            <h3>
                <spring:message code="closedQuestion.list.header"/>
                <a href="${closedQuestionsUrl}" class="btn btn-default">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </h3>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="testTemplate.closedQuestionMandatory.label"/></th>
                    <th><spring:message code="closedQuestion.question.label"/></th>
                    <th><spring:message code="testTemplate.manage.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${testTemplate.closedQuestions}" var="closedQuestion">
                    <c:choose>
                        <c:when test="${closedQuestion.mandatory}">
                            <c:set var="glyphicon" value="glyphicon-check text-success"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="glyphicon" value="glyphicon-unchecked"/>
                        </c:otherwise>
                    </c:choose>
                    <tr>
                        <td>${closedQuestion.id}</td>
                        <td>
                            <span class="glyphicon ${glyphicon}"></span>
                        </td>
                        <td>${closedQuestion.question}</td>
                        <td>
                            <div class="btn-group">
                                <a href="#" class="btn btn-default">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <script src="${activeJsUrl}"></script>
    </body>
</html>
