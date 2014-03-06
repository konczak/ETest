<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="myForm" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="button" tagdir="/WEB-INF/tags/button"%>

<spring:message var="categoryNamePlaceholder" code="category.name.placeholder"/>
<spring:url var="newLink" value="/admin/category/new"/>

<div id="newModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 id="parentName" class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="inputName" class="col-sm-2 control-label">Kategoria</label>
                        <div id="newCategory" class="col-sm-10">
                            <input type="hidden" id="parentId">
                            <input type="text" class="form-control" id="name" placeholder="${categoryNamePlaceholder}" data-toggle="tooltip" title="Some tooltip text!">
                        </div>
                    </div>
                </form>
<!--                <div id="errors" class="alert alert-danger">

                </div>-->

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    Close
                </button>
                <button type="button" class="btn btn-primary" onclick="saveNewCategory();">
                    <i class="glyphicon glyphicon-asterisk"></i>
                    Save changes
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>
                    $("a.text-success").click(function() {
                        var categoryId = $(this).closest("li").attr("data-categoryId");
                        var parentName = $(this).closest("li").attr("data-categoryName");
                        $("#newModal #parentName").html(parentName);
                        $("#newModal #parentId").val(categoryId);
                        $("#newModal #name").val("");
                        $('#newModal').modal();

                        return false;
                    });

                    function saveNewCategory() {
                        $(".glyphicon-asterisk").addClass("spin");
                        var category = new Object();
                        category.parentId = $("#newModal #parentId").val();
                        category.name = $("#newModal #name").val();
                        $.ajax({
                            type: "POST",
                            url: "${newLink}",
                            dataType: "json",
                            contentType: "application/json",
                            data: JSON.stringify(category),
                            success: function(response) {
//                                alert("success");
                                $(".glyphicon-asterisk").removeClass("spin");
                            },
                            error: function(e) {
//                                alert('Error ' + JSON.stringify(e));
                                var tab = e.responseJSON.fieldErrors;
                                var index;
                                for (index = 0; index < tab.length; ++index) {
                                    var field = tab[index].field;
                                    var message = tab[index].message;
//                                    $("#newModal #errors").append(field + ": " + message);
                                    $("#newModal #" + field).attr("title", message);
                                    $("#newModal #" + field).tooltip("show");
                                }
                                $("#newModal .form-group").addClass("has-error");
                                $(".glyphicon-asterisk").removeClass("spin");
                            }
                        });
                    }
</script>