<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="language" required="true"%>
<%@attribute name="disabled" type="java.lang.Boolean" required="false"%>

<c:set var="enabled" value="false"/>
<c:if test="${not disabled}">
    <c:set var="enabled" value="true"/>
</c:if>

<c:url var="blankGifUrl" value="/resources/images/blank.gif"/>

<spring:url var="actualLinkAndLanguageChange" value="">
    <c:forEach items="${pageContext.request.parameterNames}" var="name">
        <c:if test="${name!='lang'}">
            <spring:param name="${name}" value="${pageContext.request.parameterMap[name][0]}"/>
        </c:if>
    </c:forEach>
    <spring:param name="lang" value="${language}"/>
</spring:url>

<c:set var="languageUpperCase" value="${fn:toUpperCase(language)}"/>

<a href="${actualLinkAndLanguageChange}" onclick="return ${enabled};">
    <img src="${blankGifUrl}" class="flag lang-${language}" alt="${languageUpperCase}" /> ${languageUpperCase}
</a>