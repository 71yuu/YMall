<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>编辑快递</title>
</head>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="validate-form">
        <div class="row cl">
            <input type="text" class="input-text" hidden value="" placeholder="" id="id" name="id">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>快递名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="快递名称" id="expressName" name="expressName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>排序值：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="值越小排序越前" id="sortOrder" name="sortOrder">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button id="saveButton" type="submit" class="btn btn-success radius" id="admin-role-save" name="admin-role-save"><i class="icon-ok"></i> 确定</button>
            </div>
        </div>
    </form>
</article>

<jsp:include page="../includes/footer.jsp"/>
<script type="text/javascript">

    $("#id").val(parent.id);
    $("#expressName").val(parent.expressName);
    $("#sortOrder").val(parent.sortOrder);

    /**
     * 提交成功后执行的方法
     * */
    function successMethod(data) {
        parent.refresh();
        parent.App.msgSuccess(data.message);
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
    Validate.validate('/express/save', "", successMethod);

</script>
</body>
</html>
