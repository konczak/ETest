<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="manage" tagdir="/WEB-INF/tags/manage/"%>
<%@taglib prefix="tree" tagdir="/WEB-INF/tags/tree/"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/page/"%>

<spring:url var="knockoutJsUrl" value="/resources/js/knockout-3.0.0/knockout.js" />
<spring:url var="activeJsUrl" value="/resources/js/activeLink/category.js" />

<spring:url var="readUrl" value="/admin/category/read"/>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="category.list.title"/></title>
    </head>
    <body>
        <page:header text="category.list.header"/>
        <div class="row">
            <div class="col-lg-4 col-lg-offset-2">
                <ul class="list-unstyled">
                    <c:forEach items="${categories}" var="category">
                        <li data-categoryId="${category.id}" data-categoryName="${category.name}">
                            <tree:category category="${category}"/>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-lg-offset-2">
                <ul class="list-unstyled" data-bind="template: { name: 'categories-template-new', foreach: categories() }">
                    <li>
                        <span class="label label-primary" data-bind="text: name"></span>
                    </li>
                </ul>
            </div>
        </div>
        <script id="categories-template-new"  type="text/html">
            <li>
                <span class="label label-primary" data-bind="text: name"></span>
                <div class="pull-right">
                    <span class="badge alert-warning" data-bind="text: countOfClosedQuestions"></span>
                </div>
                <ul data-bind="template: { name: 'categories-template-new', foreach: categories() }"></ul>
            </li>
        </script>

        
        <div data-bind="template: { name: 'categories-template', foreach: categories() }"></div>
        <script id="categories-template"  type="text/html">
            <label data-bind="attr: { 'for': categoryId }">Name:</label>
            <input data-bind="attr: { 'id': categoryId }, value: name" />
            <div data-bind="template: { name: 'categories-template', foreach: categories() }"></div>
        </script>

        <script src="${activeJsUrl}"></script>
        <script src="${knockoutJsUrl}"></script>
        <script>
        function Category(id, name, parentId, countOfClosedQuestions) {
            this.categoryId = id;
            this.name = name;
            this.parentId = parentId;
            this.countOfClosedQuestions = countOfClosedQuestions;
            this.categories = ko.observableArray([]);
        }

        function CategoryViewModel() {
            var self = this;
            self.categories = ko.observableArray([]);

        
           $.ajax({
                url: '${readUrl}',
                dataType: 'json',
                success: function (data) {
//                    alert(JSON.stringify(data));
                    initialiseModel(self, data);
                }, 
                error: function(e) {
                    alert('Error ' + JSON.stringify(e));
                }
            });
        }

        function initialiseModel(model, jsonModel) {
            parsedModel = jsonModel;
//            alert(JSON.stringify(parsedModel));
            addCategories(model, parsedModel.categories);
            console.log(model);
        }

        function addCategories(topModel, categories) {
//        alert("kuku");
            if (typeof categories === "undefined") {
                return;
            }

            for (var i = 0; i < categories.length; i++) {
                var category = categories[i];
                var categoryModel = new Category(category.id, category.name, category.parentId, category.countOfClosedQuestions);
                topModel.categories.push(categoryModel);
                addCategories(categoryModel, category.categories);
            }
        }

        ko.applyBindings(new CategoryViewModel());
        </script>
    </body>
</html>
