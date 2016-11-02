package by.hustlestar.controller;

import by.hustlestar.command.Command;

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

    private static final String COMMAND = "command";

    public Controller() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandName = request.getParameter(COMMAND);
        Command command  = CommandProvider.getInstance().getCommand(commandName);
        command.execute(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
