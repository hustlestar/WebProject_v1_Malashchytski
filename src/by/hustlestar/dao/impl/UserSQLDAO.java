package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.User;
import by.hustlestar.dao.iface.UserDAO;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.impl.pool.ConnectionPool;
import by.hustlestar.dao.impl.pool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hustler on 31.10.2016.
 */
public class UserSQLDAO implements UserDAO {
    private final static String LOG_IN_STATEMENT =
            "SELECT * FROM user WHERE u_nick=? and u_password=?";
    private final static String REGISTER_STATEMENT =
            "INSERT INTO user VALUES(?,?,?,'user',?,?)";
    private final static String VIEW_ALL_USERS =
            "SELECT * FROM user";
    private static final String VIEW_ALL_BANNED_USERS =
            "SELECT * FROM user WHERE u_type='banned'";

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

    private static final String U_NICK = "u_nick";
    private static final String U_MAIL = "u_mail";
    private static final String U_TYPE = "u_type";
    private static final String U_SEX = "u_sex";
    private static final String U_REGISTER = "u_register";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public User authorize(String login, String password) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

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
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPool.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }

        }
    }

    @Override
    public User register(String login, String email, String password, String sex) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(REGISTER_STATEMENT);
            st.setString(1, login);
            st.setString(2, email);
            st.setString(3, password);
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf =
                    new java.text.SimpleDateFormat(DATE_FORMAT);
            String currentTime = sdf.format(dt);
            System.out.println(currentTime);
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
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            try {
                ConnectionPool.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
        return null;
    }

    @Override
    public List<User> viewAllUsers() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

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
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPool.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public List<User> viewAllBannedUsers() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

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
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPool.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public User viewUserByNickname(String nickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

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
            }
            return user;

        } catch (SQLException e) {
            throw new DAOException("User sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("User pool connection error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing result set", e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPool.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void banUser(String userNickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(BAN_USER_BY_NICKNAME);
            st.setString(1, userNickname);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("User v bane vse ok" + userNickname);
                return;
            }
            throw new DAOException("Wrong ban data");
        } catch (SQLException e) {
            throw new DAOException("User sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("User pool connection error", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPool.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }

    @Override
    public void unbanUser(String userNickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(UNBAN_USER_BY_NICKNAME);
            st.setString(1, userNickname);
            int update = st.executeUpdate();
            if (update > 0) {
                System.out.println("User razbanen vse ok " + userNickname);
                return;
            }
            throw new DAOException("Wrong unban data");
        } catch (SQLException e) {
            throw new DAOException("User sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("User pool connection error", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new DAOException("Exception while closing statement", e);
                }
            }
            try {
                ConnectionPool.getInstance().returnConnection(con);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception while returning connection", e);
            }
        }
    }
}