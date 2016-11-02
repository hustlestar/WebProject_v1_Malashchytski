package by.hustlestar.dao.impl.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Hustler on 31.10.2016.
 */
public class ConnectionPool {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1/jackdb";
    private static final String USER = "root";
    private static final String PASSWORD = "pass";

    private static final int MINIMAL_CONNECTION_COUNT = 5;

    private static BlockingQueue<Connection> freeConnections;
    private static BlockingQueue<Connection> usedConnections;

    private final static ConnectionPool instance = new ConnectionPool();

    private ConnectionPool() {
    }

    static {
        try {
            freeConnections = new ArrayBlockingQueue<>(MINIMAL_CONNECTION_COUNT);
            usedConnections = new ArrayBlockingQueue<>(MINIMAL_CONNECTION_COUNT);
            Class.forName(DRIVER);
            Connection connection;
            for (int i = 0; i < MINIMAL_CONNECTION_COUNT; i++) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                freeConnections.add(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            if (freeConnections == null) {
                System.out.println("bedaaa");
                throw new ConnectionPoolException("Pool doesn't exist");
            }
            connection = freeConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Couldn't take connection from pull", e);
        }
        return connection;
    }

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
