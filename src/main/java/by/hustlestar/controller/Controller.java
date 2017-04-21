package by.hustlestar.controller;

import by.hustlestar.bean.entity.User;
import by.hustlestar.command.Command;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * FrontController of web-application, which receives HTTP GET and POST requests
 * from client, processes this request. Then dispatching it to particular
 * Command for further handling.
 */
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(Controller.class);

    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final String COMMAND = "command";
    private static final String USER = "user";
    private static final String GUEST = "guest";
    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "You don't have permission to do that.";
    private static final String MESSAGE_OF_ERROR_2 = "Ooops something went wrong";

    public Controller() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandString;
        if (ServletFileUpload.isMultipartContent(request)) {
            commandString = String.valueOf(CommandName.UPLOAD_PHOTO);
        } else {
            commandString = request.getParameter(COMMAND);
        }
        logger.log(Level.INFO, "Controller processRequest() - commandName = {}", commandString);
        if (commandString != null && !commandString.isEmpty()) {
            try {
                User user = (User) request.getSession(false).getAttribute(USER);
                String type;
                if (user != null) {
                    type = user.getType();
                } else {
                    type = GUEST;
                }
                Command command = CommandProvider.getInstance().getCommandForUser(type, commandString);
                if (command == null) {
                    logger.log(Level.ERROR, "Access without permission from client");
                    request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                    request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
                } else {
                    command.execute(request, response);
                }
            } catch (IllegalArgumentException ex) {
                logger.log(Level.ERROR, "404 error, client requests a nonexistent command", ex);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            }
        } else {
            logger.log(Level.ERROR, "No such command");
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
