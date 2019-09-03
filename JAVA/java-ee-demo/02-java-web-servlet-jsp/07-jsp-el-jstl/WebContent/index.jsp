<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <a href="/07-jsp-el-jstl/JSP/hello_jsp.jsp">hello jsp</a>
    <br>
    <a href="/07-jsp-el-jstl/JSP/page.jsp">page</a>
    <br>
    <a href="/07-jsp-el-jstl/JSP/result.jsp">include</a>
    <br>
    <a href="/07-jsp-el-jstl/JSP/page-context.jsp">pagecontext</a>
    <br>

    <H3>jsp动作标签</H3>
    <a href="/07-jsp-el-jstl/JSP/ACTION/forward.jsp">forward</a>
    <br>
    <a href="/07-jsp-el-jstl/JSP/ACTION/include.jsp">动态包含</a>
    <br>


    <H3>el</H3>
    <a href="/07-jsp-el-jstl/JSP/el/demo01.jsp">el方式获取简单数据</a>
    <br>

    <a href="/07-jsp-el-jstl/JSP/el/getComplexData.jsp">el方式获取复杂数据</a>
    <br>

    <a href="/07-jsp-el-jstl/JSP/el/javaBean.jsp">el方式获取javaBean</a>
    <br>

    <a href="/07-jsp-el-jstl/JSP/el/calculation.jsp">运算</a>
    <br>

    <HR>
    <H3>el内置对象</H3>

    <a
        href="/07-jsp-el-jstl/JSP/el/elInnerOBj.jsp?username=tom&hobby=eat&hobby=drink">el内置对象param</a>
    <br>
    <a href="/07-jsp-el-jstl/JSP/el/header.jsp">el内置对象 header</a>
    <br>

    <a href="/07-jsp-el-jstl/JSP/el/cookie.jsp">el内置对象 cookie</a>
    <br>

    <HR>
    <BR>
    <BR>

    <H3>jstl</H3>
    <A href="${pageContext.request.contextPath   }/JSP/jstl/if.jsp">jstl
        core if</A>
    <BR>

    <A href="${pageContext.request.contextPath   }/JSP/jstl/for1.jsp">jstl
        core for基础用法</A>
    <BR>
    
    <A href="${pageContext.request.contextPath   }/JSP/jstl/collection-for.jsp">jstl
        core for 遍历集合</A>
    <BR>
    
    
    <A href="${pageContext.request.contextPath   }/JSP/jstl/choose.jsp">jstl
        core choose </A>
    <BR>

    <BR>
    <BR>
    <BR>
    <BR>
    <BR>

</body>
</html>









