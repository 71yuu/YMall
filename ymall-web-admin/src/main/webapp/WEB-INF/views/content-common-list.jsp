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
    <input id="type" type="hidden" value="${type}" />
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商城管理 <span class="c-gray en">&gt;</span> 首页${type == -1 ? "板块内容" : "轮播图"}管理 <span class="c-gray en">&gt;</span><span id="name"></span> <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <form id="form-search" class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="deleteMulti()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="common_add('添加展示内容','content-common-form')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加展示内容</a></span> <span class="r">最大容纳内容(商品)数：<strong id="limitNum"></strong></span> </div>
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

<script type="text/javascript">
    /**
     * 初始化
     * */
    var index = layer.load(3);
    /**
     * panelType = 0 时，为轮播图板块内容管理，type = -1 时，为其他板块内容管理
     * */
    var panelType = $("#type").val();
    /**
     * 初始化类别数据
     * */
    var panelId = 1, name = "", limitCount = 0, currentCount = 0;
    $.ajax({
        url: '/panel/common/list/' + panelType,
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
            updateCurrentCount();
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
                        return "<span class=\"label label-success radius td-status type\">关联商品</span>";
                    } else if(data == 1){
                        return "<span class=\"label label-warning radius td-status type\">其他链接</span>";
                    } else if(data == 2){
                        return "<span  class=\"label label-primary radius td-status type\">封面(关联商品)</span>";
                    } else if(data == 3){
                        return "<span class=\"label label-primary radius td-status type\">封面(其它链接)</span>";
                    }
                }
            },
            { "data": "picUrl",
                render: function(data, type, row, meta) {
                    return '<img id="picUrl" src="'+ data +'" style="width: 80px;height: 60px" alt="" onclick="App.previewImg(this, 700, 400)" />';
                }
            },
            {"data": "fullUrl",
                render: function(data, type, row, meta) {
                    if (data == null || data == "") {
                        return "无";
                    } else {
                        return data;
                    }
                }
            },
            { "data": "productId",
                render: function(data, type, row, meta) {
                    if (data == null || data == "") {
                        return "无";
                    } else {
                        return data;
                    }
                }
            },
            { "data": "productName",
                render: function(data, type, row, meta) {
                    if (data == null || data == "") {
                        return "无";
                    } else {
                        return data;
                    }
                }
            },
            {"data": "sortOrder"},
            { "data": "updated",
                render : function(data,type, row, meta) {
                    return date(data);
                }
            },
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"common_edit('内容编辑','content-common-form')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> " +
                        "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"deleteSinge("+row.id+")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
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
                updateCurrentCount();
                var url="/content/list/"+panelId;
                _dataTable.ajax.url(url).load();
                return true;
            }
        }
    };
    App.initZtree("/panel/common/list/" + panelType, callback);
    /**
     * 更新当前数量
     * */
    function updateCurrentCount() {
        $.ajax({
            url: "/content/list/" + panelId,
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
     * 判断是否有封面
     * */
    function isExistCover() {
        let cover = 1;
        $(".type").each(function () {
            if ($(this).html().indexOf('封面') != -1) {
                cover = 0;
                return cover;
            }
        });
        return cover;
    }
    /**
     * 编辑板块内容
     */
    var id, productId, productName, picUrl, type, cover;
    function common_edit(title, url) {
        $("#dataTable tbody").on('click', 'tr', function () {
            id = _dataTable.row(this).data().id;
            productId = _dataTable.row(this).data().productId;
            productName = _dataTable.row(this).data().productName;
            fullUrl = _dataTable.row(this).data().fullUrl;
            picUrl = _dataTable.row(this).data().picUrl;
            type = _dataTable.row(this).data().type;
            sortOrder = _dataTable.row(this).data().sortOrder;
            cover = isExistCover();
            if (type == 2 || type == 3) {
                cover = 1;
            }
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
    function common_add(title, url) {
        id = "", productId = "", productName = "", picUrl = "", type = "";
        cover = isExistCover();
        if (type == 2 || type == 3) {
            cover = 1;
        }
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
     * 删除单个板块内容
     *
     * @param id 板块内容 id
     */
    function deleteSinge(id) {
        // 确认消息
        var confirmMsg = '确定要删除ID为\''+ id +'\'的数据吗？';
        // 提交请求
        var url = '/content/delete/' + id;
        // 成功回调方法
        function successMethod() {
            updateCurrentCount('/content/delete/' + id);
            refresh();
            layer.msg("删除成功！", {icon:1, time:1000});
        }
        App.deleteSinge(confirmMsg, url, successMethod);
    }
    /**
     * 批量删除
     * */
    function deleteMulti() {
        // 请求路径
        var url = "/content/delete/";
        // 请求成功执行方法
        function successMethod() {
            updateCurrentCount();
            refresh();
            layer.msg("删除成功！", {icon:1, time:1000});
        }
        App.deleteMulti(url, successMethod);
    }
</script>
</body>
</html>
