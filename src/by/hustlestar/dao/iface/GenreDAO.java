package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.Genre;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Hustler on 08.11.2016.
 */
public interface GenreDAO {
    List<Genre> getGenresByMovie(int id) throws DAOException;

    void addGenreForMovie(int intMovieID, String nameRu, String nameEn) throws DAOException;

    void deleteGenreForMovie(int intMovieID, String nameEn) throws DAOException;
}
