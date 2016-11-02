package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.bean.entity.User;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.MovieDAO;
import by.hustlestar.dao.UserDAO;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.service.MovieService;
import by.hustlestar.service.exception.ServiceAuthException;
import by.hustlestar.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Hustler on 01.11.2016.
 */
public class MovieServiceImpl implements MovieService {
    @Override
    public List<Movie> showFullList() throws ServiceException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        List<Movie> movies;
        try {
            movies = dao.fullList();
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }
}
