layui.cache.page = 'jie';
layui.cache.user = {
    username: '测试'
    , uid: -1
    , avatar: '../res/images/avatar/00.jpg'
    , experience: 83
    , sex: '男'
};

layui.config({
    version: "3.0.0"
    , base: 'http://localhost:8081/res/mods/'
}).extend({
    tag: 'tag',
    fly: 'index'
}).use(['fly', 'face', 'flow', 'form', 'layer', 'laytpl', 'laypage', 'layedit', 'laydate', 'upload', 'tag', 'element'], function () {
    var $ = layui.$,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        element = layui.element,
        laytpl = layui.laytpl,
        tag = layui.tag,
        flow = layui.flow,
        fly = layui.fly;

    // 初始化文章信息
    $.ajax({
        url: "http://localhost:8081/portal/index/getArticleByArtId/" + layui.url().search.artId,
        async: false,
        success: function (res) {
            let data;
            if (res.code === -1) {
                top.layer.msg(res.msg);
            } else {
                data = res.data;
                let artTplHtml = $("#articleTpl").html();
                let artTplView = $("#articleView");
                laytpl(artTplHtml).render(data, function (html) {
                    artTplView.html(html);
                })
            }

            // 查询此文章的评论
            $.ajax({
                url: "http://localhost:8081/portal/index/listCommentByPageNumAndArtId",
                data: {artId: data.artId, page: 1, limit: 5},
                async: false,
                success: function (res) {
                    if (res.code === -1) {
                        top.layer.msg(res.msg);
                    } else {
                        let commentHtml = $("#commentTpl").html();
                        let commentView = $("#commentView");
                        laytpl(commentHtml).render(res.data, function (html) {
                            commentView.html(html);
                        })
                    }
                }
            })

            // 加载文章用户信息
            var user = data.user;
            let artUserHtml = $("#artUserTpl").html();
            let artUserView = $("#artUserView");
            laytpl(artUserHtml).render(user, function (html) {
                artUserView.html(html);
            })

            // 查询文章类型信息
            $.ajax({
                url: "http://localhost:8081/portal/index/listTypeByArtId/" + data.artId,
                async: false,
                success: function (res) {
                    let artTypeHtml = $("#artTypeTpl").html();
                    let artTypeView = $("#artTypeView");
                    laytpl(artTypeHtml).render(res.data, function (html) {
                        artTypeView.html(html);
                    })
                    tag.render("test");
                }
            });
        }
    })

    //展开多级评论信息
    $("span[name=unfoldCommentMulti]").click(function () {
        let commentMultiDiv = $(this).parents("li[name=commentItem]").find("div[name=commentMultiDiv]");
        let display = commentMultiDiv.css("display");
        if (display === 'none') {
            commentMultiDiv.css("display", "block");
            // 获取一级评论的id
            let comId = $(this).parents("li[name=commentItem]").attr("data-id");

            // 流加载
            flow.load({
                elem: commentMultiDiv /*指定列表容器*/,
                isAuto: false,
                done: function (page, next) { //到达临界点（默认滚动触发），触发下一页
                    var lis = [];

                    // 通过一级评论 查询二级评论信息
                    $.ajax({
                        url: "http://localhost:8081/portal/index/listCommentMultiByPageNumAndComId",
                        data: {comId: comId, page: page, limit: 5},
                        success: function (res) {
                            let commentMultiTpl = $("#commentMultiTpl").html();
                            laytpl(commentMultiTpl).render(res.data, function (html) {
                                // commentMultiDiv.html(html);
                                lis.push(html);
                            })
                            next(lis.join(''), page < (res.count / 5));
                        }
                    })
                }
            });

        } else {
            commentMultiDiv.css("display", "none");
        }
    });

    // 点击回复
    $(document).on("click", "span[type = reply]", function () {
        // 取得要回复的人员姓名和id
        let item = $(this).parentsUntil("div[name=commentMultiDiv]", "li[name=commentItem]");
        let targetUserName = item.find(".fly-detail-user:eq(0)").text();
        let targetUserId = item.find("a[type=userId]").attr("user-id");
        let targetComId = item.attr("data-id");

        $("#L_content").focus();
        $("#targetUserName").text("@" + targetUserName + ":");
        $("input[name=targetId]").val(targetUserId);
        $("input[name=replayId]").val(targetComId);
        $("#clearTips").removeClass("layui-hide");
    });

    $(document).on("click", "#clearTips", function () {
        $("#targetUserName").text("");
        $("input[name=targetId]").val("");
        $("input[name=replayId]").val("");
        $(this).addClass("layui-hide");
    })

    // 评论提交
    form.on('submit(submitComment)', function (data) {
        $.ajax({
            url: "http://localhost:8081/portal/index/addComment",
            success: function (res) {

            },
            complete: function (xhr, ts) {
                if ((xhr.status >= 300 && xhr.status < 400) && xhr.status != 304) {
                    //重定向网址在响应头中，取出再执行跳转
                    let redirectUrl = xhr.getResponseHeader('redirectUrl');
                    let localUrl = location.href;
                    location.href = redirectUrl + '?redirectUrl=' + localUrl;
                }
            }
        })


        return false;
    })

});

