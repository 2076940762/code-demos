<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>jquery ajax</title>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script type="application/javascript">
        var url = "/15-ajax-jquery/heloJqueryAjax";

        // load1 get
        $(function () {
            $("#id-load").click(function () {
                
                //    jquery对象.load(url,params,function(数据){});
                $(this).load("/15-ajax-jquery/heloJqueryAjax", "username=王麻子", function (obj) {
                    alert(obj);
                });
            })
        });

        // load 2 post
        $(function () {
            $("#id-load2").click(function () {
                // jquery对象.load(url,params,function(数据){});
                $(this).load("/15-ajax-jquery/heloJqueryAjax", {
                    "username": "王麻子",
                    "sex": "未知"
                }, function (obj) {
                    alert(obj);
                });
            });
        });

        // get
        // ★: $.get(url,params,function(数据){},type);
        // 发送get请求的ajax
        // url:请求的路径
        // params:请求的参数 参数为key\value的形式 key=value {"":"","":""}
        // fn:回调函数 参数就是服务器发送回来的数据
        // type:返回内容格式，xml, html, script, json, text, _default。 以后用"json"
        $(function () {
            $("#id-load3").click(function () {
                $.get(url, {
                    "username": "张三",
                    "sex": "女"
                }, function (obj) {
                    alert(obj);
                });
            });
        });

        // post
        // ★: $.post(url,params,function(数据){},type);
        // 发送post请求的ajax
        $(function () {
            $("#id-load4").click(function () {
                $.post(url, {
                    "username": "张三",
                    "sex": "女"
                }, function (obj) {
                    alert(obj);
                });
            });
        });
        /*          $(function () {
                    $("#id-load4").click(function () {
                        $.post(url, {"username": "张三","sex": "女"}, function (obj) {
                            alert(obj);
                        });
                    });
                });  */

        // ajax
        // $.ajax({
        // url:"/day15/demo1",
        // type:"post",
        // data:"username=tom",
        // success:function(d){
        // alert(d.msg);
        // },
        // error:function(){},
        // dataType:"json"
        // });
        $(function () {
            $("#id-load5").click(function () {
                $.ajax({
                    url: "/15-ajax-jquery/heloJqueryAjax",
                    type: "post",
                    data: {"username": "张三","sex": "女"},
                    success: function (d) {
                        alert(d);
                    },
                    error: function () {},
                    dataType: "text"
                });

            });
        });
    </script>

</head>

<body>
    <h5>load</h5>
    <input type="button" value="jquery ajax load" id="id-load" />
    <input type="button" value="jquery ajax load" id="id-load2" />
    <hr />

    <h5>get</h5>
    <input type="button" value="jquery ajax load" id="id-load3" />

    <h5>post</h5>
    <input type="button" value="jquery ajax load" id="id-load4" />

    <h5>ajax</h5>
    <input type="button" value="jquery ajax load" id="id-load5" />
</body>

</html>