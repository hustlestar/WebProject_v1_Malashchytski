package by.hustlestar.command;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hustler on 27.10.2016.
 */
public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

}
