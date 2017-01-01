package LayerDAO;

import by.hustlestar.bean.entity.User;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ConnectionPoolDAO;
import by.hustlestar.dao.iface.UserDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dell on 26.12.2016.
 */
public class UserSQLDAOTest {
    @Test
    public void registerTest(){
        DAOFactory factory = null;
        ConnectionPoolDAO poolDAO = null;
        UserDAO dao = null;
        try{
            factory = DAOFactory.getInstance();
            poolDAO = factory.getConnectionPoolDAO();
            dao = factory.getUserDAO();

            poolDAO.init();
            String userNickname= "testUser";
            String userEmail= "testuser@mail.ru";
            String userPass= "testUserPass";
            String userSex= "f";

            dao.register(userNickname, userEmail, userPass, userSex);
            User user = dao.getUserByNickname(userNickname);


            dao.deleteUser(userNickname);

            Assert.assertNotEquals(user, null);
            Assert.assertEquals(userNickname, user.getNickname());
            Assert.assertEquals(userEmail, user.getEmail());
            Assert.assertEquals(userSex, user.getSex());

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
