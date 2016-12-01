package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.Movie;
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
                    "GROUP BY m_id ORDER BY m_rating DESC;";

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
                    "WHERE country.country_en = ? GROUP BY m_id ORDER BY m_rating DESC;";

    private static final String SHOW_BY_GENRE =
            "SELECT m_id, m_title_ru, m_title_en, AVG(rating.rating_score) AS m_rating, COUNT(rating.rating_score) AS m_votes\n" +
                    "FROM movies\n" +
                    "LEFT JOIN rating ON movies.m_id = rating.movies_m_id\n" +
                    "LEFT JOIN genres ON movies.m_id = genres.movies_m_id " +
                    "WHERE genres.genres_genre_en = ? GROUP BY m_id ORDER BY m_rating DESC;";

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

    private static final String M_ID = "m_id";
    private static final String M_TITLE_RU = "m_title_ru";
    private static final String M_TITLE_EN = "m_title_en";
    private static final String M_YEAR = "m_year";
    private static final String M_BUDGET = "m_budget";
    private static final String M_GROSS = "m_gross";

    private static final String M_RATING = "m_rating";
    private static final String M_VOTES = "m_votes";

    @Override
    public List<Movie> fullList() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_ALL);

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
    public List<Movie> showMoviesByCountry(String country) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_BY_COUNTRY);
            st.setString(1, country);
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
    public List<Movie> showMoviesByGenre(String genre) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_BY_GENRE);
            st.setString(1, genre);
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
    public List<Movie> showMoviesOfTenYearsPeriod(int years) throws DAOException {
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
    public List<Movie> showMoviesOfYear(int year) throws DAOException {
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
    public Movie showMovieByID(int id) throws DAOException {
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

}