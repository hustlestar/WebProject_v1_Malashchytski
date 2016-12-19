package by.hustlestar.service.iface;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Hustler on 01.11.2016.
 */
public interface MovieService {

    List<Movie> showFullList(int offset, int recordsPerPage) throws ServiceException;

    List<Movie> showMoviesByCountry(int offset, int recordsPerPage, String country) throws ServiceException;

    List<Movie> showMoviesByGenre(int offset, int recordsPerPage, String genre) throws ServiceException;

    Movie showMovieByID(int offset, int recordsPerPage, String id, String lang) throws ServiceException;

    List<Movie> findMovieByTitle(String title) throws ServiceException;

    List<Movie> showMoviesOfTenYearsPeriod(String years) throws ServiceException;

    List<Movie> showMoviesOfYear(String year) throws ServiceException;

    void addReview(String movieID, String userNickname, String review, String lang) throws ServiceException;

    void likeReview(String movieID, String reviewerNickname, String score, String userNickname) throws ServiceException;

    void addRating(String movieID, String userNickname, String review) throws ServiceException;

    List<Movie> showLatestReviews(String lang) throws ServiceException;

    List<Movie> showLatestMovies() throws ServiceException;

    int countAllMoviesAmount() throws ServiceException;

    int countMoviesByCountry(String country) throws ServiceException;

    int countMoviesByGenre(String genre) throws ServiceException;

    int countReviewsForMovie(String id, String lang) throws ServiceException;
}
