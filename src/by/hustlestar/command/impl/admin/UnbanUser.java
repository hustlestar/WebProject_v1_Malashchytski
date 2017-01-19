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
 * UnbanUser class is used to handle client request to give
 * back access to the system for some user.
 */
public class UnbanUser implements by.hustlestar.command.Command {
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final Logger logger = LogManager.getLogger(UnbanUser.class);

    private static final String USER_NICKNAME = "userNickname";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot unban user";
    private static final String MESSAGE_OF_ERROR_2 = "You don't have permission to do that";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String userNickname = request.getParameter(USER_NICKNAME);

        String previousQuery = CommandsUtil.getPreviousQuery(request);

        /*admin rights check*/
        AdminService adminService= AdminUtil.getAdminService(request, response);

        if (userNickname != null) {
            try {
                adminService.unbanUser(userNickname);
                request.getRequestDispatcher(previousQuery).forward(request, response);

            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
