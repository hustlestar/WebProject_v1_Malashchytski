<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.erorrPage" var="erorrPage"/>


<!DOCTYPE html>
<html>
<head>
    <title>${erorrPage}</title>
    <link rel="shortcut icon" href="images/main/favicon_16x16.png">
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


<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="WEB-INF/jsp/template/sideleft.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <c:if test="${requestScope.get('errorMessage')!=null}">
                <h3 class="red"><c:out value="${requestScope.get('errorMessage')}"/></h3>
                <c:remove var="errorMessage" scope="request"/>
            </c:if>
        </div>

        <c:import url="WEB-INF/jsp/template/sideright.jsp"/>

    </div>
</div>

<c:import url="WEB-INF/jsp/template/footer.jsp"/>
</body>
</html>
