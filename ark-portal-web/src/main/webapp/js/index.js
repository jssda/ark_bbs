layui.config({
    version: "3.0.0"
    , base: '../../res/mods/' //这里实际使用时，建议改成绝对路径
}).extend({
    fly: 'index',
    tag: 'tag',
    cookie: 'cookie'
}).use(['cookie', 'fly', 'form', 'layer', 'util', 'laytpl', 'laypage', 'layedit', 'laydate', 'upload', 'tag'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        tag = layui.tag,
        fly = layui.fly,
        util = layui.util,
        cookie = layui.cookie;

    //加载公共页面资源
    $("#header").load("http://localhost:8081/html/common/header.html");
    $("#footer").load("http://localhost:8081/html/common/footer.html");
    $("#column").load("http://localhost:8081/html/common/column.html");
    $("#rightBar").load("http://localhost:8081/html/common/right-bar.html");

    let articleCount;

    // 查询板块和板块中的数据, 渲染进页面
    function listSection(page, limit) {
        $.ajax({
            url: "http://localhost:8081/portal/index/listArticleByTop",
            data: {
                page: page,
                limit: limit
            },
            async: false,
            success: function (res) {
                let articleList = res.data;
                articleCount = res.count;
                layui.each(articleList, function (index, item) {
                    item.create = getTimeAge(item.create);
                })
                let getTpl = $("#arkArticleTpl").html();
                laytpl(getTpl).render(articleList, function (html) {
                    $("#arkArticleView").html(html);
                });
            }
        })
    }

    listSection(1, 5);

    //执行一个laypage实例
    laypage.render({
        elem: 'articlePage' //注意，这里的 test1 是 ID，不用加 # 号
        , count: articleCount //数据总数，从服务端得到
        , limit: 5
        , jump: function (obj, first) {
            // 分页查询板块信息
            listSection(obj.curr, obj.limit);
        }
    });


    /*'yyyy-MM-dd HH-mm-ss'格式的字符串转日期*/
    function stringToDate(str) {
        var tempStrs = str.split(" ");
        var dateStrs = tempStrs[0].split("-");
        var year = parseInt(dateStrs[0], 10);
        var month = parseInt(dateStrs[1], 10) - 1;
        var day = parseInt(dateStrs[2], 10);
        var timeStrs = tempStrs[1].split("-");
        var hour = parseInt(timeStrs [0], 10);
        var minute = parseInt(timeStrs[1], 10);
        var second = parseInt(timeStrs[2], 10);
        var date = new Date(year, month, day, hour, minute, second);
        return date;
    }

    function getTimeAge(str) {
        return util.timeAgo(stringToDate(str));
    }

});