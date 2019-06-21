<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>商品列表</title>
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
    <ul style="margin-top: 15px;margin-left: 20px"><i class="Hui-iconfont Hui-iconfont-fenlei"></i> 商品分类</ul>
    <ul id="myTree" style="margin-left: 10px" class="ztree"></ul>
</div>
<div style="margin-left:200px;">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> 商品列表 <span class="c-gray en">&gt;</span><span id="category">所有商品</span> <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <form id="form-search" class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="deleteMulti()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="product_add('添加商品','product-form')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加商品</a></span> <span class="r"></div>
        <div class="mt-20">
            <div class="mt-20" style="margin-bottom: 70px">
                <table id="dataTable" class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
                    <thead>
                    <tr class="text-c">
                        <th width="30"><input type="checkbox" class="minimal icheck_master"/></th>
                        <th width="40">ID</th>
                        <th width="70">缩略图</th>
                        <th width="150">商品名称</th>
                        <th width="100">描述</th>
                        <th width="60">单价</th>
                        <th width="95">创建日期</th>
                        <th width="95">更新日期</th>
                        <th width="60">发布状态</th>
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

<!-- iCheck 1.0.1 -->
<script src="/static/assets/plugins/iCheck/icheck.min.js"></script>

<script type="text/javascript">


    // 初始化 iCheck
    App.initICheck();

    // 初始化类别数据 -1：所有数据
    var cid = -1;

    /**
     * 初始化 ZTree
     **/
    var callback = {
        onAsyncSuccess: function(){
            layer.close(index);
        },
        beforeClick: function(treeId, treeNode) {
            if (treeNode.isParent) {
                return false;
            } else {
                cid = treeNode.id;
                $("#category").html(treeNode.name);
                let param = {
                    "cid": treeNode.cid
                };
                _dataTables.settings()[0].ajax.url = '/item/list/' + cid;
                _dataTables.ajax.reload();
                return true;
            }
        }
    };
    App.initZtree("/item/cat/list/-1" , callback);

    /**
     * 分割图片
     * */
    function imageShow(data) {
        var images = new Array(); // 定义数组
        images = data.split(","); // 字符分割
        if (images.length > 0) {
            return images[0];
        } else {
            return data;
        }
    }

    /**
     * 初始化 DataTables
     */
    var _dataTables;
    $(function () {
        const _columns = [
            {
                "data": null,
                render: function(data,type, row, meta) {
                    return '<input id="' + row.id + '" type="checkbox" class="minimal" />';
                }
            },
            { "data": "id"},
            {
                "data": "image",
                render: function (data, type, row, meta) {
                    return '<img src="'+ imageShow(data) +'" style="width: 80px;height: 70px;" onclick="App.previewImg(this, 500, 500)"/>'
                }
            },
            {
                "data": "title",
                render: function (data, type, row, meta) {
                    if (type === 'display') {
                        if (data.length > 20) {
                            return '<span title="'+ data +'">'+ data.substr(0, 50) +'...</span>';
                        } else {
                            return '<span title='+ data +'>'+ data +'</span>'
                        }
                    }
                    return data;
                }
            },
            {
                "data": "sellPoint",
                render: function (data, type, row, meta) {
                    if (type === 'display') {
                        if (data.length > 25) {
                            return '<span title='+ data +'>'+ data +'</span>';
                        } else {
                            return '<span title='+ data +'>'+ data +'</span>'
                        }
                    }
                    return data;
                }
            },
            {"data": "price"},
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
              "data": "status",
              render: function(data, type, row, meta) {
                  if (data == 0) {
                      return '<span class="label label-default radius td-status">已下架</span>';
                  } else if (data == 1) {
                      return '<span class="label label-success radius td-status">已发布</span>';
                  } else {
                      return '<span class="label label-warning radius td-status">其它态</span>';
                  }
              }
            },
            {
                "data": null,
                render: function (data  , type, row, meta) {
                    if (row.status == 1) {
                        return '<a style="text-decoration:none" onClick="product_stop('+ row.id +')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>' +
                            '<a style="text-decoration:none" class="ml-5" onClick="product_edit(\'商品编辑\', \'product-form\', '+ row.id +')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>' +
                            '<a style="text-decoration:none" class="m1-5" onClick="product_del('+ row.id +')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>';
                    } else {
                        return "<a style=\"text-decoration:none\" onClick=\"product_start("+row.id+")\" href=\"javascript:;\" title=\"发布\"><i class=\"Hui-iconfont\">&#xe603;</i></a> " +
                            "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"product_edit('商品编辑','product-form',"+row.id+")\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> " +
                            "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"product_del("+row.id+")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                    }
                }
            }
        ];

        _dataTables = App.initDataTables("/item/list/" + cid, _columns);
    });


    // 加载中...
    var index = layer.load(3);

    /**
     * 产品下架
     * */
    function product_stop(id) {
        // 确认消息
        var confirmMsg = '确定下架ID为 '+ id +' 的商品吗？';

        // 提交请求路径
        var url = '/item/stop/' + id;

        // 成功回调方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon: 1, time: 1000});
        }

        App.deleteSinge(confirmMsg, url, successMethod);
    }

    /**
     * 产品发布
     * */
    function product_start(id) {
        // 确认消息
        var confirmMsg = '确定发布ID为 '+ id +' 的商品吗？';

        // 提交请求路径
        var url = '/item/start/' + id;

        // 成功回调方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon: 1, time: 1000});
        }

        App.deleteSinge(confirmMsg, url, successMethod);
    }

    // 初始化 ID 值
    var ID = 0;
    /**
     * 设置 ID
     **/
    function setId(id) {
        ID = id;
    }
    /**
     * 获取 ID
     * */
    function getId() {
        return ID;
    }

    /**
     * 产品新增
     * */
    function product_add(title, url) {
        setId(0);
        images = "";
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /**
     * 产品编辑
     * */
    var images = "";
    function product_edit(title, url, id) {
        setId(id);
        $("#dataTable tbody").on('click', 'tr', function () {
            images = _dataTables.row(this).data().image;
        });
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /**
     * 删除单个商品
     * */
    function product_del(id) {

        // 确认消息
        var confirmMsg = '确定删除ID为 '+ id +' 的商品吗？';

        // 提交请求路径
        var url = '/item/delete/' + id;

        // 成功回调方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon: 2, time: 1000});
        }

        App.deleteSinge(confirmMsg, url, successMethod);
    }

    /**
     * 批量删除
     * */
    function deleteMulti() {

        // 请求路径
        var url = "/item/delete/";

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
