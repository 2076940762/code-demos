<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.4.1-dist/css/bootstrap.min.css" />
</head>

<body>
    <div class="container-fluid " style="border: lightcoral 1px solid; background-color: lightsalmon;">
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <div class="row">
                <div class="col-lg-4 col-md-3 col-sm-3"></div>
                <div class="col-lg-1 col-md-2 col-sm-3 ">用户名：</div>
                <div class="col-lg-2 col-md-4 col-sm-5">
                    <input type="text" name="username">
                </div>
            </div>

            <div class="row">&nbsp;</div>

            <div class="row">
                <div class="col-lg-4 col-md-3 col-sm-3"></div>
                <div class="col-lg-1 col-md-2 col-sm-3">密码：</div>
                <div class="col-lg-2 col-md-4 col-sm-5">
                    <input type="password" name="password">
                </div>
            </div>

            <div class="row">&nbsp;</div>

            <div class="row">
                <div class="col-lg-5 col-md-3 col-sm-3"></div>
                <div class="col-lg-1 col-md-2 col-sm-3 text-center">
                    <input type="checkbox" name="savepwd" value="ok" />记住密码
                </div>
                <div class="col-lg-1 col-md-2 col-sm-3 text-center">
                    <input type="checkbox" name="autologin" value="ok" />自动登录
                </div>
            </div>


            <div class="row">&nbsp;</div>

            <div class="row">
                <div class="col-lg-4 col-md-3 col-sm-3"></div>
                <div class="col-lg-3 col-md-6 col-sm-8">
                    <input type="submit" class="center-block">
                </div>
            </div>
        </form>
    </div>

</body>

</html>