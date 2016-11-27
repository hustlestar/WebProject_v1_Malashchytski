package by.hustlestar.service.iface;

import by.hustlestar.service.exception.ServiceException;

/**
 * Created by dell on 27.11.2016.
 */
public interface PoolService {

    void init() throws ServiceException;

    void destroy() throws ServiceException;

}
