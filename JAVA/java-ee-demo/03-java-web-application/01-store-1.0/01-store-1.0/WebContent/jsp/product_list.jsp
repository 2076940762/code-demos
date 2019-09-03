<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员登录</title>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.cookie.js" type="application/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>


    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
            width: 100%;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }
    </style>
</head>

<body>
    <div class="container-fluid">

        <%-- 	静态包含<%@ include file="page.jsp"%>
        动态包含
        <jsp:include page="page.jsp" /> --%>
        <jsp:include page="/jsp/head.jsp" />


        <c:forEach items="${pageBean.list }" var="p">
            <div class="col-md-2">
                <a href="${pageContext.request.contextPath }/product?method=geProductById&pid=${p.pid}">
                    <img src="${pageContext.request.contextPath}/${p.pimage}" width="170" height="170"
                        style="display: inline-block;">
                </a>
                <p><a href="${pageContext.request.contextPath }/product?method=geProductById&pid=${p.pid}"
                        style='color:green'>
                        ${fn:substring( p.pname ,0,10)}...
                    </a></p>
                <p>
                    <font color="#FF0000">商城价：&yen;${p.shop_price }</font>
                </p>
            </div>
        </c:forEach>

    </div>

    <!--分页 -->
    <div style="width:380px;margin:0 auto;margin-top:50px;">
        <ul class="pagination" style="text-align:center; margin-top:10px;">

            <!-- 上一页 begin-->

            <!--  第一页-->
            <c:if test="${pageBean.currPage==1 }">
                <li class="disabled">
                    <a href="${pageContext.request.contextPath }/product?method=findByPageid&cid=${param.cid}&currPage=${pageBean.currPage-1 }"
                        aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span></a></li>
            </c:if>

            <!-- 非第一页 -->
            <c:if test="${pageBean.currPage !=1 }">
                <li>
                    <a href="${pageContext.request.contextPath }/product?method=findByPageid&cid=${param.cid}&currPage=${pageBean.currPage-1 }"
                        aria-label="Previous"><span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <!-- 上一页 end-->

            <!--  一共展示10个页签 ，当前页前五，后四-->

            <!--页码begion  -->
            <c:forEach begin="${pageBean.currPage-5>0 ? pageBean.currPage-5:1 }"
                end="${pageBean.currPage+4 > pageBean.totalPage ?  pageBean.totalPage:pageBean.currPage+4  }" var="n">
                <!--  当前页-->
                <c:if test="${pageBean.currPage==n }">
                    <li class="active">
                        <a
                            href="${pageContext.request.contextPath }/product?method=findByPageid&cid=${param.cid}&currPage=${n}">${n
                            }</a>
                    </li>
                </c:if>

                <!-- 非当前页 -->
                <c:if test="${pageBean.currPage!=n }">
                    <li>
                        <a
                            href="${pageContext.request.contextPath }/product?method=findByPageid&cid=${param.cid}&currPage=${n }">${n
                            }
                        </a>
                    </li>
                </c:if>
            </c:forEach>
            <!--页码end  -->


            <!--  下一页 begin-->
            <!-- 最后一页 -->
            <c:if test="${ pageBean.currPage == pageBean.totalPage}">
                <li class="disabled">
                    <a href="${pageContext.request.contextPath }/product?method=findByPageid&cid=${param.cid}&currPage=${pageBean.currPage+1 }"
                        aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>

            <!--  不是最后一页-->
            <c:if test="${ pageBean.currPage != pageBean.totalPage}">
                <li>
                    <a href="${pageContext.request.contextPath }/product?method=findByPageid&cid=${param.cid}&currPage=${pageBean.currPage+1 }"
                        aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <!--  下一页 end-->

        </ul>
    </div>
    <!-- 分页结束=======================        -->

    <!--
       		商品浏览记录:
        -->
    <div
        style="width:1210px;margin:0 auto; padding: 0 9px;border: 1px solid #ddd;border-top: 2px solid #999;height: 246px;">

        <h4 style="width: 50%;float: left;font: 14px/30px " 微软雅黑 ";">浏览记录</h4>
        <div style="width: 50%;float: right;text-align: right;"><a href="">more</a></div>

        <div style="clear: both;"></div>

        <div style="overflow: hidden;">

            <ul style="list-style: none;" id="id-history">
                <li
                    style="width: 150px;height: 216;float: left;margin: 0 8px 0 0;padding: 0 18px 15px;text-align: center;">
                    <img src="${pageContext.request.contextPath}/products/1/cs10001.jpg" width="130px" height="130px" />
                </li>
            </ul>

        </div>
    </div>
    <div style="margin-top:50px;">
        <img src="${pageContext.request.contextPath}/image/footer.jpg" width="100%" height="78" alt="我们的优势"
            title="我们的优势" />
    </div>

    <div style="text-align: center;margin-top: 5px;">
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
    <div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
        Copyright &copy; 2005-2016 传智商城 版权所有
    </div>

</body>
<script type="application/javascript">
    $(function () {
        // alert(document.cookie);
        var history = $.cookie("ids");
        var arr = history.split("-");

        var $h = $("#id-history");
        for (var i = 0; i < arr.length; i++) {
            var url =
                "http://localhost:8080${pageContext.request.contextPath}/product?method=geProductById4ajax&pid=" +
                arr[i];
            //alert(url); http://localhost:8080//01-store-1.0+product?method=geProductById&pid=2

            // 发送ajax请求
            $.get(url, function (d) {
                //alert(d);

            });

        }

    });
</script>

</html>