package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Hustler on 01.11.2016.
 */
public interface MovieDAO {
    List<Movie> fullList() throws DAOException;
    List<Movie> showMoviesByCountry(String country) throws DAOException;
    List<Movie> showMoviesByGenre(String genre) throws DAOException;
    Movie showMovieByID(int id) throws DAOException;
    void addMovie(String title, int year, long budget, long gross) throws DAOException;
    void updateMovie(int id, String title, int year, long budget, long gross) throws DAOException;

}
