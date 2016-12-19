package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.bean.entity.Review;
import by.hustlestar.dao.iface.MovieDAO;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.impl.pool.ConnectionPoolSQLDAO;
import by.hustlestar.dao.impl.pool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hustler on 01.11.2016.
 */
public class MovieSQLDAO implements MovieDAO {
    private final static String SHOW_ALL =
            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
                    "FROM movies LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
                    "GROUP BY m_id ORDER BY m_rating DESC LIMIT ?, ?;";

    private final static String SHOW_BY_ID =
            "SELECT m_id, m_title_ru, m_title_en, m_year, m_budget, m_gross, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes FROM movies\n" +
                    "LEFT JOIN rating\n" +
                    "ON movies.m_id=rating.movies_m_id\n" +
                    "WHERE m_id=?;";

    private final static String SHOW_BY_COUNTRY =
            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
                    "FROM movies\n" +
                    "LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
                    "LEFT JOIN country ON movies.m_id = country.movies_m_id\n" +
                    "WHERE country.country_en = ? GROUP BY m_id ORDER BY m_rating DESC LIMIT ?, ?;";

    private static final String SHOW_BY_GENRE =
            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
                    "FROM movies\n" +
                    "LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
                    "LEFT JOIN genres ON movies.m_id = genres.movies_m_id " +
                    "WHERE genres.genres_genre_en = ? GROUP BY m_id ORDER BY m_rating DESC LIMIT ?, ?;";

    private static final String FIND_BY_TITLE =
            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
                    "FROM movies\n" +
                    "LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
                    "WHERE `m_title_en` LIKE ? OR `m_title_ru` LIKE ?;";

    private static final String SHOW_OF_TEN_YEARS_PERIOD =
            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
                    "FROM movies LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
                    "WHERE movies.m_year BETWEEN ? AND ?\n" +
                    "GROUP BY m_id ORDER BY m_rating DESC;";

    private static final String SHOW_OF_YEAR =
            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
                    "FROM movies LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
                    "WHERE movies.m_year=?\n" +
                    "GROUP BY m_id ORDER BY m_rating DESC;";

    private final static String ADD_MOVIE =
            "INSERT INTO movies (m_title_ru, m_title_en, `m_year`, `m_budget`, `m_gross`) VALUES (?, ?, ?, ?, ?)";

    private final static String UPDATE_BY_ID =
            "UPDATE `jackdb`.`movies`\n" +
                    "SET m_title_ru = ?, m_title_en = ?, `m_year` = ?, `m_budget` = ?,`m_gross` = ?\n" +
                    "WHERE `m_id` = ?;\n";

    private static final String MOVIES_FOR_ACTOR =
            "SELECT DISTINCT m_id, m_title_ru, m_title_en, IFNULL(rating.m_rating, 0) AS m_rating, IFNULL(rating.m_votes, 0) AS m_votes\n" +
                    "FROM movies\n" +
                    "LEFT JOIN (\n" +
                    "SELECT movies_m_id, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes FROM rating GROUP BY movies_m_id)\n" +
                    "rating ON (movies.m_id = movies_m_id) \n" +
                    "LEFT JOIN actor_starred ON m_id= actor_starred.movies_m_id\n" +
                    "LEFT JOIN director ON m_id= director.movies_m_id\n" +
                    "WHERE director.actors_a_id = ? OR actor_starred.actors_a_id = ?;";
    private static final String MOVIES_FOR_NEWS =
            "SELECT DISTINCT m_id, m_title_ru, m_title_en\n" +
                    "FROM movies\n" +
                    "LEFT JOIN news_about_movies ON m_id= news_about_movies.movies_m_id\n" +
                    "WHERE news_about_movies.news_n_id = ?;";
    private static final String MOVIES_WITH_LATEST_REVIEWS =
            "SELECT m_id, m_title_ru, m_title_en,\n" +
                    "user_u_nick, review, review_date\n" +
                    "FROM movies\n" +
                    "LEFT JOIN reviews ON m_id= reviews.movies_m_id \n" +
                    "WHERE reviews.review_lang = ?\n" +
                    "ORDER BY reviews.review_date DESC LIMIT 10;";
    private static final String SHOW_LATEST_MOVIES =
            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
                    "FROM movies LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
                    "GROUP BY m_id ORDER BY m_id DESC LIMIT 10";
    private static final String COUNT_ALL_MOVIES =
            "SELECT COUNT(m_id) AS amount FROM movies";
    private static final String COUNT_ALL_MOVIES_BY_COUNTRY =
            "SELECT COUNT(movies_m_id) AS amount FROM country WHERE country_en = ?;";
    private static final String COUNT_ALL_MOVIES_BY_GENRE =
            "SELECT COUNT(movies_m_id) AS amount FROM genres WHERE genres_genre_en = ?;";

    private static final String M_ID = "m_id";
    private static final String M_TITLE_RU = "m_title_ru";
    private static final String M_TITLE_EN = "m_title_en";
    private static final String M_YEAR = "m_year";
    private static final String M_BUDGET = "m_budget";
    private static final String M_GROSS = "m_gross";

    private static final String R_USER_NICK = "user_u_nick";
    private static final String R_REVIEW = "review";
    private static final String R_REVIEW_DATE = "review_date";

    private static final String M_RATING = "m_rating";
    private static final String M_VOTES = "m_votes";

    private static final String AMOUNT = "amount";

    @Override
    public List<Movie> getFullMovieList(int offset, int noOfRecords) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_ALL);
            st.setInt(1, offset);
            st.setInt(2, noOfRecords);

            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitleRu(rs.getString(M_TITLE_RU));
                movie.setTitleEn(rs.getString(M_TITLE_EN));
                movie.setAvgRating(rs.getDouble(M_RATING));
                movie.setRatingVotes(rs.getInt(M_VOTES));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public List<Movie> getMoviesByCountry(String country, int offset, int recordsPerPage) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_BY_COUNTRY);
            st.setString(1, country);
            st.setInt(2, offset);
            st.setInt(3, recordsPerPage);

            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitleRu(rs.getString(M_TITLE_RU));
                movie.setTitleEn(rs.getString(M_TITLE_EN));
                movie.setAvgRating(rs.getDouble(M_RATING));
                movie.setRatingVotes(rs.getInt(M_VOTES));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre, int offset, int recordsPerPage) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_BY_GENRE);
            st.setString(1, genre);
            st.setInt(2, offset);
            st.setInt(3, recordsPerPage);
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitleRu(rs.getString(M_TITLE_RU));
                movie.setTitleEn(rs.getString(M_TITLE_EN));
                movie.setAvgRating(rs.getDouble(M_RATING));
                movie.setRatingVotes(rs.getInt(M_VOTES));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public List<Movie> findMovieByTitle(String title) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(FIND_BY_TITLE);
            st.setString(1, "%" + title + "%");
            st.setString(2, "%" + title + "%");
            //System.out.println("%" + title + "%");
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitleRu(rs.getString(M_TITLE_RU));
                movie.setTitleEn(rs.getString(M_TITLE_EN));
                movie.setAvgRating(rs.getDouble(M_RATING));
                movie.setRatingVotes(rs.getInt(M_VOTES));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public List<Movie> getMoviesOfTenYearsPeriod(int years) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_OF_TEN_YEARS_PERIOD);
            st.setInt(1, years);
            st.setInt(2, years + 9);
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitleRu(rs.getString(M_TITLE_RU));
                movie.setTitleEn(rs.getString(M_TITLE_EN));
                movie.setAvgRating(rs.getDouble(M_RATING));
                movie.setRatingVotes(rs.getInt(M_VOTES));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public List<Movie> getMoviesOfYear(int year) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_OF_YEAR);
            st.setInt(1, year);
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitleRu(rs.getString(M_TITLE_RU));
                movie.setTitleEn(rs.getString(M_TITLE_EN));
                movie.setAvgRating(rs.getDouble(M_RATING));
                movie.setRatingVotes(rs.getInt(M_VOTES));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public Movie getMovieByID(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_BY_ID);
            st.setInt(1, id);
            rs = st.executeQuery();

            Movie movie = null;
            if (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitleRu(rs.getString(M_TITLE_RU));
                movie.setTitleEn(rs.getString(M_TITLE_EN));
                movie.setYear(rs.getInt(M_YEAR));
                movie.setBudget(rs.getLong(M_BUDGET));
                movie.setGross(rs.getLong(M_GROSS));
                movie.setAvgRating(rs.getDouble(M_RATING));
                movie.setRatingVotes(rs.getInt(M_VOTES));
            }
            return movie;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void addMovie(String titleRu, String titleEn, int year, long budget, long gross) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(ADD_MOVIE);
            st.setString(1, titleRu);
            st.setString(2, titleEn);
            st.setInt(3, year);
            st.setLong(4, budget);
            st.setLong(5, gross);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Filmec dobavlen vse ok" + titleRu);
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void updateMovie(int id, String titleRu, String titleEn, int year, long budget, long gross) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_BY_ID);
            st.setString(1, titleRu);
            st.setString(2, titleEn);
            st.setInt(3, year);
            st.setLong(4, budget);
            st.setLong(5, gross);
            st.setInt(6, id);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Filmec obnovlen vse ok" + titleRu);
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public List<Movie> getMoviesForActor(int actorID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(MOVIES_FOR_ACTOR);
            st.setInt(1, actorID);
            st.setInt(2, actorID);
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitleRu(rs.getString(M_TITLE_RU));
                movie.setTitleEn(rs.getString(M_TITLE_EN));
                movie.setAvgRating(rs.getDouble(M_RATING));
                movie.setRatingVotes(rs.getInt(M_VOTES));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public List<Movie> getMoviesForNews(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(MOVIES_FOR_NEWS);
            st.setInt(1, id);
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitleRu(rs.getString(M_TITLE_RU));
                movie.setTitleEn(rs.getString(M_TITLE_EN));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public List<Movie> getMoviesWithLatestReviews(String lang) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(MOVIES_WITH_LATEST_REVIEWS);
            st.setString(1, lang);
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            List<Review> reviewList;
            Review review;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitleRu(rs.getString(M_TITLE_RU));
                movie.setTitleEn(rs.getString(M_TITLE_EN));
                reviewList = new ArrayList<>();
                review = new Review();
                review.setUserNickname(rs.getString(R_USER_NICK));
                review.setReview(rs.getString(R_REVIEW));
                review.setReviewDate(rs.getTimestamp(R_REVIEW_DATE));
                reviewList.add(review);
                movie.setReviews(reviewList);
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public List<Movie> getLatestMovies() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_LATEST_MOVIES);
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitleRu(rs.getString(M_TITLE_RU));
                movie.setTitleEn(rs.getString(M_TITLE_EN));
                movie.setAvgRating(rs.getDouble(M_RATING));
                movie.setRatingVotes(rs.getInt(M_VOTES));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public int countAllMoviesAmount() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(COUNT_ALL_MOVIES);
            int amount = 0;
            rs = st.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(AMOUNT);
            }
            return amount;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public int countMoviesByCountry(String country) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(COUNT_ALL_MOVIES_BY_COUNTRY);
            st.setString(1, country);
            int amount = 0;
            rs = st.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(AMOUNT);
            }
            return amount;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public int countMoviesByGenre(String genre) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(COUNT_ALL_MOVIES_BY_GENRE);
            st.setString(1, genre);
            int amount = 0;
            rs = st.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(AMOUNT);
            }
            return amount;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

}