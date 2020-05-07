layui.use(['form', 'layer', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url: 'http://localhost:8080/manager/user/list',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 20,
        id: "userListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'userId', title: 'id', minWidth: 50, align: "center"},
            {field: 'userName', title: '用户名', minWidth: 100, align: "center"},
            {
                field: 'userSex', title: '用户性别', align: 'center', templet: function (d) {
                    return d.userSex == "1" ? "男" : "女";
                }
            },
            {field: 'userShow', title: '个性签名', align: 'center', minWidth: 200},
            {field: 'userImg', title: '头像链接', width: 100, align: 'center'},
            {
                field: 'email', title: '用户邮箱', minWidth: 200, align: 'center', templet: function (d) {
                    return '<a class="layui-blue" href="cc:' + d.email + '">' + d.email + '</a>';
                }
            },
            {field: 'telephone', title: '手机号', align: 'center', minWidth: 150},
            {field: 'password', title: '用户密码', align: 'center', minWidth: 150},
            {field: 'credit', title: '积分', align: 'center', minWidth: 100},
            {
                field: 'userLevel', title: '用户级别', align: 'center', templet: function (d) {
                    if (d.userLevel == 0) {
                        return '游客';
                    }
                    if (d.userLevel == 1) {
                        return '会员';
                    }
                    if (d.userLevel == 2) {
                        return '管理员';
                    }
                }
            },
            {field: 'create', title: '用户创建时间', align: 'center', minWidth: 200},
            {field: 'update', title: '信息更改时间', align: 'center', minWidth: 200},
            {title: '操作', minWidth: 175, templet: '#userListBar', fixed: "right", align: "center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        if ($(".searchVal").val() != '') {
            table.reload("userListTable", {
                url: 'http://localhost:8080/manager/user/listby',
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        } else {
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addUser(edit) {
        var title = "更改用户";
        if (edit == null || edit == undefined || edit == "") {
            title = "添加用户";
        }
        var index = layui.layer.open({
            title: title,
            type: 2,
            content: "userAdd.html",
            area: ['1130px', '450px'],
            maxmin: true,
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find(".userId").val(edit.userId); // 隐层的用户id
                    body.find(".userName").val(edit.userName);  //登录名
                    body.find(".userSex input[value=" + edit.userSex + "]").prop("checked", "checked");  //性别
                    body.find(".userShow").text(edit.userShow);    //用户简介
                    body.find(".userImg").val(edit.userImg);  //头像链接
                    body.find("#demo1").attr('src', edit.userImg);
                    body.find(".userEmail").val(edit.email);  //邮箱
                    body.find(".telephone").val(edit.telephone);  //手机号
                    body.find(".password").val(edit.password);  //密码
                    body.find(".userLevel").val(edit.userLevel);  //密码
                    body.find(".credit").val(edit.credit);  //积分
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 300)
            }
        });
        window.sessionStorage.setItem("index", index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    $(".addNews_btn").click(function () {
        addUser();
    });

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            newsId = [];
        if (data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].userId);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                    var arr = {usersId: newsId};
                    $.ajax({
                        url: "http://localhost:8080/manager/user/removeUsers",
                        type: "POST",
                        data: arr,
                        datatype: "json",
                        traditional: true,
                        success: function (res) {
                            tableIns.reload();
                            layer.close(index);
                            top.layer.msg(res.msg);
                        }
                    });
                }
            )
        } else {
            layer.msg("请选择需要删除的用户");
        }
    });

    //列表操作
    table.on('tool(userList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'edit') { //编辑
            addUser(data);
        } else if (layEvent === 'usable') { //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此用户？",
                btnText = "已禁用";
            if ($.trim(_this.text()) === "已禁用") {
                usableText = "是否确定启用此用户？";
                btnText = "已启用";
            }
            layer.confirm(usableText, {
                icon: 3,
                title: '系统提示',
                cancel: function (index) {
                    layer.close(index);
                }
            }, function (index) {
                var used = data.used;
                if (used == 0)
                    used = 1;
                else
                    used = 0;
                $.ajax({
                    url: "http://localhost:8080/manager/user/addOrEditUser",
                    type: "POST",
                    data: '{"userId": ' + data.userId + ', "used": ' + used + '}',
                    dataType: "json",
                    contentType: "application/json; charset=UTF-8",
                });

                _this.text(btnText);
                layer.close(index);
            }, function (index) {
                layer.close(index);
            });
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此用户？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("http://localhost:8080/manager/user/removeUser/" + data.userId, function (res) {
                    layer.close(index);
                    tableIns.reload();
                    top.layer.msg(res.msg);
                })
            });
        }
    });
})
;
