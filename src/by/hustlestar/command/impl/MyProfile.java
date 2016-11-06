package by.hustlestar.command.impl;

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
    private static final String USER = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Object obj = request.getSession(true).getAttribute(USER);
        if (obj!=null && obj instanceof User){
            request.getRequestDispatcher("WEB-INF/jsp/profilePage.jsp").include(request, response);
        }else {
            response.sendRedirect("WEB-INF/jsp/loginPage.jsp");
        }
    }
}
