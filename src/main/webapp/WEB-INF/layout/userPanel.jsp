<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:url var="loginUrl" value="/login" />
<spring:url var="logoutUrl" value="/j_spring_security_logout" />
<spring:url var="settingsUrl" value="/user/settings" />

<sec:authorize var="loggedIn" access="isAuthenticated()" />

<c:choose>
    <c:when test="${loggedIn}">
        <li>
            <a href="${settingsUrl}" >
                <i class="glyphicon glyphicon-wrench"></i>
            </a>
        </li>
        <li>
            <p class="navbar-text"><sec:authentication property="principal.username" /></p>
        </li>
        <li>
            <a href="${logoutUrl}" >
                <i class="glyphicon glyphicon-log-out"></i>
            </a>
        </li>
    </c:when>
    <c:otherwise>
        <li>
            <a href="${loginUrl}" >
                <i class="glyphicon glyphicon-log-in"></i>
            </a>
        </li>
    </c:otherwise>
</c:choose>
