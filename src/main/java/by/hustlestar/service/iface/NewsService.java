package by.hustlestar.service.iface;

import by.hustlestar.bean.entity.News;
import by.hustlestar.service.exception.ServiceException;

import java.util.List;

/**
 * NewsService is used to interact with News bean entity mainly.
 */
public interface NewsService {

    /**
     * This method is used to show any used by its id.
     *
     * @param id id of news
     * @return News bean entity with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    News getNews(String id) throws ServiceException;

    /**
     * This method is used show several the most latest news.
     *
     * @return list of News bean entities with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    List<News> getLatestNews() throws ServiceException;
}
