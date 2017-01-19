package by.hustlestar.service.impl;

import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.iface.ConnectionPoolDAO;
import by.hustlestar.dao.pool.ConnectionPoolException;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.PoolService;

/**
 * This class is an implementation of PoolService interface.
 */
public class PoolServiceImpl implements PoolService {
    /**
     * This method is used to initialize connection pool.
     *
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public void init() throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ConnectionPoolDAO poolDAO = daoFactory.getConnectionPoolDAO();
            poolDAO.init();
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Cannot init the pool", e);
        }
    }

    /**
     * This method is used to destroy connection pool.
     *
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public void destroy() throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ConnectionPoolDAO poolDAO = daoFactory.getConnectionPoolDAO();
            poolDAO.destroy();
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Cannot destroy the pool", e);
        }
    }
}
