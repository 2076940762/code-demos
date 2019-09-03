<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>商品信息</title>

    <style>
        table {
            width: 98%;
            margin: 15px;
            background-color: lightcoral;
            text-align: center;
            border-color: chartreuse;
            border-style: double;
            border-width: 2px;
        }

        tr td {
            border-color: chartreuse;
            border-style: solid;
            border-width: 1px;
        }

        /* 导航栏*/
        ul {
            list-style-type: none;
            margin: auto;
            overflow: hidden;
            padding: 15px;
            width: 45%;
            /* background-color: rgb(240, 133, 94) ; */
            background-color: blanchedalmond;
            text-align: center;
            vertical-align: center;
            height: 50px;
        }

        li {
            float: left;
            margin-left: 2%;
            margin-right: 2%;
            margin-top: auto;
            margin-top: auto;
            padding: 1%;
            width: 19%;
            background-color: blanchedalmond;
        }

        li a {
            display: block;
            /* color: rgb(19, 194, 19); */
            color: deeppink;
            padding: 8px 16px;
            text-decoration: none;
        }

        li a.active {
            background-color: rgb(224, 224, 19);
            color: white;
        }

        li a:hover:not (.active) {
            background-color: rgb(80, 223, 175);
            color: rgb(243, 212, 39);
        }
    </style>

</head>

<body>

    <!-- <form id="form-id" action="${pageContext.request.contextPath}/deleteSelected" method="post"> -->
    <table style="border: chartreuse solid 5px;">
        <tr style="border: chartreuse solid 5px;">
            <td><input type="checkbox" onclick="selectAll(this)" />全选
            </td>
            <td>商品名称</td>
            <td>商品图片</td>
            <td>市场价</td>

            <td>商城价</td>
            <td>商品描述</td>
            <td>操作</td>
        </tr>
        <form id="form-id" action="${pageContext.request.contextPath}/showCurrPage" method="post">
            <c:forEach items="${pBean.list}" var="p">
                <tr style="height: 120px;">
                    <td><input type="checkbox" name="pid" value="${p.pid}" /></td>
                    <td width="5%">${p.pname }</td>
                    <td width="10%"><img src="${p.pimage}" width="38px" /></td>
                    <td width="5%">${p.market_price}</td>

                    <td width="5%">${p.shop_price}</td>
                    <td>${p.pdesc}</td>
                    <td width="7%"><a href="${pageContext.request.contextPath}/getProductById?pid=${p.pid}">
                            修改 </a> | <a href="javascript:void(0)" onclick="deleteProduct('${p.pid}')"> 删除 </a></td>
                </tr>
            </c:forEach>
        </form>
    </table>

    <!-- 页面导航栏-->
    <ul>
        <c:forEach var="n" begin="0" end="${pBean.getTotalPage() }">
        <!--非当前页面  -->
        <c:if test="${pBean.currPage != n }">
                <li>
                    <a href="${pageContext.request.contextPath}/showCurrPage?currPage=${n}">[${n+1 }]</a>
                </li>
        </c:if>
        
        <!--当前页面不可单击  -->
        <c:if test="${pBean.currPage == n}">
            <li>
                <a style="text-decoration: none; color: black;" href="javascript:void(0)">
                    [${n+1 }]
                </a>
            </li>
        </c:if>
        </c:forEach>

    </ul>
    <!-- 上一页 下一页 -->
    <ul>
        <c:if test="${pBean.currPage != 0}">
            <li><a href="${pageContext.request.contextPath}/showCurrPage?currPage=0">[首页]</a>
            </li>

            <li><a href="${pageContext.request.contextPath}/showCurrPage?currPage=${pBean.currPage-1}">[上一页]</a>
            </li>
        </c:if>

        <c:if test="${pBean.currPage != pBean.getTotalPage() }">
            <li><a href="${pageContext.request.contextPath}/showCurrPage?currPage=${pBean.currPage+1}">[下一页]</a>
            </li>

            <li><a href="${pageContext.request.contextPath}/showCurrPage?currPage=${pBean.getTotalPage()}">[尾页]</a>
            </li>
        </c:if>
    </ul>
    <br />
    <h5>
        <center style="float: none;">
            第${pBean.currPage+1}页/共${pBean.getTotalPage()+1}页</center>
    </h5>

</body>

</html>