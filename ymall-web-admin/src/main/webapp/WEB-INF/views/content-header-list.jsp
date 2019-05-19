<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="/static/assets/lib/html5shiv.js"></script>
    <script type="text/javascript" src="/static/assets/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/static/assets/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="/static/assets/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="/static/assets/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="/static/assets/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="/static/assets/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="/static/assets/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>导航栏管理</title>

    <!-- iCheck for checkboxes and radio inputs -->
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
                <a class="btn btn-primary radius" onclick="add('添加导航栏内容','/content/content-header-add',700,350)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加导航栏内容</a>
            </span>
        </div>
        <div class="mt-20">
            <div class="mt-20" style="margin-bottom: 70px">
                <table class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
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
                </table>
            </div>
        </div>
    </form>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/static/assets/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/static/assets/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/assets/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/assets/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/static/assets/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/static/assets/lib/datatables/dataTables.colReorder.min.js"></script>
<script type="text/javascript" src="/static/assets/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="/static/assets/lib/common.js"></script>
<!-- iCheck 1.0.1 -->
<script src="/static/assets/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript">

    // 激活 iCheck
    var _masterCheckbox;
    var _checkbox;

    // 用于存放 ID 的数组
    var _idArray;

    /**
     * 私有方法，初始化 ICheck
     */
    var handlerInitCheckbox = function () {
        // 激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

        // 获取控制端 Checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');

        // 获取全部 Checkbox 集合
        _checkbox = $('input[type="checkbox"].minimal');
    };

    /**
     * Checkbox 全选功能
     */
    var handlerCheckboxAll = function () {
        _masterCheckbox.on("ifClicked", function (e) {
            // 返回 true 表示未选中
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }

            // 选中状态
            else {
                _checkbox.iCheck("check");
            }
        });
    };



    /** DataTables **/
    $('.table').DataTable({
        // 是否开启本地分页
        "paging": true,
        // 是否开启本地排序
        "ordering": false,
        // 是否显示左下角信息
        "info": true,
        // 是否允许用户改变表格每页显示的记录数
        "lengthChange": false,
        // 是否显示处理状态（排序的时候，数据很多耗费时间长的话，也会显示这个）
        "processing": true,
        // 是否运行 DataTables 开启本地搜索
        "searching": false,
        // 控制 DataTables 的延迟渲染，可以提高初始化速度
        "deferRender": true,
        // 增加或修改通过 Ajax 提交到服务端的请求数据
        "ajax": {
            "url": "/content/list/0",
            type: 'GET'
        },
        "columns": [
            { "data": null,
                render : function(data,type, row, meta) {
                    return '<input id="' + row.id + '" type="checkbox" class="minimal" />';
                }
            },
            { "data": "id"},
            { "data": "picUrl"},
            { "data": "fullUrl"},
            { "data": "type",
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
                    return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"edit('编辑','/content/content-header-edit',700,350)\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> " +
                        "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"del("+row.id+")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                }
            }
        ],
        // 国际化
        "language": {
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        },
        // 表格重绘的回调函数
        "drawCallback": function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        }
    });

    /** 添加导航栏内容 **/
    function add(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    /** 编辑导航栏内容 **/
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
        layer_show(title,url,w,h);
    }

    /** 删除单个 **/
    function del(id) {
        layer.confirm('确定要删除ID为\''+ id +'\'的数据吗？', {icon:0},function () {
            $.ajax({
                type: 'DELETE',
                url: '/content/delete/' + id,
                dataType: 'json',
                success: function (data) {
                    if (data.status == 200) {
                        refresh();
                        layer.msg(data.message, {icon:2, time:1000});
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert('数据处理失败！错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
                }
            })
        });
    }

    /** 批量删除 **/
    function datadel() {
        _idArray = new Array();

        // 将选中的元素的 ID 放入数组中
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });

        // 判断用户是否选择了数据项
        if (_idArray.length === 0) {
            layer.msg('您还未勾选任何数据！', {icon:5, time:3000});
            return;
        }

        layer.confirm('确定要删除所选的'+ _idArray.length +'条数据吗？', {icon:0}, function () {
            var index = layer.load(3);
            $.ajax({
                type: 'DELETE',
                url: '/content/delete/' + _idArray,
                dataType: 'json',
                success: function (data) {
                    layer.close(index);
                    if (data.status == 200) {
                        refresh();
                        layer.msg(data.message, {icon:1, time:1000});
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败！错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
                }
            });
        });
    }

    /** 编辑成功消息提示 **/
    function msgSuccess(content) {
        layer.msg(content, {icon:1, time:3000});
    }
</script>
</body>
</html>
