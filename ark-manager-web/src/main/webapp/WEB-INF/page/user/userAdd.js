layui.use(['form', 'layer', 'upload'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        upload = layui.upload;

    //普通图片上传
    let loading;
    var uploadInst = upload.render({
        elem: '#uploadImg'
        , url: 'http://localhost:8080/manager/pic/upload' //改成您自己的上传接口
        , before: function (obj) {
            loading = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功
            $("#userImg").val(res.data.src);
            top.layer.close(loading);
            top.layer.msg("上传成功");
        }
        , error: function () {
            top.layer.close(loading);
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.ajax({
            url: "http://localhost:8080/manager/user/addOrEditUser",
            type: "POST",
            data: JSON.stringify(data.field),
            contentType: "application/json; charset=UTF-8",
            success: function (res) {
                top.layer.close(index);
                top.layer.msg(res.msg);
                layer.closeAll("iframe");
                parent.location.reload();
            }
        });
        return false;
    });

    //格式化时间
    function filterTime(val) {
        if (val < 10) {
            return "0" + val;
        } else {
            return val;
        }
    }

    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear() + '-' + filterTime(time.getMonth() + 1) + '-' + filterTime(time.getDate()) + ' ' + filterTime(time.getHours()) + ':' + filterTime(time.getMinutes()) + ':' + filterTime(time.getSeconds());

});