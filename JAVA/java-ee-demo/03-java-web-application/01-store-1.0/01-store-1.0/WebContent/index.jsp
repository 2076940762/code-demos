<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
    </head>
    <body>

        <h1>欢迎访问</h1>

        <h2>
            <a href="${pageContext.request.contextPath}/index">用户界面</a>
        </h2>

        <h2>
            <a href="${pageContext.request.contextPath}/admin/home.jsp">后台管理界面</a>
        </h2>

        <%-- <jsp:forward page="/index"></jsp:forward> --%>
    </body>
</html>
