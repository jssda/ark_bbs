layui.use(['cookie', 'fly', 'form', 'layer', 'laytpl', 'laypage', 'layedit', 'laydate', 'upload', 'tag'], function () {
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
        cookie = layui.cookie;


    // 查询板块信息
    $.ajax({
        url: "http://localhost:8081/portal/index/listSection",
        data: {
            page: 1,
            limit: 10
        },
        async: false,
        success: function (res) {
            let sectionList = res.data;

            let getTpl = $("#secTpl").html();
            laytpl(getTpl).render(sectionList, function (html) {
                $("#secView").html(html);
            });
        }
    });

    let sec = layui.url().search.secId;
    if (sec == null) {
        $("#index").addClass("layui-this");
    } else {
        $("#sec" + sec).addClass("layui-this");
    }
});