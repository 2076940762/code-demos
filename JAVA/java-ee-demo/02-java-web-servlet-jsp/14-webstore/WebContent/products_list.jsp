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
        </style>

    </head>
    <body>

        <!-- <form id="form-id" action="${pageContext.request.contextPath}/deleteSelected" method="post"> -->
        <table style="border: chartreuse solid 5px;">
            <tr>
                <td colspan="6">
                    <!-- 搜索框 -->
                    <form action="${pageContext.request.contextPath}/searchProduct" id="searchform" method="get">
                        <span> 商品名</span> <input type="text" name="name" />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span> 关键字</span> <input type="text" name="kw" />
                        <!-- <input type="button" value="搜索" onclick="startSearchProduct" /> -->
                        <input type="submit" value="搜索" />
                    </form>

                </td>
                <td> <input type="button" value="删除选中项" onclick="submitThisForm()" /> </td>
            </tr>
            <form id="form-id" action="${pageContext.request.contextPath}/deleteSelected" method="post">
                <tr style="border: chartreuse solid 5px;">
                    <td> <input type="checkbox" onclick="selectAll(this)" />全选 </td>
                    <td>商品名称</td>
                    <td>商品图片</td>
                    <td>市场价</td>

                    <td>商城价</td>
                    <td>商品描述</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${list}" var="p">
                    <tr>
                        <td> <input type="checkbox" name="pid" value="${p.pid}" /> </td>
                        <td width="5%">${p.pname }</td>
                        <td width="10%"><img src="${p.pimage}" width="38px" />
                        </td>
                        <td width="5%">${p.market_price}</td>

                        <td width="5%">${p.shop_price}</td>
                        <td>${p.pdesc}</td>
                        <td width="7%">
                            <a href="${pageContext.request.contextPath}/getProductById?pid=${p.pid}"> 修改 </a>
                            |
                            <a href="javascript:void(0)" onclick="deleteProduct('${p.pid}')"> 删除 </a>
                        </td>
                    </tr>
                </c:forEach>
            </form>
        </table>

    </body>
    <script>
        //删除
        function deleteProduct(obj) {
            if (confirm("真的要删除此商品吗？")) {
                location.href = "${pageContext.request.contextPath}/deleteProductById?pid=" + obj;
            }
        }

        //全选
        function selectAll(obj) {
            var arr = document.getElementsByName("pid");
            for (var i = 0; i < arr.length; i++) {
                arr[i].checked = obj.checked;
            }
        }

        //提交表单
        function submitThisForm() {
            var f = document.getElementById("form-id");
            f.submit();
        }

        // //搜索
        // function startSearchProduct() {
        //     var f = document.getElementById("searchform");
        //     f.submit();
        // }
    </script>
</html>
