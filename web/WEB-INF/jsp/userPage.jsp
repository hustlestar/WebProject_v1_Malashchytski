<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="user" class="by.hustlestar.bean.entity.User" scope="request"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.reviews" var="reviews"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title><c:out value="${user.nickname}"/> profile</title>
</head>
<body>

<c:import url="template/navbar.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">

        <c:import url="template/sideleft.jsp"/>

        <div class="col-sm-8 text-left">
            <h1><c:out value="${user.nickname}"/> profile</h1>

            <c:out value="${user.nickname}"/><br/>
            <c:out value="${user.email}"/><br/>
            <c:out value="${user.registred}"/><br/>
            <c:out value="${user.sex}"/>
            <h3>${reviews}</h3>
            <c:if test="${user.reviews.size()>0}">
                <c:forEach var="review" items="${user.reviews}">
                    <div class="col-sm-2 text-center">
                        <img src="images/users/anon.jpg" class="img-circle" height="65" width="65" alt="Avatar">
                    </div>
                    <div class="col-sm-10">
                        <h4><a href="Controller?command=movie-by-id&id=${review.movieID}">Review for movie</a></h4>
                        <h4><a href="Controller?command=view-user&nickname=${review.userNickname}">
                            <c:out value="${review.userNickname}"/></a>
                            <small>
                                <fmt:formatDate type="both" dateStyle="long" timeStyle="long"
                                                value="${review.reviewDate}"/></small>
                        </h4>
                        <c:if test="${sessionScope.get('user').type eq 'admin' || sessionScope.get('user').type eq 'moder'}">
                            <p>
                                <a href="Controller?command=delete-review&movieID=${review.movieID}&userNickname=${review.userNickname}">Удалить
                                    рецензию</a>
                                <a href="Controller?command=ban-user&userNickname=${review.userNickname}">Забанить
                                    пользователя</a></p>
                        </c:if>
                        <p><c:out value="${review.review}"/></p>
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
            </c:if>
        </div>

        <c:import url="template/sideright.jsp"/>

    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
