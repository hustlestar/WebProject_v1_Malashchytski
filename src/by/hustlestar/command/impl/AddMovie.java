package by.hustlestar.command.impl;

import by.hustlestar.command.Command;
import by.hustlestar.command.util.QueryUtil;
import by.hustlestar.service.MovieService;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceAuthException;
import by.hustlestar.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hustler on 06.11.2016.
 */
public class AddMovie implements Command {

    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/addMoviePage.jsp";
    private static final Logger logger = LogManager.getLogger();

    private static final String TITLE = "title";
    private static final String YEAR = "year";
    private static final String BUDGET = "budget";
    private static final String GROSS = "gross";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter(TITLE);
        String year = request.getParameter(YEAR);
        String budget = request.getParameter(BUDGET);
        String gross = request.getParameter(GROSS);
        System.out.println(title);
        MovieService movieService = ServiceFactory.getInstance().getMovieService();

        if (title != null && year != null && budget != null && gross !=null) {
            try {
                movieService.addMovie(title, year, budget, gross);

                request.getRequestDispatcher("index.jsp").include(request, response);
                System.out.println("vse ok");
            } catch (ServiceAuthException e) {
                logger.error(e.getMessage(), e);
                request.setAttribute("errorMessage", "Wrong login or password");
                request.getRequestDispatcher("index.jsp").include(request, response);

            } catch (ServiceException e) {
                logger.error(e.getMessage(), e);
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            QueryUtil.saveCurrentQueryToSession(request);
        }
        request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
    }
}
