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
 * Created by Hustler on 07.11.2016.
 */
public class AddReview implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String WELCOME_PAGE = "index.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String CHARACTER_ENCODING = "UTF-8";


    private static final String MOVIE_ID = "movieID";
    private static final String USER_NICKNAME = "userNickname";
    private static final String REVIEW = "review";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot add review to movie";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType(CONTENT_TYPE);
        request.setCharacterEncoding(CHARACTER_ENCODING);

        String movieID = request.getParameter(MOVIE_ID);
        String userNickname = request.getParameter(USER_NICKNAME);
        String review = request.getParameter(REVIEW);
        MovieService movieService = ServiceFactory.getInstance().getMovieService();

        if (movieID != null && userNickname != null && review != null) {
            try {
                movieService.addReview(movieID, userNickname, review);
                request.getRequestDispatcher(WELCOME_PAGE).include(request, response);
            } catch (ServiceException e) {
                LOGGER.error(e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                //redirect on same page should be and print error
                request.getRequestDispatcher(ERROR_PAGE).include(request, response);
            }
        } else {
            QueryUtil.saveCurrentQueryToSession(request);
        }
    }
}
