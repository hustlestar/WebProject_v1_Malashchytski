<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.topRatedMovies" var="topRatedMovies"/>
<fmt:message bundle="${locale}" key="locale.bestMoviesForYou" var="bestMoviesForYou"/>
<fmt:message bundle="${locale}" key="locale.theBestMovies" var="theBestMovies"/>
<fmt:message bundle="${locale}" key="locale.all" var="all"/>
<fmt:message bundle="${locale}" key="locale.adventure" var="adventure"/>
<fmt:message bundle="${locale}" key="locale.crime" var="crime"/>
<fmt:message bundle="${locale}" key="locale.biography" var="biography"/>
<fmt:message bundle="${locale}" key="locale.thriller" var="thriller"/>
<fmt:message bundle="${locale}" key="locale.comedy" var="comedy"/>
<fmt:message bundle="${locale}" key="locale.melodrama" var="melodrama"/>
<fmt:message bundle="${locale}" key="locale.drama" var="drama"/>
<fmt:message bundle="${locale}" key="locale.fantasy" var="fantasy"/>
<fmt:message bundle="${locale}" key="locale.byDecades" var="byDecades"/>
<fmt:message bundle="${locale}" key="locale.byYear" var="byYear"/>
<fmt:message bundle="${locale}" key="locale.eightieth" var="eightieth"/>
<fmt:message bundle="${locale}" key="locale.ninetieth" var="ninetieth"/>
<fmt:message bundle="${locale}" key="locale.thousandth" var="thousandth"/>
<fmt:message bundle="${locale}" key="locale.tenth" var="tenth"/>
<fmt:message bundle="${locale}" key="locale.movie" var="movie"/>
<fmt:message bundle="${locale}" key="locale.yourRating" var="yourRating"/>
<fmt:message bundle="${locale}" key="locale.ratingVotes" var="ratingVotes"/>
<fmt:message bundle="${locale}" key="locale.next" var="next"/>
<fmt:message bundle="${locale}" key="locale.previous" var="previous"/>

<!DOCTYPE html>
<html>
<head>
    <title>${topRatedMovies}</title>
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
        document.getElementById("movies-page").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/sideleft.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <h1>${bestMoviesForYou}</h1>

            <div class="selectboxes">
                <div class="sortAwaits" style="width: 391px; float: left; margin: 0">
                    <span>${theBestMovies}</span>
                    <select onchange="MakeSort(this);">
                        <option value="Controller?command=all-movies">-</option>
                        <option selected="" value="Controller?command=all-movies">${all}</option>
                        <option value="Controller?command=movies-by-genre&genre=crime">${crime}</option>
                        <option value="Controller?command=movies-by-genre&genre=adventure">${adventure}</option>
                        <option value="Controller?command=movies-by-genre&genre=biography">${biography}</option>
                        <option value="Controller?command=movies-by-genre&genre=thriller">${thriller}</option>
                        <option value="Controller?command=movies-by-genre&genre=comedy">${comedy}</option>
                        <option value="Controller?command=movies-by-genre&genre=drama">${drama}</option>
                        <option value="Controller?command=movies-by-genre&genre=melodrama">${melodrama}</option>
                        <option value="Controller?command=movies-by-genre&genre=fantasy">${fantasy}</option>
                    </select>
                    <br>
                    <span>${byDecades}</span>
                    <select class="sortStat" onchange="MakeSort(this);">
                        <option value="Controller?command=all-movies">-</option>
                        <option value="Controller?command=movies-of-ten-year-period&years=1980">${eightieth}</option>
                        <option value="Controller?command=movies-of-ten-year-period&years=1990">${ninetieth}</option>
                        <option value="Controller?command=movies-of-ten-year-period&years=2000">${thousandth}</option>
                        <option value="Controller?command=movies-of-ten-year-period&years=2010">${tenth}</option>
                    </select>
                    <br>
                    <span>${byYear}</span>
                    <select class="sortStat" name="y" onchange="MakeSort(this);">
                        <option value="Controller?command=all-movies">-</option>
                        <option value="Controller?command=movies-of-year&year=2016">2016</option>
                        <option value="Controller?command=movies-of-year&year=2015">2015</option>
                        <option value="Controller?command=movies-of-year&year=2014">2014</option>
                        <option value="Controller?command=movies-of-year&year=2013">2013</option>
                        <option value="Controller?command=movies-of-year&year=2012">2012</option>
                        <option value="Controller?command=movies-of-year&year=2011">2011</option>
                        <option value="Controller?command=movies-of-year&year=2010">2010</option>
                        <option value="Controller?command=movies-of-year&year=2009">2009</option>
                        <option value="Controller?command=movies-of-year&year=2008">2008</option>
                        <option value="Controller?command=movies-of-year&year=2007">2007</option>
                        <option value="Controller?command=movies-of-year&year=2006">2006</option>
                        <option value="Controller?command=movies-of-year&year=2005">2005</option>
                        <option value="Controller?command=movies-of-year&year=2004">2004</option>
                        <option value="Controller?command=movies-of-year&year=2003">2003</option>
                        <option value="Controller?command=movies-of-year&year=2002">2002</option>
                        <option value="Controller?command=movies-of-year&year=2001">2001</option>
                        <option value="Controller?command=movies-of-year&year=2000">2000</option>
                        <option value="Controller?command=movies-of-year&year=1999">1999</option>
                        <option value="Controller?command=movies-of-year&year=1998">1998</option>
                        <option value="Controller?command=movies-of-year&year=1997">1997</option>
                        <option value="Controller?command=movies-of-year&year=1996">1996</option>
                        <option value="Controller?command=movies-of-year&year=1995">1995</option>
                        <option value="Controller?command=movies-of-year&year=1994">1994</option>
                        <option value="Controller?command=movies-of-year&year=1993">1993</option>
                        <option value="Controller?command=movies-of-year&year=1992">1992</option>
                        <option value="Controller?command=movies-of-year&year=1991">1991</option>
                        <option value="Controller?command=movies-of-year&year=1990">1990</option>
                        <option value="Controller?command=movies-of-year&year=1989">1989</option>
                        <option value="Controller?command=movies-of-year&year=1988">1988</option>
                        <option value="Controller?command=movies-of-year&year=1987">1987</option>
                        <option value="Controller?command=movies-of-year&year=1986">1986</option>
                        <option value="Controller?command=movies-of-year&year=1985">1985</option>
                        <option value="Controller?command=movies-of-year&year=1984">1984</option>
                        <option value="Controller?command=movies-of-year&year=1983">1983</option>
                        <option value="Controller?command=movies-of-year&year=1982">1982</option>
                        <option value="Controller?command=movies-of-year&year=1981">1981</option>
                        <option value="Controller?command=movies-of-year&year=1980">1980</option>
                    </select>
                    <br>
                </div>
                <div class="clear"></div>
            </div>
            <script type="text/javascript">
                function MakeSort(element) {
                    var selected = $('option:selected', element),
                        href = selected.val();
                    if (/*selected.text() === '-' || */!href) {
                        return false;
                    }
                    document.location = href;
                }
            </script>
            <br>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>${movie}</th>
                    <th>${yourRating}</th>
                    <th>${ratingVotes}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="movie" items="${requestScope.all_movies}">
                    <tr>
                        <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">
                            <td><a href="Controller?command=movie-by-id&id=${movie.id}">
                                <c:out value="${movie.titleRu}"/></a></td>
                        </c:if>
                        <c:if test="${sessionScope.get('language') eq 'en'}">
                            <td><a href="Controller?command=movie-by-id&id=${movie.id}">
                                <c:out value="${movie.titleEn}"/></a></td>
                        </c:if>
                        <td>
                            <c:forEach var="rating" items="${movie.ratings}">
                                <c:if test="${rating.userNickname eq sessionScope.get('user').nickname}">
                                    <c:out value="${rating.ratingScore}"/>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${movie.avgRating}" />
                            <small>(<c:out value="${movie.ratingVotes}"/>)</small>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>


            <%--For displaying Previous link except for the 1st page --%>
            <ul class="pagination">
                <c:if test="${requestScope.currentPage > 1}">
                    <li>
                        <a href="Controller?command=${param.command}&page=${requestScope.currentPage - 1}&country=${param.country}&genre=${param.genre}">${previous}</a>
                    </li>
                </c:if>

                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <c:if test="${requestScope.noOfPages>1}">
                    <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${requestScope.currentPage eq i}">
                                <li class="active"><a href="#">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="Controller?command=${param.command}&page=${i}&country=${param.country}&genre=${param.genre}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>

                <%--For displaying Next link --%>
                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                    <li>
                        <a href="Controller?command=${param.command}&page=${requestScope.currentPage + 1}&country=${param.country}&genre=${param.genre}">${next}</a>
                    </li>
                </c:if>
            </ul>
        </div>

        <c:import url="template/sideright.jsp"/>

    </div>
</div>

<c:import url="template/footer.jsp"/>

</body>
</html>
