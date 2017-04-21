package by.hustlestar.dao.util;

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
 * DAOHelper is a helper class of DAO layer.
 */
public class DAOHelper {
    private static final Logger logger = LogManager.getLogger(DAOHelper.class);
    private static final ConnectionPoolSQLDAO CONNECTION_POOL = ConnectionPoolSQLDAO.getInstance();

    /**
     * This method is used to close opened resources such as PreparedStatement, ResultSet and
     * return Connection.
     * @param con Connection
     * @param st PreparedStatement
     * @param rs ResultSet
     */
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

    /**
     * This method is used to close opened resource PreparedStatement and
     * return Connection.
     * @param con Connection
     * @param st PreparedStatement
     */
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
