package by.hustlestar.dao.pool;


/**
 * Created by Hustler on 31.10.2016.
 */
public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(String s) {
        super(s);
    }

    public ConnectionPoolException(String s, Exception e) {
        super(s, e);
    }
}
