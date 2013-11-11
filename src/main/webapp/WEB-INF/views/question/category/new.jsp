<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="questionCategory.new.title"/></title>
    </head>
    <body>
        <h1><spring:message code="questionCategory.new.header"/></h1>
        <div class="project-template">
            <form:form method="POST" commandName="questionCategory" role="form">
                <form:errors path="*" cssClass="info-danger" element="div" />

                <myForm:input object="questionCategory" fieldName="title" required="true"/>
                <button type="submit" class="btn btn-primary" name="add" value="add">
                    <spring:message code="button_submit"/>
                </button>
                <button type="submit" class="btn btn-default" name="cancel" value="cancel">
                    <spring:message code="button_cancel"/>
                </button>
            </form:form>
        </div>
    </body>
</html>
