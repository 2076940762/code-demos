<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	<h3>完全解耦合的方式</h3>
	<form method="post"
		action="${pageContext.request.contextPath}/login.action">
		<table>
			<tr>
				<td></td>
				<td>用户名:</td>
				<td><input type="text" name="username"></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>密码:</td>
				<td><input type="password" name="password"></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2"><input type="submit"></td>
				<td></td>
			</tr>

		</table>
	</form>

	<h3>原生的API方式</h3>
	<form method="post"
		action="${pageContext.request.contextPath}/login2.action">
		<table>
			<tr>
				<td></td>
				<td>用户名:</td>
				<td><input type="text" name="username"></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>密码:</td>
				<td><input type="password" name="password"></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2"><input type="submit"></td>
				<td></td>
			</tr>

		</table>
	</form>
</body>

<!-- 重定向到action  -->
<a href="${pageContext.request.contextPath }/save.action">
	重定向到action</a>

<br>

<!-- 属性驱动数据封装  -->
<h4>属性驱动数据封装</h4>
<form method="post"
	action="${pageContext.request.contextPath}/AttrAction.action">
	<table>
		<tr>
			<td></td>
			<td>用户名:</td>
			<td><input type="text" name="username"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>密码:</td>
			<td><input type="password" name="password"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>性别:</td>
			<td><input type="text" name="gender"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>年龄:</td>
			<td><input type="text" name="age"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2"><input type="submit"></td>
			<td></td>
		</tr>

	</table>
</form>


<!-- 属性驱动数据封装  -->
<h4>属性驱动数据封装二 javaBean方式</h4>
<form method="post"
	action="${pageContext.request.contextPath}/javaBeanAction.action">
	<table>
		<tr>
			<td></td>
			<td>用户名:</td>
			<td><input type="text" name="user.username"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>密码:</td>
			<td><input type="password" name="user.password"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>性别:</td>
			<td><input type="text" name="user.gender"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>年龄:</td>
			<td><input type="text" name="user.age"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2"><input type="submit"></td>
			<td></td>
		</tr>

	</table>
</form>

<hr>
<h2>模型驱动的数据封装模式</h2>
<form method="post"
	action="${pageContext.request.contextPath}/modelAction.action">
	<table>
		<tr>
			<td></td>
			<td>用户名:</td>
			<td><input type="text" name="username"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>密码:</td>
			<td><input type="password" name="password"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>性别:</td>
			<td><input type="text" name="gender"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>年龄:</td>
			<td><input type="text" name="age"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2"><input type="submit"></td>
			<td></td>
		</tr>

	</table>
</form>

<!-- 将数据封装到list集合中 -->
<h3>将数据封装到list集合中</h3>
<form method="post"
	action="${pageContext.request.contextPath}/listAction.action">
	<table>
		<tr>
			<td></td>
			<td>用户名:</td>
			<td><input type="text" name="list[0].username"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>密码:</td>
			<td><input type="password" name="list[0].password"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>性别:</td>
			<td><input type="text" name="list[0].gender"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>年龄:</td>
			<td><input type="text" name="list[0].age"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<p>======================</p>
			</td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>用户名:</td>
			<td><input type="text" name="list[1].username"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>密码:</td>
			<td><input type="password" name="list[1].password"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>性别:</td>
			<td><input type="text" name="list[1].gender"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>年龄:</td>
			<td><input type="text" name="list[1].age"></td>
			<td></td>
		</tr>

		<tr>
			<td></td>
			<td colspan="2"><input type="submit"></td>
			<td></td>
		</tr>

	</table>
</form>

<h3> 将数据封装到map集合中  </h3>
<form method="post"
	action="${pageContext.request.contextPath}/mapAction.action">
	<table>
		<tr>
			<td></td>
			<td>用户名:</td>
			<td><input type="text" name="map['u1'].username"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>密码:</td>
			<td><input type="password" name="map[''u1].password"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>性别:</td>
			<td><input type="text" name="map['u1'].gender"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>年龄:</td>
			<td><input type="text" name="map['u1'].age"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<p>======================</p>
			</td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>用户名:</td>
			<td><input type="text" name="map['u2'].username"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>密码:</td>
			<td><input type="password" name="map['u2'].password"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>性别:</td>
			<td><input type="text" name="map['u2'].gender"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>年龄:</td>
			<td><input type="text" name="map['u2'].age"></td>
			<td></td>
		</tr>

		<tr>
			<td></td>
			<td colspan="2"><input type="submit"></td>
			<td></td>
		</tr>

	</table>
</form>


<!--  拦截器-->
<h2>拦截器</h2>
<a href="${pageContext.request.contextPath }/myAction"">拦截器1</a>

</html>