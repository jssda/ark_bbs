layui.use(['layer', 'laytpl', 'cookie', 'jquery', 'element'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        element = layui.element,
        cookie = layui.cookie;

    (function () {
        let loginUser = null;
        // 获取cookie
        let token = $.cookie('sso_token');
        // 假如有令牌, 查看本地是否有用户信息, 如果没有, 查询用户信息
        if (token != null) {
            loginUser = $.cookie("userInfo");
            if (loginUser == null) { // 如果本地没有存储用户数据, 查询
                // 查询用户信息
                $.ajax({
                    url: "http://localhost:8083/sso/checkUserByToken",
                    async: false,
                    data: {
                        token: token
                    },
                    success: function (res) {
                        // layer.msg(res.msg);
                        if (res.data != null) {
                            // 将用户信息存储到浏览器中
                            $.cookie.json = true;
                            $.cookie("userInfo", res.data, {domain: 'localhost', path: '/'});
                            $.cookie.json = false;
                            loginUser = $.cookie("userInfo");
                        } else {
                            loginUser = null;
                        }
                    }
                });
            }
        } else { // 没有令牌, 本地也不应该有用户数据
            $.removeCookie("userInfo");
        }
        let data = {};
        if (loginUser != null) {
            data.user = JSON.parse(loginUser);
        } else {
            data.user = null;
        }

        // 没有令牌的话, 直接没有登录, 需要登录
        let tpl = $("#userInfoTpl").html();
        laytpl(tpl).render(data, function (html) {
            $(".fly-nav-user").html(html);
        });

        element.render('nav');
    })();

    // 点击退出的时候
    $("#logout").click(function () {
        // 获取cookie
        let token = $.cookie('sso_token');
        $.ajax({
            url: "http://localhost:8083/sso/logout",
            async: false,
            data: {
                token: token
            },
            success: function (res) {
                layer.msg(res.msg);
                if (res.code === 200) {
                    $.removeCookie("userInfo");
                    $.removeCookie('sso_token');
                    location.reload();
                }
            }
        })
    })

});