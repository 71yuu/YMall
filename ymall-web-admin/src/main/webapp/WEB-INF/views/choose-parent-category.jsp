<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <!-- ZTree -->
    <link rel="stylesheet" type="text/css" href="/static/assets/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
    <title>产品分类</title>
</head>
<body>
<table class="table">
    <tr>
        <td width="200" class="va-t"><ul id="myTree" class="ztree"></ul></td>
    </tr>
</table>
<jsp:include page="../includes/footer.jsp"/>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/static/assets/lib/zTree/v3/js/jquery.ztree.all-3.5.js"></script>

<script type="text/javascript">

    var oldCid = parent.id;


    /**
     * ZTree 回调函数
     * */
    var callback = {
        beforeClick: function(treeId, treeNode) {
            if(treeNode.id == oldCid) {
                layer.confirm('不能选择自己', {
                    btn: ['知道了'], icon: 2
                });
                return false;
            }
            if (treeNode.isParent) {
                parent.setParentId(treeNode.id, treeNode.name);
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
                return false;
            } else {
                layer.confirm('请选择一父节点！', {
                    btn: ['知道了'], icon: 2
                });
            }
        }
    };
    App.initZtree("/item/cat/list/0" , callback);
</script>
</body>
</html>
