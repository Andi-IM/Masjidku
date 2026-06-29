/*
 * Copyright (c) 2026. Creative Commons Universal CC0
 */

package org.masjidku.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Shared base DAO Factory class placed in common module to prevent code duplication.
 */
public abstract class DaoFactory {

    public DaoFactory() {
        getConnection();
    }

    protected Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;

    public boolean getConnection() {
        DatabaseConnection connection = new DatabaseConnection();
        if (connection.getConnection() != null) {
            con = connection.getConnection();
            return true;
        }
        return false;
    }
}
