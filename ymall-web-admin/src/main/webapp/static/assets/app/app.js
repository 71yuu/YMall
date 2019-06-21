var App = function () {

    // iCheck
    var _masterCheckbox;
    var _checkbox;

    // 用于存放 ID 的数组
    var _idArray;

    /**
     * 私有方法，初始化 ICheck
     */
    var handlerInitCheckbox = function () {
        // 激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

        // 获取控制端 Checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');

        // 获取全部 Checkbox 集合
        _checkbox = $('input[type="checkbox"].minimal');
    };

    /**
     * Checkbox 全选功能
     */
    var handlerCheckboxAll = function () {
        _masterCheckbox.on("ifClicked", function (e) {
            // 返回 true 表示未选中
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }

            // 选中状态
            else {
                _checkbox.iCheck("check");
            }
        })
    };

    /**
     * 消息提示方法
     */
    var handlerMsgSuccess = function (content) {
        layer.msg(content, {icon: 1, time: 3000});
    };

    /**
     * 初始化 DataTables
     */
    var handlerInitDataTables = function (url, columns) {
        var _dataTable = $("#dataTable").DataTable({
            "ordering": false,
            "processing": true,
            "searching": true,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                url: url,
                type: 'GET'
            },
            "columns": columns,
            language: {
                url: '/static/assets/lib/datatables/Chinese.json'
            },
            "drawCallback": function () {
                handlerInitCheckbox();
                handlerCheckboxAll();
            }
        });
        return _dataTable;
    };

    /**
     * 初始化 zTree
     *
     * @param url 请求路径
     * @param autoParam
     * @param callback
     */
    var handlerInitZTree = function (url, callback) {
        var setting = {
            view: {
                // 禁止多选
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: ""
                }
            },
            async: {
                // 开启异步加载
                enable: true,
                // 远程访问地址
                url: url,
                // 请求方式
                type: "GET",
                contentType: "application/json",
                autoParam: ["id"]
            },
            callback: callback
        };

        $.fn.zTree.init($("#myTree"), setting);

    };

    /**
     * 弹窗并全屏打开
     *
     * @param title 标题
     * @param url 打开的页面路径
     */
    var handlerOpenAndFull = function (title, url) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    };

    /**
     * 自定义弹窗
     *
     * @param title 标题
     * @param url 打开的页面路径
     * @param w 宽度
     * @param h 高度
     */
    var handlerShow = function (title, url, w, h) {
        layer_show(title, url, w, h);
    };

    /**
     * 删除单笔数据
     *
     * @param confirmMsg 确认消息
     * @param url 请求路径
     * @param successMethod 成功回调方法
     */
    var handlerDeleteSingle = function (confirmMsg, url, successMethod) {
        layer.confirm(confirmMsg, {icon:0},function () {
            var index = layer.load(3);
            $.ajax({
                type: 'DELETE',
                url: url,
                dataType: 'json',
                success: function (data) {
                    layer.close(index);
                    if (data.status == 200) {
                        successMethod(data);
                    } else {
                        layer.alert(data.message, {title: "错误信息", icon: 2});
                    }
                },
                error: function () {
                    layer.close(index);
                    layer.alert(ERROR_REQUEST_MESSAGE, {title: "错误信息", icon: 2});
                }
            })
        });
    };

    /**
     * 批量删除
     *
     * @param url 路径
     * @param successMsg 成功回调方法
     */
    var handlerDeleteMulti = function (url, successMethod) {
        _idArray = new Array();

        // 将选中的元素 ID 放入数组中
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });

        // 判断用户是否选择了数据项
        if (_idArray.length === 0) {
            layer.msg('您还未勾选任何数据！', {icon:5, time:3000});
            return;
        }

        // 确认删除
        layer.confirm('确定要删除所选的'+ _idArray.length +'条数据吗？', {icon:0}, function () {
            var index = layer.load(3);
            $.ajax({
                type: 'DELETE',
                url: url + _idArray,
                dataType: 'json',
                success: function (data) {
                    layer.close(index);
                    if (data.status == 200) {
                        successMethod(data);
                    } else {
                        layer.alert(data.message, {title: "错误信息", icon: 2});
                    }
                },
                error: function () {
                    layer.close(index);
                    layer.alert(ERROR_REQUEST_MESSAGE, {title: "错误信息", icon: 2});
                }
            });
        });
    };

    /**
     * 通用的 Ajax 请求方法,不带数据
     *
     * @param url 请求地址
     * @param type 请求类型
     * @param successMethod 请求成功调用的方法
     */
    var handlerAjax = function (url, type, successMethod) {
        $.ajax({
            url: url,
            type: type,
            dataType: 'json',
            success:function (data) {
                if (data.status == 200) {
                    successMethod(data);
                } else {
                    layer.alert(data.message, {title: "错误信息", icon: 2});
                }
            },
            error:function(){
                layer.alert(ERROR_REQUEST_MESSAGE, {title: '错误信息',icon: 2});
            }
        });
    };

    /**
     * 通用的 Ajax 请求方法,带数据
     *
     * @param url
     * @param type
     * @param data
     * @param successMethod
     */
    var handlerAjaxWithData = function (url, type, data, successMethod) {
        $.ajax({
            url: url,
            type: type,
            dataType: 'json',
            data: data,
            success:function (data) {
                if (data.status == 200) {
                    successMethod(data);
                } else {
                    layer.alert(data.message, {title: "错误信息", icon: 2});
                }
            },
            error:function(){
                layer.alert(ERROR_REQUEST_MESSAGE, {title: '错误信息',icon: 2});
            }
        });
    };

    /**
     * 图片预览
     *
     * @param obj
     */
    var handlerPreviewImg= function (obj, w, h) {
        var img = new Image();
        img.src = obj.src;
        var imgHtml = "<img src='" + obj.src + "' width='100%' height='100%'/>";
        //弹出层
        layer.open({
            type: 1,
            shade: 0.8,
            offset: 'auto',
            area: [w + 'px',h+'px'],
            shadeClose:true,
            scrollbar: false,
            title: "图片预览", //不显示标题
            content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function () {
                //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });
            }
        });
    };


    return {
        /**
         * ICheck 初始化
         */
        initICheck: function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },

        /**
         * 消息提示
         *
         * @param content 内容
         */
        msgSuccess: function (content) {
            handlerMsgSuccess(content);
        },

        /**
         * 初始化 DataTables
         *
         * @param url 请求路径
         * @param columns 返回参数映射
         * @returns {jQuery|*}
         */
        initDataTables: function (url, columns) {
            return handlerInitDataTables(url, columns);
        },

        /**
         * 初始化 zTree
         *
         * @param url 请求路径
         * @param callback 回调方法
         */
        initZtree: function (url, callback) {
            handlerInitZTree(url, callback);
        },

        /**
         * 弹窗并全屏打开
         *
         * @param title 标题
         * @param url 打开的页面路径
         */
        openAndFull: function (title, url) {
            handlerOpenAndFull(title, url);
        },

        /**
         * 自定义弹窗
         *
         * @param title 标题
         * @param url 打开的页面路径
         * @param w 宽度
         * @param h 高度
         */
        show: function(title, url, w, h) {
            handlerShow(title, url, w, h);
        },

        /**
         * 删除单笔数据
         *
         * @param confirmMsg 确认消息
         * @param url 请求路径
         * @param successMethod 成功回调方法
         */
        deleteSinge: function (confirmMsg, url, successMethod) {
            handlerDeleteSingle(confirmMsg, url, successMethod);
        },

        /**
         * 批量删除
         *
         * @param url 路径
         * @param successMsg 成功回调方法
         */
        deleteMulti: function (url, successMethod) {
            handlerDeleteMulti(url, successMethod);
        },

        /**
         * 通用的 Ajax 请求方法
         *
         * @param url 请求地址
         * @param type 请求类型
         * @param successMethod 请求成功调用的方法
         */
        ajax: function (url, type, successMethod) {
            handlerAjax(url, type, successMethod);
        },

        /**
         * 通用的 Ajax 请求方法,带数据
         *
         * @param url
         * @param type
         * @param data
         * @param successMethod
         */
        ajaxWithData: function (url, type, data, successMethod) {
            handlerAjaxWithData(url, type, data, successMethod);
        },

        /**
         * 图片预览
         *
         * @param obj
         */
        previewImg: function (obj, w, h) {
            handlerPreviewImg(obj, w, h);
        }
    }
}();
