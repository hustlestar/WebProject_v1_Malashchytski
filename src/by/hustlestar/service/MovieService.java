package by.hustlestar.service;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Hustler on 01.11.2016.
 */
public interface MovieService {
    List<Movie> showFullList() throws ServiceException;
    Movie showMovieByID(String id) throws ServiceException;
}
