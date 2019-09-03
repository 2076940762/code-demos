<%@page import="java.util.Map"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
        List<String> list = new LinkedList<String>();
            list.add("1111");
            list.add("22");
            list.add("33");
            list.add("44");
            request.setAttribute("list", list);
    %>
      <c:forEach items="${list }" var="item"> ${item}</c:forEach> <BR>
      
      <HR>
      <H3>set</H3>
      <%
      Set<String> set=new HashSet<String>();
      set.add("s1");
      set.add("s2");
      set.add("s3");
      set.add("s4");
      set.add("s5");
      request.setAttribute("set", set);
      %>
      
      <c:forEach items="${set }"   var="s">
      ${s }
      </c:forEach>
      
      
      <HR>
      <H3>map</H3>
      
      <%
      Map<String ,String> map=new HashMap<String ,String>();
      map.put("k1", "v1");
      map.put("k2", "v2");
      map.put("k3", "v3");
      map.put("k4", "v4");
      map.put("k5", "v5");
      request.setAttribute("map", map);
      %>
      
      
      <c:forEach items="${map }"  var="e">${e.key }=${e.value } </c:forEach>
    

    
</body>
</html>