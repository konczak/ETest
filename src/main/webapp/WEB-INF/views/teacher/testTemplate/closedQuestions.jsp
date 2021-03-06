<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="manage" tagdir="/WEB-INF/tags/manage/"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/page/"%>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/testTemplate.js" />
<spring:url var="dataTablesCssUrl" value="/resources/css/dataTables.css" />
<spring:url var="dataTablesJsUrl" value="/resources/js/jquery.dataTables-1.9.4/jquery.dataTables.js" />
<spring:url var="dataTablesBootstrapPagingJsUrl" value="/resources/js/jquery.dataTables.bootstrap-paging-0.0.1/jquery.dataTables.bootstrap-paging.js" />
<spring:url var="addClosedQuestionToTestTemplateUrl" value="/teacher/testTemplate/{id}/closedQuestions/add">
    <spring:param name="id" value="${testTemplate.id}"/>
</spring:url>
<spring:url var="removeClosedQuestionFromTestTemplateUrl" value="/teacher/testTemplate/{id}/closedQuestions/delete">
    <spring:param name="id" value="${testTemplate.id}"/>
</spring:url>
<spring:url var="changeClosedQuestionMandatoryStatusUrl" value="/teacher/testTemplate/{id}/closedQuestions/mandatory">
    <spring:param name="id" value="${testTemplate.id}"/>
</spring:url>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="testTemplate.closedQuestions.title"/></title>
        <link type="text/css" href="${dataTablesCssUrl}" rel="stylesheet">
    </head>
    <body>
        <page:header text="testTemplate.closedQuestions.header"/>

        <dl class="dl-horizontal">
            <dt><spring:message code="entity_id"/></dt>
            <dd>${testTemplate.id}</dd>
            <dt><spring:message code="testTemplate.subject.label"/></dt>
            <dd>${testTemplate.subject}</dd>
            <dt><spring:message code="testTemplate.countOfClosedQuestionsAlreadyIn.label"/></dt>
            <dd id="countOfClosedQuestionsAlreadyIn">${testTemplate.countOfClosedQuestionsAlreadyIn}</dd>
            <dt><spring:message code="testTemplate.countOfClosedQuestionsMandatory.label"/></dt>
            <dd id="countOfClosedQuestionsMandatory">${testTemplate.countOfClosedQuestionsMandatory}</dd>
        </dl>

        <div class="page-header">
            <h3>
                <spring:message code="closedQuestion.list.header"/>
            </h3>
        </div>
        <table id="dataTables" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="closedQuestion.question.label"/></th>
                    <th><spring:message code="testTemplate.closedQuestionAlreadyIn.label"/></th>
                    <th><spring:message code="testTemplate.closedQuestionMandatory.label"/></th>
                    <th><spring:message code="testTemplate.manage.label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${testTemplate.closedQuestions}" var="entry" varStatus="status">
                    <spring:url var="closedQuestionPreviewUrl" value="/teacher/closedQuestion/{id}">
                        <spring:param name="id" value="${entry.key}"/>
                    </spring:url>
                    <c:choose>
                        <c:when test="${entry.value.alreadyIn}">
                            <c:set var="closedQuestionAlreadyInCssClass" value="glyphicon-check text-success"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="closedQuestionAlreadyInCssClass" value="glyphicon-unchecked"/>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${entry.value.mandatory}">
                            <c:set var="closedQuestionMandatoryCssClass" value="glyphicon-check text-success"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="closedQuestionMandatoryCssClass" value="glyphicon-unchecked"/>
                        </c:otherwise>
                    </c:choose>
                    <tr>
                        <td>${entry.key}</td>
                        <td>${entry.value.question}</td>
                        <td>
                            <button type="button" class="btn btn-default" onclick="doAjax(${entry.key})">
                                <span id="alreadyIn_${entry.key}" class="glyphicon ${closedQuestionAlreadyInCssClass}" dataAlreadyIn="${entry.value.alreadyIn}"></span>
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-default" onclick="doAjaxMandatory(${entry.key})">
                                <span id="mandatory_${entry.key}" class="glyphicon ${closedQuestionMandatoryCssClass}" dataMandatory="${entry.value.mandatory}"></span>
                            </button>
                                <c:if  test="${not entry.value.alreadyIn}">
                                    <script type="text/javascript">
                                        $("span#mandatory_" + ${entry.key}).parent().hide();
                                    </script>
                                </c:if>
                        </td>
                        <td>
                            <manage:preview url="${closedQuestionPreviewUrl}"/>
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
            function setAlreadyInToPositive(closedQuestionId) {
                var theSpan = $("span#alreadyIn_" + closedQuestionId);
                theSpan.removeClass("glyphicon-unchecked");
                theSpan.addClass("glyphicon-check");
                theSpan.addClass("text-success");
                theSpan.attr("dataAlreadyIn",true);
                var theDd = $("dd#countOfClosedQuestionsAlreadyIn");
                var countOfClosedQuestionsAlreadyIn = theDd.html();
                theDd.html(++countOfClosedQuestionsAlreadyIn);
                $("span#mandatory_" + closedQuestionId).parent().fadeIn();
            }
            function setAlreadyInToNegative(closedQuestionId) {
                var theSpan = $("span#alreadyIn_" + closedQuestionId);
                theSpan.removeClass("glyphicon-check");
                theSpan.removeClass("text-success");
                theSpan.addClass("glyphicon-unchecked");
                theSpan.attr("dataAlreadyIn",false);
                var theDd = $("dd#countOfClosedQuestionsAlreadyIn");
                var countOfClosedQuestionsAlreadyIn = theDd.html();
                theDd.html(--countOfClosedQuestionsAlreadyIn);
                $("span#mandatory_" + closedQuestionId).parent().fadeOut();
            }
            function setMandatoryToPositive(closedQuestionId) {
                var theSpan = $("span#mandatory_" + closedQuestionId);
                theSpan.removeClass("glyphicon-unchecked");
                theSpan.addClass("glyphicon-check");
                theSpan.addClass("text-success");
                theSpan.attr("dataMandatory",true);
                var theDd = $("dd#countOfClosedQuestionsMandatory");
                var countOfClosedQuestionsMandatory = theDd.html();
                theDd.html(++countOfClosedQuestionsMandatory);
            }
            function setMandatoryToNegative(closedQuestionId) {
                var theSpan = $("span#mandatory_" + closedQuestionId);
                theSpan.removeClass("glyphicon-check");
                theSpan.removeClass("text-success");
                theSpan.addClass("glyphicon-unchecked");
                theSpan.attr("dataMandatory",false);
                var theDd = $("dd#countOfClosedQuestionsMandatory");
                var countOfClosedQuestionsMandatory = theDd.html();
                theDd.html(--countOfClosedQuestionsMandatory);
            }
            function doAjax(closedQuestionId) {
                var theSpan = $("span#alreadyIn_" + closedQuestionId);
                var alreadyIn = theSpan.attr("dataAlreadyIn");
                if(alreadyIn === 'true') {
                    $.ajax({
                        type: "POST",
                        url: "${removeClosedQuestionFromTestTemplateUrl}",
                        data: { closedQuestionId: closedQuestionId },
                        success: function(response) {
                            setMandatoryToNegative(closedQuestionId);
                            setAlreadyInToNegative(closedQuestionId);
                        },
                        error: function(e) {
                            alert('Error ' + e);
                        }
                    });
                } else {
                    $.ajax({
                        type: "POST",
                        url: "${addClosedQuestionToTestTemplateUrl}",
                        data: { closedQuestionId: closedQuestionId },
                        success: function(response) {
                            setAlreadyInToPositive(closedQuestionId);
                        },
                        error: function(e) {
                            alert('Error ' + e);
                        }
                    });
                }
                
            }
            
            function doAjaxMandatory(closedQuestionId) {
                var theSpan = $("span#mandatory_" + closedQuestionId);
                var alreadyIn = theSpan.attr("dataMandatory");
                if(alreadyIn === 'true') {
                    $.ajax({
                        type: "POST",
                        url: "${changeClosedQuestionMandatoryStatusUrl}",
                        data: { closedQuestionId: closedQuestionId, mandatory: false },
                        success: function(response) {
                            setMandatoryToNegative(closedQuestionId);
                        },
                        error: function(e) {
                            alert('Error ' + e);
                        }
                    });
                } else {
                    $.ajax({
                        type: "POST",
                        url: "${changeClosedQuestionMandatoryStatusUrl}",
                        data: { closedQuestionId: closedQuestionId, mandatory: true },
                        success: function(response) {
                            setMandatoryToPositive(closedQuestionId);
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
