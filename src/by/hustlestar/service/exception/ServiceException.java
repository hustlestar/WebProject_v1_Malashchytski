package by.hustlestar.service.exception;

import by.hustlestar.dao.exception.DAOException;

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
}
