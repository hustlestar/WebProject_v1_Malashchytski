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
import java.util.List;

/**
 * Created by Hustler on 07.11.2016.
 */
public class ShowMoviesByCountry implements Command {
    private static final String COUNTRY = "country";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String country = request.getParameter(COUNTRY);
        System.out.println(country);
        List<Movie> movies;
        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        try {
            movies = movieService.showMoviesByCountry(country);
            /*for (Movie movie : movies) {
                System.out.println(movie.getTitle() + " - " + movie.getYear());
            }*/
            request.setAttribute("all_movies", movies);
            request.getRequestDispatcher("WEB-INF/jsp/moviesPage.jsp").include(request, response);
        } catch (ServiceException e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
