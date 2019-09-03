<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>

<body>
    <form action="${pageContext.request.contextPath }/login" method="get">
        <table>
            <tr>
                <td></td>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td>密码</td>
                <td><input type="password" name="password"></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td colspan="2"><input type="submit"></td>
                <td></td>
            </tr>
        </table>
    </form>

</body>

</html>