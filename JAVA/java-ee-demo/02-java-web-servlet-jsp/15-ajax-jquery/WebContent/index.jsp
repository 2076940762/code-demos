<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="${pageContext.request.contextPath }/01-hello-ajax/01-hello-ajax.jsp">hello ajax</a>                      <br>
<a href="${pageContext.request.contextPath }/01-hello-ajax/02-ajax-get.jsp"> ajax get 方式发送请求</a>     <br>
<a href="${pageContext.request.contextPath }/01-hello-ajax/03-ajax-post.jsp"> ajax post 方式发送请求</a>  <br>
<a href="${pageContext.request.contextPath }/02-ajax-demo/01-check-user.jsp"> ajax 检查用户名是否存在</a>  <br>
<hr>
<a href="${pageContext.request.contextPath }/03-jquery-ajax/01-hello-jquery-ajax.jsp"> jquery ajax load</a>  <br>
<a href="${pageContext.request.contextPath }/03-jquery-ajax/02-jquery-ajax-checkuser.jsp"> jquery 检查用户名是否存在</a>  <br>
<a href="${pageContext.request.contextPath }/03-jquery-ajax/03-search-tips.jsp">搜索提示</a>  <br>
<a href="${pageContext.request.contextPath }/03-jquery-ajax/04-provinces-cityies.jsp">省市联动</a>  <br>

</body>
</html>