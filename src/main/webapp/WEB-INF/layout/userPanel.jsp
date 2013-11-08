<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url var="loginUrl" value="/login" />
<c:url var="logoutUrl" value="/j_spring_security_logout" />
<sec:authorize var="loggedIn" access="isAuthenticated()" />

<li>
    <c:choose>
        <c:when test="${loggedIn}">
            <a href="${logoutUrl}" >
                <span class="glyphicon glyphicon-log-out"></span>
            </a>
        </c:when>
        <c:otherwise>
            <a href="${loginUrl}" >
                <span class="glyphicon glyphicon-log-in"></span>
            </a>
        </c:otherwise>
    </c:choose>
</li>
