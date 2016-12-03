<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.movieID" var="movieID"/>
<fmt:message bundle="${locale}" key="locale.leaveBlankMovieID" var="leaveBlankMovieID"/>
<fmt:message bundle="${locale}" key="locale.titleRu" var="titleRu"/>
<fmt:message bundle="${locale}" key="locale.titleEn" var="titleEn"/>
<fmt:message bundle="${locale}" key="locale.year" var="year"/>
<fmt:message bundle="${locale}" key="locale.budget" var="budget"/>
<fmt:message bundle="${locale}" key="locale.gross" var="gross"/>
<fmt:message bundle="${locale}" key="locale.addMovie" var="addMovie"/>
<fmt:message bundle="${locale}" key="locale.updateMovie" var="updateMovie"/>
<fmt:message bundle="${locale}" key="locale.submit" var="submit"/>
<c:if test="${sessionScope.get('user') == null && sessionScope.get('user').type ne 'admin' && sessionScope.get('user').type ne'moder'}">
    <c:redirect url="/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="./src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<c:import url="template/navbar.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">

        <c:import url="template/sideleft.jsp"/>

        <div class="col-sm-8 text-left">
            <c:if test="${requestScope.get('errorMessage')!=null}">
                <h3 class="red"><c:out value="${requestScope.get('errorMessage')}"/></h3>
                <c:remove var="errorMessage" scope="request"/>
            </c:if>
            <c:if test="${requestScope.get('successMessage')!=null}">
                <h3 class="green"><c:out value="${requestScope.get('successMessage')}"/></h3>
                <c:remove var="successMessage" scope="request"/>
            </c:if>
            <br>
            <form action="Controller" method="get">
                <p>
                    <label><input type="radio" name="command" value="add-movie" checked/>${addMovie}</label>
                    <label><input type="radio" name="command" value="update-movie"/>${updateMovie}</label>
                </p>
                <label for="movieID">${movieID}<br></label>
                <input id="movieID" class="form-control" type="text" name="id" placeholder="${leaveBlankMovieID}"/>
                <br/>
                <label for="titleRu">${titleRu}<br></label>
                <input id="titleRu" class="form-control" type="text" name="titleRu" required/>
                <br/>
                <label for="titleEn">${titleEn}<br></label>
                <input id="titleEn" class="form-control" type="text" name="titleEn" required/>
                <br/>
                <label for="year">${year}<br></label>
                <input id="year" class="form-control" type="text" name="year" required/>
                <br/>
                <label for="budget">${budget}<br></label>
                <input id="budget" class="form-control" type="text" name="budget"/>
                <br/>
                <label for="gross">${gross}<br></label>
                <input id="gross" class="form-control" type="text" name="gross"/>
                <br/>
                <button type="submit" class="btn btn-primary">${submit}</button>
            </form>
        </div>

        <c:import url="template/sideright.jsp"/>

    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
