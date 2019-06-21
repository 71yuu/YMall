<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>订单信息</title>
</head>
<style>
    .title {
        margin-bottom: 15px;
    }

    .item {
        width: 24%;
        text-align: left;
        display: inline-block;
        line-height: 30px;
    }

    .itemAdress {
        width: 50%;
        text-align: left;
        display: inline-block;
        line-height: 30px;
    }
</style>
<body>
<div class="page-container">
    <div id="print">
        <form class="form form-horizontal">
            <div>
                <h3 class="text-c title">YMall商城订单信息</h3>
                <div class="item">会员名称：<span id="customerName"></span></div>
                <div class="item">下单时间：<span id="createTime"></span></div>
                <div class="item">订单编号：<span id="orderCode"></span></div>
                <div class="item">支付方式：<span id="payType"></span></div>
                <div class="item">付款时间：<span id="payTime"></span></div>
                <div class="item">发货时间：<span id="deliverTime"></span></div>
                <div class="item">发货单号：<span id="expressId"></span></div>
                <div class="item">收货人：<span id="receiver"></span></div>
                <div class="item">手机：<span id="telephone"></span></div>
                <div class="itemAdress">收货地址：<span id="expressAdress"></span></div>
            </div>
        </form>

        <div class="mt-20">
            <table class="table table-border table-bg table-bordered table-striped table-hover">
                <thead>
                <tr class="text-c">
                    <th width="40">商品名称</th>
                    <th width="40">商品ID号</th>
                    <th width="20">价格(￥)</th>
                    <th width="10">数量(件)</th>
                    <th width="20">小计(￥)</th>
                </tr>
                </thead>
                <tbody id="products">

                </tbody>

            </table>
        </div>

        <div class="text-r" style="margin-top: 1vw">
            订单总金额：￥<span id="orderPriceAll"></span>
        </div>
        <div class="text-l">订单备注：<span id="remark"></div>
    </div>

    <div class="row cl" style="margin-top: 1vw">
        <div class="text-c">
            <input id="saveButton" class="btn btn-primary radius" onclick="print()" value="打印">
        </div>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>

<!-- jQuery print -->
<script type="text/javascript" src="/static/assets/lib/jQuery.print.js"></script>
<script type="text/javascript">
    function print() {
        jQuery('#print').print();
    }

    //加载数据
    function successMethod(data) {
        $("#customerName").html(data.result.tbOrder.buyerNick);
        $("#createTime").html(date(data.result.tbOrder.created));
        $("#orderCode").html(data.result.tbOrder.id);
        if (data.result.tbOrder.paymentType == 1) {
            $("#payType").html("在线支付");
        } else if (data.result.tbOrder.paymentType == 2) {
            $("#payType").html("货到付款");
        } else {
            $("#payType").html("未支付");
        }
        if (data.result.tbOrder.consignTime == null) {
            $("#deliverTime").html("未发货");
        }
        $("#deliverTime").html(date(data.result.tbOrder.consignTime));
        $("#expressId").html(data.result.tbOrder.shippingCode);
        $("#expressAdress").html(data.result.tbOrderShipping.receiverAddress);
        if (data.result.tbOrder.paymentTime == null) {
            $("#payTime").html("未付款")
        } else {
            $("#payTime").html(date(data.result.tbOrder.paymentTime));
        }
        $("#receiver").html(data.result.tbOrderShipping.receiverName);
        $("#telephone").html(data.result.tbOrderShipping.receiverPhone);

        var products = data.result.tbOrderItemList;
        var i = 0;
        for (i = 0; i < products.length; i++) {
            $("#products").append("<tr class='text-c'><td>" + products[i].title + "</td>" +
                "<td>" + products[i].itemId + "</td><td>" + products[i].price + "</td><td>" + products[i].num + "</td>" +
                "<td>" + products[i].totalFee + "</td></tr>");
        }
        if (data.result.tbOrder.postFee == null || data.result.tbOrder.postFee == "") {
            data.result.tbOrder.postFee = 0;
        }
        $("#expressCost").html(data.result.tbOrder.postFee);
        $("#orderPriceAll").html(data.result.tbOrder.payment);
        $("#remark").html(data.result.tbOrder.buyerMessage);
    }
    App.ajax('/order/detail/' + parent.orderId, 'GET', successMethod);

</script>
</body>
</html>