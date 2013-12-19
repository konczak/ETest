<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<spring:url var="activeJsUrl" value="/resources/js/activeLink/userExam.js" />
<spring:url var="momentJsUrl" value="/resources/js/moment-with-langs.min.js" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="userExam.sheet.title" arguments="${userExam.testTemplateSubject}, ${userExam.titleSuffix}"/></title>
    </head>
    <body>
        <div class="page-header">
            <h1><spring:message code="userExam.sheet.header" arguments="${userExam.testTemplateSubject}, ${userExam.titleSuffix}"/></h1>
        </div>

        <div class="row">
            <div class="col-md-3 col-md-offset-2 alert alert-info text-center">
                <spring:message code="userExam.notActiveYet.text"/>
                <strong>
                    <joda:format value="${userExam.activeFrom}" style="MM" />
                </strong> 
            </div>
            <c:if test="${not empty userExam.activeInSeconds}">
                <div class="col-md-3 col-md-offset-1 alert alert-info text-center">
                    <spring:message code="userExam.activeInSeconds.text"/><br>
                    <strong id="counter">--:--:--</strong>
                </div>
                <script src="${momentJsUrl}"></script>
                <script>
                    var diffTime = ${userExam.activeInSeconds};
                    var duration = moment.duration(diffTime, 'seconds');
                    var interval = 1;
                    function formatter(numericValue) {
                        var result = numericValue;
                        if (numericValue < 10) {
                            result = "0" + result;
                        }
                        return result;
                    }
                    setInterval(function() {
                        duration = moment.duration(duration.asSeconds() - interval, 'seconds');
                        if (duration.asSeconds() === 0) {
                            location.reload();
                        }
                        $('#counter').text(formatter(duration.hours()) + ':' 
                                + formatter(duration.minutes()) + ':' 
                                + formatter(duration.seconds()));
                    }, 1000);
                </script>
            </c:if>
        </div>
        <script src="${activeJsUrl}"></script>
    </body>
</html>
