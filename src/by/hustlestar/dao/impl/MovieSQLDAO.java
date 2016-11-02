package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.dao.MovieDAO;
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

    @Override
    public List<Movie> fullList() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_ALL);

            rs = st.executeQuery();

            if (!rs.next()) {
                return null;
            }
            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setTitle(rs.getString("m_title"));
                movie.setYear(rs.getInt("m_year"));
                movie.setBudget(rs.getLong("m_budget"));
                movie.setGross(rs.getLong("m_gross"));
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
}
