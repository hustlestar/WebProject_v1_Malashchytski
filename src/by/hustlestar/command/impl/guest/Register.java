package by.hustlestar.command.impl.guest;

import by.hustlestar.bean.entity.User;
import by.hustlestar.command.Command;
import by.hustlestar.command.util.CommandsUtil;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.iface.UserService;
import by.hustlestar.service.exception.ServiceAuthException;
import by.hustlestar.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/**
 * Register is used to handle register request from client.
 */
public class Register implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/registerPage.jsp";

    private static final Logger logger = LogManager.getLogger(Register.class);

    private static final String USER = "user";

    private static final String LOGIN = "nickname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "pass";
    private static final String PASSWORD_2 = "pass2";
    private static final String SEX = "sex";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR_1 = "All fields should be filled";
    private static final String MESSAGE_OF_ERROR_2 = "Wrong login or password";
    private static final String MESSAGE_OF_ERROR_3 = "Login and password should be at least 6 characters";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(LOGIN);
        String email = request.getParameter(EMAIL);
        byte[] password = request.getParameter(PASSWORD).getBytes();
        byte[] password2 = request.getParameter(PASSWORD_2).getBytes();
        String sex = request.getParameter(SEX);
        UserService userService = ServiceFactory.getInstance().getUserService();
        String previousQuery = CommandsUtil.getPreviousQuery(request);
        HttpSession session = request.getSession(true);

        if (login != null && email != null && password.length>0 && password2.length>0 && sex != null) {
            try {
                User user = userService.register(login, email, password, password2, sex);
                Arrays.fill(password, (byte) 0);
                Arrays.fill(password2, (byte) 0);

                session.setAttribute(USER, user);

                response.sendRedirect(previousQuery);
            } catch (ServiceAuthException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR_1);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_3);
            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        }
    }
}
