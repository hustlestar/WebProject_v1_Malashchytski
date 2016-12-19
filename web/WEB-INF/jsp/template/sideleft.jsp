<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.siteName" var="siteName"/>
<div class="col-sm-2 sidenav">
    <c:if test='${sessionScope.get("user").type eq "admin" || sessionScope.get("user").type eq "moder"}'>
        <p><a href="Controller?command=add-movie">Add movie</a></p>
        <p><a href="Controller?command=add-actor">Add actor</a></p>
        <p><a href="Controller?command=add-news">Add news</a></p>
        <p><a href="Controller?command=view-all-users">View all users</a></p>
        <p><a href="Controller?command=view-all-banned-users">View all banned users</a></p>
        <p><a href="Controller?command=view-all-actors">View all actors</a></p>

    </c:if>
    <p><a href="Controller?command=view-latest-news">Latest news</a></p>
    <p><a href="Controller?command=view-latest-reviews">Latest reviews</a></p>
    <p><a href="Controller?command=latest-movies">Latest movies</a></p>
    <p><a href="Controller?command=view-top-users">Top users</a></p>
    <p><a href="#">Link</a></p>
    <p><a href="#">Link</a></p>
</div>

