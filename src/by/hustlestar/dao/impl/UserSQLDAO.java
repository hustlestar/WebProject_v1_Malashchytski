package by.hustlestar.dao.impl;

import by.hustlestar.bean.entity.User;
import by.hustlestar.dao.UserDAO;
import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.impl.pool.ConnectionPool;
import by.hustlestar.dao.impl.pool.ConnectionPoolException;
import java.sql.*;


/**
 * Created by Hustler on 31.10.2016.
 */
public class UserSQLDAO implements UserDAO {
    private final static String LOG_IN_STATEMENT = "SELECT * FROM user WHERE u_nick=? and u_password=?";
    private final static String REGISTER_STATEMENT = "INSERT INTO user VALUES(?,?,?,'user',?,?)";

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
            user.setNickname(rs.getString("u_nick"));
            user.setEmail(rs.getString("u_mail"));
            user.setType(rs.getString("u_type"));
            user.setSex(rs.getString("u_sex"));
            user.setRegistred(rs.getDate("u_register"));
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
                    new java.text.SimpleDateFormat("yyyy-MM-dd");
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
}