layui.use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
    $ = layui.jquery;


    //登录按钮
    form.on("submit(login)", function (data) {
        $(this).text("登录中...").attr("disabled", "disabled").addClass("layui-disabled");

        // 登录请求
        let redirectUrl = layui.url(location.href).search.redirectUrl;
        if (redirectUrl == null) {
            redirectUrl = "http://localhost:8081/";
        }
        // ajax请求登录, 登录成功跳转到重定向页面
        $.ajax({
            url: "http://localhost:8083/sso/login",
            data: data.field,
            async: false,
            success: function (res) {
                if (res.code == 200) {
                    top.layer.msg(res.msg);
                    location.href = redirectUrl;
                }
            }
        })

        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function (e) {
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    });
    $(".loginBody .layui-form-item .layui-input").focus(function () {
        $(this).parent().addClass("layui-input-focus");
    });
    $(".loginBody .layui-form-item .layui-input").blur(function () {
        $(this).parent().removeClass("layui-input-focus");
        if ($(this).val() != '') {
            $(this).parent().addClass("layui-input-active");
        } else {
            $(this).parent().removeClass("layui-input-active");
        }
    })
});
