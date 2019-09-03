<%@page import="domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
欢迎${user.username }回来 <br>
欢迎${sessionScope.user.username }回来 <br>
<%-- <%=((User)(session.getAttribute("user"))).getUsername() %> <br> --%>
</body>
</html>