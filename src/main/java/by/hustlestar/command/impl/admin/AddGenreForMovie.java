package by.hustlestar.command.impl.admin;

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
 * AddGenre class is used to handle client request to
 * add any genre for a particular movie.
 */
public class AddGenreForMovie implements by.hustlestar.command.Command {

    private static final Logger logger = LogManager.getLogger(AddGenreForMovie.class);

    private static final String ID = "id";
    private static final String NAME_RU = "nameRu";
    private static final String NAME_EN = "nameEn";

    private static final String ERROR = "errorCountry";
    private static final String MESSAGE_OF_ERROR = "Cannot add country for movie";
    private static final String MESSAGE_OF_ERROR_2 = "Cannot add country for movie, wrong input";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String movieID = request.getParameter(ID);
        String nameRu = request.getParameter(NAME_RU);
        String nameEn = request.getParameter(NAME_EN);
        String previousQuery = CommandsUtil.getPreviousQuery(request);

        AdminService adminService = AdminUtil.getAdminService(request, response);

        if (nameRu != null && nameEn != null && movieID !=null) {
            try {
                adminService.addGenreForMovie(movieID, nameRu, nameEn);

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
