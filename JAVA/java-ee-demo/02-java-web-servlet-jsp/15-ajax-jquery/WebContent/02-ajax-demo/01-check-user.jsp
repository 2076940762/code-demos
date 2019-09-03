<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>检查用户名是否已经被使用</title>
    </head>
    <body>
        <form method="post" action="">
            <table>
                <tr>
                    <td>用户名：</td>
                    <td><input type="text" name="username" onblur="checkUsr(this)" onfocus="clearWaring()"></td>
                    <td> <span id="usr-msg"></span> </td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td><input type="password" name="password"></td>
                    <td><span></span> </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="submit" id="sub" />
                    </td>
                </tr>
            </table>
        </form>

    </body>
    <script type="application/javascript">
        function checkUsr(obj) {
            // axja 核心对象
            var xmlhttp = new XMLHttpRequest();

            // 回调函数
            xmlhttp.onreadystatechange = function() {
                var obj2 = document.getElementById("usr-msg");
                var obj3 = document.getElementById("sub");
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    if (xmlhttp.responseText == 1) {
                        /*                     if(xmlhttp.responseText == "1"){ */
                        // 用户名可以使用
                        obj2.innerHTML = "<font  color='lightgreen'>OK</font>";
                        obj3.isDisabled = false;
                    } else {
                        obj2.innerHTML = "<font  color='red'>该用户名已经被注册，换一个试试</font>";
                        obj3.isDisabled = true;
                    }
                }
            }

            xmlhttp.open("GET", "${pageContext.request.contextPath}/checkUsr?username=" + obj.value);

            xmlhttp.send();
        }

        function clearWaring() {
            var obj = document.getElementById("usr-msg");
            obj.innerHTML="";
        }
    </script>
</html>
