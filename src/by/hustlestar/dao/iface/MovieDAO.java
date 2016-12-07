package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Hustler on 01.11.2016.
 */
public interface MovieDAO {
    List<Movie> getFullMovieList() throws DAOException;

    List<Movie> getMoviesByCountry(String country) throws DAOException;

    List<Movie> getMoviesByGenre(String genre) throws DAOException;

    List<Movie> findMovieByTitle(String title) throws DAOException;

    List<Movie> getMoviesOfTenYearsPeriod(int years) throws DAOException;

    List<Movie> getMoviesOfYear(int year) throws DAOException;

    Movie getMovieByID(int id) throws DAOException;

    void addMovie(String titleRu, String titleEn, int year, long budget, long gross) throws DAOException;

    void updateMovie(int id, String titleRu, String titleEn, int year, long budget, long gross) throws DAOException;

    List<Movie> getMoviesForActor(int actorID) throws DAOException;

    List<Movie> getMoviesForNews(int id) throws DAOException;
}
