<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>编辑管理员</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-admin-add">
        <input type="text" hidden value="" placeholder="" id="id" name="id">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>管理员：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="username" name="username">
            </div>
        </div>
        <div class="password row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>初始密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" autocomplete="off" value="" placeholder="密码" id="password" name="password">
            </div>
        </div>
        <div class="password row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" autocomplete="off"  placeholder="确认新密码" id="password2" name="password2">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input id="saveButton" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

<jsp:include page="../includes/footer.jsp"/>

<script type="text/javascript">

    var id = parent.userId;
    if (id != "") {
        $(".password").hide();
    }

    $("#id").val(parent.userId);
    $("#username").val(parent.username);

    /**
     * 美化单选
     */
    function radioCheck() {
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });
    }

    $(function () {
        radioCheck();

        $("#form-admin-add").validate({
            rules: {
                username: {
                    required: true,
                    minlength: 1,
                    maxlength: 16,
                    remote: "/user/username/" + parent.userId
                },
                password: {
                    required: true,
                    minlength: 6
                },
                password2: {
                    required: true,
                    minlength: 6,
                    equalTo: "#password"
                }
            },
            messages: {
                username: {
                    required: "请填写用户名",
                    remote: "该用户名已被注册"
                },
                password: {
                    required: "请填写密码",
                },
                password2: {
                    required: "请确认密码",
                    equalTo: "两次密码输入不一致"
                }
            },
            onkeyup: false,
            focusCleanup: false,
            success: "valid",
            submitHandler: function (form) {
                var index = layer.load(3);
                if ($("#select").val() == 2) {
                    $("#description").val("游客");
                } else {
                    $("#description").val("超级管理员");
                }
                $(form).ajaxSubmit({
                    url: "/user/save",
                    type: "POST",
                    success: function (data) {
                        layer.close(index);
                        if (data.status == 200) {
                            parent.refresh();
                            parent.App.msgSuccess(data.message);
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        } else {
                            layer.alert(data.message, {title: '错误信息', icon: 2});
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.close(index);
                        layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {
                            title: '错误信息',
                            icon: 2
                        });
                    }
                });
            }
        });
    });
</script>
</body>
</html>