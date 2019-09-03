<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" autoFlush="true" session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
        <%
		out.println("hello jsp");
	%>
        <br>
        <%=out%><br>
        <%=a%><br>
        <%!int a = 0;%><br>
        <hr />

        <h1>page指令: </h1> <br />
        <h2>重要属性:3个 </h2> <br />
        <font size="3">
            <p>
                &nbsp; &nbsp; &nbsp; &nbsp; contentType:设置响应流的编码,及通知浏览器用什么编码打开.设置文件的mimetype <br />
                &nbsp; &nbsp; &nbsp; &nbsp; pageEncoding:设置页面的编码 <br />
                &nbsp; &nbsp; &nbsp; &nbsp; import:导入所需要的包 <br />
                &nbsp; &nbsp; &nbsp; &nbsp; contentType和pageEncoding联系: <br />
                &nbsp; &nbsp; &nbsp; &nbsp; 若两者都出现的时候,各自使用各自的编码 <br />
                &nbsp; &nbsp; &nbsp; &nbsp; 若只出现一者,两个都使用出现的这个编码 <br />
                &nbsp; &nbsp; &nbsp; &nbsp; 若两者都不出现,使用服务器默认的编码 tomcat7使用的iso-8859-1 <br />
            </p>
        </font>
        <h2>解属性:</h2> <br />
        <p>
            &nbsp; &nbsp; &nbsp; &nbsp; language:当前jsp页面里面可以嵌套的语言 <br />
            &nbsp; &nbsp; &nbsp; &nbsp; buffer:设置jsp页面的流的缓冲区的大小 <br />
            &nbsp; &nbsp; &nbsp; &nbsp; autoFlush:是否自动刷新 <br />
            &nbsp; &nbsp; &nbsp; &nbsp; extends:声明当前jsp的页面继承于那个类.必须继承的是httpservlet 及其子类 <br />
            &nbsp; &nbsp; &nbsp; &nbsp; session:设置jsp页面是否可以使用session内置对象 <br />
            &nbsp; &nbsp; &nbsp; &nbsp; isELIgnored:是否忽略el表达式 <br />
            &nbsp; &nbsp; &nbsp; &nbsp; errorPage:当前jsp页面出现异常的时候要跳转到的jsp页面 <br />
            &nbsp; &nbsp; &nbsp; &nbsp; isErrorPage:当前jsp页面是否是一个错误页面 <br />
            &nbsp; &nbsp; &nbsp; &nbsp; 若值为true,可以使用jsp页面的一个内置对象 exception <br />
        </p>
    </body>
</html>
