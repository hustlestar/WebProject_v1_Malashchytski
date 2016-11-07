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
 * Created by Hustler on 07.11.2016.
 */
public class AddReview implements Command {

    //private static final String JSP_PAGE_PATH = "WEB-INF/jsp/addMoviePage.jsp";
    private static final Logger logger = LogManager.getLogger();

    private static final String MOVIE_ID = "movieID";
    private static final String USER_NICKNAME = "userNickname";
    private static final String REVIEW = "review";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String movieID = request.getParameter(MOVIE_ID);
        String userNickname = request.getParameter(USER_NICKNAME);
        String review = request.getParameter(REVIEW);
        System.out.println(movieID+" "+userNickname+" "+review);
        MovieService movieService = ServiceFactory.getInstance().getMovieService();

        if (movieID != null && userNickname != null && review != null) {
            try {
                movieService.addReview(movieID, userNickname, review);
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
        //request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
    }
}
