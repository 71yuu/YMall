<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>YMall后台管理系统</title>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/">YMall后台管理系统</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/">YMall后台管理系统</a>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav class="nav navbar-nav">
                <ul class="cl">
                    <li ><a href="http://localhost:9999" target="_blank">商城前台</a></li>
                    </li>
                </ul>
            </nav>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li style="right:5px" id="role"></li>
                    <li class="dropDown dropDown_hover">
                        <a href="#" class="dropDown_A"><sapn id="username"></sapn> <i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a onclick="logout()">切换账户</a></li>
                            <li><a onclick="logout()">退出</a></li>
                        </ul>
                    </li>
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
            <dt><i class="Hui-iconfont">&#xe616;</i> 首页管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="/content-panel" data-title="板块管理" href="javascript:void(0)">首页板块管理</a></li>
                    <li><a data-href="/content-common-list?type=0" data-title="轮播图管理" href="javascript:void(0)">首页轮播图管理</a></li>
                    <li><a data-href="/content-common-list?type=-1" data-title="板块内容管理" href="javascript:void(0)">首页板块内容管理</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-product">
            <dt><i class="Hui-iconfont">&#xe620;</i> 商品管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="product-list" data-title="商品列表" href="javascript:void(0)">商品列表</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-category">
            <dt><i class="Hui-iconfont">&#xe620;</i> 分类管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="product-category" data-title="分类列表" href="javascript:void(0)">分类列表</a></li>
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
        <dl id="menu-member">
            <dt><i class="Hui-iconfont">&#xe60d;</i> 会员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="member-list" data-title="会员列表" href="javascript:;">会员列表</a></li>
                    <li><a data-href="member-ban" data-title="封禁的会员" href="javascript:;">封禁的会员</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-admin">
            <dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="admin-list" data-title="管理员列表" href="javascript:void(0)">管理员列表</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-admin">
            <dt><i class="Hui-iconfont">&#xe687;</i> 报表管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="chart-order" data-title="订单销量统计" href="javascript:void(0)">订单销量统计</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-system">
            <dt><i class="Hui-iconfont">&#xe62e;</i> 快递管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="system-express" data-title="快递管理" href="javascript:void(0)">快递列表</a></li>
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
