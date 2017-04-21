package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.User;
import by.hustlestar.dao.iface.UserDAO;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.pool.ConnectionPoolSQLDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import by.hustlestar.dao.util.DAOHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * UserSQLDAO is an implementation of UserDAO for MySQL.
 */
public class UserSQLDAO implements UserDAO {
    private final static String LOG_IN_STATEMENT =
            "SELECT * FROM user WHERE u_nick=? and u_password=?";
    private final static String REGISTER_STATEMENT =
            "INSERT INTO user (`u_nick`, `u_mail`, `u_password`, `u_type`, `u_register`, `u_sex`) VALUES(?,?,?,'user',?,?)";
    private final static String VIEW_ALL_USERS =
            "SELECT * FROM user";
    private static final String VIEW_ALL_BANNED_USERS =
            "SELECT * FROM user WHERE u_type='banned'";
    private static final String UPDATE_IMAGE =
            "UPDATE user SET u_image= ? WHERE u_nick=?;";
    private static final String VIEW_BY_NICKNAME =
            "SELECT * FROM user WHERE u_nick=?";
    private static final String BAN_USER_BY_NICKNAME =
            "UPDATE `jackdb`.`user`\n" +
                    "SET\n" +
                    "`u_type` = 'banned'\n" +
                    "WHERE `u_nick` = ?;";
    private static final String UNBAN_USER_BY_NICKNAME =
            "UPDATE `jackdb`.`user`\n" +
                    "SET\n" +
                    "`u_type` = 'user'\n" +
                    "WHERE `u_nick` = ?;";
    private static final String VIEW_TOP_USERS =
            "SELECT user.u_nick, u_register, COUNT(review_score.review_u_nick) AS reputation FROM user\n" +
                    "LEFT JOIN review_score ON review_u_nick = u_nick\n" +
                    "WHERE review_score.value='1'\n" +
                    "GROUP BY review_u_nick\n" +
                    "HAVING COUNT(review_u_nick) > 0\n" +
                    "ORDER BY reputation DESC LIMIT 10;";
    private static final String DELETE_BY_NICKNAME =
            "DELETE FROM `jackdb`.`user` WHERE u_nick=?;";

    private static final String U_NICK = "u_nick";
    private static final String U_MAIL = "u_mail";
    private static final String U_TYPE = "u_type";
    private static final String U_SEX = "u_sex";
    private static final String U_IMAGE = "u_image";
    private static final String REPUTATION = "reputation";
    private static final String U_REGISTER = "u_register";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final UserDAO instance = new UserSQLDAO();

    private UserSQLDAO() {
    }

    public static UserDAO getInstance() {
        return instance;
    }

    /**
     * This method is used to authorize user in the system using data source.
     *
     * @param login    of user
     * @param password of user
     * @return filled User bean or null
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public User authorize(String login, String password) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(LOG_IN_STATEMENT);

            st.setString(1, login);
            st.setString(2, password);

            rs = st.executeQuery();

            if (!rs.next()) {
                return null;
            }

            User user = new User();
            user.setNickname(rs.getString(U_NICK));
            user.setEmail(rs.getString(U_MAIL));
            user.setType(rs.getString(U_TYPE));
            user.setSex(rs.getString(U_SEX));
            user.setRegistred(rs.getDate(U_REGISTER));
            return user;

        } catch (SQLException e) {
            throw new DAOException("Login sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Login pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st, rs);
        }
    }

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
    @Override
    public User register(String login, String email, String password, String sex) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(REGISTER_STATEMENT);
            st.setString(1, login);
            st.setString(2, email);
            st.setString(3, password);
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf =
                    new java.text.SimpleDateFormat(DATE_FORMAT);
            String currentTime = sdf.format(dt);
            st.setDate(4, Date.valueOf(currentTime));
            st.setString(5, sex);
            int i = st.executeUpdate();

            if (i > 0) {
                return authorize(login, password);
            }

        } catch (SQLException e) {
            throw new DAOException("Register sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Login pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
        return null;
    }

    /**
     * This method is used to get all existing users from data source.
     *
     * @return list of filled User beans
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public List<User> getAllUsers() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(VIEW_ALL_USERS);

            rs = st.executeQuery();

            List<User> users = new ArrayList<>();
            User user;
            while (rs.next()) {
                user = new User();
                user.setNickname(rs.getString(U_NICK));
                user.setEmail(rs.getString(U_MAIL));
                user.setType(rs.getString(U_TYPE));
                user.setSex(rs.getString(U_SEX));
                user.setRegistred(rs.getDate(U_REGISTER));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new DAOException("User sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to get all banned users from data source.
     *
     * @return list of filled User beans
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public List<User> getAllBannedUsers() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(VIEW_ALL_BANNED_USERS);

            rs = st.executeQuery();

            List<User> users = new ArrayList<>();
            User user;
            while (rs.next()) {
                user = new User();
                user.setNickname(rs.getString(U_NICK));
                user.setEmail(rs.getString(U_MAIL));
                user.setType(rs.getString(U_TYPE));
                user.setSex(rs.getString(U_SEX));
                user.setRegistred(rs.getDate(U_REGISTER));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new DAOException("User sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to get detailed information about some user from data source.
     *
     * @param nickname of user
     * @return filled User bean.
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public User getUserByNickname(String nickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(VIEW_BY_NICKNAME);
            st.setString(1, nickname);
            rs = st.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User();
                user.setNickname(rs.getString(U_NICK));
                user.setEmail(rs.getString(U_MAIL));
                user.setType(rs.getString(U_TYPE));
                user.setSex(rs.getString(U_SEX));
                user.setRegistred(rs.getDate(U_REGISTER));
                user.setImage(rs.getString(U_IMAGE));
            }
            return user;

        } catch (SQLException e) {
            throw new DAOException("User sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("User pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to block some user access to the system and mark him in data source.
     *
     * @param userNickname of user
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void banUser(String userNickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(BAN_USER_BY_NICKNAME);
            st.setString(1, userNickname);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("User v bane vse ok" + userNickname);
                return;
            }
            throw new DAOException("Wrong ban data");
        } catch (SQLException e) {
            throw new DAOException("User sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("User pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }

    /**
     * This method is used to return access to the system for some user.
     *
     * @param userNickname of user
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void unbanUser(String userNickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(UNBAN_USER_BY_NICKNAME);
            st.setString(1, userNickname);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("User razbanen vse ok " + userNickname);
                return;
            }
            throw new DAOException("Wrong unban data");
        } catch (SQLException e) {
            throw new DAOException("User sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("User pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }

    /**
     * This method is used to get users with the highest reputation from data source.
     *
     * @return list of filled User beans
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public List<User> getTopUsers() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();

            st = con.prepareStatement(VIEW_TOP_USERS);

            rs = st.executeQuery();

            List<User> users = new ArrayList<>();
            User user;
            while (rs.next()) {
                user = new User();
                user.setNickname(rs.getString(U_NICK));
                user.setRegistred(rs.getDate(U_REGISTER));
                user.setReputation(rs.getInt(REPUTATION));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new DAOException("User sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to delete some user from the system and used only for tests!!!
     *
     * @param userNickname of user
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void deleteUser(String userNickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_BY_NICKNAME);
            st.setString(1, userNickname);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("User udalen vse ok " + userNickname);
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }

    /**
     * This method is used to update image path for some user photo.
     *
     * @param nickname of user
     * @param path     to image
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void updateImage(String nickname, String path) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolSQLDAO.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_IMAGE);
            st.setString(1, path);
            st.setString(2, nickname);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("News image obnovlen vse ok " + path);
                return;
            }
            throw new DAOException("Wrong image data");
        } catch (SQLException e) {
            throw new DAOException("News sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error", e);
        } finally {
            DAOHelper.closeResource(con, st);
        }
    }
}