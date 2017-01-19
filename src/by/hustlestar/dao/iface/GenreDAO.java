package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.Genre;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * GenreDAO interface is mainly used to interact with Genre bean mainly.
 */
public interface GenreDAO {
    /**
     * This method is used to get genres for a particular movie from data source.
     *
     * @param id of movie
     * @return filled Genre beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Genre> getGenresByMovie(int id) throws DAOException;

    /**
     * This method is used to add connection between some movie and genre into data source.
     *
     * @param intMovieID id of movie
     * @param nameRu     genre name in russian
     * @param nameEn     genre name in english
     * @throws DAOException if some error occurred while processing data.
     */
    void addGenreForMovie(int intMovieID, String nameRu, String nameEn) throws DAOException;

    /**
     * This method is used to remove connection between some movie and genre from data source.
     *
     * @param intMovieID movie id
     * @param nameEn     name in english
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteGenreForMovie(int intMovieID, String nameEn) throws DAOException;
}
