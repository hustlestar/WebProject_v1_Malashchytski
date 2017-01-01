package by.hustlestar.controller;

import by.hustlestar.bean.entity.User;
import by.hustlestar.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hustler on 27.10.2016.
 */
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getRootLogger();

    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final String COMMAND = "command";
    private static final String USER = "user";
    private static final String GUEST = "guest";
    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot update movie";

    public Controller() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*String commandName = request.getParameter(COMMAND);
        Command command  = CommandProvider.getInstance().getCommand(commandName);
        command.execute(request, response);*/
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String commandName = request.getParameter(COMMAND);
        Command command  = CommandProvider.getInstance().getCommand(commandName);
        command.execute(request, response);*/
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandString = request.getParameter(COMMAND);
        logger.info("Controller processRequest() - commandName = {}", commandString);

        if (commandString != null && !commandString.isEmpty()) {
            try {
                User user = (User) request.getSession(false).getAttribute(USER);
                String type;
                if (user != null) {
                    type = user.getType();
                } else{
                    type = GUEST;
                }
                Command command = CommandProvider.getInstance().getCommandForUser(type, commandString);
                if (command==null){
                    logger.error("Access without permission from client");
                    request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                    response.sendRedirect(ERROR_PAGE);
                } else {
                    command.execute(request, response);
                }
            } catch (IllegalArgumentException ex) {
                logger.error("404 error, client requests a nonexistent command", ex);
                response.sendError(404);
            }
        } else {
            response.sendRedirect(ERROR_PAGE);
        }
    }
}
