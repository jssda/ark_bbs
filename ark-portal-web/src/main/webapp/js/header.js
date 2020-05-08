layui.use(['layer', 'laytpl', 'cookie', 'jquery', 'element'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        element = layui.element,
        cookie = layui.cookie;

    (function () {
        let loginUser = {};

        // 获取cookie
        let token = $.cookie('sso_token');
        // 假如有令牌, 查看本地是否有用户信息, 如果没有, 查询用户信息
        if (token != null) {
            loginUser = layui.data("userInfo");
            if (loginUser.user == null) {
                // 查询用户信息
                $.ajax({
                    url: "http://localhost:8083/sso/checkUserByToken",
                    async: false,
                    data: {
                        token: token
                    },
                    success: function (res) {
                        layer.msg(res.msg);
                        if (res.data != null) {
                            // 将用户信息存储到浏览器中
                            layui.data("userInfo", {
                                key: "user",
                                value: res.data
                            });
                            loginUser.user = res.data;
                        } else {
                            loginUser.user = null;
                        }
                    }
                });
            }
        } else {
            layui.data("userInfo", null);
        }
        // 没有令牌的话, 直接没有登录, 需要登录
        let tpl = $("#userInfoTpl").html();
        laytpl(tpl).render(loginUser, function (html) {
            $(".fly-nav-user").html(html);
        });

        element.render('nav');
    })();

});