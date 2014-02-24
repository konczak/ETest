<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="manage" tagdir="/WEB-INF/tags/manage/"%>
<%@taglib prefix="tree" tagdir="/WEB-INF/tags/tree/"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/page/"%>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/category.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="category.list.title"/></title>
    </head>
    <body>
        <page:header text="category.list.header"/>
        <div class="row">
            <div class="col-lg-4 col-lg-offset-2">
                <ul class="list-unstyled">
                    <c:forEach items="${categories}" var="category">
                        <li data-categoryId="${category.id}" data-categoryName="${category.name}">
                            <tree:category category="${category}"/>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <jsp:include page="_newModal.jsp"/>
        <jsp:include page="_editModal.jsp"/>

        <script src="${activeJsUrl}"></script>
    </body>
</html>
