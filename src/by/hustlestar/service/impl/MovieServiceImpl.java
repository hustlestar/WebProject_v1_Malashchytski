package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.*;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.iface.*;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.service.iface.MovieService;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.util.UtilService;
import by.hustlestar.service.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by Hustler on 01.11.2016.
 */
public class MovieServiceImpl implements MovieService {
    private static final Logger logger = LogManager.getLogger(MovieServiceImpl.class.getName());
    @Override
    public List<Movie> showFullList(int offset, int recordsPerPage) throws ServiceException {
        logger.debug(" showFullList() : start");
        if (!Validator.validate(offset, recordsPerPage)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        List<Movie> movies;
        try {
            movies = dao.getFullMovieList(offset, recordsPerPage);
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
    public List<Movie> showMoviesByCountry(int offset, int recordsPerPage, String country) throws ServiceException {
        if (!Validator.validate(offset, recordsPerPage)
                || !Validator.validate(country)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        List<Movie> movies;
        try {
            movies = dao.getMoviesByCountry(country, offset, recordsPerPage);
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
    public List<Movie> showMoviesByGenre(int offset, int recordsPerPage, String genre) throws ServiceException {
        if (!Validator.validate(offset, recordsPerPage)
                || !Validator.validate(genre)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        List<Movie> movies;
        try {
            movies = dao.getMoviesByGenre(genre, offset, recordsPerPage);
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
    public Movie showMovieByID(int offset, int recordsPerPage, String id, String lang) throws ServiceException {
        if (!Validator.validate(offset, recordsPerPage)
                || !Validator.validate(id, lang)
                || !Validator.validateNumber(id)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        CountryDAO countryDAO = daoFactory.getCountryDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        ReviewDAO reviewDAO = daoFactory.getReviewDAO();
        GenreDAO genreDAO = daoFactory.getGenreDAO();
        ActorDAO actorDAO = daoFactory.getActorDAO();
        Movie movie;
        List<Country> countryList;
        List<Rating> ratingList;
        List<Review> reviewList;
        List<Genre> genreList;
        List<Actor> actorList;
        Actor director;
        int normId;
        try {
            normId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ServiceException("No film with such ID");
        }
        try {
            movie = dao.getMovieByID(normId);
            if (movie != null) {

                countryList = countryDAO.getCountriesByMovie(normId);

                genreList = genreDAO.getGenresByMovie(normId);

                ratingList = ratingDAO.getRatingsForMovie(normId);

                reviewList = reviewDAO.getReviewsForMovie(normId, lang, offset, recordsPerPage);
                UtilService.fillReviewWithScore(reviewList);

                actorList = actorDAO.getActorsForMovie(normId);
                director = actorDAO.getDirectorForMovie(normId);

                movie.setCountries(countryList);
                movie.setGenres(genreList);
                movie.setRatings(ratingList);
                movie.setReviews(reviewList);
                movie.setActors(actorList);
                movie.setDirector(director);

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
        if (!Validator.validate(title)) {
            throw new ServiceException("Illegal data input");
        }
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
        if (!Validator.validateYear(years)) {
            throw new ServiceException("Illegal data input");
        }
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
            movies = dao.getMoviesOfTenYearsPeriod(intYears);
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
        if (!Validator.validateYear(year)) {
            throw new ServiceException("Illegal data input");
        }
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
            movies = dao.getMoviesOfYear(intYear);
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
        if (!Validator.validate(movieID, userNickname, review, lang)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
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
        if (!Validator.validate(movieID, reviewerNickname, score, userNickname)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ReviewScoreDAO dao = daoFactory.getReviewScoreDAO();
        int intMovieID;
        int value = score.equals("up") ? 1 : -1;
        try {
            intMovieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding like");
        }
        if (reviewerNickname.equals(userNickname)) {
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
        if (!Validator.validate(movieID, userNickname, rating)
                || !Validator.validateNumber(movieID)
                || !Validator.validateNumber(rating)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        RatingDAO dao = daoFactory.getRatingDAO();

        int intMovieID;
        int intRating;
        try {
            intMovieID = Integer.parseInt(movieID);
            intRating = Integer.parseInt(rating);
            if (intRating < 1 && intRating > 10) {
                throw new ServiceException("Wrong rating input, while adding rating");
            }
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding rating");
        }
        try {
            Rating rate = dao.checkRating(intMovieID, userNickname);
            if (rate != null) {
                dao.updateRating(intMovieID, userNickname, intRating);
            } else {
                dao.addRating(intMovieID, userNickname, intRating);
            }

        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public List<Movie> showLatestReviews(String lang) throws ServiceException {
        if (!Validator.validate(lang)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        List<Movie> movies;
        try {
            movies = dao.getMoviesWithLatestReviews(lang);
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    @Override
    public List<Movie> showLatestMovies() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        List<Movie> movies;
        try {
            movies = dao.getLatestMovies();
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
    public int countAllMoviesAmount() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        int amount;
        try {
            amount = dao.countAllMoviesAmount();
            if (amount == 0) {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return amount;
    }

    @Override
    public int countMoviesByCountry(String country) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        int amount;
        try {
            amount = dao.countMoviesByCountry(country);
            if (amount == 0) {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return amount;
    }

    @Override
    public int countMoviesByGenre(String genre) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        int amount;
        try {
            amount = dao.countMoviesByGenre(genre);
            if (amount == 0) {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return amount;
    }

    @Override
    public int countReviewsForMovie(String id, String lang) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ReviewDAO dao = daoFactory.getReviewDAO();
        int amount;
        int normId;
        try {
            normId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ServiceException("No film with such ID");
        }
        try {
            amount = dao.countReviewsForMovie(normId, lang);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return amount;
    }

}
