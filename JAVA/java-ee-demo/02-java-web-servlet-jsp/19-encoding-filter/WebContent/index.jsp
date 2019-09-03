<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        
        <style>
            tr {
                height: 50px;
                margin: auto;
                padding: 2px;
                text-align: center;
                vertical-align: middle;
            }
        </style>

    </head>
    <body>
        <form method="post" action="${pageContext.request.contextPath}/show">
            <table style="width: 85%; text-align: center;margin: auto;vertical-align:middle; border: aqua solid 0.0625rem;">
                <tr >
                    <td style="width: 15%;"></td>
                    <td style="width: 15%;"></td>
                    <td style="width: 20%;"></td>

                    <td style="width: 20%;"></td>
                    <td style="width: 15%;"></td>
                    <td style="width: 15%;"></td>

                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td> 用户名：</td>
                    <td>
                        <input type="text" name="username">
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td> 密码：</td>
                    <td>
                        <input type="password" name="password">
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td colspan="2">
                        <input type="submit">
                       </td>
                    <td></td>
                    <td></td>
                </tr>

            </table>
        </form>

    </body>
</html>
