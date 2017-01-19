<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.usersPage" var="usersPage"/>
<fmt:message bundle="${locale}" key="locale.ban" var="ban"/>
<fmt:message bundle="${locale}" key="locale.unban" var="unban"/>
<!DOCTYPE html>
<html>
<head>
    <title>${usersPage}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="images/main/favicon_16x16.png">

</head>
<body>
<c:import url="template/navbar.jsp"/>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/sideleft.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <br>
            <table class="table">
                <c:forEach var="user" items="${requestScope.all_users}">
                    <tr>
                        <td><a href="Controller?command=view-user&nickname=${user.nickname}"><c:out value="${user.nickname}"/></a></td>
                        <td>${user.email}</td>
                        <td>${user.type}</td>
                        <td>${user.sex}</td>
                        <td>${user.registred}</td>
                        <td>${user.reputation}</td>
                        <c:if test="${sessionScope.get('user').type eq 'admin' || sessionScope.get('user').type eq 'moder'}">
                        <td><a href="Controller?command=ban-user&userNickname=${user.nickname}">${ban}</a></td>
                        <td><a href="Controller?command=unban-user&userNickname=${user.nickname}">${unban}</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <c:import url="template/sideright.jsp"/>

    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
