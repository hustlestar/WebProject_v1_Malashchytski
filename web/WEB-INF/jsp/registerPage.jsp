<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set scope="session" var="previousQuery" value="index.jsp"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.nickname" var="nickname"/>
<fmt:message bundle="${locale}" key="locale.password" var="password"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>
<fmt:message bundle="${locale}" key="locale.movieRateRegister" var="movieRateRegister"/>
<fmt:message bundle="${locale}" key="locale.email" var="email"/>
<fmt:message bundle="${locale}" key="locale.repeatPassword" var="repeatPassword"/>
<fmt:message bundle="${locale}" key="locale.sex" var="sex"/>
<fmt:message bundle="${locale}" key="locale.male" var="male"/>
<fmt:message bundle="${locale}" key="locale.female" var="female"/>
<fmt:message bundle="${locale}" key="locale.other" var="other"/>
<fmt:message bundle="${locale}" key="locale.register" var="register"/>
<!DOCTYPE html>
<html>
<head>
    <title>${register}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="./src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body onload="active()">

<c:import url="template/navbar.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("index-page").className = "active";
    }
</script>
<div class="container-fluid text-center flex">
    <div class="row content">

        <c:import url="template/sideleft.jsp"/>

        <div class="col-sm-8 text-left">

            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;  </button>
                        <h4 class="modal-title" id="myModalLabel">${movieRateRegister}</h4>
                    </div>
                    <c:if test="${requestScope.get('errorMessage')!=null}">
                        <h4 class="red text-center"><c:out value="${requestScope.get('errorMessage')}"/></h4>
                        <c:remove var="errorMessage" scope="request"/>
                    </c:if>
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
                                    <input type="text" class="form-control" id="inputPassword3" placeholder="${password}"
                                           name="pass" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword4" class="col-sm-3 control-label">${repeatPassword}</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="inputPassword4" placeholder="${repeatPassword}"
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

        <c:import url="template/sideright.jsp"/>

    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
