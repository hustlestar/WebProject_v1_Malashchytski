<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.addMovie" var="addMovie"/>
<fmt:message bundle="${locale}" key="locale.addActor" var="addActor"/>
<fmt:message bundle="${locale}" key="locale.addNews" var="addNews"/>
<fmt:message bundle="${locale}" key="locale.viewAllUsers" var="viewAllUsers"/>
<fmt:message bundle="${locale}" key="locale.viewAllBanned" var="viewAllBanned"/>
<fmt:message bundle="${locale}" key="locale.viewAllActors" var="viewAllActors"/>
<fmt:message bundle="${locale}" key="locale.latestNews" var="latestNews"/>
<fmt:message bundle="${locale}" key="locale.latestReviews" var="latestReviews"/>
<fmt:message bundle="${locale}" key="locale.newMovies" var="newMovies"/>
<fmt:message bundle="${locale}" key="locale.topUsers" var="topUsers"/>
<div class="col-sm-2 sidenav">
    <c:if test='${sessionScope.get("user").type eq "admin" || sessionScope.get("user").type eq "moder"}'>
        <p><a class="admin" href="Controller?command=add-movie">${addMovie}</a></p>
        <p><a class="admin" href="Controller?command=add-actor">${addActor}</a></p>
        <p><a class="admin" href="Controller?command=add-news">${addNews}</a></p>
        <p><a class="admin" href="Controller?command=view-all-users">${viewAllUsers}</a></p>
        <p><a class="admin" href="Controller?command=view-all-banned-users">${viewAllBanned}</a></p>
        <p><a class="admin" href="Controller?command=view-all-actors">${viewAllActors}</a></p>
    </c:if>
    <p><a href="Controller?command=view-latest-news">${latestNews}</a></p>
    <p><a href="Controller?command=view-latest-reviews">${latestReviews}</a></p>
    <p><a href="Controller?command=latest-movies">${newMovies}</a></p>
    <p><a href="Controller?command=view-top-users">${topUsers}</a></p>
</div>

