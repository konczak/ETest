<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="language" value="${pageContext.response.locale.language}"/>
<c:url var="closedQuestionListLink" value="/teacher/closedQuestion/"/>
<c:url var="testTemplateListLink" value="/teacher/testTemplate/"/>
<c:url var="examListLink" value="/teacher/exam/"/>

<!DOCTYPE html>
<html lang="${language}">
    <head>
        <title><sitemesh:write property="title"/></title>
    <sitemesh:write property="head"/>
</head>
<body>
    <ul class="nav nav-tabs nav-justified">
        <li id="closedQuestion">
            <a href="${closedQuestionListLink}">
                <spring:message code="closedQuestion.list.title"/>
            </a>
        </li>
        <li id="testTemplate">
            <a href="${testTemplateListLink}">
                <spring:message code="testTemplate.list.title"/>
            </a>
        </li>
        <li id="exam">
            <a href="${examListLink}">
                <spring:message code="exam.list.title"/>
            </a>
        </li>
    </ul>

<sitemesh:write property="body"/>
</body>
</html>
