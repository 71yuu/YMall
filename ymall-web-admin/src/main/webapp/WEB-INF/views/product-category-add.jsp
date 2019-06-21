<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>添加产品分类</title>
</head>
<body>
<div class="page-container">
    <form action="" method="post" class="form form-horizontal" id="validate-form">
        <input type="text" hidden class="input-text" value="0" id="parentId" name="parentId">
        <input type="text" hidden class="input-text" value="true" id="isParent" name="isParent">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">
                <span class="c-red">*</span>
                分类名称：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <input type="text" class="input-text" value="" placeholder="" id="name" name="name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>是否为父节点：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <div id="parentSwitch" class="switch" data-on-label="是" data-on="info" data-off-label="否">
                    <input type="checkbox" checked />
                </div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-7 col-offset-4">
                <input id="saveButton" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存并提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</div>
<jsp:include page="../includes/footer.jsp"/>

<!-- Validate -->
<script type="text/javascript" src="/static/assets/app/validate.js"></script>
<script type="text/javascript">

    /**
     * 备注文本输入限制
     */
    $(".textarea").Huitextarealength({
        minlength:0,
        maxlength:100
    });

    /**
     * 判断是否是父节点
     */
    if(!parent.isRoot){
        $('#parentSwitch').bootstrapSwitch('setState', false);
        $("#isParent").val(0);
    }else{
        $('#parentSwitch').bootstrapSwitch('setState', true);
        $("#isParent").val(1);
    }

    /**
     * 若父节点节点不为空
     */
    if(parent.id != ""){
        $("#parentId").val(parent.id);
    }

    /**
     * 同步更新节点值
     */
    $('#parentSwitch').on('switch-change', function (e, data) {
        if(data.value == true){
            $("#isParent").val(true);
        }else{
            $("#isParent").val(false);
        }
    });

    /**
     * 验证成功触发的方法
     *
     * @param data
     */
    function successMethod(data) {
        parent.App.initZtree("/item/cat/list/-1", parent.callback);
        parent.App.msgSuccess(data.message);
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
    Validate.validate("/item/cat/save", "", successMethod);

</script>
</body>
</html>