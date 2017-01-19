<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="user" class="by.hustlestar.bean.entity.User" scope="request"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.reviews" var="reviews"/>
<fmt:message bundle="${locale}" key="locale.banUser" var="banUser"/>
<fmt:message bundle="${locale}" key="locale.usefulReview" var="usefulReview"/>
<fmt:message bundle="${locale}" key="locale.latestReviews" var="latestReviews"/>
<fmt:message bundle="${locale}" key="locale.reviewFor" var="reviewFor"/>
<fmt:message bundle="${locale}" key="locale.deleteReview" var="deleteReview"/>
<fmt:message bundle="${locale}" key="locale.submit" var="submit"/>
<fmt:message bundle="${locale}" key="locale.nickname" var="nickname"/>
<fmt:message bundle="${locale}" key="locale.sex" var="sex"/>
<fmt:message bundle="${locale}" key="locale.registered" var="registered"/>
<fmt:message bundle="${locale}" key="locale.email" var="email"/>
<fmt:message bundle="${locale}" key="locale.reputation" var="reputation"/>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>${user.nickname} profile</title>
    <link rel="shortcut icon" href="images/main/favicon_16x16.png">

</head>
<body>

<c:import url="template/navbar.jsp"/>

<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/sideleft.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <div>
                <h1><c:out value="${user.nickname}"/> profile</h1>
                <div class="col-sm-3">
                    <img src="${user.image}" class="img-circle" alt="Avatar" width="100%">
                    <c:if test="${not empty param.errorMessage}">
                        <h4 class="red"><c:out value="${param.errorMessage}"/></h4>
                    </c:if>
                    <c:if test="${sessionScope.get('user').nickname eq user.nickname}">
                        <form action="Controller" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="command" value="upload-photo-for-avatar"/>
                            <input type="hidden" value="${user.nickname}" name="filename">
                            <input name="data" type="file"><br>
                            <button class="btn-sm btn-info" type="submit">${submit}</button>
                            <br>
                        </form>
                    </c:if>
                </div>
                <div class="col-sm-9">
                    ${nickname} <c:out value="${user.nickname}"/><br/>
                    ${email} <c:out value="${user.email}"/><br/>
                    ${registered} <c:out value="${user.registred}"/><br/>
                    ${sex} <c:out value="${user.sex}"/><br/>
                    ${reputation} <c:out value="${user.reputation}"/>
                </div>
            </div>
            <div class="col-sm-12">
                <hr>
                <h3>${reviews}</h3>
                <c:if test="${requestScope.get('errorLikeReview')!=null}">
                    <h3 class="red"><c:out value="${requestScope.get('errorLikeReview')}"/></h3>
                    <c:remove var="errorLikeReview" scope="request"/>
                </c:if>
                <c:if test="${user.reviews.size()>0}">
                    <c:forEach var="review" items="${user.reviews}">
                        <div class="col-sm-2 text-center">
                            <img src="${user.image}" class="img-circle" height="100" width="100"
                                 alt="Avatar">
                        </div>
                        <div class="col-sm-10">
                            <h4><a href="Controller?command=movie-by-id&id=${review.movieID}">${reviewFor}</a></h4>
                            <h4><a href="#" class="user">
                                <c:out value="${review.userNickname}"/></a>
                                <small>
                                    <fmt:formatDate type="both" dateStyle="long" timeStyle="long"
                                                    value="${review.reviewDate}"/></small>
                            </h4>
                            <c:if test="${sessionScope.get('user').type eq 'admin' || sessionScope.get('user').type eq 'moder'}">
                                <p>
                                    <a class="edit" href="Controller?command=delete-review&movieID=${review.movieID}&userNickname=${review.userNickname}">${deleteReview}</a>
                                    <a class="edit" href="Controller?command=ban-user&userNickname=${review.userNickname}">${banUser}</a></p>
                            </c:if>
                            <p><c:out value="${review.review}"/></p>
                            <p>
                                <small>${usefulReview} <c:if test="${sessionScope.get('user') != null}">
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
        </div>

        <c:import url="template/sideright.jsp"/>

    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
