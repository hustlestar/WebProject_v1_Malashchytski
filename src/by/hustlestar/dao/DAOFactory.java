package by.hustlestar.dao;

import by.hustlestar.dao.iface.*;
import by.hustlestar.dao.impl.*;

/**
 * Created by Hustler on 28.10.2016.
 */
public class DAOFactory {
    private static final DAOFactory INSTANCE = new DAOFactory();

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return INSTANCE;
    }



    private UserDAO userDAO = new UserSQLDAO();
    private MovieDAO movieDAO = new MovieSQLDAO();
    private CountryDAO countryDAO = new CountrySQLDAO();
    private ReviewDAO reviewDAO = new ReviewSQLDAO();
    private GenreDAO genreDAO = new GenreSQLDAO();

    public UserDAO getUserDAO(){
        return userDAO;
    }

    public MovieDAO getMovieDAO() {
        return movieDAO;
    }

    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }

    public GenreDAO getGenreDAO() {
        return genreDAO;
    }
}