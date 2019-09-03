<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	  <%	pageContext.setAttribute("k1", "value1", pageContext.REQUEST_SCOPE);	%>    <br/>
	 <%  session.setAttribute("k1", "s1"); %><br/>
	 <%  pageContext.setAttribute("k1", "s1"); %><br/>
	
	request中的K1：        <%=request.getAttribute("k1")	%><br/>
   	pageContext中的K1：<%=pageContext.getAttribute("k1") %><br/>
   	session中的K1：         <%=session.getAttribute("k1") %><br/>
   	
    快捷查找K1：         <%=pageContext.findAttribute("k1") %><br/>

</body>
</html>