<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
    //数组
        request.setAttribute("arr", new String[]{"asdfa","wrfghsdfgsdfg","dasfga"} );
    
    //list
    List<String> list=new LinkedList<String>();
    request.setAttribute("list", list);
    list.add("11111");
    list.add("2222");
    list.add("113333111");
    list.add("45555");
    
    //map
    Map<String ,String> map =new HashMap();
    request.setAttribute("map", map);
    map.put("k1", "v1");
    map.put("k2", "v2");
    map.put("k3", "v3");
    map.put("k4", "v4");
   
    %>
    
    <HR>
    <H3>获取数组元素</H3>
        方式一：<%=( (String[])request.getAttribute("arr") )[0] %> <BR>
        方式二：${arr[0] } <BR>
        
        <HR>
        <H3>获取list元素</H3>
        方式一：<%=((List<String>)request.getAttribute("list")).get(0) %> <BR>
        方式二：${list[0] } <BR>
        <HR>
        <H3>获取map中的元素</H3>
        方式一：<%=(( Map<String ,String>)request.getAttribute("map")).get("k1") %> <BR>
        方式二：${map.k1 }
        
        <HR>
        <H1>含有特殊符号的属性获取：</H1>
        <% request.setAttribute("tom.age", 18); %>
        ${requestScope["tom.age"] }
        
        
        
</body>
</html>