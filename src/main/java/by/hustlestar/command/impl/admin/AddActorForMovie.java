package by.hustlestar.command.impl.admin;

import by.hustlestar.command.Command;
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
 * AddActorForMovie class is used to handle client request to
 * add actor for a particular movie.
 */
public class AddActorForMovie implements Command {
    private static final Logger logger = LogManager.getLogger(AddActorForMovie.class);

    private static final String ID = "id";
    private static final String ACTOR_ID = "actor-id";

    private static final String ERROR = "errorActor";
    private static final String MESSAGE_OF_ERROR = "Cannot add actor for movie";
    private static final String MESSAGE_OF_ERROR_2 = "Cannot add actor for movie, wrong input";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String movieID = request.getParameter(ID);
        String actorID = request.getParameter(ACTOR_ID);
        String previousQuery = CommandsUtil.getPreviousQuery(request);

        AdminService adminService = AdminUtil.getAdminService(request, response);

        if (actorID != null && movieID !=null) {
            try {
                adminService.addActorForMovie(actorID, movieID);

                response.sendRedirect(previousQuery);
            }  catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(previousQuery).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(previousQuery).forward(request, response);
        }
    }
}
