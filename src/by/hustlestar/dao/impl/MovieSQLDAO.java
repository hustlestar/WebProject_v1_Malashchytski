package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.dao.iface.MovieDAO;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.impl.pool.ConnectionPool;
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
    private final static String SHOW_ALL = "SELECT * FROM movies";
    private final static String SHOW_BY_ID = "SELECT * FROM movies WHERE m_id=?";
    private final static String SHOW_BY_COUNTRY = "SELECT movies.m_title, movies.m_year FROM movies\n" +
            "INNER JOIN  country ON movies.m_id=country.movies_m_id\n WHERE country=?";
    private static final String SHOW_BY_GENRE = "SELECT movies.m_title, movies.m_year FROM movies\n" +
            "INNER JOIN  genres ON movies.m_id=genres.movies_m_id WHERE genre=?";

    private final static String ADD_MOVIE = "INSERT INTO movies (`m_title`, `m_year`, `m_budget`, `m_gross`) VALUES (?, ?, ?, ?)";


    private final static String UPDATE_BY_ID = "UPDATE `jackdb`.`movies`\n" +
            "SET `m_title` = ?, `m_year` = ?, `m_budget` = ?,`m_gross` = ?\n" +
            "WHERE `m_id` = ?;\n";

    private static final String M_ID = "m_id";
    private static final String M_TITLE = "m_title";
    private static final String M_YEAR = "m_year";
    private static final String M_BUDGET = "m_budget";
    private static final String M_GROSS = "m_gross";

    @Override
    public List<Movie> fullList() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_ALL);

            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitle(rs.getString(M_TITLE));
                movie.setYear(rs.getInt(M_YEAR));
                movie.setBudget(rs.getLong(M_BUDGET));
                movie.setGross(rs.getLong(M_GROSS));
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
                ConnectionPool.getInstance().returnConnection(con);
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
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_BY_COUNTRY);
            st.setString(1, country);
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitle(rs.getString(M_TITLE));
                movie.setYear(rs.getInt(M_YEAR));
                movie.setBudget(rs.getLong(M_BUDGET));
                movie.setGross(rs.getLong(M_GROSS));
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
                ConnectionPool.getInstance().returnConnection(con);
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
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_BY_GENRE);
            st.setString(1, genre);
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitle(rs.getString(M_TITLE));
                movie.setYear(rs.getInt(M_YEAR));
                movie.setBudget(rs.getLong(M_BUDGET));
                movie.setGross(rs.getLong(M_GROSS));
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
                ConnectionPool.getInstance().returnConnection(con);
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
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_BY_ID);
            st.setInt(1, id);
            rs = st.executeQuery();

            Movie movie = null;
            if (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(M_ID));
                movie.setTitle(rs.getString(M_TITLE));
                movie.setYear(rs.getInt(M_YEAR));
                movie.setBudget(rs.getLong(M_BUDGET));
                movie.setGross(rs.getLong(M_GROSS));
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
                ConnectionPool.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void addMovie(String title, int year, long budget, long gross) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(ADD_MOVIE);
            st.setString(1, title);
            st.setInt(2, year);
            st.setLong(3, budget);
            st.setLong(4, gross);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Filmec dobavlen vse ok" + title);
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
                ConnectionPool.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void updateMovie(int id, String title, int year, long budget, long gross) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_BY_ID);
            st.setString(1, title);
            st.setInt(2, year);
            st.setLong(3, budget);
            st.setLong(4, gross);
            st.setInt(5, id);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Filmec obnovlen vse ok" + title);
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
                ConnectionPool.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

}