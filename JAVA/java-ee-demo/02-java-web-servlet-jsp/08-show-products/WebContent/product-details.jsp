<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <TABLE border="1  solid"   align="center">
        <TR>
            <TD>商品id</TD>
            <TD>商品名称</TD>
            <TD>单价</TD>
            <TD>简介</TD>
        </TR>

        <c:forEach items="${productList }" var="p">
            <TR>
                <TD>${p.id }</TD>
                <TD>${p.pname }</TD>
                <TD>${p.price }</TD>
                <TD>${p.pdesc }</TD>
            </TR>
        </c:forEach>
    </TABLE>

</body>
</html>