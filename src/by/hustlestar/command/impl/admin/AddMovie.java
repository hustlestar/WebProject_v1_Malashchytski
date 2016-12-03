package by.hustlestar.command.impl.admin;

import by.hustlestar.command.Command;
import by.hustlestar.command.util.CommandsUtil;
import by.hustlestar.command.util.QueryUtil;
import by.hustlestar.service.iface.AdminService;
import by.hustlestar.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hustler on 06.11.2016.
 */
public class AddMovie implements Command {

    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/addMoviePage.jsp";

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String CHARACTER_ENCODING = "UTF-8";

    private static final String TITLE_RU = "titleRu";
    private static final String TITLE_EN = "titleEn";
    private static final String YEAR = "year";
    private static final String BUDGET = "budget";
    private static final String GROSS = "gross";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot add movie";
    private static final String SUCCESS = "successMessage";
    private static final String MESSAGE_OF_SUCCESS = "Movie successfully added";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        QueryUtil.saveCurrentQueryToSession(request);
        response.setContentType(CONTENT_TYPE);
        request.setCharacterEncoding(CHARACTER_ENCODING);

        String titleRu = request.getParameter(TITLE_RU);
        String titleEn = request.getParameter(TITLE_EN);
        String year = request.getParameter(YEAR);
        String budget = request.getParameter(BUDGET);
        String gross = request.getParameter(GROSS);
        AdminService adminService = AdminUtil.getAdminService(request, response);

        if (titleRu == null && titleEn == null && year == null && budget == null && gross == null) {

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);

        } else {
            try {
                adminService.addMovie(titleRu, titleEn, year, budget, gross);
                request.setAttribute(SUCCESS, MESSAGE_OF_SUCCESS);
                //response.sendRedirect(JSP_PAGE_PATH);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            } catch (ServiceException e) {
                LOGGER.error(e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            }
        }
    }
}
