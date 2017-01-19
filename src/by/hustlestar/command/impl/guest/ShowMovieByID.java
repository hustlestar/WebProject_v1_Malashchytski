package by.hustlestar.command.impl.guest;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.command.Command;
import by.hustlestar.command.util.CommandsUtil;
import by.hustlestar.service.iface.MovieService;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ShowMoviesByID class is used to handle client request to show
 * a particular movie.
 */
public class ShowMovieByID implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/moviePage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final Logger logger = LogManager.getLogger(ShowMovieByID.class);

    private static final String PAGE = "page";
    private static final String AMOUNT_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";
    private static final int RECORDS_PER_PAGE = 5;
    private static final String AMOUNT_OF_REVIEWS = "noOfReviews";

    private static final String ID = "id";

    private static final String REQUEST_ATTRIBUTE = "movie";
    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No movie with such id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandsUtil.saveCurrentQueryToSession(request);

        String id = request.getParameter(ID);
        String lang = (String) CommandsUtil.getLanguage(request);
        Movie movie;

        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        try {
            int page = 1;
            if (request.getParameter(PAGE) != null) {
                page = Integer.parseInt(request.getParameter(PAGE));
            }

            movie = movieService.getMovieByID((page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE, id, lang);

            int numberOfReviews = movieService.countReviewsForMovie(id, lang);
            int noOfPages = (int) Math.ceil(numberOfReviews * 1.0 / RECORDS_PER_PAGE);
            request.setAttribute(AMOUNT_OF_REVIEWS, numberOfReviews);
            request.setAttribute(AMOUNT_OF_PAGES, noOfPages);
            request.setAttribute(CURRENT_PAGE, page);

            request.setAttribute(REQUEST_ATTRIBUTE, movie);

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);

            request.setAttribute(ERROR, MESSAGE_OF_ERROR);

            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }


}
