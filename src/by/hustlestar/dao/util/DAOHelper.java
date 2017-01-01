package by.hustlestar.dao.util;

import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.pool.ConnectionPoolException;
import by.hustlestar.dao.pool.ConnectionPoolSQLDAO;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dell on 01.01.2017.
 */
public class DAOHelper {
    private static final Logger logger = LogManager.getLogger(DAOHelper.class);
    private static final ConnectionPoolSQLDAO CONNECTION_POOL = ConnectionPoolSQLDAO.getInstance();

    public static void closeResource(Connection con, PreparedStatement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e + "Exception while closing result set");
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e + "Exception while closing statement");
            }
        }
        try {
            CONNECTION_POOL.returnConnection(con);
        } catch (ConnectionPoolException e) {
            logger.log(Level.ERROR, e + "Exception while returning connection");
        }
    }

    public static void closeResource(Connection con, PreparedStatement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e + "Exception while closing statement");
            }
        }
        try {
            CONNECTION_POOL.returnConnection(con);
        } catch (ConnectionPoolException e) {
            logger.log(Level.ERROR, e + "Exception while returning connection");
        }
    }
}
