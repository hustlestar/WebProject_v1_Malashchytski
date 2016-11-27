package by.hustlestar.dao.iface;

import by.hustlestar.dao.impl.pool.ConnectionPoolException;

/**
 * Created by dell on 27.11.2016.
 */
public interface ConnectionPoolDAO {

        void init() throws ConnectionPoolException;

        void destroy() throws ConnectionPoolException;

}
