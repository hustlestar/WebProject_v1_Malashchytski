package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.User;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Hustler on 31.10.2016.
 */
public interface UserDAO {

    User authorize(String login, String password) throws DAOException;

    User register(String login, String email, String password, String sex) throws DAOException;

    List<User> getAllUsers() throws DAOException;

    List<User> getAllBannedUsers() throws DAOException;

    User getUserByNickname(String nickname) throws DAOException;

    void banUser(String userNickname) throws DAOException;

    void unbanUser(String userNickname) throws DAOException;

}
