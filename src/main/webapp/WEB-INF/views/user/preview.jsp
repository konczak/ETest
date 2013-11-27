<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<c:url var="activeJsUrl" value="/resources/js/activeLink/user.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="user.preview.title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="user.preview.header"/></h1>
        </div>

        <dl class="dl-horizontal">
            <dt><spring:message code="entity_id"/></dt>
            <dd>${user.id}</dd>
            <dt><spring:message code="user.email.label"/></dt>
            <dd>${user.email}</dd>
            <dt><spring:message code="user.firstname.label"/></dt>
            <dd>${user.firstname}</dd>
            <dt><spring:message code="user.lastname.label"/></dt>
            <dd>${user.lastname}</dd>
            <dt><spring:message code="user.locked.label"/></dt>
            <c:choose>
                <c:when test="${user.locked}">
                    <c:set var="glyphicon" value="glyphicon-lock red"/>
                </c:when>
                <c:otherwise>
                    <c:set var="glyphicon" value="glyphicon-ok-circle green"/>
                </c:otherwise>
            </c:choose>
            <dd><span class="glyphicon ${glyphicon}"></span></dd>
            <dt><spring:message code="user.registeredAt.label"/></dt>
            <dd><joda:format value="${user.registeredAt}" style="MM" /></dd>
        </dl>

        <div class="page-header">
            <h3>
                <spring:message code="userGroup.list.header"/>
            </h3>
        </div>
        <c:forEach items="${user.userGroups}" var="userGroup">
            <span class="label label-info">${userGroup.title}</span>
        </c:forEach>

        <script src="${activeJsUrl}"></script>
    </body>
</html>
