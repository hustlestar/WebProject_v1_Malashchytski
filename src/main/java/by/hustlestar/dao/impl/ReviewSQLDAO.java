package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.Review;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ReviewDAO;
import by.hustlestar.dao.pool.ConnectionPoolSQLDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import by.hustlestar.dao.util.DAOHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ReviewSQLDAO is an implementation of ReviewDAO for MySQL.
 */
public class ReviewSQLDAO implements ReviewDAO {
    private final static String SHOW_REVIEWS_BY_ID =
            "SELECT user_u_nick, review, review_date, u_image FROM reviews\n" +
                    "LEFT JOIN user ON user_u_nick=user.u_nick WHERE movies_m_id=? AND review_lang=?\n" +
                    "LIMIT ?, ?";

    private static final String SHOW_REVIEWS_BY_USER =
            "SELECT movies_m_id, review, review_date FROM reviews WHERE user_u_nick=?";

    private static final String ADD_REVIEW =
            "INSERT INTO reviews (movies_m_id, user_u_nick, review, review_lang, review_date) VALUES (?, ?, ?, ?, ?)";

    private static final String DELETE_REVIEW =
            "DELETE FROM `jackdb`.`reviews`\n" +
                    "WHERE movies_m_id=? AND user_u_nick=?;";

    private static final String COUNT_ALL_REVIEWS_FOR_MOVIE =
            "SELECT COUNT(movies_m_id) AS amount FROM reviews WHERE movies_m_id=? AND review_lang=?";

    private static final String REVIEW = "review";
    private static final String USER_U_NICK = "user_u_nick";
    private static final String MOVIES_M_ID = "movies_m_id";
    private static final String REVIEW_DATE = "review_date";
    private static final String U_IMAGE = "u_image";

    private static final String AMOUNT = "amount";
    private static final ReviewDAO instance = new ReviewSQLDAO();

    private ReviewSQLDAO() {
    }

    public static ReviewDAO getInstance() {
        return instance;
    }

    /**
     * This method is used to get reviews for some movie from data source
     *
     * @param id             of movie
     * @param lang           language of review
     * @param offset         of first entry
     * @param recordsPerPage records to get
     * @return list of filled Review beans
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public List<Review> getReviewsForMovie(int id, String lang, int offset, int recordsPerPage) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_REVIEWS_BY_ID);
            st.setInt(1, id);
            st.setString(2, lang);
            st.setInt(3, offset);
            st.setInt(4, recordsPerPage);

            rs = st.executeQuery();

            List<Review> reviewList = new ArrayList<>();
            Review review = null;
            while (rs.next()) {
                review = new Review();
                review.setMovieID(id);
                review.setUserNickname(rs.getString(USER_U_NICK));
                review.setReview(rs.getString(REVIEW));
                review.setReviewDate(rs.getTimestamp(REVIEW_DATE));
                review.setImage(rs.getString(U_IMAGE));
                reviewList.add(review);
            }
            return reviewList;

        } catch (SQLException e) {
            throw new DAOException("Review sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to get reviews for some particular user from data source
     *
     * @param nickname of user
     * @return list of filled Review beans
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public List<Review> getReviewsForUser(String nickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

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
                review.setReviewDate(rs.getTimestamp(REVIEW_DATE));
                reviewList.add(review);
            }
            return reviewList;

        } catch (SQLException e) {
            throw new DAOException("Review sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to add review of some user to data source.
     *
     * @param intMovieID   movie id
     * @param userNickname nickname of user
     * @param review       review text
     * @param lang         language of review
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void addReview(int intMovieID, String userNickname, String review, String lang) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(ADD_REVIEW);
            st.setInt(1, intMovieID);
            st.setString(2, userNickname);
            st.setString(3, review);
            st.setString(4, lang);
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("Review dobavlen vse ok" + userNickname + " " + review);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Review sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }

    /**
     * This method is used to remove review from data source.
     *
     * @param intMovieID   movie id
     * @param userNickname nickname of reviewer
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void deleteReview(int intMovieID, String userNickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_REVIEW);
            st.setInt(1, intMovieID);
            st.setString(2, userNickname);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("Review udalen vse ok " + userNickname);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }

    /**
     * This method is used to count number of reviews for a particular movie.
     *
     * @param normId id of movie
     * @param lang   language of reviews
     * @return number of reviews
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public int countReviewsForMovie(int normId, String lang) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(COUNT_ALL_REVIEWS_FOR_MOVIE);
            st.setInt(1, normId);
            st.setString(2, lang);
            int amount = 0;
            rs = st.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(AMOUNT);
            }
            return amount;

        } catch (SQLException e) {
            throw new DAOException("Review sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st, rs);
        }
    }
}
