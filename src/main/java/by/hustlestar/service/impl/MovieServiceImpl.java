package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.*;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.iface.*;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.service.iface.MovieService;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.util.UtilService;
import by.hustlestar.service.validation.Validator;

import java.util.List;

/**
 * MovieServiceImpl is an implementation class of MovieService.
 */
public class MovieServiceImpl implements MovieService {
    /**
     * This method is used to get list of all movies in the system.
     *
     * @param offset         starting from
     * @param recordsPerPage amount of movies
     * @return list of filled movie beans
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<Movie> getFullList(int offset, int recordsPerPage) throws ServiceException {
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

    /**
     * This method is used to get list of movies of a particular country.
     *
     * @param offset         starting from
     * @param recordsPerPage amount of movies
     * @param country        of movies
     * @return list of filled movie beans
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<Movie> getMoviesByCountry(int offset, int recordsPerPage, String country) throws ServiceException {
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

    /**
     * This method is used to get movies of a particular genre.
     *
     * @param offset         starting from
     * @param recordsPerPage amount of movies
     * @param genre          of movies
     * @return list of filled movie beans
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<Movie> getMoviesByGenre(int offset, int recordsPerPage, String genre) throws ServiceException {
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

    /**
     * This method is used to get detailed information about particular movie.
     *
     * @param offset         starting from
     * @param recordsPerPage amount of movies
     * @param id             of movie
     * @param lang           language of reviews
     * @return completely filled movie bean
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public Movie getMovieByID(int offset, int recordsPerPage, String id, String lang) throws ServiceException {
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

    /**
     * This method is used to search for any movie in the system.
     *
     * @param title of movie
     * @return list of filled movie beans
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to get movies by a particular decade.
     *
     * @param years of decade
     * @return list of filled movie beans
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<Movie> getMoviesOfTenYearsPeriod(String years) throws ServiceException {
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

    /**
     * This method is used to get movies of a particular decade.
     *
     * @param year of movies
     * @return list of filled movie beans
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<Movie> getMoviesOfYear(String year) throws ServiceException {
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

    /**
     * This method is used to add review for a particular movie.
     *
     * @param movieID      id of movie
     * @param userNickname reviewer nickname
     * @param review       text of review
     * @param lang         language of review
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to vote for any review.
     *
     * @param movieID          id of movie
     * @param reviewerNickname reviewer nickname
     * @param score            like or dislike
     * @param userNickname     nickname of user who votes
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to add 1-10 rating for a particular movie.
     *
     * @param movieID      id of movie
     * @param userNickname user nickname
     * @param rating       1-10 rating
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to get movies with the latest reviews.
     *
     * @param lang of reviews
     * @return list of filled movie beans
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<Movie> getLatestReviews(String lang) throws ServiceException {
        if (!Validator.validate(lang)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        List<Movie> movies;
        try {
            movies = dao.getMoviesWithLatestReviews(lang);
            for (Movie movie : movies) {
                //System.out.println(movie.getReviews().size()+" for movie"+movie.getId());
                UtilService.fillReviewWithScore(movie.getReviews());
            }
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    /**
     * This method is used to get new movies in the system.
     *
     * @return list of filled movie beans
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<Movie> getLatestMovies() throws ServiceException {
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

    /**
     * This method is used to count amount of all movies in the system.
     *
     * @return number of movies
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to count amount of all movies of a particular country.
     *
     * @param country name of country
     * @return number of movies
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to count amount of all movies of a particular genre.
     *
     * @param genre of movie
     * @return number of movies
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to count number of reviews for a particular movie.
     *
     * @param id   of movie
     * @param lang language of reviews
     * @return number of reviews
     * @throws ServiceException if any error occurred while processing method.
     */
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
