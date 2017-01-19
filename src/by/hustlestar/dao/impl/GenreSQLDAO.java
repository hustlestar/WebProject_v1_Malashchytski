package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.Genre;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.GenreDAO;
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
 * GenreSQLDAO is an implementation of GenreDAO for MySQL.
 */
public class GenreSQLDAO implements GenreDAO {
    private final static String SHOW_GENRES_BY_ID = "SELECT genres_genre_ru, genres_genre_en FROM genres WHERE movies_m_id=?";
    private static final String ADD_GENRE =
            "INSERT INTO genres (movies_m_id, genres_genre_ru, genres_genre_en) VALUES (?, ?, ?)";
    private static final String DELETE_GENRE =
            "DELETE FROM genres\n" +
                    "WHERE movies_m_id=? AND genres_genre_en=?;";

    private static final String GENRE_RU = "genres_genre_ru";
    private static final String GENRE_EN = "genres_genre_en";

    /**
     * This method is used to get genres for a particular movie from data source.
     *
     * @param id of movie
     * @return filled Genre beans
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public List<Genre> getGenresByMovie(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

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
            DAOHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to add connection between some movie and genre into data source.
     *
     * @param intMovieID id of movie
     * @param nameRu     genre name in russian
     * @param nameEn     genre name in english
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void addGenreForMovie(int intMovieID, String nameRu, String nameEn) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(ADD_GENRE);
            st.setInt(1, intMovieID);
            st.setString(2, nameRu);
            st.setString(3, nameEn);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Genre dobavlen vse ok " + nameEn + " " + nameRu);
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

    /**
     * This method is used to remove connection between some movie and genre from data source.
     *
     * @param intMovieID movie id
     * @param nameEn     name in english
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void deleteGenreForMovie(int intMovieID, String nameEn) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_GENRE);
            st.setInt(1, intMovieID);
            st.setString(2, nameEn);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Genre udalen vse ok  " + intMovieID);
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
