package by.hustlestar.command.impl.admin;

import by.hustlestar.command.util.QueryUtil;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 06.12.2016.
 */
public class AddNews implements by.hustlestar.command.Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/addNewsPage.jsp";
    private static final String REDIRECT = "Controller?command=add-news";

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String CHARACTER_ENCODING = "UTF-8";

    private static final String NEWS_TITLE_RU = "newsTitleRu";
    private static final String NEWS_TITLE_EN = "newsTitleEn";
    private static final String NEWS_TEXT_RU = "newsTextRu";
    private static final String NEWS_TEXT_EN = "newsTextEn";
    private static final String ACTOR_ID = "actor-id";
    private static final String MOVIE_ID = "id";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot add actor";
    private static final String SUCCESS = "successMessage";
    private static final String MESSAGE_OF_SUCCESS = "Actor successfully added";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        QueryUtil.saveCurrentQueryToSession(request);
        response.setContentType(CONTENT_TYPE);
        request.setCharacterEncoding(CHARACTER_ENCODING);

        String newsTitleRu = request.getParameter(NEWS_TITLE_RU);
        String newsTitleEn = request.getParameter(NEWS_TITLE_EN);
        String newsTextRu = request.getParameter(NEWS_TEXT_RU);
        String newsTextEn = request.getParameter(NEWS_TEXT_EN);
        String actorID = request.getParameter(ACTOR_ID);
        String movieID = request.getParameter(MOVIE_ID);
        AdminService adminService = AdminUtil.getAdminService(request, response);
        if (newsTitleRu == null && newsTitleEn == null && newsTextRu == null
                && newsTextEn ==null && actorID==null && movieID == null) {

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);

        } else {
            try {
                adminService.addNews(newsTitleRu, newsTitleEn, newsTextRu, newsTextEn, actorID, movieID);
                request.setAttribute(SUCCESS, MESSAGE_OF_SUCCESS);
                response.sendRedirect(REDIRECT);
                //request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            } catch (ServiceException e) {
                LOGGER.error(e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            }
        }
    }
}
