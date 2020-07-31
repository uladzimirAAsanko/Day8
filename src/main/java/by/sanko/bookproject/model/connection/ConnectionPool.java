package by.sanko.bookproject.model.connection;


import by.sanko.bookproject.exception.ConnectionDatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    static final String USER = "root";
    static final String PASSWORD = "rootPASSWORD";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/books";
    private static final int POOL_SIZE = 6;
    private static ConnectionPool instance;
    private static volatile boolean instanceIsCreated;

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> surrenderedConnections;

    public static ConnectionPool getInstance() throws ConnectionDatabaseException {
        if (!instanceIsCreated) {
            synchronized (ConnectionPool.class) {
                if (!instanceIsCreated) {
                    instance = new ConnectionPool();
                    instanceIsCreated = true;
                }
            }
        }

        return instance;
    }

    private ConnectionPool() throws ConnectionDatabaseException {
        try {
            Class.forName(DRIVER_NAME);
            freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
            surrenderedConnections = new ArrayDeque<>(POOL_SIZE);

            for (int i = 0; i < POOL_SIZE; i++) {
                freeConnections.offer(new ProxyConnection(DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionDatabaseException("Error while connection pool creating", e);
        }
    }

    public Connection getConnection() throws ConnectionDatabaseException {
        ProxyConnection connection;

        try {
            connection = freeConnections.take();
            surrenderedConnections.offer(connection);
        } catch (InterruptedException e) {
            throw new ConnectionDatabaseException("Error while getting connection", e);
        }

        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionDatabaseException {
        if (connection instanceof ProxyConnection) {
            surrenderedConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        } else {
            throw new ConnectionDatabaseException("Invalid connection to close");
        }
    }

    public void destroyPool() throws ConnectionDatabaseException {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                freeConnections.take().reallyClose();
            }
            deregisterDrivers();
        } catch (SQLException | InterruptedException e) {
            throw new ConnectionDatabaseException("Error while close connection pool", e);
        }
    }

    private void deregisterDrivers() throws SQLException {
        while (DriverManager.getDrivers().hasMoreElements()) {
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        }
    }
}