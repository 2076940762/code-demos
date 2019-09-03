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
    <c:set var="tom" value="cat"></c:set>
    <c:choose>
        <c:when test="${tom==\"cat\" }"> tomcat</c:when>
        <c:otherwise>guess what</c:otherwise>
    </c:choose>

</body>
</html>