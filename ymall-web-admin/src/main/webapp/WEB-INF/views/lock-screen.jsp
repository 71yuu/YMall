<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Yuu">
    <link rel="Shortcut Icon" href="/static/assets/icon/logo.png" />
    <title>YMall后台管理系统 v1.0</title>
    <meta name="keywords" content="YMall后台管理系统 v1.0,YMall,YMall购物商城后台管理系统">
    <meta name="description" content="YMall后台管理系统 v1.0，是一款电商后台管理系统，适合中小型CMS后台系统。">

    <title>Lock Screen</title>

    <!-- Bootstrap core CSS -->
    <link href="/static/assets/lib/flatlab/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/assets/lib/flatlab/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="/static/assets/lib/flatlab/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="/static/assets/lib/flatlab/css/style.css" rel="stylesheet">
    <link href="/static/assets/lib/flatlab/css/style-responsive.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="/static/assets/lib/flatlab/js/html5shiv.js"></script>
    <script src="/static/assets/lib/flatlab/js/respond.min.js"></script>
    <![endif]-->

</head>

<body class="lock-screen" onload="startTime()">

<div class="lock-wrapper">

    <div id="time"></div>

    <div class="lock-box text-center">
        <img id="avatar" width="85px" height="85px" src="/static/assets/static/h-ui/images/ucnter/avatar-default.jpg" alt="lock avatar"/>
        <h1 id="username">Yuu</h1>
        <span class="locked">Locked</span>
        <form role="form" class="form-inline">
            <div class="form-group col-lg-12">
                <input type="password" placeholder="Password" id="password" class="form-control lock-input">
                <button id="unlockButton" class="btn btn-lock" type="button">
                    <i class="icon-arrow-right"></i>
                </button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="/static/assets/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/static/assets/lib/layer/2.4/layer.js"></script>

<!-- App -->
<script type="text/javascript" src="/static/assets/app/app.js"></script>
<script type="text/javascript" src="/static/assets/app/const.js"></script>
<script>

    // 获取用户信息
    function successMethod(data) {
        $('#username').html(data.result.username);
        if (data.result.file != null && data.result.file != "") {
            $('#avatar').attr('src', data.result.file);
        }
    }
    App.ajax("/user/userInfo", "GET", successMethod);

    // 解锁
    $("#unlockButton").click(function () {
        var password = $("#password").val();
        if (password == null || password == "") {
            layer.msg("密码不能为空");
            return ;
        }
        var data = {password: password};
        function successMethod() {
            window.location.href = "/";
        }
        App.ajaxWithData("/user/unlock", "GET", data, successMethod);
    });

    /**
     * 设置时间
     */
    function startTime()
    {
        var today=new Date();
        var h=today.getHours();
        var m=today.getMinutes();
        var s=today.getSeconds();
        // add a zero in front of numbers<10
        m=checkTime(m);
        s=checkTime(s);
        document.getElementById('time').innerHTML=h+":"+m+":"+s;
        t=setTimeout(function(){startTime()},500);
    }

    /**
     * 计时
     * @param i
     * @returns {*}
     */
    function checkTime(i)
    {
        if (i<10)
        {
            i="0" + i;
        }
        return i;
    }
</script>
</body>
</html>

