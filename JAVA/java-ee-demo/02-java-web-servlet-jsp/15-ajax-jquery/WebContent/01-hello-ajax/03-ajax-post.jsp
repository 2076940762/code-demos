<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
        <input type="button" onclick="sendMsgBypost()" value="post" />
    </body>
    <script type="text/javascript">
        function sendMsgBypost(){
            //获取ajax核心对象
            var xmlhttp = new XMLHttpRequest();
            
            if(xmlhttp == null){
                alert("xmlhttp is null");
                return;
            }
            
            //编写回调函数
            xmlhttp.onreadystatechange=function(){
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
                    alert(xmlhttp.responseText);
                    // alert("hello");
                }
                
            }
            
            //open
            xmlhttp.open("POST","http://localhost:8080/15-ajax-jquery/postmsg?username=李四");
            
            //设置content-type
            xmlhttp.setRequestHeader("content-type","application/x-www-form-urlencoded");
            
            //send
            xmlhttp.send("sex=男");
        }
    </script>
    
<!--       <form action="" method="" enctype="application/x-www-form-urlencoded">
            
        </form> -->
</html>
