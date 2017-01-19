package by.hustlestar.command.impl.guest;

import by.hustlestar.bean.entity.User;
import by.hustlestar.command.util.CommandsUtil;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ViewTopUsers class is used to handle client request to
 * show the best users by their reputation.
 */
public class ViewTopUsers implements by.hustlestar.command.Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/usersPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final Logger logger = LogManager.getLogger(ViewTopUsers.class);


    private static final String REQUEST_ATTRIBUTE = "all_users";
    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No user with such nickname";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandsUtil.saveCurrentQueryToSession(request);

        List<User> users;

        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            users = userService.getTopUsers();

            request.setAttribute(REQUEST_ATTRIBUTE, users);

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
