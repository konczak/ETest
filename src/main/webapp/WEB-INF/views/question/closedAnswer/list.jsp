<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url var="dataTablesCssUrl" value="/resources/css/dataTables.css" />
<c:url var="activeJsUrl" value="/resources/js/activeLink/closedAnswer.js" />
<c:url var="dataTablesJsUrl" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesBootstrapPagingJsUrl" value="/resources/js/jquery.dataTables.bootstrap-paging.js" />
<c:set var="language" value="${pageContext.response.locale.language}"/>
<c:url var="questionCategoryNewLink" value="/question/closedAnswer/new"/>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="closedAnswer.list.title"/></title>
        <link type="text/css" href="${dataTablesCssUrl}" rel="stylesheet">
    </head>
    <body>
        <div class="page-header">
            <h1>
                <spring:message code="closedAnswer.list.header"/>
                <a href="${questionCategoryNewLink}" class="btn btn-default">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                </a>
            </h1>
        </div>
        <table id="closedAnswers" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="closedAnswer.answer.label"/></th>
                    <th><spring:message code="closedAnswer.remove.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${closedAnswers}" var="closedAnswer">
                    <c:url var="editCategoryUrl" value="/question/closedAnswer/edit">
                        <c:param name="id" value="${closedAnswer.id}"/>
                    </c:url>
                    <c:url var="removeCategoryUrl" value="/question/closedAnswer/delete">
                        <c:param name="id" value="${closedAnswer.id}"/>
                    </c:url>
                    <tr>
                        <td>${closedAnswer.id}</td>
                        <td>${closedAnswer.answer}</td>
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

        <script src="${activeJsUrl}"></script>
        <script src="${dataTablesJsUrl}"></script>
        <script src="${dataTablesBootstrapPagingJsUrl}"></script>

        <script type="text/javascript">
            $('#closedAnswers').dataTable({
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
