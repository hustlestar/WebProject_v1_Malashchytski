<%--
  Created by IntelliJ IDEA.
  User: Hustler
  Date: 28.10.2016
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<form method="post" action="Controller">
    <input type="hidden" name="command" value="login"/>
    Login:<input type="text" name="nickname"/><br/>
    Password:<input type="text" name="pass"/><br/>
    <input type="submit" value="login"/>
</form>
</body>
</html>
