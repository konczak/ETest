<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="util" tagdir="/WEB-INF/tags/util"%>

<spring:message var="authorEmail" code="global.author.email"/>
<spring:message var="ownerEmail" code="global.owner.email"/>
<spring:url var="aboutUsUrl" value="/about"/>
<spring:url var="contactUrl" value="/contact"/>

<footer>
    <div class="container text-left">
        <ul class="list-inline pull-right">
            <li><util:language language="pl"/></li>
            <li><util:language language="en"/></li>
        </ul>
        <p>
            <spring:message code="application_name"/>&nbsp;<spring:message code="app_version"/>
        </p>
        <ul class="list-inline">
            <li>
                <a href="${aboutUsUrl}">
                    <i class="glyphicon glyphicon-info-sign"></i>
                    <spring:message code="global.aboutUs.title"/>
                </a>
            </li>
            <li>-</li>
            <li>
                <a href="${contactUrl}">
                    <i class="glyphicon glyphicon-send"></i>
                    <spring:message code="global.contact.title"/>
                </a>
            </li>
        </ul>
        <p>
            Copyright <i class="glyphicon glyphicon-copyright-mark"></i> 2013 
            <a href="mailto:${authorEmail}">
                <i class="glyphicon glyphicon-envelope"></i>
                <spring:message code="global.author"/>
            </a>
            &AMP;
            <a href="mailto:${ownerEmail}">
                <i class="glyphicon glyphicon-envelope"></i>
                <spring:message code="global.owner"/>
            </a>
        </p>
    </div>
</footer>