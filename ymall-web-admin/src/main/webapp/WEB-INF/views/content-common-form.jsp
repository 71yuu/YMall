<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>

    <!-- DropZone -->
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/dropzone.min.css" />
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css" />
</head>

<body>
<div class="page-container">
    <form name="product-add" action="" method="post" class="form form-horizontal" id="validate-form">
        <input type="text" hidden class="input-text" id="id" name="id">
        <input type="text" hidden class="input-text" id="panelId" name="panelId">
        <input type="text" hidden class="input-text" id="position" name="position">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">所属板块：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <span id="name" name="name"></span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">
                <span class="c-red">*</span>
                类型：</label>
            <div class="formControls col-xs-6 col-sm-3">
                <select id="type" class="select-box" name="type">
                    <option value="0">关联商品</option>
                    <option value="1">其他链接</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>排序优先值：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" placeholder="请输入0~9999，值越小排序越前" id="sortOrder" name="sortOrder">
            </div>
        </div>
        <div class="row cl" id="oUrl" style="display:none">
            <label class="form-label col-xs-4 col-sm-2">其他链接：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" placeholder="http://" id="fullUrl" name="fullUrl">
            </div>
        </div>
        <div class="row cl product">
            <label class="form-label col-xs-4 col-sm-2">选择展示商品：</label>
            <div class="formControls col-xs-8 col-sm-6">
                <input type="text" onclick='App.show("选择展示商品","choose-product",900,600)' readonly class="input-text" placeholder="请点击选择按钮选择关联商品" id="title" name="title" style="width: 65%">
                <input type="button" onclick='App.show("选择展示商品","choose-product",900,600)' class="btn btn-secondary radius" value="选择关联商品">
            </div>
        </div>
        <div class="row cl product">
            <label class="form-label col-xs-4 col-sm-2">所选商品ID：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" onclick='App.show("选择展示商品","choose-product",900,600)' readonly placeholder="请点击选择按钮选择关联商品" class="input-text" id="productId" name="productId">
            </div>
        </div>
        <input type="text" name="picUrl" id="picUrl" hidden/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">3D轮播图图片上传：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div id="dropz" class="dropzone"></div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存并提交</button>
                <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
            </div>
        </div>
    </form>
</div>


<jsp:include page="../includes/footer.jsp"/>

<!-- DropZone -->
<script src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script>

<!-- App -->
<script type="text/javascript" src="/static/assets/app/validate.js"></script>

<script type="text/javascript">

    /**
     * 获取父窗口的值
     */
    $("#id").val(parent.id);
    $("#productId").val(parent.productId);
    $("#name").html(parent.name);
    $("#panelId").val(parent.panelId);
    $("#title").val(parent.productName);
    $("#picUrl").val(parent.picUrl);
    $("#fullUrl").val(parent.fullUrl);
    $("#sortOrder").val(parent.sortOrder);

    /**
     * 板块类型：0：轮播图 -1：其他板块内容
     * */
    var panelType = parent.panelType;

    /**
     * 是否已有封面
     * */
    var cover = parent.cover;

    /**
     * 若不是关联商品，显示其他链接
     * */
    if (parent.type == 1 || parent.type == 3) {
        $(".product").hide();
        $("#oUrl").show();
    }

    /**
     * 轮播图没有封面选择，其他板块内容添加封面选项
     * */
    if (panelType == -1) {
        $("#type option:last").remove();
        if (cover == 1) {
            $("#type").append("<option value=\"2\">封面(关联商品)</option>");
            $("#type").append("<option value=\"3\">封面(其它链接)</option>");
        }
    }
    $("#type").val(parent.type);

    /**
     * 选项改变更换
     * */
    $("#type").change(function () {
       var type = $("#type").val();
       if (type == 1 || type == 3) {
           $("#title").val("");
           $("#productId").val("");
           $(".product").hide();
           $("#oUrl").show();
       } else if (type == 0 || type == 2) {
           $("#fullUrl").val("");
           $("#oUrl").hide();
           $(".product").show();
       }
    });

    /**
     * 获取轮播图的地址
     * */
    var picUrl = parent.picUrl;

    /**
     * 表单提交之前执行的方法
     * */
    function beforeMethod() {
        var type = $("#type").val();
        var fullUrl = $("#fullUrl").val();
        var selectProductId = $("#productId").val();
        var image = $("#picUrl").val();
        if ((type == 0 && selectProductId == "") || (type == 2 && selectProductId == "")) {
            layer.alert('请选择关联商品！', {title: '错误信息', icon: 0});
            return false;
        }
        if (type == 1 && fullUrl == "") {
            layer.alert('请填写跳转链接！', {title: '错误信息', icon: 0});
            return false;
        }
        if (image == "") {
            layer.alert('请上传轮播图片！', {title: '错误信息', icon: 0});
            return false;
        }
        return true;
    }
    /**
     * 提交成功后执行的方法
     * */
    function successMethod(data) {
        parent.refresh();
        parent.App.msgSuccess(data.message);
        parent.updateCurrentCount();
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
    Validate.validate('/content/save', beforeMethod, successMethod);

    /**
     * 初始化 Dropzone
     */
    Dropzone.autoDiscover = false;
    var myDropzone = new Dropzone("#dropz", {
        url: "/upload", // 文件提交地址
        method: "post",  // 也可用put
        paramName: "dropFile", // 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 10, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传1个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
        init: function () {
            this.emit("initimage", picUrl); // 以逗号分开
            this.on("success", function (file, data) {
                if (data.status == 200) {
                    // 上传成功触发的事件
                    $("#picUrl").val(data.fileName);
                } else {
                    layer.alert(data.message, {title: "错误信息", icon: 2});
                }
            });
        }
    });

    /**
     * 选择完成设置商品的 id 和标题
     * @param id
     * @param title
     */
    function setIdAndTitle(id, title) {
        $("#productId").val(id);
        $("#title").val(title);
    }
</script>
</body>
</html>