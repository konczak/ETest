<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="language" value="${pageContext.response.locale.language}"/>
<c:url var="categoryListLink" value="/question/category/"/>
<c:url var="closedAnswerListLink" value="/question/closedAnswer/"/>
<c:url var="closedQuestionListLink" value="/question/closedQuestion/"/>

<!DOCTYPE html>
<html lang="${language}">
    <head>
        <title><sitemesh:write property="title"/></title>
        <sitemesh:write property="head"/>
    </head>
<body>
    <ul class="nav nav-tabs nav-justified">
        <li id="categoryOfQuestion">
            <a href="${categoryListLink}">
                <spring:message code="categoryOfQuestion.list.title"/>
            </a>
        </li>
        <li id="closedAnswer">
            <a href="${closedAnswerListLink}">
                <spring:message code="closedAnswer.list.title"/>
            </a>
        </li>
        <li id="closedQuestion">
            <a href="${closedQuestionListLink}">
                <spring:message code="closedQuestion.list.title"/>
            </a>
        </li>

        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                Pytania <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a href="#">
                        <spring:message code="entity_list"/>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <spring:message code="entity_add"/>
                    </a>
                </li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                Odpowiedzi <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a href="#">
                        <spring:message code="entity_list"/>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <spring:message code="entity_add"/>
                    </a>
                </li>
            </ul>
        </li>
    </ul>

    <sitemesh:write property="body"/>
</body>
</html>
