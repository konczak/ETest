<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title><spring:message code="error_systemexception_title"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1>
                <span class="label label-danger">
                    ${systemException.errorCode.class.simpleName}.${systemException.errorCode}.${systemException.errorCode.number}
                </span>
            </h1>
        </div>
        <div class="panel panel-warning">
            <div class="panel-heading">Error description</div>
            <div class="panel-body">
                <spring:message code="error_errorCode_${systemException.errorCode.class.simpleName}_${systemException.errorCode}_description" />
            </div>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">Values</div>
            <div class="panel-body">
            <dl class="dl-horizontal">
                <c:forEach items="${systemException.properties}" var="entry" varStatus="status">
                    <dt>${entry.key}</dt>
                    <dd>${entry.value}</dd>
                </c:forEach>
            </dl>
            </div>
        </div>

        <div class="panel panel-danger">
            <div class="panel-heading"><spring:message code="exception_stacktrace" /></div>
            <div class="panel-body">
                            <c:forEach items="${systemException.stackTrace}" var="trace">
                ${trace}<br>
            </c:forEach>
            </div>
        </div>
    </body>
</html>