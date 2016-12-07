package by.hustlestar.command.impl.news;

import by.hustlestar.bean.entity.News;
import by.hustlestar.command.Command;
import by.hustlestar.command.util.QueryUtil;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.NewsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 06.12.2016.
 */
public class ViewNews implements Command{
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/newsPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";
    private static final Logger LOGGER = LogManager.getLogger();


    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String CHARACTER_ENCODING = "UTF-8";

    private static final String NEWS_ID = "news-id";

    private static final String REQUEST_ATTRIBUTE = "news";
    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No news with such id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        QueryUtil.saveCurrentQueryToSession(request);
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CHARACTER_ENCODING);
        request.setCharacterEncoding(CHARACTER_ENCODING);

        String id = request.getParameter(NEWS_ID);
        News news;

        NewsService newsService = ServiceFactory.getInstance().getNewsService();
        try {
            news = newsService.viewNews(id);

            request.setAttribute(REQUEST_ATTRIBUTE, news);

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);

            request.setAttribute(ERROR, MESSAGE_OF_ERROR);

            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
