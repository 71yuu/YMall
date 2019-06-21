<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>管理员列表</title>
</head>
<style>
    .table>tbody>tr>td{
        text-align:center;
    }
</style>
<body>

<div>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <form class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="admin_add('添加管理员','admin-form',400,300)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加管理员</a></span></div>
        <div class="mt-20">
            <div class="mt-20" style="margin-bottom: 70px">
                <table id="dataTable" class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
                    <thead>
                    <tr class="text-c">
                        <th width="25"><input type="checkbox" class="minimal icheck_master"/></th>
                        <th width="40">ID</th>
                        <th width="150">登录名</th>
                        <th width="130">创建时间</th>
                        <th width="100">操作</th>
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
            { "data": null,
                render : function(data,type, row, meta) {
                    return '<input id="' + row.id + '" type="checkbox" class="minimal" />';
                }
            },
            { "data": "id"},
            { "data": "username"},
            { "data": "created",
                render : function(data,type, row, meta) {
                    return date(data);
                }
            },
            {
                "data": null,
                render : function(data,type, row, meta) {
                    if(row.state==1){
                        return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"change_password('修改密码','change-admin-password',"+row.id+",'600','270')\" href=\"javascript:;\" title=\"修改密码\"><i class=\"Hui-iconfont\">&#xe63f;</i></a> <a title=\"删除\" href=\"javascript:;\" onclick=\"admin_del(this,"+row.id+")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                    } else{
                        return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"change_password('修改密码','change-admin-password',"+row.id+",'600','270')\" href=\"javascript:;\" title=\"修改密码\"><i class=\"Hui-iconfont\">&#xe63f;</i></a> <a title=\"删除\" href=\"javascript:;\" onclick=\"admin_del(this,"+row.id+")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                    }
                }
            }
        ];

        _dataTables = App.initDataTables("/user/list/", _columns);
    });

    // 初始化数据
    var username="",userId="",phone="",email="",roleNames="",sex="",description="";

    /**
     * 管理员增加
     **/
    function admin_add(title,url,w,h){
        username="",userId=0,phone="",email="",roleNames="",sex="",description=""
        layer_show(title,url,w,h);
    }

    /**
     * 管理员编辑
     **/
    function admin_edit(title,url,id,w,h){
        userId=id;
        var table = $('.table').DataTable();
        $('.table tbody').on( 'click', 'tr', function () {
            username = table.row(this).data().username;
            phone = table.row(this).data().phone;
            email = table.row(this).data().email;
            roleNames = table.row(this).data().description;
            sex = table.row(this).data().sex;
            description = table.row(this).data().description;
        });
        layer_show(title,url,w,h);
    }

    /**
     * 删除用户
     **/
    function admin_del(obj,id){
        // 确认消息
        var confirmMsg = '确定删除ID为 '+ id +' 的用户吗？';

        // 提交请求路径
        var url = '/user/delete/' + id;

        // 成功回调方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon: 2, time: 1000});
        }

        App.deleteSinge(confirmMsg, url, successMethod);
    }

    /**
     * 批量删除
     */
    function datadel() {
        // 请求路径
        var url = "/user/delete/";

        // 请求成功执行方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon:1, time:1000});
        }

        App.deleteMulti(url, successMethod);
    }


    /**
     * 密码修改
     */
    function change_password(title,url,id,w,h){
        userId=id;
        var table = $('.table').DataTable();
        $('.table tbody').on( 'click', 'tr', function () {
            username = table.row(this).data().username;
        });
        layer_show(title,url,w,h);
    }

</script>
</body>
</html>