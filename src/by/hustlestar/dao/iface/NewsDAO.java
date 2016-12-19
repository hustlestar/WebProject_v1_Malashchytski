package by.hustlestar.dao.iface;


import by.hustlestar.bean.entity.News;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * Created by dell on 05.12.2016.
 */
public interface NewsDAO {

    void addNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn) throws DAOException;

    void updateNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn, int newsID) throws DAOException;

    News getNews(int id) throws DAOException;

    void addActorForNews(int actorID, int newsID) throws DAOException;

    void deleteActorForNews(int actorID, int newsID) throws DAOException;

    void addMovieForNews(int intNewsID, int intMovieID) throws DAOException;

    void deleteMovieForNews(int intNewsID, int intMovieID) throws DAOException;

    List<News> getLatestNews() throws DAOException;
}
