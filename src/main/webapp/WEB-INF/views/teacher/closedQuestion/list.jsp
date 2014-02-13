<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="manage" tagdir="/WEB-INF/tags/manage/"%>

<spring:url var="dataTablesCssUrl" value="/resources/css/dataTables.css" />
<spring:url var="activeJsUrl" value="/resources/js/activeLink/closedQuestion.js" />
<spring:url var="dataTablesJsUrl" value="/resources/js/jquery.dataTables-1.9.4/jquery.dataTables.js" />
<spring:url var="dataTablesBootstrapPagingJsUrl" value="/resources/js/jquery.dataTables.bootstrap-paging-0.0.1/jquery.dataTables.bootstrap-paging.js" />
<c:set var="language" value="${pageContext.response.locale.language}"/>
<spring:url var="newLink" value="/teacher/closedQuestion/new"/>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="closedQuestion.list.title"/></title>
        <link type="text/css" href="${dataTablesCssUrl}" rel="stylesheet">
    </head>
    <body>
        <div class="page-header">
            <h1>
                <spring:message code="closedQuestion.list.header"/>
                <manage:add url="${newLink}"/>
            </h1>
        </div>
        <table id="closedQuestions" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="closedQuestion.question.label"/></th>
                    <th><spring:message code="closedQuestion.author.label"/></th>
                    <th><spring:message code="closedQuestion.manage.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${closedQuestions}" var="closedQuestion">
                    <spring:url var="previewUrl" value="/teacher/closedQuestion/{id}">
                        <spring:param name="id" value="${closedQuestion.id}"/>
                    </spring:url>
                    <spring:url var="deleteUrl" value="/teacher/closedQuestion/delete/{id}">
                        <spring:param name="id" value="${closedQuestion.id}"/>
                    </spring:url>
                    <tr>
                        <td>${closedQuestion.id}</td>
                        <td>${closedQuestion.question}</td>
                        <td id="${closedQuestion.authorId}">${closedQuestion.authorLastname} ${closedQuestion.authorFirstname}</td>
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
            $('#closedQuestions').dataTable({
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
