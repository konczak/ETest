<div id="newModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body">
                <p id="categoryId"></p>
                <p id="categoryName"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <i class="glyphicon glyphicon-asterisk spin"></i>
                    Close
                </button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>
    $("a.text-success").click(function() {
        var categoryId = $(this).closest("li").attr("data-categoryId");
        var categoryName = $(this).closest("li").attr("data-categoryName");
        $("#categoryId").html(categoryId);
        $("#categoryName").html(categoryName);
        $('#newModal').modal();

        return false;
    });
</script>