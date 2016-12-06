<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.actorID" var="actorID"/>
<fmt:message bundle="${locale}" key="locale.leaveBlank" var="leaveBlankActorID"/>
<fmt:message bundle="${locale}" key="locale.nameRu" var="nameRu"/>
<fmt:message bundle="${locale}" key="locale.nameEn" var="nameEn"/>
<fmt:message bundle="${locale}" key="locale.year" var="year"/>
<fmt:message bundle="${locale}" key="locale.budget" var="budget"/>
<fmt:message bundle="${locale}" key="locale.gross" var="gross"/>
<fmt:message bundle="${locale}" key="locale.addActor" var="addActor"/>
<fmt:message bundle="${locale}" key="locale.updateActor" var="updateActor"/>
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
            <form action="Controller" method="post">
                <p>
                    <label><input type="radio" name="command" value="add-actor" checked/>${addActor}</label>
                    <label><input type="radio" name="command" value="update-actor"/>${updateActor}</label>
                </p>
                <label for="movieID">${actorID}<br></label>
                <input id="movieID" class="form-control" type="text" name="actor-id" placeholder="${leaveBlankActorID}"/>
                <br/>
                <label for="titleRu">${nameRu}<br></label>
                <input id="titleRu" class="form-control" type="text" name="nameRu" required/>
                <br/>
                <label for="titleEn">${nameEn}<br></label>
                <input id="titleEn" class="form-control" type="text" name="nameEn" required/>
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
