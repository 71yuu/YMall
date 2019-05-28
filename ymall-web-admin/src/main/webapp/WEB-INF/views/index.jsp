<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>YMall后台管理系统 v1.0</title>
    <meta name="keywords" content="YMall后台管理系统 v1.0,YMall,YMall购物商城后台管理系统">
    <meta name="description" content="YMall后台管理系统 v1.0，是一款电商后台管理系统，适合中小型CMS后台系统。">
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/">YMall后台管理系统</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/">YMall后台管理系统</a>
            <span class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0</span>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav class="nav navbar-nav">
                <ul class="cl">
                    <li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" onclick="App.openAndFull('添加商品','/product-add')"><i class="Hui-iconfont">&#xe620;</i> 商品</a></li>
                            <li><a href="javascript:;" onclick="App.show('添加会员','/member-add','','630')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
                        </ul>
                    <li class="navbar-levelone current"><a href="javascript:;">平台</a></li>
                    <li class="navbar-levelone"><a href="javascript:;">财务</a></li>
                    <li ><a href="http://YMall.Yuu.cn" target="_blank">商城前台</a></li>
                    </li>
                </ul>
            </nav>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li style="right:5px" id="role"></li>
                    <li class="dropDown dropDown_hover">
                        <a href="#" class="dropDown_A"><sapn id="username"></sapn> <i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" onClick="App.show('管理员信息','/admin-show',360,400)">个人信息</a></li>
                            <li><a onclick="logout()">切换账户</a></li>
                            <li><a onclick="logout()">退出</a></li>
                        </ul>
                    </li>
                    <li id="thanks"> <a onclick="thanks()" title="捐赠"><i class="Hui-iconfont" style="font-size:18px">&#xe6bb;</i></a> </li>
                    <li id="LockScreen"> <a href="/lock-screen" title="锁屏"><i class="Hui-iconfont" style="font-size:18px">&#xe60e;</i></a> </li>
                    <li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" data-val="default" title="默认（蓝色）">默认（蓝色）</a></li>
                            <li><a href="javascript:;" data-val="black" title="黑色">黑色</a></li>
                            <li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                            <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                            <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                            <li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <dl id="menu-article">
            <dt><i class="Hui-iconfont">&#xe616;</i> 商城内容管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="/content-header-list" data-title="首页导航栏管理" href="javascript:void(0)">首页导航栏管理</a></li>
                    <li><a data-href="/content-panel" data-title="首页板块管理" href="javascript:void(0)">首页板块管理</a></li>
                    <li><a data-href="/content-banner-list" data-title="首页轮播图管理" href="javascript:void(0)">首页轮播图管理</a></li>
                    <li><a data-href="/content-index-list" data-title="首页板块内容管理" href="javascript:void(0)">首页板块内容管理</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-picture">
            <dt><i class="Hui-iconfont">&#xe634;</i> 缓存管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="refresh-index-redis" data-title="首页缓存管理" href="javascript:void(0)">首页缓存管理</a></li>
                    <li><a data-href="refresh-other-redis" data-title="其它板块缓存管理" href="javascript:void(0)">其它板块缓存管理</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-product">
            <dt><i class="Hui-iconfont">&#xe620;</i> 商品管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="product-category" data-title="分类管理" href="javascript:void(0)">分类管理</a></li>
                    <li><a data-href="product-list" data-title="商品列表" href="javascript:void(0)">商品列表</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-order">
            <dt><i class="Hui-iconfont">&#xe627;</i> 订单管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="order-list" data-title="订单列表" href="javascript:void(0)">订单列表</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-search">
            <dt><i class="Hui-iconfont">&#xe665;</i> 搜索管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="refresh-index" data-title="同步索引" href="javascript:void(0)">同步索引</a></li>
                    <li><a data-href="dict-list" data-title="词典库管理" href="javascript:void(0)">词典库管理</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-comments">
            <dt><i class="Hui-iconfont">&#xe622;</i> 评论管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="http://changyan.kuaizhan.com/" data-title="畅言评论管理" href="javascript:void(0)">畅言评论管理</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-member">
            <dt><i class="Hui-iconfont">&#xe60d;</i> 会员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="member-list" data-title="会员列表" href="javascript:;">会员列表</a></li>
                    <li><a data-href="member-del" data-title="删除的会员" href="javascript:;">删除的会员</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-admin">
            <dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="admin-role" data-title="角色管理" href="javascript:void(0)">角色管理</a></li>
                    <li><a data-href="admin-permission" data-title="权限管理" href="javascript:void(0)">权限管理</a></li>
                    <li><a data-href="admin-list" data-title="管理员列表" href="javascript:void(0)">管理员列表</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-chart">
            <dt><i class="Hui-iconfont">&#xe61e;</i> 统计报表<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="chart-order" data-title="订单销量统计" href="javascript:void(0)">订单销量统计</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-system">
            <dt><i class="Hui-iconfont">&#xe62e;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="system-base" data-title="基本设置" href="javascript:void(0)">基本设置</a></li>
                    <li><a data-href="system-express" data-title="快递管理" href="javascript:void(0)">快递管理</a></li>
                    <li><a data-href="system-shiro" data-title="权限配置" href="javascript:void(0)">权限配置</a></li>
                    <li><a data-href="system-log" data-title="系统日志" href="javascript:void(0)">系统日志</a></li>
                </ul>
            </dd>
        </dl>
    </div>

    <div class="menu_dropdown bk_2" style="display:none">
        <dl id="menu-thank">
            <dt><i class="Hui-iconfont">&#xe6b7;</i> 捐赠管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="thanks-list" data-title="捐赠列表" href="javascript:void(0)">捐赠列表</a></li>
                </ul>
            </dd>
        </dl>
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active">
                    <span title="我的首页" data-href="welcome">我的首页</span>
                    <em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="welcome"></iframe>
        </div>
    </div>
</section>

<div class="contextMenu" id="Huiadminmenu">
    <ul>
        <li id="closethis">关闭当前 </li>
        <li id="closeall">关闭全部 </li>
    </ul>
</div>

<jsp:include page="../includes/footer.jsp"/>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/static/assets/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">

    // 平台与财务 tab 切换
    $(function(){
        $("body").Huitab({
            tabBar:".navbar-wrapper .navbar-levelone",
            tabCon:".Hui-aside .menu_dropdown",
            className:"current",
            index:0,
        });
    });

    // 获取用户信息
    var username="",description="",sex="",phone="",email="",address="",created="",file="";
    function successMethod(data) {
        $("#role").html(data.result.description);
        $("#username").html(data.result.username);
        username = data.result.username;
        description = data.result.description;
        sex = data.result.sex;
        phone = data.result.phone;
        email = data.result.email;
        address = data.result.address;
        created = data.result.created;
        file = data.result.file;
    }
    App.ajax("/user/userInfo", "GET", successMethod);

    // 退出登录
    function logout() {
        function successMethod() {
            window.location.href = "/login";
        }
        App.ajax("/user/logout", "GET", successMethod());
    }

    // 弹出感谢窗口
    function thanks(){
        layer.open({
            title: '捐赠',
            type: 2,
            skin: 'layui-layer-rim', //加上边框
            area: ['525px', '300px'], //宽高
            content: ['/thanks-pic','no']
        });
    }
</script>
</body
</html>
