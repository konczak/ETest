<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url var="registerLink" value="/user/new" />

<sec:authorize var="loggedIn" access="isAuthenticated()" />

<ul class="nav navbar-nav">
    <c:if test="${not loggedIn}">
        <li>
            <a href="${registerLink}">
                <spring:message code="user.new.label"/>
            </a>
        </li>
    </c:if>
</ul>

