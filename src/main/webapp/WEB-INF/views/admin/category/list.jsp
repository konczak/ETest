<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="manage" tagdir="/WEB-INF/tags/manage/"%>
<%@taglib prefix="tree" tagdir="/WEB-INF/tags/tree/" %>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/category.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="category.list.title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1>
                <spring:message code="category.list.header"/>
            </h1>
        </div>
        <ul class="list-unstyled">
            <c:forEach items="${categories}" var="category">
                <li>
                    <tree:category category="${category}"/>
                </li>
            </c:forEach>
        </ul>
        <script src="${activeJsUrl}"></script>
    </body>
</html>
