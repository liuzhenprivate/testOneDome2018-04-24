<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%
String user = request.getParameter("username");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><%=user%>的个人信息</title>
</head>
<body>
<%
    out.println("现在时间是： "+new java.util.Date() +"<br />");
    out.println("用户名： "+user);
%>
</body>
</html>