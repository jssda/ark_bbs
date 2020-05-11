layui.config({
    version: "3.0.0"
    , base: 'http://localhost:8081/res/mods/' //这里实际使用时，建议改成绝对路径
}).extend({
    fly: 'index',
    tag: 'tag',
    cookie: 'cookie'
}).use(['cookie', 'fly', 'form', 'layer', 'util', 'laytpl', 'laypage', 'layedit', 'laydate', 'upload', 'tag'], function () {
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
        util = layui.util,
        cookie = layui.cookie;

    // 加载信息
    $.ajax({
        url: "http://localhost:8081/portal/index/listMessageByUserId",
        async: false,
        success: function (res) {
            let messageList = res.data;
            if (messageList == null) {
                messageList = {}
            }
            layui.each(messageList, function (index, item) {
                item.create = getTimeAge(item.create);
                let artId = item.mesContent;
                $.ajax({ // 获取每个消息的文章信息
                    url: "http://localhost:8081/portal/index/getArticleByArtId/" + artId,
                    async: false,
                    success: function (res) {
                        item.article = res.data;
                    }
                })
            });

            let getTpl = $("#messageTpl").html();
            laytpl(getTpl).render(messageList, function (html) {
                $("#messageView").html(html);
            });
        },
        complete: function (xhr, ts) {
            if ((xhr.status >= 300 && xhr.status < 400) && xhr.status != 304) {
                //重定向网址在响应头中，取出再执行跳转
                let redirectUrl = xhr.getResponseHeader('redirectUrl');
                let localUrl = location.href;
                location.href = redirectUrl + '?redirectUrl=' + localUrl;
            }
        }
    });

    // 删除信息
    $(document).on('click', 'a[name=delMes]', function () {
        let mesId = $(this).attr("lay-data");
        $.ajax({
            url: "http://localhost:8081/portal/index/removeMes/" + mesId,
            success: function (res) {
                layer.msg(res.msg);
                if (res.code == 200) {
                    setTimeout(function () {
                        location.reload();
                    }, 1000);
                }
            }
        })
    })

    /*'yyyy-MM-dd HH-mm-ss'格式的字符串转日期*/
    function stringToDate(str) {
        var tempStrs = str.split(" ");
        var dateStrs = tempStrs[0].split("-");
        var year = parseInt(dateStrs[0], 10);
        var month = parseInt(dateStrs[1], 10) - 1;
        var day = parseInt(dateStrs[2], 10);
        var timeStrs = tempStrs[1].split("-");
        var hour = parseInt(timeStrs [0], 10);
        var minute = parseInt(timeStrs[1], 10);
        var second = parseInt(timeStrs[2], 10);
        var date = new Date(year, month, day, hour, minute, second);
        return date;
    }

    function getTimeAge(str) {
        return util.timeAgo(stringToDate(str));
    }


});