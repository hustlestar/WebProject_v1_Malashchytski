package by.hustlestar.service;

import by.hustlestar.bean.entity.User;
import by.hustlestar.service.exception.ServiceException;

/**
 * Created by Hustler on 31.10.2016.
 */
public interface RegisterService {
    User register(String login, String email, String password, String sex) throws ServiceException;
}
