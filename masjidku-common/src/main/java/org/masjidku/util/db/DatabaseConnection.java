package org.masjidku.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Database Connection Abstraction
 * Supports switching between MySQL and SQLite
 */
public class DatabaseConnection {
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
    public Connection dbLink;

    public interface ConnectionProvider {
        Connection getConnection() throws SQLException, ClassNotFoundException;
    }

    public static class MySQLProvider implements ConnectionProvider {
        @Override
        public Connection getConnection() throws SQLException, ClassNotFoundException {
            String dbName = "masjidku";
            String url = "jdbc:mysql://127.0.0.1:3306/" + dbName;
            String username = "root";
            String password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : ""; // using default password=root in GitHub
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                LOGGER.info("MySQL JDBC driver not explicitly found. Relying on DriverManager SPI...");
            }
            return DriverManager.getConnection(url, username, password);
        }
    }

    public static class SQLiteProvider implements ConnectionProvider {
        private Connection singleConnection = null;

        @Override
        public Connection getConnection() throws SQLException, ClassNotFoundException {
            if (singleConnection == null || singleConnection.isClosed()) {
                // using SQLite
                String url = "jdbc:sqlite:masjidku.db";
                try {
                    Class.forName("org.sqlite.JDBC");
                } catch (ClassNotFoundException e) {
                    LOGGER.info("SQLite JDBC driver not explicitly found. Relying on DriverManager SPI...");
                }
                singleConnection = DriverManager.getConnection(url);
                try (java.sql.Statement stmt = singleConnection.createStatement()) {
                    stmt.execute("PRAGMA busy_timeout = 10000;");
                    stmt.execute("PRAGMA journal_mode = WAL;");
                }
            }
            return singleConnection;
        }
    }

    // Abstraction point: easily switch DBMS by changing this instance
    private static ConnectionProvider activeProvider = new SQLiteProvider();

    public static void setProvider(ConnectionProvider provider) {
        activeProvider = provider;
    }

    public Connection getConnection() {
        try {
            dbLink = activeProvider.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to connect to database", e);
        }
        return dbLink;
    }
}
