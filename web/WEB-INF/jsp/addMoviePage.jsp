<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="WINDOWS-1251" language="java" %>
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
                <li class="active"><a href="index.jsp">Home</a></li>
                <li>
                    <a href="Controller?command=all-movies">
                        Movies
                    </a>
                </li>
                <li><a href="#">Projects</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.get('user') == null}">
                    <li class="sign-up">
                        <a href="Controller?command=register">
                            <span class="glyphicon glyphicon-user"></span>
                            Sign Up</a>
                    </li>
                    <li><a href="Controller?command=login"><span class="glyphicon glyphicon-log-in"></span>
                        Login</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.get('user') != null}">
                    <li class="sign-up">
                        <a href="Controller?command=my-profile">
                            <span class="glyphicon glyphicon-user"></span> ${sessionScope.get('user').nickname}</a>
                    </li>
                    <li><a href="Controller?command=log-out">
                        <span class="glyphicon glyphicon-log-out"></span> Logout</a>
                    </li>
                </c:if>
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
            <form action="Controller" method="get">
                <input type="hidden" name="command" value="add-movie" lang="ru"/>
                Movie Title:<input type="text" name="title"/><br/>
                Year filmed:<input type="text" name="year"/><br/>
                Budget:<input type="text" name="budget"/>
                Gross:<input type="text" name="gross"/>
                <input type="submit" value="add-movie"/>
            </form>
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