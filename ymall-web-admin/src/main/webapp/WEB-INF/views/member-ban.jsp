<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>删除的用户</title>
</head>
<style>
    .table > tbody > tr > td {
        text-align: center;
    }
</style>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span
        class="c-gray en">&gt;</span> 删除的用户<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20"><span class="l"><a href="javascript:;" onclick="datadel()"
                                                               class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a
            href="javascript:;" onclick="restoreAll()" class="btn btn-primary radius"><i
            class="Hui-iconfont">&#xe66b;</i> 批量还原</a></span></div>
    <div class="mt-20" style="margin-bottom: 70px">
        <table id="dataTable" class="table table-border table-bordered table-hover table-bg table-sort" width="100%">
            <thead>
            <tr class="text-c">
                <th width="30"><input type="checkbox" class="minimal icheck_master"/></th>
                <th width="40">ID</th>
                <th width="80">用户名</th>
                <th width="40">性别</th>
                <th width="90">手机</th>
                <th width="110">邮箱</th>
                <th width="130">地址</th>
                <th width="100">创建时间</th>
                <th width="50">状态</th>
                <th width="100">操作</th>
            </tr>
            </thead>
        </table>
    </div>
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
                    return '<input id="' + row.id + '" type="checkbox" class="minimal" />';
                }
            },
            {"data": "id"},
            {"data": "username"},
            {
                "data": "sex",
                render: function (data, type, row, meta) {
                    if (data == null || data == "") {
                        return "用户未设置该信息！";
                    } else {
                        return data;
                    }
                }
            },
            {"data": "phone"},
            {
                "data": "email",
                render: function (data, type, row, meta) {
                    if (data == null || data == "") {
                        return "用户未设置该信息！";
                    } else {
                        return data;
                    }
                }
            },
            {
                "data": "address",
                render: function (data, type, row, meta) {
                    if (data == null || data == "") {
                        return "用户未设置该信息！";
                    } else {
                        return data;
                    }
                }
            },
            {
                "data": "created",
                render: function (data, type, row, meta) {
                    return date(data);
                }
            },
            {
                "data": "state",
                render: function (data, type, row, meta) {
                    if (data == 1) {
                        return "<span class=\"label label-defant radius td-status\">已启用</span>";
                    } else if (data == 2) {
                        return "<span class=\"label label-warning radius td-status\">已被封禁</span>";
                    }
                }
            },
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return "<a style=\"text-decoration:none\" href=\"javascript:;\" onClick=\"member_restore(this," + row.id + ")\" title=\"还原\"><i class=\"Hui-iconfont\">&#xe66b;</i></a> <a title=\"彻底删除\" href=\"javascript:;\" onclick=\"member_del(this," + row.id + ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                }
            }
        ];

        _dataTables = App.initDataTables("/member/ban/list", _columns);
    });


    /**
     * 解封会员
     **/
    function member_restore(obj, id) {
        // 确认消息
        var confirmMsg = '确定要解封ID为 '+ id +' 的会员吗？';

        // 提交请求路径
        var url = '/member/start/' + id;

        // 成功回调方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon: 5, time: 1000});
        }

        App.deleteSinge(confirmMsg, url, successMethod);
    }

    /**
     * 用户删除
     **/
    function member_del(obj, id) {
        // 确认消息
        var confirmMsg = '确定要要彻底删除ID为 '+ id +' 的会员吗？';

        // 提交请求路径
        var url = '/member/delete/' + id;

        // 成功回调方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon: 5, time: 1000});
        }

        App.deleteSinge(confirmMsg, url, successMethod);
    }

    /**
     * 批量删除
     */
    function datadel() {
        // 请求路径
        var url = "/member/delete/";

        // 请求成功执行方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon:1, time:1000});
        }

        App.deleteMulti(url, successMethod);
    }

    /**
     * 批量还原
     * @param obj
     * @param id
     */
    function restoreAll(obj, id) {
        // 请求路径
        var url = "/member/start/";

        // 请求成功执行方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon:1, time:1000});
        }

        App.deleteMulti(url, successMethod);
    }
</script>
</body>
</html>
