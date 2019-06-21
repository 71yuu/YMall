<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>发货</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="validate-form">
        <input type="text" hidden id="orderId" name="orderId">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>快递名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select id="shippingName" class="select" name="shippingName" size="1">

                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>快递单号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" autocomplete="off" value="" placeholder="" id="shippingCode"
                       name="shippingCode">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input id="saveButton" class="btn btn-primary radius" type="submit" value="提交">
            </div>
        </div>
    </form>
</article>

<jsp:include page="../includes/footer.jsp"/>
<script type="text/javascript">

    // 初始化快递数据
    $("#orderId").val(parent.orderId);
    $.ajax({
        url: '/express/list',
        type: 'GET',
        success: function (data) {
            var i = 0;
            for (i = 0; i < data.data.length; i++) {
                $("#shippingName").append("<option value='" + data.data[i].expressName + "'>" + data.data[i].expressName + "</option>");
            }
        }
    })

    /**
     * 提交成功后执行的方法
     * */
    function successMethod() {
        parent.refresh();
        parent.App.msgSuccess("发货成功!");
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
    Validate.validate('/order/deliver', "", successMethod);
</script>
</body>
</html>