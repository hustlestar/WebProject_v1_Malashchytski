<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.deleteReview" var="deleteReview"/>
<fmt:message bundle="${locale}" key="locale.banUser" var="banUser"/>
<fmt:message bundle="${locale}" key="locale.usefulReview" var="usefulReview"/>
<fmt:message bundle="${locale}" key="locale.latestReviews" var="latestReviews"/>
<fmt:message bundle="${locale}" key="locale.reviewFor" var="reviewFor"/>
<!DOCTYPE html>
<html>
<head>
    <title>${latestReviews}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="images/main/favicon_16x16.png">

</head>
<body onload="active()">

<c:import url="template/navbar.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("reviews-page").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/sideleft.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <h1>${latestReviews}</h1>
            <br>
            <c:if test="${requestScope.get('errorLikeReview')!=null}">
                <h3 class="red"><c:out value="${requestScope.get('errorLikeReview')}"/></h3>
                <c:remove var="errorLikeReview" scope="request"/>
            </c:if>
            <hr>
            <c:forEach var="movie" items="${requestScope.latest_movies_reviews}">
                <c:set var="review" value="${movie.reviews.get(0)}"/>
                <div class="col-sm-2 text-center">
                    <img src="${review.image}" class="img-circle" height="100" width="100" alt="Avatar">
                </div>
                <div class="col-sm-10">
                    <h4><a href="Controller?command=movie-by-id&id=${movie.id}">${reviewFor}
                        <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">
                            ${movie.titleRu}
                        </c:if>
                        <c:if test="${sessionScope.get('language') eq 'en'}">
                            ${movie.titleEn}
                        </c:if></a></h4>
                    <h4><a class="user" href="Controller?command=view-user&nickname=${review.userNickname}">
                        <c:out value="${review.userNickname}"/></a>
                        <small>
                            <fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                                            value="${review.reviewDate}"/></small>
                    </h4>
                    <c:if test="${sessionScope.get('user').type eq 'admin' || sessionScope.get('user').type eq 'moder'}">
                        <p>
                            <a class="edit"
                               href="Controller?command=delete-review&movieID=${movie.id}&userNickname=${review.userNickname}">${deleteReview}</a>
                            <a class="edit"
                               href="Controller?command=ban-user&userNickname=${review.userNickname}">${banUser}</a></p>
                    </c:if>
                    <p><c:out value="${movie.reviews.get(0).review}"/></p>
                    <p>
                        <small>${usefulReview} <c:if test="${sessionScope.get('user') != null}">
                            <a href="Controller?command=like-review&movieID=${movie.id}&reviewer=${review.userNickname}&score=up">
                                <img src="images/main/thumb_up.png" height="25px"> <i
                                    class="green"><c:out value="${review.thumbsUp}"/></i></a> /
                            <a href="Controller?command=like-review&movieID=${movie.id}&reviewer=${review.userNickname}&score=down">
                                <img src="images/main/thumb_down.png" height="25px"> <i class="red"><c:out value="${review.thumbsDown}"/></i></a>
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
        </div>

        <c:import url="template/sideright.jsp"/>

    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
