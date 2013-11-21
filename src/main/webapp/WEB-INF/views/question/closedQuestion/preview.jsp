<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url var="activeJsUrl" value="/resources/js/activeLink/closedQuestion.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="closedQuestion.preview.title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="closedQuestion.preview.header"/></h1>
        </div>

        <dl class="dl-horizontal">
            <dt><spring:message code="entity_id"/></dt>
            <dd>${closedQuestion.id}</dd>
            <dt><spring:message code="closedQuestion.question.label"/></dt>
            <dd>${closedQuestion.question}</dd>
        </dl>

        <spring:url var="closedAnswerNewLink" value="/question/closedAnswer/new/{closedQuestionId}">
            <spring:param name="closedQuestionId" value="${closedQuestion.id}"/>
        </spring:url>
        <div class="page-header">
            <h3>
                <spring:message code="closedAnswer.list.header"/>
                <a href="${closedAnswerNewLink}" class="btn btn-default">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                </a>
            </h3>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="closedAnswer.correct.label"/></th>
                    <th><spring:message code="closedAnswer.answer.label"/></th>
                    <th><spring:message code="closedAnswer.manage.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${closedQuestion.closedAnswers}" var="closedAnswer">
                    <c:choose>
                        <c:when test="${closedAnswer.correct}">
                            <c:set var="rowCssClass" value="success"/>
                            <c:set var="glyphicon" value="glyphicon-check"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="rowCssClass" value=""/>
                            <c:set var="glyphicon" value="glyphicon-ban-circle"/>
                        </c:otherwise>
                    </c:choose>
                    <spring:url var="deleteUrl" value="/question/closedAnswer/delete/{id}">
                        <spring:param name="id" value="${closedAnswer.id}"/>
                    </spring:url>
                    <tr class="${rowCssClass}">
                        <td>${closedAnswer.id}</td>
                        <td>
                            <span class="glyphicon ${glyphicon}"></span>
                        </td>
                        <td>${closedAnswer.answer}</td>
                        <td>
                            <div class="btn-group">
                                <a href="${deleteUrl}" class="btn btn-default">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="page-header">
            <h3>
                <spring:message code="categoryOfQuestion.list.header"/>
            </h3>
        </div>
        <c:forEach items="${closedQuestion.categoriesOfQuestion}" var="categoryOfQuestion">
            <span class="label label-info">${categoryOfQuestion.title}</span>
        </c:forEach>
        <script src="${activeJsUrl}"></script>
    </body>
</html>
