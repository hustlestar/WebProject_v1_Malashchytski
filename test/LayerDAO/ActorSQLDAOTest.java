package LayerDAO;

import by.hustlestar.bean.entity.Actor;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ActorDAO;
import by.hustlestar.dao.iface.ConnectionPoolDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by dell on 20.12.2016.
 */
public class ActorSQLDAOTest {
    @Test
    public void addActorTest() {
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        ActorDAO dao = null;
        try {
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getActorDAO();

            poolDAO.init();
            String nameRu = "Тест";
            String nameEn = "Test";

            dao.addActor(nameRu, nameEn);
            Actor actor = dao.getLastInsertedActor();
            int id = actor.getId();

            dao.deleteActor(id);

            Assert.assertEquals(nameRu, actor.getNameRu());
            Assert.assertEquals(nameEn, actor.getNameEn());

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
    public void updateActorTest(){
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        ActorDAO dao = null;
        try{
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getActorDAO();

            poolDAO.init();

            List<Actor> actorList = dao.getAllActors();
            Actor actor = null;
            if(actorList.size()>0) {
                actor = actorList.get(0);
            }
            int id=0;

            assert actor != null;
            id = actor.getId();

            String nameRu= "Тест";
            String nameEn= "Test";

            String nameRuTemp= actor.getNameRu();
            String nameEnTemp= actor.getNameEn();

            dao.updateActor(id, nameRu, nameEn);
            actor = dao.getActor(id);
            Assert.assertEquals(nameRu, actor.getNameRu());
            Assert.assertEquals(nameEn, actor.getNameEn());

            dao.updateActor(id, nameRuTemp, nameEnTemp);

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
