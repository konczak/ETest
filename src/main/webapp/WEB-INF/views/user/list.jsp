<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url var="dataTablesCssUrl" value="/resources/css/dataTables.css" />
<c:url var="activeJsUrl" value="/resources/js/activeLink/user.js" />
<c:url var="dataTablesJsUrl" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesBootstrapPagingJsUrl" value="/resources/js/jquery.dataTables.bootstrap-paging.js" />
<c:url var="newLink" value="/question/closedQuestion/new"/>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="user.list.title"/></title>
        <link type="text/css" href="${dataTablesCssUrl}" rel="stylesheet">
    </head>
    <body>
        <div class="page-header">
            <h1>
                <spring:message code="user.list.header"/>
            </h1>
        </div>
        <table id="users" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="user.lastname.label"/></th>
                    <th><spring:message code="user.firstname.label"/></th>
                    <th><spring:message code="user.email.label"/></th>
                    <th><spring:message code="user.locked.label"/></th>
                    <th><spring:message code="user.manage.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <spring:url var="previewUrl" value="/user/{id}">
                        <spring:param name="id" value="${user.id}"/>
                    </spring:url>
                    <c:choose>
                        <c:when test="${user.locked}">
                            <c:set var="glyphicon" value="glyphicon-lock red"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="glyphicon" value="glyphicon-ok-circle green"/>
                        </c:otherwise>
                    </c:choose>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.lastname}</td>
                        <td>${user.firstname}</td>
                        <td>${user.email}</td>
                        <td>
                            <span class="glyphicon ${glyphicon}"></span>
                        </td>
                        <td>
                            <div class="btn-group">
                                <a href="${previewUrl}" class="btn btn-default">
                                    <span class="glyphicon glyphicon-eye-open"></span>
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <script src="${activeJsUrl}"></script>
        <script src="${dataTablesJsUrl}"></script>
        <script src="${dataTablesBootstrapPagingJsUrl}"></script>

        <script type="text/javascript">
            $('#users').dataTable({
                "oLanguage": {
                    "sSearch": "<spring:message code="global_search"/>",
                    "sZeroRecords": "<spring:message code="dataTables.sZeroRecords"/>",
                    "sEmptyTable": "<spring:message code="dataTables.sEmptyTable"/>",
                    "sInfo": "<spring:message code="dataTables.sInfo"/>",
                    "sInfoEmpty": "<spring:message code="dataTables.sInfoEmpty"/>",
                    "sInfoFiltered": "<spring:message code="dataTables.sInfoFiltered"/>",
                    "sLengthMenu": "<spring:message code="dataTables.sLengthMenu"/>",
                    "sLoadingRecords": "<spring:message code="dataTables.sLoadingRecords"/>",
                    "sProcessing": "<spring:message code="dataTables.sProcessing"/>",
                    "oPaginate": {
                        "sFirst": "<spring:message code="list_first"/>",
                        "sPrevious": "<spring:message code="list_previous"/>",
                        "sNext": "<spring:message code="list_next"/>",
                        "sLast": "<spring:message code="list_last"/>"
                    }
                }
            });
        </script>
    </body>
</html>
