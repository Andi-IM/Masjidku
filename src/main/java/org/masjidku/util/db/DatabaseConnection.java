/*
 * Copyright (c) 2021. Creative Commons Legal Code
 *
 *                            CC0 1.0 Universal
 *
 *                                CREATIVE COMMONS CORPORATION IS NOT A LAW FIRM AND DOES NOT PROVIDE
 *                                LEGAL SERVICES. DISTRIBUTION OF THIS DOCUMENT DOES NOT CREATE AN
 *                                ATTORNEY-CLIENT RELATIONSHIP. CREATIVE COMMONS PROVIDES THIS
 *                                INFORMATION ON AN "AS-IS" BASIS. CREATIVE COMMONS MAKES NO WARRANTIES
 *                                REGARDING THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS
 *                                PROVIDED HEREUNDER, AND DISCLAIMS LIABILITY FOR DAMAGES RESULTING FROM
 *                                THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS PROVIDED
 *                                HEREUNDER.
 */

package org.masjidku.util.db;

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
