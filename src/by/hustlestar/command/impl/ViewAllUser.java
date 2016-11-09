package by.hustlestar.command.impl;

import by.hustlestar.bean.entity.User;
import by.hustlestar.command.Command;
import by.hustlestar.service.AdminService;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Hustler on 08.11.2016.
 */
public class ViewAllUser implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/usersPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";
    private static final Logger LOGGER = LogManager.getLogger();


    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String CHARACTER_ENCODING = "UTF-8";

    private static final String REQUEST_ATTRIBUTE = "all_users";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No users matching your query";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType(CONTENT_TYPE);
        request.setCharacterEncoding(CHARACTER_ENCODING);

        List<User> users;
        AdminService adminService = ServiceFactory.getInstance().getAdminService();

        try {
            users = adminService.showAllUsers();

            request.setAttribute(REQUEST_ATTRIBUTE, users);
            request.getRequestDispatcher(JSP_PAGE_PATH).include(request, response);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            request.setAttribute(ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(ERROR_PAGE).include(request, response);
        }
    }
}
