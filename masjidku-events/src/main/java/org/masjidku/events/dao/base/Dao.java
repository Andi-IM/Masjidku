package org.masjidku.events.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Dao<T> {
    protected Connection con;
    protected String query = null;
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
