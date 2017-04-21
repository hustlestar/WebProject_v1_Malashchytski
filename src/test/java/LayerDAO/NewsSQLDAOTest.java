package LayerDAO;

import by.hustlestar.bean.entity.News;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ConnectionPoolDAO;
import by.hustlestar.dao.iface.NewsDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by dell on 21.12.2016.
 */
public class NewsSQLDAOTest {
    @Test
    public void addNewsTest() {
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        NewsDAO dao = null;
        try {
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getNewsDAO();

            poolDAO.init();
            String titleRu = "Тест";
            String titleEn = "Test";
            String textRu = "Тест тест тест";
            String textEn = "Test test test";

            dao.addNews(titleRu, titleEn, textRu, textEn);
            News news = dao.getLastInsertedNews();
            int id = news.getId();

            dao.deleteNews(id);

            Assert.assertEquals(titleRu, news.getTitleRu());
            Assert.assertEquals(titleEn, news.getTitleEn());
            Assert.assertEquals(textRu, news.getTextRu());
            Assert.assertEquals(textEn, news.getTextEn());

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
    public void updateNewsTest(){
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        NewsDAO dao = null;
        try{
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getNewsDAO();

            poolDAO.init();


            int id=0;
            News news = dao.getLastInsertedNews();

            assert news != null;
            id = news.getId();

            String titleRu = "Тест";
            String titleEn = "Test";
            String textRu = "Тест тест тест";
            String textEn = "Test test test";

            String titleRuTemp = news.getTitleRu();
            String titleEnTemp = news.getTitleEn();
            String textRuTemp = news.getTextRu();
            String textEnTemp = news.getTextEn();



            dao.updateNews(titleRu, titleEn, textRu, textEn, id);
            news = dao.getNews(id);
            Assert.assertEquals(titleRu, news.getTitleRu());
            Assert.assertEquals(titleEn, news.getTitleEn());
            Assert.assertEquals(textRu, news.getTextRu());
            Assert.assertEquals(textEn, news.getTextEn());

            dao.updateNews(titleRuTemp, titleEnTemp, textRuTemp, textEnTemp, id);

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
