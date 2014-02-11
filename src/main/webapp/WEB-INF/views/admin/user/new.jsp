<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="user.new.title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="user.new.header"/></h1>
        </div>
        <div class="project-template">
            <form:form method="POST" commandName="user" role="form">
                <form:errors path="*" cssClass="info-danger" element="div" />

                <myForm:input object="user" fieldName="email" required="true"/>
                <myForm:password object="user" fieldName="password" required="true"/>
                <myForm:password object="user" fieldName="passwordConfirm" required="true"/>
                <myForm:input object="user" fieldName="firstname" required="true"/>
                <myForm:input object="user" fieldName="lastname" required="true"/>
                <button type="submit" class="btn btn-primary" name="register" value="register">
                    <spring:message code="button_submit"/>
                </button>
                <button type="submit" class="btn btn-default" name="cancel" value="cancel">
                    <spring:message code="button_cancel"/>
                </button>
            </form:form>
        </div>
    </body>
</html>