layui.config({
    base: '../../res/mods/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    fly: 'index',
    cookie: 'cookie',
    tag: 'tag' //如果 tag.js 是在根目录，也可以不用设定别名
}).use(['cookie', 'fly', 'form', 'layer', 'laytpl', 'laypage', 'layedit', 'laydate', 'upload', 'tag'], function () {
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

    //加载公共页面资源
    $("#header").load("http://localhost:8081/html/common/header.html");
    $("#footer").load("http://localhost:8081/html/common/footer.html");

    // 删除一个tag
    tag.on('delete(tagType)', function (data) {
        var typeId = $(this).parent().attr('lay-id');
        $(".tagType").append('<input type="checkbox" lay-ignore="" name="typeIds" value="' + typeId + '" class="layui-hide" checked>');
    });

    // 新增的tag内容上传
    tag.on('add(tagType)', function (data) {
        var addText = $(data.othis).text();
        $(".tagType").append('<input type="checkbox" lay-ignore="" name="typeNames" value="' + addText + '" class="layui-hide" checked>');
    });


    //创建一个编辑器
    var editIndex = layedit.build('news_content', {
        height: 535,
        uploadImage: {
            url: "http://localhost:8081/portal/pic/upload"
        }
    });
    // 创建编辑器的时候, 将textArera中的数据同步到编辑器中, 以追加方式添加
    layedit.setContent(editIndex, $("#news_content").text(), true);
    //用于同步编辑器内容到textarea
    layedit.sync(editIndex);


    //格式化时间
    function filterTime(val) {
        if (val < 10) {
            return "0" + val;
        } else {
            return val;
        }
    }

    //自定义验证
    form.verify({
        newsName: function (val) {
            if (val === '') {
                return "文章标题不能为空";
            }
        }
    });


    //渲染下拉框插件
    let artSection = xmSelect.render({
        el: "#artSection",
        language: 'zn',
        name: 'artSecId',
        tips: '输入搜索板块或者选择',
        searchTips: '键入板块题目搜索',
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
                url: "http://localhost:8081/portal/index/queryBy",
                data: {"key": val},
                success: function (res) {
                    return cb(res.data);
                }
            })
        },
        prop: {
            name: 'secTitle',
            value: 'secId',
        },
        data: function () {
            // 初始化10个用户数据
            let sectionList = null;
            if ($("#secId").val() !== -1 && $("#secTitle").val() != -1) {
                sectionList = [];
                sectionList.push({
                    secId: $("#secId").val(),
                    secTitle: $("#secTitle").val(),
                    selected: true
                })
            } else {
                $.ajax({
                    url: "http://localhost:8081/portal/index/list",
                    async: false,
                    success: function (res) {
                        sectionList = res.data;
                    }
                });
            }
            return sectionList == null ? [] : sectionList;
        },
        template({item, sels, name, value}) {
            return name + '<span style="position: absolute; right: 10px; color: #8799a3">ID:' + value + '</span>'
        }
    });

    form.on("submit(addNews)", function (data) {
        //弹出loading
        let index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 富文本编辑器中的内容
        data.field.artContent = layedit.getContent(editIndex).split('<audio controls="controls" style="display: none;"></audio>')[0];

        //js开始
        var nameSet = new Set();
        $(".tagType input[type='checkbox']").each(function (i) {
            nameSet.add($(this).attr('name'));
        });
        nameSet.forEach(function (name) {
            var value = [];
            $("input[name=" + name + "]:checked").each(function (i) {
                value.push($(this).val());
            });

            data.field[name] = value;
        });

        // 提交信息
        $.ajax({
            url: "http://localhost:8081/portal/index/addArticle",
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

/*    //预览
    form.on("submit(look)", function () {
        layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问");
        return false;
    });*/
});

