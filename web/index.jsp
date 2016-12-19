<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set scope="session" var="previousQuery" value="index.jsp"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.indexTitle" var="indexTitle"/>
<fmt:message bundle="${locale}" key="locale.searchTitle" var="searchTitle"/>
<fmt:message bundle="${locale}" key="locale.searchLabel" var="searchLabel"/>
<fmt:message bundle="${locale}" key="locale.search" var="search"/>
<fmt:message bundle="${locale}" key="locale.movie" var="movie"/>
<!DOCTYPE html>
<html>
<head>
    <title>${indexTitle}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="./src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body onload="active()">

<c:import url="WEB-INF/jsp/template/navbar.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("index-page").className = "active";
    }
</script>
<div class="container-fluid text-center flex">
    <div class="row content">

        <c:import url="WEB-INF/jsp/template/sideleft.jsp"/>

        <div class="col-sm-8 text-left">
            <br>
            <form method="get" action="Controller" class="form-horizontal">
                <input type="hidden" name="command" value="find-movie-by-title"/>
                <div class="form-group">
                    <label for="search" class="col-sm-2 control-label">${searchLabel}</label>
                    <div class="col-sm-8">
                        <input id="search" title="${searchTitle}" type="text" class="form-control"
                               placeholder="${movie}"
                               name="movieTitle"/>
                        <br/>
                    </div>
                    <div class="col-sm-2">
                        <button type="submit" class="btn btn-success">${search}</button>
                    </div>
                </div>

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
