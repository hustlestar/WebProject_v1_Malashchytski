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
 * DeleteActorForNews class is used to handle client request to
 * remove any actor from a particular actor.
 */
public class DeleteActorForNews implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteActorForNews.class);

    private static final String NEWS_ID = "news-id";
    private static final String ACTOR_ID = "actor-id";

    private static final String ERROR = "errorActor";
    private static final String MESSAGE_OF_ERROR = "Cannot delete actor for movie";
    private static final String MESSAGE_OF_ERROR_2 = "Cannot delete actor for movie, wrong input";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String newsID = request.getParameter(NEWS_ID);
        String actorID = request.getParameter(ACTOR_ID);
        String previousQuery = CommandsUtil.getPreviousQuery(request);

        AdminService adminService = AdminUtil.getAdminService(request, response);

        if (actorID != null && newsID !=null) {
            try {
                adminService.deleteActorForNews(actorID, newsID);

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
