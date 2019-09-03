<%@page import="hello.User"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
</head>
<body>
    <%
            request.setAttribute("i", 123);
            request.setAttribute("j", 321);
            request.setAttribute("k", "string");
            request.setAttribute("q", "1111");
            
            
            List l1=null;
            request.setAttribute("list1",l1);
            
            List l2=new LinkedList();
            request.setAttribute("list2", l2);
            
            List l3=new LinkedList<String>();
            l3.add("hello");
            request.setAttribute("list3", l3);
            
            User u1=null;
            request.setAttribute("usr1", u1);
            
            User u2=new User();
            request.setAttribute("usr2", u2);
    %>

    <H4>加法</H4>
    ${i+j }<BR>
    ${i+q }<BR>
    <%--${k+q }<BR>     org.apache.jasper.JasperException: 在 [18] 行处理 [/JSP/el/calculation.jsp] 时发生异常--%>
    <%--  ${k+i }    --%>
    
    <H2>判空</H2>
    list1是否为空：${empty list1 } <BR>
    list2是否为空：${empty list2 } <BR>
    list3是否为空：${empty list3 } <BR>
    
    <HR>
    ${empty usr1 }<BR>
    ${empty usr2 }<BR>
    <HR>
    
    ${2>3? "y":"N" } <BR>
    ${3==4}
    
    
    

</body>
</html>
