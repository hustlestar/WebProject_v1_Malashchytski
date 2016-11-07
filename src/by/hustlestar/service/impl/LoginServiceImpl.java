package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.User;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.UserDAO;
import by.hustlestar.service.LoginService;
import by.hustlestar.service.exception.ServiceAuthException;
import by.hustlestar.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by Hustler on 31.10.2016.
 */
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public User authorize(String login, String password) throws ServiceException{
        logger.debug("authorize begin");
        if(!Validation.validate(login, password)){
            throw new ServiceAuthException("Wrong parameters!");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();

        User user;
        try {
            user = dao.authorize(login, password);

            if(user == null){
                throw new ServiceAuthException("Wrong login or password!");
            }

        } catch (DAOException e) {
            throw new ServiceException("Error in source", e);
        }

        return user;
    }


    private static class Validation{
        static boolean validate(String login, String password){
            if(login == null || login.isEmpty()){
                return false;
            }
            if(password == null || password.isEmpty()){
                return false;
            }
            return true;
        }
    }
}
