package by.hustlestar.dao.exception;

import by.hustlestar.dao.pool.ConnectionPoolException;

import java.sql.SQLException;

/**
 * Created by Hustler on 31.10.2016.
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
