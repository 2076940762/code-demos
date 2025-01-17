<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    <!--  动态包含-->
    <jsp:include page="/jsp/head.jsp"></jsp:include>


    <div class="container">
        <div class="row">
            <div
                style="border: 1px solid #e4e4e4;width:930px;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;">
                <a href="./index.htm">首页&nbsp;&nbsp;&gt;</a>
                <a href="./蔬菜分类.htm">蔬菜&nbsp;&nbsp;&gt;</a>
                <a>无公害蔬菜</a>
            </div>

            <div style="margin:0 auto;width:950px;">
                <div class="col-md-6">
                    <img style="opacity: 1;width:400px;height:350px;" title="" class="medium"
                        src="${pageContext.request.contextPath}/${product.pimage}">
                </div>


                <form id="id-form" action="http://localhost:8080${pageContext.request.contextPath }/cart?method=add"
                    method="post">
                    <input type="hidden" name="pid" value="${product.pid}" />
                    <div class="col-md-6">
                        <div><strong>${product.pname}"</strong></div>
                        <div style="border-bottom: 1px dotted #dddddd;width:350px;margin:10px 0 10px 0;">
                            <div>编号：${product.pid}"</div>
                        </div>

                        <div style="margin:10px 0 10px 0;">亿家价: <strong style="color:#ef0101;">￥：${
                                product.shop_price}元/份</strong> 参 考 价： <del>￥${ product.market_price}元/份</del>
                        </div>

                        <div style="margin:10px 0 10px 0;">促销: <a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)"
                                style="background-color: #f07373;">限时抢购</a> </div>

                        <div
                            style="padding:10px;border:1px solid #e7dbb1;width:330px;margin:15px 0 10px 0;;background-color: #fffee6;">
                            <div style="margin:5px 0 10px 0;">白色</div>

                            <div style="border-bottom: 1px solid #faeac7;margin-top:20px;padding-left: 10px;">购买数量:
                                <input id="quantity" name="quantity" value="1" maxlength="4" size="10" type="text" />
                            </div>


                            <div style="margin:20px 0 10px 0;;text-align: center;">
                                <a href="javascript:void(0)">
                                    <input
                                        style="background: url('${pageContext.request.contextPath}/images/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;"
                                        value="加入购物车" type="button" onclick="addCart()">
                                </a> &nbsp;收藏商品</div>
                        </div>
                    </div>
            </div>
            </form>



            <div class="clear"></div>
            <div style="width:950px;margin:0 auto;">
                <div style="background-color:#d3d3d3;width:930px;padding:10px 10px;margin:10px 0 10px 0;">
                    <strong>${ product.pdesc}</strong>
                </div>

                <div>
                    <img src="${pageContext.request.contextPath}/${ product.pimage}">
                </div>

                <div style="background-color:#d3d3d3;width:930px;padding:10px 10px;margin:10px 0 10px 0;">
                    <strong>商品参数</strong>
                </div>
                <div style="margin-top:10px;width:900px;">
                    <table class="table table-bordered">
                        <tbody>
                            <tr class="active">
                                <th colspan="2">基本参数</th>
                            </tr>
                            <tr>
                                <th width="10%">级别</th>
                                <td width="30%">标准</td>
                            </tr>
                            <tr>
                                <th width="10%">标重</th>
                                <td>500</td>
                            </tr>
                            <tr>
                                <th width="10%">浮动</th>
                                <td>200</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div style="background-color:#d3d3d3;width:900px;">
                    <table class="table table-bordered">
                        <tbody>
                            <tr class="active">
                                <th><strong>商品评论</strong></th>
                            </tr>
                            <tr class="warning">
                                <th>暂无商品评论信息 <a>[发表商品评论]</a></th>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div style="background-color:#d3d3d3;width:900px;">
                    <table class="table table-bordered">
                        <tbody>
                            <tr class="active">
                                <th><strong>商品咨询</strong></th>
                            </tr>
                            <tr class="warning">
                                <th>暂无商品咨询信息 <a>[发表商品咨询]</a></th>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

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
    function addCart() {
        document.getElementById("id-form").submit();
    }
</script>

</html>