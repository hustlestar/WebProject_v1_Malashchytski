<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<div class="container-fluid text-center flex">
    <div class="row content">
        <div class="col-sm-2 sidenav">
            <p><a href="#">Link</a></p>
            <p><a href="#">Link</a></p>
            <p><a href="#">Link</a></p>
        </div>
        <div class="col-sm-8 text-left">
            <h1>Welcome</h1>

            <div class="selectboxes">
                <div class="sortAwaits" style="width: 391px; float: left; margin: 0">
                    <span>Лучшие фильмы:</span>
                    <select onchange="MakeSort(this);">
                        <option value="Controller?command=all-movies">-</option>
                        <option selected="" value="Controller?command=all-movies">все</option>
                        <option value="Controller?command=movies-by-genre&genre=crime">криминал</option>
                        <option value="Controller?command=movies-by-genre&genre=adventure">приключения</option>
                        <option value="Controller?command=movies-by-genre&genre=biography">биографии</option>
                        <option value="Controller?command=movies-by-genre&genre=thriller">триллеры</option>
                        <option value="Controller?command=movies-by-genre&genre=comedy">комедии</option>
                        <option value="Controller?command=movies-by-genre&genre=drama">драмы</option>
                        <option value="Controller?command=movies-by-genre&genre=melodrama">мелодрамы</option>
                        <option value="Controller?command=movies-by-genre&genre=fantasy">фантастика</option>
                    </select>
                    <br>
                    <span>По десятилетиям:</span>
                    <select class="sortStat" onchange="MakeSort(this);">
                        <option value="Controller?command=all-movies">-</option>
                        <option value="Controller?command=movies-of-ten-year-period&years=1980">80-е</option>
                        <option value="Controller?command=movies-of-ten-year-period&years=1990">90-е</option>
                        <option value="Controller?command=movies-of-ten-year-period&years=2000">2000-е</option>
                        <option value="Controller?command=movies-of-ten-year-period&years=2010">2010-е</option>
                    </select>
                    <br>
                    <span>Фильмы года:</span>
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
                <div class="sortAwaits" style="float: left; margin: 0">
                    <span>Худшие фильмы:</span>
                    <select class="badMovie" onchange="MakeSort(this);">
                        <option value="Controller?command=all-movies">-</option>
                        <option value="/top/asc/1/">все</option>
                        <option value="/top/rus/list/asc/1/">Россия + СССР</option>
                        <option value="/top/serial/list/asc/1/">сериалы</option>
                        <option value="/top/id_genre/3/asc/1/">боевики</option>
                        <option value="/top/id_genre/4/asc/1/">триллеры</option>
                        <option value="/top/id_genre/6/asc/1/">комедии</option>
                        <option value="/top/id_genre/8/asc/1/">драмы</option>
                        <option value="/top/id_genre/7/asc/1/">мелодрамы</option>
                        <option value="/top/id_genre/1/asc/1/">ужасы</option>
                        <option value="/top/id_genre/2/asc/1/">фантастика</option>
                        <option value="/top/id_genre/12/asc/1/">документальные</option>
                        <option value="/top/id_genre/14/asc/1/">мультфильмы</option>
                        <option value="/top/mult_serial/list/asc/1/">мультсериалы</option>
                        <option value="/top/sex/male/asc/1/">мужские</option>
                        <option value="/top/sex/female/asc/1/">женские</option>
                    </select>
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
                $(function () {
                    var isbadMovie = $('.badMovie option:selected').text() !== '-';
                    if (isbadMovie) {
                        $('.sortStat').prop('disabled', true);
                    }
                });
            </script>
            <br>
            <table border="1">
                <c:forEach var="movie" items="${requestScope.all_movies}">
                    <tr>
                        <c:if test="${sessionScope.get('language') eq 'ru'}"><td><a href="Controller?command=movie-by-id&id=${movie.id}"><c:out
                                value="${movie.titleRu}"/></a></td></c:if>
                        <c:if test="${sessionScope.get('language') ne 'ru'}">
                            <td><a href="Controller?command=movie-by-id&id=${movie.id}"><c:out
                                    value="${movie.titleEn}"/></a></td>
                        </c:if>
                        <td><c:forEach var="rating" items="${sessionScope.get('user').ratings}">
                            <c:if test="${rating.movieID==movie.id}">
                                <c:out value="${rating.ratingScore}"/>
                            </c:if></c:forEach>
                        </td>
                        <td><div style="width: 74px">
                            <div style="display: block; position: relative" class="item" title="" id="rating_user_329"><div title="поставить оценку" class="show_vote_329 star" onclick="$(&quot;.rateNow&quot;).hide(); VotesLock=setTimeout(function(){VotesLock=false;},300); $(this).parents(&quot;.item&quot;).find(&quot;.rateNow[mid=329]&quot;).fadeIn();"></div><div class="rateNow rateNow329" mid="329" vote="" style="display: none;"><div class="rateNowItem seen " title="добавить пометку о просмотре" onclick="miniSeen(this)"><div class="text">просмотрен</div><div class="eye"></div></div><div class="rateNowItem " title="поставить оценку" onclick="miniVote(this,10, '9ef6ba277ffccb48e711a2fc64e28316' )"><div class="stars stars10"></div><div class="voteNum">10</div></div><div class="rateNowItem " title="поставить оценку" onclick="miniVote(this,9, '9ef6ba277ffccb48e711a2fc64e28316' )"><div class="stars stars9"></div><div class="voteNum">9</div></div><div class="rateNowItem " title="поставить оценку" onclick="miniVote(this,8, '9ef6ba277ffccb48e711a2fc64e28316' )"><div class="stars stars8"></div><div class="voteNum">8</div></div><div class="rateNowItem " title="поставить оценку" onclick="miniVote(this,7, '9ef6ba277ffccb48e711a2fc64e28316' )"><div class="stars stars7"></div><div class="voteNum">7</div></div><div class="rateNowItem " title="поставить оценку" onclick="miniVote(this,6, '9ef6ba277ffccb48e711a2fc64e28316' )"><div class="stars stars6"></div><div class="voteNum">6</div></div><div class="rateNowItem " title="поставить оценку" onclick="miniVote(this,5, '9ef6ba277ffccb48e711a2fc64e28316' )"><div class="stars stars5"></div><div class="voteNum">5</div></div><div class="rateNowItem " title="поставить оценку" onclick="miniVote(this,4, '9ef6ba277ffccb48e711a2fc64e28316' )"><div class="stars stars4"></div><div class="voteNum">4</div></div><div class="rateNowItem " title="поставить оценку" onclick="miniVote(this,3, '9ef6ba277ffccb48e711a2fc64e28316' )"><div class="stars stars3"></div><div class="voteNum">3</div></div><div class="rateNowItem " title="поставить оценку" onclick="miniVote(this,2, '9ef6ba277ffccb48e711a2fc64e28316' )"><div class="stars stars2"></div><div class="voteNum">2</div></div><div class="rateNowItem " title="поставить оценку" onclick="miniVote(this,1, '9ef6ba277ffccb48e711a2fc64e28316' )"><div class="stars stars1"></div><div class="voteNum">1</div></div><div class="rateNowArrow"></div></div></div>
                            <div class="MyKP_Folder_Select shortestselect MyKP_Folder_329 already_inited" type="film" mid="329" alt=""><div class="select" fid="select_329" title="добавить в Мои фильмы"><span class="title" onclick="Folders.ClickFolders(this)"></span><div class="list_div"></div></div><div class="MyKP_Folder_Select_dot"></div></div>
                        </div></td>
                        <td><c:out value="${movie.avgRating}"/>(<c:out value="${movie.ratingVotes}"/>)</td>
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
