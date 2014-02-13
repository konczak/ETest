<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="language" value="${pageContext.response.locale.language}"/>
<c:url var="categoryOfQuestionListLink" value="/admin/categoryOfQuestion/"/>
<c:url var="userListLink" value="/admin/user/"/>
<c:url var="userGroupListLink" value="/admin/userGroup/"/>

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
                <i class="glyphicon glyphicon-list"></i>
                <spring:message code="user.list.title"/>
            </a>
        </li>
        <li id="userGroup">
            <a href="${userGroupListLink}">
                <i class="glyphicon glyphicon-list"></i>
                <spring:message code="userGroup.list.title"/>
            </a>
        </li>
        <li id="categoryOfQuestion">
            <a href="${categoryOfQuestionListLink}">
                <i class="glyphicon glyphicon-list"></i>
                <spring:message code="categoryOfQuestion.list.title"/>
            </a>
        </li>
    </ul>

<sitemesh:write property="body"/>
</body>
</html>
