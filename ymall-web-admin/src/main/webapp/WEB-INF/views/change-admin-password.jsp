<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>修改密码</title>
</head>
<body>
<article class="page-container">
    <form action="" method="" class="form form-horizontal" id="form-change-password">
        <input type="text" class="input-text" hidden name="id" id="id">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账号：</label>
            <div class="formControls col-xs-8 col-sm-9"><span id="username"></span></div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" autocomplete="off" placeholder="密码长度不得小于6位" name="password"
                       id="password">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" autocomplete="off" placeholder="请再次输入密码" name="password2"
                       id="password2">
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

    $("#id").val(parent.userId);
    $("#username").html(parent.username);
    $(function () {
        $("#form-change-password").validate({
            rules: {
                password: {
                    required: true,
                    minlength: 6,
                    maxlength: 16
                },
                password2: {
                    required: true,
                    minlength: 6,
                    maxlength: 16,
                    equalTo: "#password"
                },
            },
            onkeyup: false,
            focusCleanup: false,
            success: "valid",
            submitHandler: function (form) {
                var index = layer.load(3);
                $(form).ajaxSubmit({
                    url: "/user/changePass",
                    type: "POST",
                    success: function (data) {
                        layer.close(index);
                        if (data.status == 200) {
                            parent.App.msgSuccess(data.message);
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        } else {
                            layer.alert('提交失败! ' + data.message, {title: '错误信息', icon: 2});
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.close(index);
                        layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
                    }
                });
            }
        });
    });
</script>
</body>
</html>