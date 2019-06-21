/**
 * 函数对象
 */
var Validate = function () {

    /**
     * 增加自定义验证规则
     */
    var handlerInitDecimalsValidate = function () {
        jQuery.validator.addMethod("decimalsValue",function(value, element) {
            var decimalsValue =/^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/ ;
            return this.optional(element) || (decimalsValue.test(value));
        }, "金额必须大于0并且只能精确到分");
    };

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
                },
                title: {
                    required: true
                },
                sellPoint: {
                    required: true
                },
                cname: {
                    required: true
                },
                price: {
                    decimalsValue: true,
                    required: true,
                    maxlength: 10
                },
                num: {
                    digits: true,
                    required: true,
                    maxlength: 5
                },
                shippingName: {
                    required: true
                },
                shippingCode: {
                    required: true
                },
                password: {
                    required: true,
                    minlength: 6,
                },
                password2: {
                    required: true,
                    minlength: 6,
                    equalTo: "#password"
                },
                expressName:{
                    required:true,
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
                    required: "限制数量不能为空"
                },
                cname: {
                    required: "商品分类不能"
                },
                price: {
                    required: "产品展示价格不能为空"
                },
                num: {
                    required: "库存数量不能为空"
                },
                shippingName: {
                    required: "请选择快递名称"
                },
                shippingCode: {
                    required: "请填写快递单号"
                },
                password: {
                    required: "请填写密码",
                },
                password2: {
                    required: "请确认密码",
                    equalTo: "两次密码输入不一致"
                },
                expressName:{
                    required: "快递名称不能为空"
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
            handlerInitDecimalsValidate();
            handlerInitValidate(url, beforeSubmit, successMethod);
        }
    }
}();
