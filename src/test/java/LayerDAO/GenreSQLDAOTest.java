package LayerDAO;

import by.hustlestar.bean.entity.Genre;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ConnectionPoolDAO;
import by.hustlestar.dao.iface.GenreDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by dell on 21.12.2016.
 */
public class GenreSQLDAOTest {
    @Test
    public void deleteGenreForMovieTest(){
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        GenreDAO dao = null;
        try{
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getGenreDAO();

            poolDAO.init();
            String nameRu= "Тест";
            String nameEn= "Test";

            int movieId = factory.getMovieDAO().getLastInsertedMovie().getId();
            int sizeBefore = dao.getGenresByMovie(movieId).size();
            dao.addGenreForMovie(movieId, nameRu, nameEn);
            int sizeAfter = dao.getGenresByMovie(movieId).size();
            List<Genre> genres = dao.getGenresByMovie(movieId);
            Genre genre = null;
            for (Genre entry : genres) {
                genre = entry.getNameEn().equals(nameEn) ? entry : null;
            }
            dao.deleteGenreForMovie(movieId, nameEn);
            int sizeFinal = dao.getGenresByMovie(movieId).size();

            assert genre != null;
            Assert.assertNotEquals(sizeBefore, sizeAfter);
            Assert.assertEquals(sizeBefore, sizeFinal);

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
    public void addGenreForMovieTest(){
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        GenreDAO dao = null;
        try{
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getGenreDAO();

            poolDAO.init();
            String nameRu= "Тест";
            String nameEn= "Test";

            int movieId = factory.getMovieDAO().getLastInsertedMovie().getId();
            dao.addGenreForMovie(movieId, nameRu, nameEn);
            List<Genre> countries = dao.getGenresByMovie(movieId);
            Genre genre = null;
            for (Genre entry : countries) {
                genre = entry.getNameEn().equals(nameEn) ? entry : null;
            }

            dao.deleteGenreForMovie(movieId, nameEn);

            assert genre != null;
            Assert.assertEquals(nameRu, genre.getNameRu());
            Assert.assertEquals(nameEn, genre.getNameEn());

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
