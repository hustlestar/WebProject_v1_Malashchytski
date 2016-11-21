package by.hustlestar.command.impl.review;

import by.hustlestar.bean.entity.User;
import by.hustlestar.command.Command;
import by.hustlestar.command.util.QueryUtil;
import by.hustlestar.service.iface.MovieService;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mozilla.universalchardet.UniversalDetector;

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


    private static final String PREVIOUS_QUERY = "previousQuery";

    private static final String MOVIE_ID = "movieID";
    private static final String USER = "user";
    private static final String REVIEW = "review";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot add review to movie";
    private static final String MESSAGE_OF_ERROR_2 = "Fill in all fields";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType(CONTENT_TYPE);
        request.setCharacterEncoding(CHARACTER_ENCODING);

        String review = request.getParameter(REVIEW);

        System.out.println(review);
        byte[] buf = review.getBytes();


        UniversalDetector detector = new UniversalDetector(null);

        for (byte b : buf) {
            detector.handleData(buf, 0, buf.length);
        }
        detector.dataEnd();

        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            System.out.println("Detected encoding = " + encoding);
        } else {
            System.out.println("No encoding detected.");
        }

        String previousQuery = (String) request.getSession(false).getAttribute(PREVIOUS_QUERY);
        if (previousQuery == null) {
            previousQuery = WELCOME_PAGE;
        }

        String movieID = request.getParameter(MOVIE_ID);
        String userNickname = null;

        Object object = request.getSession(false).getAttribute(USER);
        if (object instanceof User){
            User user = (User) object;
            userNickname = user.getNickname();
        }
        //String review = request.getParameter(REVIEW);
        MovieService movieService = ServiceFactory.getInstance().getMovieService();

        if (movieID != null && userNickname != null && review != null) {
            try {
                movieService.addReview(movieID, userNickname, new String(buf, "WINDOWS-1251"));
                //response.sendRedirect(previousQuery);
                request.getRequestDispatcher(previousQuery).forward(request, response);
            } catch (ServiceException e) {
                LOGGER.error(e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                //redirect on same page should be and print error
                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
            //redirect on same page should be and print error
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
