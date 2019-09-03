<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
        request.setAttribute("rKey", "rValue");
		session.setAttribute("sKey", "sValue");
		application.setAttribute("aKey", "aValue");
    %>

    <H5>获取request中的数据：</H5>
    原来的方式：<%=request.getAttribute("rKey")%>    <BR> 
    el方式：${requestScope.rKey}

    <hr/>
    <H5>获取session中的数据</H5>
    原来的方式：<%=session.getAttribute("sKey") %><BR>
    EL方式：${sessionScope.sKey }<BR>
    
    <HR>
    
    <H5>获取application中的数据：</H5>
    原方式：<%=application.getAttribute("aKey") %> <BR>
    EL方式：${applicationScope.aKey }
    
    <HR>
    
    <H3>获取数据失败:</H3>
    原方式：<%=application.getAttribute("aa") %> <BR>
    El方式：${applicationScope.aa} <BR>
    
	<hr/>
	<h4>  便捷获取简单数据	</h4>
	${sKey},${rKey},${aa},${aKey}
	
	<%session.setAttribute("rKey","11111");%>
	
	${rKey}
	
	
</body>
</html>