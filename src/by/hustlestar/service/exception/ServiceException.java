package by.hustlestar.service.exception;

import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.pool.ConnectionPoolException;

/**
 * Created by Hustler on 28.10.2016.
 */
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String s, DAOException e) {
        super(s, e);
    }

    public ServiceException(String s, ConnectionPoolException e) {
        super(s, e);
    }
}
