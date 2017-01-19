package by.hustlestar.service;

import by.hustlestar.service.iface.*;
import by.hustlestar.service.impl.*;

/**
 * ServiceFactory represents the factory for obtaining Service objects.
 */
public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    private MovieService movieService = new MovieServiceImpl();
    private AdminService adminService = new AdminServiceImpl();
    private UserService userService = new UserServiceImpl();
    private PoolService poolService = new PoolServiceImpl();
    private ActorService actorService = new ActorServiceImpl();
    private NewsService newsService = new NewsServiceImpl();


    public MovieService getMovieService() {
        return movieService;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public UserService getUserService() {
        return userService;
    }

    public PoolService getPoolService() {
        return poolService;
    }

    public ActorService getActorService() {
        return actorService;
    }

    public NewsService getNewsService() {
        return newsService;
    }
}
