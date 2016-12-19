package by.hustlestar.service.iface;

import by.hustlestar.bean.entity.User;
import by.hustlestar.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Hustler on 09.11.2016.
 */
public interface UserService {

    User showUserByNickname(String nickname) throws ServiceException;

    User register(String login, String email, String password, String passwordrep, String sex) throws ServiceException;

    User authorize(String login, String password) throws ServiceException;

    List<User> showTopUsers() throws ServiceException;
}
