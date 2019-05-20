<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <title>YMall后台管理系统</title>
    <link rel="Shortcut Icon" href="/static/assets/icon/logo.png" />
    <!-- Meta tag Keywords -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="YMall后台管理系统 v1.0,YMall,YMall购物商城后台管理系统">
    <meta name="description" content="YMall后台管理系统 v1.0，是一款电商后台管理系统，适合中小型CMS后台系统。">

    <!-- css files -->
    <link rel="stylesheet" href="/static/assets/lib/login/style.css" type="text/css" media="all" /> <!-- Style-CSS -->
    <link rel="stylesheet" href="/static/assets/lib/login/font-awesome.css"> <!-- Font-Awesome-Icons-CSS -->
    <link rel="stylesheet" href="/static/assets/lib/layer/2.4/skin/layer.css"> <!-- Font-Awesome-Icons-CSS -->

    <!-- js -->
    <script type="text/javascript" src="/static/assets/lib/jquery/jquery-2.1.4.min.js"></script>
    <script src="/static/assets/lib/login/jquery.vide.min.js"></script>
    <script type="text/javascript" src="/static/assets/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="/static/assets/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
    <script type="text/javascript" src="/static/assets/lib/jquery.validation/1.14.0/validate-methods.js"></script>
    <script type="text/javascript" src="/static/assets/lib/gt.js"></script>

    <style>
        html,body{
            height: 100%;
        }
        .title,h6{
            font-family: "黑体";
        }
        .layui-layer-title {
            padding-right: 305px;
            font-family:"Microsoft Yahei"
        }
        .layui-layer-dialog .layui-layer-content{
            font-family:"Microsoft Yahei"
        }
        .layui-layer-btn{
            font-family:"Microsoft Yahei"
        }
        span.icon1 {
            top: 23%;
        }
        span.icon2 {
            top: 42%;
        }
        #wait {
            text-align: left;
            color: #ffffff;
            margin: 0;
            font-family: "黑体";
        }
    </style>
</head>
<body>
<!-- main -->
<div data-vide-bg="/static/assets/lib/video/Ipad" style="height: 100%;">
    <div class="center-container" style="height: 100%;">
        <!--header-->
        <div class="header-w3l">
            <h1>YMall<span class="title">后台管理系统</span></h1>
        </div>
        <!--//header-->
        <div class="main-content-agile">
            <div class="sub-main-w3">
                <div class="wthree-pro">
                    <h2>Login Here</h2>
                </div>
                <form id="login" action="" method="post" width="100%">
                    <input placeholder="用户名" name="username" id="username" class="user" type="text" required="">
                    <span class="icon1"><i class="fa fa-user" aria-hidden="true"></i></span><br><br>
                    <input  placeholder="密码" name="password" id="password" class="pass" type="password" required="">
                    <span class="icon2"><i class="fa fa-unlock" aria-hidden="true"></i></span><br><br>

                    <div id="captcha">
                        <p id="wait" class="show">正在加载验证码...</p>
                    </div>

                    <div class="sub-w3l">
                        <h6 onclick="forgetPass()" style="cursor: pointer"><a>游客体验账号密码?</a></h6>
                        <div class="right-w3l">
                            <input id="loginButton" type="button" class="login" value="登录">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!--//main-->

        <!--footer-->
        <div class="footer">
            <p>&copy; 2019 YMall. All rights reserved | Design by <a href="https://www.71yuu.com/" target="_blank">Yuu</a></p>
        </div>
        <!--//footer-->

    </div>
</div>
<script type="text/javascript">

    // 登录验证
    var handler = function (captchaObj) {
        captchaObj.appendTo('#captcha');
        captchaObj.onReady(function () {
            $("#wait").hide();
        });
        $('#loginButton').click(function () {
            $("#loginButton").val("登录中...");
            $("#loginButton").attr("disabled","disabled");
            var name=$("#username").val();
            var pass=$("#password").val();
            if(name==""||pass==""){
                layer.msg("用户名或密码不能为空");
                $("#loginButton").val("登录");
                $("#loginButton").removeAttr("disabled");
                return;
            }
            var reg = /^[0-9A-Za-z]+$/;
            if(!reg.exec(name))
            {
                layer.msg("用户名格式有误");
                $("#loginButton").val("登录");
                $("#loginButton").removeAttr("disabled");
                return;
            }
            var result = captchaObj.getValidate();
            if (!result) {
                layer.msg("请完成验证");
                $("#loginButton").val("登录");
                $("#loginButton").removeAttr("disabled");
                return ;
            }
            $.ajax({
                url: '/user/login?t=' + (new Date()).getTime(), // 加随机数防止缓存
                type: 'POST',
                dataType: 'json',
                data: {
                    username: name,
                    password: pass,
                    challenge: result.geetest_challenge,
                    validate: result.geetest_validate,
                    seccode: result.geetest_seccode
                },
                success: function (data) {
                    if(data.status == 200){
                        window.location.href="/";
                    } else {
                        layer.msg(data.message);
                        captchaObj.reset();
                        $("#loginButton").val("登录");
                        $("#loginButton").removeAttr("disabled");
                    }
                },
                error:function(){
                    layer.alert(ERROR_REQUEST_MESSAGE, {title: "错误信息", icon: 2});
                    $("#loginButton").val("登录");
                    $("#loginButton").removeAttr("disabled");
                }
            });
        })
        window.gt = captchaObj;
    };

    // 获取极验
    $.ajax({
        url: '/user/geetestInit?t=' + (new Date()).getTime(), // 加随机数防止缓存
        type: "GET",
        dataType: 'json',
        success: function (data) {
            initGeetest({
                gt: data.gt,
                challenge: data.challenge,
                new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
                offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                product: "popup", // 产品形式，包括：float，popup
                width: "100%"
            }, handler);
        }
    });

    // 提示测试账号密码
    function forgetPass() {
        layer.alert('体验测试账号密码: test | test', {
            icon: 4,
            title: "提示"
        });
    }

    // 获取系统基本信息
    $.ajax({
        url: '/sys/base',
        type: 'GET',
        success: function (data) {
            if (data.status == 200) {
                if (data.result.hasLogNotice == 1) {
                    layer.alert(data.result.logNotice, {
                        title: "通知"
                    });
                }
            } else {
                layer.alert(data.message, {title: '错误信息', icon: 2});
            }
        },
        error: function () {
            layer.alert(ERROR_REQUEST_MESSAGE, {title:'错误信息', icon: 2});
        }
    })

</script>
</body>
</html>
