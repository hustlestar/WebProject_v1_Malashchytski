package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.*;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.*;
import by.hustlestar.service.iface.UserService;
import by.hustlestar.service.exception.ServiceAuthException;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.util.UtilService;
import by.hustlestar.service.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by Hustler on 09.11.2016.
 */
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public User showUserByNickname(String nickname) throws ServiceException {
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

                user.setReviews(reviewList);
            } else {
                throw new ServiceException("No user matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return user;
    }

    @Override
    public User authorize(String login, String password) throws ServiceException {
        logger.debug("authorize begin");
        if (!Validation.validate(login, password)) {
            throw new ServiceAuthException("Wrong parameters!");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        //ReviewDAO reviewDAO = daoFactory.getReviewDAO();
        //RatingDAO ratingDAO = daoFactory.getRatingDAO();
        //UtilService utilService = new UtilService();
        User user;
        //List<Review> reviewList;
        //List<Rating> ratingList;
        try {
            user = dao.authorize(login, password);

            if (user == null) {
                throw new ServiceAuthException("Wrong login or password!");
            }
            //reviewList = reviewDAO.getReviewsForUser(login);
            //ratingList = ratingDAO.getRatingsOfUser(login);

            //utilService.fillReviewWithScore(reviewList);

            //user.setReviews(reviewList);
            //user.setRatings(ratingList);
        } catch (DAOException e) {
            throw new ServiceException("Error in source", e);
        }

        return user;
    }

    @Override
    public User register(String login, String email, String password, String sex) throws ServiceException {
        if (!Validation.validate(login, email, password, sex)) {
            throw new ServiceAuthException("Check input parameters");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        User user;
        try {
            user = dao.register(login, email, password, sex);

            if (user == null) {
                throw new ServiceAuthException("Wrong login or password!");
            }

        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }

        return user;
    }
}
