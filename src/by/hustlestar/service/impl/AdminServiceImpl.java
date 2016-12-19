package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.Actor;
import by.hustlestar.bean.entity.User;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.*;
import by.hustlestar.service.iface.AdminService;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.validation.Validator;

import java.util.List;

/**
 * Created by Hustler on 07.11.2016.
 */
public class AdminServiceImpl implements AdminService {
    private static final String USER = "user";
    private static final String BANNED = "banned";

    @Override
    public void banUser(String userNickname) throws ServiceException {
        if (!Validator.validate(userNickname)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            User user = userDAO.getUserByNickname(userNickname);
            if (user.getType().equals(USER)) {
                userDAO.banUser(userNickname);
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void unbanUser(String userNickname) throws ServiceException {
        if (!Validator.validate(userNickname)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            User user = userDAO.getUserByNickname(userNickname);
            if (user.getType().equals(BANNED)) {
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
            users = dao.getAllUsers();
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
            users = dao.getAllBannedUsers();
            if (users == null || users.size() == 0) {
                throw new ServiceException("No users matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return users;
    }

    @Override
    public void addMovie(String titleRu, String titleEn, String year, String budget, String gross) throws ServiceException {
        if (!Validator.validate(titleRu, titleEn, year, budget, gross)
                || !Validator.validateYear(year)) {
            throw new ServiceException("Illegal data input");
        }
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
    public void updateMovie(String id, String titleRu, String titleEn, String year, String budget, String gross) throws ServiceException {
        if (!Validator.validate(titleRu, titleEn, year, budget, gross)
                || !Validator.validateYear(year)
                || !Validator.validateNumber(id)) {
            throw new ServiceException("Illegal data input");
        }
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
        if (!Validator.validate(movieID, userNickname)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
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

    @Override
    public void addCountryForMovie(String movieID, String nameRu, String nameEn) throws ServiceException {
        if (!Validator.validate(movieID, nameRu, nameEn)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        CountryDAO dao = daoFactory.getCountryDAO();
        int intMovieID;
        try {
            intMovieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {
            dao.addCountryForMovie(intMovieID, nameRu, nameEn);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void deleteCountryForMovie(String movieID, String nameEn) throws ServiceException {
        if (!Validator.validate(movieID, nameEn)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        CountryDAO dao = daoFactory.getCountryDAO();
        int intMovieID;
        try {
            intMovieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {
            dao.deleteCountryForMovie(intMovieID, nameEn);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void addGenreForMovie(String movieID, String nameRu, String nameEn) throws ServiceException {
        if (!Validator.validate(movieID, nameRu, nameEn)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenreDAO dao = daoFactory.getGenreDAO();
        int intMovieID;
        try {
            intMovieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {
            dao.addGenreForMovie(intMovieID, nameRu, nameEn);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void deleteGenreForMovie(String movieID, String nameEn) throws ServiceException {
        if (!Validator.validate(movieID, nameEn)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        GenreDAO dao = daoFactory.getGenreDAO();
        int intMovieID;
        try {
            intMovieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {
            dao.deleteGenreForMovie(intMovieID, nameEn);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void addActor(String nameRu, String nameEn) throws ServiceException {
        if (!Validator.validate(nameRu, nameEn)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ActorDAO dao = daoFactory.getActorDAO();
        try {
            dao.addActor(nameRu, nameEn);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void updateActor(String actorID, String nameRu, String nameEn) throws ServiceException {
        if (!Validator.validate(actorID, nameRu, nameEn)
                || !Validator.validateNumber(actorID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ActorDAO dao = daoFactory.getActorDAO();
        int intActorID;
        try {
            intActorID = Integer.parseInt(actorID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while updating actor for movie");
        }
        try {
            dao.updateActor(intActorID, nameRu, nameEn);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void addActorForMovie(String actorID, String movieID) throws ServiceException {
        if (!Validator.validate(actorID, movieID)
                || !Validator.validateNumber(actorID)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ActorDAO dao = daoFactory.getActorDAO();
        int intMovieID;
        int intActorID;
        try {
            intMovieID = Integer.parseInt(movieID);
            intActorID = Integer.parseInt(actorID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding actor for movie");
        }
        try {
            dao.addActorForMovie(intActorID, intMovieID);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void addDirectorForMovie(String actorID, String movieID) throws ServiceException {
        if (!Validator.validate(actorID, movieID)
                || !Validator.validateNumber(actorID)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ActorDAO dao = daoFactory.getActorDAO();
        int intMovieID;
        int intActorID;
        try {
            intMovieID = Integer.parseInt(movieID);
            intActorID = Integer.parseInt(actorID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding actor for movie");
        }
        try {
            dao.addDirectorForMovie(intActorID, intMovieID);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void deleteActorForMovie(String actorID, String movieID) throws ServiceException {
        if (!Validator.validate(actorID, movieID)
                || !Validator.validateNumber(actorID)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ActorDAO dao = daoFactory.getActorDAO();
        int intMovieID;
        int intActorID;
        try {
            intMovieID = Integer.parseInt(movieID);
            intActorID = Integer.parseInt(actorID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding actor for movie");
        }
        try {
            dao.deleteActorForMovie(intActorID, intMovieID);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void deleteDirectorForMovie(String actorID, String movieID) throws ServiceException {
        if (!Validator.validate(actorID, movieID)
                || !Validator.validateNumber(actorID)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ActorDAO dao = daoFactory.getActorDAO();
        int intMovieID;
        int intActorID;
        try {
            intMovieID = Integer.parseInt(movieID);
            intActorID = Integer.parseInt(actorID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding actor for movie");
        }
        try {
            dao.deleteDirectorForMovie(intActorID, intMovieID);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void addNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn) throws ServiceException {
        if (!Validator.validate(newsTitleRu, newsTitleEn, newsTextRu, newsTextEn)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO dao = daoFactory.getNewsDAO();
        try {
            dao.addNews(newsTitleRu, newsTitleEn, newsTextRu, newsTextEn);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void updateNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn, String newsID) throws ServiceException {
        if (!Validator.validate(newsTitleRu, newsTitleEn, newsTextRu, newsTextEn, newsID)
                || !Validator.validateNumber(newsID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO dao = daoFactory.getNewsDAO();
        int intNewsID;
        try {
            intNewsID = Integer.parseInt(newsID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding news for movie");
        }
        try {
            dao.updateNews(newsTitleRu, newsTitleEn, newsTextRu, newsTextEn, intNewsID);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void addActorForNews(String actorID, String newsID) throws ServiceException {
        if (!Validator.validate(actorID, newsID)
                || !Validator.validateNumber(actorID)
                || !Validator.validateNumber(newsID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO dao = daoFactory.getNewsDAO();
        int intNewsID;
        int intActorID;
        try {
            intNewsID = Integer.parseInt(newsID);
            intActorID = Integer.parseInt(actorID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding actor for movie");
        }
        try {
            dao.addActorForNews(intActorID, intNewsID);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void deleteActorForNews(String actorID, String newsID) throws ServiceException {
        if (!Validator.validate(actorID, newsID)
                || !Validator.validateNumber(actorID)
                || !Validator.validateNumber(newsID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO dao = daoFactory.getNewsDAO();
        int intNewsID;
        int intActorID;
        try {
            intNewsID = Integer.parseInt(newsID);
            intActorID = Integer.parseInt(actorID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding actor for movie");
        }
        try {
            dao.deleteActorForNews(intActorID, intNewsID);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void addMovieForNews(String newsID, String movieID) throws ServiceException {
        if (!Validator.validate(newsID, movieID)
                || !Validator.validateNumber(movieID)
                || !Validator.validateNumber(newsID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO dao = daoFactory.getNewsDAO();
        int intNewsID;
        int intMovieID;
        try {
            intNewsID = Integer.parseInt(newsID);
            intMovieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding actor for movie");
        }
        try {
            dao.addMovieForNews(intNewsID, intMovieID);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void deleteMovieForNews(String newsID, String movieID) throws ServiceException {
        if (!Validator.validate(newsID, movieID)
                || !Validator.validateNumber(movieID)
                || !Validator.validateNumber(newsID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO dao = daoFactory.getNewsDAO();
        int intNewsID;
        int intMovieID;
        try {
            intNewsID = Integer.parseInt(newsID);
            intMovieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding actor for movie");
        }
        try {
            dao.deleteMovieForNews(intNewsID, intMovieID);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public List<Actor> showAllActors() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ActorDAO dao = daoFactory.getActorDAO();
        List<Actor> actors;
        try {
            actors = dao.getAllActors();
            if (actors == null || actors.size() == 0) {
                throw new ServiceException("No users matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return actors;
    }

}
