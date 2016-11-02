<%--
  Created by IntelliJ IDEA.
  User: Hustler
  Date: 28.10.2016
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register page</title>
</head>
<body>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="register"/>
    Nickname:<input type="text" name="nickname"/><br/>
    Email ID:<input type="text" name="email"/><br/>
    Password:<input type="password" name="pass"/>
    <p><input type="radio" name="sex" value="m" checked/>Мужской
    <input type="radio" name="sex" value="f"/>Женский
    <input type="radio" name="sex" value="n"/>Иной</p>
    <input type="submit" value="register"/>
</form>

</body>
</html>
