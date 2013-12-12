<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="util" tagdir="/WEB-INF/tags/util"%>

<c:url var="datarangepcikerCssUrl" value="/resources/css/daterangepicker-bs3.css" />
<spring:url var="momentJsUrl" value="/resources/js/moment-with-langs.min.js" />
<spring:url var="datarangepickerJsUrl" value="/resources/js/daterangepicker.js" />
<spring:url var="activeJsUrl" value="/resources/js/activeLink/exam.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="exam.new.title"/></title>
        <link type="text/css" href="${datarangepcikerCssUrl}" rel="stylesheet">
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="exam.new.header"/></h1>
        </div>

        <div class="project-template">
            <form:form method="POST" commandName="exam" role="form">
                <form:errors path="*" cssClass="info-danger" element="div" />

                <c:set var="opaqueDivCssClass" value="form-group"/>
                <c:set var="newScoreErrors"><form:errors path="testTemplateId"/></c:set>
                <c:if test="${not empty newScoreErrors}">
                    <c:set var="opaqueDivCssClass" value="form-group has-error"/>
                </c:if>

                <div class="${opaqueDivCssClass}">
                    <form:label path="testTemplateId">
                        <spring:message code="testTemplate.subject.label"/>
                        <util:requiredField required="true"/>
                    </form:label>

                    <spring:message var="testTemplateEntityName" code="testTemplate.preview.title" htmlEscape="false"/>
                    <spring:message var="chooseTestTemplateSubject" code="input_select_default" arguments="${testTemplateEntityName}" htmlEscape="false"/>
                    
                    <form:errors path="testTemplateId" cssClass="alert alert-danger" element="div"/>
                    <form:select path="testTemplateId" cssClass="form-control">
                        <form:option value="0" label="--- ${chooseTestTemplateSubject} ---" />
                        <form:options items="${testTemplateList}" />
                    </form:select>
                </div>

                <c:set var="opaqueDivCssClass" value="form-group"/>
                <c:set var="newScoreErrors"><form:errors path="userGroupId"/></c:set>
                <c:if test="${not empty newScoreErrors}">
                    <c:set var="opaqueDivCssClass" value="form-group has-error"/>
                </c:if>

                <div class="${opaqueDivCssClass}">
                    <form:label path="userGroupId">
                        <spring:message code="userGroup.title.label"/>
                    </form:label>
                    <util:requiredField required="true"/>

                    <spring:message var="userGroupEntityName" code="userGroup.preview.title" htmlEscape="false"/>
                    <spring:message var="chooseUserGroupTitle" code="input_select_default" arguments="${userGroupEntityName}" htmlEscape="false"/>
                    
                    <form:errors path="userGroupId" cssClass="alert alert-danger" element="div"/>
                    <form:select path="userGroupId" cssClass="form-control">
                        <form:option value="0" label="--- ${chooseUserGroupTitle} ---" />
                        <form:options items="${userGroupList}" />
                    </form:select>
                </div>

                <myForm:input object="exam" fieldName="suffix"/>

                <c:set var="opaqueDivCssClass" value="form-group"/>
                <c:set var="newScoreErrors"><form:errors path="duration"/></c:set>
                <c:if test="${not empty newScoreErrors}">
                    <c:set var="opaqueDivCssClass" value="form-group has-error"/>
                </c:if>

                <div class="${opaqueDivCssClass}">
                    <form:label path="duration">
                        <spring:message code="exam.duration.label"/>
                        <util:requiredField required="true"/>
                    </form:label>
                    <form:errors path="duration" cssClass="alert alert-danger" element="div"/>
                    <div class="input-group">
                        <span class="input-group-addon glyphicon glyphicon glyphicon-calendar"></span>
                        <input type="text" id="duration" name="duration" class="form-control" value="${exam.duration}">
                    </div>
                </div>                

                <button type="submit" class="btn btn-primary" name="add" value="add">
                    <spring:message code="button_submit"/>
                </button>
                <button type="submit" class="btn btn-default" name="cancel" value="cancel">
                    <spring:message code="button_cancel"/>
                </button>
            </form:form>
        </div>

        <script src="${momentJsUrl}"></script>
        <script src="${datarangepickerJsUrl}"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                moment.lang("pl");
                $("input#duration").daterangepicker({
                    minDate: moment().hour(0).minute(0),
                    startDate: moment().add("day", 1).minute(0),
                    endDate: moment().add("day", 1).minute(45),
                    timePicker: true,
                    timePickerIncrement: 5,
                    format: "YYYY-MM-DD HH:mm",
                    timePicker12Hour: false,
                    locale: {
                        applyLabel: '<spring:message code="datarangepicker.applyLabel"/>',
                        cancelLabel: '<spring:message code="datarangepicker.cancelLabel"/>',
                        fromLabel: '<spring:message code="datarangepicker.fromLabel"/>',
                        toLabel: '<spring:message code="datarangepicker.toLabel"/>',
                        firstDay: 1
                    }
                });
            });
        </script>
        <script src="${activeJsUrl}"></script>
    </body>
</html>
