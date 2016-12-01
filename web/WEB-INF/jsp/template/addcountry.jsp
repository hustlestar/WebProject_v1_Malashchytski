<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.addCountryForMovie" var="addCountryForMovie"/>
<fmt:message bundle="${locale}" key="locale.countryNameRu" var="countryNameRu"/>
<fmt:message bundle="${locale}" key="locale.countryNameEn" var="countryNameEn"/>
<fmt:message bundle="${locale}" key="locale.submit" var="submit"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div id="add-country" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;  </button>
                <h4 class="modal-title" id="myModalLabel">${addCountryForMovie}</h4>
            </div>
            <div class="modal-body text-center">
                <form class="form-horizontal" method="post" action="Controller">
                    <input type="hidden" name="command" value="add-country-for-movie"/>
                    <input type="hidden" name="id" value="${movie.id}"/>
                    <div class="form-group">
                        <label for="inputNickname" class="col-sm-3 control-label">${countryNameRu}</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputNickname" placeholder="${countryNameRu}"
                                   name="nameRu" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-3 control-label">${countryNameEn}</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputPassword3" placeholder="${countryNameEn}"
                                   name="nameEn" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-7">
                            <button type="submit" class="btn btn-primary">${submit}</button>
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
</body>
</html>
