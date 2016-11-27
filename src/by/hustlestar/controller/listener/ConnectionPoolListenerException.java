package by.hustlestar.controller.listener;

import by.hustlestar.service.exception.ServiceException;

/**
 * Created by dell on 27.11.2016.
 */
public class ConnectionPoolListenerException extends RuntimeException {
    public ConnectionPoolListenerException(String s, ServiceException e) {
        super(s, e);
    }
}
