package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.Country;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.CountryDAO;
import by.hustlestar.dao.impl.pool.ConnectionPool;
import by.hustlestar.dao.impl.pool.ConnectionPoolException;

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
    private final static String SHOW_COUNTRIES_BY_ID = "SELECT country_ru, country_en FROM country WHERE movies_m_id=?";

    private static final String COUNTRY_RU = "country_ru";
    private static final String COUNTRY_EN = "country_en";

    @Override
    public List<Country> getCountriesByMovie(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

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
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPool.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }
}
