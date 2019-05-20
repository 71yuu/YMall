<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
    <title>首页板块</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商城管理 <span class="c-gray en">&gt;</span> 首页板块 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div style="margin-left: 1vw;margin-right: 1vw" class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="content_panel_del()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 删除所选板块</a> <a class="btn btn-primary radius" onclick="content_panel_add('添加板块','content-panel-add')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加首页板块</a></span> </div>
<table class="table">
    <tr>
        <td style="padding-left: 4vw" width="200" class="va-t"><ul id="myTree" class="ztree"></ul></td>
        <td class="va-t">
            <div class="page-container">
                <form action="" method="post" class="form form-horizontal" id="validate-form">
                    <input type="text" hidden class="input-text" id="id" name="id">
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
                                <option value="3">板块种类三</option>
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
                        <label class="form-label col-xs-4 col-sm-2">备注：</label>
                        <div class="formControls col-xs-6 col-sm-6">
                            <textarea name="remark" id="remark" cols="" rows="" class="textarea"  placeholder="说点什么...最多输入100个字符"></textarea>
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

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/static/assets/lib/zTree/v3/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="/static/assets/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="/static/assets/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="/static/assets/lib/jquery.validation/1.14.0/messages_zh.js"></script>

<!-- App -->
<script type="text/javascript" src="/static/assets/app/validate.js"></script>
<script type="text/javascript">

    // 加载中...
    var index = layer.load(3);


    // treeNode 初始化值
    var id = -1, name = "";

    // zTree 的配置
    var setting =  {
        view: {
            // 禁止多选
            selectedMulti: false
        },
        async: {
            // 开启异步加载
            enable: true,
            // 远程访问地址
            url: "/panel/indexAll/list",
            // 请求方式
            type: "GET"
        },
        callback: {
            onAsyncSuccess: function () {
                layer.close(index);
            },
            // 点击之前的操作
            beforeClick: function (treeId, treeNode) {
                $("#name").val(treeNode.name);
                $("#id").val(treeNode.id);
                $("#type").val(treeNode.type);
                $("#sortOrder").val(treeNode.sortOrder);
                $("#remark").val(treeNode.remark);
                $("#limitNum").val(treeNode.limitNum);
                changeSwitch(treeNode.status);
                id = treeNode.id;
                name = treeNode.name;
                if (treeNode.isParent) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    };

    // 初始化 zTree 控件
    initTree();
    function initTree() {
        $.fn.zTree.init($("#myTree"), setting);
    }

    // 切换按钮
    function changeSwitch(value) {
        if (value == 1) {
            $("#mySwitch").bootstrapSwitch('setState', true);
        } else {
            $("#mySwitch").bootstrapSwitch('setState', false);
        }
    }

    // 同步 Status 的值
    $("#mySwitch").on('switch-change', function (e, data) {
        if (data.value == true) {
            $("#status").val(1);
        } else {
            $("#status").val(0);
        }
    });

    // 备注文本框输入限制
    $(".textarea").Huitextarealength({
        minlength: 0,
        maxlength: 100
    });

    // 编辑提交验证
    // 验证之前执行的方法
    function beforeSubmit() {
        if ($("#id").val() == null || $("#id").val() == "") {
            layer.alert('请点击选择要修改的板块！请勿自己填写！', {title: '错误信息', icon: 0});
            return;
        }
    }
    // 验证成功
    function successMethod(data) {
        layer.alert(data.message, {icon: 1}, function (index) {
            initTree();
            msgSuccess("编辑成功");
        });
    }
    Validate.validate("/panel/update", beforeSubmit, successMethod);

</script>
</body>
</html>
