<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@attribute name="name" required="false"%>
<%@attribute name="value" required="false"%>

<c:if test="${empty name}">
    <c:set var="name" value="add"/>
</c:if>
<c:if test="${empty value}">
    <c:set var="value" value="add"/>
</c:if>

<button type="submit" class="btn btn-labeled btn-primary" name="${name}" value="${value}">
    <span class="btn-label">
        <i class="glyphicon glyphicon-ok"></i>
    </span>
    <spring:message code="button_submit"/>
</button>