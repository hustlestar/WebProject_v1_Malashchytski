<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set scope="session" var="previousQuery" value="index.jsp"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.nickname" var="nickname"/>
<fmt:message bundle="${locale}" key="locale.password" var="password"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>
<fmt:message bundle="${locale}" key="locale.movieRateEntrance" var="movieRateEntrance"/>
<fmt:message bundle="${locale}" key="locale.signIn" var="signIn"/>
<!DOCTYPE html>
<html>
<head>
    <title>${signIn}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="./src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="images/main/favicon_16x16.png">


</head>
<body onload="active()">

<c:import url="template/navbar.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("index-page").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/sideleft.jsp"/>

        <div class="col-sm-8 text-left mainContent">

            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <a href="index.jsp">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;  </button>
                        </a>
                        <h4 class="modal-title">${movieRateEntrance}</h4>
                    </div>
                    <c:if test="${requestScope.get('errorMessage')!=null}">
                        <h4 class="red text-center"><c:out value="${requestScope.get('errorMessage')}"/></h4>
                        <c:remove var="errorMessage" scope="request"/>
                    </c:if>
                    <div class="modal-body text-center">
                        <form name="loginForm" class="form-horizontal" method="post" action="Controller"
                              onsubmit="return validateForm();">
                            <input type="hidden" name="command" value="login"/>
                            <div class="form-group">
                                <label for="nickname" class="col-sm-3 control-label">${nickname} </label>
                                <span id="unameDemo" class="red"></span>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="nickname" placeholder="${nickname}"
                                           name="nickname" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password3" class="col-sm-3 control-label">${password}</label>
                                <span id="pswDemo" class="red"></span>
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
                        <a href="index.jsp">
                            <button type="button" class="btn btn-default" data-dismiss="modal">${cancel}</button>
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <c:import url="template/sideright.jsp"/>

    </div>
</div>

<c:import url="template/footer.jsp"/>

<script language="javascript">
    function validateForm() {
        var uname, psw;
        var unameText, pswText;
        var result = true;

        uname = document.forms["loginForm"]["nickname"].value;
        psw = document.forms["loginForm"]["pass"].value;

        var unamePattern = /[a-zA-Z_0-9]{3,16}/;
        if (!unamePattern.test(uname)) {
            unameText = "Nickname should contain only latin symbols, digits and _";
            document.getElementById("unameDemo").innerHTML = unameText;
            result = false;
        } else if (uname.length < 3) {
            unameText = "Nickname should be at least 3 symbols.";
            document.getElementById("unameDemo").innerHTML = unameText;
            result = false;
        } else if (uname.length > 16) {
            unameText = "Nickname should be less then 17 symbols.";
            document.getElementById("unameDemo").innerHTML = unameText;
            result = false;
        } else {
            unameText = "";
            document.getElementById("unameDemo").innerHTML = unameText;
        }

        var passPattern = /[a-zA-Z0-9_]{6,32}/;
        if (psw.length < 6) {
            pswText = "Password should be at least 6 symbols";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        } else if (!passPattern.test(psw)) {
            pswText = "Password should contain only latin symbols, digits and _";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        } else if (psw.length > 32) {
            pswText = "Password should be less then 32 symbols";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        } else {
            unameText = "";
            document.getElementById("pswDemo").innerHTML = unameText;
        }

        return result;
    }
</script>
</body>
</html>

