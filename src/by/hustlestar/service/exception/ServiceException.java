package by.hustlestar.service.exception;

import by.hustlestar.dao.exception.DAOException;
import by.hustlestar.dao.pool.ConnectionPoolException;

/**
 * ServiceException is thrown when some error occurred while
 * proceeding some Service.
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
