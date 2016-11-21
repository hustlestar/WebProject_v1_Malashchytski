package by.hustlestar.service.iface;

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

    List<User> showAllBannedUsers() throws ServiceException;

    void addMovie(String title, String year, String budget, String gross) throws ServiceException;

    void updateMovie(String id, String title, String year, String budget, String gross) throws ServiceException;

    void deleteReview(String movieID, String userNickname) throws ServiceException;
}
