<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.latestNews" var="latestNews"/>
<!DOCTYPE html>
<html>
<head>
    <title>${latestNews}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="images/main/favicon_16x16.png">

</head>
<body onload="active()">

<c:import url="template/navbar.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("news-page").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/sideleft.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <h1>${latestNews}</h1>

            <br>
            <table>
                <c:forEach var="news" items="${requestScope.latest_news}">
                    <tr>
                        <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">
                            <td><a href="Controller?command=view-news&news-id=${news.id}">
                                <h2>${news.titleRu}</h2></a></td>
                        </c:if>
                        <c:if test="${sessionScope.get('language') eq 'en'}">
                            <td><a href="Controller?command=view-news&news-id=${news.id}">
                                <h2>${news.titleEn}</h2></a></td>
                        </c:if>
                        <td><small><fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                                                   value="${news.newsDate}"/></small></td>
                    </tr>
                </c:forEach>

            </table>
        </div>

        <c:import url="template/sideright.jsp"/>

    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
