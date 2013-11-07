<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <title>User form</title>
    </head>
    <body>
        <h1>User form!</h1>
        <div class="project-template">
            <form:form method="POST" commandName="userRegistration" role="form">
                <form:errors path="*" cssClass="info-danger" element="div" />

                <myForm:input object="userRegistration" fieldName="email" required="true"/>
                <myForm:password object="userRegistration" fieldName="password" required="true"/>
                <button type="submit" class="btn btn-default">Submit</button>
            </form:form>
        </div>
    </body>
</html>
