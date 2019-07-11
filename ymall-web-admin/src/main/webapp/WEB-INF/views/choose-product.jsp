<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>选择商品</title>
</head>
<style>
    .table>tbody>tr>td{
        text-align:center;
    }
</style>
<body>
<div style="padding:2vw">
    <table id="dataTable" class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
        <thead>
        <tr class="text-c">
            <th width="70">ID</th>
            <th width="70">缩略图</th>
            <th width="130">商品名称</th>
            <th width="90">描述</th>
            <th width="60">单价</th>
            <th width="95">创建日期</th>
            <th width="95">更新日期</th>
            <th width="50">状态</th>
            <th width="80">操作</th>
        </tr>
        </thead>
    </table>
</div>

<jsp:include page="../includes/footer.jsp"/>

<!-- DataTables -->
<script type="text/javascript" src="/static/assets/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">

    /**
     * 初始化 DataTables
     */
    var _dataTable;
    $(function(){
        const _columns = [
            { "data": "id"},
            {
                "data": "image",
                render: function (data, type, row, meta) {
                    return '<img src="'+ data.split(',')[0] +'" style="width:80px; height: 60px;"/>';
                }
            },
            {
                "data": "title",
                render: function (data, type, row, meta) {
                    if (data.length > 20) {
                        return '<span title='+ data +'>'+ data.substr(0, 50) +'...</span>'
                    } else {
                        return '<span title='+ data +'>'+ data +'</span>';
                    }
                    return data;
                }
            },
            {
                "data": "sellPoint",
                render: function (data, type, row, meta) {
                    if (type == 'display') {
                        if (data.length > 20) {
                            return '<span title=' + data + '>' + data.substr(0, 20) + '...</span>';
                        } else {
                            return '<span title=' + data + '>' + data + '</span>';
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
                render : function(data,type, row, meta) {
                    if(data==0){
                        return "<span class=\"label label-defant radius td-status\">已下架</span>";
                    }else if(data==1){
                        return "<span class=\"label label-success radius td-status\">已发布</span>";
                    }else{
                        return "<span class=\"label label-warning radius td-status\">其它态</span>";
                    }
                }
            },
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return "<input onclick='chooseProduct()' class='btn btn-danger-outline size-S radius' type='button' value='选择该商品'>";
                }
            }
        ];

        _dataTable = App.initDataTables("/item/list/-1", _columns);
    });


    /**
     * 选择商品
     * @param id
     */
    function chooseProduct(id) {
        $("#dataTable tbody").on('click', 'tr', function () {
            var status = _dataTable.row(this).data().status;
            if (status == 0) {
                layer.alert("此商品已下架，暂时不能选择噢！", {title: "错误消息", icon: 2});
                return ;
            }
            var id = _dataTable.row(this).data().id;
            var title = _dataTable.row(this).data().title;
            parent.setIdAndTitle(id, title);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        })
    }

</script>
</body>
</html>
