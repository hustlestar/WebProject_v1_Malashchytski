package by.hustlestar.dao;

import by.hustlestar.dao.impl.MovieSQLDAO;
import by.hustlestar.dao.impl.UserSQLDAO;

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

    public UserDAO getUserDAO(){
        return userDAO;
    }

    public MovieDAO getMovieDAO() {
        return movieDAO;
    }
}