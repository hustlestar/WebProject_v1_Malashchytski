package by.hustlestar.command.impl.admin;

import by.hustlestar.bean.entity.Actor;
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
import java.util.List;

/**
 * ViewAllActors class is used to handle client request to
 * show all actors in the system.
 */
public class ViewAllActors implements by.hustlestar.command.Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/actorsPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final Logger logger = LogManager.getLogger(ViewAllActors.class);

    private static final String REQUEST_ATTRIBUTE = "all_actors";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No actors matching your query";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandsUtil.saveCurrentQueryToSession(request);

        List<Actor> actors;
        AdminService adminService = AdminUtil.getAdminService(request, response);

        try {
            actors = adminService.showAllActors();

            request.setAttribute(REQUEST_ATTRIBUTE, actors);
            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
