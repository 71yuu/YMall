<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    String htmlData = request.getParameter("detail") != null ? request.getParameter("detail") : "";
%>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>

    <!-- DropZone -->
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/dropzone.min.css" />
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css" />

    <!-- wangEditor -->
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css"/>
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor-fullscreen-plugin.css"/>
</head>
<body>
<div class="page-container">
    <%=htmlData%>
    <form name="product-add" action="" method="post" class="form form-horizontal" id="validate-form">
        <input type="hidden" id="id" name="id"/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>产品标题：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" name="title" id="title">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>简介描述：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" name="sellPoint" id="sellPoint">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>商品分类：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" hidden class="input-text" id="cid" name="cid" >
                <input type="text" onclick='App.show("选择商品分类","choose-category",300,510)' readonly class="input-text" value="" placeholder="请点击选择按钮选择商品分类" id="cname" name="cname" style="width:50%">
                <input type="button" onclick='App.show("选择商品分类","choose-category",300,510)' class="btn btn-secondary radius" value="选择类别">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>产品展示价格：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="price" name="price" placeholder="请输入正确金额" value="" class="input-text" style="width:50%">
                元</div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>库存数量：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name="num" id="num" placeholder="0~99999" value="" class="input-text" style="width:50%">
                件</div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>购买限制数量：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name="limitNum" id="limitNum" placeholder="0~999" value="" class="input-text" style="width:50%">
                件</div>
        </div>
        <input type="text" name="image" id="image" hidden/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>展示缩略图片上传：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div id="dropz" class="dropzone"></div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">商品详情：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div id="editor"></div>
                <textarea id="detail" name="detail" style="visibility:hidden;"></textarea>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button id="saveButton" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存并发布</button>
                <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
            </div>
        </div>
    </form>
</div>

<jsp:include page="../includes/footer.jsp"/>

<!-- DropZone -->
<script src="/static/assets/plugins/dropzone/dropzone.js"></script>

<!-- wangEditor -->
<script src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>
<script src="/static/assets/plugins/wangEditor/wangEditor-fullscreen-plugin.js"></script>

<script type="text/javascript">

    var editor;

    // 获取父窗口 ID 值
    var id = parent.getId();

    // 编辑商品
    var images = parent.images;
    var imageArray = new Array();
    if (images != "") {
        $("#image").val(images);
        imageArray = images.split(",");
    }
    function successMethod2(data) {
        $("#id").val(data.result.id);
        $("#title").val(data.result.title);
        $("#sellPoint").val(data.result.sellPoint);
        $("#price").val(data.result.price);
        $("#cname").val(data.result.cname);
        $("#cid").val(data.result.cid);
        $("#num").val(data.result.num);
        $("#limitNum").val(data.result.limitNum);
        $("#detail").val(data.result.detail);
        editor.txt.html(data.result.detail);
    }
    if (id != 0) {
        App.ajax('/item/' + id, 'GET', successMethod2);
    }

    /**
     * 设置 cid
     * */
    function setCid(cid) {
        $("#cid").val(cid);
    }

    /**
     * 设置 cname
     **/
    function setCname(cname) {
        $("#cname").val(cname);
    }

    /**
     * 初始化 Dropzone
     */
    Dropzone.autoDiscover = false;
    let first = true;
    var myDropzone = new Dropzone("#dropz", {
        url: "/upload", // 文件提交地址
        method: "post",  // 也可用put
        paramName: "dropFile", // 默认为file
        maxFiles: 5,// 一次性上传的文件数量上限
        maxFilesize: 10, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传,最多上传5张！',
        dictMaxFilesExceeded: "您最多只能上传5个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
        init: function () {
            this.emit("initimage", images); // 以逗号分开
            this.on("success", function (file, data) {
                if (data.status == 200) {
                    // 上传成功触发的事件
                    if (imageArray.length == 0) {
                        images = data.fileName;
                        $("#image").val(images);
                    } else {
                        images += ",";
                        images += data.fileName;
                        $("#image").val(images);
                    }
                    imageArray.push(file.name);
                } else {
                    layer.alert(data.message, {title: "错误信息", icon: 2});
                }
            });
            this.on("removedfile", function (file) {
                for (let i = 0; i < imageArray.length; i++) {
                    let imageStr = imageArray[i];
                    let index = imageStr.indexOf(file.name);
                    if (index > -1) {
                        let imageArray1 = images.split(",");
                        imageArray1.splice(i, 1);
                        imageArray.splice(i, 1);
                        images = imageArray1.toString();
                        $("#image").val(images);
                    }
                }
            })
        }
    });

    /**
     * 初始化 wangEditor
     */
    initWangEditor();
    function initWangEditor() {
        var E = window.wangEditor;
        editor = new E('#editor');
        // 配置服务端地址
        editor.customConfig.uploadImgServer = '/upload';
        editor.customConfig.uploadFileName = 'editorFiles';
        editor.customConfig.uploadImgMaxSize = 50 * 1024 * 1024
        // 将 timeout 时间改为 3s
        editor.customConfig.uploadImgTimeout = 200000
        editor.create();
        E.fullscreen.init('#editor');

        $("#saveButton").bind("click", function () {
            var detailHtml = editor.txt.html();
            $("#detail").val(detailHtml);
        });
    }

    /**
     * 表单提交之前执行的方法
     * */
    function beforeMethod() {
        var image = $("#image").val();
        if (image == "") {
            layer.alert('请上传展示缩略图片！', {title: '错误信息', icon: 0});
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
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
    Validate.validate('/item/save', beforeMethod, successMethod);

</script>
</body>
</html>
