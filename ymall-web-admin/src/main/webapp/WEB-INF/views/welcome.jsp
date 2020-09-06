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

    <!-- Bootstrap core CSS -->
    <link href="/static/assets/lib/flatlab/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/assets/lib/flatlab/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="/static/assets/lib/flatlab/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="/static/assets/lib/flatlab/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <link rel="stylesheet" href="/static/assets/lib/flatlab/css/owl.carousel.css" type="text/css">
    <!-- Custom styles for this template -->
    <link href="/static/assets/lib/flatlab/css/style.css" rel="stylesheet">
    <link href="/static/assets/lib/flatlab/css/style-responsive.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="/static/assets/lib/flatlab/js/html5shiv.js"></script>
    <script src="/static/assets/lib/flatlab/js/respond.min.js"></script>
    <![endif]-->
</head>
<style>
    #main-content {
        margin-left: 20px;
        margin-top: -50px;
    }
    .site-footer .text-center a{
        color: #53bee6;
    }
</style>
<body>
<section id="container" >
    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
            <!--state overview start-->
            <div class="row state-overview">
                <div class="col-lg-4 col-sm-6">
                    <section class="panel">
                        <div class="symbol terques">
                            <i class="icon-user"></i>
                        </div>
                        <div class="value">
                            <h1 class="count">
                                ...
                            </h1>
                            <p>用户总数</p>
                        </div>
                    </section>
                </div>
                <div class="col-lg-4 col-sm-6">
                    <section class="panel">
                        <div class="symbol red">
                            <i class="icon-tags"></i>
                        </div>
                        <div class="value">
                            <h1 class=" count2">
                                ...
                            </h1>
                            <p>商品总数</p>
                        </div>
                    </section>
                </div>
                <div class="col-lg-4 col-sm-6">
                    <section class="panel">
                        <div class="symbol yellow">
                            <i class="icon-shopping-cart"></i>
                        </div>
                        <div class="value">
                            <h1 class=" count3">
                                ...
                            </h1>
                            <p>订单总数</p>
                        </div>
                    </section>
                </div>
            </div>
            <!--state overview end-->

            <div class="row">

                <div class="col-lg-6">
                    <!--user info table start-->
                    <section class="panel">
                        <div class="panel-body">
                            <a href="http://blog.Yuu.cn" target="_blank" class="task-thumb">
                                <img width="83px" height="83px" src="/static/assets/lib/flatlab/img/avatar1.jpg" alt="">
                            </a>
                            <div class="task-thumb-details">
                                <h1><a href="https://blog.csdn.net/qq_37581282" target="_blank">Yuu</a></h1>
                                <p>Author</p>
                            </div>
                        </div>
                        <table class="table table-hover personal-task">
                            <tbody>
                            <tr>
                                <td>
                                    <i class=" icon-tasks"></i>
                                </td>
                                <td>
                                    <a target="_blank" href="https://blog.csdn.net/qq_37581282">
                                        个人简介
                                    </a>
                                </td>
                                <td> <span style="margin-top: -1px" class="label label-primary pull-right r-activity">01</span></td>
                            </tr>
                            <tr>
                                <td>
                                    <i class="icon-book"></i>
                                </td>
                                <td>
                                    <a target="_blank" href="https://blog.csdn.net/qq_37581282/">
                                        个人博客
                                    </a>
                                </td>
                                <td> <span style="margin-top: -1px" class="label label-info pull-right r-activity">01</span></td>
                            </tr>
                            <tr>
                                <td>
                                    <i class=" icon-music"></i>
                                </td>
                                <td>
                                    <a target="_blank" href="https://music.163.com/#/user/home?id=327286657">
                                        网易云音乐
                                    </a>
                                </td>
                                <td> <span style="margin-top: -1px" class="label label-info pull-right r-activity">01</span></td>
                            </tr>
                            <tr>
                                <td>
                                    <i class="icon-envelope"></i>
                                </td>
                                <td>
                                    <a href="mailto:yuu71@qq.com" target="_blank">yuu71@qq.com</a>
                                </td>
                                <td> <span style="margin-top: -1px" class="label label-warning pull-right r-activity">01</span></td>
                            </tr>
                            <tr>
                                <td>
                                    <i class=" icon-bell-alt"></i>
                                </td>
                                <td>
                                    <a target="_blank" href="https://github.com/71yuu">
                                        Github
                                    </a>
                                </td>
                                <td> <span style="margin-top: -1px" class="label label-success pull-right r-activity">01</span></td>
                            </tr>
                            </tbody>
                        </table>
                    </section>
                    <!--user info table end-->
                </div>

                <div class="col-lg-6">
                    <!--widget start-->
                    <section class="panel">
                        <header class="panel-heading tab-bg-dark-navy-blue">
                            <ul class="nav nav-tabs nav-justified ">
                                <li class="active">
                                    <a href="#popular" data-toggle="tab">
                                        公告
                                    </a>
                                </li>
                            </ul>
                        </header>
                        <div class="panel-body" >
                            <div class="tab-content tasi-tab">
                                <div class="tab-pane active" id="popular">
                                    <article class="media">
                                        <a class="pull-left thumb p-thumb">
                                            <img src="/static/assets/lib/flatlab/img/avatar-mini.jpg">
                                        </a>
                                        <div class="media-body">
                                            <a class="p-head" href="#">
                                                尊敬的 <span id="username"></span>，
                                                <span id="hello"></span>
                                                现在时间是：<span id="currentTime"></span></a>
                                            <p style="font-size: 20px;"><br>YMALL 商城系统是基于 SSM 的电商 B2C 购物商城系统。前后端开发分离，前台基于 Vue 开发，主要功能有会员登录注册、首页管理、分类商品页、商品详情页、购物车、订单、在线支付、会员中心等。后台管理系统主要包括登录、首页管理、分类管理、商品管理、订单管理、会员管理、管理员管理、报表管理、快递管理等等。<br><br>
                                                By: Yuu
                                            </p>
                                        </div>
                                    </article>
                                </div>
                            </div>
                        </div>
                    </section>
                    <!--widget end-->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <!--timeline start-->
                    <section class="panel">
                        <div class="panel-body">
                            <div class="text-center mbot30">
                                <h3 class="timeline-title">YMall项目日志</h3>
                                <p class="t-info">This is a project timeline</p>
                            </div>

                            <div class="timeline">
                                <article class="timeline-item alt">
                                    <div class="timeline-desk">
                                        <div class="panel">
                                            <div class="panel-body">
                                                <span class="arrow-alt"></span>
                                                <span class="timeline-icon purple"></span>
                                                <span class="timeline-date">04:00 pm</span>
                                                <h1 class="purple">2019年7月12日</h1>
                                                <p><a href="https://www.71yuu.com/" target="_blank">Yuu</a> 正在进行项目评审与答辩</p>
                                            </div>
                                        </div>
                                    </div>
                                </article>
                                <article class="timeline-item">
                                    <div class="timeline-desk">
                                        <div class="panel">
                                            <div class="panel-body">
                                                <span class="arrow-alt"></span>
                                                <span class="timeline-icon purple"></span>
                                                <span class="timeline-date">02:05 am</span>
                                                <h1 class="purple">2019年7月11日</h1>
                                                <p><a href="https://www.71yuu.com/" target="_blank">Yuu</a> 完成
                                                    <a href="https://github.com/71yuu/YMall" target="_blank">ymall-web-ui</a>项目
                                            </div>
                                        </div>
                                    </div>
                                </article>
                                <article class="timeline-item alt">
                                    <div class="timeline-desk">
                                        <div class="panel">
                                            <div class="panel-body">
                                                <span class="arrow-alt"></span>
                                                <span class="timeline-icon purple"></span>
                                                <span class="timeline-date">08:00 am</span>
                                                <h1 class="purple">2019年6月22日</h1>
                                                <p><a href="https://www.71yuu.com/" target="_blank">Yuu</a> 启动
                                                    <a href="https://github.com/71yuu/YMall" target="_blank">ymall-web-ui</a>项目
                                            </div>
                                        </div>
                                    </div>
                                </article>
                                <article class="timeline-item">
                                    <div class="timeline-desk">
                                        <div class="panel">
                                            <div class="panel-body">
                                                <span class="arrow"></span>
                                                <span class="timeline-icon red"></span>
                                                <span class="timeline-date">07:00 am</span>
                                                <h1 class="red">2019年6月21日</h1>
                                                <p><a href="https://www.71yuu.com/" target="_blank">Yuu</a> 完成
                                                    <a href="https://github.com/71yuu/YMall" target="_blank">YMall 后台管理系统</a>
                                            </div>
                                        </div>
                                    </div>
                                </article>
                                <article class="timeline-item alt">
                                    <div class="timeline-desk">
                                        <div class="panel">
                                            <div class="panel-body">
                                                <span class="arrow-alt"></span>
                                                <span class="timeline-icon green"></span>
                                                <span class="timeline-date">07:00 pm</span>
                                                <h1 class="green">2019年6月20日</h1>
                                                <p><a href="https://www.71yuu.com/" target="_blank">Yuu</a> 启动
                                                    <a href="https://github.com/71yuu/YMall" target="_blank">YMall</a> 项目
                                            </div>
                                        </div>
                                    </div>
                                </article>
                                <article class="timeline-item">
                                    <div class="timeline-desk">
                                        <div class="panel">
                                            <div class="panel-body">
                                                <span class="arrow"></span>
                                                <span class="timeline-icon blue"></span>
                                                <span class="timeline-date">05:00 pm</span>
                                                <h1 class="blue">2019年6月20日</h1>
                                                <p><a href="https://www.71yuu.com/" target="_blank">Yuu</a> 完成了
                                                    数据库设计文档
                                            </div>
                                        </div>
                                    </div>
                                </article>
                                <article class="timeline-item alt">
                                    <div class="timeline-desk">
                                        <div class="panel">
                                            <div class="panel-body">
                                                <span class="arrow-alt"></span>
                                                <span class="timeline-icon purple"></span>
                                                <span class="timeline-date">02:30 pm</span>
                                                <h1 class="purple">2019年6月19日</h1>
                                                <p><a href="https://www.71yuu.com/" target="_blank">Yuu</a> 完成了
                                                    需求分析文档
                                            </div>
                                        </div>
                                    </div>
                                </article>
                                <article class="timeline-item">
                                <div class="timeline-desk">
                                    <div class="panel">
                                        <div class="panel-body">
                                            <span class="arrow"></span>
                                            <span class="timeline-icon light-green"></span>
                                            <span class="timeline-date">05:00 pm</span>
                                            <h1 class="light-green">2019年6月18日</h1>
                                            <p><a href="https://www.71yuu.com/" target="_blank">Yuu</a> 完成了
                                                项目计划文档
                                        </div>
                                    </div>
                                </div>
                            </article>
                            </div>

                            <div class="clearfix">&nbsp;</div>
                        </div>
                    </section>
                    <!--timeline end-->
                </div>
                <div class="col-lg-4">

                    <!--weather statement start-->
                    <section class="panel">
                        <div class="weather-bg">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <i id="weather" class="icon-cloud"></i>
                                        <span id="city">...</span>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="degree">
                                            <span id="degree">...</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <footer class="weather-category">
                            <ul>
                                <li class="active">
                                    <h5>湿度</h5>
                                    <span id="humidity">...</span>
                                </li>
                                <li>
                                    <h5>空气质量</h5>
                                    <span id="airCondition">...</span>
                                </li>
                                <li>
                                    <h5>风力</h5>
                                    <span id="wind">...</span>
                                </li>
                            </ul>
                        </footer>
                    </section>
                    <!--weather statement end-->
                </div>

                <div class="col-lg-4">
                    <!--latest product info start-->
                    <section class="panel post-wrap pro-box">
                        <aside>
                            <div class="post-info">
                                <span class="arrow-pro right"></span>
                                <div class="panel-body">
                                    <h1><strong>popular</strong> <br> 本周热门商品</h1>
                                    <div class="desk yellow">
                                        <h3 id="hot-title">商品名称</h3>
                                        <p>共卖出 <span id="hot-num"></span> 件</p>
                                    </div>
                                    <div class="post-btn">
                                        <a href="javascript:;">
                                            <i class="icon-chevron-sign-left"></i>
                                        </a>
                                        <a href="javascript:;">
                                            <i class="icon-chevron-sign-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </aside>
                        <aside class="post-highlight yellow v-align">
                            <div class="panel-body text-center">
                                <div class="pro-thumb">
                                    <img id="hot-img" width="112px" height="112px" src="/static/assets/icon/none.png" alt="">
                                </div>
                            </div>
                        </aside>
                    </section>
                    <!--latest product info end-->
                    <!--twitter feedback start-->
                    <section class="panel post-wrap pro-box">
                        <aside>
                            <div class="post-info">
                                <span class="arrow-pro left"></span>
                                <div class="panel-body">
                                    <div class="text-center twite">
                                        <h1>Follow Me</h1>
                                    </div>

                                    <footer class="social-footer">
                                        <ul>
                                            <li>
                                                <a href="https://github.com/71yuu" target="_blank">
                                                    <i class="icon-github"></i>
                                                </a>
                                            </li>
                                            <li class="active">
                                                <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1225459207&site=qq&menu=yes">
                                                    <img style="margin-bottom: 3px" width="24px" height="24px" src="/static/assets/icon/qq.png"/>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="https://www.71yuu.com/" target="_blank">
                                                    <i class="icon-book"></i>
                                                </a>
                                            </li>
                                        </ul>
                                    </footer>
                                </div>
                            </div>
                        </aside>
                    </section>
                    <!--twitter feedback end-->
                </div>
            </div>
        </section>
    </section>
    <!--main content end-->
    <!--footer start-->
    <footer class="site-footer">
        <div class="text-center">
            Copyright &copy;2019 <a href="https://www.71yuu.com/" target="_blank">Yuu</a> All Rights Reserved.
            本后台系统由<a href="http://www.h-ui.net/" target="_blank"> H-ui</a>、<a href="https://themeforest.net/item/flatlab-bootstrap-3-responsive-admin-template/5902687" target="_blank">FlatLab </a>提供前端静态页面支持
            <a href="#" class="go-top">
                <i class="icon-angle-up"></i>
            </a>
        </div>
    </footer>
    <!--footer end-->
</section>

<!-- js placed at the end of the document so the pages load faster -->
<script src="/static/assets/lib/flatlab/js/jquery.js"></script>
<script src="/static/assets/lib/flatlab/js/jquery-1.8.3.min.js"></script>
<script src="/static/assets/lib/flatlab/js/bootstrap.min.js"></script>
<script src="/static/assets/lib/flatlab/js/jquery.scrollTo.min.js"></script>
<script src="/static/assets/lib/flatlab/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="/static/assets/lib/flatlab/js/jquery.sparkline.js" type="text/javascript"></script>
<script src="/static/assets/lib/flatlab/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
<script src="/static/assets/lib/flatlab/js/respond.min.js" ></script>

<!--common script for all pages-->
<script type="text/javascript" src="/static/assets/lib/layer/2.4/layer.js"></script>

<!--script for this page-->
<script src="/static/assets/lib/flatlab/js/count.js"></script>

<!-- App -->
<script type="text/javascript" src="/static/assets/app/const.js"></script>

<script>

    // 获取用户名
    $("#username").html(parent.username);

    // 显示现在时段
    var now = new Date(),hour = now.getHours();
    if (hour < 5 || hour > 22) {$("#hello").html("深夜好！");}
    if(hour < 6){ $("#hello").html("凌晨好！");}
    else if (hour < 9){$("#hello").html("早上好！");}
    else if (hour < 12){$("#hello").html("上午好！");}
    else if (hour < 14){$("#hello").html("中午好！");}
    else if (hour < 17){$("#hello").html("下午好！");}
    else if (hour < 19){$("#hello").html("傍晚好！");}
    else if (hour < 22){$("#hello").html("晚上好！");}

    // 获取当前时间
    $(function(){
        setInterval(function(){
            $("#currentTime").text(new Date().toLocaleString());
        },1000);
    });


    // 统计用户数
    $.ajax({
        url:"/member/count",
        type:"GET",
        success:function (data) {
            countUp(data.result);
        },
        error:function(){
            layer.alert(ERROR_REQUEST_MESSAGE, {title: '错误信息',icon: 2});
        }
    });

    // 统计商品总数
    $.ajax({
        url:"/item/count",
        type: 'GET',
        success:function (data) {
            countUp2(data.result);
        },
        error:function(){
            layer.alert(ERROR_REQUEST_MESSAGE, {title: '错误信息',icon: 2});
        }
    });

    // 统计订单总数
    $.ajax({
        url:"/order/count",
        type: 'GET',
        success:function (data) {
            countUp3(data.result);
        },
        error:function(){
            layer.alert(ERROR_REQUEST_MESSAGE, {title: '错误信息',icon: 2});
        }
    });

    // 获取天气数据
    $.ajax({
        url:"/sys/weather",
        type: 'GET',
        success:function (data) {
            if(data.result == null || data.result == "" || data.result.indexOf('错误') >= 0) {
                layer.msg("无法获取您的IP，天气信息获取失败");
                return ;
            }
            var json = JSON.parse(data.result);
            var param = json.result[0];
            var weather = param.weather;
            if (weather.indexOf("雨") >= 0) {
                $("#weather").removeAttr("class");
                $("#weather").attr("class","icon-umbrella");
            } else if (weather.indexOf("晴") >= 0){
                $("#weather").removeAttr("class");
                $("#weather").attr("class","icon-sun");
            }
            $("#city").html(param.city);
            $("#degree").html(param.temperature);
            $("#humidity").html(param.humidity);
            $("#airCondition").html(param.airCondition);
            $("#wind").html(param.wind);
        },
        error:function(){
            layer.alert(ERROR_REQUEST_MESSAGE, {title: '错误信息',icon: 2});
        }
    });

    //本周热门商品
    $.ajax({
        url:"/sys/weekHot",
        type: 'GET',
        success:function (data) {
            if (data.status == 200) {
                $("#hot-title").html(data.result.title);
                $("#hot-num").html(data.result.total);
                if(data.result.picPath != "" && data.result.picPath != null) {
                    $("#hot-img").attr("src", data.result.picPath);
                }
            } else {
                layer.alert(data.message, {title: '错误信息',icon: 2});
            }
        },
        error:function(){
            layer.alert(ERROR_REQUEST_MESSAGE,{title: '错误信息',icon: 2});
        }
    });

    // 如果标题太长，截取前 18 个
    if($("#hot-title").text().length > 18){
        $("#hot-title").text($("#hot-title").text().substring(0,18) + "...");
    }

</script>

</body>
</html>
