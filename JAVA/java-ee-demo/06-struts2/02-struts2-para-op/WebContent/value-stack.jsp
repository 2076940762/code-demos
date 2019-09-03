<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<s:property value="[0]" />
	<br />
	<hr>
	<s:property value="[0].msg" />
	<br />
	<hr>
	<s:property value="[0].top" />
	<br />
	<hr>

	<s:property value="username" />
	<br />
	<hr>
	<s:property value="password" />
	<br />
	<hr>
	<s:property value="age" />
	<br />
	<hr>
	<s:property value="gender" />
	<br />
	<hr>
	<s:debug></s:debug>

	<p>*******************迭代*******************</p>
	<p>不写 var 属性时，循环的临时变量保存在root栈顶</p>
	<s:iterator value="list">
		<s:property value="[0].top.username" />
		<s:property value="age" />
		<s:property value="gender" />
		<br>
	</s:iterator>

	<p>有var属性时，循环变量保存在context map中</p>
	<s:iterator value="list" var="user">
		<s:property value="#user.username" />
		<s:property value="#user.password" />
		<br>
	</s:iterator>

	<h5>put</h5>
	<s:iterator value="[0].top" var="user">
		<s:property value="#user.username" />
		<s:property value="#user.password" />
		<br>
	</s:iterator>

	<s:iterator value="[0].top">
		<s:property value="[0].username" />
		<s:property value="password" />
		<br>
	</s:iterator>

</body>

</html>