<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@attribute name="url" required="true" %>

<a href="${url}" class="btn btn-labeled btn-info">
    <span class="btn-label"><i class="glyphicon glyphicon-info-sign"></i></span><spring:message code="manage.preview"/>
</a>