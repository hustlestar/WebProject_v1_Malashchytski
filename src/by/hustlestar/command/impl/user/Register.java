package by.hustlestar.command.impl.user;

import by.hustlestar.bean.entity.User;
import by.hustlestar.command.Command;
import by.hustlestar.command.util.QueryUtil;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.iface.UserService;
import by.hustlestar.service.exception.ServiceAuthException;
import by.hustlestar.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hustler on 28.10.2016.
 */
public class Register implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/registerPage.jsp";
    private static final String WELCOME_PAGE = "index.jsp";

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String USER = "user";

    private static final String LOGIN = "nickname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "pass";
    private static final String SEX = "sex";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR_1 = "All fields should be filled";
    private static final String MESSAGE_OF_ERROR_2 = "Wrong login or password";
    private static final String MESSAGE_OF_ERROR_3 = "Login and password should be at least 6 characters";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(LOGIN);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String sex = request.getParameter(SEX);
        UserService userService = ServiceFactory.getInstance().getUserService();

        if (login != null && email != null && password != null) {
            try {
                User user = userService.register(login, email, password, sex);

                request.setAttribute(USER, user);

                request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);
                System.out.println("vse ok");
            } catch (ServiceAuthException e) {
                LOGGER.error(e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR_1);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            } catch (ServiceException e) {
                LOGGER.error(e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_3);
            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        }
    }
}
