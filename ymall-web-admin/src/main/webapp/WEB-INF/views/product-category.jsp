<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <!-- ZTree -->
    <link rel="stylesheet" type="text/css" href="/static/assets/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
    <title>商品分类</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> 商品分类 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div style="margin-left: 1vw;margin-right: 1vw" class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="l">
        <a href="javascript:;" onclick="delSingle()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 删除所选分类</a>
        <a class="btn btn-primary radius" onclick="categoryAdd('添加子级分类','product-category-add')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加子级分类</a>
        <a class="btn btn-primary radius" onclick="categoryRootAdd('添加根节点分类','product-category-add')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加根节点分类</a>
    </span>
</div>
<table class="table">
    <tr>
        <td style="padding-left: 4vw" width="200" class="va-t"><ul id="myTree" class="ztree"></ul></td>
        <td class="va-t">
            <div class="page-container">
                <form action="" method="post" class="form form-horizontal" id="validate-form">
                    <input type="text" hidden class="input-text" id="id" name="id">
                    <input type="text" hidden class="input-text" value="0" id="parentId" name="parentId">
                    <input type="text" hidden class="input-text" value="1" id="status" name="status">
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
                    <div class="row cl" id="choose-parent">
                        <label class="form-label col-xs-4 col-sm-2">选择父节点：</label>
                        <div class="formControls col-xs-8 col-sm-9">
                            <input type="text" onclick='App.show("选择父节点分类","choose-parent-category",300,510)' readonly class="input-text" value="" placeholder="请点击选择其父节点分类" id="parentName" name="parentName" style="width:48%">
                            <input type="button" onclick='App.show("选择父节点分类","choose-parent-category",300,510)' class="btn btn-secondary radius" value="选择父节点分类">
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
                        <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>是否启用：</label>
                        <div class="formControls col-xs-6 col-sm-6">
                            <div id="mySwitch" class="switch" data-on-label="启用" data-on="info" data-off-label="禁用">
                                <input type="checkbox" checked />
                            </div>
                        </div>
                    </div>
                    <div class="row cl">
                        <div class="col-9 col-offset-2">
                            <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交修改&nbsp;&nbsp;">
                        </div>
                    </div>
                </form>
            </div>
        </td>
    </tr>
</table>

<jsp:include page="../includes/footer.jsp"/>

<!-- ZTree -->
<script type="text/javascript" src="/static/assets/lib/zTree/v3/js/jquery.ztree.all-3.5.js"></script>

<!-- Validate -->
<script type="text/javascript" src="/static/assets/app/validate.js"></script>


<script type="text/javascript">

    /**
     * 备注文本框输入限制
     * */
    $(".textarea").Huitextarealength({
        minlength:0,
        maxlength:100
    });

    // 初始值
    var isParent = false,id = "",name = "";

    // 加载中...
    var index = layer.load(3);


    /**
     * ZTree 回调函数
     * */
    var callback = {
        onAsyncSuccess: function(){
            layer.close(index);
        },
        beforeClick: function(treeId, treeNode) {
            $("#name").val(treeNode.name);
            $("#id").val(treeNode.id);
            $("#sortOrder").val(treeNode.sortOrder);
            $("#parentId").val(treeNode.pid);
            $("#parentName").val(treeNode.parentName);
            if($("#parentId").val() == ""){
                $("#parentId").val(0);
            }
            if(treeNode.pId != 0){
                $("#parentName").val(treeNode.getParentNode().name);
            }
            changeSwitch2(treeNode.status);
            id = treeNode.id;
            name = treeNode.name;

            if (treeNode.isParent) {
                isParent = true;
                changeSwitch1(1);
                return false;
            } else {
                isParent = false;
                changeSwitch1(0);
                return true;
            }
        }
    };
    App.initZtree("/item/cat/list/-1" , callback);

    /**
     * 是否为父节点
     * */
    function changeSwitch1(value){
        if(value == 1){
            $('#parentSwitch').bootstrapSwitch('setState', true);
        }else{
            $('#parentSwitch').bootstrapSwitch('setState', false);
        }
    }

    /**
     * 同步更新父节点值
     * */
    $('#parentSwitch').on('switch-change', function (e, data) {
        if(data.value == true){
            $("#isParent").val(true);
        }else{
            $("#isParent").val(false);
        }
    });

    /**
     * 是否禁用
     * */
    function changeSwitch2(value){
        if(value == 1){
            $('#mySwitch').bootstrapSwitch('setState', true);
        }else{
            $('#mySwitch').bootstrapSwitch('setState', false);
        }
    }

    /**
     * 同步更新禁用节点值
     * */
    $('#mySwitch').on('switch-change', function (e, data) {
        if(data.value==true){
            $("#status").val(1);
        }else{
            $("#status").val(0);
        }
    });


    /**
     * 提交之前触发的方法
     */
    function beforeMethod() {
        if (id == "") {
            layer.alert("请选择要修改的分类", {title: "警告", icon: 2});
            return false;
        }
        return true;
    }
    /**
     * 验证成功触发的方法
     *
     * @param data
     */
    function successMethod(data) {
        layer.alert(data.message, {icon: 1}, function (index) {
            App.initZtree("/item/cat/list/-1", callback);
            layer.close(index);
        });
    }
    Validate.validate("/item/cat/save", beforeMethod, successMethod);



    var isRoot = false;

    /**
     * 添加子分类
     * */
    function categoryAdd(title, url) {
        if (!isParent) {
            layer.alert('请点击选择一个父级分类', {title: '错误信息', icon: 0});
            return ;
        }
        isRoot = false;
        layer_show(title, url, 700, 350);
    }


    /**
     * 添加根节点分类
     * */
    function categoryRootAdd(title, url) {
        id = "";
        isRoot = true;
        layer_show(title, url, 700, 350);
    }

    /**
    * 删除单个分类
    */
    function delSingle() {
        if(id == -1){
            layer.alert('请点击选择要删除的分类! ', {title: '错误信息',icon: 0});
            return;
        }

        // 确认消息
        var confirmMsg = '确定要删除所选的\''+ name +'\'分类吗？该操作会删除所有子分类！！！';

        // 提交请求
        var url = '/item/cat/del/' + id;

        // 成功回调方法
        function successMethod(data) {
            App.initZtree("/item/cat/list/-1", callback);
            App.msgSuccess(data.message);
        }

        App.deleteSinge(confirmMsg, url, successMethod);
    }

    /**
     * 设置父类 id 和名称
     * @param parentId
     * @param parentName
     */
    function setParentId(parentId, parentName) {
        $("#parentId").val(parentId);
        $("#parentName").val(parentName);
    }
</script>
</body>
</html>
