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
 * DeleteGenreForMovie class is used to handle client request to
 * remove any genre from a cast of a particular movie.
 */
public class DeleteGenreForMovie implements by.hustlestar.command.Command {
    private static final Logger logger = LogManager.getLogger(DeleteGenreForMovie.class);

    private static final String MOVIE_ID = "id";
    private static final String GENRE = "genre";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot delete review";
    private static final String MESSAGE_OF_ERROR_2 = "Fill in all fields";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String previousQuery = CommandsUtil.getPreviousQuery(request);

        String movieID = request.getParameter(MOVIE_ID);
        String nameEn = request.getParameter(GENRE);


        AdminService adminService = AdminUtil.getAdminService(request, response);

        if (movieID != null && nameEn != null) {
            try {
                adminService.deleteGenreForMovie(movieID, nameEn);
                //response.sendRedirect(previousQuery);
                response.sendRedirect(previousQuery);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                //redirect on same page should be and print error
                request.getRequestDispatcher(previousQuery).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
            //redirect on same page should be and print error
            request.getRequestDispatcher(previousQuery).forward(request, response);
        }
    }
}
