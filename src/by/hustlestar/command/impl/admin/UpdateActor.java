package by.hustlestar.command.impl.admin;

import by.hustlestar.command.Command;
import by.hustlestar.command.util.QueryUtil;
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
 * Created by dell on 06.12.2016.
 */
public class UpdateActor implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/addActorPage.jsp";
    private static final String REDIRECT = "Controller?command=add-actor";

    private static final Logger logger = LogManager.getLogger(UpdateActor.class);

    private static final String ACTOR_ID = "actor-id";
    private static final String NAME_RU = "nameRu";
    private static final String NAME_EN = "nameEn";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot add movie";
    private static final String SUCCESS = "successMessage";
    private static final String MESSAGE_OF_SUCCESS = "Actor successfully added";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        QueryUtil.saveCurrentQueryToSession(request);

        String actorID = request.getParameter(ACTOR_ID);
        String nameRu = request.getParameter(NAME_RU);
        String nameEn = request.getParameter(NAME_EN);
        AdminService adminService = AdminUtil.getAdminService(request, response);
        if (nameRu == null && nameEn == null) {

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);

        } else {
            try {
                adminService.updateActor(actorID, nameRu, nameEn);
                request.setAttribute(SUCCESS, MESSAGE_OF_SUCCESS);
                response.sendRedirect(REDIRECT);
                //request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            }
        }
    }
}
