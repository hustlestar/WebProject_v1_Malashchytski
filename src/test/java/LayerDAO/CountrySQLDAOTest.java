package LayerDAO;

import by.hustlestar.bean.entity.Country;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ConnectionPoolDAO;
import by.hustlestar.dao.iface.CountryDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by dell on 20.12.2016.
 */
public class CountrySQLDAOTest {
    @Test
    public void deleteCountryForMovieTest(){
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        CountryDAO dao = null;
        try{
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getCountryDAO();

            poolDAO.init();
            String nameRu= "Тест";
            String nameEn= "Test";

            int movieId = factory.getMovieDAO().getLastInsertedMovie().getId();
            int sizeBefore = dao.getCountriesByMovie(movieId).size();
            dao.addCountryForMovie(movieId, nameRu, nameEn);
            int sizeAfter = dao.getCountriesByMovie(movieId).size();
            List<Country> countries = dao.getCountriesByMovie(movieId);
            Country country = null;
            for (Country entry : countries) {
                country = entry.getNameEn().equals(nameEn) ? entry : null;
            }
            dao.deleteCountryForMovie(movieId, nameEn);
            int sizeFinal = dao.getCountriesByMovie(movieId).size();

            assert country != null;
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
    public void addCountryForMovieTest(){
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        CountryDAO dao = null;
        try{
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getCountryDAO();

            poolDAO.init();
            String nameRu= "Тест";
            String nameEn= "Test";

            int movieId = factory.getMovieDAO().getLastInsertedMovie().getId();
            dao.addCountryForMovie(movieId, nameRu, nameEn);
            List<Country> countries = dao.getCountriesByMovie(movieId);
            Country country = null;
            for (Country entry : countries) {
                country = entry.getNameEn().equals(nameEn) ? entry : null;
            }

            dao.deleteCountryForMovie(movieId, nameEn);

            assert country != null;
            Assert.assertEquals(nameRu, country.getNameRu());
            Assert.assertEquals(nameEn, country.getNameEn());

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
