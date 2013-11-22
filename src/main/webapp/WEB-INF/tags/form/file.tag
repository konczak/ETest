<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@attribute name="object" required="true" %>
<%@attribute name="fieldName" required="true" %>
<%@attribute name="acceptedFiles" required="true" %>

<c:set var="opaqueDivCssClass" value="form-group"/>
<c:set var="newScoreErrors"><form:errors path="${fieldName}"/></c:set>
<c:if test="${not empty newScoreErrors}">
    <c:set var="opaqueDivCssClass" value="form-group has-error"/>
</c:if>
<spring:message var="label" code="${object}.${fieldName}.label" htmlEscape="false"/>

<div class="${opaqueDivCssClass}">
    <form:label path="${fieldName}">
        <spring:message code="${object}.${fieldName}.label"/>
    </form:label>
    <form:errors path="${fieldName}" cssClass="alert alert-danger" element="div"/>
    <form:input object="${object}" path="${fieldName}" type="file" name="some" accept="${acceptedFiles}"/>
    <p class="help-block">Example block-level help text here.</p>
</div>

