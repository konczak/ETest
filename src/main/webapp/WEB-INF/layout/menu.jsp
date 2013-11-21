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
    <li><a href="#about">About</a></li>
    <li><a href="#contact">Contact</a></li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
        <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li class="dropdown-header">Nav header</li>
            <li><a href="#">Separated link</a></li>
            <li><a href="#">One more separated link</a></li>
        </ul>
    </li>
</ul>

