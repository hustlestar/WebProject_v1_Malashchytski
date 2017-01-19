package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.*;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.*;
import by.hustlestar.service.exception.ServiceBannedException;
import by.hustlestar.service.iface.UserService;
import by.hustlestar.service.exception.ServiceAuthException;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.util.UtilService;
import by.hustlestar.service.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This class is an implementation of UserService.
 */
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();
    private static final String BANNED = "banned";

    /**
     * This method is used to show any user by the nickname.
     *
     * @param nickname of user.
     * @return User bean with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public User getUserByNickname(String nickname) throws ServiceException {
        if (!Validator.validate(nickname)) {
            throw new ServiceAuthException("Wrong username!");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        ReviewDAO reviewDAO = daoFactory.getReviewDAO();
        User user;
        List<Review> reviewList;
        try {
            user = dao.getUserByNickname(nickname);
            if (user != null) {
                reviewList = reviewDAO.getReviewsForUser(nickname);

                UtilService.fillReviewWithScore(reviewList);
                UtilService.calculateReputation(reviewList, user);
                user.setReviews(reviewList);
            } else {
                throw new ServiceException("No user matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return user;
    }

    /**
     * This method is used to let user enter his account in the system.
     *
     * @param login    of user
     * @param password of user
     * @return User bean with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public User authorize(String login, byte[] password) throws ServiceException {
        logger.debug("authorize begin");
        if (!Validator.validateLogin(login) ||
                !Validator.validatePassword(password)) {
            throw new ServiceAuthException("Wrong parameters!");
        }
        String encodedPassword = UtilService.encodePassword(password);
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        User user;
        try {
            user = dao.authorize(login, encodedPassword);

            if (user == null) {
                throw new ServiceAuthException("Wrong login or password!");
            } else if (user.getType().equals(BANNED)) {
                throw new ServiceBannedException("Sorry access for you is temporary unavailable");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source", e);
        }

        return user;
    }

    /**
     * This method is used to show the best users in the system, based on their reputation.
     *
     * @return List of User bean with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<User> getTopUsers() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        List<User> users;
        try {
            users = dao.getTopUsers();
            if (users == null || users.size() == 0) {
                throw new ServiceException("No users matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return users;
    }

    /**
     * This method is used to register and give access to the system for
     * some new visitor.
     *
     * @param login       of user
     * @param email       of user
     * @param password    of user
     * @param passwordrep of user
     * @param sex         of user
     * @return User bean with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public User register(String login, String email, byte[] password, byte[] passwordrep, String sex) throws ServiceException {
        if (!Validator.validate(login, email, sex) ||
                !Validator.validateLogin(login) ||
                !Validator.validatePassword(password, passwordrep) ||
                !Validator.validateEmail(email)) {
            throw new ServiceAuthException("Check input parameters");
        }
        String encodedPassword = UtilService.encodePassword(password);
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        User user;
        try {
            user = dao.register(login, email, encodedPassword, sex);

            if (user == null) {
                throw new ServiceAuthException("Wrong login or password!");
            }

        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }

        return user;
    }
}
