<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users page</title>
</head>
<body>
<table border="1">
    <c:forEach var="user" items="${requestScope.all_users}">
        <tr>
            <td><a href="Controller?command=view-user&nickname=${user.nickname}"><c:out value="${user.nickname}"/></a></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.type}"/></td>
            <td><c:out value="${user.sex}"/></td>
            <td><c:out value="${user.registred}"/></td>
            <td><a href="Controller?command=ban-user&userNickname=${user.nickname}">Ban</a></td>
            <td><a href="Controller?command=unban-user&userNickname=${user.nickname}">Unban</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
