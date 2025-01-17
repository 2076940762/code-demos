<%@page import="domain.OrderPageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>


<!doctype html>
<html>

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>会员登录</title>
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
	</style>
    </head>

    <body>

        <!-- 动态包含 -->
        <jsp:include page="/jsp/head.jsp"></jsp:include>

        <div class="container">
            <div class="row">

                <div style="margin: 0 auto; margin-top: 10px; width: 950px;">
                    <strong>我的订单</strong>
                    <table class="table table-bordered">

                        <c:forEach items="${pageBean.orders }" var="order">

                            <tbody>
                                <tr class="success">
                                    <td colspan="2">订单编号:${order.oid }</td>

                                    <td colspan="3">
                                        <span style="margin: auto;padding-left: 2px;">订单总金额：${order.total}</span>

                                        <c:if test="${order.state == 0 }">
                                            <span style="margin: auto;">
                                                订单状态：待支付
                                                <a href="${pageContext.request.contextPath }/order?method=getOrderDetailsById&oid=${order.oid }">支付</a>
                                            </span>
                                        </c:if>
                                        <c:if test="${order.state == 2 }">
                                            <span style="margin: auto;">订单状态：已支付待发货</span>
                                        </c:if>
                                        <c:if test="${order.state == 3 }">
                                            <span style="margin: auto;">订单状态：已发货</span>
                                        </c:if>
                                        <c:if test="${order.state == 2 }">
                                            <span style="margin: auto;">订单状态：已确认收货</span>
                                        </c:if>
                                    </td>

                                </tr>
                                <tr class="warning">
                                    <th>图片</th>
                                    <th>商品</th>
                                    <th>价格</th>
                                    <th>数量</th>
                                    <th>小计</th>
                                </tr>

                                <c:forEach items="${order.items }" var="item">

                                    <tr class="active">
                                        <td width="60" width="40%"><input type="hidden" name="id" value="22">
                                            <img src="${pageContext.request.contextPath}/${item.product.pimage}" width="70"
                                                height="60">
                                        </td>
                                        <td width="30%">
                                            <a target="_blank"> ${item.product.pname}</a>
                                        </td>
                                        <td width="20%">￥${item.product.shop_price}</td>
                                        <td width="10%">${item.count }</td>
                                        <td width="15%">
                                            <span class="subtotal">￥ ${item.subTotal }</span>
                                        </td>
                                    </tr>

                                </c:forEach>

                            </tbody>

                        </c:forEach>


                    </table>
                </div>
            </div>
            <div style="text-align: center;">
                <ul class="pagination">

                    <!--上一页  -->
                    <!--如果当前页是第一页  -->
                    <c:if test="${pageBean.currPage==1 }">
                        <li class="disabled">
                            <a href="javascript:void(0)" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span></a>
                        </li>
                    </c:if>

                    <!--如果不是第一页  -->
                    <c:if test="${pageBean.currPage !=1 }">
                        <li>
                            <a href="${pageContext.request.contextPath }/order?method=findAllByPage&currPage=${pageBean.currPage-1}"
                                aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span></a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${pageBean.totalPages }" var="n">
                        <!--在当前页  -->
                        <c:if test="${n == pageBean.currPage}">
                            <li class="active">
                                <a href="javascript:void(0)">${n }</a>
                            </li>
                        </c:if>

                        <!-- 不在当前页 -->
                        <c:if test="${n != pageBean.currPage }">
                            <li>
                                <a href="${pageContext.request.contextPath }/order?method=findAllByPage&currPage=${n}">${n
                                    }</a>
                            </li>
                        </c:if>
                    </c:forEach>

                    <!--  下一页-->

                    <!-- 如果不是最后一页-->
                    <c:if test="${ pageBean.currPage  !=  pageBean.totalPages}">
                        <li>
                            <a href="${pageContext.request.contextPath }/order?method=findAllByPage&currPage=	${pageBean.currPage+1 }"
                                aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <!--如果是最后一页  -->
                    <c:if test="${ pageBean.currPage  ==  pageBean.totalPages}">
                        <li class="disabled">
                            <a href="javascript:void(0)" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>

                </ul>

            </div>

        </div>

        <div style="margin-top: 50px;">
            <img src="${pageContext.request.contextPath}/image/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
        </div>

        <div style="text-align: center; margin-top: 5px;">
            <ul class="list-inline">
                <li><a>关于我们</a></li>
                <li><a>联系我们</a></li>
                <li><a>招贤纳士</a></li>
                <li><a>法律声明</a></li>
                <li><a>友情链接</a></li>
                <li><a target="_blank">支付方式</a></li>
                <li><a target="_blank">配送方式</a></li>
                <li><a>服务声明</a></li>
                <li><a>广告声明</a></li>
            </ul>
        </div>
        <div style="text-align: center; margin-top: 5px; margin-bottom: 20px;">
            Copyright &copy; 2005-2016 传智商城 版权所有</div>
    </body>

</html>
