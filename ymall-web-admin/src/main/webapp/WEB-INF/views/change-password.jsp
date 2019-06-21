<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>修改密码 - 会员管理</title>
</head>
<body>
<article class="page-container">
    <form action="" method="" class="form form-horizontal" id="validate-form">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账号：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span id="username"></span> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" autocomplete="off" placeholder="密码长度不得小于6位" name="password" id="password">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" autocomplete="off" placeholder="请再次输入密码" name="password2" id="password2">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

<jsp:include page="../includes/footer.jsp"/>

<script type="text/javascript">

    // 接收父页面传的 id
    var id = parent.Id;
    $("#username").html(parent.username);

    /**
     * 修改密码
     * @param data
     */
    function successMethod(data) {
        parent.App.msgSuccess(data.message);
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
    Validate.validate("/member/changePass/" + id, "", successMethod);

</script>
</body>
</html>