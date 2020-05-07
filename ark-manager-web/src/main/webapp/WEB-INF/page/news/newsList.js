layui.config({
    base: '/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    tag: 'tag' //如果 tag.js 是在根目录，也可以不用设定别名
}).use(['form', 'layer', 'laydate', 'table', 'laytpl', 'tag'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        tag = layui.tag;

    //新闻列表
    var tableIns = table.render({
        elem: '#newsList',
        url: 'http://localhost:8080/manager/article/list',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limit: 20,
        limits: [10, 15, 20, 25],
        id: "newsListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'artId', title: 'ID', width: 60, align: "center"},
            {field: 'artTitle', title: '文章标题', width: 250},
            {
                field: 'artAuthor', title: '发布者', align: 'center', templet: function (d) {
                    if (d.user != null)
                        return d.user.userName;
                    else
                        return "";
                }
            },
            {
                field: 'artSec', title: "板块标题", align: 'center', templet: function (d) {
                    if (d.section != null) {
                        return d.section.secTitle;
                    } else
                        return "";
                }
            },
            {field: 'artHotNum', title: '文章热度', align: 'center'},
            {field: 'artLikeNum', title: '点赞数量', align: 'center'},
            {field: 'isTop', title: '是否置顶', align: 'center', toolbar: '#changeTopBar'},
            {field: 'create', title: '发布时间', align: 'center', minWidth: 110},
            {field: 'update', title: '更改时间', align: 'center', minWidth: 110},
            {title: '操作', width: 250, templet: '#newsListBar', fixed: "right", align: "center"}
        ]]
    });

    //是否置顶
    form.on('switch(newsTop)', function (data) {
        var index = layer.msg('修改中，请稍候', {icon: 16, time: false, shade: 0.8});

        let artId = $(data.elem).parents("tr").find("td[data-field=artId]").text();
        let val = null;
        if (data.elem.checked) {
            val = 1;
        } else {
            val = 0;
        }

        $.ajax({
            url: "http://localhost:8080/manager/article/addOrEditArticle",
            type: "POST",
            data: '{"isTop": "' + val + '", "artId": "' + artId + '"}',
            contentType: "application/json; charset=UTF-8",
            success: function (res) {
                $(data.elem).parents("tr").find("td[data-field=artId]").text(0);
                layer.close(index);
                layer.msg(res.msg);
            }
        })
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        if ($(".searchVal").val() != '') {
            table.reload("newsListTable", {
                url: 'http://localhost:8080/manager/article/listby',
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        } else {
            layer.msg("请输入搜索的内容");
        }
    });

    //添加文章
    function addNews(edit) {
        var title = "修改文章";
        if (edit == null || edit == undefined || edit == "") {
            title = "添加文章";
        }

        var index = layui.layer.open({
            title: title,
            type: 2,
            content: "newsAdd.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find("#artId").val(edit.artId);
                    body.find(".newsName").val(edit.artTitle);

                    /*var userNameDom = body.find("#artUserName");
                    userNameDom.parent().remove();*/

                    body.find("#userId").val(edit.user.userId);
                    body.find("#userName").val(edit.user.userName);

                    body.find("#secId").val(edit.section.secId);
                    body.find("#secTitle").val(edit.section.secTitle);

                    body.find("#news_content").text(edit.artContent);
                    body.find("#artHotNum").val(edit.artHotNum);
                    body.find("#artLikeNum").val(edit.artLikeNum);
                    body.find(".isTop input[name='isTop']").prop("checked", edit.isTop == 1 ? "checked" : "");

                    // 发送查询此文章的类型请求
                    $.ajax({
                        url: "http://localhost:8080/manager/article/findType/" + edit.artId,
                        async: false,
                        success: function (res) {
                            for (let i = 0; i < res.data.length; i++) {
                                body.find(".tagType").append('<button type="button" class="tag-item" lay-id="' + res.data[i].typeId + '">' + res.data[i].typeName + '</button>\n');
                            }
                            form.render();
                        }
                    });
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    }

    $(".addNews_btn").click(function () {
        addNews();
    });

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('newsListTable'),
            data = checkStatus.data,
            newsId = [];
        if (data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].artId);
            }
            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
                var arr = {artIds: newsId};
                $.ajax({
                    url: "http://localhost:8080/manager/article/removeArticles",
                    type: "POST",
                    data: arr,
                    datatype: "json",
                    traditional: true,
                    success: function (res) {
                        tableIns.reload();
                        layer.close(index);
                        top.layer.msg(res.msg);
                    }
                });
            })
        } else {
            layer.msg("请选择需要删除的文章");
        }
    });

    //列表操作
    table.on('tool(newsList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'edit') { //编辑
            addNews(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此文章？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: "http://localhost:8080/manager/article/removeArticle/" + data.artId,
                    success: function (res) {
                        tableIns.reload();
                        layer.close(index);
                        top.layer.msg(res.msg);
                    }
                })
            });
        } else if (layEvent === 'look') { //预览
            showArticle(data);
        }
    });

    function showArticle(data) {
        let index = layui.layer.open({
            title: '预览此文章',
            maxmin: true,
            type: 2,
            content: "detail.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);

                // 发送查询此文章
                $.ajax({
                    url: "http://localhost:8080/manager/article/findArticle/" + data.artId,
                    async: false,
                    success: function (res) {
                        if (res.code === -1) {
                            top.layer.msg(res.msg);
                        } else {
                            data = res.data;
                            let artTplHtml = body.find("#articleTpl").html();
                            let artTplView = body.find("#articleView");
                            laytpl(artTplHtml).render(data, function (html) {
                                artTplView.html(html);
                            })
                        }
                    }
                });

                // 查询此文章的评论
                $.ajax({
                    url: "http://localhost:8080/manager/comment/listCommentByArtId",
                    async: false,
                    data: {artId: data.artId},
                    success: function (res) {
                        if (res.code === -1) {
                            top.layer.msg(res.msg);
                        } else {
                            let commentHtml = body.find("#commentTpl").html();
                            let commentView = body.find("#commentView");
                            laytpl(commentHtml).render(res.data, function (html) {
                                commentView.html(html);
                            })
                        }
                    }
                })

                // 加载文章用户信息
                var user = data.user;
                let artUserHtml = body.find("#artUserTpl").html();
                let artUserView = body.find("#artUserView");
                laytpl(artUserHtml).render(user, function (html) {
                    artUserView.html(html);
                })

                // 查询文章类型信息
                $.ajax({
                    url: "http://localhost:8080/manager/article/findType/" + data.artId,
                    async: false,
                    success: function (res) {
                        let artTypeHtml = body.find("#artTypeTpl").html();
                        let artTypeView = body.find("#artTypeView");
                        laytpl(artTypeHtml).render(res.data, function (html) {
                            artTypeView.html(html);
                        })
                    }
                });

                setTimeout(function () {
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    }
})