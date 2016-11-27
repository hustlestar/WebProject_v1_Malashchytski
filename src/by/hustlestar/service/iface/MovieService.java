package by.hustlestar.service.iface;

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

    Movie showMovieByID(String id, String lang) throws ServiceException;

    List<Movie> findMovieByTitle(String title) throws ServiceException;

    List<Movie> showMoviesOfTenYearsPeriod(String years) throws ServiceException;

    List<Movie> showMoviesOfYear(String year) throws ServiceException;

    void addReview(String movieID, String userNickname, String review, String lang) throws ServiceException;

    void likeReview(String movieID, String reviewerNickname, String score, String userNickname) throws ServiceException;

    void addRating(String movieID, String userNickname, String review) throws ServiceException;

}
