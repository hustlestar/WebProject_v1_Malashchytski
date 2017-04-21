package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.Country;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * CountryDAO interface represents all methods for working with Country entity mainly.
 */
public interface CountryDAO {
    /**
     * This method is used to retrieve countries for a particular movie from data source.
     *
     * @param id of movie
     * @return list of filled Country beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Country> getCountriesByMovie(int id) throws DAOException;

    /**
     * This method is used to add connection between some country and a particular movie into data source.
     *
     * @param intMovieID id of movie
     * @param nameRu     name of country in russian
     * @param nameEn     name of country in english
     * @throws DAOException if some error occurred while processing data.
     */
    void addCountryForMovie(int intMovieID, String nameRu, String nameEn) throws DAOException;

    /**
     * This method is used to remove connection between some movie and country from data source.
     *
     * @param intMovieID id of movie
     * @param nameEn     name of country in english
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteCountryForMovie(int intMovieID, String nameEn) throws DAOException;
}
