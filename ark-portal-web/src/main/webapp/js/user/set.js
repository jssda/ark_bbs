layui.config({
    version: "3.0.0"
    , base: '../../res/mods/' //这里实际使用时，建议改成绝对路径
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

    // 取得用户信息, 加载到表格中
    $.ajax({
        url: "http://localhost:8081/portal/index/getLoginUserInfo",
        success: function (res) {
            let userInfo = res.data;
            $("#userId").val(userInfo.userId);
            $("#userName").val(userInfo.userName);
            if (userInfo.userSex !== 1) {
                $("#female").prop('checked');
            }
            $("#email").val(userInfo.email);
            $("#telephone").val(userInfo.telephone);
            $("#userShow").text(userInfo.userShow);
            $('#uploadFile').attr('src', userInfo.userImg);
        }, complete: function (xhr, ts) {
            if ((xhr.status >= 300 && xhr.status < 400) && xhr.status != 304) {
                //重定向网址在响应头中，取出再执行跳转
                let redirectUrl = xhr.getResponseHeader('redirectUrl');
                let localUrl = location.href;
                location.href = redirectUrl + '?redirectUrl=' + localUrl;
            }
        }
    })

    // 修改用户信息
    form.on('submit(modifyUserInfo)', function (data) {
        // 修改用户信息
        $.ajax({
            url: "http://localhost:8081/portal/index/modifyUserInfo",
            data: data.field,
            success: function (res) {
                top.layer.msg(res.msg);
                if (res.code == 200) { // 修改成功, 退出重新登录
                    // 获取cookie
                    let token = $.cookie('sso_token');
                    $.ajax({
                        url: "http://localhost:8083/sso/logout",
                        async: false,
                        data: {
                            token: token
                        },
                        success: function (res) {
                            layer.msg(res.msg);
                            if (res.code === 200) {
                                // 删除 cookie
                                $.removeCookie("userInfo", {domain: 'localhost', path: '/'});
                                location.reload();
                            }
                        }
                    })
                }
            }
        })
        return false;
    })

    let loading;
    //普通图片上传
    var uploadInst = upload.render({
        elem: '#uploadImg'
        , url: 'http://localhost:8081/portal/pic/upload' //改成您自己的上传接口
        , before: function (obj) {
            loading = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#uploadFile').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            $.ajax({ // 修改后台图片路径
                url: "http://localhost:8081/portal/index/modifyUserInfo",
                data: {userId: $("#userId").val(), userImg: res.data.src},
                success: function (res) {
                    //上传成功
                    top.layer.close(loading);
                    top.layer.msg("上传成功, 重新登录即可看到新头像");
                }
            })
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });


    form.on('submit(modifyPass)', function (data) {
        let pass = $("#L_pass").val();
        let repass = $("#L_repass").val();
        if (pass !== repass) {
            layer.open({
                title: '错误提示',
                content: '确认密码必须和新密码一致',
                icon: 2
            });
            return false;
        }
        $.ajax({
            url: "http://localhost:8081/portal/index/modifyPass",
            type:"POST",
            data: data.field,
            success: function (res) {
                if (res.code === -1) {
                    layer.open({
                        title: '错误提示',
                        content: res.msg,
                        icon: 2
                    });
                } else if (res.code === 200) {
                    layer.open({
                        title: '提示',
                        content: res.msg + "请重新登录",
                        icon: 1
                    });
                    // 获取cookie
                    let token = $.cookie('sso_token');
                    $.ajax({
                        url: "http://localhost:8083/sso/logout",
                        async: false,
                        data: {
                            token: token
                        },
                        success: function (res) {
                            layer.msg(res.msg);
                            if (res.code === 200) {
                                // 删除 cookie
                                $.removeCookie("userInfo", {domain: 'localhost', path: '/'});
                                location.reload();
                            }
                        }
                    })
                }
            },
            complete: function (xhr, ts) {
                if ((xhr.status >= 300 && xhr.status < 400) && xhr.status != 304) {
                    //重定向网址在响应头中，取出再执行跳转
                    let redirectUrl = xhr.getResponseHeader('redirectUrl');
                    let localUrl = location.href;
                    location.href = redirectUrl + '?redirectUrl=' + localUrl;
                }
            }
        })
        return false;
    })
});