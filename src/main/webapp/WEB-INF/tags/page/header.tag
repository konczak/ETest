<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="manage" tagdir="/WEB-INF/tags/manage/"%>
<%@attribute name="text" required="true"%>
<%@attribute name="textArguments" required="false"%>
<%@attribute name="addButtonUrl" required="false"%>

<div class="page-header">
    <h1>
        <spring:message code="${text}" arguments="${textArguments}"/>
        <c:if test="${not empty addButtonUrl}">
            <manage:add url="${addButtonUrl}"/>
        </c:if>
    </h1>
</div>