package by.hustlestar.controller.listener;
/**
 * Created by dell on 27.11.2016.
 */

import by.hustlestar.service.ServiceFactory;
import by.hustlestar.service.exception.ServiceException;
import by.hustlestar.service.iface.PoolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConnectionPoolListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger();

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
            LOGGER.debug("Pool successfully initialized");
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
            LOGGER.debug("Pool successfully destroyed");
        } catch (ServiceException e) {
            throw new ConnectionPoolListenerException("Cannot destroy the pool", e);
        }
    }
}
