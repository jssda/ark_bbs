layui.use(['form', 'layer', 'jquery', 'util'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        util = layui.util,
        $ = layui.jquery;

    // 检查用户名是否重复
    $("#userName").blur(function () {
        var userName = this.value;
        if (userName == '王京京') {
            layer.tips('用户名重复', '#userName');
        }
    });

    // 检查邮箱是否已经注册
    $("#email").blur(function () {
        var email = this.value;
        if (email == '1624022009@qq.com') {
            layer.tips('邮箱已注册', '#email');
            this.value = "";
        }
    });

    // 检查手机号是否已经注册
    $("#phone").blur(function () {
        var phone = this.value;
        if (phone == '15188875022') {
            layer.tips('手机号已注册', '#phone');
            this.value = "";
        }
    });

    $("#codeTip").click(function () {
        // 发送验证码
        sendCode(this);

        $(this).addClass('layui-btn-disabled');
        $(this).attr("disabled", true);

        // 绑定一个一分钟的倒计时, 一分钟过后,可以重复发送验证码
        var nowDate = new Date();
        var endDate = new Date().setSeconds(nowDate.getSeconds() + 59);

        util.countdown(endDate, nowDate.getTime(), function (date, serverTime, timer) {
            var str = date[3] + '秒后可重新发送';
            $("#codeTip").text(str);
            if (date[3] == 0) {
                $('#codeTip').removeClass('layui-btn-disabled');
                $('#codeTip').attr("disabled", false);
                $("#codeTip").text("重新发送验证码");
                clearTimeout(timer);
            }
        });
    });

    function sendCode(ele) {
        // 发送验证码的方法
    }

    $(".loginBody .seraph").click(function () {
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧", {
            time: 5000
        });
    });

    //登录按钮
    form.on("submit(login)", function (data) {
        $(this).text("登录中...").attr("disabled", "disabled").addClass("layui-disabled");
        setTimeout(function () {
            window.location.href = "/layuicms2.0";
        }, 1000);
        return false;
    });

    //表单输入效果
    $(".input-item").click(function (e) {
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    });
    $(".layui-form-item .layui-input,.layui-input-inline").focus(function () {
        $(this).parent().addClass("layui-input-focus");
    });
    $(".layui-form-item .layui-input,.layui-input-inline").blur(function () {
        $(this).parent().removeClass("layui-input-focus");
        if ($(this).val() != '') {
            $(this).parent().addClass("layui-input-active");
        } else {
            $(this).parent().removeClass("layui-input-active");
        }
    });

    //表单自定义验证
    form.verify({
        username: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                return '用户名不能有特殊字符';
            }
            if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                return '用户名首尾不能出现下划线\'_\'';
            }
            if (/^\d+\d+\d$/.test(value)) {
                return '用户名不能全为数字';
            }
        }

        //我们既支持上述函数式的方式，也支持下述数组的形式
        //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        , pass: [
            /^[\S]{6,12}$/
            , '密码必须6到12位，且不能出现空格'
        ]
    });
});