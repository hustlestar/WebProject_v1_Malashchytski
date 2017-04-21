package by.hustlestar.service.iface;

import by.hustlestar.bean.entity.User;
import by.hustlestar.service.exception.ServiceException;

import java.util.List;

/**
 * UserService is an interface mainly used to interact with user entity.
 */
public interface UserService {
    /**
     * This method is used to show any user by the nickname.
     *
     * @param nickname of user.
     * @return User bean with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    User getUserByNickname(String nickname) throws ServiceException;

    /**
     * This method is used to register and give access to the system for
     * some new visitor.
     *
     * @param login of user
     * @param email of user
     * @param password of user
     * @param passwordrep of user
     * @param sex of user
     * @return User bean with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    User register(String login, String email, byte[] password, byte[] passwordrep, String sex) throws ServiceException;

    /**
     * This method is used to let user enter his account in the system.
     *
     * @param login of user
     * @param password of user
     * @return User bean with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    User authorize(String login, byte[] password) throws ServiceException;

    /**
     * This method is used to show the best users in the system, based on their reputation.
     *
     * @return List of User bean with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    List<User> getTopUsers() throws ServiceException;
}
