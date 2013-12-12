<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<c:url var="activeJsUrl" value="/resources/js/activeLink/exam.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="exam.preview.title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="exam.preview.header"/></h1>
        </div>

        <dl class="dl-horizontal">
            <dt><spring:message code="entity_id"/></dt>
            <dd>${exam.id}</dd>
            <dt><spring:message code="testTemplate.subject.label"/></dt>
            <dd>${exam.testTampleteSubject}</dd>
            <dt><spring:message code="userGroup.title.label"/></dt>
            <dd>${exam.userGroupTitle}</dd>
            <dt><spring:message code="exam.examiner.label"/></dt>
            <dd>${exam.examinerLastname} ${exam.examinerFirstname}</dd>
            <dt><spring:message code="exam.activeFrom.label"/></dt>
            <dd><joda:format value="${exam.activeFrom}" style="MM" /></dd>
            <dt><spring:message code="exam.activeTo.label"/></dt>
            <dd><joda:format value="${exam.activeTo}" style="MM" /></dd>
        </dl>

        <div class="page-header">
            <h3>
                <spring:message code="userGroup.members.header"/>
            </h3>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="user.lastname.label"/></th>
                    <th><spring:message code="user.firstname.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${exam.userGroupMembers}" var="member">
                    <tr>
                        <td>${member.id}</td>
                        <td>${member.lastname}</td>
                        <td>${member.firstname}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <script src="${activeJsUrl}"></script>
    </body>
</html>
