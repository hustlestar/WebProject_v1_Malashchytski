package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.Actor;
import by.hustlestar.bean.entity.Movie;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ActorDAO;
import by.hustlestar.dao.iface.MovieDAO;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.ActorService;
import by.hustlestar.service.validation.Validator;

import java.util.List;

/**
 * Created by dell on 05.12.2016.
 */
public class ActorServiceImpl implements ActorService {

    @Override
    public Actor viewActor(String id) throws ServiceException {
        if (!Validator.validateNumber(id)) {
            throw new ServiceException("Illegal data input");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO movieDAO = daoFactory.getMovieDAO();
        ActorDAO actorDAO = daoFactory.getActorDAO();
        Actor actor;
        List<Movie> movieList;
        int normId;
        try {
            normId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ServiceException("No person with such ID");
        }
        try {
            actor = actorDAO.getActor(normId);
            if (actor != null) {
                movieList = movieDAO.getMoviesForActor(normId);

                actor.setMovies(movieList);

            } else {
                throw new ServiceException("No persons matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return actor;
    }
}
