layui.cache.page = 'jie';
layui.cache.user = {
    username: '游客'
    , uid: -1
    , avatar: '../../res/images/avatar/00.jpg'
    , experience: 83
    , sex: '男'
};

layui.config({
    version: "3.0.0"
    , base: '../../res/mods/' //这里实际使用时，建议改成绝对路径
}).extend({
    fly: 'index',
    tag: 'tag'
}).use(['fly', 'form', 'layer', 'laytpl', 'laypage', 'layedit', 'laydate', 'upload', 'tag'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        tag = layui.tag,
        fly = layui.fly;

    let articleCount;
    let sectionInfo;


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
});