<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="user" class="by.hustlestar.bean.entity.User" scope="request"/>
<html>
<head>
    <title><c:out value="${user.nickname}"/> profile</title>
</head>
<body>
<c:out value="${user.nickname}"/><br/>
<c:out value="${user.email}"/><br/>
<c:out value="${user.type}"/><br/>
<c:out value="${user.registred}"/><br/>
<c:out value="${user.sex}"/>
<h3>Рецензии</h3>
<c:if test="${user.reviews.size()>0}">
    <c:forEach var="review" items="${requestScope.user.reviews}">
        <c:out value="${review.userNickname}"/><i class="green"><c:out value="${review.thumbsUp}"/></i> / <i
            class="red"><c:out value="${review.thumbsDown}"/></i>
        : <c:out value="${review.review}"/><br/>
    </c:forEach>
</c:if>

</body>
</html>
