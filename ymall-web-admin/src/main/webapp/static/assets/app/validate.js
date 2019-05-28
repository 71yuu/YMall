/**
 * 函数对象
 */
var Validate = function () {

    /**
     * 初始化 Validae
     */
    var handlerInitValidate = function (url, beforeSubmit, successMethod) {
        $("#validate-form").validate({
            ignore: ":hidden",//不验证的元素
            rules: {
                picUrl: {
                    required: true,
                    minlength: 1,
                    maxlength: 20
                },
                fullUrl: {
                    required: true,
                    minlength: 10
                },
                sortOrder: {
                    required: true,
                    digits: true,
                    maxlength: 4
                },
                name: {
                    required: true,
                    minlength: 1,
                    maxlength: 25
                },
                limitNum: {
                    required: true,
                    digits: true,
                    maxlength: 3
                }
            },
            messages: {
                picUrl: {
                    required: "名称不能为空"
                },
                fullUrl: {
                    required: "跳转链接不能为空"
                },
                sortOrder: {
                    required: "排序值不能为空",
                },
                name: {
                    required: "板块名称不能为空"
                },
                limitNum: {
                    required: " 最大容纳内容(商品)数不能为空"
                }
            },
            submitHandler: function (form) {
                if (beforeSubmit != "") {
                    if(!beforeSubmit()) {
                        return;
                    }
                }
                var index = layer.load(3);
                $(form).ajaxSubmit({
                    url: url,
                    type: "POST",
                    success: function (data) {
                        layer.close(index);
                        if (data.status == 200) {
                            successMethod(data);
                        } else {
                            layer.alert(data.message, {title: '错误信息', icon: 2});
                        }
                    },
                    error: function () {
                        layer.close(index);
                        layer.alert(ERROR_REQUEST_MESSAGE, {title: "错误信息", icon: 2});
                    }
                });
            }
        });
    };

    return {
        /**
         * 验证
         */
        validate: function (url, beforeSubmit, successMethod) {
            handlerInitValidate(url, beforeSubmit, successMethod);
        }
    }
}();
