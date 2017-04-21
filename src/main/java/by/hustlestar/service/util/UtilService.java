package by.hustlestar.service.util;

import by.hustlestar.bean.entity.*;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.RatingDAO;
import by.hustlestar.dao.iface.ReviewScoreDAO;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

/**
 * UtilService is a helper class for some Service implementations.
 */
public class UtilService {

    private UtilService() {
    }

    /**
     * This method is used fill any review with likes and dislikes.
     *
     * @param reviewList list of reviews to be filled
     * @throws DAOException if any error occurred while processing method.
     */
    public static void fillReviewWithScore(List<Review> reviewList) throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ReviewScoreDAO reviewScoreDAO = daoFactory.getReviewScoreDAO();
        for (Review review : reviewList) {
            List<ReviewScore> reviewScoreList =
                    reviewScoreDAO.getReviewScoresForReview(review.getMovieID(), review.getUserNickname());
            review.setReviewScores(reviewScoreList);
            int thumbsUp = 0;
            int thumbsDown = 0;
            for (ReviewScore reviewScore : reviewScoreList) {
                if (reviewScore.getScore() == -1) {
                    thumbsDown = thumbsDown + 1;
                } else if (reviewScore.getScore() == 1) {
                    thumbsUp = thumbsUp + 1;
                }
            }
            review.setThumbsUp(thumbsUp);
            review.setThumbsDown(thumbsDown);
        }
    }

    /**
     * This method is used to calculate user reputation based on his reviews.
     *
     * @param reviewList list of reviews
     * @param user       User bean
     */
    public static void calculateReputation(List<Review> reviewList, User user) {
        int reputation = 0;
        for (Review review : reviewList) {
            reputation = reputation + review.getThumbsUp() - review.getThumbsDown();
        }
        user.setReputation(reputation);
    }

    /**
     * This method is used to fill movies with ratings.
     *
     * @param ratingDAO rating DAO
     * @param movies    list of movies
     * @throws DAOException if any error occurred while processing method.
     */
    public static void fillRatingsForMovie(RatingDAO ratingDAO, List<Movie> movies) throws DAOException {
        List<Rating> ratingList;
        for (Movie movie : movies) {
            ratingList = ratingDAO.getRatingsForMovie(movie.getId());
            movie.setRatings(ratingList);
        }
    }

    /**
     * This method encodes user password in String using md5.
     *
     * @param password of user
     * @return encoded String
     */
    public static String encodePassword(byte[] password) {
        return DigestUtils.md5Hex(password);
    }
}
