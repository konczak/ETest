<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <title><spring:message code="welcome_titlepane" arguments="ETest"/></title>
        <meta name='description' content='A simple page'>
    </head>
    <body>
        <div class="page-header">
            <h1>
                <spring:message code="welcome_h3" arguments="ETest"/>
            </h1>
        </div>
        <div class="jumbotron">
            <spring:message code="welcome_text"/>
        </div>
<!--        <p>Message : ${message}</p>
        <p>Username : ${username}</p>-->
    </body>
</html>