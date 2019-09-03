<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
</head>
<body>
    header: &nbsp; &nbsp; &nbsp; &nbsp; ${header }
    <BR>
    <BR>
    <HR>
    headerValues：&nbsp; &nbsp; &nbsp; &nbsp;${headerValues      };
    <BR>
    <BR>
    <HR>

    <BR>
    <BR>
    referer:${header.referer }<BR>
    user-agent:${headerValues["user-agent"][0] }
    
    <HR>
    <BR>
    <BR>
    
    
    
    
    
    
</body>
</html>
