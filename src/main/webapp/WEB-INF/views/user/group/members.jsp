<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url var="activeJsUrl" value="/resources/js/activeLink/userGroup.js" />
<c:url var="dataTablesCssUrl" value="/resources/css/dataTables.css" />
<c:url var="dataTablesJsUrl" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesBootstrapPagingJsUrl" value="/resources/js/jquery.dataTables.bootstrap-paging.js" />
<spring:url var="addUserAsMemberUrl" value="/user/group/{id}/members/add">
    <spring:param name="id" value="${userGroup.id}"/>
</spring:url>
<spring:url var="removeUserFromMembersUrl" value="/user/group/{id}/members/delete">
    <spring:param name="id" value="${userGroup.id}"/>
</spring:url>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="userGroup.members.title"/></title>
        <link type="text/css" href="${dataTablesCssUrl}" rel="stylesheet">
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="userGroup.members.header"/></h1>
        </div>

        <dl class="dl-horizontal">
            <dt><spring:message code="entity_id"/></dt>
            <dd>${userGroup.id}</dd>
            <dt><spring:message code="userGroup.title.label"/></dt>
            <dd>${userGroup.title}</dd>
            <dt><spring:message code="userGroup.membersCount.label"/></dt>
            <dd id="countOfMembers">${userGroup.countOfMembers}</dd>
        </dl>

        <div class="page-header">
            <h3>
                <spring:message code="user.list.header"/>
            </h3>
        </div>
        <table id="dataTables" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>#</th>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="user.lastname.label"/></th>
                    <th><spring:message code="user.firstname.label"/></th>
                    <th><spring:message code="userGroup.membersAlreadyIn.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${userGroup.members}" var="entry" varStatus="status">
                    <c:choose>
                        <c:when test="${entry.value.alreadyIn}">
                            <c:set var="memberColor" value="glyphicon-user text-primary"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="memberColor" value="glyphicon-ban-circle"/>
                        </c:otherwise>
                    </c:choose>
                    <tr>
                        <td>${status.count}</td>
                        <td>${entry.key}</td>
                        <td>${entry.value.lastname}</td>
                        <td>${entry.value.firstname}</td>
                        <td>
                            <button type="button" class="btn btn-default" onclick="doAjax(${entry.key})">
                                <span id="${entry.key}" class="glyphicon ${memberColor}" dataAlreadyIn="${entry.value.alreadyIn}"></span>
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
                var alreadyIn = theSpan.attr("dataAlreadyIn");
                if(alreadyIn === 'true') {
                    $.ajax({
                        type: "POST",
                        url: "${removeUserFromMembersUrl}",
                        data: { userId: userId },
                        success: function(response) {
                            var theSpan = $("span#" + userId);
                            theSpan.removeClass("glyphicon-user");
                            theSpan.removeClass("text-primary");
                            theSpan.addClass("glyphicon-ban-circle");
                            theSpan.attr("dataAlreadyIn",false);
                            var theDd = $("dd#countOfMembers");
                            var countOfMembers = theDd.html();
                            theDd.html(--countOfMembers);
                        },
                        error: function(e) {
                            alert('Error ' + e);
                        }
                    });
                } else {
                    $.ajax({
                        type: "POST",
                        url: "${addUserAsMemberUrl}",
                        data: { userId: userId },
                        success: function(response) {
                            var theSpan = $("span#" + userId);
                            theSpan.removeClass("glyphicon-ban-circle");
                            theSpan.addClass("glyphicon-user");
                            theSpan.addClass("text-primary");
                            theSpan.attr("dataAlreadyIn",true);
                            var theDd = $("dd#countOfMembers");
                            var countOfMembers = theDd.html();
                            theDd.html(++countOfMembers);
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
