package by.hustlestar.service;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Hustler on 01.11.2016.
 */
public interface MovieService {

    List<Movie> showFullList() throws ServiceException;

    List<Movie> showMoviesByCountry(String country) throws ServiceException;

    List<Movie> showMoviesByGenre(String genre) throws ServiceException;

    Movie showMovieByID(String id) throws ServiceException;

    void addMovie(String title, String year, String budget, String gross) throws ServiceException;

    void updateMovie(String id, String title, String year, String budget, String gross) throws ServiceException;

    void addReview(String movieID, String userNickname, String review) throws ServiceException;

}
