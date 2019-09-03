<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>

    <body>
        <input type="button" value="ajax get" onclick="sendMsgByGet()" />
    </body>
    
    <script type="text/javascript">
        function sendMsgByGet() {
            //获取ajax核心对象
            xmlhttp = null;
            if (window.XMLHttpRequest) { // code for Firefox, Opera, IE7, etc.
                xmlhttp = new XMLHttpRequest();
            } else if (window.ActiveXObject) { // code for IE6, IE5
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }

            //回调函数
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    alert(xmlhttp.responseText);
                }
            }

            //open
            /* xmlhttp.open("GET", "http://localhost:8080/${pageContext.request.contextPath}/getmsg?username=张三"); */
            xmlhttp.open("GET", "http://localhost:8080/15-ajax-jquery/getmsg?username=张三"); 
            
            xmlhttp.send();
        }
    </script>
</html>
