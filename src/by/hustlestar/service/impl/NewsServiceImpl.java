package by.hustlestar.service.impl;

import by.hustlestar.bean.entity.Actor;
import by.hustlestar.bean.entity.Movie;
import by.hustlestar.bean.entity.News;
import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.iface.ActorDAO;
import by.hustlestar.dao.iface.MovieDAO;
import by.hustlestar.dao.iface.NewsDAO;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.NewsService;

import java.util.List;

/**
 * Created by dell on 06.12.2016.
 */
public class NewsServiceImpl implements NewsService {
    @Override
    public News viewNews(String id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoFactory.getNewsDAO();
        ActorDAO actorDAO = daoFactory.getActorDAO();
        MovieDAO movieDAO = daoFactory.getMovieDAO();
        News news;
        List<Movie> movieList;
        List<Actor> actorList;
        int intId;
        try {
            intId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ServiceException("No person with such ID");
        }
        try {
            news = newsDAO.getNews(intId);
            if (news != null) {
                actorList = actorDAO.getActorsForNews(intId);
                movieList = movieDAO.getMoviesForNews(intId);

                news.setNewsActors(actorList);
                news.setNewsMovies(movieList);
            } else {
                throw new ServiceException("No persons matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return news;
    }
}
