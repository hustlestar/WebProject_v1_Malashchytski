package by.hustlestar.controller.listener;

import by.hustlestar.service.exception.ServiceException;

/**
 * ConnectionPoolListenerException is thrown when some error happens
 * during processing of the ConnectionPoolListener methods.
 */
public class ConnectionPoolListenerException extends RuntimeException {
    public ConnectionPoolListenerException(String s, ServiceException e) {
        super(s, e);
    }
}
