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

    //板块列表
    var tableIns = table.render({
        elem: '#sectionList',
        url: 'http://localhost:8080/manager/section/list',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limit: 20,
        limits: [10, 15, 20, 25],
        id: "sectionListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'secId', title: 'ID', width: 60, align: "center"},
            {field: 'secTitle', title: '板块内容', align:"center", width: 250},
            {
                field: 'secUser', title: '版主', align: 'center', templet: function (d) {
                    if (d.user != null)
                        return d.user.userName;
                    else
                        return "";
                }
            },
            {field: 'create', title: '发布时间', align: 'center', minWidth: 110},
            {field: 'update', title: '更改时间', align: 'center', minWidth: 110},
            {title: '操作', width: 250, templet: '#sectionListBar', fixed: "right", align: "center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        if ($(".searchVal").val() != '') {
            table.reload("sectionListTable", {
                url: 'http://localhost:8080/manager/section/listby',
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

    //添加板块
    function addSection(edit) {
        var index = layui.layer.open({
            title: '添加板块',
            type: 2,
            content: "sectionAdd.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    // 初始化板块id
                    body.find("#secId").val(edit.secId);
                    // 初始化版主
                    body.find("#userId").val(edit.secUserId);
                    body.find("#userName").val(edit.user.userName);
                    // 初始化内容
                    body.find("#secTitle").text(edit.secTitle);
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500);
            }
        });
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    }

    $(".addSection_btn").click(function () {
        addSection();
    });

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('sectionListTable'),
            data = checkStatus.data,
            secIds = [];
        if (data.length > 0) {
            for (let i in data) {
                secIds.push(data[i].secId);
            }
            layer.confirm('确定删除选中的板块？', {icon: 3, title: '提示信息'}, function (index) {
                var arr = {secIds: secIds};
                $.ajax({
                    url: "http://localhost:8080/manager/section/removeSections",
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
    table.on('tool(sectionList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'edit') { //编辑
            addSection(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此板块么？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: "http://localhost:8080/manager/section/removeSection/" + data.secId,
                    success: function (res) {
                        tableIns.reload();
                        layer.close(index);
                        top.layer.msg(res.msg);
                    }
                })
            });
        }
    });
})