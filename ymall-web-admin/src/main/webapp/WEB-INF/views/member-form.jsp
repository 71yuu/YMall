<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>

    <title>添加用户</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="validate-form">
        <input id="id" name="id" type="hidden"/>
        <input id="address" name="address" type="hidden"/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="username" name="username">
            </div>
        </div>
        <div class="row cl password">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>设置密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" autocomplete="off" value="" placeholder="" id="password"
                       name="password">
            </div>
        </div>
        <div class="row cl password">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" autocomplete="off" placeholder="" id="password2"
                       name="password2">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="phone" name="phone">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="@" name="email" id="email">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">头像：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <span class="btn-upload">
                    <input id="file" name="file" type="hidden"/>
                    <img id="fileImg" width="140" height="140" src="/static/assets/icon/avatar.png" alt="..." class="round">
                    <a href="javascript:void(0);" class="btn btn-primary radius upload-btn"><i
                            class="Hui-iconfont">&#xe642;</i> 上传头像</a>
                    <input type="file" id="fileBtn" name="userFile" accept='image/gif,image/jpeg,image/jpg,image/png,image/svg' class="input-file" onchange="upload()">
                </span>
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

<!-- AjaxFileUpload -->
<script type="text/javascript" src="/static/assets/plugins/ajaxfileupload/ajaxfileupload.js"></script>

<script type="text/javascript">

    var id = parent.Id;

    if (id == "") {
        id = 0;
        $("#id").val(0);
    }

    // 编辑，隐藏密码
    if (id != "") {
        $("#id").val(id);
        $(".password").hide();
    }

    // 获取父页面的值
    $("#username").val(parent.username);
    $("#phone").val(parent.phone);
    $("#email").val(parent.email);
    $("#description").val(parent.description);
    $("#phone").val(parent.phone);

    if (parent.sex == '男') {
        $("#sex-1").attr('checked', 'checked');
        radioCheck();
    } else if (parent.sex == '女') {
        $("#sex-2").attr('checked', 'checked');
        radioCheck();
    } else if (parent.sex == '保密') {
        $("#sex-3").attr('checked', 'checked');
        radioCheck();
    }
    if (parent.file != "") {
        $("#file").val(parent.file);
        $("#fileImg").attr("src", parent.file);
    }

    /**
     * iCheck 美化单选按钮
     **/
    function radioCheck() {
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });
    }

    /**
     * 文本输入限制
     */
    $(".textarea").Huitextarealength({
        minlength: 0,
        maxlength: 100
    });

    /**
     * 上传头像
     **/
    function upload() {
        $.ajaxFileUpload({
            url: 'upload',
            type: 'post',
            dataType: 'json',
            fileElementId: 'fileBtn',
            success: function (data) {
                if (data.status == 200) {
                    $("#file").val(data.imagePath);
                    $("#fileImg").attr("src", data.imagePath);
                } else {
                    layer.alert(data.message, {title: "错误信息", icon: 2});
                }
            },
            error: function () {
                layer.alert(ERROR_REQUEST_MESSAGE, {title: "错误信息", icon: 2});
            }
        })
    }

    $(function () {
        /**
         * 初始化 iCheck
         */
        radioCheck();


        $("#validate-form").validate({
            ignore: ":hidden", //不验证的元素
            rules: {
                username: {
                    required: true,
                    minlength: 2,
                    maxlength: 16,
                    remote: "/member/username?id=" + id
                },
                password: {
                    required: true,
                    minlength: 6,
                },
                password2: {
                    required: true,
                    minlength: 6,
                    equalTo: "#password"
                },
                phone: {
                    required: true,
                    isMobile: true,
                    remote: "/member/phone?id=" + id
                },
                email: {
                    required: true,
                    email: true,
                    remote: "/member/email?id=" + id
                },
                sex: {
                    required: true,
                },
                province: {
                    required: true,
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
                },
                phone: {
                    required: "请输入手机号",
                    isMobile: "请输入正确的手机格式",
                    remote: "该手机号已被注册"
                },
                email: {
                    required: "请输入邮箱",
                    remote: "该邮箱已被注册"
                },
                sex: {
                    required: "请输入性别"
                },
                province: {
                    required: "请选择所在城市"
                }
            },
            submitHandler: function (form) {
                var fileVal = $("#file").val();
                if (fileVal == null || fileVal == "") {
                    layer.msg("请上传头像", {icon: 1, time: 3000});
                    return false;
                }
                var province = $("#province").find("option:selected").text();
                var city = $("#city").find("option:selected").text();
                var district = $("#district").find("option:selected").text();
                $("#address").val(province + " " + city + " " + district);
                var index = layer.load(3);
                $(form).ajaxSubmit({
                    url: "/member/save",
                    type: "POST",
                    success: function (data) {
                        layer.close(index);
                        if (data.status == 200) {
                            if (parent.location.pathname != '/') {
                                parent.refresh();
                                parent.App.msgSuccess(data.message);
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                            } else {
                                layer.confirm('添加成功!', {
                                    btn: ['确认'], icon: 1
                                }, function () {
                                    var index = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(index);
                                });
                            }
                        } else {
                            layer.alert(data.message, {title: '错误信息', icon: 2});
                        }
                    },
                    error: function () {
                        layer.close(index);
                        layer.alert(ERROR_REQUEST_MESSAGE, {title: "错误信息", icon: 2});
                    }
                });
            }
        });
    });

</script>
</body>
</html>
