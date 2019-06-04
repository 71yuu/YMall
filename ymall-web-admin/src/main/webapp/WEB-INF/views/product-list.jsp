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
        <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="App.openAndFull('添加商品','product-add')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加商品</a></span> <span class="r"></div>
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
    App.initZtree("/item/cat/list" , callback);

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
                    return '<img src="'+ data +'" style="width: 80px;height: 70px;"/>'
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
                        return '<a style="text-decoration:none" onClick="product_stop(this, '+ row.id +')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>' +
                            '<a style="text-decoration:none" class="ml-5" onClick="product-edit(\'商品编辑\', \'product-edit\', \'+ row.id +\')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>' +
                            '<a style="text-decoration:none" class="m1-5" onClick="product_del(this, '+ row.id +')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>';
                    } else {
                        return "<a style=\"text-decoration:none\" onClick=\"product_start(this,"+row.id+")\" href=\"javascript:;\" title=\"发布\"><i class=\"Hui-iconfont\">&#xe603;</i></a> " +
                            "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"product_edit('商品编辑','product-edit',"+row.id+")\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> " +
                            "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"product_del(this,"+row.id+")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                    }
                }
            }
        ];

        _dataTables = App.initDataTables("/item/list/" + cid, _columns);
    });


    // 加载中...
    var index = layer.load(3);



    /*产品-添加*/
    function product_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*产品-查看*/
    function product_show(title,url,id){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /*产品-下架*/
    function product_stop(obj,id){
        layer.confirm('确认要下架ID为\''+id+'\'的商品吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/item/stop/'+id,
                dataType: 'json',
                success: function(data){
                    layer.close(index);
                    if(data.success!=true){
                        layer.alert(data.message,{title: '错误信息',icon: 2});
                        return;
                    }
                    refresh();
                    layer.msg('已下架!',{icon: 5,time:1000});
                },
                error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });

        });
    }

    /*产品-发布*/
    function product_start(obj,id){
        layer.confirm('确认要发布ID为\''+id+'\'的商品吗？',{icon:3},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/item/start/'+id,
                dataType: 'json',
                success: function(data){
                    layer.close(index);
                    if(data.success!=true){
                        layer.alert(data.message,{title: '错误信息',icon: 2});
                        return;
                    }
                    refresh();
                    layer.msg('已发布!',{icon: 6,time:1000});
                },
                error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });
        });
    }

    /*产品-编辑*/
    function product_edit(title,url,id){
        setId(id);
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    var ID=0;
    function setId(id){
        ID=id;
    }

    function getId(){
        return ID;
    }

    /*产品-删除*/
    function product_del(obj,id){
        layer.confirm('确认要删除ID为\''+id+'\'的商品吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'DELETE',
                url: '/item/del/'+id,
                dataType: 'json',
                success: function(data){
                    layer.close(index);
                    if(data.success!=true){
                        layer.alert(data.message,{title: '错误信息',icon: 2});
                        return;
                    }
                    productCount();
                    refresh();
                    layer.msg('已删除!',{icon:1,time:1000});
                },
                error:function(XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                },
            });
        });
    }

    /*批量删除*/
    function datadel() {
        var cks=document.getElementsByName("checkbox");
        var count=0,ids="";
        for(var i=0;i<cks.length;i++){
            if(cks[i].checked){
                count++;
                ids+=cks[i].value+",";
            }
        }
        if(count==0){
            layer.msg('您还未勾选任何数据!',{icon:5,time:3000});
            return;
        }
        /*去除末尾逗号*/
        if(ids.length>0){
            ids=ids.substring(0,ids.length-1);
        }
        layer.confirm('确认要删除所选的'+count+'条数据吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'DELETE',
                url: '/item/del/'+ids,
                dataType: 'json',
                success:function(data){
                    layer.close(index);
                    if(data.success!=true){
                        layer.alert(data.message,{title: '错误信息',icon: 2});
                        return;
                    }
                    layer.msg('已删除!',{icon:1,time:1000});
                    productCount();
                    refresh();
                },
                error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });
        });
    }

    function msgSuccess(content){
        layer.msg(content, {icon: 1,time:3000});
    }
</script>
</body>
</html>
