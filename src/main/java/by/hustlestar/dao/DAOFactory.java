package by.hustlestar.dao;

import by.hustlestar.dao.iface.*;
import by.hustlestar.dao.impl.*;
import by.hustlestar.dao.pool.ConnectionPoolSQLDAO;

/**
 * DAOFactory represents the factory for obtaining DAO objects.
 */
public class DAOFactory {
    private static final DAOFactory INSTANCE = new DAOFactory();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return INSTANCE;
    }


    private UserDAO userDAO = UserSQLDAO.getInstance();
    private MovieDAO movieDAO = MovieSQLDAO.getInstance();
    private CountryDAO countryDAO = CountrySQLDAO.getInstance();
    private RatingDAO ratingDAO = RatingSQLDAO.getInstance();
    private ReviewDAO reviewDAO = ReviewSQLDAO.getInstance();
    private ReviewScoreDAO reviewScoreDAO = ReviewScoreSQLDAO.getInstance();
    private GenreDAO genreDAO = GenreSQLDAO.getInstance();
    private ActorDAO actorDAO = ActorSQLDAO.getInstance();
    private NewsDAO newsDAO = NewsSQLDAO.getInstance();
    private ConnectionPoolDAO connectionPoolDAO = ConnectionPoolSQLDAO.getInstance();

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public MovieDAO getMovieDAO() {
        return movieDAO;
    }

    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    public RatingDAO getRatingDAO() {
        return ratingDAO;
    }

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }

    public ReviewScoreDAO getReviewScoreDAO() {
        return reviewScoreDAO;
    }

    public GenreDAO getGenreDAO() {
        return genreDAO;
    }

    public ConnectionPoolDAO getConnectionPoolDAO() {
        return connectionPoolDAO;
    }

    public ActorDAO getActorDAO() {
        return actorDAO;
    }

    public NewsDAO getNewsDAO() {
        return newsDAO;
    }
}