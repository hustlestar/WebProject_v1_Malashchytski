package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.User;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.iface.UserDAO;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.service.RegisterService;
import by.hustlestar.service.exception.ServiceAuthException;
import by.hustlestar.service.exception.ServiceException;

/**
 * Created by Hustler on 31.10.2016.
 */
public class RegisterServiceImpl implements RegisterService {

    @Override
    public User register(String login, String email, String password, String sex) throws ServiceException {
            if(!Validation.validate(login, email, password, sex)){
                throw new ServiceAuthException("Check input parameters");
            }

            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO dao = daoFactory.getUserDAO();
            User user;
            try {
                user = dao.register(login, email, password, sex);

                if(user == null){
                    throw new ServiceAuthException("Wrong login or password!");
                }

            } catch (DAOException e) {
                throw new ServiceException("Error in source!", e);
            }

            return user;
        }


        private static class Validation{
            static boolean validate(String login, String email, String password, String sex){

                if(login == null || login.isEmpty()){
                    return false;
                }

                if(email == null || email.isEmpty() || !email.matches("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}")){
                    return false;
                }

                if(password == null || password.isEmpty()){
                    return false;
                }

                if(sex == null || sex.isEmpty()){
                    return false;
                }

                return true;
            }
        }
}
