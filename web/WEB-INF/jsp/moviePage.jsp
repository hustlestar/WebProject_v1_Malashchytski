<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="movie" class="by.hustlestar.bean.entity.Movie" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="src/first.css">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title><c:out value="${movie.titleRu}"/></title>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">Home</a></li>
                <li class="active"><a href="#">Movies</a></li>
                <li><a href="#">Projects</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.get('user') == null}">
                    <li class="sign-up">
                        <a href="Controller?command=register">
                            <span class="glyphicon glyphicon-user"></span>
                            Sign Up</a>
                    </li>
                    <li><a href="Controller?command=login"><span class="glyphicon glyphicon-log-in"></span>
                        Login</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.get('user') != null}">
                    <li class="sign-up">
                        <a href="Controller?command=my-profile">
                            <span class="glyphicon glyphicon-user"></span> ${sessionScope.get('user').nickname}</a>
                    </li>
                    <li><a href="Controller?command=log-out">
                        <span class="glyphicon glyphicon-log-out"></span> Logout</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
            <p><a href="#">Link</a></p>
            <p><a href="#">Link</a></p>
            <p><a href="#">Link</a></p>
        </div>
        <div class="col-sm-8 text-left">
            <h1>Welcome</h1>

            Название фильма <c:out value="${movie.titleRu}"/><br/>
            Оригинальное название <c:out value="${movie.titleEn}"/><br/>
            Год <c:out value="${movie.year}"/><br/>
            <c:if test="${movie.countries.size()>0}">
                Страна <c:forEach var="country" items="${requestScope.movie.countries}">
                <a href="Controller?command=movies-by-country&country=${country.nameEn}"><c:out
                        value="${country.nameRu}"/></a>
            </c:forEach>
                <br/>
            </c:if>
            <c:if test="${movie.genres.size()>0}">
                Жанры <c:forEach var="genre" items="${requestScope.movie.genres}">
                <a href="Controller?command=movies-by-genre&genre=${genre.nameEn}"><c:out
                        value="${genre.nameRu}"/></a>
            </c:forEach>
                <br/>
            </c:if>
            Бюджет <c:out value="${movie.budget}"/><br/>
            Сборы в мире <c:out value="${movie.gross}"/><br/>
            <hr>
            <c:if test="${movie.ratingVotes>0}">
                Рейтинг <c:out value="${movie.avgRating}"/><br/>
                Количество голосов <c:out value="${movie.ratingVotes}"/><br/>
            </c:if>

            <c:if test="${sessionScope.get('user') != null}">
                <!-- rating -->
                <div class="container">
                    <!--<fieldset class="rating">
                        <legend>Please rate:</legend>
                        <input type="radio" id="star10" nameRu="rating" value="5" /><label for="star10" titleRu="Rocks! 10">10 stars</label>
                        <a href="Controller?command=add-rating&movieID=${movie.id}&rating=9"><input type="radio" id="star9" nameRu="rating" value="5" /><label for="star9" titleRu="Great! 9">9 stars</label></a>
                        <a href="Controller?command=add-rating&movieID=${movie.id}&rating=8"><input type="radio" id="star8" nameRu="rating" value="5" /><label for="star8" titleRu="Nice! 8">8 stars</label></a>
                        <a href="Controller?command=add-rating&movieID=${movie.id}&rating=7"><input type="radio" id="star7" nameRu="rating" value="5" /><label for="star7" titleRu="Good! 7">7 stars</label></a>
                        <a href="Controller?command=add-rating&movieID=${movie.id}&rating=6"><input type="radio" id="star6" nameRu="rating" value="5" /><label for="star6" titleRu="Average! 6">6 stars</label></a>
                        <a href="Controller?command=add-rating&movieID=${movie.id}&rating=5"><input type="radio" id="star5" nameRu="rating" value="5" /><label for="star5" titleRu="Kinda bad 5">5 stars</label></a>
                        <a href="Controller?command=add-rating&movieID=${movie.id}&rating=4"><input type="radio" id="star4" nameRu="rating" value="4" /><label for="star4" titleRu="Poor 4">4 stars</label></a>
                        <a href="Controller?command=add-rating&movieID=${movie.id}&rating=3"><input type="radio" id="star3" nameRu="rating" value="3" /><label for="star3" titleRu="Really bad 3">3 stars</label></a>
                        <a href="Controller?command=add-rating&movieID=${movie.id}&rating=2"><input type="radio" id="star2" nameRu="rating" value="2" /><label for="star2" titleRu="Sucks big time 2">2 stars</label></a>
                        <a href="Controller?command=add-rating&movieID=${movie.id}&rating=1"><input type="radio" id="star1" nameRu="rating" value="1" /><label for="star1" titleRu="Awful 1">1 star</label></a>
                    </fieldset>-->
                    Моя оценка: <c:forEach var="rating" items="${sessionScope.get('user').ratings}">
                    <c:if test="${rating.movieID==movie.id}">
                        <c:out value="${rating.ratingScore}"/>
                    </c:if>
                </c:forEach>
                    <br>
                    <a href="Controller?command=add-rating&movieID=${movie.id}&rating=10">10</a>
                    <a href="Controller?command=add-rating&movieID=${movie.id}&rating=9">9</a>
                    <a href="Controller?command=add-rating&movieID=${movie.id}&rating=8">8</a>
                    <a href="Controller?command=add-rating&movieID=${movie.id}&rating=7">7</a>
                    <a href="Controller?command=add-rating&movieID=${movie.id}&rating=6">6</a>
                    <a href="Controller?command=add-rating&movieID=${movie.id}&rating=5">5</a>
                    <a href="Controller?command=add-rating&movieID=${movie.id}&rating=4">4</a>
                    <a href="Controller?command=add-rating&movieID=${movie.id}&rating=3">3</a>
                    <a href="Controller?command=add-rating&movieID=${movie.id}&rating=2">2</a>
                    <a href="Controller?command=add-rating&movieID=${movie.id}&rating=1">1</a>
                </div>
                <br>

                <form action="Controller" method="post" accept-charset="UTF-8">
                    <input type="hidden" name="command" value="add-review" lang="ru"/>
                    <input type="hidden" value="${movie.id}" name="movieID">
                    <!-- TO FIX WITH CSS -->
                    <input type="text" name="review" height="200px" lang="ru"/><br/>
                    <input type="submit" value="Add review"/>
                </form>
            </c:if>
            <h3>Рецензии</h3>
            <c:if test="${movie.reviews.size()>0}">
                <c:forEach var="review" items="${requestScope.movie.reviews}">
                    <c:if test="${sessionScope.get('user').type eq 'admin' || sessionScope.get('user').type eq 'moder'}">
                        <a href="Controller?command=delete-review&movieID=${movie.id}&userNickname=${review.userNickname}">Удалить рецензию</a>
                        <a href="Controller?command=ban-user&userNickname=${review.userNickname}">Забанить пользователя</a>
                    </c:if>
                    <c:if test="${sessionScope.get('user') != null}"><c:out value="${review.userNickname}"/>
                        <a href="Controller?command=like-review&movieID=${movie.id}&reviewer=${review.userNickname}&score=up"><i
                                class="green"><c:out value="${review.thumbsUp}"/></i></a> /
                        <a href="Controller?command=like-review&movieID=${movie.id}&reviewer=${review.userNickname}&score=down"><i
                                class="red"><c:out value="${review.thumbsDown}"/></i></a>
                        : <c:out value="${review.review}"/><br/></c:if>
                    <c:if test="${sessionScope.get('user') == null}">
                        <c:out value="${review.userNickname}"/>
                        <i class="green"><c:out value="${review.thumbsUp}"/></i> /
                        <i class="red"><c:out value="${review.thumbsDown}"/></i>
                        : <c:out value="${review.review}"/><br/>
                    </c:if>
                </c:forEach>
            </c:if>


        </div>
        <div class="col-sm-2 sidenav">
            <div class="well">
                <p>ADS</p>
            </div>
            <div class="well">
                <p>ADS</p>
            </div>
        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <p>Footer Text</p>
</footer>

</body>
</html>
