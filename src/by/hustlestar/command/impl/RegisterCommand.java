package by.hustlestar.command.impl;

import by.hustlestar.bean.entity.User;
import by.hustlestar.command.Command;
import by.hustlestar.command.util.QueryUtil;
import by.hustlestar.service.RegisterService;
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
 * Created by Hustler on 28.10.2016.
 */
public class RegisterCommand implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/registerPage.jsp";
    private static final Logger logger = LogManager.getLogger();

    private static final String LOGIN = "nickname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "pass";
    private static final String SEX = "sex";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(LOGIN);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String sex = request.getParameter(SEX);
        RegisterService registerService = ServiceFactory.getInstance().getRegisterService();

        if (login != null && email != null && password != null) {
            try {
                User user = registerService.register(login, email, password, sex);

                request.setAttribute("user", user);

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
        request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
    }
}
