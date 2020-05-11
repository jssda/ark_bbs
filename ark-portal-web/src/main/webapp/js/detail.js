layui.config({
    version: "3.0.0"
    , base: 'http://localhost:8081/res/mods/'
}).extend({
    tag: 'tag',
    fly: 'index',
    cookie: 'cookie'
}).use(['fly', 'face', 'flow', 'form', 'layer', 'laytpl', 'cookie', 'laypage', 'layedit', 'laydate', 'upload', 'tag', 'element'], function () {
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
        cookie = layui.cookie,
        fly = layui.fly;

    //加载公共页面资源
    $("#header").load("http://localhost:8081/html/common/header.html");
    $("#footer").load("http://localhost:8081/html/common/footer.html");
    $("#column").load("http://localhost:8081/html/common/column.html");

    let article;
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

                // 查看文章是否被收藏
                let loginUser;
                if ($.cookie("userInfo") != null) {
                    loginUser = JSON.parse($.cookie("userInfo"));
                }
                let loginUserId = null;
                if (loginUser != null) {
                    loginUserId = loginUser.userId;
                }
                $.ajax({
                    url: 'http://localhost:8081/portal/index/isCollection',
                    type: "POST",
                    data: {
                        artId: data.artId,
                        userId: loginUserId
                    },
                    async: false,
                    success: function (res) {
                        if (res.code === 200) {
                            data.isCollection = true;
                        } else {
                            data.isCollection = false;
                        }
                    },
                    error: function () {
                        data.isCollection = false;
                    }
                });

                data.loginUserId = loginUserId;

                let artTplHtml = $("#articleTpl").html();
                let artTplView = $("#articleView");
                laytpl(artTplHtml).render(data, function (html) {
                    artTplView.html(html);
                })
            }
            article = data;
            // 流加载评论信息
            flow.load({
                elem: "#commentView" /*指定列表容器*/,
                isAuto: false,
                done: function (page, next) { //到达临界点（默认滚动触发），触发下一页
                    var lis = [];

                    // 查询此文章的评论
                    $.ajax({
                        url: "http://localhost:8081/portal/index/listCommentByPageNumAndArtId",
                        data: {artId: data.artId, page: page, limit: 4},
                        async: false,
                        success: function (res) {
                            if (res.code === -1) {
                                top.layer.msg(res.msg);
                            } else {
                                let commentHtml = $("#commentTpl").html();
                                laytpl(commentHtml).render(res.data, function (html) {
                                    lis.push(html);
                                })
                                next(lis.join(''), page < (res.count / 5));
                            }
                        }
                    })
                }
            });

            // 加载文章用户信息
            let user = data.user;
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

    //创建一个编辑器
    var editIndex = layedit.build('commentContent', {
        height: 200,
        uploadImage: {
            url: "http://localhost:8081/portal/pic/upload"
        }
    });
    // 创建编辑器的时候, 将textArera中的数据同步到编辑器中, 以追加方式添加
    layedit.setContent(editIndex, $("#commentContent").text(), true);
    //用于同步编辑器内容到textarea
    layedit.sync(editIndex);

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

        // 查看回复的是一级评论还是二级评论
        let comId;
        if ("commentMultiDiv" === item.parent().attr("class")) {
            // 假如回复的是多级评论, 取得一级评论id
            comId = item.parents("li[name=commentItem]").attr("data-id");
            $("input[name=replyType]").val(1);
        } else {
            comId = targetComId;
            $("input[name=replyType]").val(0);
        }

        $("#submitComment").focus();
        $("#targetUserName").text("@" + targetUserName + ":");
        $("input[name=targetId]").val(targetUserId);
        $("input[name=replayId]").val(targetComId);
        $("input[name=comId]").val(comId);
        $("#clearTips").removeClass("layui-hide");
    });
    // 清除回复对象
    $(document).on("click", "#clearTips", function () {
        $("#targetUserName").text("");
        $("input[name=targetId]").val("");
        $("input[name=replayId]").val("");
        $(this).addClass("layui-hide");
    })

    // 评论提交
    form.on('submit(submitComment)', function (data) {
        // 取得评论的文章id
        let artId = $("#artId").text();

        data.field.artId = artId;
        // 富文本编辑器中的内容
        data.field.content = layedit.getContent(editIndex).split('<audio controls="controls" style="display: none;"></audio>')[0];

        $.ajax({
            url: "http://localhost:8081/portal/index/addComment",
            type: "POST",
            data: data.field,
            success: function (res) {
                layer.msg(res.msg);
                location.reload();
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

    // 收藏文章
    $(document).on('click', '#collectionThis', function () {
        $.ajax({
            url: "http://localhost:8081/portal/index/collectionThis/" + article.artId,
            success: function (res) {
                layer.msg(res.msg);
                if (res.code === 200) {
                    location.reload();
                }
            },
            complete: function (xhr, ts) {
                if ((xhr.status >= 300 && xhr.status < 400) && xhr.status !== 304) {
                    //重定向网址在响应头中，取出再执行跳转
                    let redirectUrl = xhr.getResponseHeader('redirectUrl');
                    let localUrl = location.href;
                    location.href = redirectUrl + '?redirectUrl=' + localUrl;
                }
            }
        })
    })

    // 置顶
    $(document).on('click', '#topThis', function () {
        $.ajax({
            url: "http://localhost:8081/portal/index/topThis/" + article.artId,
            success: function (res) {
                layer.msg(res.msg);
                if (res.code === 200) {
                    location.reload();
                }
            },
            complete: function (xhr, ts) {
                if ((xhr.status >= 300 && xhr.status < 400) && xhr.status !== 304) {
                    //重定向网址在响应头中，取出再执行跳转
                    let redirectUrl = xhr.getResponseHeader('redirectUrl');
                    let localUrl = location.href;
                    location.href = redirectUrl + '?redirectUrl=' + localUrl;
                }
            }
        })
    })

    // 取消置顶
    $(document).on('click', '#unTopThis', function () {
        $.ajax({
            url: "http://localhost:8081/portal/index/unTopThis/" + article.artId,
            success: function (res) {
                layer.msg(res.msg);
                if (res.code === 200) {
                    location.reload();
                }
            },
            complete: function (xhr, ts) {
                if ((xhr.status >= 300 && xhr.status < 400) && xhr.status !== 304) {
                    //重定向网址在响应头中，取出再执行跳转
                    let redirectUrl = xhr.getResponseHeader('redirectUrl');
                    let localUrl = location.href;
                    location.href = redirectUrl + '?redirectUrl=' + localUrl;
                }
            }
        })
    })

    // 删除此文章
    $(document).on('click', '#delThis', function () {
        $.ajax({
            url: "http://localhost:8081/portal/index/delThis/" + article.artId,
            success: function (res) {
                layer.msg(res.msg);
                if (res.code === 200) {
                    location.href = "http://localhost:8081";
                }
            },
            complete: function (xhr, ts) {
                if ((xhr.status >= 300 && xhr.status < 400) && xhr.status !== 304) {
                    //重定向网址在响应头中，取出再执行跳转
                    let redirectUrl = xhr.getResponseHeader('redirectUrl');
                    let localUrl = location.href;
                    location.href = redirectUrl + '?redirectUrl=' + localUrl;
                }
            }
        })
    })


    let secId = layui.url().search.secId;
    // 查询评论最多的10篇文章
    $.ajax({
        url: "http://localhost:8081/portal/index/mostCommentBySecId",
        data: {secId: secId},
        success: function (res) {
            let data = res.data;
            if (data == null) {
                data = {}
            }

            let tpl = $("#mostCommentTpl").html();
            laytpl(tpl).render(data, function (html) {
                $("#mostCommentView").html(html);
            });
        }
    })

});

