package by.hustlestar.service;

import by.hustlestar.bean.entity.User;
import by.hustlestar.service.exception.ServiceException;

/**
 * Created by Hustler on 31.10.2016.
 */
public interface LoginService {
    User authorize(String login, String password) throws ServiceException;
}
