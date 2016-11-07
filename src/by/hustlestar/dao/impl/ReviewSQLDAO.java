package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.Country;
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
    private final static String SHOW_REVIEWS_BY_ID = "SELECT user_u_nick, review FROM reviews WHERE movies_m_id=?";

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
                review.setUserNickname(rs.getString("user_u_nick"));
                review.setReview(rs.getString("review"));
                reviewList.add(review);
            }
            return reviewList;

        } catch (SQLException e) {
            throw new DAOException("Country sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Country pool connection error", e);
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
