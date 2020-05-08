layui.config({
    version: "3.0.0"
    , base: '../../res/mods/' //这里实际使用时，建议改成绝对路径
}).extend({
    cookie: 'cookie',
    fly: 'index'
}).use(['element', 'jquery'], function () {
    var $ = layui.jquery,
        cookie = layui.cookie,
        fly = layui.fly,
        element = layui.element;

    //加载公共页面资源
    $("#header").load("http://localhost:8081/html/common/header.html");
    $("#footer").load("http://localhost:8081/html/common/footer.html");

    // 加载用户导航
    $("#user_nav").load("http://localhost:8081/html/common/user-nav.html");

    element.render('nav');
});