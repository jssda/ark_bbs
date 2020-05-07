layui.config({
    version: "3.0.0"
    , base: '../../res/mods/' //这里实际使用时，建议改成绝对路径
}).extend({
    fly: 'index',
    tag: 'tag'
}).use(['fly', 'form', 'layer', 'laytpl', 'laypage', 'layedit', 'laydate', 'upload', 'tag'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        tag = layui.tag,
        fly = layui.fly;

    //加载公共页面资源
    $("#header").load("http://localhost:8081/html/common/header.html");
    $("#footer").load("http://localhost:8081/html/common/footer.html");

    let sectionCount;

    // 查询板块和板块中的数据, 渲染进页面
    function listSection(page, limit) {
        $.ajax({
            url: "http://localhost:8081/portal/index/listSection",
            data: {
                page: page,
                limit: limit
            },
            async: false,
            success: function (res) {
                let sectionList = res.data;
                sectionCount = res.count;
                // 查询指定板块的文章信息
                $.each(sectionList, function (index, item) {
                    $.ajax({
                        url: "http://localhost:8081/portal/index/listArticleBySecId/" + item.secId,
                        async: false,
                        success: function (result) {
                            item["articleList"] = result.data;
                        }
                    })
                })

                let getTpl = $("#arkSectionTpl").html()
                    , view = document.getElementById('arkSectionView');

                laytpl(getTpl).render(sectionList, function (html) {
                    view.innerHTML = html;
                });
            }
        })
    }

    listSection(1, 3);

    //执行一个laypage实例
    laypage.render({
        elem: 'sectionPage' //注意，这里的 test1 是 ID，不用加 # 号
        , count: sectionCount //数据总数，从服务端得到
        , limit: 3
        , jump: function (obj, first) {
            // 分页查询板块信息
            listSection(obj.curr, obj.limit);
        }
    });

    // 加载用户数据, 查看用户是否登录, 如果用户登录, 将用户信息缓存到浏览器
    let token = layui.data("token").token;
    if (token == null) {
        token = '';
    }
    $.ajax({
        url: "http://localhost:8083/sso/checkUserByToken",
        data: {
            token: token
        },
        success: function (res) {
            layer.msg(res.msg);
            let data = {};
            data.user = res.data;
            let tpl = $("#userInfoTpl").html();
            laytpl(tpl).render(data, function (html) {
                $(".fly-nav-user").html(html);
            });
        }
    });

});