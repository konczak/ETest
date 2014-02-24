<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@taglib prefix="manage" tagdir="/WEB-INF/tags/manage/"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/page/"%>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/exam.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="userExam.preview.title"/></title>
    </head>
    <body>
        <page:header text="userExam.preview.header" textArguments="${userExam.userFirstname}, ${userExam.userLastname}"/>

        <dl class="dl-horizontal">
            <dt><spring:message code="entity_id"/></dt>
            <dd>${userExam.id}</dd>
            <dt><spring:message code="testTemplate.subject.label"/></dt>
            <dd>${userExam.testTemplateSubject}</dd>
            <dt><spring:message code="user.lastname.label"/></dt>
            <dd>${userExam.userFirstname}</dd>
            <dt><spring:message code="user.firstname.label"/></dt>            
            <dd>${userExam.userLastname}</dd>
            <dt><spring:message code="userExam.resultPoints.label"/> / <spring:message code="userExam.maxPoints.label"/></dt>
            <dd>${userExam.resultPoints} / ${userExam.maxPoints}</dd>
            <dt>%</dt>
            <dd><fmt:formatNumber value="${userExam.resultPoints / userExam.maxPoints * 100.00}" minFractionDigits="0" maxFractionDigits="0"/></dd>
            <dt><spring:message code="userExam.passed.label"/></dt>
            <dd>
                <c:choose>
                    <c:when test="${userExam.resultPoints > 0.5 * userExam.maxPoints}">
                        <i class="glyphicon glyphicon-ok-sign text-success"></i>
                    </c:when>
                    <c:otherwise>
                        <i class="glyphicon glyphicon-minus-sign text-danger"></i>
                    </c:otherwise>
                </c:choose>
            </dd>
        </dl>

        <div class="panel panel-default">
            <c:forEach items="${userExam.userExamClosedQuestions}" var="userExamClosedQuestion" varStatus="index">
                <c:set var="closedQuestionResultColor" value="text-info"/>
                <c:choose>
                    <c:when test="${userExamClosedQuestion.resultPoints == userExamClosedQuestion.maxPoints}">
                        <c:set var="closedQuestionResultColor" value="text-success"/>
                    </c:when>
                    <c:when test="${userExamClosedQuestion.resultPoints == 0}">
                        <c:set var="closedQuestionResultColor" value="text-danger"/>
                    </c:when>
                </c:choose>
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse${userExamClosedQuestion.userExamClosedQuestionId}">
                            ${index.index + 1}. ${userExamClosedQuestion.question}
                        </a>
                        <%--
                        ${userExamClosedQuestion.userExamClosedQuestionId}
                        ${userExamClosedQuestion.closedQuestionId}
                        --%>
                        <strong class="pull-right ${closedQuestionResultColor}">
                            ${userExamClosedQuestion.resultPoints} / ${userExamClosedQuestion.maxPoints}
                        </strong>
                    </h4>
                </div>
                <div id="collapse${userExamClosedQuestion.userExamClosedQuestionId}" class="panel-collapse collapse">
                    <div class="panel-body">
                        <dl class="dl-horizontal">
                            <c:forEach items="${userExamClosedQuestion.userExamClosedAnswers}" var="userExamClosedAnswer">
                                <dt>
                                <c:choose>
                                    <c:when test="${userExamClosedAnswer.correct == userExamClosedAnswer.userAnswer}">
                                        <i class="glyphicon glyphicon-ok-circle text-info"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="glyphicon glyphicon-remove-circle text-danger"></i>
                                    </c:otherwise>
                                </c:choose>
                                </dt>
                                <%--
                                ${userExamClosedAnswer.userExamClosedAnswerId}
                                ${userExamClosedAnswer.closedAnswerId}
                                --%>
                                <dd>
                                    ${userExamClosedAnswer.answer}
                                </dd>
                            </c:forEach>
                        </dl>
                    </div>
                </div>
            </c:forEach>
        </div>
        <ul class="pager">
            <li>
                <a href="#">
                    <i class="glyphicon glyphicon-arrow-left"></i>
                    <spring:message code="list_previous"/>
                </a>
            </li>
            <li>
                <a href="#">
                    back to exam
                    <i class="glyphicon glyphicon-repeat"></i>
                </a>
            </li>
            <li>                
                <a href="#">
                    <spring:message code="list_next"/>
                    <i class="glyphicon glyphicon-arrow-right"></i>
                </a>
            </li>
        </ul>

        <script src="${activeJsUrl}"></script>

    </body>
</html>
