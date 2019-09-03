<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>转账</title>

        <style type="text/css">
            #container {

                margin-top: 80px;
                margin-left: auto;
                margin-right: auto;
                width: 55%;
                height: 420px;
                
                border-width: 7px;
                border-color: tomato;
                border-style: outset;

                outline-color: gold;
                outline-style: double;
                outline-width: 3px;

                background-color: wheat;
            }
            
            form{
                width: 100%;
                margin: auto;
                text-align: center;
            }
            
            table{
                border: aqua 1px solid;
                width: 88%;
                
                margin-top: 30px;
                margin-bottom: 38px;
                
                margin-left: auto;
                margin-right: auto;
                
                padding-top: 20px;
                padding-left: 15px;
                
            }

            tr {
                margin: auto;
                width: 33%;
                height: 80px;
                text-align: center;
                
                font-size: xx-large;
            }
            
            .left{
             width: 28%;   
             text-align: center;
             padding-left:17px;
             padding-right: 1px;
            }
            
            input[type="text"]{
                width: 75%;
                height: 32px;
                text-align: right;
            }
            
            input[type="submit"]{
                width: 32%;
                height: 32px;
                margin-top: 12px;
            }

            .c1 {
                background-color: lightcyan;
            }
        </style>

    </head>
    <body>

        <div id="container">
            <form action="http://localhost:8080${pageContext.request.contextPath}/account"  method="post">
			
                <table>
                    <tr>
                        <td class="left">转出方：</td>
                        <td colspan="2" class="right"><input type="text" name="fromusr" class="c1" /></td>
                    </tr>

                    <tr>
                        <td class="left" >转入方：</td>
                        <td colspan="2" class="right" ><input type="text" name="tousr" class="c1" /></td>
                    </tr>
                    
                     <tr>
                        <td class="left" >金额：</td>
                        <td colspan="2" class="right" ><input type="text" name="money" class="c1" /></td>
                    </tr>

                    <tr>
                        <td colspan="3" class="right" ><input type="submit" /></td>
                    </tr>

                </table>
            </form>
        </div>


    </body>
</html>
