<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url var="dataTablesCssUrl" value="/resources/css/dataTables.css" />
<c:url var="activeJsUrl" value="/resources/js/activeLink/closedQuestion.js" />
<c:url var="dataTablesJsUrl" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesBootstrapPagingJsUrl" value="/resources/js/jquery.dataTables.bootstrap-paging.js" />
<c:set var="language" value="${pageContext.response.locale.language}"/>
<c:url var="newLink" value="/question/closedQuestion/new"/>

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
                <a href="${newLink}" class="btn btn-default">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                </a>
            </h1>
        </div>
        <table id="closedQuestions" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="closedQuestion.question.label"/></th>
                    <th>autor be</th>
                    <th><spring:message code="closedQuestion.remove.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${closedQuestions}" var="closedQuestion">
                    <c:url var="editUrl" value="/question/closedQuestion/edit">
                        <c:param name="id" value="${closedQuestion.id}"/>
                    </c:url>
                    <c:url var="removeUrl" value="/question/closedQuestion/delete">
                        <c:param name="id" value="${closedQuestion.id}"/>
                    </c:url>
                    <tr>
                        <td>${closedQuestion.id}</td>
                        <td>${closedQuestion.question}</td>
                        <td>${closedQuestion.author.email}</td>
                        <td>
                            <div class="btn-group">
                                <a href="${editUrl}" class="btn btn-default">
                                    <span class="glyphicon glyphicon-edit"></span>
                                </a>
                                <a href="${removeUrl}" class="btn btn-default">
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
