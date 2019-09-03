<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <style>
        tr {
            height: 48px;
            border: cyan solid 0.0625rem;
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>

<body>
    <form method="post" action="${pageContext.request.contextPath }/upload" enctype="multipart/form-data">
        <table style="width: 75%; margin: auto;border: aqua solid 1px; padding: 2px;text-align: center;vertical-align: center
            ;">
            <tr>
                <td style="width: 30%;"></td>
                <td>用户名：</td>
                <td><input type="text" name="username"></td>
                <td style="width: 30%;"></td>
            </tr>
            <tr>
                <td></td>
                <td>文件名：</td>
                <td> <input type="file" name="filename" /> </td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td colspan="2"><input type="submit"></td>
                <td></td>
            </tr>
        </table>
    </form>

    <section>
        <br />
        <h2>
            浏览器端的要求:
        </h2>
        <br />
        表单的提交方法必须是post
        <br />
        必须有一个文件上传组件 <input type="file" name="" />
        <br />
        必须设置表单的enctype=multipart/form-data
        <br />
        <h2>
            服务器端的要求:
        </h2>
        <br />
        servlet3.0中
        <br />
        需要在servlet中添加注解
        <br />
        @MultipartConfig
        <br />
        接受普通上传组件 (除了文件上传组件):request.getParameter(name属性的值)
        <br />
        接受文件上传组件 request.getPart(name属性的值);
        <br />
        getName():获取的name的属性值
        <br />
        获取文件名:
        <br />
        part.getHeader("Content-Disposition"):获取头信息 然后截取
        <br />
    </section>

</body>

</html>