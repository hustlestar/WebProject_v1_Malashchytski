<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="by.hustlestar.bean.entity.User" scope="request"/>
<html>
<head>
    <title><c:out value="${user.nickname}"/> profile</title>
</head>
<body>
<c:out value="${user.nickname}"/><br/>
<c:out value="${user.email}"/><br/>
<c:out value="${user.registred}"/><br/>
<c:out value="${user.sex}"/>
</body>
</html>
