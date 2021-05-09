package org.masjidku.util;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  The MySQL Connection
 *  using connection with http://localhost:3306
 *
 * @author Andi Irham
 */
public class DatabaseConnection {
    static final private String url = "jdbc:mysql://localhost/masjidku";
    static final private String username = "root";
    static final private String password = "";

    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return java.sql.DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public ResultSet getQuery(Connection con, String sql) throws SQLException {
        return con.createStatement().executeQuery(sql);
    }
}
