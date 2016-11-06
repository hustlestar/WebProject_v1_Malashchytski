package by.hustlestar.command.impl;

import by.hustlestar.bean.entity.Movie;
import by.hustlestar.command.Command;
import by.hustlestar.service.MovieService;
import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hustler on 06.11.2016.
 */
public class ShowMovieByID implements Command {
    private static final String ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter(ID);
        Movie movie;
        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        try {
            movie = movieService.showMovieByID(id);
            /*for (Movie movie : movies) {
                System.out.println(movie.getTitle() + " - " + movie.getYear());
            }*/
            request.setAttribute("movie", movie);
            request.getRequestDispatcher("WEB-INF/jsp/moviePage.jsp").include(request, response);
        } catch (ServiceException e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
