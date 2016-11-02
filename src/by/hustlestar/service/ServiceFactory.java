package by.hustlestar.service;

import by.hustlestar.service.impl.LoginServiceImpl;
import by.hustlestar.service.impl.MovieServiceImpl;
import by.hustlestar.service.impl.RegisterServiceImpl;

/**
 * Created by Hustler on 31.10.2016.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();


    private LoginService loginService = new LoginServiceImpl();
    private RegisterService registerService = new RegisterServiceImpl();
    private MovieService movieService = new MovieServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public RegisterService getRegisterService() {
        return registerService;
    }

    public MovieService getMovieService() {
        return movieService;
    }
}
