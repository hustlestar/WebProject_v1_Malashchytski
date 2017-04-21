package by.hustlestar.command;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interface Command is implemented by the classes, which
 * encapsulate algorithm of handling HTTP request.
 * Command layer situated between Controller(gets client request)
 * and Service(logic of application).
 * Command is used for handling request parameters and passing
 * them to Service in correct format.
 * As the result of completing command should set attributes
 * to request or session scope, which will inform client about
 * the result of request handling (did request successfully complete
 * or error emerged). Also command dispatches or redirects requests
 * on different pages depending on the result of Service methods
 * calls.
 */
public interface Command {
    /**
     * Method should be overridden to implement particular Command logic.
     * Mainly method works with HTTP-request to get parameters, session, cookies
     * and etc.
     * @param request http-request from client
     * @param response http-response from server
     * @throws IOException
     * @throws ServletException
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

}
