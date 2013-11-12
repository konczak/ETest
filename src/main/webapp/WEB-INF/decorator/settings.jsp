<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${pageContext.response.locale.language}"/>

<!DOCTYPE html>
<html lang="${language}">
    <head>
        <title><sitemesh:write property="title"/></title>
        <sitemesh:write property="head"/>
    </head>
<body>
    <div class="project-template">
        <div class="row">
            <div class="col-md-3">
                <jsp:include page="../layout/userSettingsMenu.jsp"/>
            </div>
            <div class="col-md-9">
                <sitemesh:write property="body"/>
            </div>
        </div>
    </div>
</body>
</html>
