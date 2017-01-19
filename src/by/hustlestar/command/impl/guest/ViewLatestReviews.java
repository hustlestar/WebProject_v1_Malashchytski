package by.hustlestar.command.impl.guest;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.command.util.CommandsUtil;
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
 * ViewLatestReviews class is used to handle client request to
 * show the latest reviews.
 */
public class ViewLatestReviews implements by.hustlestar.command.Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/latestReviewsPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final Logger logger = LogManager.getLogger(ViewLatestReviews.class);

    private static final String REQUEST_ATTRIBUTE = "latest_movies_reviews";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No news matching your query";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandsUtil.saveCurrentQueryToSession(request);

        List<Movie> movies;
        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        String lang = (String) CommandsUtil.getLanguage(request);

        try {
            movies = movieService.getLatestReviews(lang);
            request.setAttribute(REQUEST_ATTRIBUTE, movies);
            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
