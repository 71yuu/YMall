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
                            <a href="http://blog.exrick.cn" target="_blank" class="task-thumb">
                                <img width="83px" height="83px" src="/static/assets/lib/flatlab/img/avatar1.jpg" alt="">
                            </a>
                            <div class="task-thumb-details">
                                <h1><a href="http://blog.exrick.cn" target="_blank">Yuu</a></h1>
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
                                    <a target="_blank" href="http://www.71yuu.com/intro/">
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
                                    <a target="_blank" href="http://www.71yuu.com/">
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
                                            <p style="font-size: 20px;"><br>YMall 是一个电商B2C购物商城，前后端开发分离,前台
                                                基于Vue全家桶开发后台几乎完成所有功能开发，你所看到的几乎皆为真实后台数据，为避免数据遭恶意修改，测试体验账号只具备查看权限。 后台主要使用技术有SSM/Elasticsearch/Redis/MySQL/ActiveMQ/Shiro等。<br><br>
                                                此项目主要仅用于学习、练习使用。<br/>
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
                                <h3 class="timeline-title">YMall更新日志</h3>
                                <p class="t-info">This is a project timeline</p>
                            </div>

                            <div class="timeline">
                               <%-- <article class="timeline-item">
                                    <div class="timeline-desk">
                                        <div class="panel">
                                            <div class="panel-body">
                                                <span class="arrow"></span>
                                                <span class="timeline-icon red"></span>
                                                <span class="timeline-date">11:25 am</span>
                                                <h1 class="red">22 Oct | Sunday</h1>
                                                <p>待更新</p>
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
                                                <span class="timeline-date">17:00 pm</span>
                                                <h1 class="green">22 Oct | Sunday</h1>
                                                <p><a href="http://blog.exrick.cn" target="_blank">Exrick</a> 完成目前所有功能开发 <span><a href="https://github.com/Exrick/YMall" class="green" target="_blank">v1.0发布</a></span></p>
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
                                                <span class="timeline-date">11:35 pm</span>
                                                <h1 class="blue">13 Oct | Friday</h1>
                                                <p><a href="http://blog.exrick.cn" target="_blank">Exrick</a> 完成后端接口改造二次开发 <span><a class="blue" href="https://github.com/Exrick/YMall-front" target="_blank">YMall-Front</a></span></p>
                                                <div class="album">
                                                 &lt;%&ndash;   <a href="#">
                                                        <img alt="" width="48px" height="32px" src="http://oweupqzdv.bkt.clouddn.com/QQ%E6%88%AA%E5%9B%BE20170915134720.png">
                                                    </a>
                                                    <a href="#">
                                                        <img alt="" width="48px" height="32px" src="http://oweupqzdv.bkt.clouddn.com/QQ%E6%88%AA%E5%9B%BE20170925230559.png">
                                                    </a>
                                                    <a href="#">
                                                        <img alt="" width="48px" height="32px" src="http://oweupqzdv.bkt.clouddn.com/QQ%E6%88%AA%E5%9B%BE20170915135622.png">
                                                    </a>
                                                    <a href="#">
                                                        <img alt="" width="48px" height="32px" src="http://oweupqzdv.bkt.clouddn.com/QQ%E6%88%AA%E5%9B%BE20171022112023.jpg">
                                                    </a>
                                                    <a href="#">
                                                        <img alt="" width="48px" height="32px" src="http://oweupqzdv.bkt.clouddn.com/QQ%E6%88%AA%E5%9B%BE20171022112048.jpg">
                                                    </a>&ndash;%&gt;
                                                </div>
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
                                                <span class="timeline-date">3:00 pm</span>
                                                <h1 class="purple">14 Sep | Saturday</h1>
                                                <p>
                                                    感谢 <a href="https://github.com/yucccc" target="_blank">yucccc</a> 的开源
                                                    <a href="https://github.com/yucccc/vue-mall" target="_blank">vue-mall</a> 项目提供前端页面及框架支持
                                                </p>
                                                <div class="notification">
                                                    <i class=" icon-exclamation-sign"></i> 启动了前台项目
                                                    <a href="https://github.com/Exrick/YMall-front" target="_blank">YMall-Front</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </article>--%>
                                <article class="timeline-item">
                                <div class="timeline-desk">
                                    <div class="panel">
                                        <div class="panel-body">
                                            <span class="arrow"></span>
                                            <span class="timeline-icon light-green"></span>
                                            <span class="timeline-date">09:00 pm</span>
                                            <h1 class="light-green">10 May | Friday</h1>
                                            <p><a href="https://www.71yuu.com/" target="_blank">Yuu</a> 启动了
                                                <span><a href="https://github.com/Exrick/YMall" target="_blank" class="light-green">YMall</a></span>
                                                项目，并完成第一次提交</p>
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
<script src="/static/assets/lib/flatlab/js/owl.carousel.js" ></script>
<script src="/static/assets/lib/flatlab/js/jquery.customSelect.min.js" ></script>
<script src="/static/assets/lib/flatlab/js/respond.min.js" ></script>

<script class="include" type="text/javascript" src="/static/assets/lib/flatlab/js/jquery.dcjqaccordion.2.7.js"></script>

<!--common script for all pages-->
<script src="/static/assets/lib/flatlab/js/common-scripts.js"></script>

<script type="text/javascript" src="/static/assets/lib/layer/2.4/layer.js"></script>

<!--script for this page-->
<script src="/static/assets/lib/flatlab/js/sparkline-chart.js"></script>
<script src="/static/assets/lib/flatlab/js/easy-pie-chart.js"></script>
<script src="/static/assets/lib/flatlab/js/count.js"></script>

<script>

    $("#username").html(parent.username);

    var now = new Date(),hour = now.getHours();
    if (hour < 5 || hour > 22) {$("#hello").html("深夜好！");}
    if(hour < 6){ $("#hello").html("凌晨好！");}
    else if (hour < 9){$("#hello").html("早上好！");}
    else if (hour < 12){$("#hello").html("上午好！");}
    else if (hour < 14){$("#hello").html("中午好！");}
    else if (hour < 17){$("#hello").html("下午好！");}
    else if (hour < 19){$("#hello").html("傍晚好！");}
    else if (hour < 22){$("#hello").html("晚上好！");}

    $(function(){
        setInterval(function(){
            $("#currentTime").text(new Date().toLocaleString());
        },1000);
    });


    //owl carousel
    $(document).ready(function() {
        $("#owl-demo").owlCarousel({
            navigation : true,
            slideSpeed : 300,
            paginationSpeed : 400,
            singleItem : true,
            autoPlay:true
        });
    });

    //custom select box
    $(function(){
        $('select.styled').customSelect();
    });

    /** 统计用户数 **/
    $.ajax({
        url:"/member/count",
        type:"GET",
        success:function (result) {
            countUp(result.data);
        },
        error:function(XMLHttpRequest){
            layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
        }
    });

    /** 统计商品总数 **/
    $.ajax({
        url:"/item/count",
        type: 'GET',
        success:function (result) {
            countUp2(result.data);
        },
        error:function(XMLHttpRequest){
            if(XMLHttpRequest.status!=200){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
            }
        }
    });

    /** 统计订单总数 **/
    $.ajax({
        url:"/order/count",
        type: 'GET',
        success:function (result) {
            countUp3(result.data);
        },
        error:function(XMLHttpRequest){
            if(XMLHttpRequest.status!=200){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
            }
        }
    });

    /** 获取天气数据 **/
    $.ajax({
        url:"/sys/weather",
        type: 'GET',
        success:function (result) {
            if(result.data == null || result.data == "" || result.data.indexOf('错误') >= 0) {
                layer.msg("无法获取您的IP，天气信息获取失败");
                return ;
            }
            var json = JSON.parse(result.data);
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
        error:function(XMLHttpRequest){
            if(XMLHttpRequest.status!=200){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
            }
        }
    });

    //本周热门商品
    $.ajax({
        url:"/sys/weekHot",
        type: 'GET',
        success:function (result) {
            $("#hot-title").html(result.data.title);
            $("#hot-num").html(result.data.total);
            if(result.data.picPath != "" && result.data.picPath != null) {
                $("#hot-img").attr("src", result.data.picPath);
            }
        },
        error:function(XMLHttpRequest){
            if(XMLHttpRequest.status!=200){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
            }
        }
    });

    if($("#hot-title").text().length > 18){
        $("#hot-title").text($("#hot-title").text().substring(0,18) +"...");

    }

    $.ajax({
        url:"/sys/base",
        type: 'GET',
        success:function (result) {
            if(result.status == 500){
                layer.alert(result.message,{title: '错误信息',icon: 2});
                return;
            }
            if(result.data.hasAllNotice == 1){
                allNotice(result.data.allNotice);
            }
        },
        error:function(XMLHttpRequest){
            if(XMLHttpRequest.status!=200){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
            }
        }
    });

    function allNotice(data){
        layer.open({
            type: 1
            ,title:'通知'
            ,area: ['350px', '230px']
            ,content: '<div style="margin: 10px 20px 10px 20px;">'+data+'</div>'
            ,btn: ['知道了']
            ,shade: 0 //不显示遮罩
            ,yes: function(){
                layer.closeAll();
            }
        });
    }

</script>

</body>
</html>
