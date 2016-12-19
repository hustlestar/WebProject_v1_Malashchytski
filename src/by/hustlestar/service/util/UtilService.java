package by.hustlestar.service.util;

import by.hustlestar.bean.entity.*;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.RatingDAO;
import by.hustlestar.dao.iface.ReviewScoreDAO;

import java.util.List;

/**
 * Created by Hustler on 10.11.2016.
 */
public class UtilService {

    private UtilService() {
    }

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
            //System.out.println(review.getReview()+" "+thumbsUp+"/"+thumbsDown);
            review.setThumbsUp(thumbsUp);
            review.setThumbsDown(thumbsDown);
        }
    }

    public static void calculateReputation(List<Review> reviewList, User user) {
        int reputation = 0;
        for (Review review : reviewList) {
            reputation = reputation + review.getThumbsUp() - review.getThumbsDown();
        }
        user.setReputation(reputation);
    }

    public static void fillRatingsForMovie(RatingDAO ratingDAO, List<Movie> movies) throws DAOException {
        List<Rating> ratingList;
        for (Movie movie : movies) {
            ratingList = ratingDAO.getRatingsForMovie(movie.getId());
            movie.setRatings(ratingList);
        }
    }


}
