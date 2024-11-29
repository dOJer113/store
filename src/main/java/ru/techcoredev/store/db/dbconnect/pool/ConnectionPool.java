package ru.techcoredev.store.db.dbconnect.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.db.dbconnect.DAOinterfeices.DBConfigurator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    public static final String FILE_PATH_PROPERTIES = "application.properties";
    private BlockingQueue<Connection> connectionQueue;
    private final List<Connection> usedConnections = new CopyOnWriteArrayList<>();
    private final Properties connectionProperties = DBConfigurator.getProperties(FILE_PATH_PROPERTIES);
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
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
        logger.info("Connection pool was initialized");
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
            logger.warn("Connection queue is full! Added new connection");
        } else {
            newConnection = this.connectionQueue.poll();
        }
        this.usedConnections.add(newConnection);
        return newConnection;
    }

    public synchronized void putBack(Connection connection) {
        if (connection != null) {
            if (this.usedConnections.remove(connection)) {
                if (!this.connectionQueue.offer(connection)) {
                    logger.error("connection can`t be offered");
                }
            } else {
                logger.error("Connection not in the used connection");
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
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(this.connectionProperties.getProperty("db.url"), this.connectionProperties);
        } catch (SQLException | ClassNotFoundException e) {
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
