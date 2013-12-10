<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message var="authorEmail" code="global.author.email"/>
<spring:message var="ownerEmail" code="global.owner.email"/>
<spring:url var="aboutUsUrl" value="/about"/>
<spring:url var="contactUrl" value="/contact"/>

<footer>
    <div class="container">

        <p>
            <spring:message code="application_name"/>&nbsp;<spring:message code="app_version"/>
        </p>
        <ul class="list-inline">
            <li>
                <a href="${aboutUsUrl}">
                    <spring:message code="global.aboutUs.title"/>
                </a>
            </li>
            <li>-</li>
            <li>
                <a href="${contactUrl}">
                    <spring:message code="global.contact.title"/>
                </a>
            </li>
        </ul>
        <p>
            Copyright &copy; 2013 
            <a href="mailto:${authorEmail}">
                <spring:message code="global.author"/>
            </a>
            &AMP;
            <a href="mailto:${ownerEmail}">
                <spring:message code="global.owner"/>
            </a>
        </p>
    </div>
</footer>