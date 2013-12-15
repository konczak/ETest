<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<c:url var="dataTablesCssUrl" value="/resources/css/dataTables.css" />
<c:url var="activeJsUrl" value="/resources/js/activeLink/exam.js" />
<c:url var="dataTablesJsUrl" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesBootstrapPagingJsUrl" value="/resources/js/jquery.dataTables.bootstrap-paging.js" />
<c:url var="newLink" value="/teacher/exam/new"/>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="exam.list.title"/></title>
        <link type="text/css" href="${dataTablesCssUrl}" rel="stylesheet">
    </head>
    <body>
        <div class="page-header">
            <h1>
                <spring:message code="exam.list.header"/>
                <a href="${newLink}" class="btn btn-default">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                </a>
            </h1>
        </div>
        <table id="exams" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="testTemplate.subject.label"/></th>
                    <th><spring:message code="userGroup.title.label"/></th>
                    <th><spring:message code="exam.suffix.label"/></th>
                    <th><spring:message code="exam.examiner.label"/></th>
                    <th><spring:message code="exam.activeFrom.label"/></th>
                    <th><spring:message code="exam.activeFrom.label"/></th>
                    <th><spring:message code="exam.activeTo.label"/></th>
                    <th><spring:message code="exam.activeTo.label"/></th>
                    <th><spring:message code="exam.manage.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${exams}" var="exam">
                    <spring:url var="previewUrl" value="/teacher/exam/{id}">
                        <spring:param name="id" value="${exam.id}"/>
                    </spring:url>
                    <tr>
                        <td>${exam.id}</td>
                        <td>${exam.testTemplateSubject}</td>
                        <td>${exam.userGroupTitle}</td>
                        <td>${exam.suffix}</td>
                        <td id="${exam.examinerId}">${exam.examinerLastname} ${exam.examinerFirstname}</td>
                        <td>
                            <joda:format value="${exam.activeFrom}" style="M-" />
                        </td>
                        <td>
                            <joda:format value="${exam.activeFrom}" style="-M" />
                        </td>
                        <td>
                            <joda:format value="${exam.activeTo}" style="-M" />
                        </td>
                        <td>
                            <joda:format value="${exam.activeTo}" style="M-" />
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
            $('#exams').dataTable({
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
