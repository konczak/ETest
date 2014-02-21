<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="button" tagdir="/WEB-INF/tags/button"%>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="user.new.title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="user.new.header"/></h1>
        </div>
        <form:form method="POST" commandName="user" role="form">
            <form:errors path="*" cssClass="info-danger" element="div" />

            <myForm:input object="user" fieldName="email" required="true"/>
            <myForm:password object="user" fieldName="password" required="true"/>
            <myForm:password object="user" fieldName="passwordConfirm" required="true"/>
            <myForm:input object="user" fieldName="firstname" required="true"/>
            <myForm:input object="user" fieldName="lastname" required="true"/>
            <button:submit name="register" value="register"/>
            <button:cancel/>
        </form:form>
    </body>
</html>
