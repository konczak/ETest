<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:url var="registerUrl" value="/user/new" />
<spring:url var="studentUrl" value="/student" />
<spring:url var="teacherUrl" value="/teacher" />
<spring:url var="adminUrl" value="/admin" />


<ul class="nav navbar-nav">
    <sec:authorize var="loggedIn" access="isAuthenticated()" >
        <li>
            <a href="${registerUrl}">
                <spring:message code="user.new.label"/>
            </a>
        </li>
    </sec:authorize>
    <sec:authorize access="hasRole('STUDENT')">
        <li id="student">
            <a href="${studentUrl}">
                <spring:message code="menu.student.label"/>
            </a>
        </li>
    </sec:authorize>
    <sec:authorize access="hasRole('TEACHER')">
        <li id="teacher">
            <a href="${teacherUrl}">
                <spring:message code="menu.teacher.label"/>
            </a>
        </li>
    </sec:authorize>
    <sec:authorize access="hasRole('ADMIN')">
        <li id="admin">
            <a href="${adminUrl}">
                <spring:message code="menu.admin.label"/>
            </a>
        </li>
    </sec:authorize>
</ul>

