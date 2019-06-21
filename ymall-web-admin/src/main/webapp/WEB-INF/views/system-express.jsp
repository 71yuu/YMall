<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>快递管理</title>
</head>
<style>
    .table > tbody > tr > td {
        text-align: center;
    }
</style>
<body>

<div>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span
            class="c-gray en">&gt;</span> 快递管理 <a class="btn btn-success radius r"
                                                  style="line-height:1.6em;margin-top:3px"
                                                  href="javascript:location.replace(location.href);" title="刷新"><i
            class="Hui-iconfont">&#xe68f;</i></a></nav>
    <form class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l">
                <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
                <a class="btn btn-primary radius" onclick="add('添加快递','system-express-form',500,250)"
                   href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加快递</a>
            </span>
        </div>
        <div class="mt-20">
            <div class="mt-20" style="margin-bottom: 70px">
                <table id="dataTable" class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
                    <thead>
                    <tr class="text-c">
                        <th width="15"><input type="checkbox" class="minimal icheck_master"/></th>
                        <th width="30">ID</th>
                        <th width="80">快递名称</th>
                        <th width="20">排序值</th>
                        <th width="50">操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </form>
</div>

<jsp:include page="../includes/footer.jsp"/>
<script type="text/javascript">

    /**
     * 初始化 DataTables
     */
    var _dataTables;
    $(function () {
        const _columns = [
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return '<input id="\' + row.id + \'" type="checkbox" class="minimal" />';
                }
            },
            {"data": "id"},
            {"data": "expressName"},
            {"data": "sortOrder"},
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"edit('快递编辑','system-express-form',500,250)\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> " +
                        "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"del(" + row.id + ")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                }
            }
        ];

        _dataTables = App.initDataTables("/express/list/", _columns);
    });

    // 定义初始化数据
    var id, expressName, sortOrder;

    /**
     * 添加快递
     **/
    function add(title, url, w, h) {
        id = "", expressName = "", sortOrder = "";
        layer_show(title, url, w, h);
    }

    /**
     * 编辑快递
     **/
    function edit(title, url, w, h) {
        var table = $('.table').DataTable();
        $('.table tbody').on('click', 'tr', function () {
            id = table.row(this).data().id;
            expressName = table.row(this).data().expressName;
            sortOrder = table.row(this).data().sortOrder;
        });
        layer_show(title, url, w, h);
    }

    /**
     * 删除快递
     **/
    function del(id) {
        // 确认消息
        var confirmMsg = '确定删除ID为 '+ id +' 的快递吗？';

        // 提交请求路径
        var url = '/express/delete/' + id;

        // 成功回调方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon: 2, time: 1000});
        }

        App.deleteSinge(confirmMsg, url, successMethod);
    }

    /**
     * 批量删除快递
     */
    function datadel() {
        // 请求路径
        var url = "/express/delete/";

        // 请求成功执行方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon:1, time:1000});
        }

        App.deleteMulti(url, successMethod);
    }

    function msgSuccess(content) {
        layer.msg(content, {icon: 1, time: 3000});
    }
</script>
</body>
</html>
