<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="movie" class="by.hustlestar.bean.entity.Movie" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title><c:out value="${movie.title}"/></title>
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

            Название фильма <c:out value="${movie.title}"/><br/>
            Год <c:out value="${movie.year}"/><br/>
            <c:if test="${movie.countries.size()>0}">
                Страна <c:forEach var="country" items="${requestScope.movie.countries}">
                <a href="Controller?command=movies-by-country&country=${country.name}"><c:out
                        value="${country.name}"/></a>
            </c:forEach>
                <br/>
            </c:if>
            Бюджет <c:out value="${movie.budget}"/><br/>
            Сборы в мире <c:out value="${movie.gross}"/>

            <c:if test="${sessionScope.get('user') != null}">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="add-review" lang="ru"/>
                <input type="hidden" value="${movie.id}" name="movieID">
                <input type="hidden" value="${sessionScope.user.nickname}" name="userNickname">
                <!-- TO FIX WITH CSS -->
                <input type="text" name="review" height="200px"/><br/>
                <input type="submit" value="Add review"/>
            </form>
            </c:if>
            <h3>Рецензии</h3>
            <c:if test="${movie.reviews.size()>0}">
                <c:forEach var="review" items="${requestScope.movie.reviews}">
                    <c:out value="${review.userNickname}"/>: <c:out value="${review.review}"/><br/>
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
