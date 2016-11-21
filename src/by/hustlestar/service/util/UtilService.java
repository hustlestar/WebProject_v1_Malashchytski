package by.hustlestar.service.util;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.bean.entity.Rating;
import by.hustlestar.bean.entity.Review;
import by.hustlestar.bean.entity.ReviewScore;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ReviewScoreDAO;

import java.util.List;

/**
 * Created by Hustler on 10.11.2016.
 */
public class UtilService {

    private static final UtilService instance = new UtilService();

    public static UtilService getInstance() {
        return instance;
    }

    public void fillReview(List<Review> reviewList) throws DAOException {
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

}
