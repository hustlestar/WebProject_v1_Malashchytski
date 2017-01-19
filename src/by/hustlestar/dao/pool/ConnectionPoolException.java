package by.hustlestar.dao.pool;


/**
 * ConnectionPoolException is thrown when error with ConnectionPool occurred.
 */
public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(String s) {
        super(s);
    }

    public ConnectionPoolException(String s, Exception e) {
        super(s, e);
    }
}
