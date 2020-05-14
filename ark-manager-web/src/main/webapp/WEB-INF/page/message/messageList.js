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
        elem: '#messageList',
        url: 'http://localhost:8080/manager/message/listMessage',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limit: 20,
        limits: [10, 15, 20, 25],
        loading: true,
        id: "messageListTable",
        cols: [[
            {field: 'mesId', title: 'ID', width: 60, align: "center"},
            {field: 'mesTitle', title: '消息标题', align: 'center',},
            {field: 'mesContent', title: '信息内容', align: 'center'},
            {field: 'mesState', title: '信息状态', align: 'center'},
            {field: 'create', title: '发布时间', align: 'center', minWidth: 110},
            {field: 'update', title: '更改时间', align: 'center', minWidth: 110},
        ]]
    });
})