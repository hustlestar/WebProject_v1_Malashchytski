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
 * This AdminServiceImpl is a class which implements AdminService.
 */
public class AdminServiceImpl implements AdminService {
    private static final String USER = "user";
    private static final String BANNED = "banned";
    private static final String MOVIE = "movies";
    private static final String ACTOR = "actors";
    private static final String NEWS = "news";
    private static final String USERS = "users";
    private static final String IMAGES = "images/";
    private static final String DELIM = "/";

    /**
     * This method is used to ban and block any user.
     *
     * @param userNickname user nickname
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to give back access to the system for previously
     * banned users.
     *
     * @param userNickname user nickname
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to show all users registered in the system.
     *
     * @return List of User beans with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<User> getAllUsers() throws ServiceException {
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

    /**
     * This method is used to show all banned users in the system.
     *
     * @return List of banned User beans with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<User> getAllBannedUsers() throws ServiceException {
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

    /**
     * This method is used to add new movie to the system.
     *
     * @param titleRu movie title in russian
     * @param titleEn movie title in english
     * @param year    movie year
     * @param budget  movie budget
     * @param gross   movie gross
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to update any movie information.
     *
     * @param id      of movie
     * @param titleRu movie title in russian
     * @param titleEn movie title in english
     * @param year    of movie
     * @param budget  of movie
     * @param gross   of movie
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to delete any user review.
     *
     * @param movieID      id of movie
     * @param userNickname nickname of user
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to add a country for any movie in the system
     *
     * @param movieID id of movie
     * @param nameRu  name of country
     * @param nameEn  name of country
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to delete any country for any movie.
     *
     * @param movieID id of movie
     * @param nameEn  name in english of country
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to add any genre for any movie.
     *
     * @param movieID id of movie
     * @param nameRu  name of genre in russian
     * @param nameEn  name of genre in english
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to delete genre for movie.
     *
     * @param movieID id of movie
     * @param nameEn  name of genre for movie
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to add new actor to the system.
     *
     * @param nameRu name of actor in russian
     * @param nameEn name of actor in english
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to update any actor information.
     *
     * @param actorID id of actor
     * @param nameRu  name of actor in russian
     * @param nameEn  name of actor in english
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to add any actor for any movie.
     *
     * @param actorID id of actor
     * @param movieID id of movie
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to add director for any movie.
     *
     * @param actorID id of director
     * @param movieID id of movie
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to delete actor from any movie.
     *
     * @param actorID actor id
     * @param movieID movie id
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to remove director from any movie.
     *
     * @param actorID id of director
     * @param movieID id of movie
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to add new news to the system.
     *
     * @param newsTitleRu news title in russian
     * @param newsTitleEn news title in english
     * @param newsTextRu  news text in russian
     * @param newsTextEn  news text in english
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to update any news information.
     *
     * @param newsTitleRu news title in russian
     * @param newsTitleEn news title in english
     * @param newsTextRu  news text in russian
     * @param newsTextEn  news text in english
     * @param newsID      id of news
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to add any actor to any news.
     *
     * @param actorID id of actor
     * @param newsID  id of news
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to delete actor from news.
     *
     * @param actorID actor id
     * @param newsID  news id
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to add movie for any news.
     *
     * @param newsID  news id
     * @param movieID movie id
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to delete movie from any news.
     *
     * @param newsID  news id
     * @param movieID movie id
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to show list of all actors in the system.
     *
     * @return List of Actor beans with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to update image path for any entity type.
     *
     * @param entity   type of bean
     * @param filename name of file
     * @param path     path
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public void updateImage(String entity, String filename, String path) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO movieDAO;
        UserDAO userDAO;
        ActorDAO actorDAO;
        NewsDAO newsDAO;
        try {
            switch (entity) {
                case USERS:
                    userDAO = daoFactory.getUserDAO();
                    userDAO.updateImage(filename, IMAGES + USERS + DELIM + path);
                    break;
                case NEWS:
                    newsDAO = daoFactory.getNewsDAO();
                    int id = Integer.parseInt(filename);
                    newsDAO.updateImage(id, IMAGES + NEWS + DELIM + path);
                    break;
                case ACTOR:
                    actorDAO = daoFactory.getActorDAO();
                    id = Integer.parseInt(filename);
                    actorDAO.updateImage(id, IMAGES + ACTOR + DELIM + path);
                    break;
                case MOVIE:
                    movieDAO = daoFactory.getMovieDAO();
                    id = Integer.parseInt(filename);
                    movieDAO.updateImage(id, IMAGES + MOVIE + DELIM + path);
                    break;
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

}
