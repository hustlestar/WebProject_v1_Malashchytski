package by.hustlestar.dao.iface;


import by.hustlestar.bean.entity.News;
import by.hustlestar.dao.exception.DAOException;

/**
 * Created by dell on 05.12.2016.
 */
public interface NewsDAO {

    void addNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn, int actorID, int movieID) throws DAOException;

    void updateNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn, int actorID, int movieID, int newsID) throws DAOException;

    News getNews(int id) throws DAOException;
}
