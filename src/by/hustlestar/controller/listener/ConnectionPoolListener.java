package by.hustlestar.controller.listener;

import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.PoolService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ConnectionPoolListener is listener for ServletContext initialization
 * and destroying. And used to initialize and destroy DB connection pool.
 */
public class ConnectionPoolListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ConnectionPoolListener.class);

    // Public constructor is required by servlet spec
    public ConnectionPoolListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------

    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PoolService poolService = serviceFactory.getPoolService();
            poolService.init();
            logger.log(Level.INFO, "Pool successfully initialized");

        } catch (ServiceException e) {
            throw new ConnectionPoolListenerException("Cannot init the pool", e);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PoolService poolService = serviceFactory.getPoolService();
            poolService.destroy();
            logger.log(Level.INFO, "Pool successfully destroyed");
        } catch (ServiceException e) {
            throw new ConnectionPoolListenerException("Cannot destroy the pool", e);
        }
    }
}
