<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>搜索提示</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
    <link href="../bootstrap-3.4.1-dist/css/bootstrap.min.css" rel="stylesheet" />
</head>

<body>
    <div class="container" style="margin-top: 80px;">
        <div class="row">
            <div class="col-lg-4 col-md-3"></div>
            <center class="col-lg-2  col-md-3 col-sm">
                <h1>搜索</h1>
            </center>
        </div>
        <div class="row" style="margin-top: 120px;margin-bottom: 0px; ">
            <div class="col-lg-4 col-md-3">&nbsp;</div>
            <input type="text" class="col-lg-3  col-md-4 col-sm-6" />
            <input type="button" value="search" class="col-lg-1 col-md-2 col-sm-3" />
        </div>
        <div class="row" style="margin-top: 0px;">
            <div class="col-lg-4 col-md-3">&nbsp;</div>
            <ul class="col-lg-3  col-md-4 col-sm-6" id="tips" style="display:none;list-style-type:none ;">
            </ul>
        </div>
    </div>
</body>

<script type="application/javascript">
    $(function () {
        // 按键弹起时发起查询
        $("input[type='text']").keyup(function () {
            // 先清空提示框
            $("#tips").html('');

            // 搜索框中输入的值
            var $kw = $(this).val();

            // 输入值不为空时发起查询
            if ($kw != null && $kw != '') {
                // $.get("${pagecontext.request.contextPath}/searchkw","keyword="+$kw,function(d){
                $.get("/15-ajax-jquery/searchkw", "keyword=" + $kw, function (d) {
                    var arr = d.split(",");
                    $(arr).each(function () {
                        $("#tips").append(("<li>" + this + "</li>"));
                    });
                })

                $("#tips").show();
            }

        });
    });
</script>

</html>