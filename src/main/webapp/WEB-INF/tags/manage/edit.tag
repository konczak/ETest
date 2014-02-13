<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@attribute name="url" required="true" %>

<a href="${url}" class="btn btn-labeled btn-primary">
    <span class="btn-label"><i class="glyphicon glyphicon-edit"></i></span><spring:message code="manage.edit"/>
</a>