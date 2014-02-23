<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tree" tagdir="/WEB-INF/tags/tree/" %>
<%@attribute name="category" type="pl.konczak.etest.dto.admin.category.CategoryDTO" required="true" %>
${category.name}
<span class="badge alert-info">${category.countOfClosedQuestions}</span>
<ul>
    <c:forEach items="${category.childrens}" var="child">
        <li>
            <tree:category category="${child}"/>
        </li>
    </c:forEach>
</ul>
