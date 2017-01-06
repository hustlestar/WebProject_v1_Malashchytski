package by.hustlestar.command.impl.user;

import by.hustlestar.bean.entity.User;
import by.hustlestar.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hustler on 06.11.2016.
 */
public class MyProfile implements Command {
    private static final String PROFILE_PAGE_PATH = "WEB-INF/jsp/profilePage.jsp";
    private static final String LOGIN_PAGE = "WEB-INF/jsp/loginPage.jsp";

    private static final String USER = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Object obj = request.getSession(true).getAttribute(USER);
        if (obj != null && obj.getClass().equals(User.class)) {
            request.getRequestDispatcher(PROFILE_PAGE_PATH).forward(request, response);
        } else {
            response.sendRedirect(LOGIN_PAGE);
        }
    }
}
