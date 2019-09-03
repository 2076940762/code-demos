<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    hello <br>
    <a href="${pageContext.request.contextPath }/01-destory-session.jsp">session销毁</a> <br>
    <hr>
    <h5>属性</h5>
    <a href="${pageContext.request.contextPath }/02-attr/create.jsp">创建</a>
    <br>
    <a href="${pageContext.request.contextPath }/02-attr/replace.jsp">替换</a> <br>
    <a href="${pageContext.request.contextPath }/02-attr/replace.jsp">删除</a> <br>
    
    <hr>
            HttpSessionBindingListener(绑定和解绑)<br>
            检测java是否添加到session或者从session中移除<br>
    <a href="${pageContext.request.contextPath }/03-session/set.jsp">绑定</a> <br>
    <a href="${pageContext.request.contextPath }/03-session/remove.jsp">解绑</a> <br>
    
    <hr>
            HttpSessionActivationListener(钝化和活化) <br/>
            钝化:javabean从session中序列化到磁<br/>
            活化:javabean从磁盘上加载到了session<br/>
            <a href="${pageContext.request.contextPath }/03-session/bingStudent2session.jsp">java bean 绑定到session</a> <br>
            <a href="${pageContext.request.contextPath }/03-session/getJavabeanfromsession.jsp">从session中获取java bean</a> <br>
            
</body>
</html>