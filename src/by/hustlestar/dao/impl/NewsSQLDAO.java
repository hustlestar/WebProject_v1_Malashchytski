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
                    " (n_title_ru, n_title_en, n_text_ru, n_text_en, n_date)" +
                    " VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_REVIEW =
            "UPDATE news SET\n" +
                    " n_title_ru = ?, n_title_en= ?, n_text_ru= ?, n_text_en= ?, n_date= ?\n" +
                    " WHERE n_id= ?;";
    public static final String NEWS_BY_ID =
            "SELECT n_title_ru, n_title_en, n_text_ru, n_text_en, n_date\n" +
                    "FROM news WHERE n_id=?";
    private final static String ADD_ACTOR_FOR_NEWS =
            "INSERT INTO news_about_actors (news_n_id, actors_a_id) VALUES (?, ?)";
    private static final String DELETE_ACTOR_FOR_NEWS =
            "DELETE FROM news_about_actors\n" +
                    "WHERE news_n_id=? AND actors_a_id=?;";
    private final static String ADD_MOVIE_FOR_NEWS =
            "INSERT INTO news_about_movies (movies_m_id, news_n_id) VALUES (?, ?)";
    private static final String DELETE_MOVIE_FOR_NEWS =
            "DELETE FROM news_about_movies\n" +
                    "WHERE news_n_id=? AND movies_m_id=?;";

    private static final String NEWS_TITLE_RU = "n_title_ru";
    private static final String NEWS_TITLE_EN = "n_title_en";
    private static final String NEWS_TEXT_RU = "n_text_ru";
    private static final String NEWS_TEXT_EN = "n_text_en";
    private static final String NEWS_DATE = "n_date";


    @Override
    public void addNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn) throws DAOException {
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
    public void updateNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn, int newsID) throws DAOException {
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
            st.setInt(6, newsID);
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

    @Override
    public void addActorForNews(int actorID, int newsID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(ADD_ACTOR_FOR_NEWS);
            System.out.println(newsID);
            st.setInt(1, newsID);
            st.setInt(2, actorID);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Actor dobavlen k news");
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
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
    public void deleteActorForNews(int actorID, int newsID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_ACTOR_FOR_NEWS);
            st.setInt(1, newsID);
            st.setInt(2, actorID);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Actor udalen vse ok " + actorID);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
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
    public void addMovieForNews(int intNewsID, int intMovieID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(ADD_MOVIE_FOR_NEWS);
            st.setInt(1, intMovieID);
            st.setInt(2, intNewsID);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Movie dobavlen k news");
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
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
    public void deleteMovieForNews(int intNewsID, int intMovieID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_MOVIE_FOR_NEWS);
            System.out.println("id news" + intNewsID + " id movie"+intMovieID);
            st.setInt(1, intNewsID);
            st.setInt(2, intMovieID);
            System.out.println(st.toString());
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("Movie udalen for news ");
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
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

}
