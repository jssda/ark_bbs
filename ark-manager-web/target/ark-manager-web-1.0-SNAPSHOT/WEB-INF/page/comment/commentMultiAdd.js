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

    //创建一个编辑器
    var editIndex = layedit.build('comMulContent', {
        height: 200,
        tool: ['left', 'center', 'right', '|', 'face'],
        uploadImage: {
            url: "http://localhost:8080/manager/pic/upload"
        }
    });
    // 创建编辑器的时候, 将textArera中的数据同步到编辑器中, 以追加方式添加
    layedit.setContent(editIndex, $("#comMulContent").text(), true);
    //用于同步编辑器内容到textarea
    layedit.sync(editIndex);

    //渲染下拉框插件
    var comMultiUserName = xmSelect.render({
        el: '#comMultiUserName',
        language: 'zn',
        name: 'commentMulUserId',
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

    form.on("submit(addCommentMulti)", function (data) {
        //弹出loading
        let index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 富文本编辑器中的内容
        data.field.comMulContent = layedit.getContent(editIndex).split('<audio controls="controls" style="display: none;"></audio>')[0];

        // 提交信息
        $.ajax({
            url: "http://localhost:8080/manager/comment/addAndEditCommentMulti",
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