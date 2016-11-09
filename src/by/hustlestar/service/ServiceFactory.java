package by.hustlestar.service;

import by.hustlestar.service.impl.*;

/**
 * Created by Hustler on 31.10.2016.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private LoginService loginService = new LoginServiceImpl();
    private RegisterService registerService = new RegisterServiceImpl();
    private MovieService movieService = new MovieServiceImpl();
    private AdminService adminService = new AdminServiceImpl();
    private UserService userService = new UserServiceImpl();


    public LoginService getLoginService() {
        return loginService;
    }

    public RegisterService getRegisterService() {
        return registerService;
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public UserService getUserService() {
        return userService;
    }
}
