<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="questionCategory.list.title"/></title>
    </head>
    <body>
        <h1><spring:message code="questionCategory.list.header"/></h1>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><spring:message code="questionCategory.title.label"/></th>
                    <th><spring:message code="SquestionCategory.remove.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${categories}" var="category">
                    <tr>
                        <td>${category.title}</td>
                        <td>
                            <span class="glyphicon glyphicon-trash"></span>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
