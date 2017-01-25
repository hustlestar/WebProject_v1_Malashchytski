package by.hustlestar.command.impl.user;

import by.hustlestar.bean.entity.User;
import by.hustlestar.command.Command;
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

/**
 * LikeReview class is used to handle client request to like or
 * dislike the other user review.
 */
public class LikeReview implements Command {
    private static final Logger logger = LogManager.getLogger(LikeReview.class);

    private static final String MOVIE_ID = "movieID";
    private static final String REVIEWER = "reviewer";
    private static final String SCORE = "score";
    private static final String USER = "user";

    private static final String ERROR_LIKE_REVIEW = "errorLikeReview";
    private static final String MESSAGE_OF_ERROR = "Score wasn't added, you cannot vote twice or for your own review";
    private static final String MESSAGE_OF_ERROR_2 = "Some data is missing";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String previousQuery = CommandsUtil.getPreviousQuery(request);

        String movieID = request.getParameter(MOVIE_ID);
        String reviewerNickname = request.getParameter(REVIEWER);
        String score = request.getParameter(SCORE);
        String userNickname = null;

        Object object = request.getSession(false).getAttribute(USER);

        if (object.getClass().equals(User.class)) {
            User user = (User) object;
            userNickname = user.getNickname();
        }

        MovieService movieService = ServiceFactory.getInstance().getMovieService();

        if (movieID != null && userNickname != null && score != null && reviewerNickname != null) {
            try {
                System.out.println(movieID+" "+ reviewerNickname+" "+score+" "+userNickname);
                movieService.likeReview(movieID, reviewerNickname, score, userNickname);
                response.sendRedirect(previousQuery);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR_LIKE_REVIEW, MESSAGE_OF_ERROR);
                //response.sendRedirect(previousQuery);
                request.getRequestDispatcher(previousQuery).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR_LIKE_REVIEW, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(previousQuery).forward(request, response);
        }
    }
}
