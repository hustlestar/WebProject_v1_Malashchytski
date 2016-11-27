<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
            <form action="Controller" method="get">
                <p>
                    <label><input type="radio" name="command" value="add-movie" checked/>Добавить</label>
                    <label><input type="radio" name="command" value="update-movie"/>Обновить</label>
                </p>
                Movie ID:<input type="text" name="id"/><br/>
                Movie Title Ru:<input type="text" name="titleRu"/><br/>
                Movie Title En:<input type="text" name="titleEn"/><br/>
                Year filmed:<input type="text" name="year"/><br/>
                Budget:<input type="text" name="budget"/>
                Gross:<input type="text" name="gross"/>
                <input type="submit" value="Confirm"/>
            </form>
        </div>

        <c:import url="template/sideright.jsp"/>

    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
