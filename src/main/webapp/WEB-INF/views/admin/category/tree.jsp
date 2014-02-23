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
                <manage:add url=""/>
            </h1>
        </div>
        <div class="row">
            <div class="col-lg-4 col-lg-offset-2">
                <ul class="list-unstyled">
                    <c:forEach items="${categories}" var="category">
                        <li>
                            <tree:category category="${category}"/>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <script src="${activeJsUrl}"></script>
    </body>
</html>