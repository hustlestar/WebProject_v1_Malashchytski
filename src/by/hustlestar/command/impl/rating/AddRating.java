package by.hustlestar.command.impl.rating;

import by.hustlestar.bean.entity.User;
import by.hustlestar.command.Command;
import by.hustlestar.command.util.CommandsUtil;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hustler on 17.11.2016.
 */
public class AddRating implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String CHARACTER_ENCODING = "UTF-8";

    private static final String MOVIE_ID = "movieID";
    private static final String USER = "user";
    private static final String RATING = "rating";

    private static final String ERROR_RATING = "errorRating";
    private static final String MESSAGE_OF_ERROR = "Cannot add rating to movie";
    private static final String MESSAGE_OF_ERROR_2 = "Fill in all fields";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType(CONTENT_TYPE);
        request.setCharacterEncoding(CHARACTER_ENCODING);

        String previousQuery = CommandsUtil.getPreviousQuery(request);

        String movieID = request.getParameter(MOVIE_ID);
        String userNickname = null;

        Object object = request.getSession(false).getAttribute(USER);
        if (object instanceof User){
            User user = (User) object;
            userNickname = user.getNickname();
        }
        String rating = request.getParameter(RATING);
        MovieService movieService = ServiceFactory.getInstance().getMovieService();

        if (movieID != null && userNickname != null && rating != null) {
            try {
                movieService.addRating(movieID, userNickname, rating);

                request.getRequestDispatcher(previousQuery).forward(request, response);
            } catch (ServiceException e) {
                LOGGER.error(e.getMessage(), e);
                request.setAttribute(ERROR_RATING, MESSAGE_OF_ERROR);

                request.getRequestDispatcher(previousQuery).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR_RATING, MESSAGE_OF_ERROR_2);

            request.getRequestDispatcher(previousQuery).forward(request, response);
        }
    }
}
