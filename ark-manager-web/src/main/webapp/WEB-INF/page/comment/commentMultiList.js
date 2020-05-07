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

    //展示二级评论
    var tableIns = table.render({
        elem: '#commentMultiList',
        url: 'http://localhost:8080/manager/comment/listCommentMulti',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limit: 20,
        limits: [10, 15, 20, 25],
        loading: true,
        id: "commentMultiListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'comMulId', title: 'ID', width: 60, align: "center"},
            {field: 'comMulContent', title: '多级评论内容', width: 200, align: "center"},
            {field: 'comMulCommentId', title: '一级评论id', width: 100, align: "center"},
            {field: 'replayId', title: '上一级评论id', width: 120, align: "center"},
            {
                field: 'replyType', title: '评论类型', width: 150, templet: function (d) {
                    if (d.replyType == 1) {
                        return "对二级评论的回复";
                    } else {
                        return "对一级评论的回复";
                    }
                }
            },
            {
                field: 'commentMulUser', title: '发布者', align: 'center', templet: function (d) {
                    if (d.commentMulUser != null)
                        return d.commentMulUser.userName;
                    else
                        return "";
                }
            },
            {
                field: 'targetUser', title: '评论目标用户', width: 150, align: 'center', templet: function (d) {
                    if (d.targetUser != null)
                        return d.targetUser.userName;
                    else
                        return "";
                }
            },
            {field: 'create', title: '发布时间', align: 'center', minWidth: 110},
            {field: 'update', title: '更改时间', align: 'center', minWidth: 110},
            {title: '操作', width: 250, templet: '#commentMultiListBar', fixed: "right", align: "center"}
        ]]
    });


    //通过发布者id搜索评论
    $(".search_btn_user").on("click", function () {
        if ($(".searchUserVal").val() !== '') {
            table.reload("commentMultiListTable", {
                url: 'http://localhost:8080/manager/comment/listCommentMultiByComUserId',
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    comUserId: $(".searchUserVal").val()  //搜索的关键字
                }
            })
        } else {
            layer.msg("请输入搜索的内容");
        }
    });

    //通过一级评论id搜索多级评论信息
    $(".search_btn_parent").on("click", function () {
        if ($(".searchParentVal").val() !== '') {
            table.reload("commentMultiListTable", {
                url: 'http://localhost:8080/manager/comment/listCommentMultiByCommentId',
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    commentId: $(".searchParentVal").val()  //搜索的关键字
                }
            })
        } else {
            layer.msg("请输入搜索的内容");
        }
    });

    //添加多级评论
    $(".addComMulti_btn").click(function () {
        addCommentMulti();
    });

    function addCommentMulti(edit) {
        let title = null;
        if (edit == null) {
           title = "添加新的评论多级内容";
        } else {
            title = "更改内容";
        }
        var index = layui.layer.open({
            title: title,
            type: 2,
            content: "commentMultiAdd.html", area: ['800px', '500px'],
            maxmin: true,
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find("#comMulId").val(edit.comMulId);

                    var userNameDom = body.find("#comMultiUserName");
                    userNameDom.parent().remove();
                    body.find("#comMultiUserName").val(edit.commentMulUser.userName);
                    body.find("#comMulContent").text(edit.comMulContent);
                    body.find("#comMulCommentId").val(edit.comMulCommentId);
                    body.find("#replayId").val(edit.replayId);
                    body.find(".replyType input[name='replyType']").prop("checked", edit.replyType == 1 ? "checked" : "");
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回评论列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })

    }

    //列表操作
    table.on('tool(commentMultiList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'edit') { //编辑
            addCommentMulti(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此评论？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: "http://localhost:8080/manager/comment/removeCommentMulti/" + data.comMulId,
                    success: function (res) {
                        tableIns.reload();
                        layer.close(index);
                        top.layer.msg(res.msg);
                    }
                })
            });
        }
    });


    //批量删除
    $(".delAll_btn").click(function () {
        let checkStatus = table.checkStatus('commentMultiListTable'),
            data = checkStatus.data,
            comMulIds = [];
        if (data.length > 0) {
            for (let i in data) {
                comMulIds.push(data[i].comMulId);
            }
            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
                var arr = {comMulIds: comMulIds};
                $.ajax({
                    url: "http://localhost:8080/manager/comment/removeCommentMultis",
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
            layer.msg("请选择需要删除的评论");
        }
    });

})