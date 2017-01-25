<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="actor" class="by.hustlestar.bean.entity.Actor" scope="request"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.involvedIn" var="involvedIn"/>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title><c:out value="${actor.nameEn}"/></title>
    <link rel="shortcut icon" href="images/main/favicon_16x16.png">
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
            <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">
                <h1><c:out value="${actor.nameRu}"/></h1>
                <h3><c:out value="${actor.nameEn}"/></h3>
            </c:if>
            <c:if test="${sessionScope.get('language') eq 'en'}">
                <h1><c:out value="${actor.nameEn}"/></h1>
            </c:if>
            <div class="col-sm-3">
                <img src="${actor.image}" alt="picture for actor" width="100%"/>
                <c:if test="${sessionScope.get('user').type eq 'admin' || sessionScope.get('user').type eq 'moder'}">
                    <c:if test="${not empty param.errorMessage}">
                        <h4 class="red"><c:out value="${param.errorMessage}"/></h4>
                    </c:if>
                    <form action="Controller" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="upload-photo-for-actor"/>
                        <input type="hidden" value="${actor.id}" name="filename">
                        <input name="data" type="file"><br>
                        <button class="btn-sm btn-info" type="submit">Confirm</button>
                        <br>
                    </form>
                </c:if>
            </div>
            <div class="col-sm-9 text-left">
                <p>What is Lorem Ipsum?
                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
                    industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type
                    and scrambled it to make a type specimen book. It has survived not only five centuries, but also the
                    leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s
                    with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop
                    publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
            </div>
            <div class="col-sm-12">
                <h3>${involvedIn}</h3>
                <table class="table">
                    <c:forEach var="movie" items="${requestScope.get('actor').movies}">
                        <tr>
                            <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">
                                <td><a href="Controller?command=movie-by-id&id=${movie.id}"><c:out
                                        value="${movie.titleRu}"/></a></td>
                            </c:if>
                            <c:if test="${sessionScope.get('language') eq 'en'}">
                                <td><a href="Controller?command=movie-by-id&id=${movie.id}"><c:out
                                        value="${movie.titleEn}"/></a></td>
                            </c:if>
                            <td>
                                <c:forEach var="rating" items="${movie.ratings}">
                                    <c:if test="${rating.userNickname eq sessionScope.get('user').nickname}">
                                        <c:out value="${rating.ratingScore}"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td><c:out value="${movie.avgRating}"/>
                                <small>(<c:out value="${movie.ratingVotes}"/>)</small>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <c:import url="template/sideright.jsp"/>
    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
