package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.User;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * UserDAO interface represents methods for interacting with User beans mainly.
 */
public interface UserDAO {
    /**
     * This method is used to authorize user in the system using data source.
     *
     * @param login    of user
     * @param password of user
     * @return filled User bean or null
     * @throws DAOException if some error occurred while processing data.
     */
    User authorize(String login, String password) throws DAOException;

    /**
     * This method is used to add new user to the system and data source.
     *
     * @param login    of user
     * @param email    of user
     * @param password of user
     * @param sex      of user
     * @return filled User bean or null
     * @throws DAOException if some error occurred while processing data.
     */
    User register(String login, String email, String password, String sex) throws DAOException;

    /**
     * This method is used to get all existing users from data source.
     *
     * @return list of filled User beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<User> getAllUsers() throws DAOException;

    /**
     * This method is used to get all banned users from data source.
     *
     * @return list of filled User beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<User> getAllBannedUsers() throws DAOException;

    /**
     * This method is used to get detailed information about some user from data source.
     *
     * @param nickname of user
     * @return filled User bean.
     * @throws DAOException if some error occurred while processing data.
     */
    User getUserByNickname(String nickname) throws DAOException;

    /**
     * This method is used to block some user access to the system and mark him in data source.
     *
     * @param userNickname of user
     * @throws DAOException if some error occurred while processing data.
     */
    void banUser(String userNickname) throws DAOException;

    /**
     * This method is used to return access to the system for some user.
     *
     * @param userNickname of user
     * @throws DAOException if some error occurred while processing data.
     */
    void unbanUser(String userNickname) throws DAOException;

    /**
     * This method is used to get users with the highest reputation from data source.
     *
     * @return list of filled User beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<User> getTopUsers() throws DAOException;

    /**
     * This method is used to delete some user from the system and used only for tests!!!
     *
     * @param userNickname of user
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteUser(String userNickname) throws DAOException;

    /**
     * This method is used to update image path for some user photo.
     *
     * @param nickname of user
     * @param path     to image
     * @throws DAOException if some error occurred while processing data.
     */
    void updateImage(String filename, String s) throws DAOException;
}
