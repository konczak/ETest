<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/page/"%>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/userExam.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="userExam.sheet.title" arguments="${testTemplateSubject}, ${examTitleSuffix}"/></title>
    </head>
    <body>
        <page:header text="userExam.sheet.header" textArguments="${testTemplateSubject}, ${examTitleSuffix}"/>

        <div class="row">
            <div class="col-md-4 col-md-offset-4 alert alert-info text-center">
                <spring:message code="userExam.alreadyFinished.text"/>
            </div>
        </div>

        <script src="${activeJsUrl}"></script>
    </body>
</html>
