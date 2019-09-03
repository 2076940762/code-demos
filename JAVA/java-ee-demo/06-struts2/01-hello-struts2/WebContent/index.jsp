<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="${pageContext.request.contextPath }/hello.action">Struts入门</a>
	<br>
	<a href="${pageContext.request.contextPath }/hellopojo.action">pojo	action </a>
	<br>
	<a href="${pageContext.request.contextPath }/implaction.action">实现Action接口</a>
	<br>
	<a href="${pageContext.request.contextPath}/actionsupport.action">继承ActionSupport类方式</a>
	
	<!--传统的配置文件方式  -->
	<a href="${pageContext.request.contextPath}/saveCustomer.action">保存客户信息</a> <br>
	<a href="${pageContext.request.contextPath}/deleteCustomer.action">删除客户信息</a>  <br>
	
	<!-- 通配符 -->
	<a href="${pageContext.request.contextPath}/contact_save.action">保存联系人信息</a>  <br>
	<a href="${pageContext.request.contextPath}/contact_update.action">更新联系人信息</a>  <br>
	
	<hr>
	<h4>动态访问方式</h4>
	
	<!-- 动态访问 -->
	<a href="${pageContext.request.contextPath}/contact!save.action">保存联系人信息</a>  <br>
	<a href="${pageContext.request.contextPath}/contact!update.action">更新联系人信息</a>  <br>

</body>
</html>