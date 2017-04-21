package LayerDAO;

import by.hustlestar.bean.entity.Rating;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ConnectionPoolDAO;
import by.hustlestar.dao.iface.RatingDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dell on 26.12.2016.
 */
public class RatingSQLDAOTest {
    @Test
    public void addRatingTest(){
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        RatingDAO dao = null;
        try{
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getRatingDAO();

            poolDAO.init();

            String userNickname= "test";
            int movieID;
            int score= 10;

            movieID = factory.getMovieDAO().getLastInsertedMovie().getId();
            Rating rating = dao.checkRating(movieID, userNickname);
            Assert.assertEquals(rating, null);
            dao.addRating(movieID, userNickname, score);
            rating = dao.checkRating(movieID, userNickname);
            Assert.assertNotEquals(rating, null);

            dao.deleteRating(movieID, userNickname);

            Assert.assertEquals(userNickname, rating.getUserNickname());
            Assert.assertEquals(movieID, rating.getMovieID());
            Assert.assertEquals(score, rating.getRatingScore());

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
