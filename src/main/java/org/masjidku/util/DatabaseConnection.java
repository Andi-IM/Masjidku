package org.masjidku.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *  The MySQL Connection
 *  using connection with http://localhost:3306
 *
 * @author Andi Irham
 */
public class DatabaseConnection {
    public Connection dbLink;

    public Connection getConnection(){
        String dbName = "masjidku";
        String url = "jdbc:mysql://127.0.0.1:3306/"+dbName;
        String username = "root";
        String password = ""; // using default password=root in github

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbLink = java.sql.DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
        return dbLink;
    }
}
