package by.hustlestar.command.impl.user;

import by.hustlestar.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Logout class is used to handle client request to
 * terminate signed-in session.
 */
public class Logout implements Command {
    private static final String WELCOME_PAGE = "index.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            response.sendRedirect(WELCOME_PAGE);
        }
    }
}
