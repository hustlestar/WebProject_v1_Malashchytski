package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.Review;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ReviewDAO;
import by.hustlestar.dao.impl.pool.ConnectionPool;
import by.hustlestar.dao.impl.pool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hustler on 07.11.2016.
 */
public class ReviewSQLDAO implements ReviewDAO {
    private final static String SHOW_REVIEWS_BY_ID =
            "SELECT user_u_nick, review FROM reviews WHERE movies_m_id=?";

    private static final String SHOW_REVIEWS_BY_USER =
            "SELECT movies_m_id, review FROM reviews WHERE user_u_nick=?";

    private static final String ADD_REVIEW =
            "INSERT INTO reviews (movies_m_id, user_u_nick, review) VALUES (?, ?, ?)";

    private static final String DELETE_REVIEW =
            "DELETE FROM `jackdb`.`reviews`\n" +
                    "WHERE movies_m_id=? AND user_u_nick=?;";

    private static final String REVIEW = "review";
    private static final String USER_U_NICK = "user_u_nick";
    private static final String MOVIES_M_ID = "movies_m_id";

    @Override
    public List<Review> getReviewsForMovie(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_REVIEWS_BY_ID);
            st.setInt(1, id);
            rs = st.executeQuery();

            List<Review> reviewList = new ArrayList<>();
            Review review = null;
            while (rs.next()) {
                review = new Review();
                review.setMovieID(id);
                review.setUserNickname(rs.getString(USER_U_NICK));
                review.setReview(rs.getString(REVIEW));
                reviewList.add(review);
            }
            return reviewList;

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
                ConnectionPool.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public List<Review> getReviewsForUser(String nickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_REVIEWS_BY_USER);
            st.setString(1, nickname);
            rs = st.executeQuery();

            List<Review> reviewList = new ArrayList<>();
            Review review = null;
            while (rs.next()) {
                review = new Review();
                review.setMovieID(rs.getInt(MOVIES_M_ID));
                review.setUserNickname(nickname);
                review.setReview(rs.getString(REVIEW));
                reviewList.add(review);
            }
            return reviewList;

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
                ConnectionPool.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void addReview(int intMovieID, String userNickname, String review) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(ADD_REVIEW);
            st.setInt(1, intMovieID);
            st.setString(2, userNickname);
            st.setString(3, review);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Review dobavlen vse ok"+userNickname+" "+review);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Review sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error", e);
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
    public void deleteReview(int intMovieID, String userNickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_REVIEW);
            st.setInt(1, intMovieID);
            st.setString(2, userNickname);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Review udalen vse ok "+userNickname);
                return;
            }
            throw new DAOException("Wrong review data");
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
