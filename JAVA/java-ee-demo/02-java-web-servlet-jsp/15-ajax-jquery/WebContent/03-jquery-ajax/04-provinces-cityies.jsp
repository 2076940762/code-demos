<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>省市联动</title>

    <!-- 导入jQuery -->
    <script src="/15-ajax-jquery/js/jquery-3.4.1.min.js"></script>

    <!--bootstrap  -->
    <link rel="stylesheet" href="/15-ajax-jquery/bootstrap-3.4.1-dist/css/bootstrap.min.css" />
    <script src="/15-ajax-jquery/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container">
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col-lg-3">&nbsp;</div>
            <select name="province" class="col-lg-2 col-md-4 col-sm-6 off" id="province-id">
                <option>--请选择--</option>
            </select>
            <div class="col-lg-1 col-md-1 col-sm-1"></div>
            <select name="city" class="col-lg-2 col-md-4 col-sm-6" id="city-id">
                <option>--请选择-- </option>
            </select>
        </div>
    </div>

</body>

<script type="application/javascript">
    $(function () {
        // 获取province 下拉选则框的jquery对象
        var $pro = $("#province-id");

        //页面加载成功后从数据库中查询所有的省份
        $.get("/15-ajax-jquery/getProvinces", function (d) {
            //将json字符串转为js对象
            // jQuery.parseJSON()函数用于将格式完好的JSON字符串转为与之对应的JavaScript对象
            var obj = $.parseJSON(d);

            // 遍历数组形式的json，将省份添加到下拉选中
            $(obj).each(function () {
                $pro.append($("<option value='" + this.provinceId + "'>" + this.name +
                    "</option>"));
            });
        });

        // 当province改变时，获取相应的市
        $pro.change(function () {
            var cityObj = $("#city-id");

            $.get("/15-ajax-jquery/getCities", "provinceId=" + $pro.val(), function (d) {
                //将服务端返回的json字符串转为js对象
                var obj = $.parseJSON(d);

                $(obj).each(function () {
                    cityObj.append($("<option value=" + this.cityID + ">" + this.name +
                        "</option>"));
                });
            });
        });
    });
</script>

</html>