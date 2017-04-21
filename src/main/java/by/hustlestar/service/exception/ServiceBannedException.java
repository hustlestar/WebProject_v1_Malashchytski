package by.hustlestar.service.exception;

/**
 * ServiceBannedException is thrown when banned user tries to
 * enter the system.
 */
public class ServiceBannedException extends ServiceException {
    public ServiceBannedException(String s) {
        super(s);
    }
}
