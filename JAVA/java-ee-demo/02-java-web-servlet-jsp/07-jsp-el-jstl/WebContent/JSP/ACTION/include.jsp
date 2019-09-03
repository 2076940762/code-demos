<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <H1>动态包含：</H1>
    将其他页面运行结果复制过来
    <HR>
    <H3>from jsp</H3>
    <jsp:include page="/JSP/include/JSP.jsp"></jsp:include>
    <HR>
    <H3>from html</H3>
    <jsp:include page="/JSP/include/HTML.html"></jsp:include>

</body>
</html>