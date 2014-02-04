<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/exam.js" />
<spring:url var="examCheckUrl" value="/teacher/exam/{id}/check" >
    <spring:param name="id" value="${exam.id}"/>
</spring:url>
<spring:url var="examProlongUrl" value="/teacher/exam/{id}/prolong" >
    <spring:param name="id" value="${exam.id}"/>
</spring:url>
<spring:url var="examTerminateUrl" value="/teacher/exam/{id}/terminate" >
    <spring:param name="id" value="${exam.id}"/>
</spring:url>
<spring:url var="processingImageUrl" value="/resources/images/processing.gif"/>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="exam.preview.title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="exam.preview.header"/></h1>
        </div>

        <dl class="dl-horizontal">
            <dt><spring:message code="entity_id"/></dt>
            <dd>${exam.id}</dd>
            <dt><spring:message code="testTemplate.subject.label"/></dt>
            <dd>${exam.testTampleteSubject}</dd>
            <dt><spring:message code="userGroup.title.label"/></dt>
            <dd>${exam.userGroupTitle}</dd>
            <dt><spring:message code="exam.examiner.label"/></dt>
            <dd>${exam.examinerLastname} ${exam.examinerFirstname}</dd>
            <dt><spring:message code="exam.activeFrom.label"/></dt>
            <dd><joda:format value="${exam.activeFrom}" style="MM" /></dd>
            <dt><spring:message code="exam.activeTo.label"/></dt>
            <dd><joda:format value="${exam.activeTo}" style="MM" /></dd>
            <dt><spring:message code="exam.ended.label"/></dt>
            <dd>
                <c:choose>
                    <c:when test="${exam.ended}">
                        <i class="glyphicon glyphicon-check text-success"></i>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-danger" onclick="runTerminate();">
                            <i class="glyphicon glyphicon-off"></i> <spring:message code="exam.terminate.button"/>
                        </button>
                    </c:otherwise>
                </c:choose>
            </dd>
            <dt><spring:message code="exam.checked.label"/></dt>
            <dd>
                <c:choose>
                    <c:when test="${exam.checked}">
                        <i class="glyphicon glyphicon-check text-success"></i>
                    </c:when>
                    <c:when test="${exam.ended}">
                        <button class="btn btn-primary" onclick="runCheck();">
                            <i class="glyphicon glyphicon-play"></i> <spring:message code="exam.check.button"/>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <i class="glyphicon glyphicon-unchecked"></i>
                    </c:otherwise>
                </c:choose>
            </dd>
            <c:if test="${not exam.ended}">
                <dt>
                Przedluz
                </dt>
                <dd id="prolong">
                    <div class="collapse-group">
                        <button type="button" class="btn btn-info btn-sm pull-left">
                            <i class="glyphicon glyphicon-chevron-right"></i>
                        </button>
                        <div class="collapse pull-left">
                            <button type="button" class="btn btn-primary btn-sm" onclick="runProlong(5);">
                                +5<spring:message code="global.time.minutes.short"/>
                            </button>
                            <button type="button" class="btn btn-primary btn-sm" onclick="runProlong(15);">
                                +15<spring:message code="global.time.minutes.short"/>
                            </button>
                            <button type="button" class="btn btn-primary btn-sm" onclick="runProlong(60);">
                                +1<spring:message code="global.time.hours.short"/>
                            </button>
                            <button type="button" class="btn btn-primary btn-sm" onclick="runProlong(1440);">
                                +24<spring:message code="global.time.hours.short"/>
                            </button>
                        </div>
                    </div>
                </dd>
            </c:if>
        </dl>
        <div class="modal modal-static fade" id="processing-modal" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="text-center">
                            <img src="${processingImageUrl}" class="icon" />
                            <h4>
                                <spring:message code="exam.checkInProgress.label"/>
                                <!--<button type="button" class="close" style="float: none;" data-dismiss="modal" aria-hidden="true">×</button>-->
                            </h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="page-header">
            <h3>
                <spring:message code="exam.members.header"/>
            </h3>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="entity_id"/></th>
                    <th><spring:message code="user.lastname.label"/></th>
                    <th><spring:message code="user.firstname.label"/></th>
                        <c:if test="${exam.checked}">
                        <th><spring:message code="userExam.resultPoints.label"/></th>
                        <th><spring:message code="userExam.maxPoints.label"/></th>
                        <th><spring:message code="userExam.passed.label"/></th>
                        </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${exam.examinedUsers}" var="examinedUser">
                    <tr>
                        <td>${examinedUser.id}</td>
                        <td>${examinedUser.lastname}</td>
                        <td>${examinedUser.firstname}</td>
                        <c:if test="${exam.checked}">
                            <td>${examinedUser.resultPoints}</td>
                            <td>${examinedUser.maxPoints}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${examinedUser.resultPoints > 0.5 * examinedUser.maxPoints}">
                                        <i class="glyphicon glyphicon-ok-sign text-success"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="glyphicon glyphicon-minus-sign text-danger"></i>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <script src="${activeJsUrl}"></script>
        <script>
            function runCheck() {
                $('#processing-modal')
                        .modal();
                $.ajax({
                    type: "POST",
                    url: "${examCheckUrl}",
                    success: function(response) {
                        $('#processing-modal')
                                .modal("hide");
                        location.reload();
                    },
                    error: function(e) {
                        alert('Error ' + e);
                    }
                });
            }

            function runProlong(minutes) {
                $.ajax({
                    type: "POST",
                    url: "${examProlongUrl}",
                    data: {minutes: minutes},
                    success: function(response) {
                        location.reload();
                    },
                    error: function(e) {
                        alert('Error ' + e);
                    }
                });
            }
            
            function runTerminate() {
                $.ajax({
                    type: "POST",
                    url: "${examTerminateUrl}",
                    success: function(response) {
                        location.reload();
                    },
                    error: function(e) {
                        alert('Error ' + e);
                    }
                });
            }

            $('#prolong .btn').on('click', function(e) {
                e.preventDefault();
                var $this = $(this);
                var $collapse = $this.closest('.collapse-group').find('.collapse');
                if ($($collapse).is(":visible")) {
                    $this.find('i').removeClass("glyphicon-chevron-left");
                    $this.find('i').addClass("glyphicon-chevron-right");
                } else {
                    $this.find('i').removeClass("glyphicon-chevron-right");
                    $this.find('i').addClass("glyphicon-chevron-left");
                }
                $collapse.collapse('toggle');
            });
        </script>
    </body>
</html>
