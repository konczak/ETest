<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="util" tagdir="/WEB-INF/tags/util"%>

<c:set var="language" value="${pageContext.response.locale.language}"/>
<spring:url var="blankGifUrl" value="/resources/images/blank.gif"/>

<li>
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        <img src="${blankGifUrl}" class="flag lang-${language}" alt="${language}" />
        <b class="caret"></b>
    </a>
    <ul class="dropdown-menu">
        <li class="disabled"><util:language language="zh" disabled="true"/></li>
        <li class="disabled"><util:language language="de" disabled="true"/></li>
        <li class="disabled"><util:language language="es" disabled="true"/></li>
        <li class="disabled"><util:language language="fr" disabled="true"/></li>
        <li><util:language language="en"/></li>
        <li class="disabled"><util:language language="it" disabled="true"/></li>
        <li class="disabled"><util:language language="ja" disabled="true"/></li>
        <li class="disabled"><util:language language="ko" disabled="true"/></li>
        <li><util:language language="pl"/></li>
        <li class="disabled"><util:language language="ru" disabled="true"/></li>
        <li class="disabled"><util:language language="sv" disabled="true"/></li>
        <li class="disabled"><util:language language="tr" disabled="true"/></li>
    </ul>
</li>