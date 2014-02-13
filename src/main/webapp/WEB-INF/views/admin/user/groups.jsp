<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/user.js" />
<spring:url var="dataTablesCssUrl" value="/resources/css/dataTables.css" />
<spring:url var="dataTablesJsUrl" value="/resources/js/jquery.dataTables-1.9.4/jquery.dataTables.js" />
<spring:url var="dataTablesBootstrapPagingJsUrl" value="/resources/js/jquery.dataTables.bootstrap-paging-0.0.1/jquery.dataTables.bootstrap-paging.js" />
<spring:url var="addToGroupUrl" value="/admin/user/{id}/groups/add">
    <spring:param name="id" value="${user.id}"/>
</spring:url>
<spring:url var="removeFromGroupUrl" value="/admin/user/{id}/groups/delete">
    <spring:param name="id" value="${user.id}"/>
</spring:url>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="user.groups.title"/></title>
        <link type="text/css" href="${dataTablesCssUrl}" rel="stylesheet">
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="user.groups.header"/></h1>
        </div>

        <dl class="dl-horizontal">
            <dt><spring:message code="entity_id"/></dt>
            <dd>${user.id}</dd>
            <dt><spring:message code="user.email.label"/></dt>
            <dd>${user.email}</dd>
            <dt><spring:message code="user.firstname.label"/></dt>
            <dd>${user.firstname}</dd>
            <dt><spring:message code="user.lastname.label"/></dt>
            <dd>${user.lastname}</dd>
            <dt><spring:message code="user.groupsCount.label"/></dt>
            <dd id="countOfGroups">${user.countOfGroupsBelongTo}</dd>
        </dl>

        <div class="page-header">
            <h3>
                <spring:message code="userGroup.list.header"/>
            </h3>
        </div>
        <table id="dataTables" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>#</th>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="userGroup.title.label"/></th>
                    <th><spring:message code="user.groupsAlreadyMember.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${user.groups}" var="entry" varStatus="status">
                    <c:choose>
                        <c:when test="${entry.value.alreadyMember}">
                            <c:set var="memberColor" value="glyphicon-user text-success"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="memberColor" value="glyphicon-ban-circle"/>
                        </c:otherwise>
                    </c:choose>
                    <tr>
                        <td>${status.count}</td>
                        <td>${entry.key}</td>
                        <td>${entry.value.title}</td>
                        <td>
                            <button type="button" class="btn btn-default" onclick="doAjax(${entry.key})">
                                <span id="${entry.key}" class="glyphicon ${memberColor}" dataAlreadyMember="${entry.value.alreadyMember}"></span>
                            </button>
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

        <script type="text/javascript">
            function doAjax(userId) {
                var theSpan = $("span#" + userId);
                var alreadyIn = theSpan.attr("dataAlreadyMember");
                if(alreadyIn === 'true') {
                    $.ajax({
                        type: "POST",
                        url: "${removeFromGroupUrl}",
                        data: { userId: userId },
                        success: function(response) {
                            var theSpan = $("span#" + userId);
                            theSpan.removeClass("glyphicon-user");
                            theSpan.removeClass("text-success");
                            theSpan.addClass("glyphicon-ban-circle");
                            theSpan.attr("dataAlreadyMember",false);
                            var theDd = $("dd#countOfGroups");
                            var countOfGroups = theDd.html();
                            theDd.html(--countOfGroups);
                        },
                        error: function(e) {
                            alert('Error ' + e);
                        }
                    });
                } else {
                    $.ajax({
                        type: "POST",
                        url: "${addToGroupUrl}",
                        data: { userId: userId },
                        success: function(response) {
                            var theSpan = $("span#" + userId);
                            theSpan.removeClass("glyphicon-ban-circle");
                            theSpan.addClass("glyphicon-user");
                            theSpan.addClass("text-success");
                            theSpan.attr("dataAlreadyMember",true);
                            var theDd = $("dd#countOfGroups");
                            var countOfGroups = theDd.html();
                            theDd.html(++countOfGroups);
                        },
                        error: function(e) {
                            alert('Error ' + e);
                        }
                    });
                }
                
            }
        </script>
    </body>
</html>
