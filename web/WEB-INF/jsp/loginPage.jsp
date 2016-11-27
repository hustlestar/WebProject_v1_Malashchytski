<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<c:if test="${requestScope.get('errorMessage')!=null}">
    <c:out value="${requestScope.get('errorMessage')}"/>
    <c:remove var="errorMessage" scope="request"/>
</c:if>
<form method="post" action="Controller">
    <input type="hidden" name="command" value="login"/>
    Login:<input type="text" name="nickname"/><br/>
    Password:<input type="text" name="pass"/><br/>
    <input type="submit" value="login"/>
</form>
</body>
</html>
