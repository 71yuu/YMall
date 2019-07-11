<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>订单列表</title>

</head>
<style>
    .table > tbody > tr > td {
        text-align: center;
    }
</style>
<body>

<div>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 订单管理 <span
            class="c-gray en">&gt;</span> 订单列表 <a class="btn btn-success radius r"
                                                  style="line-height:1.6em;margin-top:3px"
                                                  href="javascript:location.replace(location.href);" title="刷新"><i
            class="Hui-iconfont">&#xe68f;</i></a></nav>
    <form class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l">
                <a href="javascript:;" onclick="deleteMulti()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
                <a href="javascript:;" onclick="orderPrint()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe652;</i> 订单打印</a>
            </span>
            <span class="l" style="margin-top: 5px;margin-left: 1200px;">
              <select class="select" size="1" id="orderType" style="height:35px;width: 200px;">
                <option value="-1" selected>所有订单</option>
                <option value="0">待付款订单</option>
                <option value="2">待发货订单</option>
                <option value="4">交易成功订单</option>
                <option value="5">交易失败订单</option>
              </select>
            </span>
        </div>
        <div class="mt-20">
            <div class="mt-20" style="margin-bottom: 70px">
                <table id="dataTable" class="table table-border table-bordered table-bg table-hover table-sort"
                       width="100%">
                    <thead>
                    <tr class="text-c">
                        <th width="25"><input type="checkbox" class="minimal icheck_master"/></th>
                        <th width="80">订单号</th>
                        <th width="80">支付金额(￥)</th>
                        <th width="70">用户账号</th>
                        <th width="100">物流号</th>
                        <th width="100">备注</th>
                        <th width="100">创建时间</th>
                        <th width="100">支付时间</th>
                        <th width="100">关闭时间</th>
                        <th width="100">完成时间</th>
                        <th width="80">订单状态</th>
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
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return '<input id="' + row.id + '" type="checkbox" class="minimal" />';
                }
            },
            {"data": "id"},
            {"data": "payment"},
            {"data": "buyerNick"},
            {
                "data": "shippingCode",
                render: function (data, type, row, meta) {
                    if (data == null || data == "") {
                        return "暂无物流信息！";
                    } else {
                        return data;
                    }
                }
            },
            {
                "data": "buyerMessage",
                render: function (data, type, row, meta) {
                    if (data == null || data == "") {
                        return "暂无买家备注信息！"
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
                "data": "paymentTime",
                render: function (data, type, row, meta) {
                    if (data == null) {
                        if (row.status == 0) {
                            return "该买家还未支付!";
                        } else if (row.status == 5) {
                            return "交易已关闭！";
                        } else if (row.status == 6) {
                            return "交易失败！";
                        }
                    } else {
                        return date(data);
                    }
                }
            },
            {
                "data": "closeTime",
                render: function (data, type, row, meta) {
                    if (data == null) {
                        if (row.status == 4) {
                            return "交易成功！";
                        } else if (row.status == 6) {
                            return "交易失败！";
                        } else {
                            return "交易正在进行中！";
                        }
                    } else {
                        return date(data);
                    }
                }
            },
            {
                "data": "endTime",
                render: function (data, type, row, meta) {
                    if (data == null) {
                        if (row.status == 5) {
                            return "交易关闭！";
                        } else if (row.status == 6) {
                            return "交易失败！";
                        } else {
                            return "交易正在进行中！";
                        }
                    } else {
                        return date(data);
                    }
                }
            },
            {
                "data": "status",
                render: function (data, type, row, meta) {
                    if (data == 0) {
                        return "<span class=\"label label-defant radius td-status\">待支付</span>";
                    } else if (data == 1) {
                        return "<span class=\"label label-warning radius td-status\">已支付</span>";
                    } else if (data == 2) {
                        return "<span class=\"label label-primary radius td-status\">待发货</span>";
                    } else if (data == 3) {
                        return "<span class=\"label label-secondary radius td-status\">已发货</span>";
                    } else if (data == 4) {
                        return "<span class=\"label label-success radius td-status\">交易成功</span>";
                    } else if (data == 5) {
                        return "<span class=\"label label-danger radius td-status\">交易关闭</span>";
                    } else if (data == 6) {
                        return "<span class=\"label label-defant radius td-status\">支付失败</span>";
                    } else {
                        return "<span class=\"label label-warning radius td-status\">其它态</span>";
                    }
                }
            },
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return "<a title=\"发货\" href=\"javascript:;\" onclick=\"order_deliver(" + row.id + ", "+ row.status +")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe603;</i></a>" +
                        "<a title=\"取消订单\" href=\"javascript:;\" onclick=\"order_cancel(" + row.id + ", "+ row.status +")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe60b;</i></a>" +
                        "<a title=\"删除\" href=\"javascript:;\" onclick=\"order_del(this," + row.id + ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                }
            }
        ];

        _dataTables = App.initDataTables("/order/list/", _columns);
    });


    var orderId = "";

    function orderPrint() {
        var _idArray = new Array();
        // 获取全部 Checkbox 集合
        var _checkbox = $('input[type="checkbox"].minimal');

        // 将选中的元素 ID 放入数组中
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                orderId = _id;
                _idArray.push(_id);
            }
        });

        // 判断用户是否选择了数据项
        if (_idArray.length === 0) {
            layer.msg('您还未勾选任何数据！', {icon: 5, time: 3000});
            return;
        }

        // 判断是否只选择了一个数据项
        if (_idArray.length > 1) {
            layer.msg('只能选择一个订单！', {icon: 5, time: 3000});
            return;
        }

        var index = layer.open({
            type: 2,
            title: '打印预览',
            content: 'order-print'
        });
        layer.full(index);
    }

    /**
     * 发货
     **/
    function order_deliver(id, status) {
        if (status == 1 || status == 2) {
            orderId = id;
            layer_show("发货", "order-deliver", 500, 280);
        } else if (status == 0) {
            layer.alert("订单还未付款", {title: "错误信息", icon: 2});
        } else if (status == 3 || status == 4) {
            layer.alert("订单已发货", {title: "错误信息", icon: 2});
        } else if (status == 5) {
            layer.alert("订单已关闭", {title: "错误信息", icon: 2});
        }
    }

    /**
     * 备注
     **/
    var oldRemark = "";
    function order_remark(message, id) {
        oldRemark = message;
        orderId = id;
        layer_show("备注", "order-remark", 500, 250);
    }

    /**
     * 取消订单
     **/
    function order_cancel(id, status) {
        if (status == 4) {
            layer.alert("交易已成功，无法取消！");
            return;
        } else if (status == 5) {
            layer.alert("交易已经取消！");
            return;
        } else if (status == 6) {
            layer.alert("交易已经失败");
            return;
        }

        // 确认消息
        var confirmMsg = '确定取消ID为 '+ id +' 的订单吗？';

        // 提交请求路径
        var url = '/order/cancel/' + id;

        // 成功回调方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon: 1, time: 1000});
        }

        App.deleteSinge(confirmMsg, url, successMethod);
    }

    /**
     * 删除订单
     * */
    function order_del(obj, id) {
        // 确认消息
        var confirmMsg = '确定删除ID为 '+ id +' 的订单吗？';

        // 提交请求路径
        var url = '/order/delete/' + id;

        // 成功回调方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon: 1, time: 1000});
        }

        App.deleteSinge(confirmMsg, url, successMethod);

    }

    /**
     * 批量删除
     * */
    function deleteMulti() {

        // 请求路径
        var url = "/order/delete/";

        // 请求成功执行方法
        function successMethod(data) {
            refresh();
            layer.msg(data.message, {icon:1, time:1000});
        }

        App.deleteMulti(url, successMethod);
    }

    /**
     * 选择订单类型
     */
    $("#orderType").on("change",function(){
        let type = $("#orderType").val()

        let param = {
            "status": type
        }

        _dataTables.settings()[0].ajax.data = param
        _dataTables.ajax.reload()
    })

</script>
</body>
</html>
