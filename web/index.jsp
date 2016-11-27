<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>

<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="./src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<c:import url="WEB-INF/jsp/template/navbar.jsp"/>

<div class="container-fluid text-center flex">
    <div class="row content">

        <c:import url="WEB-INF/jsp/template/sideleft.jsp"/>

        <div class="col-sm-8 text-left">
            <form method="get" action="Controller">
                <input type="hidden" name="command" value="find-movie-by-title"/>
                All about:<input type="text" placeholder="Movie" name="movieTitle"/><br/>
                <input type="submit" value="Search"/>
            </form>
            <h4>
                <small>RECENT POSTS</small>
            </h4>
            <hr>
            <h2>Officially Blogging</h2>
            <h5><span class="glyphicon glyphicon-time"></span> Post by John Doe, Sep 24, 2015.</h5>
            <h5><span class="label label-success">Lorem</span></h5><br>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
                ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
                ut aliquip ex ea commodo consequat.</p>
            <hr>
        </div>

        <c:import url="WEB-INF/jsp/template/sideright.jsp"/>

    </div>
</div>

<c:import url="WEB-INF/jsp/template/footer.jsp"/>

</body>
</html>
