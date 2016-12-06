<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="movie" class="by.hustlestar.bean.entity.Movie" scope="request"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.year" var="year"/>
<fmt:message bundle="${locale}" key="locale.country" var="country"/>
<fmt:message bundle="${locale}" key="locale.genre" var="genre"/>
<fmt:message bundle="${locale}" key="locale.director" var="director"/>
<fmt:message bundle="${locale}" key="locale.budget" var="budget"/>
<fmt:message bundle="${locale}" key="locale.gross" var="gross"/>
<fmt:message bundle="${locale}" key="locale.rating" var="rating"/>
<fmt:message bundle="${locale}" key="locale.votes" var="votes"/>
<fmt:message bundle="${locale}" key="locale.noRating" var="noRating"/>
<fmt:message bundle="${locale}" key="locale.noVotes" var="noVotes"/>
<fmt:message bundle="${locale}" key="locale.yourRating" var="yourRating"/>
<fmt:message bundle="${locale}" key="locale.rateMovie" var="rateMovie"/>
<fmt:message bundle="${locale}" key="locale.reviews" var="reviews"/>
<fmt:message bundle="${locale}" key="locale.cast" var="cast"/>
<fmt:message bundle="${locale}" key="locale.deleteReview" var="deleteReview"/>
<fmt:message bundle="${locale}" key="locale.banUser" var="banUser"/>
<fmt:message bundle="${locale}" key="locale.usefulReview" var="usefulReview"/>


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
    <title><c:out value="${movie.titleRu}"/></title>
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
                <h1><c:out value="${movie.titleRu}"/></h1>
                <h3><c:out value="${movie.titleEn}"/></h3>
            </c:if>
            <c:if test="${sessionScope.get('language') eq 'en'}">
                <h1><c:out value="${movie.titleEn}"/></h1>
            </c:if>
            <div class="col-sm-3">
                <img src="images/movies/${movie.id}.jpg" alt="picture for movie" width="100%"/>
            </div>
            <div class="col-sm-9 text-left">
                ${year} <c:out value="${movie.year}"/><br/>
                <c:if test="${sessionScope.get('user').type eq 'admin' || sessionScope.get('user').type eq 'moder'}">
                    <c:if test="${requestScope.get('errorCountry')!=null}">
                        <h3 class="red"><c:out value="${requestScope.get('errorCountry')}"/></h3>
                        <c:remove var="errorCountry" scope="request"/>
                    </c:if>
                    <c:if test="${requestScope.get('errorGenre')!=null}">
                        <h3 class="red"><c:out value="${requestScope.get('errorGenre')}"/></h3>
                        <c:remove var="errorGenre" scope="request"/>
                    </c:if>
                    <c:if test="${sessionScope.get('language') eq 'en'}">
                        <c:if test="${movie.countries.size()>0}">
                            ${country}
                            <c:forEach var="country" items="${requestScope.movie.countries}">
                                <a href="Controller?command=movies-by-country&country=${country.nameEn}">
                                    <c:out value="${country.nameEn}"/></a>
                                <a class="edit" href="Controller?command=delete-country-for-movie&id=${movie.id}&country=${country.nameEn}">x</a>
                            </c:forEach>
                        </c:if>
                        <br>
                        <a  class="edit"  data-toggle="modal" data-target="#add-country" href="#">Add country</a>
                        <br/>
                        <c:if test="${movie.genres.size()>0}">
                            ${genre}
                            <c:forEach var="genre" items="${requestScope.movie.genres}">
                                <a href="Controller?command=movies-by-genre&genre=${genre.nameEn}"><c:out
                                        value="${genre.nameEn}"/></a>
                                <a class="edit"  href="Controller?command=delete-genre-for-movie&id=${movie.id}&genre=${genre.nameEn}">x</a>
                            </c:forEach>
                        </c:if>
                        <br>
                        <a  class="edit"  data-toggle="modal" data-target="#add-genre" href="#">Add genre</a>
                        <br/>
                        <c:if test="${movie.director ne null}">
                            ${director}
                            <a href="Controller?command=view-actor&actor-id=${movie.director.id}">
                                <c:out value="${movie.director.nameEn}"/></a>
                            <a  class="edit"  href="Controller?command=delete-director-for-movie&actor-id=${movie.director.id}&id=${movie.id}">x</a>
                        </c:if>
                        <br>
                        <a class="edit"  data-toggle="modal" data-target="#add-director" href="#">Add director</a>
                        <br/>
                    </c:if>
                    <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">
                        <c:if test="${movie.countries.size()>0}">
                            ${country}
                            <c:forEach var="country" items="${requestScope.movie.countries}">
                                <a href="Controller?command=movies-by-country&country=${country.nameEn}"><c:out
                                        value="${country.nameRu}"/></a>
                                <a class="edit"  href="Controller?command=delete-country-for-movie&actor-id=${movie.id}&country=${country.nameEn}">x</a>
                            </c:forEach>
                        </c:if>
                        <br>
                        <a class="edit"  data-toggle="modal" data-target="#add-country" href="#">Добавить страну</a>
                        <br/>
                        <c:if test="${movie.genres.size()>0}">
                            ${genre}
                            <c:forEach var="genre" items="${requestScope.movie.genres}">
                                <a href="Controller?command=movies-by-genre&genre=${genre.nameEn}">
                                    <c:out value="${genre.nameRu}"/></a>
                                <a class="edit"  href="Controller?command=delete-genre-for-movie&id=${movie.id}&genre=${genre.nameEn}">x</a>
                            </c:forEach>
                        </c:if>
                        <br>
                        <a class="edit"  data-toggle="modal" data-target="#add-genre" href="#">Добавить жанр</a>
                        <br/>
                        <c:if test="${movie.director ne null}">
                            ${director}
                            <a href="Controller?command=view-actor&actor-id=${movie.director.id}">
                                <c:out value="${movie.director.nameRu}"/></a>
                            <a class="edit"  href="Controller?command=delete-director-for-movie&actor-id=${movie.director.id}&id=${movie.id}">x</a>
                        </c:if>
                        <br>
                        <a class="edit"  data-toggle="modal" data-target="#add-director" href="#">Добавить режиссера</a>
                        <br/>
                    </c:if>
                    <c:import url="template/addcountry.jsp"/>
                    <c:import url="template/addgenre.jsp"/>
                    <c:import url="template/adddirector.jsp"/>
                </c:if>
                <c:if test="${sessionScope.get('user').type ne 'admin' && sessionScope.get('user').type ne 'moder'}">
                    <c:if test="${sessionScope.get('language') eq 'en'}">
                        <c:if test="${movie.countries.size()>0}">
                            ${country} <c:forEach var="country" items="${requestScope.movie.countries}">
                            <a href="Controller?command=movies-by-country&country=${country.nameEn}">
                                <c:out value="${country.nameEn}"/></a>
                        </c:forEach>
                        </c:if>
                        <br/>
                        <c:if test="${movie.genres.size()>0}">
                            ${genre} <c:forEach var="genre" items="${requestScope.movie.genres}">
                            <a href="Controller?command=movies-by-genre&genre=${genre.nameEn}">
                                <c:out value="${genre.nameEn}"/></a>
                        </c:forEach>
                        </c:if>
                        <br/>
                        <c:if test="${movie.director ne null}">
                            ${director}
                            <a href="Controller?command=view-actor&actor-id=${movie.director.id}">
                                <c:out value="${movie.director.nameEn}"/></a>
                        </c:if>
                        <br/>
                    </c:if>
                    <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">
                        <c:if test="${movie.countries.size()>0}">
                            ${country} <c:forEach var="country" items="${requestScope.movie.countries}">
                            <a href="Controller?command=movies-by-country&country=${country.nameEn}">
                                <c:out value="${country.nameRu}"/></a>
                        </c:forEach>
                        </c:if>
                        <br/>
                        <c:if test="${movie.genres.size()>0}">
                            ${genre} <c:forEach var="genre" items="${requestScope.movie.genres}">
                            <a href="Controller?command=movies-by-genre&genre=${genre.nameEn}">
                                <c:out value="${genre.nameRu}"/></a>
                        </c:forEach>
                        </c:if>
                        <br/>
                        <c:if test="${movie.director ne null}">
                            ${director}
                            <a href="Controller?command=view-actor&actor-id=${movie.director.id}">
                                <c:out value="${movie.director.nameRu}"/></a>
                        </c:if>
                        <br/>
                    </c:if>
                </c:if>

                ${budget} <c:out value="${movie.budget}"/><br/>
                ${gross} <c:out value="${movie.gross}"/><br/>
                <hr>
                <c:if test="${movie.ratingVotes>0}">
                    ${rating} <c:out value="${movie.avgRating}"/><br/>
                    ${votes} <c:out value="${movie.ratingVotes}"/><br/>
                </c:if>
                <c:if test="${movie.ratingVotes==0}">
                    ${noRating}<br/>
                    ${noVotes}<br>
                </c:if>

                <c:if test="${sessionScope.get('user') != null}">
                    <c:if test="${requestScope.get('errorRating')!=null}">
                        <h3 class="red"><c:out value="${requestScope.get('errorRating')}"/></h3>
                        <c:remove var="errorRating" scope="request"/>
                    </c:if>
                    ${yourRating}
                    <c:forEach var="rating" items="${movie.ratings}">
                        <c:if test="${rating.userNickname eq sessionScope.get('user').nickname}">
                            <c:out value="${rating.ratingScore}"/>
                        </c:if>
                    </c:forEach>
                    <br>
                    ${rateMovie}
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
                    <br>
                </c:if>
                <hr>
                <c:if test="${sessionScope.get('user').type ne 'admin' && sessionScope.get('user').type ne 'moder'}">
                    <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">
                        <c:if test="${movie.actors.size()>0}">
                            ${cast}
                            <c:forEach var="actor" items="${requestScope.movie.actors}">
                                <a href="Controller?command=view-actor&actor-id=${actor.id}">
                                    <c:out value="${actor.nameRu}"/></a>
                            </c:forEach>
                        </c:if>
                    </c:if>
                    <c:if test="${sessionScope.get('language') eq 'en'}">
                        <c:if test="${movie.actors.size()>0}">
                            ${cast}
                            <c:forEach var="actor" items="${requestScope.movie.actors}">
                                <a href="Controller?command=view-actor&actor-id=${actor.id}">
                                    <c:out value="${actor.nameEn}"/></a>
                            </c:forEach>
                        </c:if>
                    </c:if>
                </c:if>
                <c:if test="${sessionScope.get('user').type eq 'admin' || sessionScope.get('user').type eq 'moder'}">
                    <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">
                        <c:if test="${movie.actors.size()>0}">
                            ${cast}
                            <c:forEach var="actor" items="${requestScope.movie.actors}">
                                <a href="Controller?command=view-actor&actor-id=${actor.id}">
                                    <c:out value="${actor.nameRu}"/></a>
                            <a class="edit"  href="Controller?command=delete-actor-for-movie&actor-id=${actor.id}&id=${movie.id}">x</a>

                            </c:forEach>
                            <br>
                            <a class="edit"  data-toggle="modal" data-target="#add-actor" href="#">Добавить актера</a>
                            <br>
                        </c:if>
                    </c:if>
                    <c:if test="${sessionScope.get('language') eq 'en'}">
                        <c:if test="${movie.actors.size()>0}">
                            ${cast}
                            <c:forEach var="actor" items="${requestScope.movie.actors}">
                                <a href="Controller?command=view-actor&actor-id=${actor.id}">
                                    <c:out value="${actor.nameEn}"/></a>
                                <a class="edit"  href="Controller?command=delete-actor-for-movie&actor-id=${actor.id}&id=${movie.id}">x</a>
                            </c:forEach>
                            <br>
                            <a class="edit"  data-toggle="modal" data-target="#add-actor" href="#">Add actor</a>
                            <br>
                        </c:if>
                    </c:if>

                    <c:import url="template/addactor.jsp"/>

                </c:if>


            </div>
            <div class="col-sm-12">
                <hr>
                <c:if test="${sessionScope.get('user') != null}">
                    <c:if test="${requestScope.get('errorReview')!=null}">
                        <h3 class="red"><c:out value="${requestScope.get('errorReview')}"/></h3>
                        <c:remove var="errorReview" scope="request"/>
                    </c:if>
                    <form action="Controller" method="post" accept-charset="UTF-8">
                        <input type="hidden" name="command" value="add-review" lang="ru"/>
                        <input type="hidden" value="${movie.id}" name="movieID">
                        <p>
                            <label>
                                Русский <input type="radio" name="lang" value="ru" checked/>
                            </label>
                            <label>
                                English <input type="radio" name="lang" value="en"/>
                            </label>
                        </p>
                        <label>
                            Review text <input type="text" name="review" required/>
                        </label><br/>
                        <button class="btn btn-info" type="submit">Add review</button>
                    </form>
                </c:if>
                <h3><span class="badge">${movie.reviews.size()}</span> ${reviews}</h3><br>

                <div class="row">
                    <c:if test="${requestScope.get('errorLikeReview')!=null}">
                        <h3 class="red"><c:out value="${requestScope.get('errorLikeReview')}"/></h3>
                        <c:remove var="errorLikeReview" scope="request"/>
                    </c:if>
                    <c:if test="${movie.reviews.size()>0}">
                        <c:forEach var="review" items="${requestScope.movie.reviews}">
                            <div class="col-sm-2 text-center">
                                <img src="images/users/anon.jpg" class="img-circle" height="65" width="65" alt="Avatar">
                            </div>
                            <div class="col-sm-10">
                                <h4><a href="Controller?command=view-user&nickname=${review.userNickname}">
                                    <c:out value="${review.userNickname}"/></a>
                                    <small><fmt:formatDate type="both" dateStyle="long" timeStyle="long"
                                                           value="${review.reviewDate}"/></small>
                                </h4>
                                <c:if test="${sessionScope.get('user').type eq 'admin' || sessionScope.get('user').type eq 'moder'}">
                                    <p>
                                        <a  class="edit"  href="Controller?command=delete-review&movieID=${movie.id}&userNickname=${review.userNickname}">
                                        ${deleteReview}</a>
                                        <a  class="edit"  href="Controller?command=ban-user&userNickname=${review.userNickname}">${banUser}</a></p>
                                </c:if>
                                <p><c:out value="${review.review}"/></p>
                                <p>
                                    <small>${usefulReview}<c:if test="${sessionScope.get('user') != null}">
                                        <a href="Controller?command=like-review&movieID=${movie.id}&reviewer=${review.userNickname}&score=up"><i
                                                class="green"><c:out value="${review.thumbsUp}"/></i></a> /
                                        <a href="Controller?command=like-review&movieID=${movie.id}&reviewer=${review.userNickname}&score=down"><i
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
                    </c:if>
                </div>
            </div>

        </div>

        <c:import url="template/sideright.jsp"/>

    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
