package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.*;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.*;
import by.hustlestar.service.UserService;
import by.hustlestar.service.exception.ServiceException;

/**
 * Created by Hustler on 09.11.2016.
 */
public class UserServiceImpl implements UserService {
    @Override
    public User showUserByNickname(String nickname) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        //ReviewDAO reviewDAO = daoFactory.getReviewDAO();
        User user;
        //List<Review> reviewList;
        try {
            user = dao.viewUserByNickname(nickname);
            if (user != null) {
                //reviewList = reviewDAO.getReviewsForMovie(normId);
                //user.setReviews(reviewList);
            } else {
                throw new ServiceException("No user matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return user;
    }
}
