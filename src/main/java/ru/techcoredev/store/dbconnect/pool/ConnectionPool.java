package ru.techcoredev.store.dbconnect.pool;

import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.dbconnect.DAOinterfeices.DBConfigurator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    public static final String FILE_PATH_PROPERTIES = "application.properties";
    private BlockingQueue<Connection> connectionQueue;
    private final Vector<Connection> usedConnections = new Vector<>();
    private final Properties connectionProperties = DBConfigurator.getProperties(FILE_PATH_PROPERTIES);
    private static ConnectionPool instance;

    private ConnectionPool() {
    }

    public void initPool() {
        int poolSize = Integer.parseInt(this.connectionProperties.getProperty("db.poolSize"));
        this.connectionQueue = new LinkedBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Connection connection = this.getConnection();
            if (!this.connectionQueue.offer(connection)) {
                throw new IllegalArgumentException("connection can`t be offered ");
            }

        }
    }

    public static ConnectionPool getInstance() {
        ConnectionPool pool = instance;
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                instance = pool = new ConnectionPool();
                pool.initPool();
            }
        }
        return pool;
    }

    public synchronized Connection retrieve() {
        Connection newConnection;
        if (this.connectionQueue.size() == 0) {
            newConnection = this.getConnection();
        } else {
            newConnection = this.connectionQueue.poll();
        }
        this.usedConnections.addElement(newConnection);
        return newConnection;
    }

    public synchronized void putBack(Connection connection) {
        if (connection != null) {
            if (this.usedConnections.removeElement(connection)) {
                if (!this.connectionQueue.offer(connection)) {
                    throw new IllegalArgumentException("connection can`t be offered ");
                }
            } else {
                throw new NullPointerException("Connection not in the used connection");
            }
        }
    }

    public int getCountConnections() {
        return this.connectionQueue.size() + this.usedConnections.size();
    }

    public int getCountFreeConnections() {
        return this.connectionQueue.size();
    }

    public int getCountUsedConnections() {
        return this.usedConnections.size();
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(this.connectionProperties.getProperty("db.url"), this.connectionProperties);
        } catch (SQLException e) {
            ExceptionHandler.handleException("Exception connecting DB", e);
        }
        return (Connection) Proxy.newProxyInstance(
                Connection.class.getClassLoader(),
                new Class<?>[]{Connection.class},
                new ConnectionHandler(connection)
        );
    }

    private class ConnectionHandler implements InvocationHandler {
        private final Connection connection;

        public ConnectionHandler(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("close".equals(method.getName())) {
                putBack((Connection) proxy);
                return null;
            } else {
                return method.invoke(connection, args);
            }
        }

    }


}
