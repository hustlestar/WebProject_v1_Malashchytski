package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.News;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.NewsDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import by.hustlestar.dao.pool.ConnectionPoolSQLDAO;
import by.hustlestar.dao.util.DAOHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * NewsSQLDAO is an implementation of NewsDAO for MySQL.
 */
public class NewsSQLDAO implements NewsDAO {
    private static final String ADD_REVIEW =
            "INSERT INTO news" +
                    " (n_title_ru, n_title_en, n_text_ru, n_text_en, n_date)" +
                    " VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_NEWS =
            "UPDATE news SET\n" +
                    " n_title_ru = ?, n_title_en= ?, n_text_ru= ?, n_text_en= ?, n_date= ?\n" +
                    " WHERE n_id= ?;";
    private static final String UPDATE_IMAGE =
            "UPDATE news SET n_image= ? WHERE n_id= ?;";
    private static final String NEWS_BY_ID =
            "SELECT n_title_ru, n_title_en, n_text_ru, n_text_en, n_date, n_image\n" +
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
    private static final java.lang.String SHOW_LATEST_NEWS =
            "SELECT n_id, n_title_ru, n_title_en, n_date FROM news ORDER BY n_id DESC LIMIT 10";
    private static final String DELETE_NEWS_BY_ID =
            "DELETE FROM `jackdb`.`news` WHERE n_id=?;";
    private static final String LAST_INSERTED_NEWS =
            "SELECT * FROM jackdb.news ORDER BY n_id DESC LIMIT 1;";

    private static final String NEWS_ID = "n_id";
    private static final String NEWS_TITLE_RU = "n_title_ru";
    private static final String NEWS_TITLE_EN = "n_title_en";
    private static final String NEWS_TEXT_RU = "n_text_ru";
    private static final String NEWS_TEXT_EN = "n_text_en";
    private static final String NEWS_DATE = "n_date";
    private static final String NEWS_IMAGE = "n_image";

    /**
     * This method is used to add new news entry to the data source.
     *
     * @param newsTitleRu news title in russian
     * @param newsTitleEn news title in english
     * @param newsTextRu  news text in russian
     * @param newsTextEn  news text in english
     * @throws DAOException if some error occurred while processing data.
     */
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
                //System.out.println("News dobavlen vse ok " + newsTitleEn);
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
     * This method is used to update any news information in data source.
     *
     * @param newsTitleRu title of news in russian
     * @param newsTitleEn title of news
     * @param newsTextRu  news text in russian
     * @param newsTextEn  news text in english
     * @param newsID      id of news
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void updateNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn, int newsID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_NEWS);
            st.setString(1, newsTitleRu);
            st.setString(2, newsTitleEn);
            st.setString(3, newsTextRu);
            st.setString(4, newsTextEn);
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.setInt(6, newsID);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("News obnovlen vse ok " + newsTitleEn);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("News sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }

    /**
     * This method is used to get detailed information of a particular news.
     *
     * @param id of news
     * @return filled News bean
     * @throws DAOException if some error occurred while processing data.
     */
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
                news.setImage(rs.getString(NEWS_IMAGE));
            }
            return news;

        } catch (SQLException e) {
            throw new DAOException("Actor sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Actor pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to add connection between some news and actor into data source.
     *
     * @param actorID actor id
     * @param newsID  news id
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void addActorForNews(int actorID, int newsID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(ADD_ACTOR_FOR_NEWS);
            //System.out.println(newsID);
            st.setInt(1, newsID);
            st.setInt(2, actorID);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("Actor dobavlen k news");
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }

    /**
     * This method is used to remove
     *
     * @param actorID actor id
     * @param newsID  news id
     * @throws DAOException if some error occurred while processing data.
     */
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
                //System.out.println("Actor udalen vse ok " + actorID);
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

    /**
     * This method is used to add connection between some news and movie into data source.
     *
     * @param intNewsID  id of news
     * @param intMovieID id of news
     * @throws DAOException if some error occurred while processing data.
     */
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
                //System.out.println("Movie dobavlen k news");
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }

    /**
     * This method is used to remove connection between
     *
     * @param intNewsID  id of news
     * @param intMovieID id of movie
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void deleteMovieForNews(int intNewsID, int intMovieID) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_MOVIE_FOR_NEWS);
            //System.out.println("id news" + intNewsID + " id movie" + intMovieID);
            st.setInt(1, intNewsID);
            st.setInt(2, intMovieID);
            //System.out.println(st.toString());
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("Movie udalen for news ");
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

    /**
     * This method is used to get the latest news from data source.
     *
     * @return list of filled news beans
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public List<News> getLatestNews() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_LATEST_NEWS);

            rs = st.executeQuery();

            List<News> newsList = new ArrayList<>();
            News news;
            while (rs.next()) {
                news = new News();
                news.setId(rs.getInt(NEWS_ID));
                news.setTitleRu(rs.getString(NEWS_TITLE_RU));
                news.setTitleEn(rs.getString(NEWS_TITLE_EN));
                //news.setTextRu(rs.getString(NEWS_TEXT_RU));
                //news.setTextEn(rs.getString(NEWS_TEXT_EN));
                news.setNewsDate(rs.getTimestamp(NEWS_DATE));
                newsList.add(news);
            }
            return newsList;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to retrieve last inserted news from data source.
     *
     * @return filled news bean
     * @throws DAOException
     */
    @Override
    public News getLastInsertedNews() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(LAST_INSERTED_NEWS);
            rs = st.executeQuery();

            News news = null;
            if (rs.next()) {
                news = new News();
                news.setId(rs.getInt(NEWS_ID));
                news.setTitleRu(rs.getString(NEWS_TITLE_RU));
                news.setTitleEn(rs.getString(NEWS_TITLE_EN));
                news.setTextRu(rs.getString(NEWS_TEXT_RU));
                news.setTextEn(rs.getString(NEWS_TEXT_EN));
            }
            return news;

        } catch (SQLException e) {
            throw new DAOException("News sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("News pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to remove news from data source. Used only for tests.
     *
     * @param id of news
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void deleteNews(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_NEWS_BY_ID);
            st.setInt(1, id);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("News udalen vse ok" + id);
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("News sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("News pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }

    /**
     * This method is used update image path for a particular news in data source.
     *
     * @param id   of news
     * @param path to image
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void updateImage(int id, String path) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_IMAGE);
            st.setString(1, path);
            st.setInt(2, id);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("News image obnovlen vse ok " + path);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("News sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }

}
