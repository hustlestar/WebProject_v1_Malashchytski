package by.hustlestar.service.iface;

import by.hustlestar.service.exception.ServiceException;

/**
 * PoolService is and interface used to initialize and destroy connection poll
 * with database or some other data source.
 */
public interface PoolService {

    /**
     * This method is used to initialize connection pool.
     *
     * @throws ServiceException if any error occurred while processing method.
     */
    void init() throws ServiceException;

    /**
     * This method is used to destroy connection pool.
     *
     * @throws ServiceException if any error occurred while processing method.
     */
    void destroy() throws ServiceException;

}
