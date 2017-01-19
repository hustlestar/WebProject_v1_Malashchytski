package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.ReviewScore;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * ReviewScoreDAO interface represents methods to interact with ReviewScore beans.
 */
public interface ReviewScoreDAO {
    /**
     * This method is used to get all ReviewScores for some review.
     *
     * @param movieID      id of movie
     * @param userNickname reviewer nickname
     * @return list of filled ReviewScore beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<ReviewScore> getReviewScoresForReview(int movieID, String userNickname) throws DAOException;

    /**
     * This method is used to like or dislike some review.
     *
     * @param intMovieID       id of movie
     * @param reviewerNickname nickname of reviewer
     * @param value            like/dislike
     * @param userNickname     nickname of user
     * @throws DAOException if some error occurred while processing data.
     */
    void likeReview(int intMovieID, String reviewerNickname, int value, String userNickname) throws DAOException;
}
