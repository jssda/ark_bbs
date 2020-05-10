layui.config({
    version: "3.0.0"
    , base: '../../res/mods/' //这里实际使用时，建议改成绝对路径
}).extend({
    fly: 'index',
    tag: 'tag'
}).use(['fly', 'form', 'layer', 'laytpl', 'laypage', 'layedit', 'laydate','util', 'upload', 'tag'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery,
        util = layui.util,
        laytpl = layui.laytpl,
        tag = layui.tag,
        fly = layui.fly;

    let articleCount;
    let sectionInfo;

    $("#header").load("http://localhost:8081/html/common/header.html");
    $("#column").load("http://localhost:8081/html/common/column.html");
    $("#rightBar").load("http://localhost:8081/html/common/right-bar.html");


    /**
     * 获取展示的板块信息
     */
    let getSectionInfo = $.ajax({
        url: "http://localhost:8081/portal/index/getSectionBySecId/" + layui.url().search.secId,
        async: false,
        success: function (res) {
            if (res.code == -1) {
                layer.msg(res.msg);
            } else {
                sectionInfo = res.data;
                let userId = sectionInfo.secUserId;
                // 通过用户id查询用户信息, 添加到板块中
                $.ajax({
                    url: "http://localhost:8081/portal/index/getUserInfoByUserId/" + userId,
                    async: false,
                    success: function (result) {
                        sectionInfo.user = result.data;
                    }
                })
            }
        }
    })

    /**
     * 分页查询文章信息
     * @param page
     * @param limit
     */
    function listArticle(page, limit) {
        $.ajax({
            url: "http://localhost:8081/portal/index/listArticleBySecId/" + layui.url().search.secId,
            async: false,
            success: function (res) {
                articleCount = res.count;
                let articleList = res.data;
                articleList.section = sectionInfo;

                layui.each(articleList, function (index, item) {
                    item.create = getTimeAge(item.create);
                })
                // 渲染文章信息
                let getTpl = $("#arkArticleTpl").html()
                    , view = document.getElementById('arkArticleView');
                laytpl(getTpl).render(articleList, function (html) {
                    view.innerHTML = html;
                });
            }
        })

    }

    $.when(getSectionInfo).done(function () {
        listArticle(1, 5);
    })

    // 渲染分页
    laypage.render({
        elem: 'articlePage' //注意，这里的 test1 是 ID，不用加 # 号
        , count: articleCount //数据总数，从服务端得到
        , limit: 5
        , jump: function (obj, first) {
            if (!first) {
                // 分页查询板块信息
                listArticle(obj.curr, obj.limit);
            }
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