<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:message var="applciationName" code="application_name"/>

<html>
    <head>
        <title><spring:message code="welcome_titlepane" arguments="${applciationName}"/></title>
        <meta name="description" content="A simple page">
    </head>
    <body>
        <div class="project-template">
            <div class="jumbotron">
                <h3>
                    <spring:message code="welcome_h3" arguments="${applciationName}"/>
                </h3>
                <p>
                    <spring:message code="welcome_text"/>
                </p>
            </div>
        </div>
    </body>
</html>