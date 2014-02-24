<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/page/"%>

<html>
    <head>
        <title><spring:message code="welcome_titlepane" arguments="ETest"/></title>
        <meta name='description' content='A simple page'>
    </head>
    <body>
        <page:header text="welcome_h3" textArguments="ETest"/>

        <div class="jumbotron">
            <spring:message code="welcome_text"/>
        </div>
    </body>
</html>