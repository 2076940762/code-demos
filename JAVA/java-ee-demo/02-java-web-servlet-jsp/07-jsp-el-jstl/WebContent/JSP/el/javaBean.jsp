<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="hello.User" %>
<!DOCTYPE html>
<html>s
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
        hello.User u = new hello.User();
        u.setAge(111);
        u.setId(1234);
        u.setName("tom");
        
        request.setAttribute("user", u);
    %>
    <HR>
    
    方式一：<%=((hello.User)request.getAttribute("user")).getName()%><BR>
    方式二：${user.name }

</body>
</html>