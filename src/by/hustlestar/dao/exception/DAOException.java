package by.hustlestar.dao.exception;

import by.hustlestar.dao.pool.ConnectionPoolException;

import java.sql.SQLException;

/**
 * DAOException is thrown when error occurred while working with the data source.
 */
public class DAOException extends Exception {

    public DAOException(String s, SQLException e) {
        super(s, e);
    }

    public DAOException(String s, ConnectionPoolException e) {
        super(s, e);
    }

    public DAOException(String s) {
        super(s);
    }
}
