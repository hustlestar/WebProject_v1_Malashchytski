package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.ReviewScore;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ReviewScoreDAO;
import by.hustlestar.dao.pool.ConnectionPoolSQLDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import by.hustlestar.dao.util.DAOHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hustler on 10.11.2016.
 */
public class ReviewScoreSQLDAO implements ReviewScoreDAO {

    private final static String GET_REVIEW_SCORES_FOR_REVIEW =
            "SELECT * FROM review_score WHERE movies_m_id=? AND review_u_nick=?";

    private static final String LIKE_DISLIKE_REVIEW =
            "INSERT INTO review_score\n" +
            "(movies_m_id, review_u_nick, value, user_u_nick)\n" +
            "VALUES (?, ?, ?, ?) ;";

    //private static final String MOVIES_M_ID = "movies_m_id";
    //private static final String REVIEW_U_NICK = "review_u_nick";
    private static final String SCORE_VALUE = "value";
    private static final String USER_WHO_SCORED_REVIEW = "user_u_nick";

    @Override
    public List<ReviewScore> getReviewScoresForReview(int movieID, String userNickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(GET_REVIEW_SCORES_FOR_REVIEW);
            st.setInt(1, movieID);
            st.setString(2, userNickname);
            rs = st.executeQuery();

            List<ReviewScore> reviewScoreList = new ArrayList<>();
            ReviewScore reviewScore = null;
            while (rs.next()) {
                reviewScore = new ReviewScore();
                reviewScore.setMovieID(movieID);
                reviewScore.setUserNicknameWhoReviewed(userNickname);
                reviewScore.setScore(rs.getInt(SCORE_VALUE));
                reviewScore.setUserNicknameWheScoredReview(rs.getString(USER_WHO_SCORED_REVIEW));
                reviewScoreList.add(reviewScore);
            }
            return reviewScoreList;

        } catch (SQLException e) {
            throw new DAOException("Review score sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review score pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st, rs);
        }
    }

    @Override
    public void likeReview(int intMovieID, String reviewerNickname, int value, String userNickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(LIKE_DISLIKE_REVIEW);
            st.setInt(1, intMovieID);
            st.setString(2, reviewerNickname);
            st.setInt(3, value);
            st.setString(4, userNickname);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Review like dobavlen vse ok");
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }
}
