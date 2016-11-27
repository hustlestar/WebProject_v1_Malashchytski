package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.User;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.MovieDAO;
import by.hustlestar.dao.iface.ReviewDAO;
import by.hustlestar.dao.iface.UserDAO;
import by.hustlestar.service.iface.AdminService;
import by.hustlestar.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Hustler on 07.11.2016.
 */
public class AdminServiceImpl implements AdminService {
    private static final String USER = "user";
    private static final String BANNED = "banned";

    @Override
    public void banUser(String userNickname) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            User user = userDAO.viewUserByNickname(userNickname);
            if (user.getType().equals(USER)){
                userDAO.banUser(userNickname);
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void unbanUser(String userNickname) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            User user = userDAO.viewUserByNickname(userNickname);
            if (user.getType().equals(BANNED)){
                userDAO.unbanUser(userNickname);
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public List<User> showAllUsers() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        List<User> users;
        try {
            users = dao.viewAllUsers();
            if (users == null || users.size() == 0) {
                throw new ServiceException("No users matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return users;
    }

    @Override
    public List<User> showAllBannedUsers() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        List<User> users;
        try {
            users = dao.viewAllBannedUsers();
            if (users == null || users.size() == 0) {
                throw new ServiceException("No users matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return users;
    }

    @Override
    public void addMovie(String titleRu ,String titleEn, String year, String budget, String gross) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        int intYear;
        long longBudget;
        long longGross;
        try {
            intYear = Integer.parseInt(year);
            longBudget = Long.parseLong(budget);
            longGross = Long.parseLong(gross);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {

            dao.addMovie(titleRu, titleEn, intYear, longBudget, longGross);

        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void updateMovie(String id,String titleRu, String titleEn, String year, String budget, String gross) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        int intID;
        int intYear;
        long longBudget;
        long longGross;
        try {
            intID = Integer.parseInt(id);
            intYear = Integer.parseInt(year);
            longBudget = Long.parseLong(budget);
            longGross = Long.parseLong(gross);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {
            dao.updateMovie(intID, titleRu, titleEn, intYear, longBudget, longGross);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void deleteReview(String movieID, String userNickname) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ReviewDAO dao = daoFactory.getReviewDAO();
        int intMovieID;
        try {
            intMovieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {
            dao.deleteReview(intMovieID, userNickname);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }
}
