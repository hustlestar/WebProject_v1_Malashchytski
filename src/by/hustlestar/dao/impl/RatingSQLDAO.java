package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.Rating;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.RatingDAO;
import by.hustlestar.dao.impl.pool.ConnectionPoolSQLDAO;
import by.hustlestar.dao.impl.pool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hustler on 15.11.2016.
 */
public class RatingSQLDAO implements RatingDAO {

    private final static String SHOW_RATINGS_BY_ID =
            "SELECT user_u_nick, rating_score FROM rating WHERE movies_m_id=?";
    private static final String SHOW_RATINGS_OF_USER =
            "SELECT movies_m_id, user_u_nick, rating_score FROM rating WHERE user_u_nick=?";
    private static final String CHECK_RATING =
            "SELECT user_u_nick, rating_score FROM rating WHERE movies_m_id=? AND user_u_nick=?";
    private static final String ADD_RATING =
            "INSERT INTO rating (movies_m_id, user_u_nick, rating_score) VALUES (?, ?, ?)";
    private final static String UPDATE_RATING =
            "UPDATE rating SET rating_score=? WHERE movies_m_id=? AND user_u_nick=?;";

    private static final String USER_U_NICK = "user_u_nick";
    private static final String MOVIES_M_ID = "movies_m_id";
    private static final String RATING_SCORE = "rating_score";


    @Override
    public List<Rating> getRatingsForMovie(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_RATINGS_BY_ID);
            st.setInt(1, id);
            rs = st.executeQuery();

            List<Rating> ratingList = new ArrayList<>();
            Rating rating = null;
            while (rs.next()) {
                rating = new Rating();
                rating.setMovieID(id);
                rating.setUserNickname(rs.getString(USER_U_NICK));
                rating.setRatingScore(rs.getInt(RATING_SCORE));
                ratingList.add(rating);
            }
            return ratingList;

        } catch (SQLException e) {
            throw new DAOException("Rating sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Rating pool connection error", e);
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
    public List<Rating> getRatingsOfUser(String nickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_RATINGS_OF_USER);
            st.setString(1, nickname);
            rs = st.executeQuery();

            List<Rating> ratingList = new ArrayList<>();
            Rating rating = null;
            while (rs.next()) {
                rating = new Rating();
                rating.setMovieID(rs.getInt(MOVIES_M_ID));
                rating.setUserNickname(nickname);
                rating.setRatingScore(rs.getInt(RATING_SCORE));
                ratingList.add(rating);
            }
            return ratingList;

        } catch (SQLException e) {
            throw new DAOException("Review sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error", e);
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
    public Rating checkRating(int intMovieID, String userNickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(CHECK_RATING);
            st.setInt(1, intMovieID);
            st.setString(2, userNickname);
            rs = st.executeQuery();

            Rating rating = null;
            if (rs.next()) {
                rating = new Rating();
            }
            return rating;

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
    public void addRating(int intMovieID, String userNickname, int rating) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(ADD_RATING);
            st.setInt(1, intMovieID);
            st.setString(2, userNickname);
            st.setInt(3, rating);
            int update = st.executeUpdate();
            if (update > 0) {
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
    public void updateRating(int intMovieID, String userNickname, int intRating) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_RATING);
            st.setInt(1, intRating);
            st.setInt(2, intMovieID);
            st.setString(3, userNickname);
            int update = st.executeUpdate();
            if (update > 0) {
                return;
            }
            throw new DAOException("Wrong rating data");
        } catch (SQLException e) {
            throw new DAOException("Rating sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Rating pool connection error", e);
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
