package LayerDAO;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.bean.entity.Review;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ConnectionPoolDAO;
import by.hustlestar.dao.iface.ReviewDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by dell on 21.12.2016.
 */
public class ReviewSQLDAOTest {
    @Test
    public void deleteReviewForMovieTest(){
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        ReviewDAO dao = null;
        try{
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getReviewDAO();

            poolDAO.init();
            String userNickname= "test";
            String reviewText= "Test text";
            String lang= "ru";

            List<Movie> movies = factory.getMovieDAO().getMoviesWithLatestReviews(lang);
            int movieId= 0;
            if(movies.size()>0){
                movieId = movies.get(0).getId();
            } else{
                Assert.fail();
            }
            dao.addReview(movieId, userNickname, reviewText, lang);
            int reviewsNumber = dao.countReviewsForMovie(movieId, lang);
            List<Review> reviews = dao.getReviewsForMovie(movieId, lang, 0, reviewsNumber);
            Review review = null;
            for (Review entry : reviews) {
                if (entry.getUserNickname().equals(userNickname)) {
                    review = entry;
                    break;
                }else review = null;
            }
            assert review != null;
            Assert.assertEquals(movieId, review.getMovieID());
            Assert.assertEquals(userNickname, review.getUserNickname());
            Assert.assertEquals(reviewText, review.getReview());

            dao.deleteReview(movieId, userNickname);
            reviews = dao.getReviewsForMovie(movieId, lang, 0, reviewsNumber);
             review = null;
            for (Review entry : reviews) {
                if (entry.getUserNickname().equals(userNickname)) {
                    review = entry;
                    break;
                }else review = null;
            }
            Assert.assertEquals(review, null);

        } catch (ConnectionPoolException | DAOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert poolDAO != null;
                poolDAO.destroy();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void addReviewForMovieTest(){
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        ReviewDAO dao = null;
        try{
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getReviewDAO();

            poolDAO.init();
            String userNickname= "test";
            String reviewText= "Test text";
            String lang= "ru";

            int movieId = factory.getMovieDAO().getLastInsertedMovie().getId();
            dao.addReview(movieId, userNickname, reviewText, lang);
            int reviewsNumber = dao.countReviewsForMovie(movieId, lang);
            List<Review> reviews = dao.getReviewsForMovie(movieId, lang, 0, reviewsNumber);
            Review review = null;
            for (Review entry : reviews) {
                review = entry.getUserNickname().equals(userNickname) ? entry : null;
            }

            dao.deleteReview(movieId, userNickname);

            Assert.assertNotEquals(review, null);
            Assert.assertEquals(movieId, review.getMovieID());
            Assert.assertEquals(userNickname, review.getUserNickname());
            Assert.assertEquals(reviewText, review.getReview());

        } catch (ConnectionPoolException | DAOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert poolDAO != null;
                poolDAO.destroy();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
    }
}
