<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="manage" tagdir="/WEB-INF/tags/manage/"%>

<c:url var="dataTablesCssUrl" value="/resources/css/dataTables.css" />
<c:url var="activeJsUrl" value="/resources/js/activeLink/testTemplate.js" />
<c:url var="dataTablesJsUrl" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesBootstrapPagingJsUrl" value="/resources/js/jquery.dataTables.bootstrap-paging.js" />
<c:url var="newLink" value="/teacher/testTemplate/new"/>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="testTemplate.list.title"/></title>
        <link type="text/css" href="${dataTablesCssUrl}" rel="stylesheet">
    </head>
    <body>
        <div class="page-header">
            <h1>
                <spring:message code="testTemplate.list.header"/>
                <manage:add url="${newLink}"/>
            </h1>
        </div>
        <table id="testTemplates" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="testTemplate.subject.label"/></th>
                    <th><spring:message code="testTemplate.author.label"/></th>
                    <th><spring:message code="testTemplate.manage.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${testTemplates}" var="testTemplate">
                    <spring:url var="previewUrl" value="/teacher/testTemplate/{id}">
                        <spring:param name="id" value="${testTemplate.id}"/>
                    </spring:url>
                    <spring:url var="deleteUrl" value="/teacher/testTemplate/delete/{id}">
                        <spring:param name="id" value="${testTemplate.id}"/>
                    </spring:url>
                    <tr>
                        <td>${testTemplate.id}</td>
                        <td>${testTemplate.subject}</td>
                        <td id="${testTemplate.authorId}">${testTemplate.authorLastname} ${testTemplate.authorFirstname}</td>
                        <td>
                            <div class="btn-group">
                                <manage:preview url="${previewUrl}"/>
                                <manage:remove url="${deleteUrl}"/>
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
            $('#testTemplates').dataTable({
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
