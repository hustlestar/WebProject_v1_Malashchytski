package by.hustlestar.command.impl.guest;

import by.hustlestar.bean.entity.News;
import by.hustlestar.command.Command;
import by.hustlestar.command.util.CommandsUtil;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.NewsService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ViewNews class is used to handle client request to
 * show particular news.
 */
public class ViewNews implements Command{
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/newsPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final Logger logger = LogManager.getLogger(ViewNews.class);

    private static final String NEWS_ID = "news-id";

    private static final String REQUEST_ATTRIBUTE = "news";
    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No news with such id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandsUtil.saveCurrentQueryToSession(request);

        String id = request.getParameter(NEWS_ID);
        News news;

        NewsService newsService = ServiceFactory.getInstance().getNewsService();
        try {
            news = newsService.getNews(id);

            request.setAttribute(REQUEST_ATTRIBUTE, news);

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);

            request.setAttribute(ERROR, MESSAGE_OF_ERROR);

            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
