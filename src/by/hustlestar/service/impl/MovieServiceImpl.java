package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.*;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.iface.*;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.service.iface.MovieService;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.util.UtilService;

import java.util.List;

/**
 * Created by Hustler on 01.11.2016.
 */
public class MovieServiceImpl implements MovieService {
    @Override
    public List<Movie> showFullList() throws ServiceException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        List<Movie> movies;
        try {
            movies = dao.fullList();
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
            UtilService.fillRatingsForMovie(ratingDAO, movies);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }



    @Override
    public List<Movie> showMoviesByCountry(String country) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        List<Movie> movies;
        try {
            movies = dao.showMoviesByCountry(country);
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query" + country);
            }
            UtilService.fillRatingsForMovie(ratingDAO, movies);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    @Override
    public List<Movie> showMoviesByGenre(String genre) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        List<Movie> movies;
        try {
            movies = dao.showMoviesByGenre(genre);
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
            UtilService.fillRatingsForMovie(ratingDAO, movies);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    @Override
    public Movie showMovieByID(String id, String lang) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        CountryDAO countryDAO = daoFactory.getCountryDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        ReviewDAO reviewDAO = daoFactory.getReviewDAO();
        GenreDAO genreDAO = daoFactory.getGenreDAO();
        Movie movie;
        List<Country> countryList;
        List<Rating> ratingList;
        List<Review> reviewList;
        List<Genre> genreList;
        int normId;
        try {
            normId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ServiceException("No film with such ID");
        }
        try {
            movie = dao.showMovieByID(normId);
            if (movie != null) {

                countryList = countryDAO.getCountriesByMovie(normId);

                genreList = genreDAO.getGenresByMovie(normId);

                ratingList = ratingDAO.getRatingsForMovie(normId);

                reviewList = reviewDAO.getReviewsForMovie(normId, lang);
                UtilService.fillReviewWithScore(reviewList);

                movie.setCountries(countryList);
                movie.setGenres(genreList);
                movie.setRatings(ratingList);
                movie.setReviews(reviewList);

            } else {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movie;
    }

    @Override
    public List<Movie> findMovieByTitle(String title) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        List<Movie> movies;
        try {
            movies = dao.findMovieByTitle(title);
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
            UtilService.fillRatingsForMovie(ratingDAO, movies);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    @Override
    public List<Movie> showMoviesOfTenYearsPeriod(String years) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        List<Movie> movies;
        int intYears;
        try {
            intYears = Integer.parseInt(years);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong years input!");
        }
        try {
            movies = dao.showMoviesOfTenYearsPeriod(intYears);
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
            UtilService.fillRatingsForMovie(ratingDAO, movies);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    @Override
    public List<Movie> showMoviesOfYear(String year) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        List<Movie> movies;
        int intYear;
        try {
            intYear = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong years input!");
        }
        try {
            movies = dao.showMoviesOfYear(intYear);
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
            UtilService.fillRatingsForMovie(ratingDAO, movies);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    @Override
    public void addReview(String movieID, String userNickname, String review, String lang) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ReviewDAO dao = daoFactory.getReviewDAO();
        int intMovieID;
        try {
            intMovieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }
        try {
            dao.addReview(intMovieID, userNickname, review, lang);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void likeReview(String movieID, String reviewerNickname, String score, String userNickname) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ReviewScoreDAO dao = daoFactory.getReviewScoreDAO();
        int intMovieID;
        int value = score.equals("up") ? 1 : -1;
        try {
            intMovieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding like");
        }
        if (reviewerNickname.equals(userNickname)){
            throw new ServiceException("You cannot vote for your own review");
        }
        try {
            dao.likeReview(intMovieID, reviewerNickname, value, userNickname);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void addRating(String movieID, String userNickname, String rating) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        RatingDAO dao = daoFactory.getRatingDAO();

        int intMovieID;
        int intRating;
        try {
            intMovieID = Integer.parseInt(movieID);
            intRating = Integer.parseInt(rating);
            if (intRating<1 && intRating>10 ){
                throw new ServiceException("Wrong rating input, while adding rating");
            }
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding rating");
        }
        try {
            Rating rate = dao.checkRating(intMovieID, userNickname);
            if (rate!=null){
                dao.updateRating(intMovieID, userNickname, intRating);
            } else {
                dao.addRating(intMovieID, userNickname, intRating);
            }

        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }
}
