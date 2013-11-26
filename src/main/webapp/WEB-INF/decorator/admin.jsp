<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="language" value="${pageContext.response.locale.language}"/>
<c:url var="categoryListLink" value="/question/category/"/>
<c:url var="closedQuestionListLink" value="/question/closedQuestion/"/>
<c:url var="userListLink" value="/user/"/>
<c:url var="userGroupListLink" value="/user/group/"/>

<!DOCTYPE html>
<html lang="${language}">
    <head>
        <title><sitemesh:write property="title"/></title>
    <sitemesh:write property="head"/>
</head>
<body>
    <ul class="nav nav-tabs nav-justified">
        <li id="user">
            <a href="${userListLink}">
                <spring:message code="user.list.title"/>
            </a>
        </li>
        <li id="userGroup">
            <a href="${userGroupListLink}">
                <spring:message code="userGroup.list.title"/>
            </a>
        </li>
        <li id="categoryOfQuestion">
            <a href="${categoryListLink}">
                <spring:message code="categoryOfQuestion.list.title"/>
            </a>
        </li>
        <li id="closedQuestion">
            <a href="${closedQuestionListLink}">
                <spring:message code="closedQuestion.list.title"/>
            </a>
        </li>
    </ul>

<sitemesh:write property="body"/>
</body>
</html>
