package by.hustlestar.dao;

import by.hustlestar.dao.iface.*;
import by.hustlestar.dao.impl.*;
import by.hustlestar.dao.pool.ConnectionPoolSQLDAO;

/**
 * Created by Hustler on 28.10.2016.
 */
public class DAOFactory {
    private static final DAOFactory INSTANCE = new DAOFactory();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return INSTANCE;
    }


    private UserDAO userDAO = new UserSQLDAO();
    private MovieDAO movieDAO = new MovieSQLDAO();
    private CountryDAO countryDAO = new CountrySQLDAO();
    private RatingDAO ratingDAO = new RatingSQLDAO();
    private ReviewDAO reviewDAO = new ReviewSQLDAO();
    private ReviewScoreDAO reviewScoreDAO = new ReviewScoreSQLDAO();
    private GenreDAO genreDAO = new GenreSQLDAO();
    private ActorDAO actorDAO = new ActorSQLDAO();
    private NewsDAO newsDAO = new NewsSQLDAO();
    private ConnectionPoolDAO connectionPoolDAO = new ConnectionPoolSQLDAO();

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