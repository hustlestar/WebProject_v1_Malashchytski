package by.hustlestar.service;

import by.hustlestar.bean.entity.User;
import by.hustlestar.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Hustler on 07.11.2016.
 */
public interface AdminService {
    void banUser(String userNickname) throws ServiceException;
    void unbanUser(String userNickname) throws ServiceException;

    List<User> showAllUsers() throws ServiceException;
}
