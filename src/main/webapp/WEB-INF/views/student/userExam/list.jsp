<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="manage" tagdir="/WEB-INF/tags/manage/"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/page/"%>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/userExam.js" />
<spring:url var="dataTablesCssUrl" value="/resources/css/dataTables.css" />
<spring:url var="dataTablesJsUrl" value="/resources/js/jquery.dataTables-1.9.4/jquery.dataTables.js" />
<spring:url var="dataTablesBootstrapPagingJsUrl" value="/resources/js/jquery.dataTables.bootstrap-paging-0.0.1/jquery.dataTables.bootstrap-paging.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="userExam.list.title"/></title>
        <link type="text/css" href="${dataTablesCssUrl}" rel="stylesheet">
    </head>
    <body>
        <page:header text="userExam.list.header"/>
        <table id="dataTables" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="testTemplate.subject.label"/></th>
                    <th><spring:message code="exam.suffix.label"/></th>
                    <th><spring:message code="exam.examiner.label"/></th>
                    <th><spring:message code="userExam.manage.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${userExams}" var="userExam">
                    <spring:url var="userExamSheetUrl" value="/student/userExam/{id}">
                        <spring:param name="id" value="${userExam.id}"/>
                    </spring:url>
                    <tr>
                        <td>${userExam.id}</td>
                        <td>${userExam.testTemplateSubject}</td>
                        <td>${userExam.titleSuffix}</td>
                        <td>${userExam.examinerFirstname} ${userExam.examinerLastname}</td>
                        <td>
                            <div class="btn-group">
                                <manage:preview url="${userExamSheetUrl}"/>
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
