<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="manage" tagdir="/WEB-INF/tags/manage/"%>
<%@taglib prefix="link" tagdir="/WEB-INF/tags/link/"%>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/closedQuestion.js" />

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
            <c:if test="${not empty closedQuestion.imageId}">
                <spring:url var="closedQuestionImageUrl" value="/image/{id}">
                    <spring:param name="id" value="${closedQuestion.imageId}"/>
                </spring:url>
                <dt>
                    <spring:message code="closedQuestion.multipartFile.label"/>
                </dt>
                <dd>
                    <a id="showClosedQuestionPicture" href="#" class="btn btn-default">
                        <i class="glyphicon glyphicon-picture"></i>
                    </a>
                </dd>
            </c:if>
            <dt><spring:message code="category.preview.title"/></dt>
            <dd>
                ${closedQuestion.categoryName}
            </dd>
            <dt><spring:message code="closedQuestion.author.label"/></dt>
            <dd>
                ${closedQuestion.authorFirstname}
                ${closedQuestion.authorLastname}
            </dd>
        </dl>

        <spring:url var="closedAnswerNewLink" value="/teacher/closedAnswer/new/{closedQuestionId}">
            <spring:param name="closedQuestionId" value="${closedQuestion.id}"/>
        </spring:url>
        <div class="page-header">
            <h3>
                <spring:message code="closedAnswer.list.header"/>
                <manage:add url="${closedAnswerNewLink}"/>
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
                            <c:set var="glyphicon" value="glyphicon-ok-sign text-success"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="glyphicon" value="glyphicon-minus-sign text-danger"/>
                        </c:otherwise>
                    </c:choose>
                    <spring:url var="deleteUrl" value="/teacher/closedAnswer/delete/{id}">
                        <spring:param name="id" value="${closedAnswer.id}"/>
                    </spring:url>
                    <tr>
                        <td>${closedAnswer.id}</td>
                        <td>
                            <i class="glyphicon ${glyphicon}"></i>
                        </td>
                        <td>${closedAnswer.answer}</td>
                        <td>
                            <div class="btn-group">
                                <c:if test="${not empty closedAnswer.imageId}">
                                    <spring:url var="imageUrl" value="/image/{id}">
                                        <spring:param name="id" value="${closedAnswer.imageId}"/>
                                    </spring:url>
                                    <a id="${closedAnswer.imageId}" href="#" class="btn btn-default">
                                        <i class="glyphicon glyphicon-picture"></i>
                                    </a>
                                    <script>
                                        var img = '<img src="${imageUrl}">';
                                        $("a#" + ${closedAnswer.imageId}).popover({
                                            title: "<spring:message code="closedAnswer.multipartFile.label"/>",
                                            trigger: "hover",
                                            placement: "left",
                                            content: img,
                                            html: true,
                                            container: "body"
                                        });

                                        $("a#" + ${closedAnswer.imageId}).on("shown.bs.popover", function() {
                                            $(".popover").css("width", "auto");
                                            $(".popover").css("max-width", "600px");
                                        });
                                    </script>
                                </c:if>
                                <manage:remove url="${deleteUrl}"/>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <script src="${activeJsUrl}"></script>
        <c:if test="${not empty closedQuestion.imageId}">
            <script>
                var img = '<img src="${closedQuestionImageUrl}">';

                $("a#showClosedQuestionPicture").popover({
                    title: "<spring:message code="closedQuestion.multipartFile.label"/>",
                    trigger: "hover",
                    content: img,
                    html: true
                });

                $("a#showClosedQuestionPicture").on("shown.bs.popover", function() {
                    $(".popover").css("width", "auto");
                    $(".popover").css("max-width", "600px");
                });
            </script>
        </c:if>
    </body>
</html>
