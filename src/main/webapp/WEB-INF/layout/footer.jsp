<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message var="authorEmail" code="global.author.email"/>
<spring:message var="ownerEmail" code="global.owner.email"/>
<footer>
    <div class="container">
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