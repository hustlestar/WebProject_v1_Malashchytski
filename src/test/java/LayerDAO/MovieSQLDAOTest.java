package LayerDAO;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ConnectionPoolDAO;
import by.hustlestar.dao.iface.MovieDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by dell on 20.12.2016.
 */
public class MovieSQLDAOTest {
    @Test
    public void addMovieTest(){
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        MovieDAO dao = null;
        try{
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getMovieDAO();

            poolDAO.init();
            String titleRu= "Тест";
            String titleEn= "Test";
            int year= 1999;
            long budget = 1000_000;
            long gross = 10_000_000;

            dao.addMovie(titleRu, titleEn, year, budget, gross);
            Movie movie = dao.getLastInsertedMovie();
            int id = movie.getId();

            dao.deleteMovie(id);

            Assert.assertEquals(titleRu, movie.getTitleRu());
            Assert.assertEquals(titleEn, movie.getTitleEn());
            Assert.assertEquals(year, movie.getYear());
            Assert.assertEquals(budget, movie.getBudget());
            Assert.assertEquals(gross, movie.getGross());

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
    public void updateMovieTest(){
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        MovieDAO dao = null;
        try{
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getMovieDAO();

            poolDAO.init();

            List<Movie> movieList = dao.getLatestMovies();
            Movie movie = null;
            if(movieList.size()>0) {
                movie = movieList.get(0);
            }
            int id=0;

            assert movie != null;
            id = movie.getId();

            String titleRu= "Тест";
            String titleEn= "Test";
            int year= 1999;
            long budget = 1000_000;
            long gross = 10_000_000;

            String titleRuTemp= movie.getTitleRu();
            String titleEnTemp= movie.getTitleEn();
            int yearTemp= movie.getYear();
            long budgetTemp = movie.getBudget();
            long grossTemp = movie.getGross();

            dao.updateMovie(id, titleRu, titleEn, year, budget, gross);
            movie = dao.getMovieByID(id);
            Assert.assertEquals(titleRu, movie.getTitleRu());
            Assert.assertEquals(titleEn, movie.getTitleEn());
            Assert.assertEquals(year, movie.getYear());
            Assert.assertEquals(budget, movie.getBudget());
            Assert.assertEquals(gross, movie.getGross());

            dao.updateMovie(id, titleRuTemp, titleEnTemp, yearTemp, budgetTemp, grossTemp);

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
