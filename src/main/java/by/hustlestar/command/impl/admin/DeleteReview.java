package by.hustlestar.command.impl.admin;

import by.hustlestar.command.util.CommandsUtil;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.AdminService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DeleteReview class is used to handle client request to
 * remove any review from the system.
 */
public class DeleteReview implements by.hustlestar.command.Command {
    private static final Logger logger = LogManager.getLogger(DeleteReview.class);
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final String MOVIE_ID = "movieID";
    private static final String USER_NICKNAME = "userNickname";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot delete review";
    private static final String MESSAGE_OF_ERROR_2 = "Fill in all fields";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String previousQuery = CommandsUtil.getPreviousQuery(request);

        String movieID = request.getParameter(MOVIE_ID);
        String userNickname = request.getParameter(USER_NICKNAME);

        AdminService adminService = AdminUtil.getAdminService(request, response);

        if (movieID != null && userNickname != null) {
            try {
                adminService.deleteReview(movieID, userNickname);
                response.sendRedirect(previousQuery);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
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
