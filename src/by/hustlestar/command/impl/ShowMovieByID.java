package by.hustlestar.command.impl;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.command.Command;
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
 * Created by Hustler on 06.11.2016.
 */
public class ShowMovieByID implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/moviePage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";
    private static final Logger LOGGER = LogManager.getLogger();


    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String CHARACTER_ENCODING = "UTF-8";

    private static final String ID = "id";

    private static final String REQUEST_ATTRIBUTE = "movie";
    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No movie with such id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType(CONTENT_TYPE);
        request.setCharacterEncoding(CHARACTER_ENCODING);
        String id = request.getParameter(ID);
        Movie movie;
        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        try {
            movie = movieService.showMovieByID(id);
            /*for (Movie movie : movies) {
                System.out.println(movie.getTitle() + " - " + movie.getYear());
            }*/
            request.setAttribute(REQUEST_ATTRIBUTE, movie);
            request.getRequestDispatcher(JSP_PAGE_PATH).include(request, response);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            request.setAttribute(ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
