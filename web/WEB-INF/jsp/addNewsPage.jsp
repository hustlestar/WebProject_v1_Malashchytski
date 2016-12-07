<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.newsID" var="newsID"/>
<fmt:message bundle="${locale}" key="locale.leaveBlank" var="leaveBlankNewsID"/>
<fmt:message bundle="${locale}" key="locale.newsTitleRu" var="newsTitleRu"/>
<fmt:message bundle="${locale}" key="locale.newsTitleEn" var="newsTitleEn"/>
<fmt:message bundle="${locale}" key="locale.newsTextRu" var="newsTextRu"/>
<fmt:message bundle="${locale}" key="locale.newsTextEn" var="newsTextEn"/>
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
            <form action="Controller" method="post">
                <p>
                    <label><input type="radio" name="command" value="add-news" checked/>${addMovie}</label>
                    <label><input type="radio" name="command" value="update-news"/>${updateMovie}</label>
                </p>
                <label for="newsID">${newsID}<br></label>
                <input id="newsID" class="form-control" type="text" name="id" placeholder="${leaveBlankNewsID}"/>
                <br/>
                <label for="titleRu">${newsTitleRu}<br></label>
                <input id="titleRu" class="form-control" type="text" name="newsTitleRu" required/>
                <br/>
                <label for="titleEn">${newsTitleEn}<br></label>
                <input id="titleEn" class="form-control" type="text" name="newsTitleEn" required/>
                <br/>
                <label for="newsTextRu">${newsTextRu}<br></label>
                <input id="newsTextRu" class="form-control" type="text" name="newsTextRu" required/>
                <br/>
                <label for="newsTextEn">${newsTextEn}<br></label>
                <input id="newsTextEn" class="form-control" type="text" name="newsTextEn"/>
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
