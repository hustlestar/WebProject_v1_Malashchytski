package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.ReviewScore;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Hustler on 10.11.2016.
 */
public interface ReviewScoreDAO {

    List<ReviewScore> getReviewScoresForReview(int movieID, String userNickname) throws DAOException;

    void likeReview(int intMovieID, String reviewerNickname, int value, String userNickname) throws DAOException;
}
