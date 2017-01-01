package by.hustlestar.command.impl.movie;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.command.Command;
import by.hustlestar.command.util.CommandsUtil;
import by.hustlestar.command.util.QueryUtil;
import by.hustlestar.service.iface.MovieService;
import by.hustlestar.service.ServiceFactory;
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
public class ShowMovieByID implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/moviePage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";
    private static final Logger LOGGER = LogManager.getLogger();


    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String CHARACTER_ENCODING = "UTF-8";

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
        QueryUtil.saveCurrentQueryToSession(request);
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CHARACTER_ENCODING);
        request.setCharacterEncoding(CHARACTER_ENCODING);

        String id = request.getParameter(ID);
        String lang = (String) CommandsUtil.getLanguage(request);
        Movie movie;

        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        try {
            int page = 1;
            if (request.getParameter(PAGE) != null) {
                page = Integer.parseInt(request.getParameter(PAGE));
            }

            movie = movieService.showMovieByID((page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE, id, lang);

            int numberOfReviews = movieService.countReviewsForMovie(id, lang);
            int noOfPages = (int) Math.ceil(numberOfReviews * 1.0 / RECORDS_PER_PAGE);
            request.setAttribute(AMOUNT_OF_REVIEWS, numberOfReviews);
            request.setAttribute(AMOUNT_OF_PAGES, noOfPages);
            request.setAttribute(CURRENT_PAGE, page);

            request.setAttribute(REQUEST_ATTRIBUTE, movie);

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);

            request.setAttribute(ERROR, MESSAGE_OF_ERROR);

            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }


}
