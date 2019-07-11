<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>用户管理</title>
</head>
<style>
    .table > tbody > tr > td {
        text-align: center;
    }
</style>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span
        class="c-gray en">&gt;</span> 用户管理 <a id="btn-refresh" class="btn btn-success radius r"
                                              style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i
                    class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
            <a href="javascript:;" onclick="member_add('添加用户','member-form')" class="btn btn-primary radius"><i
                    class="Hui-iconfont">&#xe600;</i> 添加用户</a>
        </span>
    </div>
    <div class="mt-20" style="margin-bottom: 70px">
        <table id="dataTable" class="table table-border table-bordered table-hover table-bg table-sort" width="100%">
            <thead>
            <tr class="text-c">
                <th width="30"><input type="checkbox" class="minimal icheck_master"/></th>
                <th width="40">ID</th>
                <th width="80">用户名</th>
                <th width="90">手机</th>
                <th width="100">邮箱</th>
                <th width="90">创建时间</th>
                <th width="90">更新时间</th>
                <th width="50">状态</th>
                <th width="110">操作</th>
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
                "data": "created",
                render: function (data, type, row, meta) {
                    return date(data);
                }
            },
            {
                "data": "updated",
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
                    if(row.state == 1){
                        return "<a id=\"td-manage\" style=\"text-decoration:none\" onClick=\"member_ban(this,"+row.id+")\" href=\"javascript:;\" title=\"封禁\"><i class=\"Hui-iconfont\">&#xe60e;</i></a> <a title=\"编辑\" href=\"javascript:;\" onclick=\"member_edit('编辑','member-form',"+row.id+")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"change_password('修改密码','change-password',"+row.id+",'600','270')\" href=\"javascript:;\" title=\"修改密码\"><i class=\"Hui-iconfont\">&#xe63f;</i></a> <a title=\"删除\" href=\"javascript:;\" onclick=\"member_del(this,"+row.id+")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                    } else{
                        return "<a id=\"td-manage\" style=\"text-decoration:none\" onClick=\"member_start(this,"+row.id+")\" href=\"javascript:;\" title=\"解封\"><i class=\"Hui-iconfont\">&#xe605;</i></a> <a title=\"编辑\" href=\"javascript:;\" onclick=\"member_edit('编辑','member-form',"+row.id+")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"change_password('修改密码','change-password',"+row.id+",'600','270')\" href=\"javascript:;\" title=\"修改密码\"><i class=\"Hui-iconfont\">&#xe63f;</i></a> <a title=\"删除\" href=\"javascript:;\" onclick=\"member_del(this,"+row.id+")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                    }
                }
            }
        ];

        _dataTables = App.initDataTables("/member/list/", _columns);
    });

    /**
     * 封禁会员
     **/
    function member_ban(obj, id) {
        // 确认消息
        var confirmMsg = '确定封禁ID为 '+ id +' 的会员吗？';

        // 提交请求路径
        var url = '/member/ban/' + id;

        // 成功回调方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon: 5, time: 1000});
        }

        App.deleteSinge(confirmMsg, url, successMethod);
    }

    /**
     * 解封会员
     **/
    function member_start(obj, id) {
        // 确认消息
        var confirmMsg = '确定解封ID为 '+ id +' 的会员吗？';

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
     * 初始化会员数据
     **/
    var Id = "", username = "", phone = "", email = "", description = "", sex = "", address = "", file = "";

    /**
     * 添加会员
     **/
    function member_add(title, url) {
        // 会员数据设置为空值
        Id = "", username = "", phone = "", email = "", description = "", sex = "", address = "", file = "";
        App.openAndFull(title, url);
    }

    /**
     * 编辑会员
     **/
    function member_edit(title, url, id) {
        Id = id;
        var table = $('.table').DataTable();
        $('.table tbody').on('click', 'tr', function () {
            username = table.row(this).data().username;
            phone = table.row(this).data().phone;
            email = table.row(this).data().email;
            description = table.row(this).data().description;
            sex = table.row(this).data().sex;
            address = table.row(this).data().address;
            file = table.row(this).data().file;
        });
        App.openAndFull(title, url);
    }

    /**
     * 修改密码
     **/
    function change_password(title, url, id, w, h) {
        Id = id;
        var table = $('.table').DataTable();
        $('.table tbody').on('click', 'tr', function () {
            username = table.row(this).data().username;
        });
        layer_show(title, url, w, h);
    }

    /**
     * 删除会员
     **/
    function member_del(obj, id) {
        // 确认消息
        var confirmMsg = '确定删除ID为 '+ id +' 的会员吗？';

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
</script>
</body>
</html>
