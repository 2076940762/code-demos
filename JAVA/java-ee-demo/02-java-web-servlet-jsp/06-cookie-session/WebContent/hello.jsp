<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%-- jsp的脚本:	
		<%...%> java程序片段
    生成成jsp的service方法中
    <%=...%> 输出表达式
    生成成jsp的service方法中,相当于在java中调用out.print(..)
    <%!...%> 声明成员
    成员位置. --%>
<body>
	<%-- http://localhost:8080/06-jsp-demo/hello.jsp --%>
	hello java server pages
	<%
		out.write("\r\n");
		out.println("java 代码");
		int i = 110;
	%>

	<%=i%>

	<%=pi%>
	<%!double pi = 3.2425;%>
</body>
</html>
