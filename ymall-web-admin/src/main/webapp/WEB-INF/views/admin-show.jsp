<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>用户查看</title>
</head>
<body>
<div class="cl pd-20" style=" background-color:#5bacb6">
    <img id="avatar" class="avatar size-XL l" src="/static/assets/static/h-ui/images/ucnter/avatar-default.jpg">
    <dl style="margin-left:80px; color:#fff">
        <dt>
            <span id="username" class="f-18"></span>
        </dt>
        <dd id="description" class="pt-10 f-12" style="margin-left:0">这家伙很懒，什么也没有留下</dd>
    </dl>
</div>
<div class="pd-20">
    <table class="table">
        <tbody>
        <tr>
            <th class="text-r" width="80">性别：</th>
            <td id="sex"></td>
        </tr>
        <tr>
            <th class="text-r">手机：</th>
            <td id="phone"></td>
        </tr>
        <tr>
            <th class="text-r">邮箱：</th>
            <td id="email"></td>
        </tr>
        <tr>
            <th class="text-r">地址：</th>
            <td id="address"></td>
        </tr>
        <tr>
            <th class="text-r">创建时间：</th>
            <td id="created"></td>
        </tr>
        </tbody>
    </table>
</div>

<!-- 通用的 js -->
<jsp:include page="../includes/footer.jsp"/>

<!-- 此页面的业务脚本 -->
<script type="text/javascript">

    // 封装信息
    $("#username").html(parent.username);
    $("#sex").html(parent.sex);
    $("#phone").html(parent.phone);
    $("#email").html(parent.email);
    $("#address").html(parent.address);
    $("#created").html(date(parent.created));
    if (parent.description != null) {
        $("#description").html(parent.description);
    }
    if (parent.file != null && parent.file != "") {
        $("#avatar").attr("src", parent.file);
    }
</script>
</body>
</html>
