package by.hustlestar.service.iface;

import by.hustlestar.bean.entity.News;
import by.hustlestar.service.exception.ServiceException;

import java.util.List;

/**
 * Created by dell on 06.12.2016.
 */
public interface NewsService {
    News viewNews(String id) throws ServiceException;

    List<News> showLatestNews() throws ServiceException;
}
