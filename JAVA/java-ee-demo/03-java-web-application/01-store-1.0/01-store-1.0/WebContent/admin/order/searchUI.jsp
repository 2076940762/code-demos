<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>

    <HEAD>
        <meta http-equiv="Content-Language" content="zh-cn">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />

        <script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>

        <script src="../../js/jquery-3.4.1.min.js" type="application/javascript"></script>

    </HEAD>

    <body>
        <br>

        <!-- 查询条件 -->
        <form target="searchresult" id="Form1" name="Form1" action="${pageContext.request.contextPath}/adminOrder?method=findByConditions"
            method="post">
            <table border="1px" cellpadding="1px" style=" margin: auto;">
                <tr>
                    <td>
                        订单编号：<input type="text" name="oid" />
                    </td>
                    <td>
                        订单状态：
                        <select name='state'>
                            <option value="0" selected="selected"> 未支付</option>
                            <option value="1"> 已支付</option>
                        </select>
                    </td>
                    <td>
                        用户名：<input type="text" name="uname" />
                    </td>
                    <td>
                        收货人姓名：
                        <input type="text" name="name" />
                    </td>
                    <td>
                        收货人电话：
                        <input type="text" name="telephone" />
                    </td>
                </tr>

                <tr>
                    <td colspan="5">
                        <center>
                            <a target="searchresult" class="button" href="javascript:void(0)" onclick="startSearch()">查询</a>
                            <!-- <input type="submit" value="查询" /> -->
                        </center>
                    </td>
                </tr>

            </table>
        </form>

    </body>
    <script>
        function startSearch() {
            $("#Form1").submit();
        }
    </script>

</HTML>
