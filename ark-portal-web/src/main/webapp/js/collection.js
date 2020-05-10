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


    let publicCount;
    let collectionCount;

    // 查询板块和板块中的数据, 渲染进页面
    function listPublic(page, limit) {
        $.ajax({
            url: "http://localhost:8081/portal/index/listArticleByLoginUserIdAndPageNum",
            data: {
                page: page,
                limit: limit,
            },
            async: false,
            success: function (res) {
                let articleList = res.data;
                publicCount = res.count;
                if (articleList == null) {
                    articleList = {}
                }
                layui.each(articleList, function (index, item) {
                    item.create = getTimeAge(item.create);
                });
                let getTpl = $("#myPublicTpl").html();
                laytpl(getTpl).render(articleList, function (html) {
                    $("#myPublicView").html(html);
                });
            },
            complete: function (xhr, ts) {
                if ((xhr.status >= 300 && xhr.status < 400) && xhr.status != 304) {
                    //重定向网址在响应头中，取出再执行跳转
                    let redirectUrl = xhr.getResponseHeader('redirectUrl');
                    let localUrl = location.href;
                    location.href = redirectUrl + '?redirectUrl=' + localUrl;
                }
            }
        });
    }
    listPublic(1, 10);
    //执行一个laypage实例
    laypage.render({
        elem: 'publicPage' //注意，这里的 test1 是 ID，不用加 # 号
        , count: publicCount //数据总数，从服务端得到
        , limit: 10
        , jump: function (obj, first) {
            // 分页查询板块信息
            listPublic(obj.curr, obj.limit);
        }
    });


    function listCollection(page, limit) {
        $.ajax({
            url: "http://localhost:8081/portal/index/listCollectionByUserIdAndPageNum",
            data: {
                page: page,
                limit: limit,
            },
            async: false,
            success: function (res) {
                let articleList = res.data;
                collectionCount = res.count;
                if (articleList == null) {
                    articleList = {}
                }
                layui.each(articleList, function (index, item) {
                    item.create = getTimeAge(item.create);
                });
                console.log(articleList);
                let getTpl = $("#myCollectionTpl").html();
                laytpl(getTpl).render(articleList, function (html) {
                    $("#myCollectionView").html(html);
                });
            },
            complete: function (xhr, ts) {
                if ((xhr.status >= 300 && xhr.status < 400) && xhr.status != 304) {
                    //重定向网址在响应头中，取出再执行跳转
                    let redirectUrl = xhr.getResponseHeader('redirectUrl');
                    let localUrl = location.href;
                    location.href = redirectUrl + '?redirectUrl=' + localUrl;
                }
            }
        });
    }
    listCollection(1, 10);
    //执行一个laypage实例
    laypage.render({
        elem: 'collectionPage' //注意，这里的 test1 是 ID，不用加 # 号
        , count: collectionCount //数据总数，从服务端得到
        , limit: 10
        , jump: function (obj, first) {
            // 分页查询板块信息
            listCollection(obj.curr, obj.limit);
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