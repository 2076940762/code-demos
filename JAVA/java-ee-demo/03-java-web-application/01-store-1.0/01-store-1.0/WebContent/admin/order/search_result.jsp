<%@page import="domain.OrderPageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }

        table {
            margin: 45px;
        }

        tr {
            border: orange 1px solid;
            text-align: center;
            vertical-align: middle;
            margin: auto;
            padding: auto;
        }
        
        td {
            border: orange 1px solid;
            text-align: center;
            vertical-align: middle;
            margin: auto;
            padding: auto;

        }

        th {
            border: orange 1px solid;
            text-align: center;
            vertical-align: middle;
            margin: auto;
            padding: auto;

        }
    </style>
</head>

<body>

    <div class="container">
        <div class="row">

            <div style="margin: 0 auto; margin-top: 10px; width: 98%;">

                <c:forEach items="${olist }" var="order">
                    <table class="table table-bordered" style="vertical-align: center;">
                        <tr class="success">
                            <td width="35%" style="margin: auto; padding: 2px;">
                                <span class="h4">
                                    订单编号:${order.oid }
                                </span>
                            </td>

                            <td width="15%" style="margin: auto; padding: 2px;">
                                <center>
                                    <span style="margin: auto;padding-left: 2px;">订单总金额：${order.total}</span>
                                </center>
                            </td>

                            <td width="20%" style="margin: auto; padding: 2px;">
                                <center>
                                    <c:if test="${order.state == 0 }">
                                        <span style="margin: auto;">
                                            订单状态：待支付
                                        </span>
                                    </c:if>
                                    <c:if test="${order.state == 2 }">
                                        <span style="margin: auto;">订单状态：已支付待发货</span>
                                    </c:if>
                                </center>
                            </td>

                            <td width="20%" style="margin: auto; padding: 2px;">
                                <center>
                                    <input type="button" value="查看详情" id="sub-${order.oid }" />
                                    <input type="button" value="ajax查看详情" onclick="getDetails('${order.oid}')" />
                                </center>
                            </td>

                            <td align="center" style="HEIGHT: 22px">
                                <a href="${ pageContext.request.contextPath }/adminProduct_edit.action?pid=">
                                    <img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0"
                                        style="CURSOR: hand">
                                </a>
                            </td>

                            <td align="center" style="HEIGHT: 22px">
                                <a href="${ pageContext.request.contextPath }/adminProduct_delete.action?pid=">
                                    <img src="${pageContext.request.contextPath}/images/i_del.gif" width="16"
                                        height="16" border="0" style="CURSOR: hand">
                                </a>
                            </td>
                        </tr>

                    </table>

                    <div id="order-detail" hidden="hidden" class="container-fluid" style="border: 1px solid aqua;">
                        <!-- ajax方式获取的订单详情 -->
                    </div>

                    <table id="div-${order.oid }" width="100%" style="border: 1px solid lightpink; ">

                        <tr class="warning">
                            <th width="20%">图片</th>
                            <th width="30%">商品</th>
                            <th width="10%">价格</th>

                            <th width="10%">数量</th>
                            <th width="10%">小计</th>
                            <th width="10%">编辑</th>
                            <th width="10%">删除</th>
                        </tr>

                        <!-- 订单项 -->
                        <c:forEach items="${order.items }" var="item">

                            <tr class="active" width="20%">
                                <td width="60" style="text-align: center; margin: auto; vertical-align: middle;">
                                    <input type="hidden" name="id" value="22">
                                    <img src="${pageContext.request.contextPath}/${item.product.pimage}" height="120px"
                                        width="80px">
                                </td>
                                <td width="30%">
                                    <a target="_blank"> ${item.product.pname}</a>
                                </td>
                                <td width="10%">￥${item.product.shop_price}</td>
                                <td width="10%">${item.count }</td>

                                <td width="10%">
                                    <span class="subtotal">￥ ${item.subTotal }</span>
                                </td>
                                <td width="10%" align="center" style="HEIGHT: 22px">
                                    <a href="${ pageContext.request.contextPath }/adminProduct_edit.action?pid=">
                                        <img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0"
                                            style="CURSOR: hand">
                                    </a>
                                </td>

                                <td width="10%" align="center" style="HEIGHT: 22px">
                                    <a href="${ pageContext.request.contextPath }/adminProduct_delete.action?pid=">
                                        <img src="${pageContext.request.contextPath}/images/i_del.gif" width="16"
                                            height="16" border="0" style="CURSOR: hand">
                                    </a>
                                </td>

                            </tr>

                        </c:forEach>
                    </table>

                    </tbody>
                    <script type="text/javascript">
                        $(function () {
                            $("#div-${order.oid }").hide();

                            $("#sub-${order.oid}").click(
                                function () {
                                    //显示、隐藏  来回切换
                                    $("#div-${order.oid }").toggle();
                                });
                        })
                    </script>

                </c:forEach>

                <!-- </table> -->
            </div>
        </div>

    </div>

</body>
<script type="application/javascript">
    function getDetails(oid) {
        // alert("通过ajax获取订单详情");
        $.get("${pageContext.request.contextPath}/adminOrder", {
            "method": "getDetailByoid",
            "oid": oid
        }, function (data) {
            // alert(data);

            //拼装table html
            var strtabel =
                "<table class='col-lg-10 col-md-10 col-sm-10' > <tr> <td width='45%'>图片</td>  <td width='25%'>商品名称</td>  <td width='10%'>单价</td>  <td width='10%' >数量</td>  <td width='10%' >小计</td>   </tr>";
            $(data).each(function () {
                strtabel +=
                    "<tr height='70px'> <td >" + "<img src='${pageContext.request.contextPath}/"+this.product.pimage +"'/>" + "</td>  <td>" + this.product.pname +
                    "</td>  <td>" + this.product.shop_price + "</td>  <td> " + this.count +
                    " </td>  <td> " + this.subTotal + " </td> </tr>";
            });
            strtabel += "</table>";

            $("#order-detail").html(strtabel);
            // $("#order-detail").show();
            $("#order-detail").toggle();

        }, "json");
    }
</script>

</html>