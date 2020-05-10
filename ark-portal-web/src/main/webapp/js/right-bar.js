layui.use(['layer', 'laytpl', 'cookie', 'jquery', 'element'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        element = layui.element,
        cookie = layui.cookie;

    let secId = layui.url().search.secId;
    // 查询评论最多的10篇文章
    $.ajax({
        url:"http://localhost:8081/portal/index/mostCommentBySecId",
        data:{secId: secId},
        success:function (res) {
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

    // 查询热度最高的10篇文章
    $.ajax({
        url:"http://localhost:8081/portal/index/mostHotBySecId",
        data:{secId:secId},
        success:function (res) {
            let data = res.data;
            if (data == null) {
                data = {}
            }
            let tpl = $("#mostHotTpl").html();
            laytpl(tpl).render(data, function (html) {
                $("#mostHotView").html(html);
            });
        }
    })
});