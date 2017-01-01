package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.Country;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.CountryDAO;
import by.hustlestar.dao.pool.ConnectionPoolSQLDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import by.hustlestar.dao.util.DAOHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hustler on 07.11.2016.
 */
public class CountrySQLDAO implements CountryDAO {
    private final static String SHOW_COUNTRIES_BY_ID =
            "SELECT country_ru, country_en FROM country WHERE movies_m_id=?";
    private static final String ADD_COUNTRY =
            "INSERT INTO country (movies_m_id, country_ru, country_en) VALUES (?, ?, ?)";
    private static final String DELETE_COUNTRY =
            "DELETE FROM country\n" +
                    "WHERE movies_m_id=? AND country_en=?;";

    private static final String COUNTRY_RU = "country_ru";
    private static final String COUNTRY_EN = "country_en";

    @Override
    public List<Country> getCountriesByMovie(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_COUNTRIES_BY_ID);
            st.setInt(1, id);
            rs = st.executeQuery();

            List<Country> countryList = new ArrayList<>();
            Country country = null;
            while (rs.next()) {
                country = new Country();
                country.setNameRu(rs.getString(COUNTRY_RU));
                country.setNameEn(rs.getString(COUNTRY_EN));
                countryList.add(country);
            }
            return countryList;

        } catch (SQLException e) {
            throw new DAOException("Country sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Country pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st, rs);
        }
    }

    @Override
    public void addCountryForMovie(int intMovieID, String nameRu, String nameEn) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(ADD_COUNTRY);
            st.setInt(1, intMovieID);
            st.setString(2, nameRu);
            st.setString(3, nameEn);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Country dobavlen vse ok"+ intMovieID+" "+nameEn+" "+nameRu);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Review sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }

    @Override
    public void deleteCountryForMovie(int intMovieID, String nameEn) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_COUNTRY);
            st.setInt(1, intMovieID);
            st.setString(2, nameEn);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Country udalen vse ok "+intMovieID);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }
}
