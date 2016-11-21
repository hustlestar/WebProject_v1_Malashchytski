package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.Genre;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.GenreDAO;
import by.hustlestar.dao.impl.pool.ConnectionPool;
import by.hustlestar.dao.impl.pool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hustler on 08.11.2016.
 */
public class GenreSQLDAO implements GenreDAO {
    private final static String SHOW_GENRES_BY_ID = "SELECT genres_genre_ru, genres_genre_en FROM genres WHERE movies_m_id=?";

    private static final String GENRE_RU = "genres_genre_ru";
    private static final String GENRE_EN = "genres_genre_en";
    @Override
    public List<Genre> getGenresByMovie(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_GENRES_BY_ID);
            st.setInt(1, id);
            rs = st.executeQuery();

            List<Genre> genreList = new ArrayList<>();
            Genre genre = null;
            while (rs.next()) {
                genre = new Genre();
                genre.setNameRu(rs.getString(GENRE_RU));
                genre.setNameEn(rs.getString(GENRE_EN));
                genreList.add(genre);
            }
            return genreList;

        } catch (SQLException e) {
            throw new DAOException("Genre sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Genre pool connection error", e);
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
