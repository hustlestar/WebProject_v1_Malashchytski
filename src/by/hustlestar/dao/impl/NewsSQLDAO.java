package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.News;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.NewsDAO;
import by.hustlestar.dao.impl.pool.ConnectionPoolException;
import by.hustlestar.dao.impl.pool.ConnectionPoolSQLDAO;

import java.sql.*;

/**
 * Created by dell on 06.12.2016.
 */
public class NewsSQLDAO implements NewsDAO {
    private static final String ADD_REVIEW =
            "INSERT INTO news" +
                    " (n_title_ru, n_title_en, n_text_ru, n_text_en, n_date, actors_a_id, movies_m_id)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_REVIEW =
            "UPDATE news SET\n" +
                    " n_title_ru = ?, n_title_en= ?, n_text_ru= ?, n_text_en= ?, n_date= ?, actors_a_id= ?, movies_m_id= ?\n" +
                    " WHERE n_id= ?;";
    public static final String NEWS_BY_ID =
            "SELECT n_title_ru, n_title_en, n_text_ru, n_text_en, n_date, actors_a_id, movies_m_id\n"+
                    "FROM news WHERE n_id=?";

    private static final String NEWS_TITLE_RU = "n_title_ru";
    private static final String NEWS_TITLE_EN = "n_title_en";
    private static final String NEWS_TEXT_RU = "n_text_ru";
    private static final String NEWS_TEXT_EN = "n_text_en";
    private static final String NEWS_DATE = "n_date";
    private static final String ACTOR_ID = "actors_a_id";
    private static final String MOVIE_ID = "movies_m_id";


    @Override
    public void addNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn, int actorID, int movieID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(ADD_REVIEW);
            st.setString(1, newsTitleRu);
            st.setString(2, newsTitleEn);
            st.setString(3, newsTextRu);
            st.setString(4, newsTextEn);
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.setInt(6, actorID);
            st.setInt(7, movieID);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("News dobavlen vse ok " + newsTitleEn);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Review sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void updateNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn, int actorID, int movieID, int newsID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_REVIEW);
            st.setString(1, newsTitleRu);
            st.setString(2, newsTitleEn);
            st.setString(3, newsTextRu);
            st.setString(4, newsTextEn);
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.setInt(6, actorID);
            st.setInt(7, movieID);
            st.setInt(8, newsID);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("News dobavlen vse ok " + newsTitleEn);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Review sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public News getNews(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(NEWS_BY_ID);
            st.setInt(1, id);
            rs = st.executeQuery();

            News news = null;
            if (rs.next()) {
                news = new News();
                news.setId(id);
                news.setTitleRu(rs.getString(NEWS_TITLE_RU));
                news.setTitleEn(rs.getString(NEWS_TITLE_EN));
                news.setTextRu(rs.getString(NEWS_TEXT_RU));
                news.setTextEn(rs.getString(NEWS_TEXT_EN));
                news.setNewsDate(rs.getTimestamp(NEWS_DATE));
                news.setNewsActorID(rs.getInt(ACTOR_ID));
                news.setNewsMovieID(rs.getInt(MOVIE_ID));
            }
            return news;

        } catch (SQLException e) {
            throw new DAOException("Actor sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Actor pool connection error", e);
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
                ConnectionPoolSQLDAO.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }
}
