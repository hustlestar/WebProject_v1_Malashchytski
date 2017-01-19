package by.hustlestar.dao.iface;

import by.hustlestar.dao.pool.ConnectionPoolException;

/**
 * ConnectionPoolDAO interface represents main methods for working with ConnectionPool.
 */
public interface ConnectionPoolDAO {
    /**
     * This method is used to initialize pool of connections with data source.
     *
     * @throws ConnectionPoolException if some error occurred while initializing ConnectionPool.
     */
    void init() throws ConnectionPoolException;

    /**
     * This method is used to destroy the pool of connections with data source.
     *
     * @throws ConnectionPoolException if some error occurred while initializing ConnectionPool.
     */
    void destroy() throws ConnectionPoolException;

}
