layui.use('jquery', function () {
    var $ = layui.jquery;

    (function () {
        let nav = layui.url().pathname[2];
        if (nav === 'collection.html') {
            $("#user-nav>.layui-nav-item").eq(1).addClass('layui-this');
        } else if (nav === 'set.html') {
            $("#user-nav>.layui-nav-item").eq(2).addClass('layui-this');
        } else if (nav === 'message.html') {
            $("#user-nav>.layui-nav-item").eq(3).addClass('layui-this');
        }
    })();

});