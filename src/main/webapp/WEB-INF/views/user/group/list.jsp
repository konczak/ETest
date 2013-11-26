<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url var="activeJsUrl" value="/resources/js/activeLink/userGroup.js" />
<c:url var="dataTablesCssUrl" value="/resources/css/dataTables.css" />
<c:url var="dataTablesJsUrl" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesBootstrapPagingJsUrl" value="/resources/js/jquery.dataTables.bootstrap-paging.js" />
<c:url var="newLink" value="/user/group/new"/>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="userGroup.list.title"/></title>
        <link type="text/css" href="${dataTablesCssUrl}" rel="stylesheet">
    </head>
    <body>
        <div class="page-header">
            <h1>
                <spring:message code="userGroup.list.header"/>
                <a href="${newLink}" class="btn btn-default">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                </a>
            </h1>
        </div>
        <table id="userGroups" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="userGroup.title.label"/></th>
                    <th><spring:message code="userGroup.membersCount.label"/></th>
                    <th><spring:message code="userGroup.manage.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${userGroups}" var="userGroup">
                    <spring:url var="editUrl" value="/user/group/edit/{id}">
                        <spring:param name="id" value="${userGroup.id}"/>
                    </spring:url>
                    <spring:url var="deleteUrl" value="/user/group/delete/{id}">
                        <spring:param name="id" value="${userGroup.id}"/>
                    </spring:url>
                    <tr>
                        <td>${userGroup.id}</td>
                        <td>${userGroup.title}</td>
                        <td>${userGroup.membersCount}</td>
                        <td>
                            <div class="btn-group">
                                <a href="${editUrl}" class="btn btn-default">
                                    <span class="glyphicon glyphicon-edit"></span>
                                </a>
                                <a href="${deleteUrl}" class="btn btn-default">
                                    <span class="glyphicon glyphicon-trash"></span>
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
            $('#userGroups').dataTable({
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
