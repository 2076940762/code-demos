<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>登录</title>

        <script type="text/javascript">

        </script>

        <style>
            #container {
           margin-top: 100px;
            margin-left: auto;
            margin-right: auto;
            border: lightpink solid 7px;
            outline-style: groove;
            outline-color: yellow;
            outline-width: 3px;
            /* text-align: center; */
            height: 400px;
            width: 50%;
            border-style: outset;
            background-color: lemonchiffon;
            padding: 25px;
        }

form>div {
	margin: 15px;
	height: 50px;
	padding: 5px;
	text-align: left;
	line-height: 1.75rem;
}

#submit {
	text-align: center;
}

#submit >input{
    width: 80px;
    height: 38px;
}

.left {
	font-family: "courier new";
	font-size: x-large;
	width: 17%;
	text-align: justify;
	display: inline-table;
	vertical-align: center;
}

.center {
	background-color: palegreen;
	width: 62%;
	height: 35PX;
}
</style>
    </head>
    <body>

        <div id="container" >
            <form action="http://localhost:8080/09-login-again/login"  method="post">
                
                <span>
                    <font color="crimson" style="margin: 1px; padding: 1px;text-align: center; font-size: medium; line-height: 12px;font-family: "courier new";">${msg}</font>
                </span>
                
                <div >
                    <span class="left">用户名：</span> 
                    <input type="text" name="username" class="center" value="${cookie.savedname.value}">
                    <span style=" text-align: right; font-family: " arial narrow" ; font-size: medium ; margin: 1px;
                        padding: 1px; line-height: 10px;">
                        <span>记住用户名：</span>
                        <input type="checkbox" name="savename" value="yes">
                    </span>
                </div>

                <div>
                    <span class="left">密码：</span> <input type="password" name="password" class="center" />
                </div>

                <div style="text-align: center;">
                    <img src="http://localhost:8080/09-login-again/checkcode"  alt="看不清单击换一张试试" onclick="changeImg(this)"/>
                </div>

                <div>
                    <span class="left">验证码：</span> <input type="text" name="checkcode" id="" class="center" />
                </div>

                <div id="submit">
                    <input type="submit" />
                </div>

            </form>

        </div>
    </body>
    <script type="text/javascript">
        function changeImg(obj){
            obj.src="http://localhost:8080/09-login-again/checkcode?i="+Math.random();
        }
        
    </script>
</html>
