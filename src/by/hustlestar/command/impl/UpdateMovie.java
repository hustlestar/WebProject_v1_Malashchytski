package by.hustlestar.command.impl;

import by.hustlestar.command.Command;
import by.hustlestar.command.util.QueryUtil;
import by.hustlestar.service.MovieService;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hustler on 08.11.2016.
 */
public class UpdateMovie implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/addMoviePage.jsp";
    private static final String WELCOME_PAGE = "index.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String CHARACTER_ENCODING = "UTF-8";

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String YEAR = "year";
    private static final String BUDGET = "budget";
    private static final String GROSS = "gross";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot update movie";
    private static final String MESSAGE_OF_ERROR_2 = "Cannot update movie, wrong data";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType(CONTENT_TYPE);
        request.setCharacterEncoding(CHARACTER_ENCODING);

        String id = request.getParameter(ID);
        String title = request.getParameter(TITLE);
        String year = request.getParameter(YEAR);
        String budget = request.getParameter(BUDGET);
        String gross = request.getParameter(GROSS);
        System.out.println(title);
        MovieService movieService = ServiceFactory.getInstance().getMovieService();

        if (id!=null && title != null && year != null && budget != null && gross != null) {
            try {
                movieService.updateMovie(id, title, year, budget, gross);
                request.getRequestDispatcher(JSP_PAGE_PATH).include(request, response);

            } catch (ServiceException e) {
                LOGGER.error(e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(ERROR_PAGE).include(request, response);
            }
        } else {
            QueryUtil.saveCurrentQueryToSession(request);
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(ERROR_PAGE).include(request, response);
        }
    }
}
