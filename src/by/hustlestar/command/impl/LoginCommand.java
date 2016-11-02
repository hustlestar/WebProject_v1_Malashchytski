package by.hustlestar.command.impl;

import by.hustlestar.bean.entity.User;
import by.hustlestar.command.Command;
import by.hustlestar.command.util.QueryUtil;
import by.hustlestar.service.LoginService;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceAuthException;
import by.hustlestar.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Hustler on 28.10.2016.
 */
public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN = "nickname";
    private static final String PASSWORD = "pass";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        LoginService loginService = ServiceFactory.getInstance().getLoginService();
        HttpSession session = request.getSession(true);
        while (session.getAttributeNames().hasMoreElements()) {
            System.out.println(session.getAttributeNames().nextElement());
        }
        /*String query = QueryUtil.createHttpQueryString(request);
        request.getSession(true).setAttribute("prev_query", query);

        System.out.println(query);*/

        try {
            User user = loginService.authorize(login, password);

            request.getSession(true).setAttribute("user", user.getNickname());

            request.getRequestDispatcher("index.jsp").include(request, response);
        } catch (ServiceAuthException e) {
            logger.error(e.getMessage(), e);
            request.setAttribute("errorMessage", "Wrong login or password");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }
}
