package by.hustlestar.service;

import by.hustlestar.bean.entity.User;
import by.hustlestar.service.exception.ServiceException;

/**
 * Created by Hustler on 09.11.2016.
 */
public interface UserService {
    User showUserByNickname(String nickname) throws ServiceException;
}
