<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>

    <title>编辑导航栏</title>
</head>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="validate-form">
        <input type="text" class="input-text" hidden id="id" name="id">
        <input type="text" class="input-text" hidden value="0" id="panelId" name="panelId">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="picUrl" name="picUrl">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>跳转链接：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="http://" id="fullUrl" name="fullUrl">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>类型：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="select-box" style="width: 100px" id="type" name="type">
                    <option value="1" selected>站内</option>
                    <option value="0">站外</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>排序值：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请输入0~9999，值越小越靠前" id="sortOrder" name="sortOrder">
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

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/static/assets/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="/static/assets/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="/static/assets/lib/jquery.validation/1.14.0/messages_zh.js"></script>

<!-- App -->
<script type="text/javascript" src="/static/assets/app/validate.js"></script>
<script type="text/javascript">

    // 获取导航栏内容的值
    $("#id").val(parent.id);
    $("#picUrl").val(parent.picUrl);
    $("#fullUrl").val(parent.fullUrl);
    $("#sortOrder").val(parent.sortOrder);
    $("#type").val(parent.type);

    // 编辑验证
    function successMethod(data) {
        parent.refresh();
        parent.msgSuccess(data.message);
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
    Validate.validate("/content/update", "", successMethod);

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
