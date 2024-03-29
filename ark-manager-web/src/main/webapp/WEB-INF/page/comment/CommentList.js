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

    //展示列表
    var tableIns = table.render({
        elem: '#commentList',
        url: 'http://localhost:8080/manager/comment/listComment',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limit: 20,
        limits: [10, 15, 20, 25],
        loading: true,
        id: "commentListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'comId', title: 'ID', width: 60, align: "center"},
            {field: 'comContent', title: '评论内容', width: 250},
            {
                field: 'comUserId', title: '发布者', align: 'center', templet: function (d) {
                    if (d.user != null)
                        return d.user.userName;
                    else
                        return "";
                }
            },
            {
                field: 'comArt', title: '评论文章标题', align: 'center', templet: function (d) {
                    if (d.article != null) {
                        return d.article.artTitle;
                    } else {
                        return "";
                    }
                }
            },
            {field: 'create', title: '发布时间', align: 'center', minWidth: 110},
            {field: 'update', title: '更改时间', align: 'center', minWidth: 110},
            {title: '操作', width: 250, templet: '#commentsListBar', fixed: "right", align: "center"}
        ]]
    });

    //搜索
    $(".search_btn").on("click", function () {
        if ($(".searchVal").val() != '') {
            table.reload("commentListTable", {
                url: 'http://localhost:8080/manager/comment/listCommentBy',
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    artId: $(".searchVal").val()  //搜索的关键字
                }
            })
        } else {
            layer.msg("请输入搜索的内容");
        }
    });

    //添加评论
    $(".addComment_btn").click(function () {

        var index = layui.layer.open({
            title: '添加新的评论内容',
            type: 2,
            content: "commentAdd.html", area: ['800px', '500px'],
            success: function (layero, index) {
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

    });

    //列表操作
    table.on('tool(commentList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'edit') { //编辑
            layer.prompt({title: '更改评论内容', formType: 2, value: data.comContent}, function (pass, index) {
                layer.close(index);
                $.ajax({
                    url: "http://localhost:8080/manager/comment/editComment",
                    type: "POST",
                    data: {
                        comContent: pass,
                        comId: data.comId
                    },
                    success: function (res) {
                        if (res !== -1) {
                            table.reload("commentListTable");
                        }
                        layer.msg(res.msg);
                    }
                })
            });
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此评论？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: "http://localhost:8080/manager/comment/removeComment/" + data.comId,
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
        let checkStatus = table.checkStatus('commentListTable'),
            data = checkStatus.data,
            comIds = [];
        if (data.length > 0) {
            for (let i in data) {
                comIds.push(data[i].comId);
            }
            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
                var arr = {comIds: comIds};
                $.ajax({
                    url: "http://localhost:8080/manager/comment/removeComments",
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