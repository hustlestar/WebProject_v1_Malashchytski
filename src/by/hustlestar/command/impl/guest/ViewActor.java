package by.hustlestar.command.impl.guest;

import by.hustlestar.bean.entity.Actor;
import by.hustlestar.command.util.CommandsUtil;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.ActorService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ViewActor class is used to handle client request to
 * show page of the particular actor.
 */
public class ViewActor implements by.hustlestar.command.Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/actorPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final Logger logger = LogManager.getLogger(ViewActor.class);

    private static final String ACTOR_ID = "actor-id";

    private static final String REQUEST_ATTRIBUTE = "actor";
    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No movie with such id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandsUtil.saveCurrentQueryToSession(request);

        String id = request.getParameter(ACTOR_ID);
        Actor actor;

        ActorService actorService = ServiceFactory.getInstance().getActorService();
        try {
            actor = actorService.getActor(id);

            request.setAttribute(REQUEST_ATTRIBUTE, actor);

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);

            request.setAttribute(ERROR, MESSAGE_OF_ERROR);

            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
