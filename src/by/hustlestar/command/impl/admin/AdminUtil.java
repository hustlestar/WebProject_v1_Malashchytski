package by.hustlestar.command.impl.admin;

import by.hustlestar.bean.entity.User;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.iface.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AdminUtil is a helper class for commands in admin package.
 */
class AdminUtil {

    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final String USER = "user";
    private static final String MODER = "moder";
    private static final String ADMIN = "admin";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot perform that action";
    private static final String MESSAGE_OF_ERROR_2 = "You don't have permission to do that";

    /**
     * This method is used to give Admin Service if user is admin or moderator
     * in other case return null
     * @param request
     * @param response
     * @return AdminService object
     * @throws ServletException
     * @throws IOException
     */
    static AdminService getAdminService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType;
        AdminService adminService=null;
        Object object = request.getSession(false).getAttribute(USER);
        if (object.getClass().equals(User.class)){
            User user = (User) object;
            userType = user.getType();
            if (userType.equals(ADMIN) || userType.equals(MODER))
            {
                adminService = ServiceFactory.getInstance().getAdminService();
            }
            else {
                request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
        return adminService;
    }
}
