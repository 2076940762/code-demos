<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
</head>

<body>
    <form method="post" action="">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="username" onblur="checkUsr(this)" onfocus="clearWaring()"></td>
                <td><span id="usr-msg"></span></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password"></td>
                <td><span></span></td>
            </tr>
            <tr>
                <td colspan="3"><input type="submit" id="sub" /></td>
            </tr>
        </table>
    </form>

    <script type="text/javascript">
        $(function () {
            $("input[name='username']").blur(function () {

                $.get("${pageContext.request.contextPath}/checkUsr", "username="+$(this).val(),function(d){
                    if(d==1){
                        $("#usr-msg").html("<font color='lightgreen'>OK</font>");
                    }
                    else{
                        $("#usr-msg").html("<font color='red' > 用户名已经被注册 </font>");
                    }
                });
            });
        });
    </script>

</body>

</html>