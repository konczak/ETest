<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="language" value="${pageContext.response.locale.language}"/>
<spring:url var="userExamListUrl" value="/student/userExam/"/>

<!DOCTYPE html>
<html lang="${language}">
    <head>
        <title><sitemesh:write property="title"/></title>
    <sitemesh:write property="head"/>
</head>
<body>
    <ul class="nav nav-tabs nav-justified">
        <li id="userExam">
            <a href="${userExamListUrl}">
                <i class="glyphicon glyphicon-list"></i>
                <spring:message code="exam.list.title"/>
            </a>
        </li>
    </ul>

<sitemesh:write property="body"/>
</body>
</html>
