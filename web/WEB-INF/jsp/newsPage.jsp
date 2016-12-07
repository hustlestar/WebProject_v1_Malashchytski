<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="news" class="by.hustlestar.bean.entity.News" scope="request"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.relatedMovies" var="movies"/>
<fmt:message bundle="${locale}" key="locale.relatedActors" var="actors"/>


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
    <title><c:out value="${news.titleEn}"/></title>
</head>
<body onload="active()">

<c:import url="template/navbar.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("movies-page").className = "active";
    }
</script>
<div class="container-fluid text-center">
    <div class="row content">

        <c:import url="template/sideleft.jsp"/>

        <div class="col-sm-8 text-left">
            <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">
                <h1><c:out value="${news.titleRu}"/></h1>
                <h3>
                    <small>${news.newsDate}</small>
                </h3>
                <div class="col-sm-3">
                    <img src="images/actors/${news.id}.jpg" alt="picture for news" width="100%"/>
                </div>
                <div class="col-sm-9 text-left">
                    <p>${news.textRu}</p>
                    <hr>
                    <c:if test="${sessionScope.get('user').type eq 'admin' || sessionScope.get('user').type eq 'moder'}">
                        <c:if test="${news.newsActors.size()>0}">
                            ${actors}
                            <c:forEach var="actor" items="${news.newsActors}">
                                <a href="Controller?command=view-actor&actor-id=${actor.id}">
                                    <c:out value="${actor.nameRu}"/></a>
                                <a class="edit" href="Controller?command=delete-actor-for-news&actor-id=${actor.id}&news-id=${news.id}">x</a>
                            </c:forEach>
                        </c:if>
                        <br>
                        <a class="edit"  data-toggle="modal" data-target="#add-actor-for-news" href="#">Добавить актера</a>
                        <br>
                        <c:if test="${news.newsMovies.size()>0}">
                            ${movies}
                            <c:forEach var="movie" items="${news.newsMovies}">
                                <a href="Controller?command=movie-by-id&id=${movie.id}">
                                    <c:out value="${movie.titleRu}"/></a>
                                <a class="edit" href="Controller?command=delete-movie-for-news&id=${movie.id}&news-id=${news.id}">x</a>
                            </c:forEach>
                        </c:if>
                        <br>
                        <a class="edit"  data-toggle="modal" data-target="#add-movie-for-news" href="#">Добавить фильм</a>
                        <br>
                        <c:import url="template/addactorfornews.jsp"/>
                        <c:import url="template/addmoviefornews.jsp"/>
                    </c:if>
                    <c:if test="${sessionScope.get('user').type ne 'admin' && sessionScope.get('user').type ne 'moder'}">

                    </c:if>
                </div>
            </c:if>
            <c:if test="${sessionScope.get('language') eq 'en'}">
                <h1><c:out value="${news.titleEn}"/></h1>
                <h3>
                    <small>${news.newsDate}</small>
                </h3>
                <div class="col-sm-3">
                    <img src="images/actors/${news.id}.jpg" alt="picture for news" width="100%"/>
                </div>
                <div class="col-sm-9 text-left">
                    <p>${news.textEn}</p>
                    <hr>
                    <c:if test="${sessionScope.get('user').type eq 'admin' || sessionScope.get('user').type eq 'moder'}">
                        <c:if test="${news.newsActors.size()>0}">
                            ${actors}
                            <c:forEach var="actor" items="${news.newsActors}">
                                <a href="Controller?command=view-actor&actor-id=${actor.id}">
                                    <c:out value="${actor.nameEn}"/></a>
                                <a class="edit" href="Controller?command=delete-actor-for-news&actor-id=${actor.id}&news-id=${news.id}">x</a>
                            </c:forEach>
                        </c:if>
                        <br>
                        <a class="edit"  data-toggle="modal" data-target="#add-actor-for-news" href="#">Add actor for news</a>
                        <br>
                        <c:if test="${news.newsMovies.size()>0}">
                            ${movies}
                            <c:forEach var="movie" items="${news.newsMovies}">
                                <a href="Controller?command=movie-by-id&id=${movie.id}">
                                    <c:out value="${movie.titleEn}"/></a>
                                <a class="edit" href="Controller?command=delete-movie-for-news&id=${movie.id}&news-id=${news.id}">x</a>
                            </c:forEach>
                        </c:if>
                        <br>
                        <a class="edit"  data-toggle="modal" data-target="#add-movie-for-news" href="#">Add movie for news</a>
                        <br>
                        <c:import url="template/addactorfornews.jsp"/>
                        <c:import url="template/addmoviefornews.jsp"/>
                    </c:if>
                </div>
            </c:if>
        </div>
        <c:import url="template/sideright.jsp"/>
    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
