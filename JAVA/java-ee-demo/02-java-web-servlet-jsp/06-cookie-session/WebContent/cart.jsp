<%@page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" width="45%" height="200px" border="1">
		<tr>
			<td>商品名称</td>
			<td>数量</td>
		</tr>

		<%
			//1获取购物车
			LinkedHashMap<String, Integer> trolley = (LinkedHashMap<String, Integer>) request.getSession()
					.getAttribute("trolley");
			if (trolley == null) {
				out.println("<td colspan=2>购物车为空</td>");
			} else {
				for (String key : trolley.keySet()) {
					out.println("<tr>");
					out.println("<td>");
					out.println(key);
					out.println("</td>");

					out.println("<td>");
					out.println(trolley.get(key));
					out.println("</td>");
					out.println("</tr>");
				}
			}
		%>
	</table>
	<br />
	<div align="center">
		<a href="/06-cookie-session/product_list.jsp">继续购物</a> <br/>
		<a href="/06-cookie-session/clearCart">清空购物车</a> <br/>
	</div>
</body>
</html>
