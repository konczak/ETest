<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="button" tagdir="/WEB-INF/tags/button"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/page/"%>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/closedQuestion.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="closedQuestion.new.title"/></title>
    </head>
    <body>
        <page:header text="closedQuestion.new.header"/>
        <form:form method="POST" commandName="closedQuestion" role="form" enctype="multipart/form-data">
            <form:errors path="*" cssClass="info-danger" element="div" />

            <c:set var="opaqueDivCssClass" value="form-group"/>
            <c:set var="newScoreErrors"><form:errors path="categoryId"/></c:set>
            <c:if test="${not empty newScoreErrors}">
                <c:set var="opaqueDivCssClass" value="form-group has-error"/>
            </c:if>

            <div class="${opaqueDivCssClass}">
                <form:label path="categoryId">
                    <spring:message code="category.name.label"/>
                    <util:requiredField required="true"/>
                </form:label>

                <spring:message var="categoryEntityName" code="category.preview.title" htmlEscape="false"/>
                <spring:message var="chooseCategoryName" code="input_select_default" arguments="${categoryEntityName}" htmlEscape="false"/>

                <form:errors path="categoryId" cssClass="alert alert-danger" element="div"/>
                <form:select path="categoryId" cssClass="form-control">
                    <form:option value="0" label="--- ${chooseCategoryName} ---" />
                    <form:options items="${categoryList}" />
                </form:select>
            </div>
            
            <myForm:input object="closedQuestion" fieldName="question" required="true"/>
            <myForm:file object="closedQuestion" fieldName="multipartFile" acceptedFiles="image/*"/>

            <button:submit/>
            <button:cancel/>
        </form:form>
        <script src="${activeJsUrl}"></script>
    </body>
</html>
