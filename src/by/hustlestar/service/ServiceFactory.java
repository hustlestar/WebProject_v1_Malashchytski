package by.hustlestar.service;

import by.hustlestar.service.iface.AdminService;
import by.hustlestar.service.iface.MovieService;
import by.hustlestar.service.iface.PoolService;
import by.hustlestar.service.iface.UserService;
import by.hustlestar.service.impl.*;

/**
 * Created by Hustler on 31.10.2016.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private MovieService movieService = new MovieServiceImpl();
    private AdminService adminService = new AdminServiceImpl();
    private UserService userService = new UserServiceImpl();
    private PoolService poolService = new PoolServiceImpl();

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
}
