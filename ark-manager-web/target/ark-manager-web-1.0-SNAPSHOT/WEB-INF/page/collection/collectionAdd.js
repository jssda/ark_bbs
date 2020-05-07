layui.config({
    base: '/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    tag: 'tag' //如果 tag.js 是在根目录，也可以不用设定别名
}).use(['form', 'layer', 'layedit', 'laydate', 'upload', 'tag'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery,
        tag = layui.tag;


    //渲染下拉框插件
    var colUser = xmSelect.render({
        el: '#colUser',
        language: 'zn',
        name: 'colUserId',
        tips: '输入搜索用户或者选择',
        searchTips: '键入用户姓名搜索',
        radio: true,
        layVerify: "required",
        clickClose: true,
        filterable: true,
        delay: 500,
        paging: true,
        pageSize: 5,
        pageEmptyShow: false,
        model: {
            icon: 'hidden',
            label: {
                type: 'text'
            }
        },
        remoteSearch: true,
        remoteMethod: function (val, cb, show) {
            if (!val) {
                return cb([]);
            }
            $.ajax({
                url: "http://localhost:8080/manager/user/queryBy",
                data: {"key": val},
                success: function (res) {
                    return cb(res.data);
                }
            })
        },
        prop: {
            name: 'userName',
            value: 'userId',
        },
        data: function () {
            // 初始化10个用户数据
            let userList = null;
            $.ajax({
                url: "http://localhost:8080/manager/user/list",
                async: false,
                success: function (res) {
                    userList = res.data;
                }
            })
            return userList == null ? [] : userList;
        },
        template({item, sels, name, value}) {
            return item.userName + '<span style="position: absolute; right: 10px; color: #8799a3">ID:' + value + '</span>'
        }
    });

    var colArt = xmSelect.render({
        el: '#colArt',
        language: 'zn',
        name: 'colArtId',
        tips: '输入搜索文章或者选择',
        searchTips: '键入文章标题搜索',
        radio: true,
        layVerify: "required",
        clickClose: true,
        filterable: true,
        delay: 300,
        paging: true,
        // pageSize: 5,
        pageEmptyShow: false,
        pageRemote: true,
        model: {
            icon: 'hidden',
            label: {
                type: 'text'
            }
        },
        remoteSearch: true,
        remoteMethod: function (val, cb, show, pageIndex) {
            if (!val) {
                return cb([], 1);
            }
            $.ajax({
                url: "http://localhost:8080/manager/article/listby",
                data: {"key": val, page: pageIndex},
                success: function (res) {
                    return cb(res.data, parseInt((res.count / 5)));
                }
            })
        },
        prop: {
            name: 'artTitle',
            value: 'artId',
        },
        data: function () {
            // 初始化10个用户数据
            let artList = null;
            $.ajax({
                url: "http://localhost:8080/manager/article/list",
                async: false,
                success: function (res) {
                    artList = res.data;
                }
            })
            return artList == null ? [] : artList;
        },
        template({item, sels, name, value}) {
            return name + '<span style="position: absolute; right: 10px; color: #8799a3">ID:' + value + '</span>'
        }
    });

    form.on("submit(addCollection)", function (data) {
        //弹出loading
        let index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});

        // 提交信息
        $.ajax({
            url: "http://localhost:8080/manager/collection/addCollection",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify(data.field),
            success: function (res) {
                top.layer.close(index);
                top.layer.msg(res.msg);
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }
        });

        return false;
    });
});