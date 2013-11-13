<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="categoryOfQuestion.list.title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="categoryOfQuestion.list.header"/></h1>
        </div>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="categoryOfQuestion.title.label"/></th>
                    <th><spring:message code="categoryOfQuestion.remove.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${categories}" var="category">
                    <c:url var="editCategoryUrl" value="/question/category/edit">
                        <c:param name="id" value="${category.id}"/>
                    </c:url>
                    <c:url var="removeCategoryUrl" value="/question/category/delete">
                        <c:param name="id" value="${category.id}"/>
                    </c:url>
                    <tr>
                        <td>${category.id}</td>
                        <td>${category.title}</td>
                        <td>
                            <div class="btn-group">
                                <a href="${editCategoryUrl}" class="btn btn-default">
                                    <span class="glyphicon glyphicon-edit"></span>
                                </a>
                                <a href="${removeCategoryUrl}" class="btn btn-default">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
