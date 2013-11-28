<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="activeJsUrl" value="/resources/js/activeLink/userGroup.js" />
<c:url var="dataTablesCssUrl" value="/resources/css/dataTables.css" />
<c:url var="dataTablesJsUrl" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesBootstrapPagingJsUrl" value="/resources/js/jquery.dataTables.bootstrap-paging.js" />
<spring:url var="membersUrl" value="/user/group/{id}/members">
    <spring:param name="id" value="${userGroup.id}"/>
</spring:url>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="userGroup.preview.title"/></title>
        <link type="text/css" href="${dataTablesCssUrl}" rel="stylesheet">
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="userGroup.preview.header"/></h1>
        </div>

        <dl class="dl-horizontal">
            <dt><spring:message code="entity_id"/></dt>
            <dd>${userGroup.id}</dd>
            <dt><spring:message code="userGroup.title.label"/></dt>
            <dd>${userGroup.title}</dd>
            <dt><spring:message code="userGroup.membersCount.label"/></dt>
            <dd>${fn:length(userGroup.members)}</dd>
        </dl>

        <div class="page-header">
            <h3>
                <spring:message code="user.list.header"/>
                <a href="${membersUrl}" class="btn btn-default">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </h3>
        </div>
        <table id="dataTables" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>#</th>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="user.lastname.label"/></th>
                    <th><spring:message code="user.firstname.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${userGroup.members}" var="member" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${member.id}</td>
                        <td>${member.lastname}</td>
                        <td>${member.firstname}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <script src="${activeJsUrl}"></script>
        <script src="${dataTablesJsUrl}"></script>
        <script src="${dataTablesBootstrapPagingJsUrl}"></script>
        
        <script type="text/javascript">
            $('table#dataTables').dataTable({
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
