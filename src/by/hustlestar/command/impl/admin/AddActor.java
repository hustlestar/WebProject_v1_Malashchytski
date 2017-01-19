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
 * AddActor class is used to handle client request to
 * add new actor,
 */
public class AddActor implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/addActorPage.jsp";
    private static final String REDIRECT = "Controller?command=add-actor";

    private static final Logger logger = LogManager.getLogger(AddActor.class);

    private static final String NAME_RU = "nameRu";
    private static final String NAME_EN = "nameEn";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot add actor";
    private static final String SUCCESS = "successMessage";
    private static final String MESSAGE_OF_SUCCESS = "Actor successfully added";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandsUtil.saveCurrentQueryToSession(request);

        String nameRu = request.getParameter(NAME_RU);
        String nameEn = request.getParameter(NAME_EN);
        AdminService adminService = AdminUtil.getAdminService(request, response);
        if (nameRu == null && nameEn == null) {

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);

        } else {
            try {
                adminService.addActor(nameRu, nameEn);
                request.setAttribute(SUCCESS, MESSAGE_OF_SUCCESS);
                response.sendRedirect(REDIRECT);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            }
        }
    }
}
