package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.Country;
import by.hustlestar.bean.entity.Movie;
import by.hustlestar.bean.entity.Review;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.iface.CountryDAO;
import by.hustlestar.dao.iface.MovieDAO;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ReviewDAO;
import by.hustlestar.service.MovieService;
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

    @Override
    public List<Movie> showMoviesByCountry(String country) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        List<Movie> movies;
        try {
            movies = dao.showMoviesByCountry(country);
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    @Override
    public Movie showMovieByID(String id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        CountryDAO countryDAO = daoFactory.getCountryDAO();
        ReviewDAO reviewDAO = daoFactory.getReviewDAO();
        Movie movie;
        List<Country> countryList;
        List<Review> reviewList;
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
                reviewList = reviewDAO.getReviewsForMovie(normId);
                movie.setCountries(countryList);
                movie.setReviews(reviewList);
            }
            else {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movie;
    }

    @Override
    public void addMovie(String title, String year, String budget, String gross) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        int intYear;
        long longBudget;
        long longGross;
        try {
            intYear = Integer.parseInt(year);
            longBudget = Long.parseLong(budget);
            longGross = Long.parseLong(gross);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {

            dao.addMovie(title, intYear, longBudget, longGross);

        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

}
