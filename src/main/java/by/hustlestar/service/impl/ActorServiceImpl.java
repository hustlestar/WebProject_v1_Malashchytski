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
 * ActorServiceImpl is an implementation class of ActorService
 */
public class ActorServiceImpl implements ActorService {
    /**
     * This method shows any actor by its id.
     *
     * @param id id of actor
     * @return Actor bean object with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public Actor getActor(String id) throws ServiceException {
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
