package by.hustlestar.service.impl;

import by.hustlestar.dao.DAOFactory;
import by.hustlestar.dao.iface.ConnectionPoolDAO;
import by.hustlestar.dao.impl.pool.ConnectionPoolException;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.PoolService;

/**
 * Created by dell on 27.11.2016.
 */
public class PoolServiceImpl implements PoolService {
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
