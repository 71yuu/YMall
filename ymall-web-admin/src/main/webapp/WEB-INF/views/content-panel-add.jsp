<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>添加板块</title>
</head>
<body>
<div class="page-container">
    <form action="" method="post" class="form form-horizontal" id="validate-form">
        <input type="text" hidden class="input-text" value="1" id="status" name="status">
        <input type="text" hidden class="input-text" value="0" name="position">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">
                <span class="c-red">*</span>
                板块名称：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <input type="text" class="input-text" value="" placeholder="" id="name" name="name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">
                <span class="c-red">*</span>
                类型：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <select id="type" class="select-box" name="type" style="width:200px">
                    <option value="0">轮播图</option>
                    <option value="1">板块种类一</option>
                    <option value="2">板块种类二</option>
                    <option value="2">板块种类三</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">
                <span class="c-red">*</span>
                排序优先值：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <input type="text" class="input-text" value="" placeholder="请输入0~9999，值越小排序越前" id="sortOrder" name="sortOrder">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">
                <span class="c-red">*</span>
                最大容纳内容(商品)数：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <input type="text" class="input-text" value="" placeholder="请输入0~999" id="limitNum" name="limitNum">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>是否启用：</label>
            <div class="formControls col-xs-6 col-sm-6">
                <div id="mySwitch" class="switch" data-on-label="启用" data-on="info" data-off-label="禁用">
                    <input type="checkbox" checked />
                </div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-9 col-offset-2">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交并保存&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</div>

<jsp:include page="../includes/footer.jsp"/>

<!-- jQuery Validation -->
<script type="text/javascript" src="/static/assets/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="/static/assets/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="/static/assets/lib/jquery.validation/1.14.0/messages_zh.js"></script>

<!-- App -->
<script type="text/javascript" src="/static/assets/app/validate.js"></script>

<script type="text/javascript">

    // 备注文本框输入限制
    $(".textarea").Huitextarealength({
        minlength: 0,
        maxlength: 100
    });

    // 同步 status 数据
    $('#mySwitch').on('switch-change', function (e, data) {
       if (data.value == true) {
           $("#status").val(1);
       } else {
           $("#status").val(0);
       }
    });

    // 新增提交验证
    function successMethod(data) {
        layer.alert(data.message, {icon: 1}, function (index) {
            parent.App.initZtree("/panel/common/list/1", parent.callback);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
    }
    Validate.validate("/panel/add", "", successMethod);

</script>
</body>
</html>