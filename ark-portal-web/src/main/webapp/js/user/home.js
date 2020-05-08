layui.use(['cookie', 'fly', 'form', 'layer', 'laytpl', 'laypage', 'layedit', 'laydate', 'upload', 'tag', 'util'], function () {
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

    (function () {
        // 加载用户信息
        var url = layui.url();
        let data = null;
        if (url.search.userId != null) { // 查询其他用户信息
            $.ajax({
                url: "http://localhost:8081/portal/index/getUserInfoByUserId/" + url.search.userId,
                async: false,
                success: function (res) {
                    data = res.data;
                    data.isMe = null;
                }
            })
        } else {
            data = layui.data('userInfo').user;
            if (data != null) {
                data.isMe = 1;
            }
        }

        let getTpl = $("#userTpl").html();
        laytpl(getTpl).render(data, function (html) {
            $("#userView").html(html);
        });

        // 加载最近发帖信息
        if (data == null) {
            $(".jie-row").html('<div style="text-align: center; margin-top: 20px">最近没有发帖</div>')
        } else {
            $.ajax({
                url: "http://localhost:8081/portal/index/listArticleByUserIdAndPageNum",
                data: {userId: data.userId, page: 1, limit: 10},
                async: false,
                success: function (res) {
                    let data = res.data;

                    layui.each(data, function (index, it) {
                        it.create = util.timeAgo(stringToDate(it.create));
                    })

                    let getTpl = $("#jieTpl").html();
                    laytpl(getTpl).render(data, function (html) {
                        $(".jie-row").html(html);
                    });

                }
            })
        }
    })()

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
});