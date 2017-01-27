package by.hustlestar.dao.pool;

import by.hustlestar.dao.iface.ConnectionPoolDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ConnectionPoolSQLDAO is an implementation of ConnectionPoolDAO for MySQL.
 */
public class ConnectionPoolSQLDAO implements ConnectionPoolDAO {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3309/jackdb?useEncoding=true&amp;characterEncoding=UTF-8";
    //private static final String URL = "jdbc:mysql://mysql42382-env-0609561.mycloud.by/jackdb?useEncoding=true&amp;characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "pass";
    //private static final String PASSWORD = "TOFgle57892";

    private static final int MINIMAL_CONNECTION_COUNT = 5;

    private static BlockingQueue<Connection> freeConnections;
    private static BlockingQueue<Connection> usedConnections;

    private final static ConnectionPoolSQLDAO instance = new ConnectionPoolSQLDAO();

    private volatile boolean isInit = false;


    public ConnectionPoolSQLDAO() {
    }

    /**
     * This method is used to initialize pool of connections with data source.
     *
     * @throws ConnectionPoolException if some error occurred while initializing ConnectionPool.
     */
    @Override
    public void init() throws ConnectionPoolException {
        if (!isInit) {
            try {
                freeConnections = new ArrayBlockingQueue<>(MINIMAL_CONNECTION_COUNT);
                usedConnections = new ArrayBlockingQueue<>(MINIMAL_CONNECTION_COUNT);
                Class.forName(DRIVER);
                Connection connection;
                for (int i = 0; i < MINIMAL_CONNECTION_COUNT; i++) {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    freeConnections.add(connection);
                }
                isInit = true;
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new ConnectionPoolException("Init pool only once");
        }
    }

    /**
     * This method is used to destroy the pool of connections with data source.
     *
     * @throws ConnectionPoolException if some error occurred while initializing ConnectionPool.
     */
    @Override
    public void destroy() throws ConnectionPoolException {
        if (isInit) {
            try {
                for (Connection connection : freeConnections) {
                    connection.close();
                }
                freeConnections.clear();
                for (Connection connection : usedConnections) {
                    connection.close();
                }
                usedConnections.clear();
                isInit = false;
            } catch (SQLException e) {
                throw new ConnectionPoolException("Cannot destroy a pool", e);
            }
        } else {
            throw new ConnectionPoolException("Try to destroy not init pool");
        }
    }

    public static ConnectionPoolSQLDAO getInstance() {
        return instance;
    }

    /**
     * This method is used to get Connection from the free connections queue
     *
     * @return Connection object
     * @throws ConnectionPoolException if some error occurred while proceeding.
     */
    public Connection takeConnection() throws ConnectionPoolException {

        Connection connection;
        try {
            if (freeConnections == null) {
                throw new ConnectionPoolException("Pool doesn't exist");
            }
            connection = freeConnections.take();
            usedConnections.put(connection);
            return connection;
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Couldn't take connection from pull", e);
        }

    }

    /**
     * This method is used to return Connection to the free connections queue.
     *
     * @throws ConnectionPoolException if some error occurred while proceeding.
     */
    public void returnConnection(Connection connection) throws ConnectionPoolException {
        try {
            if (connection == null || connection.isClosed()) {
                throw new ConnectionPoolException("Can't return closed connection");
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("SQL exception in returnConnection", e);
        }

        try {
            usedConnections.remove(connection);
            freeConnections.put(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Exception while returning connections to queues", e);
        }
    }

}
