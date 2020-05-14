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

    //搜索
    $('.fly-search').on('click', function () {
        layer.open({
            type: 1
            , title: false
            , closeBtn: false
            //,shade: [0.1, '#fff']
            , shadeClose: true
            , maxWidth: 10000
            , skin: 'fly-layer-search'
            , content: ['<form action="http://cn.bing.com/search">'
                , '<input autocomplete="off" placeholder="搜索内容，回车跳转" type="text" name="q">'
                , '</form>'].join('')
            , success: function (layero) {
                var input = layero.find('input');
                input.focus();

                layero.find('form').submit(function () {
                    var val = input.val();
                    if (val.replace(/\s/g, '') === '') {
                        return false;
                    }
                    input.val('site:localhost ' + input.val());
                });
            }
        })
    });
});