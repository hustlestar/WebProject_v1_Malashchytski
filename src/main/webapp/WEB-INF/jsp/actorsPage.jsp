<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.allActorsList" var="allActorsList"/>
<fmt:message bundle="${locale}" key="locale.englishName" var="englishName"/>
<fmt:message bundle="${locale}" key="locale.russianName" var="russianName"/>
<!DOCTYPE html>
<html>
<head>
    <title>${allActorsList}</title>
    <link rel="shortcut icon" href="images/main/favicon_16x16.png">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body onload="active()">

<c:import url="template/navbar.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("movies-page").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/sideleft.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <h1>${allActorsList}</h1>
            <br>
            <table class="table">
                <thead>
                <tr>
                    <th>id</th>
                    <th>${englishName}</th>
                    <th>${russianName}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="actor" items="${requestScope.all_actors}">
                    <tr>
                        <td><a href="Controller?command=view-actor&actor-id=${actor.id}">
                            <c:out value="${actor.id}"/></a></td>

                        <td><c:out value="${actor.nameRu}"/></td>
                        <td><c:out value="${actor.nameEn}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <c:import url="template/sideright.jsp"/>

    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
