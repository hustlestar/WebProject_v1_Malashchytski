package by.hustlestar.command.impl.guest;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.command.Command;
import by.hustlestar.command.util.QueryUtil;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.MovieService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Hustler on 18.11.2016.
 */
public class ShowMoviesOfYear implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/moviesPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final Logger logger = LogManager.getLogger(ShowMoviesOfYear.class);

    private static final String YEAR = "year";

    private static final String REQUEST_ATTRIBUTE = "all_movies";
    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No movies of this year";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        QueryUtil.saveCurrentQueryToSession(request);

        String year = request.getParameter(YEAR);

        List<Movie> movieList;

        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        try {
            movieList = movieService.showMoviesOfYear(year);

            request.setAttribute(REQUEST_ATTRIBUTE, movieList);

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);

            request.setAttribute(ERROR, MESSAGE_OF_ERROR);

            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
