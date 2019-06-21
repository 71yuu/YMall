<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <!-- ZTree -->
    <link rel="stylesheet" href="/static/assets/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <title>产品分类</title>
</head>
<body>
<table class="table">
    <tr>
        <td width="200" class="va-t"><ul id="myTree" class="ztree"></ul></td>
    </tr>
</table>

<jsp:include page="../includes/footer.jsp"></jsp:include>

<!-- ZTree -->
<script type="text/javascript" src="/static/assets/lib/zTree/v3/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript">

    /**
     * ZTree 回调函数
     * */
    var callback = {
        beforeClick: function(treeId, treeNode) {
            if (treeNode.isParent) {
                return false;
            } else {
                var cid = treeNode.id;
                var cname = treeNode.name;
                if (cid == -1) {
                    layer.confirm('该类别不能选择！', {
                       btn: ['知道了'],
                       icon: 2
                    });
                    return true;
                }
                parent.setCid(cid);
                parent.setCname(cname);
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
                return true;
            }
        }
    };
    App.initZtree("/item/cat/list/-1" , callback);

</script>
</body>
</html>
