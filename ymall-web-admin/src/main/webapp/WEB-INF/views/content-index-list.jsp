<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <!-- ZTree -->
    <link rel="stylesheet" href="/static/assets/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/static/assets/plugins/iCheck/all.css">
</head>
<style>
    .table>tbody>tr>td{
        text-align:center;
    }
</style>

<body class="pos-r">
<div class="pos-a" style="width:200px;left:0;top:0; bottom:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5; overflow:auto;">
    <ul style="margin-top: 15px;margin-left: 20px"><i class="Hui-iconfont Hui-iconfont-fenlei"></i> 首页板块</ul>
    <ul id="myTree" style="margin-left: 10px" class="ztree"></ul>
</div>
<div style="margin-left:200px;">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商城管理 <span class="c-gray en">&gt;</span> 首页板块内容管理 <span class="c-gray en">&gt;</span><span id="name"></span> <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <form id="form-search" class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="index_add('添加展示内容','content-banner-form')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加展示内容</a></span> <span class="r">最大容纳内容(商品)数：<strong id="limitNum"></strong></span> </div>
        <div class="mt-20">
            <div class="mt-20" style="margin-bottom: 70px">
                <table id="dataTable" class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
                    <thead>
                    <tr class="text-c">
                        <th width="30"><input type="checkbox" class="minimal icheck_master" ></th>
                        <th width="40">ID</th>
                        <th width="50">类型</th>
                        <th width="70">缩略图</th>
                        <th width="150">跳转链接</th>
                        <th width="100">展示商品ID</th>
                        <th width="150">商品名称</th>
                        <th width="60">排序值</th>
                        <th width="95">更新日期</th>
                        <th width="90">操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </form>
</div>

<jsp:include page="../includes/footer.jsp"/>

<!-- ZTree -->
<script type="text/javascript" src="/static/assets/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<!-- DataTables -->
<script type="text/javascript" src="/static/assets/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/static/assets/lib/laypage/1.2/laypage.js"></script>
<!-- iCheck -->
<script type="text/javascript" src="/static/assets/plugins/iCheck/icheck.min.js"></script>

<!-- App -->
<script type="text/javascript" src="/static/assets/lib/common.js"></script>
<script type="text/javascript">

    /**
     * 初始化
     * */
    var index = layer.load(3);


    /**
     * 初始化类别数据
     * */
    var panelId = 1, name = "", limitCount = 0, currentCount = 0;
    $.ajax({
        url: '/panel/index/list',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data.length <= 0 || data == "") {
                return ;
            }
            panelId = data[0].id;
            name = data[0].name;
            $("#name").html(name);
            limitCount = data[0].limitNum;
            $("#limitNum").html(limitCount);
            initTable("/content/list/" + panelId);
            updateCurrentCount("/content/list/"+panelId);
        },
        error: function () {
            layer.alert(ERROR_REQUEST_MESSAGE, {title: "错误信息", icon: 2});
        }
    });

    /**
     * 初始化 iCheck
     */
    App.initICheck();

    /**
     * 初始化 DataTables
     */
    var _dataTable;
    function initTable(url) {
        const _columns = [
            { "data": null,
                render : function(data,type, row, meta) {
                    return '<input id="' + row.id + '" type="checkbox" class="minimal">';
                }
            },
            { "data": "id"},
            { "data": "type",
                render : function(data,type, row, meta) {
                    if (data == 0){
                        return "<span class=\"label label-success radius td-status\">关联商品</span>";
                    } else if(data == 1){
                        return "<span class=\"label label-warning radius td-status\">其他链接</span>";
                    } else if(data == 2){
                        return "<span class=\"label label-primary radius td-status\">关联商品(封面)</span>";
                    } else if(data == 3){
                        return "<span class=\"label label-primary radius td-status\">封面(其它链接)</span>";
                    }
                }
            },
            { "data": "picUrl",
                render: function(data, type, row, meta) {
                    return '<img id="picUrl" src="'+ data +'" style="width: 80px;height: 60px" alt="" />';
                }
            },
            {"data": "fullUrl"},
            { "data": "productId"},
            { "data": "productName"},
            {"data": "sortOrder"},
            { "data": "updated",
                render : function(data,type, row, meta) {
                    return date(data);
                }
            },
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"index_edit('内容编辑','content-banner-form')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> " +
                        "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"del("+row.id+")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                }
            }
        ];

        _dataTable = App.initDataTables(url, _columns);
    }

    /**
     * ZTree 回调函数
     * */
    var callback = {
        onAsyncSuccess: function(){
            layer.close(index);
        },
        beforeClick: function(treeId, treeNode) {
            if (treeNode.isParent) {
                return false;
            } else {
                panelId = treeNode.id;
                name = treeNode.name;
                $("#name").html(name);
                limitCount = treeNode.limitNum;
                $("#limitNum").html(limitCount);
                updateCurrentCount("/content/list/"+panelId);
                var table = $('.table').DataTable();
                var url="/content/list/"+panelId;
                table.ajax.url(url).load();
                return true;
            }
        }
    };
    App.initZtree("/panel/index/list", callback);

    /**
     * 更新当前数量
     * */
    function updateCurrentCount(url) {
        $.ajax({
            url: url,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                currentCount = data.data.length;
            },
            error: function () {
                layer.alert(ERROR_REQUEST_MESSAGE, {title: '错误信息', icon: 2});
            }
        })
    }

    /**
     * 编辑板块内容
     */
    var id, productId, productName, picUrl, type;
    function index_edit(title, url) {
        $("#dataTable tbody").on('click', 'tr', function () {
            id = _dataTable.row(this).data().id;
            productId = _dataTable.row(this).data().productId;
            productName = _dataTable.row(this).data().productName;
            picUrl = _dataTable.row(this).data().picUrl;
            type = _dataTable.row(this).data().type;
            sortOrder = _dataTable.row(this).data().sortOrder;
        });
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /**
     * 添加板块内容
     * */
    function index_add(title, url) {
        id = "", productId = "", productName = "", picUrl = "", type = "";
        alert(currentCount)
        alert(limitCount)
        if (currentCount >= limitCount) {
            layer.alert('当前板块内容数量已达上限', {title: '错误信息', icon: 0});
            return;
        }
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }


    /**
     * 删除单个轮播图
     *
     * @param id 轮播图 id
     */
    function del(id) {

        // 确认消息
        var confirmMsg = '确定要删除ID为\''+ id +'\'的数据吗？';

        // 提交请求
        var url = '/content/delete/' + id;

        // 成功回调方法
        function successMethod() {
            updateCurrentCount('/content/delete/' + id);
            refresh();
            layer.msg("删除轮播图成功！", {icon:2, time:1000});
        }

        App.deleteSinge(confirmMsg, url, successMethod);
    }

    /**
     * 批量删除
     * */
    function datadel() {

        // 请求路径
        var url = "/content/delete/";

        // 请求成功执行方法
        function successMethod() {
            updateCurrentCount();
            refresh();
            layer.msg("删除轮播图成功！", {icon:1, time:1000});
        }

        App.deleteMulti(url, successMethod);
    }
</script>
</body>
</html>
