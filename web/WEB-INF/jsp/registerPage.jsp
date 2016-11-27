<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register page</title>
</head>
<body>
<c:if test="${requestScope.get('errorMessage')!=null}">
    <c:out value="${requestScope.get('errorMessage')}"/>
    <c:remove var="errorMessage" scope="request"/>
</c:if>
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
