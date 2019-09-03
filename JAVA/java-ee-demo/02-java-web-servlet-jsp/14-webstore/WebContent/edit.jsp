<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <%--     <%
        /*  使用令牌机制防止页面刷新时重复提交*/
            String code = UUID.randomUUID().toString().replace("-", ".");
            session.setAttribute("scode", code);
            pageContext.setAttribute("pcode", code);
    %> --%>

    <form method="post" action="${pageContext.request.contextPath}/updateProduct">

        <table>
            <input type="hidden" name="pid" value="${bean.pid}" />
            <tr>
                <td>商品名</td>
                <td><input type="text" name="pname"
                    value="${bean.pname }"></td>
            </tr>
            <tr>
                <td>市场价</td>
                <td><input type="text" name="market_price"
                    value="${bean.market_price }"></td>
            </tr>
            <tr>
                <td>商城价</td>
                <td><input type="text" name="shop_price"
                    value="${bean.shop_price }"></td>
            </tr>
            <tr>
                <td>商品描述</td>
                <td><input type="text" name="pdesc"
                    value="${bean.pdesc }"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="保存"></td>

            </tr>
        </table>
    </form>

</body>
</html>