package by.hustlestar.dao;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Hustler on 01.11.2016.
 */
public interface MovieDAO {
    List<Movie> fullList() throws DAOException;
    Movie byID(int id) throws DAOException;
}
