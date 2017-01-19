package by.hustlestar.service.exception;

/**
 * ServiceAuthException is thrown when user authorization data is incorrect.
 */
public class ServiceAuthException extends ServiceException {
    public ServiceAuthException(String s) {
        super(s);
    }
}
