<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tree" tagdir="/WEB-INF/tags/tree/"%>
<%@taglib prefix="link" tagdir="/WEB-INF/tags/link/" %>
<%@attribute name="category" type="pl.konczak.etest.dto.admin.category.CategoryDTO" required="true" %>

<span class="label label-primary">${category.name}</span>
<div class="pull-right">
    <span class="badge alert-warning">${category.countOfClosedQuestions}</span>
    <link:add url=""/>
    <link:edit url=""/>
</div>
<ul>
    <c:forEach items="${category.childrens}" var="child">
        <li>
            <tree:category category="${child}"/>
        </li>
    </c:forEach>
</ul>
