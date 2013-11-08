<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url var="loginUrl" value="/login" />
<c:url var="logoutUrl" value="/j_spring_security_logout" />
<c:url var="settingsUrl" value="/user/settings" />

<sec:authorize var="loggedIn" access="isAuthenticated()" />

<c:choose>
    <c:when test="${loggedIn}">
        <li>
            <a href="${settingsUrl}" >
                <span class="glyphicon glyphicon-wrench"></span>
            </a>
        </li>
        <li>
            <a href="${logoutUrl}" >
                <span class="glyphicon glyphicon-log-out"></span>
            </a>
        </li>
    </c:when>
    <c:otherwise>
        <li>
            <a href="${loginUrl}" >
                <span class="glyphicon glyphicon-log-in"></span>
            </a>
        </li>
    </c:otherwise>
</c:choose>
