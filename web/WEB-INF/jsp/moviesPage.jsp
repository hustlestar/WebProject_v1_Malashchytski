<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Hustler
  Date: 01.11.2016
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">Home</a></li>
                <li class="active"><a href="#">Movies</a></li>
                <li><a href="#">Projects</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <% if (session.getAttribute("user") == null) {
                    out.println("<li class=\"sign-up\"><a href=\"goToRegisterPage\"><span class=\"glyphicon glyphicon-user\"></span> Sign\n" +
                            " Up</a></li>\n" +
                            "<li><a href=\"goToLoginPage\"><span class=\"glyphicon glyphicon-log-in\"></span> Login</a></li>");
                } else {
                    String userName = String.valueOf(session.getAttribute("user"));

                    out.println("<li class=\"sign-up\"><a href=\"goToRegisterPage\"><span class=\"glyphicon glyphicon-user\"></span>" +
                            userName + "</a></li>\n" +
                            "<li><a href=\"Controller?command=log-out\"><span class=\"glyphicon glyphicon-log-out\"></span> Logout</a></li>");
                }
                %>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
            <p><a href="#">Link</a></p>
            <p><a href="#">Link</a></p>
            <p><a href="#">Link</a></p>
        </div>
        <div class="col-sm-8 text-left">
            <h1>Welcome</h1>
            <table border="1">
                <c:forEach var="movie" items="${requestScope.all_movies}">
                    <tr>
                        <td><c:out value="${movie.title}"/></td>
                        <td><c:out value="${movie.year}"/></td>
                    </tr>
                </c:forEach>

            </table>
            <h3>Test</h3>
            <p>Lorem ipsum...</p>
        </div>
        <div class="col-sm-2 sidenav">
            <div class="well">
                <p>ADS</p>
            </div>
            <div class="well">
                <p>ADS</p>
            </div>
        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <p>Footer Text</p>
</footer>

</body>
</html>
