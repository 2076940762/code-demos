<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>

<body>
	<input type="text" value="看我变不变" /> <br>
	
	<input type="button" value="hello-ajax" onclick="helloAjax()" />

</body>
<script type="text/javascript">
	function helloAjax() {
		/* 1.创建一个核心对象 XMLHttpRequest*/
		xmlhttp = null;
		if (window.XMLHttpRequest) { // code for Firefox, Opera, IE7, etc.
			xmlhttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) { // code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		//  2.编写一个回调函数
		xmlhttp.onreadystatechange = function () {
			/* 	            0:核心对象创建
				            1:调用了open方法
				            2:调用了send方法
				            3:部分响应已经生成(没有意思)
				            4:响应已经完成(使用的是这个状态) */
			/* alert(xmlhttp.readyState); */

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				alert("ok");
			}
		}

		//  3.编写请求方式和请求的路径(open操作)
		xmlhttp.open("get", "${pageContext.request.contextPath}/hello");

		// 4.发送请求(send操作)
		xmlhttp.send()
	}
</script>

</html>