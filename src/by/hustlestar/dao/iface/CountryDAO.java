package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.Country;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Hustler on 07.11.2016.
 */
public interface CountryDAO {
    List<Country> getCountriesByMovie(int id) throws DAOException;
}
