<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="button" tagdir="/WEB-INF/tags/button"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/page/"%>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/userGroup.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="userGroup.new.title"/></title>
    </head>
    <body>
        <page:header text="userGroup.new.header"/>

        <form:form method="POST" commandName="userGroup" role="form">
            <form:errors path="*" cssClass="info-danger" element="div" />
            <div class="row">
                <div class="col-xs-1">
                    <label><spring:message code="userGroup.class.label"/></label>
                </div>
                <div class="col-xs-1">
                    <form:input path="classPrefix" cssClass="form-control"/>
                </div>
                <div class="col-xs-1">
                    <form:select path="classNumber" cssClass="form-control">
                        <form:options items="${classNumberList}" />
                    </form:select>
                </div>
                <div class="col-xs-1">
                    <form:select path="classLetter" cssClass="form-control">
                        <form:options items="${classLetterList}" />
                    </form:select>
                </div>
                <div class="col-xs-2">
                    <form:select path="classYear" cssClass="form-control">
                        <form:options items="${classYearList}" />
                    </form:select>
                </div>
                <div class="col-xs-3">
                    <button type="button" class="btn btn-labeled btn-success" onclick="generate();">
                        <span class="btn-label">
                            <i class="glyphicon glyphicon-refresh"></i>
                        </span>
                        <spring:message code="userGroup.generate.button"/>
                    </button>
                </div>
            </div>
            <br>

            <myForm:input object="userGroup" fieldName="title" required="true"/>
            <button:submit/>
            <button:cancel/>
        </form:form>
        <script src="${activeJsUrl}"></script>
        <script type="text/javascript">
            function generate() {
                $("input#title").val(
                        $("input#classPrefix").val()
                        + " " + $("select#classNumber").val()
                        + $("select#classLetter").val()
                        + " " + $("select#classYear").val()
                        );
            }
        </script>
    </body>
</html>
