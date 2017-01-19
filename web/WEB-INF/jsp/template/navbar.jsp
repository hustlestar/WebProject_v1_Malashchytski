<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.siteName" var="siteName"/>
<fmt:message bundle="${locale}" key="locale.home" var="home"/>
<fmt:message bundle="${locale}" key="locale.signUp" var="signUp"/>
<fmt:message bundle="${locale}" key="locale.logIn" var="logIn"/>
<fmt:message bundle="${locale}" key="locale.logOut" var="logOut"/>
<fmt:message bundle="${locale}" key="locale.signIn" var="signIn"/>
<fmt:message bundle="${locale}" key="locale.movieRateEntrance" var="movieRateEntrance"/>
<fmt:message bundle="${locale}" key="locale.nickname" var="nickname"/>
<fmt:message bundle="${locale}" key="locale.password" var="password"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>
<fmt:message bundle="${locale}" key="locale.movies" var="movies"/>
<fmt:message bundle="${locale}" key="locale.movieRateRegister" var="movieRateRegister"/>
<fmt:message bundle="${locale}" key="locale.email" var="email"/>
<fmt:message bundle="${locale}" key="locale.repeatPassword" var="repeatPassword"/>
<fmt:message bundle="${locale}" key="locale.sex" var="sex"/>
<fmt:message bundle="${locale}" key="locale.male" var="male"/>
<fmt:message bundle="${locale}" key="locale.female" var="female"/>
<fmt:message bundle="${locale}" key="locale.other" var="other"/>
<fmt:message bundle="${locale}" key="locale.register" var="register"/>
<fmt:message bundle="${locale}" key="locale.reviews" var="reviews"/>
<fmt:message bundle="${locale}" key="locale.news" var="news"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">${siteName}</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li id="index-page"><a href="../index.jsp">${home}</a></li>
                <li id="movies-page"><a href="Controller?command=all-movies">${movies}</a></li>
                <li id="reviews-page"><a href="Controller?command=view-latest-reviews">${reviews}</a></li>
                <li id="news-page"><a href="Controller?command=view-latest-news">${news}</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.get('user') == null}">
                    <li class="sign-up">
                        <a data-toggle="modal" data-target="#register" href="#">
                            <span class="glyphicon glyphicon-user"></span>
                                ${signUp}</a>
                    </li>
                    <li><a data-toggle="modal" data-target="#login" href="#">
                        <span class="glyphicon glyphicon-log-in"></span>
                            ${logIn}</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.get('user') != null}">
                    <li class="sign-up">
                        <a href="Controller?command=view-user&nickname=${sessionScope.get('user').nickname}">
                            <span class="glyphicon glyphicon-user"></span> ${sessionScope.get('user').nickname}</a>
                    </li>
                    <li><a href="Controller?command=log-out">
                        <span class="glyphicon glyphicon-log-out"></span> ${logOut}</a>
                    </li>
                </c:if>
                <li><a href="Controller?command=change-language&language=en">English</a></li>
                <li><a href="Controller?command=change-language&language=ru">русский</a></li>
            </ul>
        </div>
    </div>
</nav>

<div id="login" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;  </button>
                <h4 class="modal-title">${movieRateEntrance}</h4>
            </div>
            <div class="modal-body text-center">
                <form class="form-horizontal" method="post" action="Controller">
                    <input type="hidden" name="command" value="login"/>
                    <div class="form-group">
                        <label for="nickname" class="col-sm-3 control-label">${nickname}</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="nickname" placeholder="${nickname}"
                                   name="nickname" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password3" class="col-sm-3 control-label">${password}</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="password3" placeholder="${password}"
                                   name="pass" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-7">
                            <button type="submit" class="btn btn-primary">${signIn}</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">${cancel}</button>
            </div>
        </div>
    </div>
</div>

<div id="register" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;  </button>
                <h4 class="modal-title" id="myModalLabel">${movieRateRegister}</h4>
            </div>
            <div class="modal-body text-center">
                <form class="form-horizontal" method="post" action="Controller">
                    <input type="hidden" name="command" value="register"/>
                    <div class="form-group">
                        <label for="inputNickname" class="col-sm-3 control-label">${nickname}</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputNickname" placeholder="${nickname}"
                                   name="nickname" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-3 control-label">${email}</label>
                        <div class="col-sm-7">
                            <input type="email" class="form-control" id="email" placeholder="${email}"
                                   name="email" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-3 control-label">${password}</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="inputPassword3" placeholder="${password}"
                                   name="pass" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword4" class="col-sm-3 control-label">${repeatPassword}</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="inputPassword4" placeholder="${repeatPassword}"
                                   name="pass2" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="col-sm-3 control-label"><b>${sex}</b></p>
                        <div class="col-sm-7">
                            <label class="radio-inline">
                                <input type="radio" name="sex" id="inlineRadio1" value="m" checked> ${male}
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="sex" id="inlineRadio2" value="f"> ${female}
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="sex" id="inlineRadio3" value="n"> ${other}
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-7">
                            <button type="submit" class="btn btn-primary">${register}</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">${cancel}</button>
            </div>
        </div>
    </div>
</div>
