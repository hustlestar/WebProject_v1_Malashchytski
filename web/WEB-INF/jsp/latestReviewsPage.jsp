<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.deleteReview" var="deleteReview"/>
<fmt:message bundle="${locale}" key="locale.banUser" var="banUser"/>
<fmt:message bundle="${locale}" key="locale.usefulReview" var="usefulReview"/>
<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap Example</title>
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
<div class="container-fluid text-center flex">
    <div class="row content">

        <c:import url="template/sideleft.jsp"/>

        <div class="col-sm-8 text-left">
            <h1>Latest news</h1>

            <br>
            <table>

                <hr>
                <c:forEach var="movie" items="${requestScope.latest_movies_reviews}">
                    <c:set var="review" value="${movie.reviews.get(0)}"/>
                    <div class="col-sm-2 text-center">
                        <img src="images/users/anon.jpg" class="img-circle" height="65" width="65" alt="Avatar">
                    </div>
                    <div class="col-sm-10">
                        <h4><a href="Controller?command=movie-by-id&id=${movie.id}">Review for ${movie.titleRu}</a></h4>
                        <h4><a href="Controller?command=view-user&nickname=${review.userNickname}">
                            <c:out value="${review.userNickname}"/></a>
                            <small>
                                <fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                                                value="${review.reviewDate}"/></small>
                        </h4>
                        <c:if test="${sessionScope.get('user').type eq 'admin' || sessionScope.get('user').type eq 'moder'}">
                            <p>
                                <a href="Controller?command=delete-review&movieID=${movie.id}&userNickname=${review.userNickname}">Удалить
                                    рецензию</a>
                                <a href="Controller?command=ban-user&userNickname=${review.userNickname}">Забанить
                                    пользователя</a></p>
                        </c:if>
                        <p><c:out value="${movie.reviews.get(0).review}"/></p>
                        <p>
                            <small>Полезная рецензия? <c:if test="${sessionScope.get('user') != null}">
                                <a href="Controller?command=like-review&movieID=${review.movieID}&reviewer=${review.userNickname}&score=up"><i
                                        class="green"><c:out value="${review.thumbsUp}"/></i></a> /
                                <a href="Controller?command=like-review&movieID=${review.movieID}&reviewer=${review.userNickname}&score=down"><i
                                        class="red"><c:out value="${review.thumbsDown}"/></i></a>
                                <br/></c:if>
                                <c:if test="${sessionScope.get('user') == null}">

                                    <i class="green"><c:out value="${review.thumbsUp}"/></i> /
                                    <i class="red"><c:out value="${review.thumbsDown}"/></i>
                                    <br/>
                                </c:if></small>
                        </p>
                        <br>
                    </div>

                </c:forEach>

            </table>
        </div>

        <c:import url="template/sideright.jsp"/>

    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
