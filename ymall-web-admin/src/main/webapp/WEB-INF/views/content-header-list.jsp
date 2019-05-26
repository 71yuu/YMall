<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>导航栏管理</title>
    <!-- iCheck -->
    <link rel="stylesheet" href="/static/assets/plugins/iCheck/all.css">
</head>
<style>
    .table>tbody>tr>td{
        text-align:center;
    }
</style>
<body>

<div>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商城管理 <span class="c-gray en">&gt;</span> 导航栏管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <form class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l">
                <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
                <a class="btn btn-primary radius" onclick="add('添加导航栏内容','/content-header-form',700,350)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加导航栏内容</a>
            </span>
        </div>
        <div class="mt-20">
            <div class="mt-20" >
                <table id="dataTable" class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
                    <thead>
                    <tr class="text-c">
                        <th width="15"><input type="checkbox" class="minimal icheck_master"></th>
                        <th width="30">ID</th>
                        <th width="80">名称</th>
                        <th width="100">跳转链接</th>
                        <th width="60">类型</th>
                        <th width="50">排序值</th>
                        <th width="50">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </form>
</div>

<jsp:include page="../includes/footer.jsp"/>

<!-- DataTables -->
<script type="text/javascript" src="/static/assets/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/static/assets/lib/laypage/1.2/laypage.js"></script>
<!-- iCheck 1.0.1 -->
<script src="/static/assets/plugins/iCheck/icheck.min.js"></script>

<script type="text/javascript">

    // 初始化 iCheck
    App.initICheck();

   /**
    * 初始化 DataTables
    */
   $(function () {
        const _columns = [
            {
                "data": null,
                render: function(data,type, row, meta) {
                    return '<input id="' + row.id + '" type="checkbox" class="minimal" />';
                }
            },
            { "data": "id"},
            { "data": "picUrl"},
            {
                "data": "fullUrl",
                render: function (data, type, row, meta) {
                    return '<a href="'+ row.fullUrl+'" target="_blank">'+ row.fullUrl +'</a>';
                }
            },
            {
                "data": "type",
                render: function (data, type, row, meta) {
                    if(data == 0){
                        return "<span class=\"label label-warning radius td-status\">站外</span>";
                    }else if(data == 1){
                        return "<span class=\"label label-success radius td-status\">站内</span>";
                    }
                }
            },
            { "data": "sortOrder"},
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"edit('编辑','/content-header-form',700,350)\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> " +
                        "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"del("+row.id+")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                }
            }
        ];

       App.initDataTables("/content/list/0", _columns);
    });


   /**
    * 编辑导航栏内容
    */
   var id, picUrl, fullUrl, sortOrder, type;
   function edit(title,url,w,h){
        var table = $('.table').DataTable();
        $('.table tbody').on( 'click', 'tr', function () {
            id = table.row(this).data().id;
            picUrl = table.row(this).data().picUrl;
            fullUrl = table.row(this).data().fullUrl;
            sortOrder = table.row(this).data().sortOrder;
            type = table.row(this).data().type;
        });
        App.show(title,url,w,h);
    }

    /**
     * 添加导航栏内容
     * */
    function add(title, url, w, h) {
        id = "";
        picUrl = "";
        fullUrl = "";
        sortOrder = "";
        type = "";
        App.show(title, url, w, h);
    }


   /**
    * 删除单个导航栏
    *
    * @param id 导航栏 id
    */
   function del(id) {

       // 确认消息
       var confirmMsg = '确定要删除ID为\''+ id +'\'的数据吗？';

       // 提交请求
       var url = '/content/delete/' + id;

       // 成功回调方法
       function successMethod() {
           refresh();
           layer.msg("删除导航栏成功！", {icon:2, time:1000});
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
           refresh();
           layer.msg("删除导航栏成功！", {icon:1, time:1000});
       }

       App.deleteMulti(url, successMethod);
    }

</script>
</body>
</html>
