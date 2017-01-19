package by.hustlestar.dao.iface;


import by.hustlestar.bean.entity.News;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * NewsDAO interface represents methods for interacting with News bean stored in data source.
 */
public interface NewsDAO {
    /**
     * This method is used to add new news entry to the data source.
     *
     * @param newsTitleRu news title in russian
     * @param newsTitleEn news title in english
     * @param newsTextRu  news text in russian
     * @param newsTextEn  news text in english
     * @throws DAOException if some error occurred while processing data.
     */
    void addNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn) throws DAOException;

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
    void updateNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn, int newsID) throws DAOException;

    /**
     * This method is used to get detailed information of a particular news.
     *
     * @param id of news
     * @return filled News bean
     * @throws DAOException if some error occurred while processing data.
     */
    News getNews(int id) throws DAOException;

    /**
     * This method is used to add connection between some news and actor into data source.
     *
     * @param actorID actor id
     * @param newsID  news id
     * @throws DAOException if some error occurred while processing data.
     */
    void addActorForNews(int actorID, int newsID) throws DAOException;

    /**
     * This method is used to remove
     *
     * @param actorID actor id
     * @param newsID  news id
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteActorForNews(int actorID, int newsID) throws DAOException;

    /**
     * This method is used to add connection between some news and movie into data source.
     *
     * @param intNewsID  id of news
     * @param intMovieID id of news
     * @throws DAOException if some error occurred while processing data.
     */
    void addMovieForNews(int intNewsID, int intMovieID) throws DAOException;

    /**
     * This method is used to remove connection between
     *
     * @param intNewsID  id of news
     * @param intMovieID id of movie
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteMovieForNews(int intNewsID, int intMovieID) throws DAOException;

    /**
     * This method is used to get the latest news from data source.
     *
     * @return list of filled news beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<News> getLatestNews() throws DAOException;

    /**
     * This method is used to retrieve last inserted news from data source.
     *
     * @return filled news bean
     * @throws DAOException
     */
    News getLastInsertedNews() throws DAOException;

    /**
     * This method is used to remove news from data source. Used only for tests.
     *
     * @param id of news
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteNews(int id) throws DAOException;

    /**
     * This method is used update image path for a particular news in data source.
     *
     * @param id   of news
     * @param path to image
     * @throws DAOException if some error occurred while processing data.
     */
    void updateImage(int id, String path) throws DAOException;
}
